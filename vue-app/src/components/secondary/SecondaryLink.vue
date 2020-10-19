<template>
    <div :class="'secondary-link' + (tall ? '-tall' : '')">
        <router-link :to="url">
            <slot></slot>
        </router-link>
    </div>
</template>

<script>
export default {
    name: "SecondaryLink",
    props: {
        url: String,
        tall: {
            type: Boolean,
            default: false
        }
    }
}
</script>

<style scoped lang="scss">
.secondary-link {
    padding-bottom: 20px;

    &-tall {
        padding-bottom: 30px;
    }
}

</style>
