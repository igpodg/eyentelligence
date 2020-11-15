import Vue from "vue";
import VueRouter from "vue-router";
import Overview from "@/views/Overview.vue";
import Dashboard from "@/views/Dashboard.vue";
import TeamHome from "@/views/TeamHome.vue";
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
        // todo: implement a more unique identifier
        //   or change the api
        path: "/dashboard/:id",
        name: "Dashboard",
        component: Dashboard
    },
    {
        path: "/team/:id",
        name: "TeamHome",
        component: TeamHome
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
