<template>
  <q-btn id="btn" :label="label" @click="$emit('click')" />
</template>
<script>
export default {
  name: "UserButton",
  props: {
    active: {
      type: Boolean,
      default: false
    },
    label: {
      type: String,
      default: ""
    }
  }
};
</script>
<style lang="sass" scoped>
#btn
  width: 190px
  height: 35px
  font-size: 12px
  color: #FFFFFF
  align-content: center
  background-color: #0094ff
</style>
