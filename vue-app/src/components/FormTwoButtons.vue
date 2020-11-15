<template>
    <div class="row">
        <div :class="'col-' + (12-size-size)"></div>
        <div :class="'col-sm-12 col-md-' + size">
            <button type="submit"
                    class="btn btn-outline-secondary btn-rounded btn-lg btn-block">{{ label1 }}</button>
        </div>
        <div :class="'col-sm-12 col-md-' + size">
            <button type="submit"
                    class="btn btn-success btn-rounded btn-lg btn-block">{{ label2 }}</button>
        </div>
    </div>
</template>

<script>
export default {
    name: "FormTwoButtons",
    props: {
        label1: String,
        label2: String,
        size: Number
    }
}
</script>

<style scoped lang="scss">
.row {
    padding-top: 20px;
    padding-bottom: 5px;
}

.col-sm-12 {
    padding-left: 0;
}

.btn-outline-secondary {
    box-shadow: 0 2px 6px 0 rgba(#c8c8c8, 0.5);
}

</style>
