<template>
    <div class="the-statistics">
        <div v-for="(stat, i) in data" :key="i"
             :class="(i === 0) ? 'row mb-5 mt-2' : ((i+1 !== data.length) ? 'row mb-5' : 'row mb-3')">
            <div class="col-1 align-self-center text-black">
                <i class="icon-list ml-3"></i>
            </div>
            <div class="col-md-6 col-lg-5 align-self-center text-black">
                <h4 class="mb-0 font-26">{{ stat.left["average"] }} hrs</h4>
                <p class="mb-2">Avg Members Sessions</p>
                <p class="mb-0">
                    <span class="f-w-7">{{ $convertToPercentage(stat.left["7days"]) }}</span>&nbsp;
                    <span class="font-12">vs in last 7 days</span>
                </p>
            </div>
            <div class="col-md-6 col-lg-5 align-self-center text-black">
                <h4 class="mb-0 font-26">{{ stat.right["average"] }} hrs</h4>
                <p class="mb-2">Avg Members Sessions</p>
                <p class="mb-0">
                    <span class="f-w-7">{{ $convertToPercentage(stat.right["7days"]) }}</span>&nbsp;
                    <span class="font-12">vs in last 7 days</span>
                </p>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: "StatisticsBrief",
    props: {
        data: Array
    }
}
</script>

<style scoped lang="scss">
$color-statistics: #2b343a;

.text-black {
    color: $color-statistics !important;
}

//@each $size in (12px, 26px) {
//    .font-#{$size} {
//        font-size: $size;
//    }
//}

.f-w-7 {
    font-weight: 700;
}

</style>
