import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../views/HomeView.vue";
import CitasView from "../components/Citas.vue";
import Usuarios from "../components/Usuarios.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "login",
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import("../views/LoginView.vue"),
    },
    {
      path: "/home",
      name: "home",
      component: HomeView,
      children: [
        {
          path: "/citas",
          name: "citas",
          component: CitasView,
        },
        {
          path: "/usuarios",
          name: "usuarios",
          component: Usuarios,
        },
      ],
    },
  ],
});

function existToken() {
  // console.log(!!localStorage.token);
  return !!localStorage.token;
}

router.beforeEach((to, from, next) => {
  if (to.path != "/" && existToken()) {
    next();
  } else if (to.path == "/" && !existToken()) {
    next();
  } else if (to.path != "/" && !existToken()) {
    next("/");
  } else {
    next("/home");
  }
});

export default router;
