<template>
    <router-link class="dropdown-item" :to="url">
        <i :class="'icon-' + icon + ' text-' + color + ' mr-2'"></i>
        &nbsp;{{ title }}
    </router-link>
</template>

<script>
export default {
    name: "PopupMiniItem",
    props: {
        color: String,
        icon: String,
        title: String,
        url: {
            type: String,
            default: ""
        }
    }
}
</script>

<style scoped lang="scss">

</style>
