<template>
  <div class="vue-template">
    <div class="App">
      <div class="vertical-center">
        <div class="inner-block">
          <Home />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Home from './components/Home.vue';

export default {
  name: 'App',
  components: {
    Home,
  },
};
</script>
