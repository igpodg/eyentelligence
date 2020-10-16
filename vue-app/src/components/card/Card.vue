<template>
    <div :class="'card m-b-30' + (inner ? ' inner-card' : '')">
        <div class="card-header bg-white">
            <h5 class="card-title text-black">{{ heading }}</h5>
            <h6 class="card-subtitle" v-if="subtitle !== undefined">{{ subtitle }}</h6>
        </div>

        <div class="card-body">
            <slot></slot>
        </div>
    </div>
</template>

<script>
export default {
    name: "Card",
    props: {
        heading: String,
        subtitle: String,
        inner: {
            type: Boolean,
            default: false
        }
    }
}
</script>

<style scoped lang="scss">
.inner-card {
    width: 290px !important;
    float: left;
}

.inner-card > .card-header {
    padding-bottom: 0;
}

.inner-card > .card-body {
    padding: 0;
    text-align: center;
}



.h5, .h6, h5, h6 {
    font-weight: 700;
}

h5 {
    font-size: 20px;
}

h6 {
    font-size: 16px;
}

.xp-rightbar {
    overflow: hidden;
    min-height: 500px;
    transition: all 0.3s ease;
}

.xp-contentbar {
    padding: 30px;
    margin-bottom: 30px;
}

.m-b-30 {
    margin-bottom: 30px;
}

.bg-white {
    background-color: #ffffff !important;
}

.text-black {
    color: #2B343A !important;
}

.card {
    border: none;
    box-shadow: 0 0 30px 0 rgba(200, 200, 200, 0.2);
    border-radius: 15px;
}

.card-header {
    border-bottom: 1px solid rgba(43, 52, 58, 0.05);
}

.card-header:first-child {
    border-radius: calc(15px - 1px) calc(15px - 1px) 0 0;
}

.card-title {
    font-size: 16px;
}

.card-subtitle {
    font-size: 13px;
    line-height: 20px;
    font-weight: 400;
    color: #8A98AC;
}

@media (max-width: 991px) {
    h5 {
        font-size: 18px;
    }

    h6 {
        font-size: 16px;
    }
}

@media (max-width: 767px) {
    h5 {
        font-size: 16px;
    }

    h6 {
        font-size: 15px;
    }
}

</style>
