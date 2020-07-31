<template>
  <div class="vue-template">
    <form role="form" @submit.prevent="onSubmit()">
      <h3>FaleMais</h3>

      <div class="form-group">
        <div class="row">
          <div class="col-md-6">
            <label>DDD de origem</label>
            <input type="text" v-model="dddOrigin" class="form-control form-control-lg" />
          </div>
          <div class="col-md-6">
            <label>DDD de destino</label>
            <input type="text" v-model="dddDestiny" class="form-control form-control-lg" />
          </div>
        </div>
      </div>

      <div class="form-group">
        <div class="row">
          <div class="col-md-8">
            <label>Selecione um plano</label>
            <select v-model="plan.id" class="form-control form-control-lg">
              <option v-bind:key="plan.id" v-for="plan in plans" v-bind:value="plan.id">
                {{ plan.name }}
              </option>
            </select>
          </div>
          <div class="col-md-4">
            <label>Duração</label>
            <input type="text" v-model="duration" class="form-control form-control-lg" />
          </div>
        </div>
      </div>

      <button v-if="!showValues" type="submit"
        class="btn btn-dark btn-lg btn-block">Consultar</button>

      <div v-if="showValues" class="card text-center">
        <div class="card-body">
          <h5 class="card-title">Valores</h5>
          <div class="card-text">
            <div class="row">
              <div class="col-md-6">
                Com o plano
                <span>{{ callPrice.priceWithPlan }}</span>
              </div>
              <div class="col-md-6">
                Sem o plano
                <span>{{ callPrice.priceWithoutPlan }}</span>
              </div>
            </div>
          </div>
          <button v-on:click="showValues = false"
            class="btn btn-primary new-search">Nova consulta</button>
        </div>
      </div>

    </form>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'Login',
  data() {
    return {
      plan: {
        id: null,
      },
      callPrice: {
        priceWithPlan: null,
        priceWithoutPlan: null,
      },
      plans: [],
      dddOrigin: null,
      dddDestiny: null,
      duration: null,
      showValues: false,
    };
  },
  created() {
    this.getPlans();
  },
  methods: {
    getPlans() {
      axios.get('http://localhost:8081/plan')
        .then((res) => {
          this.plans = res.data;
        })
        .catch((e) => {
          // eslint-disable-next-line
          console.error(e);
        });
    },
    onSubmit() {
      axios.post('http://localhost:8081/plan/calculate', {
        dddOrigin: this.dddOrigin,
        dddDestiny: this.dddDestiny,
        duration: this.duration,
        plan: this.plan,
      })
        .then((res) => {
          this.callPrice = res.data;
          this.showValues = true;
        })
        .catch((e) => {
          // eslint-disable-next-line
          console.error(e);
        });
    },
  },
};
</script>

<style scoped>
.new-search {
  margin-top: 2rem;
}
</style>
