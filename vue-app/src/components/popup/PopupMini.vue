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
.font-16 {
    font-size: 16px;
}


.dropdown-menu {
    top: 26px !important;
    background-color: #ffffff;
    border: 1px solid #dee7f2;
    border-radius: 15px;
    padding: 0;
}
.dropdown-menu .dropdown-item:first-child {
    background-image: linear-gradient(58deg, #4c7cf3 0, #4cc6f3 100%);
    border-radius: 15px 15px 0 0;
    font-weight: 700;
    line-height: 1.2;
}
.dropdown-menu .dropdown-item:last-child {
    border-radius: 0 0 15px 15px;
}
.dropdown-menu a:hover {
    background-color: #F0F4F9;
}

</style>
