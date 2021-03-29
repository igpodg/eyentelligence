<template>
    <div class="team-home">
        <div class="row">
            <div class="col-md-12 col-lg-12 col-xl-5">
                <card heading="Dashboards" subtitle="See a list of all dashboards available for the team.">
                    <div v-if="dashboards.length === 0">
                        <span>No dashboards found.</span>&nbsp;
                        <router-link :to="'/team/' + this.$route.params.id + '/dashboards'">
                            View details...</router-link>
                    </div>
                    <div v-else v-for="(dash, i) in dashboards" :key="dash.id">
                        <router-link :to="'/team/' + dash.team.id + '/dashboard/' + dash.id">
                            <form-element :label="dash.name"
                                          :last="i === dashboards.length-1"/>
                        </router-link>
                    </div>
                </card>
                <card heading="Statistics — Detailed" subtitle="The detailed usage statistics are shown here.
                    Determine how well your teamwork is going.">
                    <statistics-detailed :data="statsMock"/>
                </card>
            </div>
            <div class="col-md-12 col-lg-12 col-xl-7">
                <card heading="Last Activity" subtitle="Here you can see what recent actions
                    have been taken by the team members.">
                    <div class="team-home-body" v-if="activitySecondary !== null">
                        <activity-row :name="convertName(this.activityMain.user)" :rank="1" last
                                      :tip="getTipString(this.activityMain.type, this.activityMain.subType)"
                                      :time="convertTime(this.activityMain.dateTime)"/>
                        <hr v-if="activitySecondary.length !== 0">
                        <activity-row v-for="(activity, i) in activitySecondary" :key="i"
                                      :name="convertName(activity.user)" :rank="i+2" small
                                      :tip="getTipString(activity.type, activity.subType)"
                                      :time="convertTime(activity.dateTime)"
                                      :last="i === activitySecondary.length-1"/>
                    </div>
                </card>
                <card heading="Team Information" subtitle="View or edit basic
                    information about the team here." :perform-func="editClicked"
                      button-icon="pencil" button-text="Edit">
                    <form class="edit-team-body" ref="editform" @submit="formSubmitted">
                        <form-row label="Name" :size="2" :centered="!editEnabled">
                            <form-input id="createTeamName" is-required
                                        tip="This is a required field.
                                            It will be displayed across the system."
                                        :value="this.teamName"
                                        v-if="editEnabled"/>
                            <span v-else>{{ this.teamName }}</span>
                        </form-row>
                        <form-row label="Type" :size="2" :centered="!editEnabled">
                            <form-dropdown id="createTeamType" is-required
                                           :items="teamTypes"
                                           tip="This is a required field."
                                           :value="this.teamType"
                                           v-if="editEnabled"/>
                            <span v-else>{{ convertTeamType(this.teamType) }}</span>
                        </form-row>
                        <form-row label="Location" :size="2" :centered="!editEnabled">
                            <form-dropdown id="createTeamParent"
                                           :items="this.teamLabels"
                                           :unselected="rootLabel"
                                           :value="this.currentTeamParent"
                                           v-if="editEnabled"/>
                            <span v-else>{{ convertParent(this.teamParent) }}</span>
                        </form-row>
                        <form-two-buttons label1="Cancel" label2="Change" :size="4"
                                          v-if="editEnabled"/>
                    </form>
                </card>
            </div>
        </div>
    </div>
</template>

<script>
import Card from "@/components/card/Card.vue";
import ActivityRow from "@/components/ActivityRow.vue";
import StatisticsDetailed from "@/components/statistics/StatisticsDetailed.vue";
import FormElement from "@/components/form/FormElement.vue";
import FormRow from "@/components/form/FormRow.vue";
import FormInput from "@/components/form/FormInput.vue";
import FormDropdown from "@/components/form/FormDropdown.vue";
import FormTwoButtons from "@/components/form/FormTwoButtons.vue";

