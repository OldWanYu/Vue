<template>
  <div class="hello">
    <Banner :img="img" />
    <Category />
    <!-- <div>
      <ul>
        <li></li>
      </ul>
    </div> -->
  </div>
</template>

<script>
import Banner from "./homepage/Banner";
import Category from "./homepage/Category";
import axios from 'axios'

export default {
  data() {
    return {
      img: [
        require("@/assets/images/1.jpg"),
        require("@/assets/images/2.jpg"),
        require("@/assets/images/3.jpg"),
        require("@/assets/images/4.jpg")
      ]
    };
  },
  components: {
    Banner,
    Category
  },
  methods:{
    getData:function(){
      axios.get('http:127.0.0.1:3333/home/page/1/10')
      .then(res=>{
        console.log(res)
      })
    }
  },
  mounted:function(){
    this.getData();
  }
};
</script>

<style>
body {
  margin: 0;
}
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
