<script setup>
import NavbarView from './../components/Navbar.vue';
import { RouterView } from 'vue-router';

</script>

<template>
  <NavbarView></NavbarView>


  <main>
    <RouterView></RouterView>
  </main>
</template>

<style>
.titulo-grade {
  font-size: 1000px;
}
</style>