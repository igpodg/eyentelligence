<template>
    <li :class="isActive ? 'active' : ''">
        <router-link :to="url">
            <i :class="'icon-' + icon"></i><span>{{ name }}</span>
            <i class="icon-arrow-right pull-right"></i>
        </router-link>
        <ul class="xp-vertical-submenu" :style="isActive ? 'display: block;' : ''">
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
        url: String,
        index: {
            type: Number,
            default: -1
        }
    },
    data: function() {
        return {
            isActive: false
        }
    },
    created: function() {
        this.isActive = parseInt(this.$route.params.id) === this.index &&
            (this.$route.name === "TeamHome" || this.$route.name === "Dashboard");
    },
    mounted: function() {
        let that = this;
        this.$eventBus.$on("crumb-updated", function() {
            that.isActive = parseInt(that.$route.params.id) === that.index &&
                (that.$route.name === "TeamHome" || that.$route.name === "Dashboard");
        });
    },
    beforeDestroy: function() {
        this.$eventBus.$off("crumb-updated");
    }
}
</script>

<style lang="scss">
.pull-right {
    float: right;
}

$color-item: #8a98ac;
$color-item-hover: #4c7cf3;
$color-submenu: #0d0d0d;

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
        background: rgba($color-submenu, 0);

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
                color: $color-item-hover;
            }
        }

        >hr {
            margin-left: 25px !important;
        }
    }
}

</style>
