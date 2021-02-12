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
          <q-tab name="problem" label="문제" />
          <q-tab name="problemset" label="문제집" />
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
      tab: "problem"
    };
  },
  watch: {
    tab: function() {
      this.$router.push("/mypage/bookmark/" + this.tab);
    }
  },
  methods: {}
};
</script>
