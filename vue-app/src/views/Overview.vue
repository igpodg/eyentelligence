<template>
    <div class="overview">
        <card header="Header" subtitle="Subtitle">
            asd
        </card>
        <card-invite header="Invite new members"
                     subtitle="Have anyone missing? Just add them using the form below."
                     :team-names="['SuperTeam', 'Company Inc.', 'Europe Sales', 'Asia Sales']"/>
        <card header="Header" subtitle="Subtitle">
            <statistics-brief :data="statisticsData()"/>
        </card>
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
