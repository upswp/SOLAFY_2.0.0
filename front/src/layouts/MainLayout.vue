<template>
  <q-layout view="lHh Lpr lFf">
    <q-header>
      <q-toolbar class="justify-center bg-grey-5">
        <div class="area">
        <EssentialLink
          v-for="link in essentialLinks"
          :key="link.title"
          v-bind="link"
        />
        </div>
      </q-toolbar>
    </q-header>

    <q-page-container>
      <router-view />
    </q-page-container>
  </q-layout>
</template>

<script>
import EssentialLink from "components/EssentialLink.vue";

const linksData = [
  {
    img: require("src/assets/logo.png"),
    link: "/",
  },
  {
    title: "Problem",
    link: "/FindPassword",
  },
  {
    title: "ProblemSet",
    link: "/Main",
  },
  {
    title: "MockTest",
    link: "",
  },
  {
    title: "something1",
    link: "",
  },
  {
    title: "something2",
    link: "",
  },
  {
    title: "something3",
    link: "",
  },
  {
    icon: "search",
    link: "",
  },
  {
    icon: "create",
    link: "",
    popup:true,
  },
];

export default {
  name: "MainLayout",
  components: { EssentialLink },
  data() {
    return {
      essentialLinks: linksData,
    };
  },
};
</script>
<style lang="sass" scoped>
.area 
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

.area:before 
  display: inline-block
  width: 1px
  content: ''

.area:after 
  display: inline-block
  width: 1px
  content: ''
</style>