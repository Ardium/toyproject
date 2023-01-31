<template>
  <v-container>
    <div class="text-h5 font-weight-medium ma-2">
      User Information (Admin Web)
    </div>

    <v-divider class="mb-5"></v-divider>

    <div class="ma-md-2">
      <v-row>
        <v-col>
          <v-combobox
            outlined
            hide-selected
            required
            clearable
            :items="companies"
            :rules="[rules.required]"
            v-model="company"
            label="회사"
            @change="changeCompany"
          ></v-combobox>
        </v-col>
        <v-col>
          <v-text-field
            text
            outlined
            clearable
            :rules="[rules.required, rules.employeeNo]"
            v-model="employeeNo"
            label="직원번호"
            placeholder="000000"
          ></v-text-field>
        </v-col>
      </v-row>
      <v-row>
        <v-col>
          <v-text-field
            text
            outlined
            clearable
            :rules="[rules.required]"
            v-model="nameKor"
            label="국문이름"
            placeholder="홍길동"
          ></v-text-field>
        </v-col>
        <v-col>
          <v-text-field
            text
            outlined
            clearable
            v-model="nameEng"
            label="영문이름"
            placeholder="Gildong Hong"
          ></v-text-field>
        </v-col>
      </v-row>

      <v-row>
        <v-col>
          <v-text-field
            text
            outlined
            clearable
            :rules="[rules.required, rules.phone]"
            v-model="phone"
            label="휴대폰 번호"
            placeholder="010-####-####"
          ></v-text-field>
        </v-col>
        <v-col>
          <v-text-field
            text
            outlined
            clearable
            type="email"
            :rules="[rules.required, rules.email]"
            v-model="email"
            label="이메일 주소"
            placeholder="gildong-hong@gmail.com"
          ></v-text-field>
        </v-col>
      </v-row>
      <v-text-field
        text
        outlined
        clearable
        :rules="[rules.required, rules.password]"
        v-model="password"
        label="비밀번호"
      ></v-text-field>

      <v-row>
        <v-col>
          <v-text-field
            text
            outlined
            clearable
            v-model="division"
            label="부서"
          ></v-text-field>
        </v-col>
        <v-col>
          <v-text-field
            text
            outlined
            clearable
            v-model="team"
            label="팀"
          ></v-text-field>
        </v-col>
      </v-row>

      <v-row>
        <v-col>
          <v-text-field
            text
            outlined
            clearable
            :rules="[rules.required]"
            v-model="position"
            label="직책"
          ></v-text-field>
        </v-col>
        <v-col>
          <v-text-field
            text
            outlined
            clearable
            type="date"
            :rules="[rules.required]"
            :min="minUsageExpiryDate"
            v-model="usageExpiryDate"
            label="사용기한일자"
          ></v-text-field>
        </v-col>
      </v-row>
    </div>

    <div class="d-flex justify-end">
      <router-link
        :to="{ name: 'BaseAdminUsers' }"
        style="text-decoration: none"
      >
        <v-btn text outlined class="mx-md-1 elevation-2">Previous</v-btn>
      </router-link>
      <v-btn text outlined class="mx-md-1 elevation-2" @click="clickOk"
        >OK</v-btn
      >
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
        employeeNo: (value) =>
          value.length == 6 || "직원번호는 6자리로 구성되어야 합니다.",
        phone: (value) => {
          const pattern = /^(\d{3})-(\d{4})-(\d{4})$/;
          return pattern.test(value) || "휴대폰 번호 형식에 맞지 않습니다.";
        },
        email: (value) => {
          const pattern =
            /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
          return pattern.test(value) || "이메일 주소 형식에 맞지 않습니다.";
        },
        password: (value) => {
          const pattern = /^(?=.*\d)(?=.*[a-z]).{8,}$/;
          return (
            pattern.test(value) ||
            "영어 소문자, 숫자를 포함하여 8자리 이상으로 구성되어야 합니다."
          );
        },
      },
      minUsageExpiryDate: new Date(
        Date.now() - new Date().getTimezoneOffset() * 60000
      )
        .toISOString()
        .substring(0, 10),
      companies: ["봄 회사", "여름 컴퍼니", "(주) 가을", "겨울 패밀리"],

      // V-MODEL
      company: [],
      employeeNo: "",
      nameKor: "",
      nameEng: "",
      phone: "",
      email: "",
      password: "",
      division: "",
      team: "",
      position: "",
      usageExpiryDate: "9999-12-31",
    };
  },
  created: function () {
    this.company = this.employee.company;
    this.employeeNo = this.employee.employeeNo;
    this.nameKor = this.employee.nameKor;
    this.nameEng = this.employee.nameEng;
    this.phone = this.employee.phone;
    this.email = this.employee.email;
    this.division = this.employee.division;
    this.team = this.employee.team;
    this.position = this.employee.position;
  },
  methods: {
    changeCompany: function () {
      switch (this.company) {
        case "봄 회사": // 100000 ~ 199999
          this.employeeNo = "1";
          break;
        case "여름 컴퍼니": // 300000 ~ 399999
          this.employeeNo = "3";
          break;
        case "(주) 가을": // 500000 ~ 599999
          this.employeeNo = "5";
          break;
        case "겨울 패밀리": // 700000 ~ 799999
          this.employeeNo = "7";
          break;
        default:
          // error
          break;
      }
    },
    clickOk: function () {
      console.log("press ok button");
    },
  },
};
</script>

<style scoped></style>
