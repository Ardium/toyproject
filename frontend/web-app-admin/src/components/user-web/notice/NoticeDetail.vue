<template>
  <div class="ma-md-5">
    <div class="text-h5 md-5">DETAIL</div>

    <v-divider></v-divider>

    <div class="d-flex justify-end mt-5">
      <v-btn text outlined class="mx-md-1 elevation-2">Change</v-btn>
    </div>

    <div class="mt-5">
      <v-combobox
        outlined
        dense
        hide-selected
        required
        label="Category"
        :items="categories"
      ></v-combobox>
      <v-text-field required label="Title" v-model="title"></v-text-field>
      <v-textarea required label="Content" v-model="content"></v-textarea>
    </div>

    <div class="d-flex justify-end">
      <v-btn text outlined class="mx-md-1 elevation-2">CANCEL</v-btn>
      <v-btn text outlined class="mx-md-1 elevation-2">OK</v-btn>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      categories: ["Competition", "System Check", "Others"],
    };
  },
};
</script>

<style></style>
