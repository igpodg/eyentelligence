<template>
    <div :class="'card inner-card' + (last ? ' last' : '')">
        <div class="card-body">
            <div class="row">
                <div class="col-3"><i class="icon-list"></i></div>
                <div class="col-9 label">{{ label }}</div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: "FormElement",
    props: {
        label: String,
        last: {
            type: Boolean,
            default: false
        }
    }
}
</script>

<style scoped lang="scss">
$color-main-text: #2b343a;
$color-vertical-line: #adadad;
$color-shadow: #c8c8c8;

.inner-card {
    width: 100% !important;
    margin-bottom: 20px;
    box-shadow: 0 0 30px 0 rgba($color-shadow, 0.22);
    cursor: pointer;
    &.last {
        margin-bottom: 10px;
    }

    .card-body {
        padding: 0.75rem 1.25rem !important;

        .row {
            >div:first-child {
                border-right: 1.5px solid $color-vertical-line;
                text-align: center;

                i {
                    position: relative;
                    top: 30%;
                }
            }

            .label {
                padding-top: 0.8rem !important;
                padding-bottom: 0.8rem !important;
                //text-align: left;

                font-size: 16px;
                font-weight: 700;
                color: $color-main-text !important;
            }
        }
    }
}

</style>
