import Vue from 'vue';
import axios from 'axios';
import App from './App.vue';
import './registerServiceWorker';

import 'bootstrap/dist/css/bootstrap.min.css';
import '@/assets/css/main.css';

axios.defaults.headers.post['Content-Type'] = 'application/json';
Vue.config.productionTip = false;

new Vue({
  render: (h) => h(App),
}).$mount('#app');
