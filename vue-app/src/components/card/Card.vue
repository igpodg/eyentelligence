<template>
    <div :class="'card' + (inner ? ' inner-card' : '')">
        <div class="card-header">
            <div v-if="editClicked === null">
                <h5 class="card-title">{{ heading }}</h5>
                <h6 class="card-subtitle" v-if="subtitle !== undefined">{{ subtitle }}</h6>
            </div>
            <div class="row" v-else>
                <div class="col-9">
                    <h5 class="card-title">{{ heading }}</h5>
                    <h6 class="card-subtitle" v-if="subtitle !== undefined">{{ subtitle }}</h6>
                </div>
                <div class="col-3">
                    <div style="float: right;">
                        <button type="button" class="btn btn-rounded btn-outline-primary"
                                @click="editClicked">
                            <i class="icon-pencil mr-2"></i>&nbsp;Edit
                        </button>
                    </div>
                </div>
            </div>
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
        },
        editClicked: {
            type: Function,
            default: null
        }
    }
}
</script>

<style scoped lang="scss">
$color-white: #ffffff;

$color-card-shadow: #c8c8c8;
$color-card-header-border: #2b343a;

.card {
    border: none;
    box-shadow: 0 0 30px 0 rgba($color-card-shadow, 0.2);
    border-radius: 15px;

    margin-bottom: 30px;
}

.inner-card {
    width: 290px !important;
    float: left;

    >.card-header {
        padding-bottom: 0;
    }

    >.card-body {
        padding: 0;
        text-align: center;
    }
}

.card-header {
    border-bottom: 1px solid rgba($color-card-header-border, 0.05);
    background-color: $color-white !important;

    &:first-child {
        border-radius: calc(15px - 1px) calc(15px - 1px) 0 0;
    }
}

$color-card-title: #2b343a;
$color-card-subtitle: #8a98ac;

.card-title {
    font-size: 16px;
    color: $color-card-title !important;
}

.card-subtitle {
    font-size: 13px;
    line-height: 20px;
    font-weight: 400;
    color: $color-card-subtitle;
}

</style>
