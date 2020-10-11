<template>
    <li>
        <a href="#" :class="(type === undefined) ? '' : 'text-' + type"
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
        type: String
    }
}
</script>

<style scoped lang="scss">

</style>
