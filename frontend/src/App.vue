<template>
  <div id="app" class="intro">
    <navbar></navbar>
    <div class="container">
      <router-view></router-view>
    </div>
  </div>
</template>

<script>

import { routes } from "./routes";
import Navbar from "./components/shared/navbar/Navbar.vue";

import Home from "./components/home/Home.vue";
export default {
  name: "app",
  components: {
    navbar: Navbar,
    home: Home,
  },

};
</script>

<style scoped>


</style>
