<template>
    <li class="media xp-msg">
        <img class="mr-3 align-self-center rounded-circle" :src="iconUrl" :alt="alt">
        <div class="media-body">
            <a href="#">
                <h5 class="mt-0 mb-1 font-14">{{ title }}<span class="font-12 f-w-4 float-right">{{ time }}</span></h5>
                <p class="mb-0 font-13">
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
.font-12 {
    font-size: 12px;
}

.font-13 {
    font-size: 13px;
}

.font-14 {
    font-size: 14px;
}

.f-w-4 {
    font-weight: 400;
}


.xp-msg {
    padding: 15px;
    background-color: #ffffff;
    border: none;
}
.xp-msg img {
    box-shadow: 0 0.5rem 1rem rgba(#000000, 0.15);
}
.xp-msg a h5 {
    color: #2b343a;
}
.xp-msg a h5 span {
    color: #8a98ac;
}
.xp-msg a p {
    color: #8a98ac;
}
.xp-msg .xp-noti-icon {
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
.xp-msg:hover {
    background-color: #f0f4f9;
}
.xp-msg:hover .xp-noti-icon {
    background: #ffffff;
}



</style>
