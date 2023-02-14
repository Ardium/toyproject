<template>
  <div>
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
  </div>
</template>

<script>
export default {
  props: ["password"],
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
      // TODO: SOMETHINGS
    },
    clickOk: function () {
      if (this.password === this.passwordCur) {
        if (this.passwordTobe1 === this.passwordTobe2) {
          // TODO: 변경 Axios 연동
        } else {
          this.alert("변경할 비밀번호가 서로 일치하지 않습니다.");
        }
      } else {
        this.alert("입력한 현재 비밀번호가 일치하지 않습니다.");
      }
    },
  },
};
</script>

<style></style>
