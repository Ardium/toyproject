import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import axios from "axios";
import vuetify from "./plugins/vuetify";

// axios.defaults.baseURL = "http://localhost:8080";
Vue.prototype.$axios = axios;
Vue.config.productionTip = false;

new Vue({
  router,
  axios,
  vuetify,
  render: (h) => h(App),
}).$mount("#app");
