<template>
    <div :style="'padding-bottom: ' + (tall ? 30 : 20) + 'px;'">
        <router-link :to="url">
            <slot></slot>
        </router-link>
    </div>
</template>

<script>
export default {
    name: "SecondaryLink",
    props: {
        url: String,
        tall: {
            type: Boolean,
            default: false
        }
    }
}
</script>

<style scoped lang="scss">

</style>
