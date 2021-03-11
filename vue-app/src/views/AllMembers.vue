<template>
    <div class="all-members">
        <card heading="Members..." subtitle="See a detailed list of members in your team.">
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
