<template>
    <div class="overview">
        <div class="row">
            <div class="col-md-12 col-lg-12 col-xl-7">
                <card heading="Recent Changes" subtitle="Here you can see the changes you've made in the past week.">
                    <card heading="My Dashboard #2" inner>
                        <div class="dashboard-card">
                            <img src="assets/img/tempthumb.png">
                        </div>
                    </card>
                    <span class="dashboard-card-sep">.</span>
                    <card heading="My Dashboard" inner>
                        <div class="dashboard-card">
                            <img src="assets/img/tempthumb.png">
                        </div>
                    </card>
                </card>
                <card heading="Statistics — General" subtitle="The brief statistics of usage.
                    For more information, visit the appropriate section.">
                    <statistics-brief :data="statisticsData"/>
                </card>
            </div>
            <div class="col-md-12 col-lg-12 col-xl-5">
                <card-invite header="Invite new members"
                             subtitle="Have anyone missing? Just add them using the form below."
                             :teams="availableTeams"/>
            </div>
        </div>
    </div>
</template>

<script>
import Card from "@/components/card/Card.vue";
import CardInvite from "@/components/card/CardInvite.vue";
import StatisticsBrief from "@/components/statistics/StatisticsBrief.vue";

export default {
    name: "Overview",
    components: {
        Card,
        CardInvite,
        StatisticsBrief
    },
    data: function() {
        return {
            statisticsData: [
                {
                    "left": ["5.3 hrs", "Avg Members Sessions", "+18.68%", "vs in last 7 days"],
                    "right": ["6.4 hrs", "Avg Members Sessions", "+23.54%", "vs in last 7 days"]
                },
                {
                    "left": ["2.1 hrs", "Avg Members Sessions", "+56.21%", "vs in last 7 days"],
                    "right": ["4.9 hrs", "Avg Members Sessions", "+45.34%", "vs in last 7 days"]
                },
                {
                    "left": ["11.2 hrs", "Avg Members Sessions", "+56.43%", "vs in last 7 days"],
                    "right": ["0.4 hrs", "Avg Members Sessions", "+98.32%", "vs in last 7 days"]
                }
            ],
            availableTeams: []
        }
    },
    methods: {
        updateTeamNames: function() {
            this.availableTeams = this.$root.$children[0].teams;
        }
    },
    created: function() {
        this.updateTeamNames();
    },
    mounted: function() {
        this.$eventBus.$on("update-teams-overview", this.updateTeamNames);
    },
    beforeDestroy: function() {
        this.$eventBus.$off("update-teams-overview");
    },
}
</script>

<style scoped lang="scss">
$thumbnail-height: 190px;

.dashboard-card {
    text-align: center;
    min-height: $thumbnail-height;

    &-sep {
        width: 30px;
        float: left;
        visibility: hidden;
    }
}

</style>
