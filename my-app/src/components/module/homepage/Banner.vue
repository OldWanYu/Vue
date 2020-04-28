<template>
  <div class="hello">
    <div class="banner">
      <img v-for="(v,i) in img" :key="i" :src="v" v-show="i==n" />
      <div class="banner-circle">
        <ul>
          <li v-for="(v,i) in img" :key="i" :class="i==n?'select':''"></li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      n: 0
    };
  },
  props:['img'],
  methods:{
      fun:function () {
          setInterval(this.play,1000)
      },
      play:function(){
          this.n++;
          if (this.n==this.img.length) {
              this.n=0;
          }
      }
  },
  mounted:function(){//生命周期钩子函数
      this.fun()
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
*{
    list-style: none;
    margin: 0;
    padding: 0;
}
.banner-circle{
    width: 100%;
     border: 1px;
}
img{
    width: 100%;
    height: 300px;
}
</style>
