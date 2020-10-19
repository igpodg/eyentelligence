<template>
    <li class="media xp-msg">
        <img class="mr-3 align-self-center rounded-circle" :src="iconUrl" :alt="alt">
        <div class="media-body">
            <a href="#">
                <h5 class="mt-0 mb-1">
                    {{ title }}
                    <span class="float-right">{{ time }}</span>
                </h5>
                <p class="mb-0">
                    <slot></slot>
                    <span v-if="badge !== undefined" class="badge badge-pill badge-success float-right">{{ badge }}</span>
                </p>
            </a>
        </div>
    </li>
</template>

<script>
export default {
    name: "PopupItemMsg",
    props: {
        iconUrl: String,
        alt: {
            type: String,
            default: "Generic placeholder image"
        },
        title: String,
        time: String,
        badge: Number
    }
}
</script>

<style scoped lang="scss">
$color-black: #000000;
$color-white: #ffffff;

$color-popup-item-title: #2b343a;
$color-popup-item-time: #8a98ac;
$color-popup-item-message: #8a98ac;
$color-popup-item-hover: #f0f4f9;

.xp-msg {
    padding: 15px;
    background-color: $color-white;
    border: none;

    img {
        box-shadow: 0 0.5rem 1rem rgba($color-black, 0.15);
    }

    a {
        h5 {
            color: $color-popup-item-title;

            span {
                color: $color-popup-item-time;
            }
        }

        p {
            color: $color-popup-item-message;
        }
    }

    &:hover {
        background-color: $color-popup-item-hover;
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
}

.media-body {
    h5 {
        font-size: 14px;

        span {
            font-size: 12px;
            font-weight: 400;
        }
    }

    p {
        font-size: 13px;
    }
}

</style>
