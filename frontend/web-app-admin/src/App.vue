<template>
  <v-app>
    <Login v-if="true"></Login>
    <Main v-else></Main>
  </v-app>
</template>

<script>
import Login from "./components/common/UserLogin.vue";
import Main from "./views/BaseMain.vue";

export default {
  name: "App",
  components: {
    Login,
    Main,
  },
  data: () => ({}),
};
</script>
