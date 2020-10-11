<template>
    <li>
        <router-link :to="url">
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
        url: String
    }
}
</script>

<style scoped lang="scss">

</style>
