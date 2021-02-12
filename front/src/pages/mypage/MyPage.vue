<template>
  <div class="q-pa-md">
    <div class="row justify-center">
      <div class="col-2">
        <div class="row justify-end">
          <div class="col q-pa-md" style="max-width: 150px">
            <q-list padding style="text-align: right;">
              <q-item
                dense
                clickable
                v-ripple
                :active="link === 'profile'"
                @click="link = 'profile'"
                class="sidebar-item"
                active-class="menu-link"
              >
                <q-item-section> alice </q-item-section>
              </q-item>

              <q-item
                dense
                clickable
                v-ripple
                :active="link === 'notification'"
                @click="link = 'notification'"
                class="sidebar-item"
                active-class="menu-link"
              >
                <q-item-section> 활동 알림 </q-item-section>
              </q-item>

              <q-item
                dense
                clickable
                v-ripple
                :active="link === 'myarticle'"
                @click="link = 'myarticle'"
                class="sidebar-item"
                active-class="menu-link"
              >
                <q-item-section> 내가 쓴 글 </q-item-section>
              </q-item>

              <q-item
                dense
                clickable
                v-ripple
                :active="link === 'bookmark'"
                @click="link = 'bookmark'"
                class="sidebar-item"
                active-class="menu-link"
              >
                <q-item-section> 즐겨찾기 </q-item-section>
              </q-item>

              <q-item
                dense
                clickable
                v-ripple
                :active="link === 'withdrawal'"
                @click="link = 'withdrawal'"
                class="sidebar-item"
                active-class="menu-link"
              >
                <q-item-section> 탈퇴하기 </q-item-section>
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
        </div>
      </div>
      <div class="col-8" style="max-width: 1140px">
        <router-view></router-view>
      </div>
      <div class="col-2"></div>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      link: "profile"
    };
  },
  watch: {
    link: function() {
      if (this.link == "myarticle") this.$router.push("/mypage/myarticle/free");
      else if (this.link == "bookmark")
        this.$router.push("/mypage/bookmark/problem");
      else this.$router.push("/mypage/" + this.link);
    }
  },
  methods: {}
};
</script>

<style lang="sass" scoped>
.menu-link
  color: white
  background: #6E9488
.sidebar-item
  margin: 25px 0px
  border-radius: 20px
.bottom_logo
  position: absolute
  bottom: 10px
  margin: 0 5px
</style>
