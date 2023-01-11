<template>
  <v-container>
    <div class="text-h4 font-weight-medium ma-2">NOTICE</div>

    <v-divider></v-divider>

    <v-row>
      <v-col cols="8">
        <NoticeList v-on:changeMode="changeMode"></NoticeList>
      </v-col>
      <v-col cols="4">
        <NoticeDetail :mode="mode" :notice="notice"></NoticeDetail>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import NoticeList from "./../../../components/user-web/notice/NoticeList.vue";
import NoticeDetail from "./../../../components/user-web/notice/NoticeDetail.vue";

const MODE_INIT = "INIT";
const MODE_DELETE = "DELETE";

export default {
  data() {
    return {
      mode: MODE_INIT,
      notice: [],
    };
  },
  methods: {
    changeMode: function (mode, notice) {
      this.mode = mode;
      if (mode !== MODE_DELETE && mode !== MODE_INIT) {
        if (notice[0] !== undefined) {
          this.notice = notice[0];
        } else {
          this.notice = [];
        }
      } else {
        // TODO: DELETE SQL 연결
      }
    },
  },
  components: {
    NoticeList,
    NoticeDetail,
  },
};
</script>

<style></style>
