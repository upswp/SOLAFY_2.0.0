<template>
  <q-layout view="lHh Lpr lFf">
    <div class="row justify-center">
      <div class="sidebar bg-grey-5">
        <q-list dense>
          <q-item style="margin-top:50px;margin-bottom:50px">
            <q-item-section>
              <center>
                <q-avatar size="60px">
                  <img src="~assets/logo.png" />
                </q-avatar>
              </center>
            </q-item-section>
          </q-item>
          <q-item
            class="sidebar-item"
            clickable
            :active="link === 'main'"
            @click="clickLink('main')"
            active-class="sidebar-link"
          >
            <q-item-section>Main</q-item-section>
          </q-item>
          <q-item
            class="sidebar-item"
            clickable
            :active="link === 'member'"
            @click="clickLink('member')"
            active-class="sidebar-link"
          >
            <q-item-section>회원관리</q-item-section>
          </q-item>
          <q-item
            class="sidebar-item"
            clickable
            :active="link === 'problem'"
            @click="clickLink('problem')"
            active-class="sidebar-link"
          >
            <q-item-section>문제관리</q-item-section>
          </q-item>
          <q-item
            class="sidebar-item"
            clickable
            :active="link === 'home'"
            @click="clickLink('home')"
            active-class="sidebar-link"
          >
            <q-item-section>Home</q-item-section>
          </q-item>
        </q-list>
        <div class="bottom_logo">
          <img src="~assets/ssafy_logo.png" alt="ssafy_logo" width="75px" />
          <br />
          <img
            src="~assets/im_enabling_people_logo.png"
            alt="enabling_people"
            width="90px"
          />
        </div>
      </div>
      <div class="content">
        <router-view />
      </div>
    </div>
  </q-layout>
</template>
<script>
export default {
  data() {
    return {
      link: "main"
    };
  },
  methods: {
    clickLink(itemName) {
      this.link = itemName;
      if (itemName == "home") {
        this.$router.push("/home");
        return;
      }
      if (itemName == "main") {
        itemName = "";
      }
      this.$router.push("/admin/" + itemName);
    }
  },
  created: function () {
      // 만약 경로로 접근 했을 때, link값을 주소에 맞게 변경해줌
      this.link = this.$route.path.toString().split('/')[2];
      if(this.link==null){
          this.link='main';
      }
  }
};
</script>
<style lang="sass" scoped>
.sidebar
    max-width: 100px
    width: 100px
    height: 100vh
    font-size: 14px
    text-align: center
.sidebar-item
    border-radius: 12px
    min-height: 22px
    margin-top: 30px
    margin-bottom: 30px
.sidebar-link
    color: white
    background: #0094FF
.bottom_logo
    position: absolute
    bottom: 10px
    margin: 0 5px
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

.content:before
    display: inline-block
    width: 1px
    content: ''

.content:after
    display: inline-block
    width: 1px
    content: ''
</style>
