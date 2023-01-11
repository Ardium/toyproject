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
      <!-- mode: {{ mode }} object: {{ notice }} -->
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
    clickOkBtn: function () {
      console.log("clickOkBtn");
      this.initializeElements();
    },
    clickCancelBtn: function () {
      console.log("clickCancelBtn");
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
