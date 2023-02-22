<template>
  <v-container>
    <PrivateInformation
      :mode="'Setting'"
      :employee="this.employee"
    ></PrivateInformation>
    <v-divider></v-divider>

    <UserPassword :employee="this.employee"></UserPassword>
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
  data() {
    return {
      employee: {},
    };
  },
  mounted: function () {
    this.$nextTick(function () {
      this.getUserInformation();
    });
  },
  methods: {
    getUserInformation: function () {
      // TODO: employeeNo를 로그인한 사용자의 직원 번호로 교체 필요
      let employeeNo = "123456";
      let path = "/api/admin-web/user/" + employeeNo;

      this.$axios
        .get(path)
        .then((response) => {
          this.employee = response.data;
        })
        .catch(function (error) {
          console.log("[ERR/getUserInformation]" + error);
        });
    },
  },
};
</script>

<style></style>
