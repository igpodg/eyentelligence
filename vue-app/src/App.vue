<template>
    <div id="xp-container" v-if="!isSecondaryRoute()">
        <search-modal caption="GO"/>
        <leave-team-modal heading="Are you sure you want to leave SuperTeam?"
                          subtitle="Warning: By leaving the team as an Owner,
                            a new Owner will have to be assigned."
                          main-button="Leave Team" cancel-button="Cancel"/>
        <left-bar :logo="this.$logoPath" url="/" :teams="teams"/>
        <div class="xp-rightbar">
            <top-bar :user="user"/>
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
            crumbKey: false,
            teams: [],
            user: {}
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
                        title: this.teams[this.$route.params.id],
                        url: "/"
                    }
                ];
            } else {
                hierarchy["title"] = this.$route.name;
                hierarchy["path"] = [];
            }
            return hierarchy;
        },
        downloadTeams: function() {
            this.$logDetailed("Querying the team listing...");
            let response = this.$fetchSync("https://127.0.0.1:9090/team", {
                headers: { "X-API-Key": "xxxxxxxx" }
            });
            response = JSON.parse(response);
            this.teams = response.map(x => x.name);
            this.$logDetailed("Team listing query finished.");
        },
        downloadUser: function() {
            this.$logDetailed("Querying the user info...");
            let response = this.$fetchSync("https://127.0.0.1:9090/user", {
                headers: { "X-API-Key": "xxxxxxxx" }
            });
            response = JSON.parse(response);
            this.user = response;
            this.$logDetailed("User info query finished.");
        }
    },
    created: function() {
        this.downloadTeams();
        this.downloadUser();
    },
    mounted: function() {
        this.switchBodyColor();
        this.$eventBus.$on("update-teams", this.downloadTeams);
        this.$eventBus.$on("update-user", this.downloadUser);
    },
    beforeDestroy: function() {
        this.$eventBus.$off("update-user");
        this.$eventBus.$off("update-teams");
    },
    watch: {
        $route: function() {
            this.switchBodyColor();
        }
    },
    beforeUpdate: function() {
        //this.$refs.crumb.$forceUpdate();
        this.crumbKey = !this.crumbKey;
        this.$eventBus.$emit("crumb-updated");
    }
}
</script>

<style lang="scss">
//@import url("https://fonts.googleapis.com/css?family=Comfortaa:300,400,700");
@import "./fonts/comfortaa";

$s-size: 600px;
$m-size: 768px;
$m-size-1: $m-size - 1px; //767 px
$l-size: 992px;
$l-size-1: $l-size - 1px; //991 px
$xl-size: 1200px;

$color-white: #ffffff;

$color-background: #f0f4f9;
$color-text: #8a98ac;

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

$color-link: #4c7cf3;
$color-link-extra: #346bf1;

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

$heading-font-sizes: ("h1": 40, "h2": 32, "h3": 28, "h4": 24, "h5": 20, "h6": 16);
@each $heading, $size in $heading-font-sizes {
    #{$heading} {
        font-size: #{$size}px;
    }
}

$heading-font-sizes-responsive-l: ("h3": 26, "h4": 22, "h5": 18, "h6": 16);
@each $heading, $l in $heading-font-sizes-responsive-l {
    #{$heading} {
        @media (max-width: $l-size-1) {
            font-size: #{$l}px;
        }
    }
}

$heading-font-sizes-responsive-m: ("h3": 24, "h4": 20, "h5": 16, "h6": 15);
@each $heading, $m in $heading-font-sizes-responsive-m {
    #{$heading} {
        @media (max-width: $m-size-1) {
            font-size: #{$m}px;
        }
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

</style>
