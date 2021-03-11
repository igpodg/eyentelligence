<template>
    <div id="xp-container" v-if="!isSecondaryRoute()">
        <search-modal caption="GO"/>
        <leave-team-modal/>
        <left-bar :logo="this.$logoPath" url="/" :teams="teams"
                                                 :dashboards="dashboards"/>
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
            secondaryRoutes: ["Login", "Recover", "Invited", "Register"],
            crumbKey: false,
            teams: [],
            dashboards: {},
            user: {}
        }
    },
    methods: {
        isSecondaryRoute: function() {
            return this.secondaryRoutes.includes(this.$route.name);
        },
        switchBodyColor: function() {
            let secondary = "secondary";
            if (this.isSecondaryRoute())
                document.body.classList.add(secondary);
            else
                document.body.classList.remove(secondary);
        },
        getTeamById: function(id) {
            // todo: find a better way to do this
            for (let team of this.teams) {
                if (team.id === id)
                    return team;
            }
            // not found
            this.$router.push("/");
            return null;
        },
        getDashboardById: function(teamId, id) {
            for (let dash of this.dashboards[teamId]) {
                if (dash.id === id)
                    return dash;
            }
            return null;
        },
        genHierarchy: function() {
            let hierarchy = {};
            let parsedId = parseInt(this.$route.params.id);
            if (this.$route.name === "Dashboard") {
                hierarchy["title"] = this.getDashboardById(parsedId,
                    parseInt(this.$route.params.dashid)).name;
                hierarchy["path"] = [
                    {
                        title: this.getTeamById(parsedId).name,
                        url: "/team/" + this.$route.params.id
                    }
                ];
            } else if (this.$route.name === "TeamHome") {
                hierarchy["title"] = this.getTeamById(parsedId).name;
                hierarchy["path"] = [];
            } else if (this.$route.name === "JoinTeam") {
                hierarchy["title"] = "Join a Team";
                hierarchy["path"] = [];
            } else if (this.$route.name === "CreateTeam") {
                hierarchy["title"] = "Create a New Team";
                hierarchy["path"] = [];
            } else if (this.$route.name === "AllDashboards") {
                hierarchy["title"] = "All Dashboards";
                hierarchy["path"] = [
                    {
                        title: this.getTeamById(parsedId).name,
                        url: "/team/" + this.$route.params.id
                    }
                ];
            } else if (this.$route.name === "AllMembers") {
                hierarchy["title"] = "All Members";
                hierarchy["path"] = [
                    {
                        title: this.getTeamById(parsedId).name,
                        url: "/team/" + this.$route.params.id
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
            //this.teams = response.map(x => x.name);
            this.teams = response;
            this.$logDetailed("Team listing query finished.");

            // todo: maybe there's a better way
            // send to other components
            this.$eventBus.$emit("update-teams-overview");
        },
        downloadDashboards: function() {
            this.$logDetailed("Querying all dashboards...");
            let response = this.$fetchSync("https://127.0.0.1:9090/dashboard", {
                headers: { "X-API-Key": "xxxxxxxx" }
            });
            response = JSON.parse(response);
            response = response.reduce(function(acc, val) {
                if (!(val.team.id in acc))
                    acc[val.team.id] = [];
                acc[val.team.id].push(val);
                return acc;
            }, {});
            this.dashboards = response;
            this.$logDetailed("All dashboards query finished.");
        },
        downloadUser: function() {
            this.$logDetailed("Querying the user info...");
            let userId = 1; // todo: get this from the token
            let response = this.$fetchSync("https://127.0.0.1:9090/user/" + userId, {
                headers: { "X-API-Key": "xxxxxxxx" }
            });
            response = JSON.parse(response);
            this.user = response;
            this.$logDetailed("User info query finished.");
        },
        checkSession: function() {
            this.$logDetailed("checking session...");
            if (this.$getSession() === null)
                this.$router.replace("/register");
        }
    },
    created: function() {
        this.downloadTeams();
        this.downloadDashboards();
        this.downloadUser();
    },
    mounted: function() {
        this.switchBodyColor();
        this.$eventBus.$on("update-teams", this.downloadTeams);
        this.$eventBus.$on("update-dashboards", this.downloadDashboards);
        this.$eventBus.$on("update-user", this.downloadUser);
    },
    beforeDestroy: function() {
        this.$eventBus.$off("update-user");
        this.$eventBus.$off("update-dashboards");
        this.$eventBus.$off("update-teams");
    },
    watch: {
        $route: function() {
            this.switchBodyColor();
        }
    },
    beforeMount: function() {
        if (!this.isSecondaryRoute())
            this.checkSession();
    },
    beforeUpdate: function() {
        if (!this.isSecondaryRoute())
            this.checkSession();

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
