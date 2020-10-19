<template>
    <div class="text-center mb-3">
        <h6 class="secondary-main-text">
            <slot></slot>
        </h6>
    </div>
</template>

<script>
export default {
    name: "SecondaryMainText"
}
</script>

<style scoped lang="scss">
.secondary-main-text {
    text-align: left;
}

</style>
