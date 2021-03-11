<template>
    <div class="row">
        <div :class="'col-' + (12-size)"></div>
        <div :class="'col-sm-12 col-md-' + size">
            <button type="submit" v-if="directclick !== null" @click="buttonClicked"
                    :class="'btn btn-' + color + ' btn-rounded btn-lg btn-block'">{{ label }}</button>
            <button type="submit" v-else
                    :class="'btn btn-' + color + ' btn-rounded btn-lg btn-block'">{{ label }}</button>
        </div>
    </div>
</template>

<script>
export default {
    name: "FormButton",
    props: {
        label: String,
        size: Number,
        color: String,
        directclick: {
            type: Number,
            default: null
        }
    },
    methods: {
        buttonClicked: function() {
            this.$eventBus.$emit("delete-dashboard", this.directclick)
        }
    }
}
</script>

<style scoped lang="scss">
.row {
    padding-top: 20px;
    padding-bottom: 5px;
}

</style>
