import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/module/Home'
import Order from '@/components/module/Order'
import Find  from '@/components/module/Find'
import My from '@/components/module/My'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      redirect: '/home'
    },
    {
      path: '/home',
      name: 'Home',
      component: Home
    },
    {
      path: '/find',
      name: 'Find',
      component: Find
    },
    {
      path: '/order',
      name: 'Order',
      component: Order
    },
    {
      path: '/my',
      name: 'My',
      component: My
    }
  ]
})
