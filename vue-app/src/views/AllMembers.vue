<template>
    <div class="all-members">
        <card :heading="teamName + '\'s Members'"
              subtitle="See a detailed list of members in your team.">
            <div class="allmembers-card">
                <h6>. . .</h6>
            </div>
        </card>
    </div>
</template>

<script>
import Card from "@/components/card/Card.vue";

export default {
    name: "AllMembers",
    components: {
        Card
    },
    data: function() {
        return {
            teamId: null,
            getTeamById: null,
            teamName: ""
        }
    },
    methods: {
        updateTeamIdName: function() {
            let teamId = parseInt(this.$route.params.id);
            this.teamId = teamId;
            let team = this.getTeamById(teamId);
            this.teamName = team.name;
        }
    },
    mounted: function() {
        this.getTeamById = this.$root.$children[0].getTeamById;
        this.updateTeamIdName();
    },
    watch: {
        $route: function() {
            this.updateTeamIdName();
        }
    }
}
</script>

<style scoped lang="scss">
.allmembers-card {
    $paddingval: 60px;
    padding-top: $paddingval;
    padding-bottom: $paddingval;

    padding-left: 40px;
}

</style>
