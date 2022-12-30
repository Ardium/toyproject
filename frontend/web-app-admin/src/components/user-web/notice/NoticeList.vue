<template>
  <div class="ma-md-5">
    <div class="text-h5 md-5">LIST</div>

    <v-divider></v-divider>

    <div class="d-flex justify-end mt-5">
      <!-- For Notice List Manipulation > View Detail -->
      <v-btn text outlined class="mx-md-1 elevation-2">VIEW DETAIL</v-btn>

      <!-- For Notice List Manipulation > Register  -->
      <v-btn text outlined class="mx-md-1 elevation-2">REGISTER</v-btn>

      <!-- For Notice List Manipulation > Modify  -->
      <v-btn text outlined class="mx-md-1 elevation-2">MODIFY</v-btn>

      <!-- For Notice List Manipulation > Delete  -->
      <v-btn text outlined class="mx-md-1 elevation-2">DELETE</v-btn>
    </div>

    <!-- For Notice List -->
    <v-data-table
      class="mt-md-2 elevation-5"
      :headers="headers"
      :items="data"
      :items-per-page="10"
    ></v-data-table>
  </div>
</template>

<script>
export default {
  data() {
    return {
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
      ],
      data: [
        {
          id: "1",
          category: "공모전",
          title: "제 3회 공모전 개최 공지",
          date: "2022/11/01",
        },
        {
          id: "2",
          category: "시스템 점검",
          title: "2022/12/10 시스템 점검 공지",
          date: "2022/12/01",
        },
        {
          id: "3",
          category: "시스템 점검",
          title: "2022/12/15 시스템 점검 공지",
          date: "2022/12/10",
        },
        {
          id: "4",
          category: "시스템 점검",
          title: "2023/01/01 시스템 점검 공지",
          date: "2022/12/21",
        },
        {
          id: "5",
          category: "공모전",
          title: "제 4회 공모전 개최 공지",
          date: "2023/01/01",
        },
        {
          id: "6",
          category: "시스템 점검",
          title: "2023/01/10 시스템 점검 공지",
          date: "2023/01/05",
        },
      ],
    };
  },
};
</script>

<style></style>
