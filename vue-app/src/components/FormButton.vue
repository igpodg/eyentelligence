<template>
    <div class="row">
        <div :class="'col-' + (12-size)"></div>
        <div :class="'col-sm-12 col-md-' + size">
            <button type="submit"
                    class="btn btn-success btn-rounded btn-lg btn-block">{{ label }}</button>
        </div>
    </div>
</template>

<script>
export default {
    name: "FormButton",
    props: {
        label: String,
        size: Number
    }
}
</script>

<style scoped lang="scss">
.row {
    padding-top: 30px;
}

</style>
