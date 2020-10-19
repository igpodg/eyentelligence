<template>
    <div class="dropdown-menu dropdown-menu-right" :aria-labelledby="'xp-' + name">
        <ul class="list-unstyled">
            <li class="media popup-title">
                <div class="media-body">
                    <h5 class="mt-0 mb-0 py-3 text-white text-center">{{ title }}</h5>
                </div>
            </li>
            <slot></slot>
            <li class="media popup-bottom-text">
                <div class="media-body">
                    <h5 class="mt-0 mb-0 py-3 text-black text-center">
                        <a href="#" class="text-primary">{{ bottomText }}</a>
                    </h5>
                </div>
            </li>
        </ul>
    </div>
</template>

<script>
export default {
    name: "Popup",
    props: {
        name: String,
        title: String,
        bottomText: String
    }
}
</script>

<style scoped lang="scss">
$color-title: #ffffff;

.text-white {
    color: $color-title !important;
}

$color-bottom-text-caption: #2b343a;

.text-black {
    color: $color-bottom-text-caption !important;
}

$color-popup-border: #dee7f2;

.dropdown-menu {
    width: 310px;
    border: 1px solid $color-popup-border;
}

$gradient-primary-begin: #4c7cf3;
$gradient-primary-end: #4cc6f3;

.popup-title {
    background-image: linear-gradient(58deg,
        $gradient-primary-begin 0,
        $gradient-primary-end 100%);
    border-radius: 15px 15px 0 0;

    &>div > h5 {
        font-size: 16px;
    }
}

.popup-bottom-text {
    border-radius: 0 0 15px 15px;
    border-top: 1px solid #f0f4f9;

    &>div > h5 {
        font-size: 14px;
    }
}

</style>
