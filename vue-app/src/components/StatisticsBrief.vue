<template>
    <div class="the-statistics">
        <div v-for="(stat, i) in data" :key="stat"
             :class="(i === 0) ? 'row mb-5 mt-2' : ((i+1 !== data.length) ? 'row mb-5' : 'row mb-3')">
            <div class="col-1 align-self-center text-black">
                <i class="icon-list ml-3"></i>
            </div>
            <div class="col-md-6 col-lg-5 align-self-center text-black">
                <h4 class="mb-0 font-26">{{ stat.left[0] }}</h4>
                <p class="mb-2">{{ stat.left[1] }}</p>
                <p class="mb-0">
                    <span class="f-w-7">{{ stat.left[2] }}</span>&nbsp;
                    <span class="font-12">{{ stat.left[3] }}</span>
                </p>
            </div>
            <div class="col-md-6 col-lg-5 align-self-center text-black">
                <h4 class="mb-0 font-26">{{ stat.right[0] }}</h4>
                <p class="mb-2">{{ stat.right[1] }}</p>
                <p class="mb-0">
                    <span class="f-w-7">{{ stat.right[2] }}</span>&nbsp;
                    <span class="font-12">{{ stat.right[3] }}</span>
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
.h4, h4 {
    font-weight: 700;
}

h4 {
    font-size: 24px;
}

.font-12 {
    font-size: 12px;
}

.font-26 {
    font-size: 26px;
}

.f-w-7 {
    font-weight: 700;
}

.text-black {
    color: #2B343A !important;
}

@media (max-width: 991px) {
    h4 {
        font-size: 22px;
    }
}

@media (max-width: 767px) {
    h4 {
        font-size: 20px;
    }
}

</style>
