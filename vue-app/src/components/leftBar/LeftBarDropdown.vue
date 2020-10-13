<template>
    <li :class="(name === 'SuperTeam' && this.$route.name === 'Dashboard') ? 'active' : ''">
        <router-link :to="url">
            <i :class="'icon-' + icon"></i><span>{{ name }}</span>
            <i class="icon-arrow-right pull-right"></i>
        </router-link>
        <ul class="xp-vertical-submenu" :style="(name === 'SuperTeam' && this.$route.name === 'Dashboard') ? 'display: block;' : ''">
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
