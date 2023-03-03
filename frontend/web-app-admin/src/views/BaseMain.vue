<template>
  <v-container>
    <v-navigation-drawer v-model="drawer" app>
      <v-list dense>
        <!-- User(Employee) information -->
        <v-list-item>
          <v-list-item-avatar>
            <v-img src="https://cdn.vuetifyjs.com/images/john.png"></v-img>
          </v-list-item-avatar>
        </v-list-item>
        <v-list-item link>
          <v-list-item-content>
            <v-list-item-title class="text-h6">Sylvia Park</v-list-item-title>
            <v-list-item-subtitle>ardiums@gmail.com</v-list-item-subtitle>
          </v-list-item-content>
        </v-list-item>
        <v-divider></v-divider>

        <!-- Menu/Navigation Bar list -->
        <v-list-item-group v-model="selectedItem" color="primary">
          <v-list-item
            v-for="(item, i) in navBasicItems"
            :key="'common' + i"
            :to="item.to"
          >
            <v-list-item-icon>
              <v-icon v-text="item.icon"></v-icon>
            </v-list-item-icon>
            <v-list-item-content>
              <v-list-item-title v-text="item.text"></v-list-item-title>
            </v-list-item-content>
          </v-list-item>

          <v-divider></v-divider>

          <v-subheader>User Web</v-subheader>
          <v-list-item
            v-for="(item, i) in navUserWebItems"
            :key="'user-web' + i"
            :to="item.to"
          >
            <v-list-item-icon>
              <v-icon v-text="item.icon"></v-icon>
            </v-list-item-icon>
            <v-list-item-content>
              <v-list-item-title v-text="item.text"></v-list-item-title>
            </v-list-item-content>
          </v-list-item>

          <v-divider></v-divider>

          <v-subheader>Admin Web</v-subheader>
          <v-list-item
            v-for="(item, i) in navAdminWebItems"
            :key="'admin-web' + i"
            :to="item.to"
          >
            <v-list-item-icon>
              <v-icon v-text="item.icon"></v-icon>
            </v-list-item-icon>
            <v-list-item-content>
              <v-list-item-title v-text="item.text"></v-list-item-title>
            </v-list-item-content>
          </v-list-item>
        </v-list-item-group>
      </v-list>
    </v-navigation-drawer>

    <v-app-bar app>
      <v-app-bar-nav-icon @click="drawer = !drawer"></v-app-bar-nav-icon>

      <v-toolbar-title>Application</v-toolbar-title>
    </v-app-bar>

    <v-main>
      <v-container fluid>
        <router-view></router-view>
      </v-container>
    </v-main>

    <v-footer app>
      <!-- -->
    </v-footer>
  </v-container>
</template>

<script>
export default {
  data: () => ({
    drawer: null,

    // For Menu/Navigation Bar
    selectedItem: 0,

    navBasicItems: [
      {
        text: "Dashboard",
        icon: "mdi-view-dashboard",
        to: "/page/dashboard",
      },
      {
        text: "Setting",
        icon: "mdi-cog-outline",
        to: "/page/setting",
      },
    ],
    navUserWebItems: [
      {
        text: "Notice",
        icon: "mdi-file-outline",
        to: "/page/user-web/notice",
      },
      {
        text: "User Management",
        icon: "mdi-account",
        to: "/page/user-web/users",
      },
      {
        text: "Menu Management",
        icon: "mdi-lan-check",
        to: "/page/user-web/menu",
      },
    ],
    navAdminWebItems: [
      {
        text: "User Management",
        icon: "mdi-account-supervisor-circle",
        to: "/page/admin-web/users",
      },
      {
        text: "Rights Management",
        icon: "mdi-lock-open",
        to: "/page/admin-web/rights",
      },
    ],
  }),
};
</script>

<style></style>
