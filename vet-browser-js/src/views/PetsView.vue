<template>
  <div class="about">
    <h1>This is a vet page</h1>
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
  created: async function () {

    const conf = new Configuration({
      basePath: BASEPATH_HOME
    });
    const shelteredApi = new ShelterApi();
    const shelteredAnimals = await shelteredApi.fetchShelteredAnimals();
    shelteredAnimals.forEach((animal) => {
      console.log(animal.name);
    });
  }
});
</script>
