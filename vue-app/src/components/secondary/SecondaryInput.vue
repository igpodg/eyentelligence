<template>
    <div :class="'form-group' + ((cols !== undefined) ? ' col-' + cols : '')">
        <label :for="id"></label>
        <input :type="type" class="form-control" :id="id"
               :placeholder="name" :required="isRequired">
    </div>
</template>

<script>
export default {
    name: "SecondaryInput",
    props: {
        cols: Number,
        id: String,
        name: String,
        type: {
            type: String,
            default: "text"
        },
        isRequired: {
            type: Boolean,
            default: false
        }
    }
}
</script>

<style scoped lang="scss">

</style>