export default {
    name: "TeamHome",
    components: {
        Card,
        ActivityRow,
        StatisticsDetailed,
        FormElement,
        FormRow,
        FormInput,
        FormDropdown,
        FormTwoButtons
    },
    data: function() {
        return {
            rootLabel: "—– Root",
            teamTypes: [
                {id: "O", label: "Organization"},
                {id: "T", label: "Regular team"}
            ],
            editEnabled: false,
            getTeamById: null,
            teamName: "",
            teamType: "",
            teamParent: null,
            statsMock: [
                {"name": "first", "label": "First",
                    "total": 5.3, "7days": 0.1868, "31days": -0.3423, "365days": 0.2354},
                {"name": "overall", "label": "Overall",
                    "total": 6.4, "7days": 0.2354, "31days": 0.0134, "365days": 0},
                {"name": "lastYear", "label": "Last year only",
                    "total": 12.8, "7days": 0.0529, "31days": 0.0033, "365days": 0},
                {"name": "everything", "label": "Everything",
                    "total": 23.1, "7days": 0.0153, "31days": 0, "365days": 0}
            ],
            activityMock: [
                {"type": 2, "subType": {"id": 790, "name": "Yearly"},
                    "user": {"id": 990, "firstName": "Anderea", "middleName": "Matilda", "lastName": "Carson"},
                    "dateTime": 1617012742},
                {"type": 1, "subType": {"id": 890, "name": "My Dashboard #2"},
                    "user": {"id": 991, "firstName": "John", "middleName": null, "lastName": "Johnson"},
                    "dateTime": 1616906787},
                {"type": 0, "subType": null,
                    "user": {"id": 992, "firstName": "Emily", "middleName": null, "lastName": "Watson"},
                    "dateTime": 1616482216},
                {"type": 2, "subType": {"id": 791, "name": "Monthly Sales"},
                    "user": {"id": 993, "firstName": "Mike", "middleName": null, "lastName": "Anders"},
                    "dateTime": 1616705805}
            ],
            activityMain: null,
            activitySecondary: null,
            teamLabels: [],
            currentTeamParent: null,
            dashboards: []
        }
    },
    methods: {
        updateTeamValues: function() {
            let teamId = parseInt(this.$route.params.id);
            let team = this.getTeamById(teamId);
            this.teamId = teamId;
            this.teamName = team.name;
            this.teamType = team.type;
            this.teamParent = team.parentTeam;
        },
        updateDashboards: function() {
            let teamId = parseInt(this.$route.params.id);
            this.teamId = teamId;
            if (teamId in this.$root.$children[0].dashboards)
                this.dashboards = this.$root.$children[0].dashboards[teamId];
            else
                this.dashboards = [];
        },
        updateActivity: function() {
            this.$logDetailed("updating activity...");
            if (this.activityMock.length > 0) {
                this.activityMain = this.activityMock[0];
                this.activitySecondary = this.activityMock.slice(1);
            }
            this.$logDetailed("updated activity!");
        },
        convertTeamType: function(id) {
            for (let type of this.teamTypes) {
                if (type.id === id)
                    return type.label;
            }
            return null;
        },
        convertParent: function(parentTeam) {
            //let parentTeam = this.getTeamById(id);
            //console.log(parentTeam);
            if (parentTeam === null)
                return this.rootLabel;

            return "Team " + parentTeam.name;
        },
        convertTime: function(timestamp) {
            let original = new Date(timestamp*1000);

            let today = new Date();
            today.setHours(0, 0, 0, 0);

            let yesterday = new Date(today.getTime());
            yesterday.setDate(yesterday.getDate()-1);

            let compared = new Date(timestamp*1000);
            compared.setHours(0, 0, 0, 0);
            let comparedTs = compared.getTime();

            if (comparedTs === today.getTime()) {
                return "Today, " + original.toLocaleString("default", {
                    hour: "numeric",
                    hourCycle: "h23",
                    minute: "numeric"
                });
            } else if (comparedTs === yesterday.getTime()) {
                return "Yesterday, " + original.toLocaleString("default", {
                    hour: "numeric",
                    hourCycle: "h23",
                    minute: "numeric"
                });
            } else if (compared.getFullYear() === today.getFullYear()) {
                return original.toLocaleString("default", {
                    day: "numeric",
                    month: "short",
                    hour: "numeric",
                    hourCycle: "h23",
                    minute: "numeric"
                });
            } else {
                return original.toLocaleString("en-GB", {
                    day: "numeric",
                    month: "numeric",
                    year: "numeric",
                    hour: "numeric",
                    hourCycle: "h23",
                    minute: "numeric"
                });
            }
        },
        convertName: function(user) {
            let middleName = "";
            if (user.middleName !== null) {
                middleName = user.middleName.trim()
                    .charAt(0).toUpperCase() + ". ";
            }
            return user.firstName + " " + middleName + user.lastName;
        },
        getTipString: function(type, subType) {
            switch (type) {
                case 0:
                    return "No activity";
                case 1:
                    return "Changed \"" + subType.name + "\" visibility";
                case 2:
                    return "Resized tile \"" + subType.name + "\"";
            }
        },
        formSubmitted: function(event) {
            event.preventDefault();
            this.$logDetailed("submitted");
            for (let el of this.$refs.editform.elements) {
                //console.log(el.value);
                if (el.id === "createTeamName")
                    this.teamName = el.value;
                else if (el.id === "createTeamType")
                    this.teamType = el.value;
                else if (el.id === "createTeamParent")
                    this.currentTeamParent = el.value;
            }

            this.$logDetailed("Updating a Team ...");
            this.$fetchSync("https://127.0.0.1:9090/team/" + this.teamId, {
                method: "PUT",
                headers: { "X-API-Key": "xxxxxxxx" },
                body: JSON.stringify({
                    //id: this.teamId,
                    name: this.teamName,
                    type: this.teamType,
                    parentTeam: (this.currentTeamParent === "") ? null
                        : {"id": parseInt(this.currentTeamParent)}
                })
            });
            this.$logDetailed("Team updating finished.");
            this.$eventBus.$emit("update-teams");
            this.updateTeamValues();
            this.editEnabled = false;
            return false;
        },
        editClicked: function() {
            this.$logDetailed("edit clicked");
            this.editEnabled = true;

            this.teamLabels = this.$root.$children[0].teams.map(function(team) {
                return {
                    id: team.id,
                    label: "Team " + team.name
                };
            });
            if (this.teamParent !== null)
                this.currentTeamParent = this.teamParent.id.toString();
            else
                this.currentTeamParent = null;
        },
        editCancelled: function() {
            this.$logDetailed("cancelled!");
            this.editEnabled = false;
        }
    },
    mounted: function() {
        this.$eventBus.$on("edit-cancelled", this.editCancelled);
        this.getTeamById = this.$root.$children[0].getTeamById;
        this.updateTeamValues();
        this.updateDashboards();
        this.updateActivity();
    },
    beforeDestroy: function() {
        this.$eventBus.$off("edit-cancelled");
    },
    watch: {
        $route: function() {
            this.editCancelled();
            this.updateTeamValues();
            this.updateDashboards();
            this.updateActivity();
        }
    }
}
</script>

<style scoped lang="scss">
.edit-team-body {
    padding-left: 15px;
}

</style>
