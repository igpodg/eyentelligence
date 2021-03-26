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
                    <statistics-detailed :data="generateStats()"/>
                </card>
            </div>
            <div class="col-md-12 col-lg-12 col-xl-7">
                <card heading="Last Activity" subtitle="Here you can see what recent actions
                    have been taken by the team members.">
                    <div class="team-home-body">
                        <activity-row name="Anderea Carson" :rank="1" last
                                      tip="Resized tile &quot;Yearly&quot;" time="Today, 14:32"/>
                        <hr>
                        <activity-row name="John Johnson" :rank="2" small
                                      tip="Changed &quot;My Dashboard #2&quot; visibility" time="Mon, 09:46"/>
                        <activity-row name="Emily Watson" :rank="3" small
                                      tip="No activity" time="Mon, 08:50"/>
                        <activity-row name="Mike Anders" :rank="4" small last
                                      tip="Resized tile &quot;Monthly Sales&quot;" time="Thu, 16:26"/>
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
                                           unselected="— Root"
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
            teamTypes: [
                {id: "O", label: "Organization"},
                {id: "T", label: "Regular team"}
            ],
            editEnabled: false,
            getTeamById: null,
            teamName: "",
            teamType: "",
            teamParent: null,
            stats: {
                "first": {"total": 5.3, "7days": 0.1868, "31days": -0.3423, "365days": 0.2354},
                "overall": {"total": 6.4, "7days": 0.2354, "31days": 0.0134, "365days": 0},
                "lastYear": {"total": 12.8, "7days": 0.0529, "31days": 0.0033, "365days": 0},
                "everything": {"total": 23.1, "7days": 0.0153, "31days": 0, "365days": 0}
            },
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
                return "—– Root";

            return "Team " + parentTeam.name;
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
        },
        generateStats: function() {
            return [
                ["First", this.stats.first.total + " hrs",
                    this.stats.first["7days"]*100 + "%",
                    "vs in last 7 days",
                    this.stats.first["31days"]*100 + "%",
                    "vs in last 31 days",
                    this.stats.first["365days"]*100 + "%",
                    "vs in last 365 days"],
                ["Overall", this.stats.overall.total + " hrs",
                    this.stats.overall["7days"]*100 + "%",
                    "vs in last 7 days",
                    this.stats.overall["31days"]*100 + "%",
                    "vs in last 31 days",
                    this.stats.overall["365days"]*100 + "%",
                    "vs in last 365 days"],
                ["Last year only", this.stats.lastYear.total + " hrs",
                    this.stats.lastYear["7days"]*100 + "%",
                    "vs in last 7 days",
                    this.stats.lastYear["31days"]*100 + "%",
                    "vs in last 31 days",
                    this.stats.lastYear["365days"]*100 + "%",
                    "vs in last 365 days"],
                ["Everything", this.stats.everything.total + " hrs",
                    this.stats.everything["7days"]*100 + "%",
                    "vs in last 7 days",
                    this.stats.everything["31days"]*100 + "%",
                    "vs in last 31 days",
                    this.stats.everything["365days"]*100 + "%",
                    "vs in last 365 days"]
            ];
        }
    },
    mounted: function() {
        this.$eventBus.$on("edit-cancelled", this.editCancelled);
        this.getTeamById = this.$root.$children[0].getTeamById;
        this.updateTeamValues();
        this.updateDashboards();
    },
    beforeDestroy: function() {
        this.$eventBus.$off("edit-cancelled");
    },
    watch: {
        $route: function() {
            this.editCancelled();
            this.updateTeamValues();
            this.updateDashboards();
        }
    }
}
</script>

<style scoped lang="scss">
.edit-team-body {
    padding-left: 15px;
}

</style>
