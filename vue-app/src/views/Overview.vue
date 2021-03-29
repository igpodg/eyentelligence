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
                    <statistics-brief :data="statisticsDataMock"/>
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
            statisticsDataMock: [
                {"left": {"average": 5.3, "7days": 0.1868}, "right": {"average": 6.4, "7days": 0.2354}},
                {"left": {"average": 2.1, "7days": 0}, "right": {"average": 4.9, "7days": 0.4534}},
                {"left": {"average": 11.2, "7days": 0.5643}, "right": {"average": 0.4, "7days": 0.9832}}
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
