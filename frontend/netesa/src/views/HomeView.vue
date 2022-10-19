<script setup>
import NavbarView from './../components/Navbar.vue';
import { RouterView } from 'vue-router';

</script>

<template>
  <NavbarView></NavbarView>
  <!-- <div class="m-0 vh-100 row justify-content-center align-items-center">
    <div class="col-auto p5 text-center titulo-grande">
      <strong>BIENVENIDOS  A TU PANEL ADMINISTRADOR</strong>
    </div>    
  </div> -->
  <main>
    <RouterView></RouterView>
  </main>
</template>

<style>
.titulo-grade{
  font-size:  1000px;
}
</style>