<template>
  <v-container>
    <div class="text-h5 font-weight-medium ma-2">Users (Admin Web)</div>

    <v-divider></v-divider>

    <Main></Main>
  </v-container>
</template>

<script>
import Main from "./../../../components/admin-web/users/AdminUsersMain.vue";

export default {
  components: {
    Main,
  },
};
</script>

<style scoped></style>
