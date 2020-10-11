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
                        <top-bar-messages badge="8"/>&nbsp;
                        <top-bar-notifications badge="3"/>&nbsp;
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




.xp-menu-hamburger {
    color: #4c7cf3;
    text-decoration: none;
    background-color: transparent;
    cursor: pointer;
}









.xp-badge-up {
    font-size: 11px !important;
}




.font-12 {
    font-size: 12px;
}

.font-13 {
    font-size: 13px;
}

.font-14 {
    font-size: 14px;
}

.font-16 {
    font-size: 16px;
}

.font-18 {
    font-size: 18px;
}

.font-20 {
    font-size: 20px;
}

/*  -----  Font Weight  ----  */

.f-w-4 {
    font-weight: 400;
}

/*  -----  Extra  ----  */

.v-a-m {
    vertical-align: middle;
}

.bg-success-gradient {
    background-image: linear-gradient(58deg, #2bcd72 0, #9acd2b 100%);
}

.bg-danger-gradient {
    background-image: linear-gradient(58deg, #ff4b5b 0, #ff7e4b 100%);
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
.xp-topbar .xp-userprofile a img {
    width: 40px;
}
.xp-topbar .xp-userprofile a .xp-user-live {
    position: absolute;
    bottom: -2px;
    right: -2px;
    width: 12px;
    height: 12px;
    border-radius: 50%;
    border: 2px solid #ffffff;
    background-image: linear-gradient(58deg, #2bcd72 0, #9acd2b 100%);
}
.xp-topbar .xp-userprofile .dropdown-menu {
    top: 26px !important;
    background-color: #ffffff;
    border: 1px solid #dee7f2;
    border-radius: 15px;
    padding: 0;
}
.xp-topbar .xp-userprofile .dropdown-menu .dropdown-item:first-child {
    background-image: linear-gradient(58deg, #4c7cf3 0, #4cc6f3 100%);
    border-radius: 15px 15px 0 0;
    font-weight: 700;
    line-height: 1.2;
}
.xp-topbar .xp-userprofile .dropdown-menu .dropdown-item:last-child {
    border-radius: 0 0 15px 15px;
}
.xp-topbar .xp-userprofile .dropdown-menu a:hover {
    background-color: #F0F4F9;
}
.xp-topbar .xp-notification .dropdown-menu {
    width: 310px;
    top: 26px !important;
    background-color: #ffffff;
    border: 1px solid #dee7f2;
    border-radius: 15px;
    padding: 0;
}
.xp-topbar .xp-notification li.media:first-child {
    background-image: linear-gradient(58deg, #4c7cf3 0, #4cc6f3 100%);
    border-radius: 15px 15px 0 0;
}
.xp-topbar .xp-notification li.media:last-child {
    border-radius: 0 0 15px 15px;
    border-top: 1px solid #F0F4F9;
}
.xp-topbar .xp-notification li.media.xp-noti {
    padding: 15px;
    background-color: #ffffff;
    border: none;
}
.xp-topbar .xp-notification li.media.xp-noti a h5 {
    color: #2B343A;
}
.xp-topbar .xp-notification li.media.xp-noti a h5 span {
    color: #8A98AC;
}
.xp-topbar .xp-notification li.media.xp-noti a p {
    color: #8A98AC;
}
.xp-topbar .xp-notification li.media.xp-noti .xp-noti-icon {
    width: 40px;
    height: 40px;
    line-height: 42px;
    font-size: 16px;
    border-radius: 50%;
    text-align: center;
    vertical-align: middle;
    box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.15);
}
.xp-topbar .xp-notification li.media.xp-noti:hover {
    background-color: #F0F4F9;
}
.xp-topbar .xp-message .dropdown-menu {
    width: 310px;
    top: 26px !important;
    background-color: #ffffff;
    border: 1px solid #dee7f2;
    border-radius: 15px;
    padding: 0;
}
.xp-topbar .xp-message li.media:first-child {
    background-image: linear-gradient(58deg, #4c7cf3 0, #4cc6f3 100%);
    border-radius: 15px 15px 0 0;
}
.xp-topbar .xp-message li.media:last-child {
    border-radius: 0 0 15px 15px;
    border-top: 1px solid #F0F4F9;
}
.xp-topbar .xp-message li.media.xp-msg {
    padding: 15px;
    background-color: #ffffff;
    border: none;
}
.xp-topbar .xp-message li.media.xp-msg img {
    box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.15);
}
.xp-topbar .xp-message li.media.xp-msg a h5 {
    color: #2B343A;
}
.xp-topbar .xp-message li.media.xp-msg a h5 span {
    color: #8A98AC;
}
.xp-topbar .xp-message li.media.xp-msg a p {
    color: #8A98AC;
}
.xp-topbar .xp-message li.media.xp-msg .xp-noti-icon {
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
.xp-topbar .xp-message li.media.xp-msg:hover {
    background-color: #F0F4F9;
}
.xp-topbar .xp-message li.media.xp-msg:hover .xp-noti-icon {
    background: #ffffff;
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

/* -----  Text Color  ----- */
.text-white {
    color: #ffffff !important;
}

.text-black {
    color: #2B343A !important;
}

.text-primary {
    color: #4c7cf3 !important;
}

.text-success {
    color: #2bcd72 !important;
}

.text-danger {
    color: #ff4b5b !important;
}

.text-warning {
    color: #fac751 !important;
}

.text-info {
    color: #52c4ca !important;
}

a.text-primary:focus {
    color: #346bf1 !important;
}
a.text-primary:hover {
    color: #346bf1 !important;
}

a.text-success:focus {
    color: #27b866 !important;
}
a.text-success:hover {
    color: #27b866 !important;
}

a.text-danger:focus {
    color: #ff3244 !important;
}
a.text-danger:hover {
    color: #ff3244 !important;
}

a.text-warning:focus {
    color: #f9bf38 !important;
}
a.text-warning:hover {
    color: #f9bf38 !important;
}

a.text-info:focus {
    color: #3ebdc4 !important;
}
a.text-info:hover {
    color: #3ebdc4 !important;
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

    .xp-rightbar {
        margin-left: 0;
    }
}
/*
==================
    Responsive
==================
*/
@media (min-width: 768px) and (max-width: 991px) {
    body {
        overflow-x: hidden;
    }
}
@media (max-width: 991px) {

    h5 {
        font-size: 18px;
    }
}
@media (max-width: 768px) {
    .xp-rightbar {
        margin-left: 0;
    }
}
@media (max-width: 767px) {
    body {
        overflow-x: hidden;
    }

    h5 {
        font-size: 16px;
    }

    .xp-rightbar {
        margin-left: 0 !important;
    }

    .xp-topbar {
        left: 0;
    }

    .xp-menubar {
        text-align: right;
    }
}
</style>
