<template>
  <tr>
    <td class="title">
      <ul>
        <li>{{ title }}</li>
      </ul>
    </td>
    <td class="content" v-if="content">{{ content }}</td>
    <td class="content" v-if="img"><img :src="require('src/assets/user/'+img)" width="200px"/></td>
  </tr>
</template>
<script>
export default {
  name: "DialogContent",
  props: {
    title: {
      type: String,
      default: ""
    },
    content: {
      type: String,
      default: ""
    },
    img: {
      type: String,
      default: ""
    }
  }
};
</script>
<style lang="sass" scoped>
.title
    width: 150px
    padding: 0
.content
    width: 200px  
</style>