<template>
  <v-container>
    <div class="text-h5 font-weight-medium ma-2">
      {{ mode }}
    </div>

    <v-divider class="mb-5"></v-divider>

    <div class="ma-md-2">
      <v-row>
        <v-col>
          <v-combobox
            outlined
            dense
            hide-selected
            required
            clearable
            :disabled="isModifyMode() || isSettingMode()"
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
            dense
            clearable
            :disabled="isModifyMode() || isSettingMode()"
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
            dense
            clearable
            :disabled="isSettingMode()"
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
            dense
            clearable
            :disabled="isSettingMode()"
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
            dense
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
            dense
            clearable
            type="email"
            :rules="[rules.required, rules.email]"
            v-model="email"
            label="이메일 주소"
            placeholder="gildong-hong@gmail.com"
          ></v-text-field>
        </v-col>
      </v-row>

      <v-row>
        <v-col v-show="!isSettingMode()">
          <v-text-field
            text
            outlined
            dense
            clearable
            :append-icon="showPw ? 'mdi-eye' : 'mdi-eye-off'"
            :type="showPw ? 'text' : 'password'"
            :rules="[rules.required, rules.password]"
            v-model="password"
            label="비밀번호"
            @click:append="showPw = !showPw"
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
            v-model="division"
            label="부서"
          ></v-text-field>
        </v-col>
        <v-col>
          <v-text-field
            text
            outlined
            dense
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
            dense
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
            dense
            clearable
            v-show="!isSettingMode()"
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
const MODE_REGISTER = "REGISTER";
const MODE_MODIFY = "MODIFY";
const MODE_SETTING = "SETTING";

export default {
  props: ["employee", "mode"],
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

      showPw: false,

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
  mounted: function () {
    this.$nextTick(function () {
      if (this.mode === MODE_MODIFY) {
        this.setData();
      }
    });
  },
  watch: {
    employee: {
      handler() {
        this.setData();
      },
    },
  },
  methods: {
    setData: function () {
      this.company = this.employee.company;
      this.employeeNo = this.employee.employeeNo;
      this.nameKor = this.employee.nameKor;
      this.nameEng = this.employee.nameEng;
      this.password = this.employee.password;
      this.phone = this.employee.phone;
      this.email = this.employee.email;
      this.division = this.employee.division;
      this.team = this.employee.team;
      this.position = this.employee.position;
    },
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
    isModifyMode: function () {
      return this.mode.toUpperCase() === MODE_MODIFY;
    },
    isSettingMode: function () {
      return this.mode.toUpperCase() === MODE_SETTING;
    },
    clickOk: function () {
      let path = "/api/admin-web/user";

      let jsonData = {
        employeeNo: this.employeeNo,
        registerEmployeeNo: "000000",
        updateEmployeeNo: "000000",

        employeeName: this.nameKor,
        employeeNameEng: this.nameEng,
        employeePhone: this.phone,
        employeeEmail: this.email,
        employeePw: this.password,
        employeeCompany: this.company,
        employeeDivision: this.division,
        employeeTeam: this.team,
        employeePosition: this.position,
        usageExpDate: this.usageExpiryDate.replaceAll("-", ""),
        pwTrialState: "0",
        temporaryPwState: "N",
      };

      switch (this.mode.toUpperCase()) {
        case MODE_REGISTER:
          console.log("REGISTER PROCESS");

          this.$axios.post(path, jsonData).catch(function (error) {
            console.log("[ERR/REG]" + error);
          });
          break;
        case MODE_MODIFY:
          console.log("MODIFY PROCESS");
          path += "/update/";

          this.$axios
            .put(path + this.employeeNo, jsonData)
            .catch(function (error) {
              console.log("[ERR/MOD]" + error);
            });
          break;
        default:
          // error
          console.log("[ERR/clickOk] Mode:" + this.mode);
          break;
      }

      this.$emit("finishProcess");
    },
    clickCancel: function () {
      console.log("press cancel button");
      this.$emit("finishProcess");
    },
  },
};
</script>

<style scoped></style>
