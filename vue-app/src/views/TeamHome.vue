<template>
    <div class="team-home">
        <div class="row">
            <div class="col-md-12 col-lg-12 col-xl-5">
                <card heading="Dashboards" subtitle="See a list of all dashboards available for the team.">
                    <form-element label="My Dashboard"/>
                    <form-element label="My Dashboard #2" last/>
                </card>
                <card heading="Statistics — Detailed" subtitle="The detailed usage statistics are shown here.
                    Determine how well your teamwork is going.">
                    <statistics-detailed :data="stats"/>
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
                    information about the team here." :edit-clicked="editClicked">
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
                                           :items="[]"
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
import StatisticsDetailed from "@/components/StatisticsDetailed.vue";
import FormElement from "@/components/FormElement.vue";
import FormRow from "@/components/FormRow.vue";
import FormInput from "@/components/FormInput.vue";
import FormDropdown from "@/components/FormDropdown.vue";
import FormTwoButtons from "@/components/FormTwoButtons.vue";

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
            stats: [
                ["First", "5.3 hrs", "+18.68%", "vs in last 7 days",
                    "-34.23%", "vs in last 31 days", "0.67%", "vs in last 365 days"],
                ["Overall", "6.4 hrs", "+23.54%", "vs in last 7 days",
                    "+1.34%", "vs in last 31 days", "0.00%", "vs in last 365 days"],
                ["Last year only", "12.8 hrs", "+5.29%", "vs in last 7 days",
                    "+0.33%", "vs in last 31 days", "0.00%", "vs in last 365 days"],
                ["Everything", "23.1 hrs", "+1.53%", "vs in last 7 days",
                    "0.00%", "vs in last 31 days", "0.00%", "vs in last 365 days"]
            ]
        }
    },
    methods: {
        updateTeamValues: function() {
            let team = this.getTeamById(this.$route.params.id);
            this.teamId = parseInt(this.$route.params.id);
            this.teamName = team.name;
            this.teamType = team.type;
            this.teamParent = team.parentTeam;
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
                return "— Root";

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
            }

            this.$logDetailed("Renaming a Team ...");
            this.$fetchSync("https://127.0.0.1:9090/team/" + this.teamId, {
                method: "PUT",
                headers: { "X-API-Key": "xxxxxxxx" },
                body: JSON.stringify({
                    //id: this.teamId,
                    name: this.teamName,
                    type: this.teamType
                    //parentTeamId: this.teamParent
                })
            });
            this.$logDetailed("Team renaming finished.");
            this.$eventBus.$emit("update-teams");
            this.editEnabled = false;
            return false;
        },
        editClicked: function() {
            this.$logDetailed("edit clicked");
            this.editEnabled = true;
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
    },
    beforeDestroy: function() {
        this.$eventBus.$off("edit-cancelled");
    },
    watch: {
        $route: function() {
            this.updateTeamValues();
        }
    }
}
</script>

<style scoped lang="scss">
.edit-team-body {
    padding-left: 15px;
}

</style>
