<template>
    <div class="xp-topbar">
        <div class="row">
            <div class="col-2 col-md-1 col-lg-1 order-2 order-md-1 align-self-center">
                <div class="xp-menubar">
                    <span class="xp-menu-hamburger" @click="toggleHamburger">
                        <i class="icon-menu font-20 text-white"></i>
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
.xp-toggle-menu {
    .xp-topbar {
        left: 0;
    }
}

.xp-menu-hamburger {
    color: #4c7cf3;
    text-decoration: none;
    background-color: transparent;
    cursor: pointer;
}

.xp-badge-up {
    font-size: 11px !important;
}

.font-20 {
    font-size: 20px;
}










/*
==================
    Topbar
==================
*/

.xp-topbar {
    background-color: #4c7cf3;
    padding: 15px 30px;
    position: fixed;
    left: 250px;
    right: 0;
    z-index: 999;
}
.xp-topbar .dropdown-toggle::after {
    display: none;
}
.xp-topbar .xp-badge-up {
    position: relative;
    top: -10px;
    margin: 0 -10px;
    border: 2px solid #ffffff;
    padding: 3px 4px 1px;
}
.xp-topbar .xp-profilebar > ul > li {
    margin-right: 15px;
}
.xp-topbar .xp-profilebar > ul > li:last-child {
    margin-right: 0;
}



/* -----  Dropdown  ----- */
.dropdown-menu {
    padding: 5px 0;
    font-size: 15px;
    border-color: #e1e4e9;
    box-shadow: 0 0 30px 0 rgba(200, 200, 200, 0.2);
}
.dropdown-menu .dropdown-item {
    padding: 0.5rem 1.5rem;
}
.dropdown-menu .dropdown-item:active {
    background-color: #e1e4e9;
    color: #313131;
    text-decoration: none;
}

/*
==============
    Badges
==============
*/
.badge {
    font-weight: 400;
    padding: 5px 6px 3px;
}

.badge-success {
    background-color: #2bcd72;
}
.badge-success[href]:hover {
    background-color: #27b866;
}
.badge-success[href]:focus {
    background-color: #27b866;
}

/*
=============
    Print
=============
*/
@media print {
    .xp-topbar {
        display: none;
    }
}
/*
==================
    Responsive
==================
*/



@media (max-width: 767px) {
    .xp-topbar {
        left: 0;
    }

    .xp-menubar {
        text-align: right;
    }
}
</style>
