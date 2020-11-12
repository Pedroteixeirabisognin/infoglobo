import Vue from 'vue'
import App from './App.vue'

import VueResource from 'vue-resource';
import VueRouter from 'vue-router';
import { routes } from './routes';

import 'bootstrap'
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.js';
import './css/main.css'
import msg from './pt_BR';
import VeeValidate from 'vee-validate';


Vue.use(VeeValidate, {
  locale: 'pt_BR',
  dictionary: {
    pt_BR: {
      messages: msg
    }
  }
});
Vue.use(VueResource);
Vue.use(VueRouter);
Vue.http.options.root = 'http://localhost:8080';
Vue.http.headers.common['Authorization'] = 'Basic aW5mb2dsb2JvOjEyMzQ1';  
Vue.http.headers.common['Cache-control'] = 'max-age = 60'; 
const router = new VueRouter({
  routes,
  mode: 'history'
});
Vue.filter('formatConteudo', function (value) {
  if (!value) {
    return "";
  }

  if (value.length >= 100 + 3) {
    return value.substring(0, 100).concat("...");
  }
  return value;
})

new Vue({
  el: '#app',
  router,
  render: h => h(App)
})
