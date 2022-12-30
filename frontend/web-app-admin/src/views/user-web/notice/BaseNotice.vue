<template>
  <v-container>
    <div class="text-h4 font-weight-medium ma-2">NOTICE</div>

    <v-divider></v-divider>

    <v-row>
      <v-col cols="8">
        <NoticeList></NoticeList>
      </v-col>
      <v-col cols="4">
        <NoticeDetail></NoticeDetail>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import NoticeList from "./../../../components/user-web/notice/NoticeList.vue";
import NoticeDetail from "./../../../components/user-web/notice/NoticeDetail.vue";

export default {
  components: {
    NoticeList,
    NoticeDetail,
  },
};
</script>

<style></style>
