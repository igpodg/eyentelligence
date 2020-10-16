<template>
    <button type="submit"
            :class="'btn btn-' + color + ' btn-rounded btn-lg btn-block'">{{ name }}</button>
</template>

<script>
export default {
    name: "SecondaryButton",
    props: {
        color: String,
        name: String
    }
}
</script>

<style scoped lang="scss">

</style>
