<script setup lang="ts">
import {RouterLink, RouterView} from "vue-router";
import OpenApiWelcome from "./components/OpenApiWelcome.vue";</script>

<template >
  <header>
    <img
        alt="Vue logo"
        class="logo"
        src="https://www.iteratec.com/fileadmin/html/img/logo.png"
        width="125"
    />

    <div class="wrapper">
      <OpenApiWelcome msg="Coding Dojo"/>

      <nav>
        <RouterLink to="/">Home</RouterLink>
        <RouterLink to="/about">About</RouterLink>
      </nav>
    </div>
  </header>

  <RouterView/>
</template>

<style scoped>
header {
  line-height: 1.5;
  max-height: 100vh;
}

.logo {
  display: block;
  margin: 0 auto 2rem;
}

nav {
  width: 100%;
  font-size: 12px;
  text-align: center;
  margin-top: 2rem;
}

nav a.router-link-exact-active {
  color: var(--color-text);
}

nav a.router-link-exact-active:hover {
  background-color: transparent;
}

nav a {
  display: inline-block;
  padding: 0 1rem;
  border-left: 1px solid var(--color-border);
}

nav a:first-of-type {
  border: 0;
}

@media (min-width: 1024px) {
  header {
    display: flex;
    place-items: center;
    padding-right: calc(var(--section-gap) / 2);
  }

  .logo {
    margin: 0 2rem 0 0;
  }

  header .wrapper {
    display: flex;
    place-items: flex-start;
    flex-wrap: wrap;
  }

  nav {
    text-align: left;
    margin-left: -1rem;
    font-size: 1rem;

    padding: 1rem 0;
    margin-top: 1rem;
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
