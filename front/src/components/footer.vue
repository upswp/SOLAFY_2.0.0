<template>
  <q-toolbar class="justify-center bg-grey-2">
    <div class="legal">
      <div>
        <a>개인정보 처리방침</a> | <a>웹 사이트 이용 약관</a> |
        <a>판매 및 환불</a> | <a> 법적 고지</a> | <a>사이트 맵</a>
      </div>
    </div>
  </q-toolbar>
</template>
<script></script>
<style lang="sass" scoped>
.legal
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
  font-size: 12px

.legal:before
  display: inline-block
  content: 'Copyright © 2021 SOLAFY 모든 권리 보유.'

.legal:after
  display: inline-block
  content: '대한민국'
</style>
