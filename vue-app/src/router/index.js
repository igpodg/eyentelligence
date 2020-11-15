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
import Invited from "@/views/Invited.vue";

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
        path: "/dashboard/:id",
        name: "Dashboard",
        component: Dashboard
    },
    {
        path: "/dashboards/:id",
        name: "AllDashboards",
        component: AllDashboards
    },
    {
        path: "/members/:id",
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
        path: "/invited",
        name: "Invited",
        component: Invited
    }
];

let router = new VueRouter({
    routes
});

export default router;
