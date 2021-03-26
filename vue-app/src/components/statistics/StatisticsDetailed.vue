<template>
    <div class="the-statistics">
        <div v-for="(stat, i) in data" :key="i"
             :class="(i === 0) ? 'row mb-4 mt-2' : ((i+1 !== data.length) ? 'row mb-4' : 'row mb-3')">
            <div class="col-1 align-self-center text-black">
                <i class="icon-list"></i>
            </div>
            <div class="col-11 align-self-center text-black">
                <p>{{ stat[0] }}</p>
                <span class="mb-0 first">{{ stat[1] }}</span>,&nbsp;
                <span class="f-w-7">{{ stat[2] }}</span>,&nbsp;
                <span class="font-12">{{ stat[3] }}</span>,&nbsp;
                <span class="f-w-7">{{ stat[4] }}</span>,&nbsp;
                <span class="font-12">{{ stat[5] }}</span>,&nbsp;
                <span class="f-w-7">{{ stat[6] }}</span>,&nbsp;
                <span class="font-12">{{ stat[7] }}</span>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: "StatisticsDetailed",
    props: {
        data: Array
    }
}
</script>

<style scoped lang="scss">
$color-statistics: #2b343a;

.the-statistics {
    >div {
        margin-left: 0;

        >div:nth-child(2) {
            font-size: 12px;

            >p:first-child {
                margin-bottom: 2px;
            }
        }
    }
}

.text-black {
    color: $color-statistics !important;
}

.f-w-7 {
    font-weight: 700;
}

.first {
    font-size: 17px;
    font-weight: 700;
}

</style>
