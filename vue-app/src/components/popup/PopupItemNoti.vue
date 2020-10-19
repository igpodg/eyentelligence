<template>
    <li class="media xp-noti">
        <div :class="'mr-3 xp-noti-icon ' + color + '-rgba'">
            <i :class="'icon-' + icon + ' text-' + color"></i>
        </div>
        <div class="media-body">
            <a href="#">
                <h5 class="mt-0 mb-1">
                    <slot></slot>
                </h5>
                <p class="mb-0">{{ time }}</p>
            </a>
        </div>
    </li>
</template>

<script>
export default {
    name: "PopupItemNoti",
    props: {
        color: String,
        icon: String,
        time: String
    }
}
</script>

<style scoped lang="scss">
$color-black: #000000;
$color-white: #ffffff;

$color-popup-item-title: #2b343a;
$color-popup-item-time: #8a98ac;
$color-popup-item-hover: #f0f4f9;

.xp-noti {
    padding: 15px;
    background-color: $color-white;
    border: none;

    a {
        h5 {
            color: $color-popup-item-title;

            //span {
            //    color: #8a98ac;
            //}
        }

        p {
            color: $color-popup-item-time;
        }
    }

    /*.xp-noti-icon {
        width: 40px;
        height: 40px;
        line-height: 40px;
        background: #dbe5fd;
        color: #4c7cf3;
        font-size: 20px;
        border-radius: 50%;
        text-align: center;
        vertical-align: middle;
    }

    &:hover .xp-noti-icon {
        background: $color-white;
    }*/

    .xp-noti-icon {
        width: 40px;
        height: 40px;
        line-height: 42px;
        font-size: 16px;
        border-radius: 50%;
        text-align: center;
        vertical-align: middle;
        box-shadow: 0 0.5rem 1rem rgba($color-black, 0.15);
    }

    &:hover {
        background-color: $color-popup-item-hover;
    }
}

.media-body {
    h5 {
        font-size: 14px;
    }

    p {
        font-size: 12px;
        font-weight: 400;
    }
}

@mixin colored-icon($name, $color) {
    .#{$name}-rgba {
        background-color: rgba($color, 0.3);
    }
}

$color-primary: #4c7cf3;
$color-success: #2bcd72;
$color-danger: #ff4b5b;

@include colored-icon("primary", $color-primary);
@include colored-icon("success", $color-success);
@include colored-icon("danger", $color-danger);

</style>
