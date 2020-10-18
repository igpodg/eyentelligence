<template>
    <li>
        <a href="#" :class="(color === undefined) ? '' : 'text-' + color"
           data-toggle="modal" data-target="#xpLeaveModal">
            {{ name }}
        </a>
    </li>
</template>

<script>
export default {
    name: "LeftBarStaticModal",
    props: {
        name: String,
        color: String
    }
}
</script>

<style scoped lang="scss">

</style>
