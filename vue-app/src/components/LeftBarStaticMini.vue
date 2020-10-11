<template>
    <li>
        <router-link :to="url"
                     :class="(type === undefined) ? '' :
                        'text-' + type">{{ name }}</router-link>
    </li>
</template>

<script>
export default {
    name: "LeftBarStaticMini",
    props: {
        name: String,
        url: String,
        type: String
    }
}
</script>

<style scoped lang="scss">

</style>
