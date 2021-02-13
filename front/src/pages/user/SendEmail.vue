<template>
  <div class="q-pa-md" style="height: 250px">
    <p id="title">메일 전송 완료</p>
    <br />
    <p id="subTitle">
      개인정보 확인 후 가입시 입력하신 이메일로 확인 메일을 발송해 드립니다.
    </p>
    <div class="column items-center">
      <div class="col-4">
        <UserButton @click="goHome" label="Home"></UserButton>
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
    goHome() {
      this.$router.push({
        name: "Home"
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
