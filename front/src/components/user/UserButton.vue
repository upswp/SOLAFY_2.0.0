<template>
  <q-btn id="btn" :label="label" @click="$emit('click')" />
</template>
<script>
export default {
  name: "UserButton",
  props: {
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
  font-size: 14px
  color: #FFFFFF
  align-content: center
  background-color: #0094ff
</style>
