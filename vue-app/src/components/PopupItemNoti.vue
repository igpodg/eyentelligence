<template>
    <li class="media xp-noti">
        <div :class="'mr-3 xp-noti-icon ' + color + '-rgba'">
            <i :class="'icon-' + icon + ' text-' + color"></i>
        </div>
        <div class="media-body">
            <a href="#">
                <h5 class="mt-0 mb-1 font-14">
                    <slot></slot>
                </h5>
                <p class="mb-0 font-12 f-w-4">{{ time }}</p>
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

</style>
