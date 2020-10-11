<template>
    <div class="dropdown-menu dropdown-menu-right" :aria-labelledby="'xp-' + name">
        <ul class="list-unstyled">
            <li class="media">
                <div class="media-body">
                    <h5 class="mt-0 mb-0 py-3 text-white text-center font-16">{{ title }}</h5>
                </div>
            </li>
            <slot></slot>
            <li class="media">
                <div class="media-body">
                    <h5 class="mt-0 mb-0 py-3 text-black text-center font-14">
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

</style>
