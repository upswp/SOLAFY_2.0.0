<template>
  <div>
    <div id="subnav">
      <p>Say Hello to</p>
    </div>
    <div class="column items-center">
      <img src="~src/assets/colabo_ssafy.png" />
      <div id="textInput">
        <p>
          여러분의 행복한 학습을 위한 <br />
          <strong>SOLAFY</strong> <br />
          함께 해주셔서 감사합니다.<br />
          <br />
          관리자가 승인 후 가입이 최종적으로 완료됩니다.<br />
          빠른 답변으로 가입하신 이메일로<br />
          가입승인여부를 알려드리겠습니다.<br />
          감사합니다.
        </p>
      </div>
      <div>
        <q-btn id="loginbtn" label="Home" />
      </div>
    </div>
  </div>
</template>
<style>
#subnav {
  height: 120px;
  text-align: center;
  padding: 40px;
  color: #1e3c88;
  font-size: 26px;
  background-color: #f1eff1;
}

#loginbtn {
  width: 190px;
  height: 35px;
  size: "13px";
  color: white;
  align-content: center;
  background-color: #0094ff;
  margin-bottom: 50px;
}
#textInput {
  text-align: center;
  line-height: 50px;
  color: #000000;
}
#point {
  padding-top: 10px;
  padding-bottom: 10px;
}
</style>
