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
.font-12 {
    font-size: 12px;
}

.font-14 {
    font-size: 14px;
}

.f-w-4 {
    font-weight: 400;
}


.xp-noti {
    padding: 15px;
    background-color: #ffffff;
    border: none;
}
.xp-noti a h5 {
    color: #2B343A;
}
.xp-noti a h5 span {
    color: #8A98AC;
}
.xp-noti a p {
    color: #8A98AC;
}
.xp-noti .xp-noti-icon {
    width: 40px;
    height: 40px;
    line-height: 42px;
    font-size: 16px;
    border-radius: 50%;
    text-align: center;
    vertical-align: middle;
    box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.15);
}
.xp-noti:hover {
    background-color: #F0F4F9;
}

.primary-rgba {
    background-color: rgba(76, 124, 243, 0.3);
}

.success-rgba {
    background-color: rgba(43, 205, 114, 0.3);
}

.danger-rgba {
    background-color: rgba(255, 75, 91, 0.3);
}

</style>
