<script setup>
import NavbarView from './NavbarView.vue';
import CitasView from './CitasView.vue';
import { RouterView } from 'vue-router';

</script>

<template>
  <NavbarView></NavbarView>
  <main>
    <RouterView></RouterView>
  </main>
</template>