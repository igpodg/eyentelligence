import Vue from "vue";
import App from "./App.vue";
import "./registerServiceWorker";
import router from "./router";

Vue.config.productionTip = false;
Vue.prototype.$appName = "My App";

new Vue({
    router,
    render: function(createElem) {
        return createElem(App);
    }
}).$mount("#app");
