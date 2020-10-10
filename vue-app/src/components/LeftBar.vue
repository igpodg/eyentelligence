<template>
    <div class="xp-leftbar">
        <!-- Start XP Logobar -->
        <div class="xp-logobar text-center">
            <a href="overview.html" class="xp-logo"><img src="img/logo.png" class="img-fluid"
                                                         alt="logo"></a>
        </div>
        <!-- End XP Logobar -->

        <!-- Start XP Navigationbar -->
        <div class="xp-navigationbar">
            <ul class="xp-vertical-menu">
                <li class="xp-vertical-header">Main</li>
                <li>
                    <a href="overview.html">
                        <i class="icon-doc"></i><span>Overview</span>
                    </a>
                </li>
                <li>
                    <a href="javascript:void(0)">
                        <i class="icon-list"></i><span>Statistics</span>
                    </a>
                </li>
                <li class="xp-vertical-header">Teams / Organizations</li>
                <li>
                    <a href="javascript:void(0)">
                        <i class="icon-briefcase"></i><span>SuperTeam</span><i
                        class="icon-arrow-right pull-right"></i>
                    </a>
                    <ul class="xp-vertical-submenu">
                        <li><a href="javascript:void(0)">Home</a></li>
                        <li><a href="javascript:void(0)">My Dashboard</a></li>
                        <li><a href="javascript:void(0)">My Dashboard #2</a></li>
                        <hr>
                        <li><a href="javascript:void(0)">Dashboards...</a></li>
                        <li><a href="javascript:void(0)">Members...</a></li>
                        <!--<li><a href="javascript:void(0)" class="text-danger">Leave Team</a></li>-->
                        <li><a href="#" class="text-danger" data-toggle="modal" data-target="#xpLeaveModal">Leave
                            Team</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="javascript:void(0)">
                        <i class="icon-briefcase"></i><span>Company Inc.</span><i
                        class="icon-arrow-right pull-right"></i>
                    </a>
                    <ul class="xp-vertical-submenu">
                        <li><a href="javascript:void(0)">Home</a></li>
                        <li><a href="javascript:void(0)">My Dashboard</a></li>
                        <li><a href="javascript:void(0)">My Dashboard #2</a></li>
                        <hr>
                        <li><a href="javascript:void(0)">Dashboards...</a></li>
                        <li><a href="javascript:void(0)">Members...</a></li>
                        <li><a href="javascript:void(0)" class="text-danger">Leave Team</a></li>
                    </ul>
                </li>
                <li>
                    <a href="javascript:void(0)">
                        <i class="icon-briefcase"></i><span>Europe Sales</span><i
                        class="icon-arrow-right pull-right"></i>
                    </a>
                    <ul class="xp-vertical-submenu">
                        <li><a href="javascript:void(0)">Home</a></li>
                        <li><a href="javascript:void(0)">My Dashboard</a></li>
                        <li><a href="javascript:void(0)">My Dashboard #2</a></li>
                        <hr>
                        <li><a href="javascript:void(0)">Dashboards...</a></li>
                        <li><a href="javascript:void(0)">Members...</a></li>
                        <li><a href="javascript:void(0)" class="text-danger">Leave Team</a></li>
                    </ul>
                </li>
                <li>
                    <a href="javascript:void(0)">
                        <i class="icon-briefcase"></i><span>Asia Sales</span><i
                        class="icon-arrow-right pull-right"></i>
                    </a>
                    <ul class="xp-vertical-submenu">
                        <li><a href="javascript:void(0)">Home</a></li>
                        <li><a href="javascript:void(0)">My Dashboard</a></li>
                        <li><a href="javascript:void(0)">My Dashboard #2</a></li>
                        <hr>
                        <li><a href="javascript:void(0)">Dashboards...</a></li>
                        <li><a href="javascript:void(0)">Members...</a></li>
                        <li><a href="javascript:void(0)" class="text-danger">Leave Team</a></li>
                    </ul>
                </li>
                <li class="xp-vertical-header">Extras</li>
                <li>
                    <a href="javascript:void(0)">
                        <i class="icon-plus"></i><span>Join a Team</span>
                    </a>
                </li>
                <li>
                    <a href="javascript:void(0)">
                        <i class="icon-people"></i><span>Create a New Team</span>
                    </a>
                </li>
            </ul>
        </div>
    </div>
</template>

<script>
export default {
    name: "LeftBar"/*,
    props: {
        msg: String
    }*/
}
</script>

<style scoped lang="scss">
.xp-logo {
    color: #313131 !important;
    font-size: 20px;
    font-weight: 700;
    letter-spacing: 0.03em;
    line-height: 100px;
    text-transform: uppercase;
}

.xp-leftbar {
    width: 250px;
    height: 100%;
    position: fixed;
    background-color: #fff;
    box-shadow: 0 0 30px 0 rgba(200, 200, 200, 0.2);
    z-index: 9;
    transition: all 0.3s ease;
}


.xp-vertical-menu {
    list-style: none;
    margin: 0;
    padding: 0;
}
.xp-vertical-menu > li {
    position: relative;
    margin: 5px 0;
    padding: 0 0 0 15px;
}
.xp-vertical-menu > li > a {
    padding: 10px 20px;
    display: block;
    color: #8A98AC;
}
.xp-vertical-menu > li > a > i {
    display: inline-block;
    width: 30px;
    font-size: 16px;
    vertical-align: middle;
}
.xp-vertical-menu > li > a > span {
    vertical-align: middle;
}
.xp-vertical-menu > li:hover > a {
    color: #ffffff;
    background-image: linear-gradient(58deg, #4c7cf3 0, #4cc6f3 100%);
    border-radius: 50px 0 0 50px;
    box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.15);
}
.xp-vertical-menu li.xp-vertical-header {
    padding: 14px 25px;
    font-size: 12px;
}
.xp-vertical-menu li > a > .icon-arrow-right {
    width: auto;
    height: auto;
    padding: 0;
    font-size: 10px;
    font-weight: 700;
    line-height: 24px;
}
.xp-vertical-menu a {
    color: #8A98AC;
    text-decoration: none;
}
.xp-vertical-menu .xp-vertical-submenu {
    display: none;
    list-style: none;
    padding-left: 5px;
    margin: 0 1px;
    background: rgba(13, 13, 13, 0);
}
.xp-vertical-menu .xp-vertical-submenu .xp-vertical-submenu {
    padding-left: 20px;
}
.xp-vertical-menu .xp-vertical-submenu > li > a {
    padding: 8px 25px 8px 45px;
    display: block;
    font-size: 14px;
    color: #8A98AC;
}
.xp-vertical-menu .xp-vertical-submenu > li > a > .icon-arrow-right {
    width: auto;
}
.xp-vertical-menu .xp-vertical-submenu > li > a:hover {
    color: #4c7cf3;
}

.text-danger {
    color: #ff4b5b !important;
}

a.text-danger:focus {
    color: #ff3244 !important;
}
a.text-danger:hover {
    color: #ff3244 !important;
}

@media print {
    .xp-leftbar {
        display: none;
    }
}

@media (max-width: 767px) {
    .xp-leftbar {
        position: fixed;
        left: -250px;
    }
}
</style>
