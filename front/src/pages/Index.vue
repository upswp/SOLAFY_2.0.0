<template>
  <div style="height: 250px">
    <!-- logo -->
    <div class="fit row justify-center content-center">
      <img style="width : 80px; height : 80px;" src="~src/assets/logo.png" />
    </div>
    <div class="column items-center" style="height: 700px">
      <div class="col-4">
        <q-input
          filled
          label="SOLAFY ID"
          style="width : 450px; height : 52px;"
        />
        <br />
        <br />
        <q-input
          filled
          label="PASSWORD"
          style="width : 450px; height : 52px;"
        />
        <!-- padding 값을 주어 변경 -->
        <div id="subform">
          <q-checkbox toggle-order="ft" label="자동로그인" color="primary" />
          <q-btn flat color="black" label="비밀번호 찾기" />
        </div>
      </div>
      <div class="col-4">
        <UserButton @click="link" label="로그인"></UserButton>
        <br />
        <q-btn
          id="joinbtn"
          @click="goSignup"
          flat
          color="primary"
          label="회원가입"
        />
      </div>
      <div class="col">
        <div class="column items-center">
          <img
            style="width : 77px; height : 55px;"
            src="~src/assets/ssafy_logo.png"
          />
          <br />
          <img
            style="width : 103px; height : 25px;"
            src="~src/assets/im_enabling_people_logo.png"
          />
        </div>
      </div>
    </div>
    <Footer></Footer>
  </div>
</template>
<script>
import Footer from "components/footer.vue";
import UserButton from "components/user/UserButton.vue";

export default {
  components: { Footer, UserButton },
  data() {
    return {};
  },
  methods: {
    link() {
      this.$router.push({
        name: "Home"
      });
    },
    goSignup() {
      this.$router.push({
        name: "Signup"
      });
    }
  }
};
</script>
<style>
#joinbtn {
  width: 190px;
  height: 35px;
  size: "10px";
  color: black;
  align-content: center;
  background-color: white;
}
#subform {
  padding-left: 235px;
}
</style>
