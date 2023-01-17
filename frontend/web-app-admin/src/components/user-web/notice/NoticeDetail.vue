<template>
  <div class="ma-md-5">
    <div class="d-flex justify-end">
      <v-btn
        text
        outlined
        class="mx-md-1 elevation-2"
        :class="{ visibility: getVisibleStatusBtns() }"
        :disabled="getEnableStatusElements()"
        @click="refreshElements"
        >REFRESH
      </v-btn>
    </div>

    <div class="mt-5">
      <v-combobox
        outlined
        dense
        hide-selected
        required
        label="Category"
        :items="categories"
        v-model="category"
        :disabled="getEnableStatusElements()"
      ></v-combobox>
      <v-text-field
        required
        label="Title"
        v-model="title"
        :disabled="getEnableStatusElements()"
      ></v-text-field>
      <v-textarea
        required
        label="Content"
        v-model="content"
        :disabled="getEnableStatusElements()"
      ></v-textarea>
    </div>

    <div class="d-flex justify-end">
      <v-btn
        text
        outlined
        class="mx-md-1 elevation-2"
        :class="{ visibility: getVisibleStatusBtns() }"
        :disabled="getEnableStatusElements()"
        @click="clickCancelBtn"
        >CANCEL
      </v-btn>
      <v-btn
        text
        outlined
        class="mx-md-1 elevation-2"
        :class="{ visibility: getVisibleStatusBtns() }"
        :disabled="getEnableStatusElements()"
        @click="clickOkBtn"
        >OK
      </v-btn>
    </div>
  </div>
</template>

<script>
const MODE_INIT = "INIT";
const MODE_VIEW = "VIEW";
const MODE_DELETE = "DELETE";
const MODE_MODIFY = "MODIFY";
const MODE_REGISTER = "REGISTER";

export default {
  props: ["mode", "notice"],
  data() {
    return {
      id: -1,
      category: [],
      title: "",
      content: "",

      categories: ["Competition", "System Check", "Etc"],
    };
  },
  updated: function () {
    this.$nextTick(function () {
      if (this.id != this.notice.id) {
        this.id = this.notice.id;
        this.refreshElements();

        if (this.mode == MODE_DELETE) {
          this.clickOkBtn();
        }
      }
    });
  },
  methods: {
    getEnableStatusElements: function () {
      return (
        this.mode == MODE_INIT ||
        this.mode == MODE_DELETE ||
        this.mode == MODE_VIEW
      );
    },
    getVisibleStatusBtns: function () {
      return this.mode == MODE_VIEW;
    },
    initializeElements: function () {
      this.category = [];
      this.title = "";
      this.content = "";
    },
    refreshElements: function () {
      this.category = this.notice.category;
      this.title = this.notice.title;
      this.content = this.notice.content;
    },
    doAxiosProcess: function () {
      let responseStatus = 200;
      switch (this.mode) {
        case MODE_REGISTER:
          this.$axios
            .post("/api/notices", {
              // TODO: EmployeeNo는 로그인한 유저의 사번으로 변경
              registerEmployeeNo: "000000",
              updateEmployeeNo: "000000",
              category: this.category,
              title: this.title,
              content: this.content,
            })
            .catch(function (error) {
              responseStatus = error.response.status;
              console.log("[ERR/REG]" + error);
            });
          break;
        case MODE_MODIFY:
          this.$axios
            .put("/api/notices/" + this.id, {
              id: this.id,
              // TODO: EmployeeNo는 로그인한 유저의 사번으로 변경
              updateEmployeeNo: "000000",
              category: this.category,
              title: this.title,
              content: this.content,
            })
            .then((response) => {
              console.log(JSON.stringify(response.data));
            })
            .catch(function (error) {
              console.log("[ERR/MOD]" + error);
              responseStatus = error.response.status;
            });
          break;
        case MODE_DELETE:
          this.$axios
            .delete("/api/notices/" + this.id, {
              id: this.id,
            })
            .then((response) => {
              console.log(JSON.stringify(response.data));
            })
            .catch(function (error) {
              console.log("[ERR/DEL]" + error);
              responseStatus = error.response.status;
            });
          break;
        default:
          // do nothing
          break;
      }
      console.log("responseStatus: " + responseStatus);
      return responseStatus;
    },
    clickOkBtn: function () {
      if (this.doAxiosProcess() == 200) {
        this.initializeElements();
        this.$emit("finishProcess");
      }
    },
    clickCancelBtn: function () {
      this.initializeElements();
    },
  },
};
</script>

<style scoped>
.visibility {
  visibility: hidden;
}
</style>
