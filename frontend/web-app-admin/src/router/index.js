import Vue from "vue";
import VueRouter from "vue-router";

Vue.use(VueRouter);

// For User Web
import NoticePage from "./../views/user-web/notice/BaseNotice.vue";

const routes = [
  // For User Web
  {
    name: "UserWebNoticeList",
    path: "/page/user-web/notice",
    component: NoticePage,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
