<template>
    <button type="submit"
            :class="'btn btn-' + color + ' btn-rounded btn-lg btn-block'">{{ name }}</button>
</template>

<script>
export default {
    name: "SecondaryButton",
    props: {
        color: String,
        name: String
    }
}
</script>

<style scoped lang="scss">
$color-button-primary: #346bf1;
$color-button-success: #27b866;

%button-primary {
    background-color: $color-button-primary;
    border-color: $color-button-primary;
}

%button-success {
    background-color: $color-button-success;
    border-color: $color-button-success;
}

.btn {
    &-primary {
        &:active { @extend %button-primary; }
        &:hover { @extend %button-primary; }
        &:focus { @extend %button-primary; }
        &:visited { @extend %button-primary; }
        &:not(:disabled):not(.disabled):active {
            @extend %button-primary;
        }
    }

    &-success {
        &:active { @extend %button-success; }
        &:hover { @extend %button-success; }
        &:focus { @extend %button-success; }
        &:visited { @extend %button-success; }
        &:not(:disabled):not(.disabled):active {
            @extend %button-success;
        }
    }
}

</style>
