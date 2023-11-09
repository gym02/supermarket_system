import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import axios from 'axios';
import md5 from 'js-md5';
// npm install js-md5
//引入echarts对象
import * as echarts from 'echarts'

Vue.use(ElementUI);
Vue.config.productionTip = false

//挂在在Vue全局对象下
Vue.prototype.$echarts=echarts

Vue.prototype.$md5 = md5;

//给axios取别名，建议加上$
Vue.prototype.$axios=axios

axios.interceptors.request.use(function (config){
    let token=window.localStorage.getItem("token");
    let refresh=window.localStorage.getItem("refresh");
    if(token){
      config.headers.token=token;
    }
    if(refresh){
      config.headers.refresh=refresh;
    }
    return config;
  },function (error) {// 对请求错误做些什么
  // return Promise.reject(error);
  })
  axios.interceptors.response.use(function (response){
    let token=response.headers.token;
    let refresh=response.headers.refresh;
    console.log(token)
    if(token){
      window.localStorage.setItem("token",token);
    }
    if(refresh){
      window.localStorage.setItem("refresh",refresh);
    }
    if(response.data.code==403){
      ElementUI.Message(response.data.data)
    }
    
    return response;
  },
  function (error) {// 对请求错误做些什么
    // return Promise.reject(error);
    })
new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
