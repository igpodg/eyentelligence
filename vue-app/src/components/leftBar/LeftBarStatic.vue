<template>
    <li :class="(name === 'Overview' && this.$route.name === 'Overview') ? 'active' : ''">
        <router-link :to="url" :class="(name === 'Overview' && this.$route.name === 'Overview') ? 'active' : ''"
                     :data-toggle="dataToggle" :data-target="dataTarget">
            <i :class="'icon-' + icon"></i><span>{{ name }}</span>
        </router-link>
    </li>
</template>

<script>
export default {
    name: "LeftBarStatic",
    props: {
        icon: String,
        name: String,
        url: String,
        dataToggle: String,
        dataTarget: String
    }
}
</script>

<style scoped lang="scss">

</style>
