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
    if (!("body" in options))
        options["body"] = null;

    xhr.open(options.method, url, false);
    if (options.body !== null) {
        xhr.setRequestHeader("Accept", "application/json");
        //xhr.setRequestHeader("Accept-Encoding", "charset=utf-8");
        xhr.setRequestHeader("Content-Type", "application/json;charset=utf-8");
    }
    if ("headers" in options) {
        for (const [key, value] of Object.entries(options.headers)) {
            xhr.setRequestHeader(key, value);
        }
    }
    xhr.send(options.body);

    if (xhr.status === 200)
        return xhr.responseText;

    return null;
};
Vue.prototype.$getSession = function() {
    return window.localStorage.getItem("eyentelligenceSession");
}
Vue.prototype.$setSession = function(session) {
    window.localStorage.setItem("eyentelligenceSession", session);
}
Vue.prototype.$removeSession = function() {
    window.localStorage.removeItem("eyentelligenceSession");
}

new Vue({
    router,
    render: function(createElem) {
        return createElem(App);
    }
}).$mount("#app");
