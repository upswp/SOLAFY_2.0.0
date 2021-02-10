<template>
  <div style="height: 250px">
    <p id="title">비밀번호 찾기</p>
    <br />
    <p id="subTitle">
      개인정보 확인 후 가입시 입력하신 이메일로 확인 메일을 발송해 드립니다.
    </p>
    <div class="column items-center" style="height: 800px ">
      <div class="col-6">
        <div id="textInput">
          <q-input
            outlined
            label="이름"
            placeholder="Name"
            hint="가입시 입력하신 성함을 입력해 주세요"
          />
          <br />
          <br />
          <q-input
            outlined
            label="SSAFY 기수"
            placeholder="number"
            hint="가입시 입력하신 기수를 입력해주세요. ex) 4기 -> 4"
          />
          <br />
          <br />
          <q-input
            outlined
            label="name@example.com"
            placeholder="email"
            hint="가입시 입력하신 이메일주소를 입력해주세요."
          />
        </div>
      </div>
      <div class="col-3">
        <q-btn id="loginbtn" label="인증하기" />
      </div>
      <div class="col-2">
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
#loginbtn {
  width: 190px;
  height: 35px;
  font-size: 12px;
  color: white;
  align-content: center;
  background-color: #0094ff;
}
#textInput {
  width: 420px;
  height: 50px;
  color: #dfdfdf;
}
</style>
