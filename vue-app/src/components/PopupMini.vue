<template>
    <div class="dropdown-menu dropdown-menu-right" :aria-labelledby="'xp-' + name">
        <a class="dropdown-item py-3 text-white text-center font-16" href="#">{{ title }}</a>
        <slot></slot>
    </div>
</template>

<script>
export default {
    name: "PopupMini",
    props: {
        name: String,
        title: String
    }
}
</script>

<style scoped lang="scss">

</style>
