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
$color-button-primary-selected: #346bf1;
$color-button-success-selected: #27b866;

%button-primary-selected {
    background-color: $color-button-primary-selected;
    border-color: $color-button-primary-selected;
}

%button-success-selected {
    background-color: $color-button-success-selected;
    border-color: $color-button-success-selected;
}

.btn {
    &-primary {
        &:active { @extend %button-primary-selected; }
        &:hover { @extend %button-primary-selected; }
        &:focus { @extend %button-primary-selected; }
        &:visited { @extend %button-primary-selected; }
        &:not(:disabled):not(.disabled):active {
            @extend %button-primary-selected;
        }
    }

    &-success {
        &:active { @extend %button-success-selected; }
        &:hover { @extend %button-success-selected; }
        &:focus { @extend %button-success-selected; }
        &:visited { @extend %button-success-selected; }
        &:not(:disabled):not(.disabled):active {
            @extend %button-success-selected;
        }
    }
}

</style>
