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

export default defineComponent({
  created: async function () {
    /*
    const test = new PetApi();
    const petsByTag = await test.findPetsByTags({tags: ["dogs"]});
    petsByTag.forEach((pet) => {

    });

     */
  }
});
</script>
