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
                    information about the team here." :edit-pressed="edittt">
                    <form class="edit-team-body" ref="editform" @submit="formSubmitted">
                        <form-row label="Name" :size="2">
                            <form-input id="createTeamName" is-required
                                        tip="This is a required field.
                                            It will be displayed across the system."/>
                        </form-row>
                        <form-row label="Type" :size="2">
                            <form-dropdown id="createTeamType" is-required
                                           :items="[{id: 'A', label: 'B'}]"
                                           tip="This is a required field."/>
                        </form-row>
                        <form-row label="Location" :size="2">
                            <form-dropdown id="createTeamParent"
                                           :items="[]"/>
                        </form-row>
                        <form-two-buttons label1="Cancel" label2="Change" :size="4"/>
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
        formSubmitted: function(event) {
            event.preventDefault();
            this.$logDetailed("submitted");
            return false;
        },
        edittt: function() {
            this.$logDetailed("a");
        }
    }
}
</script>

<style scoped lang="scss">
.edit-team-body {
    padding-left: 15px;
}

</style>
