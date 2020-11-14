<template>
    <div class="row">
        <div :class="'col-sm-12 col-md-' + size">
            <span class="form-control-plaintext">{{ label }}:</span>
        </div>
        <div :class="'col-' + (12-size)">
            <slot></slot>
        </div>
    </div>
</template>

<script>
export default {
    name: "FormRow",
    props: {
        label: String,
        size: Number
    }
}
</script>

<style scoped lang="scss">
.row {
    padding-bottom: 15px;

    >div:first-child {
        padding-right: 0;
    }
}

</style>
