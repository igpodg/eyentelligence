<template>
    <li :class="(name === 'SuperTeam' && this.$route.name === 'Dashboard') ? 'active' : ''">
        <router-link :to="url">
            <i :class="'icon-' + icon"></i><span>{{ name }}</span>
            <i class="icon-arrow-right pull-right"></i>
        </router-link>
        <ul class="xp-vertical-submenu" :style="(name === 'SuperTeam' && this.$route.name === 'Dashboard') ? 'display: block;' : ''">
            <slot></slot>
        </ul>
    </li>
</template>

<script>
export default {
    name: "LeftBarDropdown",
    props: {
        icon: String,
        name: String,
        url: String
    }
}
</script>

<style lang="scss">
.pull-right {
    float: right;
}

$color-item: #8a98ac;

.xp-vertical-menu {
    li {
        &.active > a > .icon-arrow-right {
            transform: rotate(-270deg);
        }
    }

    li > a > .icon-arrow-right {
        width: auto;
        height: auto;
        padding: 0;
        font-size: 10px;
        font-weight: 700;
        line-height: 24px;
    }

    .xp-vertical-submenu {
        display: none;
        list-style: none;
        padding-left: 5px;
        margin: 0 1px;
        background: rgba(#0d0d0d, 0);

        .xp-vertical-submenu {
            padding-left: 20px;
        }

        > li > a {
            padding: 8px 25px 8px 45px;
            display: block;
            font-size: 14px;
            color: $color-item;

            >.icon-arrow-right {
                width: auto;
            }

            &:hover {
                color: #4c7cf3;
            }
        }

        >hr {
            margin-left: 25px !important;
        }
    }
}

</style>
