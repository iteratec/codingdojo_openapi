<template>
  <div class="about">
    <h1>Sheltered animals</h1>

    <ul>
        <li v-for="item in items">{{ item.name }}</li>
    </ul>
  </div>
</template>

<style>
@media (min-width: 1024px) {
  .about {
    min-height: 100vh;
    display: flex;
    align-items: center;
  }
}
</style>

<script lang="ts">
import {defineComponent} from "vue";
import {Configuration} from "../generated-sources-openapi/vet-home/runtime";
import {ShelterApi} from "../generated-sources-openapi/vet-home/apis/ShelterApi";
import {BASEPATH_HOME} from "@/envconst";

export default defineComponent({
    data() {
      return {
        items: []
      }
    },
  created: async function () {

    const conf = new Configuration({
      basePath: BASEPATH_HOME
    });
    const shelteredApi = new ShelterApi(conf);
    this.items = await shelteredApi.fetchShelteredAnimals();
    this.items.forEach((animal) => {
      console.log(animal.name);
    });
  }
});
</script>
