<script>
import { RouterView } from 'vue-router';
export default {
  methods: {
        async cerrarSesion() {
          localStorage.removeItem("token")
          localStorage.removeItem("user")
          this.$router.push('/')
        }
  }
}
</script>



<template>
<div class="container">
  <!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <!-- Container wrapper -->
  <div class="container-fluid">
    <!-- Toggle button -->
    <button
      class="navbar-toggler"
      type="button"
      data-mdb-toggle="collapse"
      data-mdb-target="#navbarSupportedContent"
      aria-controls="navbarSupportedContent"
      aria-expanded="false"
      aria-label="Toggle navigation"
    >
      <i class="fas fa-bars"></i>
    </button>

    <!-- Collapsible wrapper -->
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <!-- Navbar brand -->
      <div class="navbar-brand">NETESA S.A.</div>
      <!-- Left links -->
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
           <RouterLink class="nav-link active" to="/home">Home</RouterLink>
        </li>
        <li class="nav-item">
          <RouterLink class="nav-link active" to="/citas">Citas</RouterLink>
        </li>
        <li class="nav-item">
          <RouterLink class="nav-link active" to="/usuarios">Usuarios</RouterLink>
        </li>
      </ul>
      <!-- Left links -->
    </div>
    <!-- Collapsible wrapper -->

    
    <!-- Right elements -->
    <div class="d-flex align-items-center">
      <input class="form-control me-2" type="search" placeholder="Buscar" aria-label="Search" />
      <div>&nbsp;</div>
      <div>&nbsp;</div>
      <div>&nbsp;</div>
      <!-- Avatar -->
      <div class="dropdown">
        <a
          class="dropdown-toggle d-flex align-items-center hidden-arrow"
          href="#"
          id="navbarDropdownMenuAvatar"
          role="button"
          data-mdb-toggle="dropdown"
          aria-expanded="false"
        >
          <img
            src="https://mdbcdn.b-cdn.net/img/new/avatars/2.webp"
            class="rounded-circle"
            height="25"
            alt="Black and White Portrait of a Man"
            loading="lazy"
          />
        </a>
        <ul
          class="dropdown-menu dropdown-menu-end"
          aria-labelledby="navbarDropdownMenuAvatar"
        >
          <li>
            <div class="dropdown-item" @click="cerrarSesion">Logout</div>
          </li>
        </ul>
      </div>
    </div>
    <!-- Right elements -->
  </div>
  <!-- Container wrapper -->
</nav>
<!-- Navbar -->
</div>
</template>