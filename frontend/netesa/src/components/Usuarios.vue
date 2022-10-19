<template>
  <div class="container-x px-4">
    <div class="contenedor1">
      <div class="row pb-1 mt-4 d-flex justify-content-between">
        <div class="col-sm-10">
          <h2 style="text-align: center">Gestion de Usuarios</h2>
        </div>
        <div class="col-sm-2">
          <input type="date" class="form-control" value="date" id="fecha" />
        </div>
      </div>
      <div class="row pb-2 d-flex justify-content-end">
        <div class="col-sm-3">
          <input
            type="text"
            placeholder="Search..."
            id="buscador"
            class="form-control"
          />
        </div>
      </div>
      <div
        class="card col-md-5"
        style="float: left; margin: 12px; min-height: 250px"
      >
        <div class="card-body col-md-12">
          <h5 class="card-title" style="margin-bottom: 12px">
            Netesa Administracion de Usuarios
          </h5>
          <div class="card-text col-md-12">
            <div class="input-group mb-3">
              <span class="input-group-text" style="width: 200px"
                >N Documento:
              </span>
              <input
                type="text"
                placeholder="Digite el Numero de documento..."
                v-model="numero_documento"
                aria-label="Document number"
                class="form-control"
              />
            </div>
            <div class="input-group mb-3">
              <span class="input-group-text" style="width: 200px"
                >Nombre:
              </span>
              <input
                type="text"
                placeholder="Digite el nombre..."
                v-model="nombre"
                aria-label="Firs name"
                class="form-control"
              />
            </div>
            <div class="input-group mb-3">
              <span class="input-group-text" style="width: 200px"
                >Apellido:
              </span>
              <input
                type="text"
                placeholder="Digite el apellido..."
                v-model="apellido"
                aria-label="Last name"
                class="form-control"
              />
            </div>
            <div class="input-group mb-3">
              <span class="input-group-text" style="width: 200px"
                >Dirección:
              </span>
              <input
                type="text"
                placeholder="Digite la dirección..."
                v-model="direccion"
                aria-label="Address"
                class="form-control"
              />
            </div>
            <div class="input-group mb-3">
              <span class="input-group-text" style="width: 200px"
                >Celular:
              </span>
              <input
                type="text"
                placeholder="Digite el celular..."
                v-model="celular"
                aria-label="Movil"
                class="form-control"
              />
            </div>
            <div class="input-group mb-3">
              <span class="input-group-text" style="width: 200px">Email: </span>
              <input
                type="text"
                placeholder="Digite la dirección..."
                v-model="email"
                aria-label="Email"
                class="form-control"
              />
            </div>
            <div class="input-group mb-3">
              <span class="input-group-text" style="width: 200px"
                >Tipo Documento:
              </span>
              <select
                name=""
                id=""
                class="form-control form-control-sm"
                v-model="tipo_documento"
                aria-label="Type"
                style="height: 37px"
              >
                <option value="">Cedula Ciudadania</option>
                <option value="">Tarjeta Identidad</option>
              </select>
            </div>
            <div class="input-group mb-3">
              <span class="input-group-text" style="width: 200px"
                >Id Rol:
              </span>
              <select
                name=""
                id=""
                class="form-control form-control-sm"
                v-model="id_rol"
                aria-label="Role"
                style="height: 37px"
              >
                <option value="">1</option>
                <option value="">2</option>
              </select>
            </div>
            <div class="input-group mb-3">
              <span class="input-group-text" style="width: 200px"
                >Id Ciudad:
              </span>
              <select
                name=""
                id=""
                class="form-control form-control-sm"
                v-model="id_ciudad"
                aria-label="City_Id"
                style="height: 37px"
              >
                <option value="">1</option>
                <option value="">2</option>
              </select>
            </div>
            <div class="input-group mb-3">
              <span class="input-group-text" style="width: 200px"
                >Id Genero:
              </span>
              <select
                name=""
                id=""
                class="form-control form-control-sm"
                v-model="id_genero"
                aria-label="Gender"
                style="height: 37px"
              >
                <option value="">1</option>
                <option value="">2</option>
              </select>
            </div>
          </div>
          <div
            class="btn-group"
            role="group"
            aria-label="Basic outlined example"
            style="margin-top: 12px"
          >
            <button
              @click="crear"
              :disabled="botonCrearDeshabilitado"
              type="button"
              class="btn btn-outline-primary"
            >
              Crear
            </button>
            <button
              @click="actualizar"
              :disabled="botonActualizarDeshabilitado"
              type="button"
              class="btn btn-outline-primary"
            >
              Actualizar
            </button>
            <button
              @click="eliminar"
              :disabled="botonActualizarDeshabilitado"
              type="button"
              class="btn btn-outline-danger"
            >
              Eliminar
            </button>
          </div>
        </div>
      </div>
      <div
        class="card col-6"
        style="float: left; margin: 12px; min-height: 250px"
      >
        <div class="card-body">
          <div class="input-group mb-3">
            <span class="input-group-text" id="inputGroup-sizing-default"
              >Consultar:
            </span>
            <input
              v-model="prefijo"
              placeholder="Digite el texto..."
              class="form-control"
              aria-label="Sizing example input"
              aria-describedby="inputGroup-sizing-default"
            />
          </div>

          <select
            size="6"
            v-model="seleccionado"
            class="form-select"
            aria-label="multiple select example"
          >
            <option
              v-for="item in listaUsuarios"
              :key="item"
              @click="actualizando = true"
            >
              {{
                item.numeroDocumento + " " + item.apellido + " " + item.nombre
              }}
            </option>
          </select>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      nombres: [],
      seleccionado: "",
      prefijo: "",
      nombre: "",
      apellido: "",
      token: localStorage.getItem("token"),
      mensajeError: "",
      actualizando: false,
      listaUsuarios: [],
    };
  },
  computed: {
    nombresFiltrados() {
      return this.nombres.filter((n) =>
        n.toLowerCase().startsWith(this.prefijo.toLowerCase())
      );
    },
    botonCrearDeshabilitado() {
      return this.actualizando;
    },
    botonActualizarDeshabilitado() {
      return !this.actualizando;
    },
  },
  watch: {
    seleccionado(numero_documento) {
      [this.numero_documento] = numero_documento.toString().split(", ");
    },
  },
  methods: {
    crear() {
      if (this.entradaValida()) {
        const numero_documento = `${this.numero_documento}`;
        const nombre = `${this.nombre}`;
        const apellido = `${this.apellido}`;
        const direccion = `${this.direccion}`;
        const celular = `${this.celular}`;
        const email = `${this.email}`;
        if (!this.listaUsuarios.includes(numero_documento)) {
          this.listaUsuarios.push(numero_documento);
          this.listaUsuarios.push(nombre);
          this.listaUsuarios.push(apellido);
          this.listaUsuarios.push(direccion);
          this.listaUsuarios.push(celular);
          this.listaUsuarios.push(email);
          this.nombre = this.apellido = "";
          this.mensajeError = "";
          this.actualizando = false;
          this.$forceUpdate();
        }
      } else {
        this.mensajeError =
          "Por favor ingrese todos los datos para crear la persona.";
      }
    },
    actualizar() {
      if (this.entradaValida() && this.seleccionado) {
        const i = this.numero_documento.indexOf(this.seleccionado);
        this.numero_documento[i] =
          this.seleccionado = `${this.numero_documento}`;
        this.seleccionado = this.numero_documento = "";
        this.mensajeError = "";
        this.actualizando = false;
        this.$forceUpdate();
      } else {
        this.mensajeError =
          "Por favor ingrese todos los datos para actualizar la información.";
      }
    },
    eliminar() {
      if (this.seleccionado) {
        const i = this.nombres.indexOf(this.seleccionado);
        this.nombres.splice(i, 1);
        this.seleccionado = this.nombre = this.apellido = "";
        this.mensajeError = "";
        this.actualizando = false;
      }
    },
    entradaValida() {
      return this.nombre.trim() && this.apellido.trim();
    },

    async consultarUsuarios() {
      const options = {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          Authorization: "Bearer " + this.token,
        },
      };

      fetch("http://localhost:8080/api/usuarios", options).then(
        async (response) => {
          if (!response.ok) {
            const error = new Error(response.statusText);
            error.json = response.json();
            this.mensajeError = error.message;
            throw error;
          } else {
            const data = await response.json();
            //console.log("personas",data);
            this.listaUsuarios = data;
          }
        }
      );
    },
  },
  mounted() {
    this.consultarUsuarios();
  },
};
</script>