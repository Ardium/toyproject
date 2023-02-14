<template>
  <v-container>
    <PrivateInformation :title="'Setting'"></PrivateInformation>
    <v-divider></v-divider>

    <UserPassword></UserPassword>
  </v-container>
</template>

<script>
import PrivateInformation from "./../../components/admin-web/users/AdminUsersDetail.vue";
import UserPassword from "./../../components/common/UserPassword.vue";

export default {
  components: {
    PrivateInformation,
    UserPassword,
  },
};
</script>

<style></style>
