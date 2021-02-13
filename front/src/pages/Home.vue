<template>
  <div class="body row justify-center">
    <div class="content column">
      <div class="section1">
        <center>
          <img class="logo" src="~assets/solafy_logo.png" />
          <p>여러분의 행복한 학습을 위한</p>
          <p><strong>solafy</strong></p>
          <p>함께 해주셔서 감사합니다</p>
        </center>
      </div>
      <div class="section2">
        <q-parallax src="~assets/home/section2.png" style="height:100%">
          <center class="content2">
            <p>더 이상 학습을 혼자하지 않겠다</p>
            <p>여러분의 SOLAFY</p>
            <q-btn class="btn" label="바로가기" style="margin-top:45px" />
          </center>
        </q-parallax>
      </div>
      <div class="section3">
        <center>
          <img
            src="~assets/home/section3.jpg"
            style="width:660px; margin-top:100px; margin-bottom:45px"
          />
          <p>나의 아이디어를 문제로!</p>
          <p>문제내기는 SOLAFY!</p>
          <q-btn
            class="btn"
            label="바로가기"
            style="margin-top:30px;color:white"
          />
        </center>
      </div>
      <div class="section4">
          그룹홍보 (추후구현)
      </div>
      <div class="section5">
          문제&그룹 순위 (추후구현)
      </div>
    </div>
  </div>
</template>
<script>
export default {};
</script>
<style lang="sass" scoped>
.body
    width:100%
.content
    margin: 0
    padding: 0
    display: -webkit-box
    display: -moz-box
    display: -ms-flexbox
    display: -moz-flex
    display: -webkit-flex
    display: flex
    justify-content: space-between
    list-style: none
    width: 1140px
.section1
    width: 100%
    height: 700px
    padding-top: 150px
.logo
    width: 200px
    border-radius: 50px
    margin-bottom: 45px
p
    font-size: 18px
.section2
    margin: 0
    width: 100vw
    margin-left: calc(-50vw + 50%)
    height: 700px
.content2
    color: white
    width: 940px
    height: 380px
    background-image: url(~assets/home/blackbox.png)
    padding-top: 100px
.section3
    width: 100%
    height: 700px
.btn
    width:250px
    height: 40px
    background-color: #0094FF
    font-size: 14px
.section4
    width: 100%
    height: 700px
.section5 
    width: 100%
    height: 700px 
</style>
