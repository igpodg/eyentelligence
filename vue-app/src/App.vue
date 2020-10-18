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
$m-size-1: $m-size - 1px; //767
$l-size: 992px;
$l-size-1: $l-size - 1px; //991
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

</style>
