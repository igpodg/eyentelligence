<template>
    <li class="xp-vertical-header">{{ name }}</li>
</template>

<script>
export default {
    name: "LeftBarHeading",
    props: {
        name: String
    }
}
</script>

<style lang="scss">
.xp-vertical-menu {
    li {
        &.xp-vertical-header {
            padding: 14px 25px;
            font-size: 12px;
        }
    }
}

</style>
