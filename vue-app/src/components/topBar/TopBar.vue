<template>
    <div class="xp-topbar">
        <div class="row">
            <div class="col-2 col-md-1 col-lg-1 order-2 order-md-1 align-self-center">
                <div class="xp-menubar">
                    <span class="xp-menu-hamburger" @click="toggleHamburger">
                        <i class="icon-menu text-white"></i>
                    </span>
                </div>
            </div>

            <div class="col-10 col-md-11 col-lg-11 order-1 order-md-2">
                <div class="xp-profilebar text-right">
                    <ul class="list-inline mb-0">
                        <top-bar-search/>&nbsp;
                        <top-bar-messages :badge="8"/>&nbsp;
                        <top-bar-notifications :badge="3"/>&nbsp;
                        <top-bar-profile avatar-url="img/user.svg"/>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import TopBarSearch from "@/components/topBar/TopBarSearch.vue";
import TopBarMessages from "@/components/topBar/TopBarMessages.vue";
import TopBarNotifications from "@/components/topBar/TopBarNotifications.vue";
import TopBarProfile from "@/components/topBar/TopBarProfile.vue";

export default {
    name: "TopBar",
    components: {
        TopBarSearch,
        TopBarMessages,
        TopBarNotifications,
        TopBarProfile
    },
    methods: {
        toggleHamburger: function() {
            document.body.classList.toggle("xp-toggle-menu");
        }
    }
}
</script>

<style lang="scss">
$m-size: 768px;
$m-size-1: $m-size - 1px; //767 px

$color-white: #ffffff;

.xp-toggle-menu {
    .xp-topbar {
        left: 0;
    }
}

$color-topbar-background: #4c7cf3;

.xp-topbar {
    background-color: $color-topbar-background;
    padding: 15px 30px;
    position: fixed;
    left: 250px;
    right: 0;
    z-index: 999;

    .dropdown-toggle::after {
        display: none;
    }

    .xp-badge-up {
        position: relative;
        top: -10px;
        margin: 0 -10px;
        border: 2px solid $color-white;
        padding: 3px 4px 1px;
    }

    .xp-profilebar > ul > li {
        margin-right: 15px;

        &:last-child {
            margin-right: 0;
        }
    }

    @media print {
        display: none;
    }

    @media (max-width: $m-size-1) {
        left: 0;
    }
}

.xp-menubar {
    @media (max-width: $m-size-1) {
        text-align: right;
    }
}

.xp-menu-hamburger {
    color: $color-topbar-background;
    text-decoration: none;
    background-color: transparent;
    cursor: pointer;

    >i {
        font-size: 20px;
    }
}

$color-badge-success: #2bcd72;
$color-badge-success-dark: #27b866;

.badge {
    font-weight: 400;
    padding: 5px 6px 3px;

    &-success {
        background-color: $color-badge-success;

        &[href]:hover {
            background-color: $color-badge-success-dark;
        }

        &[href]:focus {
            background-color: $color-badge-success-dark;
        }
    }
}

.xp-badge-up {
    font-size: 11px !important;
}

$color-popup-border: #e1e4e9;
$color-popup-shadow: #c8c8c8;
$color-popup-item-selected-background: #e1e4e9;
$color-popup-item-selected: #313131;

.dropdown-menu {
    padding: 5px 0;
    font-size: 15px;
    border-color: $color-popup-border;
    box-shadow: 0 0 30px 0 rgba($color-popup-shadow, 0.2);

    .dropdown-item {
        padding: 0.5rem 1.5rem;

        &:active {
            background-color: $color-popup-item-selected-background;
            color: $color-popup-item-selected;
            text-decoration: none;
        }
    }
}

</style>
