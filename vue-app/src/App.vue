<template>
    <div id="xp-container" v-if="!isSecondaryRoute()">
        <search-modal caption="GO"/>
        <leave-team-modal heading="Are you sure you want to leave SuperTeam?"
                          subtitle="Warning: By leaving the team as an Owner,
                            a new Owner will have to be assigned."
                          main-button="Leave Team" cancel-button="Cancel"/>
        <left-bar :logo="this.$logoPath" url="/"/>
        <div class="xp-rightbar">
            <top-bar/>
            <breadcrumbs :hierarchy="genHierarchy()"
                         ref="crumb" :key="crumbKey"/>
            <div class="xp-contentbar">
                <router-view/>
            </div>
        </div>
        <bottom-bar>
            2020 Eyentelligence - All Rights Reserved.
        </bottom-bar>
    </div>
    <router-view v-else/>
</template>

<script>
import LeftBar from "@/components/leftBar/LeftBar.vue";
import TopBar from "@/components/topBar/TopBar.vue";
import BottomBar from "@/components/BottomBar.vue";
import Breadcrumbs from "@/components/Breadcrumbs.vue";
import SearchModal from "@/components/modals/SearchModal.vue";
import LeaveTeamModal from "@/components/modals/LeaveTeamModal.vue";

export default {
    components: {
        LeftBar,
        TopBar,
        BottomBar,
        Breadcrumbs,
        SearchModal,
        LeaveTeamModal
    },
    data: function() {
        return {
            crumbKey: false
        }
    },
    methods: {
        isSecondaryRoute: function() {
            return ["Login", "Invited"].includes(this.$route.name);
        },
        switchBodyColor: function() {
            let secondary = "secondary";
            if (this.isSecondaryRoute())
                document.body.classList.add(secondary);
            else
                document.body.classList.remove(secondary);
        },
        genHierarchy: function() {
            let hierarchy = {};
            if (this.$route.name === "Dashboard") {
                hierarchy["title"] = "My Dashboard";
                hierarchy["path"] = [
                    {
                        title: "SuperTeam",
                        url: "/"
                    }
                ];
            } else {
                hierarchy["title"] = this.$route.name;
                hierarchy["path"] = [];
            }
            return hierarchy;
        }
    },
    mounted: function() {
        this.switchBodyColor();
    },
    watch: {
        $route: function() {
            this.switchBodyColor();
        }
    },
    beforeUpdate: function() {
        //this.$refs.crumb.$forceUpdate();
        this.crumbKey = !this.crumbKey;
    }
}
</script>

<style lang="scss">
@import url("https://fonts.googleapis.com/css?family=Comfortaa:300,400,700");

$s-size: 600px;
$m-size: 768px;
$m-size-1: $m-size - 1px; //767 px
$l-size: 992px;
$l-size-1: $l-size - 1px; //991 px
$xl-size: 1200px;

$color-background: #f0f4f9;
$color-text: #8a98ac;
$color-link: #4c7cf3;
$color-link-extra: #346bf1;

html {
    position: relative;
    min-height: 100%;
}

body {
    font-family: "Comfortaa", cursive;
    margin: 0;
    background: $color-background;
    font-size: 15px;
    font-weight: 400;
    color: $color-text;
    line-height: 1.5;

    @media (min-width: $m-size) and (max-width: $l-size-1) {
        overflow-x: hidden;
    }

    @media (max-width: $m-size-1) {
        overflow-x: hidden;
    }
}

* {
    outline: none !important;
}

a {
    color: $color-link;
    &:hover { @extend %a-extra; }
    &:active { @extend %a-extra; }
    &:focus { @extend %a-extra; }
}
%a-extra {
    color: $color-link-extra;
    outline: 0;
    text-decoration: none;
}

.h1, .h2, .h3, .h4, .h5, .h6, h1, h2, h3, h4, h5, h6 {
    font-weight: 700;
}
h1 {
    font-size: 40px;
}
@for $i from 2 through 6 {
    h#{$i} {
        font-size: 40 - $i*4px;
    }
}

h4 {
    @media (max-width: $l-size-1) {
        font-size: 22px;
    }

    @media (max-width: $m-size-1) {
        font-size: 20px;
    }
}

h5 {
    @media (max-width: $l-size-1) {
        font-size: 18px;
    }

    @media (max-width: $m-size-1) {
        font-size: 16px;
    }
}

h6 {
    @media (max-width: $l-size-1) {
        font-size: 16px;
    }

    @media (max-width: $m-size-1) {
        font-size: 15px;
    }
}

p {
    line-height: 1.3;
    margin-bottom: 10px;
}


.xp-rightbar {
    margin-left: 250px;
    overflow: hidden;
    min-height: 500px;
    transition: all 0.3s ease;

    @media print {
        margin-left: 0;
    }

    @media (max-width: $m-size) {
        margin-left: 0;
    }

    @media (max-width: $m-size-1) {
        margin-left: 0 !important;
    }
}

.xp-toggle-menu {
    .xp-rightbar {
        margin-left: 0;
    }
}

.xp-contentbar {
    padding: 30px;
    margin-bottom: 30px;
}

$color-button-more: #3b5998;
$color-button-primary: #4c7cf3;
$color-button-remove: #ff4b5b;
$color-button-success: #2bcd72;

$color-button-shadow: #007bff;

.btn {
    border-radius: 3px;
    font-size: 14px;
    padding: 7px 18px;

    &:focus {
        box-shadow: 0 0 0 0.2rem rgba($color-button-shadow, 0.25);
    }

    &-primary {
        color: $color-white;
        background-color: $color-button-primary;
        border-color: $color-button-primary;
        box-shadow: 0 2px 6px 0 rgba($color-button-primary, 0.5);

        &:disabled {
            color: #ffffff;
            background-color: #4c7cf3;
            border-color: #4c7cf3;
        }

        &:focus {
            box-shadow: 0 0 0 0.2rem #acc2f9;
        }
    }

    &-remove {
        border-radius: 50px !important;
        background-color: $color-button-remove;
        color: $color-white;
        padding: 4px 10px;
        font-weight: 700;
    }

    &-rounded {
        border-radius: 20px;
    }

    &-success {
        color: $color-white;
        background-color: $color-button-success;
        border-color: $color-button-success;
        box-shadow: 0 2px 6px 0 rgba($color-button-success, 0.5);

        &:disabled {
            color: #ffffff;
            background-color: #2bcd72;
            border-color: #2bcd72;
        }

        &:focus {
            box-shadow: 0 0 0 0.2rem #7be3a9;
        }
    }

    &-white {
        //color: #2b343a;
        background-color: $color-white;
        border-color: $color-white;
        box-shadow: 0 2px 6px 0 rgba($color-white, 0.5);
    }

    &-more {
        color: $color-white !important;
        background-color: $color-button-more;

        margin-top: 10px;
    }
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



</style>
