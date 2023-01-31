<template>
  <v-container>
    <div class="text-h5 font-weight-medium ma-2">Users (Admin Web)</div>

    <v-divider></v-divider>

    <div class="ma-md-5">
      <div class="d-flex justify-end mt-5">
        <v-dialog persistent max-width="1000px" v-model="dialogRegister">
          <template v-slot:activator="{ on, attrs }">
            <v-btn
              text
              outlined
              class="mx-md-1 elevation-2"
              v-bind="attrs"
              v-on="on"
              >REGISTER
            </v-btn>
          </template>
          <v-card>
            <Form v-on:finishProcess="finishProcess"></Form>
          </v-card>
        </v-dialog>

        <v-dialog persistent max-width="1000px" v-model="dialogModify">
          <template v-slot:activator="{ on, attrs }">
            <v-btn
              text
              outlined
              class="mx-md-1 elevation-2"
              v-bind="attrs"
              v-on="on"
              :disabled="!hasSelectedRow()"
              >MODIFY
            </v-btn>
          </template>
          <v-card>
            <Form
              :employee="this.selectedRow[0]"
              v-on:finishProcess="finishProcess"
            ></Form>
          </v-card>
        </v-dialog>

        <v-btn
          text
          outlined
          class="mx-md-1 elevation-2"
          :disabled="!hasSelectedRow()"
          >DELETE
        </v-btn>
      </div>

      <v-card-title>
        Admin Users
        <v-spacer></v-spacer>
        <v-text-field
          single-line
          hide-details
          v-model="search"
          append-icon="mdi-magnify"
          label="Search"
        ></v-text-field>
      </v-card-title>
      <v-data-table
        single-select
        show-select
        item-key="employeeNo"
        show-expand
        class="mt-md-2 elevation-5"
        v-model="selectedRow"
        :search="search"
        :headers="headers"
        :items="users"
        :items-per-page="10"
      >
        <template v-slot:expanded-item="{ headers, item }">
          <td class="pa-5" :colspan="headers.length">
            {{ "- Division: " + item.division }} <br />{{
              "- Team: " + item.team
            }}
            <br />{{ "- Name (Eng): " + item.nameEng }} <br />{{
              "- Email: " + item.email
            }}
            <br />{{ "- Phone: " + item.phone }}
          </td>
        </template>
      </v-data-table>
    </div>
  </v-container>
</template>

<script>
import Form from "./../../../components/admin-web/users/AdminUsersDetail.vue";

export default {
  components: {
    Form,
  },
  data() {
    return {
      dialogRegister: false,
      dialogModify: false,
      selectedRow: [],
      search: "",
      headers: [
        {
          text: "Company",
          value: "company",
        },
        {
          text: "Employee No.",
          value: "employeeNo",
        },
        {
          text: "Name (Kor)",
          value: "nameKor",
        },
        {
          text: "Position",
          value: "position",
        },
      ],
      users: [
        {
          company: "여름 컴패니",
          employeeNo: "100010",
          nameKor: "홍길동",
          position: "사원",

          // Expanded contents
          division: "RnD",
          team: "Web Test",
          email: "test-gildong@gmail.com",
          phone: "010-1111-1111",
          nameEng: "Gildong Hong",
          usageExpDate: "9999-12-31",
        },
        {
          company: "(주) 가을",
          employeeNo: "200010",
          nameKor: "김길동",
          position: "팀장",

          // Expanded contents
          division: "RnD",
          team: "개발 1팀",
          email: "dev-gildong@gmail.com",
          phone: "010-1111-2222",
          nameEng: "Gildong Kim",
          usageExpDate: "9999-12-31",
        },
      ],
    };
  },
  methods: {
    hasSelectedRow: function () {
      return this.selectedRow.length > 0;
    },
    finishProcess: function () {
      this.dialogRegister = false;
      this.dialogModify = false;
    },
  },
};
</script>

<style scoped></style>
