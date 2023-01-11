<!-- NoticeList.vue -->
<template>
  <div class="ma-md-5">
    <div class="d-flex justify-end mt-5">
      <!-- For Notice List Manipulation > View Detail  -->
      <v-btn
        text
        outlined
        class="mx-md-1 elevation-2"
        :disabled="!hasSelectedRow()"
        @click="viewNotice"
        >VIEW DETAIL
      </v-btn>

      <!-- For Notice List Manipulation > Register  -->
      <v-btn text outlined class="mx-md-1 elevation-2" @click="registerNotice"
        >REGISTER
      </v-btn>

      <!-- For Notice List Manipulation > Modify  -->
      <v-btn
        text
        outlined
        class="mx-md-1 elevation-2"
        :disabled="!hasSelectedRow()"
        @click="modifyNotice"
        >MODIFY
      </v-btn>

      <!-- For Notice List Manipulation > Delete  -->
      <v-dialog v-model="dialogDelete" persistent max-width="290">
        <template v-slot:activator="{ on, attrs }">
          <v-btn
            text
            outlined
            class="mx-md-1 elevation-2"
            :disabled="!hasSelectedRow()"
            v-bind="attrs"
            v-on="on"
            >DELETE
          </v-btn>
        </template>
        <v-card>
          <v-card-title class="text-h5">
            Are you sure you want to delete this notice?
          </v-card-title>
          <v-card-text
            >Deleted notices cannot be restored, and are immediately reflected
            on the user portal after deletion.</v-card-text
          >
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="blue darken-1" text @click="dialogDelete = false">
              Cancel
            </v-btn>
            <v-btn color="blue darken-1" text @click="deleteNotice">
              Confirm
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </div>

    <!-- For Notice List -->
    <v-data-table
      single-select
      show-select
      class="mt-md-2 elevation-5"
      v-model="selectedRow"
      :headers="headers"
      :items="data"
      :items-per-page="10"
    >
    </v-data-table>
  </div>
</template>

<script>
const MODE_VIEW = "VIEW";
const MODE_REGISTER = "REGISTER";
const MODE_MODIFY = "MODIFY";
const MODE_DELETE = "DELETE";

export default {
  data() {
    return {
      selectedRow: [],
      dialogDelete: false,

      // For Notice List
      headers: [
        {
          text: "Category",
          value: "category",
        },
        {
          text: "Title",
          value: "title",
        },
        {
          text: "Date",
          value: "date",
        },
        {
          text: "Name",
          value: "name",
        },
      ],
      data: [
        {
          id: "1",
          category: "Competition",
          title: "The 3rd Competition Open!",
          date: "2022/11/01",
          name: "SYSTEM",
        },
        {
          id: "2",
          category: "System Check",
          title: "2022/12/10 System Check",
          date: "2022/12/01",
          name: "SYSTEM",
        },
        {
          id: "3",
          category: "System Check",
          title: "2022/12/15 System Check",
          date: "2022/12/10",
          name: "SYSTEM",
        },
        {
          id: "4",
          category: "System Check",
          title: "2023/01/01 System Check",
          date: "2022/12/21",
          name: "SYSTEM",
        },
        {
          id: "5",
          category: "Competiton",
          title: "The 4th Competition Open!",
          date: "2023/01/01",
          name: "SYSTEM",
        },
        {
          id: "6",
          category: "Etc",
          title: "Test Announcement",
          date: "2023/01/05",
          name: "SYSTEM",
        },
      ],
    };
  },
  methods: {
    hasSelectedRow: function () {
      return this.selectedRow.length > 0;
    },
    changeMode: function (mode, notice) {
      this.$emit("changeMode", mode, notice);
    },
    viewNotice: function () {
      this.changeMode(MODE_VIEW, this.selectedRow);
    },
    registerNotice: function () {
      if (this.hasSelectedRow()) {
        this.selectedRow = [];
      }
      this.changeMode(MODE_REGISTER, this.selectedRow);
    },
    modifyNotice: function () {
      this.changeMode(MODE_MODIFY, this.selectedRow);
    },
    deleteNotice: function () {
      this.dialogDelete = false;

      this.changeMode(MODE_DELETE);
    },
  },
};
</script>

<style>
tr.v-data-table__selected {
  background: lightgoldenrodyellow !important;
}
</style>
