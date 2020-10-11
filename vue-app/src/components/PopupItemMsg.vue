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

</style>
