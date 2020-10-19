<template>
    <div class="dropdown-menu dropdown-menu-right" :aria-labelledby="'xp-' + name">
        <span class="dropdown-item py-3 text-white text-center">{{ title }}</span>
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
$color-title: #ffffff;

.text-white {
    color: $color-title !important;
}

$color-popup-border: #dee7f2;
$color-popup-item-hover: #f0f4f9;

$gradient-primary-begin: #4c7cf3;
$gradient-primary-end: #4cc6f3;

.dropdown-menu {
    border: 1px solid $color-popup-border;

    &>.dropdown-item:first-child {
        background-image: linear-gradient(58deg,
            $gradient-primary-begin 0,
            $gradient-primary-end 100%);
        border-radius: 15px 15px 0 0;
        font-weight: 700;
        line-height: 1.2;

        font-size: 16px;
    }

    .dropdown-item:last-child {
        border-radius: 0 0 15px 15px;
    }

    a:hover {
        background-color: $color-popup-item-hover;
    }
}

</style>
