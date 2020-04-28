<template>
  <div class="hello">
    <router-link to="/home" class="style active">
      <i class="fa fa-home"></i>
      <label>首页</label>
    </router-link>
     <router-link to="/find" class="style">
      <i class="fa fa-superpowers"></i>
      <label>发现</label>
    </router-link>
     <router-link to="/order" class="style">
      <i class="fa fa-th-list"></i>
      <label>订单</label>
    </router-link>
     <router-link to="/my" class="style">
        <i class="fa fa-user"></i>
      <label>我的</label>
    </router-link>
  </div>
</template>

<script>
export default {
  data() {
    return {
      name: "Tabbar"
    };
  },
  methods: {},
  mounted: function() {
    //生命周期钩子函数
  }
};
</script>
<style scoped>
*{
    list-style: none;
    text-decoration: none;
    padding: 0;
    margin: 0;
}
.hello{
    display: flex;
    justify-content: space-around;
    width: 100%;
    height: 60px;
    background-color: #eee;
    position: fixed;
    bottom: 0;
}
.style{
    color: black;
    line-height: 30px;
    padding-top: 10px;
}
.active{
    color:red;
}
i{
    display: block;
    font-size: 20px;
}
</style>
