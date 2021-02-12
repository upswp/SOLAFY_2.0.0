<template>
  <div class="q-pa-md" style="height: 250px">
    <p id="title">인증 완료</p>
    <br />
    <p id="subTitle">
      변경된 비밀번호로 재로그인 부탁드립니다.
    </p>
    <div class="column items-center" style="height: 700px ">
      <div class="col-4">
        <UserButton @click="goIndex" label="로그인"></UserButton>
      </div>
    </div>
  </div>
</template>
<script>
import UserButton from "components/user/UserButton.vue";

export default {
  components: { UserButton },
  data() {
    return {};
  },
  methods: {
    goIndex() {
      this.$router.push({
        name: "Index"
      });
    }
  }
};
</script>
<style>
#title {
  font-style: inherit;
  padding-top: 100px;
  text-align: center;
  font-size: 20px;
}

#subTitle {
  text-align: center;
  font-size: 12px;
  padding-bottom: 60px;
}
</style>
