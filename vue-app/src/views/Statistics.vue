<template>
    <div class="statistics">
        <card heading="Eyentelligence Statistics" subtitle="Here you can see the global statistics of usage.">
            <div class="stat-card">
                <h6>. . .</h6>
            </div>
        </card>
    </div>
</template>

<script>
import Card from "@/components/card/Card.vue";

export default {
    name: "Statistics",
    components: {
        Card
    }
}
</script>

<style scoped lang="scss">
.stat-card {
    $paddingval: 80px;
    padding-top: $paddingval;
    padding-bottom: $paddingval;

    padding-left: 40px;
}

</style>
