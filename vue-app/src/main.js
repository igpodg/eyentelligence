import Vue from "vue";
import App from "./App.vue";
import "./registerServiceWorker";
import router from "./router";

Vue.config.productionTip = false;
//Vue.prototype.$appName = "My App";
Vue.prototype.$eventBus = new Vue();
Vue.prototype.$logoPath = "assets/img/logo.png";
Vue.prototype.$logDetailed = function(message) {
    let currentDateTime = new Date();
    let hour = currentDateTime.getUTCHours().toString().padStart(2, "0");
    let min = currentDateTime.getUTCMinutes().toString().padStart(2, "0");
    let sec = currentDateTime.getUTCSeconds().toString().padStart(2, "0");
    let msec = currentDateTime.getUTCMilliseconds().toString().padStart(3, "0");
    console.log(`# [${hour}:${min}:${sec}.${msec}] ${message}`);
};
Vue.prototype.$fetchSync = function(url, options= {}) {
    let xhr = new XMLHttpRequest();
    if (!("method" in options))
        options["method"] = "GET";
    xhr.open(options.method, url, false);
    if ("headers" in options) {
        for (const [key, value] of Object.entries(options.headers)) {
            xhr.setRequestHeader(key, value);
        }
    }
    if (options.method !== "GET")
        xhr.send(options.body);
    else
        xhr.send(null);

    if (xhr.status === 200)
        return xhr.responseText;

    return null;
};

new Vue({
    router,
    render: function(createElem) {
        return createElem(App);
    }
}).$mount("#app");
