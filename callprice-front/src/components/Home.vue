<template>
  <div class="vue-template">
    <form>
      <h2>FaleMais</h2>

      <div class="form-group">
        <div class="row">
          <div class="col-md-4">
            <label>DDD de origem</label>
            <input type="text" v-model="dddOrigin" class="form-control form-control-lg" />
          </div>
          <div class="col-md-4">
            <label>DDD de destino</label>
            <input type="text" v-model="dddDestiny" class="form-control form-control-lg" />
          </div>
          <div class="col-md-4">
            <label>Duração</label>
            <input type="text" v-model="duration" class="form-control form-control-lg" />
          </div>
        </div>
      </div>

      <div class="form-group">
        <div class="row">
          <div class="col-md-12">
            <label>Selecione um plano</label>
            <select v-on:change="onSubmit()" v-model="plan.id" class="form-control form-control-lg">
              <option v-bind:key="plan.id" v-for="plan in plans" v-bind:value="plan.id">
                {{ plan.name }}
              </option>
            </select>
          </div>
        </div>
      </div>

      <div v-if="showAlert">
        <span class="span-alert">Atenção, preencha todos os campos!</span>
      </div>

      <div v-if="showValues" class="card text-center">
        <div class="card-body">
          <h4 class="card-title">Valores</h4>
          <div class="card-text">
            <div class="row">
              <div class="col-md-6">
                Com o plano
                <div class="card card-fm">
                  <div class="card-body">
                    <span class="span-color-fm span-size-fm">
                      R$: {{ callPrice.priceWithPlan.toFixed(2) }}
                    </span>
                  </div>
                </div>
              </div>
              <div class="col-md-6">
                Sem o plano
                <div class="card">
                  <div class="card-body">
                    <span class="span-size-fm">
                      R$: {{ callPrice.priceWithoutPlan.toFixed(2) }}
                    </span>
                  </div>
                </div>
              </div>
            </div>
          </div>
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
      showAlert: false,
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
      if (this.dddOrigin == null || this.dddDestiny == null || this.duration == null) {
        this.showAlert = true;
      } else {
        this.showAlert = false;
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
      }
    },
  },
};
</script>

<style scoped>
.new-search {
  margin-top: 2rem;
}
</style>
