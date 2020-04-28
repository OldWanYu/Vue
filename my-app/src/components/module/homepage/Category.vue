<template>
  <div class="hello">
    <div v-for="(v,i) in lists" :key="i" class="cate">
      <i :class="v.icon" :style="{background:v.color}"></i>
      <label>{{v.title}}</label>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      lists: [
        { title: "美食", icon: "fa fa-snowflake-o", color: "skyblue" },
        { title: "美食", icon: "fa fa-snowflake-o", color: "skyblue" },
        { title: "美食", icon: "fa fa-snowflake-o", color: "skyblue" },
        { title: "美食", icon: "fa fa-snowflake-o", color: "skyblue" },
        { title: "美食", icon: "fa fa-snowflake-o", color: "skyblue" },
        { title: "美食", icon: "fa fa-snowflake-o", color: "skyblue" },
        { title: "美食", icon: "fa fa-snowflake-o", color: "skyblue" },
        { title: "美食", icon: "fa fa-snowflake-o", color: "skyblue" }
      ]
    };
  },
  methods: {}
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
* {
  list-style: none;
  margin: 0;
  padding: 0;
}
.hello{
    display: flex;
    justify-content: space-around;
    flex-wrap: wrap;
}
.cate{
    width: 100px;
    height: 100px;
}
i{
    display: block;
    width:40px;
    font-size: 35px;
    padding:10px;
    color: bisque;
    border-radius:40px; 
    margin: 0 auto;
}
</style>
