<template>
    <div class="xp-leftbar">
        <div class="xp-logobar text-center">
            <router-link :to="url" class="xp-logo">
                <img :src="logo" class="img-fluid" alt="logo">
            </router-link>
        </div>

        <div class="xp-navigationbar">
            <ul class="xp-vertical-menu">
                <left-bar-heading name="Main"/>
                <left-bar-static icon="doc" name="Overview" url=""/>
                <left-bar-static icon="list" name="Statistics" url=""/>

                <left-bar-heading name="Teams / Organizations"/>
                <left-bar-dropdown v-for="th in tempHeadings" :key="th"
                                   icon="briefcase" :name="th" url="">
                    <left-bar-static-mini name="Home" url=""/>
                    <left-bar-static-mini name="My Dashboard" url="/dashboard"/>
                    <left-bar-static-mini name="My Dashboard #2" url=""/>
                    <hr>
                    <left-bar-static-mini name="Dashboards..." url=""/>
                    <left-bar-static-mini name="Members..." url=""/>
                    <left-bar-static-modal name="Leave Team" type="danger"/>
                </left-bar-dropdown>

                <left-bar-heading name="Extras"/>
                <left-bar-static icon="plus" name="Join a Team" url=""/>
                <left-bar-static icon="people" name="Create a New Team" url=""/>
            </ul>
        </div>
    </div>
</template>

<script>
import LeftBarHeading from "@/components/leftBar/LeftBarHeading.vue";
import LeftBarStatic from "@/components/leftBar/LeftBarStatic.vue";
import LeftBarStaticMini from "@/components/leftBar/LeftBarStaticMini.vue";
import LeftBarStaticModal from "@/components/leftBar/LeftBarStaticModal.vue";
import LeftBarDropdown from "@/components/leftBar/LeftBarDropdown.vue";

export default {
    name: "LeftBar",
    components: {
        LeftBarHeading,
        LeftBarStatic,
        LeftBarStaticMini,
        LeftBarStaticModal,
        LeftBarDropdown
    },
    props: {
        logo: String,
        url: String
    },
    data: function() {
        return {
            tempHeadings: [
                "SuperTeam",
                "Company Inc.",
                "Europe Sales",
                "Asia Sales"
            ]
        };
    }
}
</script>

<style lang="scss">
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

    overflow-y: auto;
    scrollbar-width: none; /* Firefox */
    -ms-overflow-style: none;  /* IE 10+ */
    &::-webkit-scrollbar {
        width: 0;
        background: transparent; /* Chrome/Safari/Webkit */
    }
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


.xp-vertical-menu .xp-vertical-submenu > hr {
    margin-left: 25px !important;
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

.pull-right {
    float: right;
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




.xp-vertical-menu > li:hover > a, .xp-vertical-menu > li.active > a {
    color: #ffffff;
    background-image: linear-gradient(58deg, #4c7cf3 0, #4cc6f3 100%);
    border-radius: 50px 0 0 50px;
    box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.15);
}

.xp-vertical-menu li.active > a > .icon-arrow-right {
    transform: rotate(-270deg);
}


.disable-scrollbars {
}


</style>
