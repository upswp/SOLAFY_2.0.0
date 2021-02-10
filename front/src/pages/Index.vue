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
        <q-btn id="loginbtn" label="로그인" />
        <br />
        <q-btn id="joinbtn" flat color="primary" label="회원가입" />
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

export default {
  components: { Footer }
};
</script>
<style>
#loginbtn {
  width: 190px;
  height: 35px;
  size: "13px";
  color: white;
  align-content: center;
  background-color: #0094ff;
}
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
