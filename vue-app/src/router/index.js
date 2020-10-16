import Vue from "vue";
import VueRouter from "vue-router";
import Overview from "@/views/Overview.vue";
import Dashboard from "@/views/Dashboard.vue";
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
        path: "/dashboard",
        name: "Dashboard",
        component: Dashboard
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
