<template>
  <div id="app" style="margin:0;">
    <router-view />

    <Tabbar />
  </div>
</template>

<script>
import Tabbar from "./components/Tabber";
export default {
  name: "App",
  components: {
    Tabbar
  }
};
</script>

<style>
body{
  margin: 0;
}
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  /* margin-top: 60px; */
  margin: 0;
}
</style>
