<template>
    <div class="xp-topbar">
        <!-- Start XP Row -->
        <div class="row">

            <!-- Start XP Col -->
            <div class="col-2 col-md-1 col-lg-1 order-2 order-md-1 align-self-center">
                <div class="xp-menubar">
                    <a class="xp-menu-hamburger" href="javascript:void(0)">
                        <i class="icon-menu font-20 text-white"></i>
                    </a>
                </div>
            </div>
            <!-- End XP Col -->

            <!-- Start XP Col -->
            <div class="col-10 col-md-11 col-lg-11 order-1 order-md-2">
                <div class="xp-profilebar text-right">
                    <ul class="list-inline mb-0">
                        <li class="list-inline-item">
                            <div class="xp-search">
                                <a href="#" class="text-white" data-toggle="modal" data-target="#xpSearchModal"><i class="icon-magnifier"></i></a>
                            </div>
                        </li>
                        <li class="list-inline-item">
                            <div class="dropdown xp-message">
                                <a class="dropdown-toggle  text-white" href="#" role="button" id="xp-message" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <i class="icon-speech font-18 v-a-m"></i>
                                    <span class="badge badge-pill bg-success-gradient xp-badge-up">8</span>
                                </a>
                                <div class="dropdown-menu dropdown-menu-right" aria-labelledby="xp-message">
                                    <ul class="list-unstyled">
                                        <li class="media">
                                            <div class="media-body">
                                                <h5 class="mt-0 mb-0 py-3 text-white text-center font-16">8 New Messages</h5>
                                            </div>
                                        </li>
                                        <li class="media xp-msg">
                                            <img class="mr-3 align-self-center rounded-circle" src="assets/images/topbar/user-message-1.jpg" alt="Generic placeholder image">
                                            <div class="media-body">
                                                <a href="#">
                                                    <h5 class="mt-0 mb-1 font-14">Ariel Blue<span class="font-12 f-w-4 float-right">3 min ago</span></h5>
                                                    <p class="mb-0 font-13">Thank you for attending...<span class="badge badge-pill badge-success float-right">2</span></p>
                                                </a>
                                            </div>
                                        </li>
                                        <li class="media xp-msg">
                                            <img class="mr-3 align-self-center rounded-circle" src="assets/images/topbar/user-message-2.jpg" alt="Generic placeholder image">
                                            <div class="media-body">
                                                <a href="#">
                                                    <h5 class="mt-0 mb-1 font-14">Jammy Moon<span class="font-12 f-w-4 float-right">5 min ago</span></h5>
                                                    <p class="mb-0 font-13">Hey no worries! Trust me...<span class="badge badge-pill badge-success float-right">3</span></p>
                                                </a>
                                            </div>
                                        </li>
                                        <li class="media xp-msg">
                                            <img class="mr-3 align-self-center rounded-circle" src="assets/images/topbar/user-message-3.jpg" alt="Generic placeholder image">
                                            <div class="media-body">
                                                <a href="#">
                                                    <h5 class="mt-0 mb-1 font-14">Lisa Ross<span class="font-12 f-w-4 float-right">5:25 PM</span></h5>
                                                    <p class="mb-0 font-13">Remedies for colic? i don't...<span class="badge badge-pill badge-success float-right">5</span></p>
                                                </a>
                                            </div>
                                        </li>
                                        <li class="media">
                                            <div class="media-body">
                                                <h5 class="mt-0 mb-0 py-3 text-black text-center font-14">
                                                    <a href="#" class="text-primary">View all</a>
                                                </h5>
                                            </div>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </li>
                        <li class="list-inline-item">
                            <div class="dropdown xp-notification">
                                <a class="dropdown-toggle  text-white" href="#" role="button" id="xp-notification" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <i class="icon-bell font-18 v-a-m"></i>
                                    <span class="badge badge-pill bg-danger-gradient xp-badge-up">3</span>
                                </a>

                                <div class="dropdown-menu dropdown-menu-right" aria-labelledby="xp-notification">
                                    <ul class="list-unstyled">
                                        <li class="media">
                                            <div class="media-body">
                                                <h5 class="mt-0 mb-0 py-3 text-white text-center font-16">3 New Notifications</h5>
                                            </div>
                                        </li>
                                        <li class="media xp-noti">
                                            <div class="mr-3 xp-noti-icon primary-rgba"><i class="icon-user-follow text-primary"></i></div>
                                            <div class="media-body">
                                                <a href="#">
                                                    <h5 class="mt-0 mb-1 font-14">New user registered</h5>
                                                    <p class="mb-0 font-12 f-w-4">2 min ago</p>
                                                </a>
                                            </div>
                                        </li>
                                        <li class="media xp-noti">
                                            <div class="mr-3 xp-noti-icon success-rgba"><i class="icon-basket-loaded text-success"></i></div>
                                            <div class="media-body">
                                                <a href="#">
                                                    <h5 class="mt-0 mb-1 font-14">New order placed</h5>
                                                    <p class="mb-0 font-12 f-w-4">8:45 PM</p>
                                                </a>
                                            </div>
                                        </li>
                                        <li class="media xp-noti">
                                            <div class="mr-3 xp-noti-icon danger-rgba"><i class="icon-like text-danger"></i></div>
                                            <div class="media-body">
                                                <a href="#">
                                                    <h5 class="mt-0 mb-1 font-14">John like your photo.</h5>
                                                    <p class="mb-0 font-12 f-w-4">Yesterday</p>
                                                </a>
                                            </div>
                                        </li>
                                        <li class="media">
                                            <div class="media-body">
                                                <h5 class="mt-0 mb-0 py-3 text-black text-center font-14">
                                                    <a href="#" class="text-primary">View all</a>
                                                </h5>
                                            </div>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </li>
                        <li class="list-inline-item mr-0">
                            <div class="dropdown xp-userprofile">
                                <a class="dropdown-toggle " href="#" role="button" id="xp-userprofile" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><img src="img/user.svg" alt="user-profile" class="rounded-circle img-fluid"><span class="xp-user-live"></span></a>

                                <div class="dropdown-menu dropdown-menu-right" aria-labelledby="xp-userprofile">
                                    <a class="dropdown-item py-3 text-white text-center font-16" href="#">Welcome, John Doe</a>
                                    <a class="dropdown-item" href="#"><i class="icon-user text-primary mr-2"></i> Profile</a>
                                    <a class="dropdown-item" href="#"><i class="icon-wallet text-success mr-2"></i> Billing</a>
                                    <a class="dropdown-item" href="#"><i class="icon-settings text-warning mr-2"></i> Setting</a>
                                    <a class="dropdown-item" href="#"><i class="icon-lock text-info mr-2"></i> Lock Screen</a>
                                    <a class="dropdown-item" href="#"><i class="icon-power text-danger mr-2"></i> Logout</a>
                                </div>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
            <!-- End XP Col -->

        </div>
        <!-- End XP Row -->
    </div>
</template>

<script>
export default {
    name: "TopBar",
    props: {
        a: String
    }
}
</script>

<style scoped lang="scss">

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
