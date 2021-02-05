<template>
  <div class="q-pa-md" style="height: 250px">
    <p id="title">비밀번호 찾기</p>
    <br />
    <p id="subTitle">
      개인정보 확인 후 가입시 입력하신 이메일로 확인 메일을 발송해 드립니다.
    </p>
    <div class="column items-center">
      <div id="textbox">
        <p id="subTextTitle">
          개인정보 또는 이메일 주소가 없는 정보입니다.
        </p>
        <p id="subText">
          5회 개인정보 또는 이메일 인증 실패시<br />
          관리자에게 문의 부탁드립니다. <br />(1/5)<br />
          <q-btn id="loginbtn" label="돌아가기" />
        </p>
      </div>
    </div>
  </div>
</template>
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
#subTextTitle {
  padding-top: 30px;
  text-align: center;
  font-size: 20px;
}
#subText {
  padding-top: 30px;
  text-align: center;
  font-size: 13px;
  padding-bottom: 60px;
  line-height: 40px;
  color: #b5b5b5;
}
#loginbtn {
  width: 190px;
  height: 35px;
  size: "13px";
  color: white;
  align-content: center;
  background-color: #a5cbe7;
}

#textbox {
  width: 720px;
  height: 300px;
  background-color: #dfdfdf;
}
</style>
