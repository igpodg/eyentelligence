<template>
    <small :id="id + 'Tip'" class="form-text text-muted"
           v-if="tip !== null">{{ tip }}</small>
</template>

<script>
export default {
    name: "FormTip",
    props: {
        id: String,
        tip: {
            type: String,
            default: null
        }
    }
}
</script>

<style scoped lang="scss">
$color-tip: #8a98ac;

.text-muted {
    color: $color-tip !important;
}

</style>
