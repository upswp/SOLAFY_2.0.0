<template>
  <div class="q-pa-md">
    <div class="row justify-center" style="margin: 20px;">
      <div class="col-12">
        <q-tabs
          v-model="tab"
          class="text-grey"
          active-color="primary"
          indicator-color="primary"
          align="justify"
          narrow-indicator
        >
          <q-tab name="free" label="자유 게시판" />
          <q-tab name="question" label="질문 게시판" />
          <q-tab name="problem" label="출제 문제" />
          <q-tab name="problemset" label="출제 문제집" />
        </q-tabs>

        <q-separator />

        <router-view></router-view>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      tab: "free"
    };
  },
  watch: {
    tab: function() {
      this.$router.push("/mypage/myarticle/" + this.tab);
    }
  },
  methods: {}
};
</script>
