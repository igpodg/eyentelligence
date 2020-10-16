<template>
    <div :class="'form-group' + ((cols !== undefined) ? ' col-' + cols : '')">
        <label :for="id"></label>
        <select class="form-control" :id="id">
            <option>{{ name }}</option>
            <option v-for="opt in data" :key="opt">{{ opt }}</option>
        </select>
    </div>
</template>

<script>
export default {
    name: "SecondaryDropdown",
    props: {
        cols: Number,
        id: String,
        name: String,
        data: Array
    }
}
</script>

<style scoped lang="scss">

</style>
