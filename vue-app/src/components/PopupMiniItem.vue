<template>
    <a class="dropdown-item" href="#">
        <i :class="'icon-' + icon + ' text-' + color + ' mr-2'"></i>
        &nbsp;{{ title }}
    </a>
</template>

<script>
export default {
    name: "PopupMiniItem",
    props: {
        color: String,
        icon: String,
        title: String
    }
}
</script>

<style scoped lang="scss">

</style>
