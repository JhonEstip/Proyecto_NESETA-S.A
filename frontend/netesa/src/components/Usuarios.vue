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
      <div class="card col-md-12" style="float: left; margin: 12px; min-height: 250px">
        <div class="card-body col-md-12">
          <h5 class="card-title" style="margin-bottom: 12px">
            Netesa Administracion de Usuarios
          </h5>
          <div class="card-text col-md-12">
            <div class="input-group mb-3">
              <span class="input-group-text" style="width: 200px">N Documento:</span>
              <input type="text" placeholder="Digite el Numero de documento..." v-model="numero_documento" aria-label="Document number" class="form-control"/>
            </div>
            <div class="input-group mb-3">
              <span class="input-group-text" style="width: 200px">Nombre:
              </span>
              <input type="text" placeholder="Digite el nombre..." v-model="nombre" aria-label="Firs name" class="form-control"/>
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
              <span class="input-group-text" style="width: 200px">
                Tipo Documento:
              </span>
              <select name="" id="" class="form-control form-control-sm" v-model="tipo_documento" aria-label="Type" style="height: 37px">
                <option v-for="itemTD in listaTiposDocumento" :key="itemTD" :value="itemTD.id_tipo_documento">{{itemTD.id_tipo_documento}}</option>
              </select>
            </div>
            <div class="input-group mb-3">
              <span class="input-group-text" style="width: 200px">
                Id Rol:
              </span>
              <select name="" id="" class="form-control form-control-sm" v-model="id_rol" aria-label="Role" style="height: 37px">
                <option v-for="itemLR in listaRoles" :key="itemLR" :value="itemLR.id">{{itemLR.id}}</option>
              </select>
            </div>
            <div class="input-group mb-3">
              <span class="input-group-text" style="width: 200px">
                Id Ciudad:
              </span>
              <select name="" id=""  class="form-control form-control-sm" v-model="id_ciudad" aria-label="City_Id" style="height: 37px">
                <option v-for="itemCiu in listaCiudades" :key="itemCiu" :value="itemCiu.id_ciudad">{{itemCiu.id_ciudad}}</option>
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
      <div class="card col-12"  style="float: left; margin: 12px; min-height: 250px">
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
          <table class="table">
            <thead>
              <tr >
                <th scope="col">Id</th>
                <th scope="col">Numero Documento</th>
                <th scope="col">Tipo Documento</th>
                <th scope="col">Apellido</th>
                <th scope="col">Nombre</th>
                <th scope="col">Dirección</th>
                <th scope="col">Celular</th>
                <th scope="col">Email</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="item in listaUsuarios" :key="item">
                <td >{{item.id}}</td>
                <td>{{item.numeroDocumento}}</td>
                <td>{{item.tipodocumento.id_tipo_documento}}</td>
                <td>{{item.apellido}}</td>
                <td>{{item.nombre}}</td>
                <td>{{item.direccion}}</td>
                <td>{{item.celular}}</td>
                <td>{{item.email}}</td>
                <td><button class = "ml-2 btn btn-sm btn-primary" @click="editar(item.id)">
                  <i>Editar</i>
                    </button>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      indice : -1,
      nombres: [],
      seleccionado: "",
      prefijo: "",
      nombre: "",
      apellido: "",
      token: localStorage.getItem("token"),
      mensajeError: "",
      actualizando: false,
      listaUsuarios: [],
      listaTiposDocumento:[],
      listaRoles:[],
      listaCiudades:[]
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
    entradaValida() { //funcional
        return this.numero_documento.trim() && this.nombre.trim() && this.apellido.trim() && this.direccion.trim() && this.celular.trim() && this.email.trim();
    },
    crear() {
            if (this.entradaValida()) {
              this.crearUsuario(
                    this.numero_documento,
                    this.nombre,
                    this.apellido,
                    this.direccion,
                    this.celular,
                    this.email,
                    this.tipo_documento,
                    this.id_rol,
                    this.id_ciudad,
                    this.id_genero
                    );
            } else {
                this.mensajeError = "Por favor ingrese todos los datos para crear la persona.";
            }
        },
        async crearUsuario(numero_documento,nombre,apellido,direccion,celular,email,tipo_documento,id_rol,id_ciudad,id_genero) {
            const options = {
                method: 'POST',
                body: JSON.stringify(
                    {
                     numeroDocumento:numero_documento,
                     nombre:nombre,
                     apellido:apellido,
                     direccion:direccion,
                     celular:celular,
                     email:email,
                     tipo_documento:tipo_documento,
                     id_rol:id_rol,
                     id_ciudad:id_ciudad,
                     id_genero:id_genero
                    }
                ),
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer '+this.token
                }
            };

            fetch('http://localhost:8080/api/usuarios', options)
                .then(async (response) => {
                    if (!response.ok) {
                        const error = new Error(response.statusText);
                        error.json = response.json();
                        this.mensajeError = error.message;
                        throw error;
                    } else {
                        const data = await response.json();
                        this.token = data.access;
                        console.log(data);
                        alertify.success('Usuario creada');
                        setTimeout(() => {
                            window.location.reload();
                        }, 1000);
                    }
                });
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
    async consultarTipoDocumento() {
      const options = {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          Authorization: "Bearer " + this.token,
        },
      };

      fetch("http://localhost:8080/api/tipodocumento", options).then(
        async (response) => {
          if (!response.ok) {
            const error = new Error(response.statusText);
            error.json = response.json();
            this.mensajeError = error.message;
            throw error;
          } else {
            const data = await response.json();
            //console.log("personas",data);
            this.listaTiposDocumento = data;
          }
        }
      );
    },
    async consultarRol() {
      const options = {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          Authorization: "Bearer " + this.token,
        },
      };

      fetch("http://localhost:8080/api/rol", options).then(
        async (response) => {
          if (!response.ok) {
            const error = new Error(response.statusText);
            error.json = response.json();
            this.mensajeError = error.message;
            throw error;
          } else {
            const data = await response.json();
            //console.log("personas",data);
            this.listaRoles = data;
          }
        }
      );
    },
    async consultarCiudad() {
      const options = {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          Authorization: "Bearer " + this.token,
        },
      };

      fetch("http://localhost:8080/api/ciudad", options).then(
        async (response) => {
          if (!response.ok) {
            const error = new Error(response.statusText);
            error.json = response.json();
            this.mensajeError = error.message;
            throw error;
          } else {
            const data = await response.json();
            //console.log("personas",data);
            this.listaCiudades = data;
          }
        }
      );
    },
  },
  mounted() {
    this.consultarUsuarios();
    this.consultarTipoDocumento();
    this.consultarRol();
    this.consultarCiudad();
  },
};
</script>
