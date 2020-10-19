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
.font-14 {
    font-size: 14px;
}

.font-16 {
    font-size: 16px;
}



.dropdown-menu {
    width: 310px;
    top: 26px !important;
    background-color: #ffffff;
    border: 1px solid #dee7f2;
    border-radius: 15px;
    padding: 0;
}
.dropdown-menu li.media:first-child {
    background-image: linear-gradient(58deg, #4c7cf3 0, #4cc6f3 100%);
    border-radius: 15px 15px 0 0;
}
.dropdown-menu li.media:last-child {
    border-radius: 0 0 15px 15px;
    border-top: 1px solid #F0F4F9;
}

</style>
