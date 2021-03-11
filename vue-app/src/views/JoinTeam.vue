<template>
    <div class="join-team">
        <card heading="Select a team" subtitle="Please select a team to join from the available list.">
            <div class="join-card">
                <h6>. . .</h6>
            </div>
        </card>
    </div>
</template>

<script>
import Card from "@/components/card/Card.vue";

export default {
    name: "JoinTeam",
    components: {
        Card
    }
}
</script>

<style scoped lang="scss">
.join-card {
    $paddingval: 40px;
    padding-top: $paddingval;
    padding-bottom: $paddingval;

    padding-left: 40px;
}

</style>
