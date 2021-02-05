<template>
  <q-item clickable tag="a" class="item" :to="link">
    <q-item-section v-if="img">
      <q-avatar size="30px">
        <img :src="img" />
      </q-avatar>
    </q-item-section>

    <q-item-section v-if="icon">
      <q-icon :name="icon" style="font-size: 24px;" />
    </q-item-section>

    <q-item-section v-if="title">
      <q-item-label style="font-size:14px">{{ title }}</q-item-label>
    </q-item-section>

    <q-popup-proxy v-if="popup" transition-show="jump-down" transition-hide="jump-up" anchor="bottom middle" self="top middle" :offset="[0, 10]">
      <q-banner class="bg-grey-5">
        <q-list style="min-width: 100px">
          <q-item>
            <q-item-section>로그인 상태가 아닙니다</q-item-section>
          </q-item>
          <q-separator />
          <q-item clickable>
            <q-item-section avatar>
              <q-avatar icon="star_border" />
            </q-item-section>
            <q-item-section>즐겨찾기</q-item-section>
          </q-item>
          <q-separator />
          <q-item clickable>
            <q-item-section avatar>
              <q-avatar icon="settings" />
            </q-item-section>
            <q-item-section>계정</q-item-section>
          </q-item>
          <q-separator />
          <q-item clickable>
            <q-item-section avatar>
              <q-avatar icon="account_circle" />
            </q-item-section>
            <q-item-section>로그인</q-item-section>
          </q-item>
        </q-list>
      </q-banner>
    </q-popup-proxy>
  </q-item>
</template>

<script>
export default {
  name: "EssentialLink",
  props: {
    title: {
      type: String,
      default: ""
    },

    link: {
      type: String,
      default: "#"
    },

    icon: {
      type: String,
      default: ""
    },

    img: {
      type: String,
      default: ""
    },

    popup: {
      type: Boolean,
      default: false
    }
  }
};
</script>
<style lang="sass" scoped>
.item
  color: black
</style>
