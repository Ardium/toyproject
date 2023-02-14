import Vue from "vue";
import VueRouter from "vue-router";

Vue.use(VueRouter);
// For Common
import SettingsPage from "./../views/admin-web/BaseAdminSettings.vue";

// For User Web
import NoticePage from "./../views/user-web/notice/BaseNotice.vue";

// For Admin Web
import AdminUsersPage from "./../views/admin-web/users/BaseAdminUsers.vue";

const routes = [
  // For Common
  {
    name: "BaseAdminSettings",
    path: "/page/setting",
    component: SettingsPage,
  },

  // For User Web
  {
    name: "UserWebNoticeList",
    path: "/page/user-web/notice",
    component: NoticePage,
  },

  // For Admin Web
  {
    name: "BaseAdminUsers",
    path: "/page/admin-web/users",
    component: AdminUsersPage,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
