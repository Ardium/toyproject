<template>
  <v-container>
    <div class="text-h5 font-weight-medium ma-3">Password</div>

    <div class="ma-md-2">
      <v-row>
        <v-col>
          <v-text-field
            text
            outlined
            dense
            clearable
            :type="'password'"
            :rules="[rules.required, rules.password]"
            v-model="passwordCur"
            label="현재 비밀번호"
          ></v-text-field>
        </v-col>
      </v-row>

      <v-row>
        <v-col>
          <v-text-field
            text
            outlined
            dense
            clearable
            :type="'password'"
            :rules="[rules.required, rules.password]"
            v-model="passwordTobe1"
            label="변경할 비밀번호"
          ></v-text-field>
        </v-col>
        <v-col>
          <v-text-field
            text
            outlined
            dense
            clearable
            :type="'password'"
            :rules="[rules.required, rules.password]"
            v-model="passwordTobe2"
            label="변경할 비밀번호"
          ></v-text-field>
        </v-col>
      </v-row>
    </div>

    <div class="d-flex justify-end">
      <v-btn
        text
        outlined
        dense
        class="mx-md-1 elevation-2"
        @click="clickCancel"
        >Cancel
      </v-btn>
      <v-btn text outlined dense class="mx-md-1 elevation-2" @click="clickOk"
        >OK
      </v-btn>
    </div>
  </v-container>
</template>

<script>
export default {
  props: ["employee"],
  data() {
    return {
      rules: {
        required: (value) => !!value || "Required.",
        password: (value) => {
          const pattern = /^(?=.*\d)(?=.*[a-z]).{8,}$/;
          return (
            pattern.test(value) ||
            "영어 소문자, 숫자를 포함하여 8자리 이상으로 구성되어야 합니다."
          );
        },
      },
      passwordCur: "",
      passwordTobe1: "",
      passwordTobe2: "",
    };
  },
  methods: {
    clickCancel: function () {
      this.passwordCur = "";
      this.passwordTobe1 = "";
      this.passwordTobe2 = "";
    },
    clickOk: function () {
      let path = "/api/admin-web/user/update";
      let jsonData = {
        employeeNo: this.employee.employeeNo,
        employeePw: this.passwordTobe1,
        registerEmployeeNo: "000000",
        updateEmployeeNo: "000000",
        pwTrialState: "0",
        temporaryPwState: "N",
      };

      if (this.passwordTobe1 === this.passwordTobe2) {
        this.$axios.put(path, jsonData).catch(function (error) {
          console.log("[ERR/UserPwChange]" + error);
        });
      } else {
        this.alert("변경할 비밀번호가 서로 일치하지 않습니다.");
      }
    },
  },
};
</script>

<style></style>
