<template>
    <li>
        <router-link :to="url">
            <i :class="'icon-' + icon"></i><span>{{ name }}</span>
            <i class="icon-arrow-right pull-right"></i>
        </router-link>
        <ul class="xp-vertical-submenu">
            <slot></slot>
        </ul>
    </li>
</template>

<script>
export default {
    name: "LeftBarDropdown",
    props: {
        icon: String,
        name: String,
        url: String
    }
}
</script>

<style scoped lang="scss">

</style>
