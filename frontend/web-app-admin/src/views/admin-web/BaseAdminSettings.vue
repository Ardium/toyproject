<template>
  <div>
    <PrivateInformation :title="'Setting'"></PrivateInformation>
    <v-divider></v-divider>
  </div>
</template>

<script>
import PrivateInformation from "./../../components/admin-web/users/AdminUsersDetail.vue";

export default {
  components: {
    PrivateInformation,
  },
};
</script>

<style></style>
