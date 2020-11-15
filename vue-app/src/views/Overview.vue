<template>
    <div class="overview">
        <div class="row">
            <div class="col-md-12 col-lg-12 col-xl-7">
                <card heading="Recent Changes" subtitle="Here you can see the changes you've made in the past week.">
                    <card heading="My Dashboard #2" inner>
                        <div style="text-align: center;">
                            <img src="assets/img/tempthumb.png">
                        </div>
                    </card>
                    <span style="width: 30px; float: left; visibility: hidden;">.</span>
                    <card heading="My Dashboard" inner>
                        <div style="text-align: center;">
                            <img src="assets/img/tempthumb.png">
                        </div>
                    </card>
                </card>
                <card heading="Statistics — General" subtitle="The brief statistics of usage.
                    For more information, visit the appropriate section.">
                    <statistics-brief :data="statisticsData()"/>
                </card>
            </div>
            <div class="col-md-12 col-lg-12 col-xl-5">
                <card-invite header="Invite new members"
                             subtitle="Have anyone missing? Just add them using the form below."
                             :team-names="['SuperTeam', 'Company Inc.', 'Europe Sales', 'Asia Sales']"/>
            </div>
        </div>
    </div>
</template>

<script>
import Card from "@/components/card/Card.vue";
import CardInvite from "@/components/card/CardInvite.vue";
import StatisticsBrief from "@/components/StatisticsBrief.vue";

export default {
    name: "Overview",
    components: {
        Card,
        CardInvite,
        StatisticsBrief
    },
    methods: {
        statisticsData: function() {
            return [
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
            ];
        }
    }
}
</script>
