<template>
  <v-container>
    <div class="text-h5 font-weight-medium ma-2">Users (Admin Web)</div>

    <v-divider></v-divider>

    <v-row>
      <v-col class="align-self-center" :cols="4">
        <div>
          <v-switch
            inset
            color="indigo darken-3"
            v-model="readAll"
            :label="`${readAll ? 'All' : 'Available'}`"
            @change="changeReadStatus"
          ></v-switch>
        </div>
      </v-col>
      <v-col class="align-self-center" :cols="8">
        <div class="d-flex justify-end">
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
              <Form
                :mode="'Register'"
                v-on:finishProcess="finishProcess"
              ></Form>
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
                :mode="'Modify'"
                :employee="this.selectedRow[0]"
                v-on:finishProcess="finishProcess"
              ></Form>
            </v-card>
          </v-dialog>

          <v-dialog v-model="dialogDelete" persistent max-width="300">
            <template v-slot:activator="{ on, attrs }">
              <v-btn
                text
                outlined
                class="mx-md-1 elevation-2"
                :disabled="!checkDeleteStatus()"
                v-bind="attrs"
                v-on="on"
                >DELETE
              </v-btn>
            </template>
            <v-card>
              <v-card-title class="text-h5">
                Are you sure you want to delete the user?
              </v-card-title>
              <v-card-text
                >Deleted user cannot be restored, and are immediately reflected
                on the admin portal after deletion.
              </v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="blue darken-1" text @click="dialogDelete = false">
                  Cancel
                </v-btn>
                <v-btn color="blue darken-1" text @click="deleteUser">
                  Confirm
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>

          <v-dialog v-model="dialogCreatePassword" persistent max-width="450">
            <template v-slot:activator="{ on, attrs }">
              <v-btn
                text
                outlined
                class="mx-md-1 elevation-2"
                :disabled="!hasSelectedRow()"
                v-bind="attrs"
                v-on="on"
                >CREATE PASSWORD
              </v-btn>
            </template>
            <v-card>
              <v-card-title class="text-h5">
                Are you sure you want to create temporary password for the user?
              </v-card-title>
              <v-card-text
                >Previous password cannot be restored, and are immediately
                reflected on the admin portal after creation temporary password.
              </v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn
                  color="blue darken-1"
                  text
                  @click="dialogCreatePassword = false"
                >
                  Cancel
                </v-btn>
                <v-btn color="blue darken-1" text @click="createPassword">
                  Confirm
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </div>
      </v-col>
    </v-row>

    <div>
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
      today: new Date(Date.now() - new Date().getTimezoneOffset() * 60000)
        .toISOString()
        .substring(0, 10)
        .replaceAll("-", ""),
      readAll: false,
      dialogRegister: false,
      dialogModify: false,
      dialogDelete: false,
      dialogCreatePassword: false,
      selectedRow: [],
      search: "",
      headers: [
        {
          text: "Company",
          value: "employeeCompany",
        },
        {
          text: "Employee No.",
          value: "employeeNo",
        },
        {
          text: "Name (Kor)",
          value: "employeeName",
        },
        {
          text: "Position",
          value: "employeePosition",
        },
      ],
      users: [],
    };
  },
  created: function () {
    this.getAdminUsers();
  },
  methods: {
    hasSelectedRow: function () {
      return this.selectedRow.length > 0;
    },
    checkDeleteStatus: function () {
      let isAvailable = false;

      if (this.hasSelectedRow() === true) {
        isAvailable = this.selectedRow[0].usageExpDate > this.today;
      }
      return isAvailable;
    },
    finishProcess: function () {
      this.dialogRegister = false;
      this.dialogModify = false;

      window.location.reload();
    },
    changeReadStatus: function () {
      this.getAdminUsers();
    },
    deleteUser: function () {
      this.dialogDelete = false;

      this.$axios
        .put("/api/admin-web/user/delete/" + this.selectedRow[0].employeeNo, {
          employeeNo: this.selectedRow[0].employeeNo,
          // TODO: EmployeeNo??? ???????????? ????????? ???????????? ??????
          updateEmployeeNo: "000000",
          usageExpDate: this.today,
        })
        .catch(function (error) {
          console.log("[ERR/DEL]" + error);
        });
    },
    createPassword: function () {
      this.dialogCreatePassword = false;

      // Generate random string
      let temporaryPassword = Math.random().toString(36).substring(2, 11);
      console.log(temporaryPassword);

      let path = "/api/admin-web/user/update";
      let jsonData = {
        employeeNo: this.selectedRow[0].employeeNo,
        employeePw: temporaryPassword,
        registerEmployeeNo: "000000",
        updateEmployeeNo: "000000",
        pwTrialState: "0",
        temporaryPwState: "Y",
      };

      this.$axios
        .put(path, jsonData)
        .then((response) => {
          if (response.statusText == "OK") {
            alert(
              "Have to notice this temporary password(" +
                temporaryPassword +
                ") to the user(" +
                this.selectedRow[0].employeeNo +
                "/" +
                this.selectedRow[0].employeeName +
                ")."
            );
          }
        })
        .catch(function (error) {
          console.log("[ERR/CREATEPASSWORD]" + error);
        });
    },
    getAdminUsers: function () {
      let path = "/api/admin-web/user/";
      path += this.readAll ? "all" : "available";

      this.$axios
        .get(path)
        .then((response) => {
          this.users = [];

          for (let idx in response.data) {
            let user = {
              employeeCompany: response.data[idx].employeeCompany,
              employeeNo: response.data[idx].employeeNo,
              employeeName: response.data[idx].employeeName,
              employeePosition: response.data[idx].employeePosition,
              employeeDivision: response.data[idx].employeeDivision,
              employeePw: response.data[idx].employeePw,
              employeeTeam: response.data[idx].employeeTeam,
              employeeEmail: response.data[idx].employeeEmail,
              employeePhone: response.data[idx].employeePhone,
              employeeNameEng: response.data[idx].employeeNameEng,
              usageExpDate: response.data[idx].usageExpDate,
            };
            this.users.push(user);
          }
        })
        .catch(function (error) {
          console.log("[ERR/GET]" + error);
        });
    },
  },
};
</script>

<style scoped></style>
