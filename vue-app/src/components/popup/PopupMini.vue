<template>
    <div class="dropdown-menu dropdown-menu-right" :aria-labelledby="'xp-' + name">
        <span class="dropdown-item py-3 text-white text-center font-16">{{ title }}</span>
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
