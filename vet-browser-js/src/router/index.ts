import { createRouter, createWebHistory } from "vue-router";
import PetsView from "../views/PetsView.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "home",
      component: PetsView,
    },
    {
      path: "/required-vaccinations",
      name: "required-vaccinations",
      component: () => import("../views/RequiredVaccinationsView.vue"),
    },
  ],
});

export default router;
