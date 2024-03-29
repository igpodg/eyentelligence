import Vue from "vue";
import VueRouter from "vue-router";
import Overview from "@/views/Overview.vue";
import Statistics from "@/views/Statistics.vue";
import TeamHome from "@/views/TeamHome.vue";
import Dashboard from "@/views/Dashboard.vue";
import AllDashboards from "@/views/AllDashboards.vue";
import AllMembers from "@/views/AllMembers.vue";
import JoinTeam from "@/views/JoinTeam.vue";
import CreateTeam from "@/views/CreateTeam.vue";
import Login from "@/views/Login.vue";
import Recover from "@/views/Recover.vue";
import InvitedRegister from "@/views/InvitedRegister.vue";

Vue.use(VueRouter);

let routes = [
    {
        path: "/",
        name: "Overview",
        component: Overview
    },
    {
        path: "/statistics",
        name: "Statistics",
        component: Statistics
    },
    {
        path: "/team/:id",
        name: "TeamHome",
        component: TeamHome
    },
    {
        path: "/team/:id/dashboard/:dashid",
        name: "Dashboard",
        component: Dashboard
    },
    {
        path: "/team/:id/dashboards",
        name: "AllDashboards",
        component: AllDashboards
    },
    {
        path: "/team/:id/members",
        name: "AllMembers",
        component: AllMembers
    },
    {
        path: "/join",
        name: "JoinTeam",
        component: JoinTeam
    },
    {
        path: "/create",
        name: "CreateTeam",
        component: CreateTeam
    },
    {
        path: "/login",
        name: "Login",
        component: Login
    },
    {
        path: "/recover",
        name: "Recover",
        component: Recover
    },
    {
        path: "/invited/:token",
        name: "Invited",
        component: InvitedRegister
    },
    {
        path: "/register",
        name: "Register",
        component: InvitedRegister
    }
];

let router = new VueRouter({
    routes
});

// eslint-disable-next-line no-unused-vars
router.afterEach(function(to, from) {
    if (to.path === "/logout") {
        Vue.prototype.$removeSession();
        router.replace("/");
    }
});

export default router;
