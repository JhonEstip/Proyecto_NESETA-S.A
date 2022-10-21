<template>
  <div class="container" style="margin-top: 5em;">
    <div class="float-end">
      <button class="btn btn-sm btn-primary ml-2" @click="abrirModalCrear">
        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-plus" viewBox="0 0 16 16">
          <path d="M6 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm2-3a2 2 0 1 1-4 0 2 2 0 0 1 4 0zm4 8c0 1-1 1-1 1H1s-1 0-1-1 1-4 6-4 6 3 6 4zm-1-.004c-.001-.246-.154-.986-.832-1.664C9.516 10.68 8.289 10 6 10c-2.29 0-3.516.68-4.168 1.332-.678.678-.83 1.418-.832 1.664h10z"/>
          <path fill-rule="evenodd" d="M13.5 5a.5.5 0 0 1 .5.5V7h1.5a.5.5 0 0 1 0 1H14v1.5a.5.5 0 0 1-1 0V8h-1.5a.5.5 0 0 1 0-1H13V5.5a.5.5 0 0 1 .5-.5z"/>
        </svg>
      </button>
    </div>    
    <table class="table table-striped">
      <thead class="thead-light">
        <tr class="table-primary">
          <th scope="col">Nombre</th>
          <th scope="col">Apellido</th>
          <th scope="col">Número de cedula</th>
          <th scope="col">Celular</th>
          <th scope="col">Email</th>
          <th scope="col">Dirección</th>
          <th scope="col">Ciudad</th>
          <th scope="col">Genero</th>
          <th scope="col">Rol</th>
          <th scope="col">Tipo de Documento</th>
          <th scope="col">Opciones</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="item in listaUsuarios" :key="item">
            <td>{{item.nombre}}</td>
            <td>{{item.apellido}}</td>
            <td>{{item.numeroDocumento}}</td>
            <td>{{item.celular}}</td>
            <td>{{item.email}}</td>
            <td>{{item.direccion}}</td>
            <td>{{item.ciudad.ciudad}}</td>
            <td>{{item.genero.descripcionGenero}}</td>
            <td>{{item.rol.descripcionRol}}</td>
            <td>{{item.tipodocumento.descrDocumento}}</td>
            <td class="text-center">
                <button class="btn btn-sm btn-warning" @click="abrirEditar(item.id,item.nombre,item.apellido,item.numeroDocumento,item.celular,item.email,item.direccion,item.ciudad.id_ciudad,item.genero.id,item.rol.id,item.tipodocumento.id_tipo_documento,item.password)">
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil" viewBox="0 0 16 16">
                        <path d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5 13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z"/>
                    </svg>
                </button>
                <button class="btn btn-sm btn-danger" style="margin-left: 5px;" @click="eliminar(item.id)">
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash3" viewBox="0 0 16 16">
                        <path d="M6.5 1h3a.5.5 0 0 1 .5.5v1H6v-1a.5.5 0 0 1 .5-.5ZM11 2.5v-1A1.5 1.5 0 0 0 9.5 0h-3A1.5 1.5 0 0 0 5 1.5v1H2.506a.58.58 0 0 0-.01 0H1.5a.5.5 0 0 0 0 1h.538l.853 10.66A2 2 0 0 0 4.885 16h6.23a2 2 0 0 0 1.994-1.84l.853-10.66h.538a.5.5 0 0 0 0-1h-.995a.59.59 0 0 0-.01 0H11Zm1.958 1-.846 10.58a1 1 0 0 1-.997.92h-6.23a1 1 0 0 1-.997-.92L3.042 3.5h9.916Zm-7.487 1a.5.5 0 0 1 .528.47l.5 8.5a.5.5 0 0 1-.998.06L5 5.03a.5.5 0 0 1 .47-.53Zm5.058 0a.5.5 0 0 1 .47.53l-.5 8.5a.5.5 0 1 1-.998-.06l.5-8.5a.5.5 0 0 1 .528-.47ZM8 4.5a.5.5 0 0 1 .5.5v8.5a.5.5 0 0 1-1 0V5a.5.5 0 0 1 .5-.5Z"/>
                    </svg>
                </button>
            </td>
        </tr>
      </tbody>
    </table>
  </div>
  <!-- Modal Crear -->
  <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel"> Netesa Administracion de Usuarios</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">   
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
                <option v-for="itemTD in listaTiposDocumento" :key="itemTD" :value="itemTD.id_tipo_documento">{{itemTD.descrDocumento}}</option>
              </select>
            </div>
            <div class="input-group mb-3">
              <span class="input-group-text" style="width: 200px">
                Rol:
              </span>
              <select name="" id="" class="form-control form-control-sm" v-model="id_rol" aria-label="Role" style="height: 37px">
                <option v-for="itemLR in listaRoles" :key="itemLR" :value="itemLR.id">{{itemLR.descripcionRol}}</option>
              </select>
            </div>
            <div class="input-group mb-3">
              <span class="input-group-text" style="width: 200px">
                Ciudad:
              </span>
              <select name="" id=""  class="form-control form-control-sm" v-model="id_ciudad" aria-label="City_Id" style="height: 37px">
                <option v-for="itemCiu in listaCiudades" :key="itemCiu" :value="itemCiu.id_ciudad">{{itemCiu.ciudad}}</option>
              </select>
            </div>
            <div class="input-group mb-3">
              <span class="input-group-text" style="width: 200px">
                Genero:
              </span>
              <select name="" id="" class="form-control form-control-sm" v-model="id_genero" aria-label="Gender" style="height: 37px">
                <option v-for="itemGen in listaGenero" :key="itemGen" :value="itemGen.id">{{itemGen.descripcionGenero}}</option>
              </select>
            </div>
            <div class="input-group mb-3">
              <span class="input-group-text" style="width: 200px">Password: </span>
              <input type="text" placeholder="Digite el password..." v-model="password" aria-label="Email" class="form-control"/>
            </div>
      </div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
          <button v-if="tipoCrud == 'Editar'" type="button" class="btn btn-primary" @click="editar">{{tipoCrud}}</button>
          <button v-else type="button" class="btn btn-primary" @click="crear">{{tipoCrud}}</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      token: localStorage.getItem("token"),
      listaUsuarios: [],
      mensajeError: "",
      listaTiposDocumento: [],
      listaCiudades: [],
      listaRoles: [],
      listaGenero: [],
      numero_documento: "",
      nombre: "",
      apellido: "",
      direccion: "",
      celular: 0,
      email: "",
      tipo_documento: 0,
      id_rol: 0,
      id_ciudad: 0,
      id_genero: 0,
      password: "",
      myModal: null,
      tipoCrud: "Crear",
      idUsuario: 0,
    };
  },
  methods: {
    entradaValida() { //funcional
        return this.numero_documento.trim() && this.nombre.trim() && this.apellido.trim() && this.direccion.trim() && this.email.trim() && this.password.trim();
    },
    async abrirEditar(id, nombre, apellido, ndoc, celular, email, direccion, id_ciudad, id_genero, id_rol, id_t_doc, password){
      this.numero_documento = ndoc,
      this.nombre= nombre,
      this.apellido= apellido,
      this.direccion= direccion,
      this.celular= celular,
      this.email= email,
      this.tipo_documento= id_t_doc,
      this.id_rol= id_rol,
      this.id_ciudad= id_ciudad,
      this.id_genero= id_genero,
      this.password= password,
      this.tipoCrud = "Editar"
      this.idUsuario = id

      this.myModal.show()
    },
    async eliminar(id){
      const options = {
                method: "DELETE",
                headers: {
                  "Content-Type": "application/json",
                  Authorization: "Bearer " + this.token,
                },
              };

              let consulta = await fetch("http://132.145.163.148:8080/netesa_sa/api/usuarios/" + id, options)
                if (!consulta.ok) {
                          const error = new Error(consulta.statusText);
                          error.json = consulta.json();
                          this.mensajeError = error.message;
                          throw error;
                } else {
                  Swal.fire(
                    'Bien Hecho!',
                    'Usuario eliminado!',
                    'success'
                  )
                  this.myModal.hide()
                  setTimeout(() => {
                    this.$router.push('home')
                    this.$router.push('usuarios')
                  }, 2000);
                  ;
                }
              // console.log("AGREGAR",consulta)
    },
    async editar(){
      if (this.entradaValida()) {
              const options = {
                method: "PUT",
                body: JSON.stringify({
                    numeroDocumento:this.numero_documento,
                    nombre:this.nombre,
                    apellido:this.apellido,
                    direccion:this.direccion,
                    celular: parseInt(this.celular),
                    email:this.email,
                    tipodocumento:{id_tipo_documento: this.tipo_documento},
                    rol:{id:this.id_rol},
                    ciudad:{id_ciudad: this.id_ciudad},
                    genero:{id:this.id_genero},
                    password:this.password
                }),
                headers: {
                  "Content-Type": "application/json",
                  Authorization: "Bearer " + this.token,
                },
              };

              let consulta = await fetch("http://132.145.163.148:8080/netesa_sa/api/usuarios/" + this.idUsuario, options)
                if (!consulta.ok) {
                          const error = new Error(consulta.statusText);
                          error.json = consulta.json();
                          this.mensajeError = error.message;
                          throw error;
                } else {
                  Swal.fire(
                    'Bien Hecho!',
                    'Usuario editado!',
                    'success'
                  )
                  this.myModal.hide()
                  setTimeout(() => {
                    this.$router.push('home')
                    this.$router.push('usuarios')
                  }, 2000);
                }
              console.log("AGREGAR",consulta)
            } else {
                this.mensajeError = "Por favor ingrese todos los datos para crear la persona.";
            }
    },
    abrirModalCrear(){
        this.numero_documento = "",
        this.nombre= "",
        this.apellido= "",
        this.direccion= "",
        this.celular= 0,
        this.email= "",
        this.tipo_documento= "",
        this.id_rol= 0,
        this.id_ciudad= 0,
        this.id_genero= 0,
        this.password= "",
        this.tipoCrud = "Crear"
        this.myModal.show()
    },
    async crear() {
            if (this.entradaValida()) {
              const options = {
                method: "POST",
                body: JSON.stringify({
                    numeroDocumento:this.numero_documento,
                    nombre:this.nombre,
                    apellido:this.apellido,
                    direccion:this.direccion,
                    celular: parseInt(this.celular),
                    email:this.email,
                    tipodocumento:{id_tipo_documento: this.tipo_documento},
                    rol:{id:this.id_rol},
                    ciudad:{id_ciudad: this.id_ciudad},
                    genero:{id:this.id_genero},
                    password:this.password
                }),
                headers: {
                  "Content-Type": "application/json",
                  Authorization: "Bearer " + this.token,
                },
              };

              let consulta = await fetch("http://132.145.163.148:8080/netesa_sa/api/usuarios", options)
                if (!consulta.ok) {
                          const error = new Error(consulta.statusText);
                          error.json = consulta.json();
                          this.mensajeError = error.message;
                          throw error;
                } else {
                  Swal.fire(
                    'Bien Hecho!',
                    'Usuario creado!',
                    'success'
                  )
                  this.myModal.hide()
                  setTimeout(() => {
                    this.$router.push('home')
                    this.$router.push('usuarios')
                  }, 2000);
                }
              console.log("AGREGAR",consulta)
            } else {
                this.mensajeError = "Por favor ingrese todos los datos para crear la persona.";
            }
    }, 
    async consultarUsuarios() {
      const options = {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          Authorization: "Bearer " + this.token,
        },
      };

      let consulta = await fetch("http://132.145.163.148:8080/netesa_sa/api/usuarios", options)
      if (!consulta.ok) {
            const error = new Error(consulta.statusText);
            error.json = consulta.json();
            this.mensajeError = error.message;
            throw error;
      } else {
            const data = await consulta.json();
            console.log("personas",data);
            this.listaUsuarios = data;
      }
    },
    async consultarTipoDocumento() {
      const options = {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          Authorization: "Bearer " + this.token,
        },
      };

      fetch("http://132.145.163.148:8080/netesa_sa/api/tipodocumento", options).then(
        async (response) => {
          if (!response.ok) {
            const error = new Error(response.statusText);
            error.json = response.json();
            this.mensajeError = error.message;
            throw error;
          } else {
            const data = await response.json();
            console.log("tdoc",data);
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

      fetch("http://132.145.163.148:8080/netesa_sa/api/rol", options).then(
        async (response) => {
          if (!response.ok) {
            const error = new Error(response.statusText);
            error.json = response.json();
            this.mensajeError = error.message;
            throw error;
          } else {
            const data = await response.json();
            console.log("rol",data);
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

      fetch("http://132.145.163.148:8080/netesa_sa/api/ciudad", options).then(
        async (response) => {
          if (!response.ok) {
            const error = new Error(response.statusText);
            error.json = response.json();
            this.mensajeError = error.message;
            throw error;
          } else {
            const data = await response.json();
            console.log("ciudad",data);
            this.listaCiudades = data;
          }
        }
      );
    },
    async consultarGenero() {
      const options = {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          Authorization: "Bearer " + this.token,
        },
      };

      fetch("http://132.145.163.148:8080/netesa_sa/api/genero", options).then(
        async (response) => {
          if (!response.ok) {
            const error = new Error(response.statusText);
            error.json = response.json();
            this.mensajeError = error.message;
            throw error;
          } else {
            const data = await response.json();
            console.log("genero",data);
            this.listaGenero = data;
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
    this.consultarGenero();
    this.myModal = new bootstrap.Modal(document.getElementById('exampleModal'), {
        keyboard: false
    })
  },
};
</script>
