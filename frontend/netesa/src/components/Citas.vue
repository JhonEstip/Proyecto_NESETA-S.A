<template>
    <div class="container-x px-4">
        <div class="contenedor1">
            <div class="row pb-1 mt-4 d-flex justify-content-between">
                <div class="col-sm-10"><h3>REPORTE DE CITAS PARA ADMINISTRADOR</h3></div>
                <div class="col-sm-2">
                    <input type="date" class="form-control" value="date" id="fecha">
                </div>
            </div>
            <div class="row pb-2 d-flex justify-content-end">
                <div class="col-sm-3">
                    <input type="text" placeholder="Search..." id="buscador" class="form-control">
                </div>
                
            </div>
            <div class="row">
                <div class="col-sm-12">
                    <table class="table table-sm table-bordered table-striped">
                        <tr class="table-primary">
                            <td>Paciente</td>
                            <td>Medico</td>
                            <td>Consultorio</td>
                            <td>Piso</td>
                            <td>Area</td>
                            <td>Fecha Hora</td>
                            <td>Opciones</td>
                        </tr>
                        <tr>
                            <td>
                                <select name="" id="" class="form-control form-control-sm" v-model="seleccionadoPaciente">
                                    <option value="" selected>Pacientes...</option>
                                    <option v-for="item in resultadoPersonas" :key="item" :value="item.id">{{item.apellido +" "+item.nombre}}</option>
                                </select>
                            </td>
                            <td>
                                <select name="" id="" class="form-control form-control-sm" v-model="seleccionadoMedico">
                                    <option value="">Medicos...</option>
                                    <option v-for="item in resultadoMedicos" :key="item" :value="item.id">{{item.usuario.apellido +" "+item.usuario.nombre}}</option>
                                </select>
                            </td>
                            <td>
                                <select name="" id="" class="form-control form-control-sm" v-model="seleccionadoConsultorio">
                                    <option value="">Consultorio...</option>
                                    <option v-for="item in resultadoConsultorios" :key="item" :value="item.id_consultorio">{{item.id_sucursal.ciudad.ciudad +" "+item.id_sucursal.descripcion +" "+item.descr_consultorio}}</option>
                                </select>
                            </td>
                            <td><input type="text" v-model="idCita" hidden></td>
                            <td></td>
                            <td>
                                <input type="text" class="form-control form-control-sm" placeholder="2022-10-15 10:00" v-model="fecha">
                            </td>

                            <td class="text-center">
                                <button class="btn btn-sm btn-success" @click="crear">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-folder-plus"
                                        viewBox="0 0 16 16">
                                        <path
                                            d="m.5 3 .04.87a1.99 1.99 0 0 0-.342 1.311l.637 7A2 2 0 0 0 2.826 14H9v-1H2.826a1 1 0 0 1-.995-.91l-.637-7A1 1 0 0 1 2.19 4h11.62a1 1 0 0 1 .996 1.09L14.54 8h1.005l.256-2.819A2 2 0 0 0 13.81 3H9.828a2 2 0 0 1-1.414-.586l-.828-.828A2 2 0 0 0 6.172 1H2.5a2 2 0 0 0-2 2zm5.672-1a1 1 0 0 1 .707.293L7.586 3H2.19c-.24 0-.47.042-.683.12L1.5 2.98a1 1 0 0 1 1-.98h3.672z" />
                                        <path
                                            d="M13.5 10a.5.5 0 0 1 .5.5V12h1.5a.5.5 0 1 1 0 1H14v1.5a.5.5 0 1 1-1 0V13h-1.5a.5.5 0 0 1 0-1H13v-1.5a.5.5 0 0 1 .5-.5z" />
                                    </svg>
                                </button>
                                <button class="ml-2 btn btn-sm btn-primary" @click="actualizar">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                        class="bi bi-arrow-up-square-fill" viewBox="0 0 16 16">
                                        <path
                                            d="M2 16a2 2 0 0 1-2-2V2a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v12a2 2 0 0 1-2 2H2zm6.5-4.5V5.707l2.146 2.147a.5.5 0 0 0 .708-.708l-3-3a.5.5 0 0 0-.708 0l-3 3a.5.5 0 1 0 .708.708L7.5 5.707V11.5a.5.5 0 0 0 1 0z" />
                                    </svg>
                                </button>
                            


                                
                            </td>
                        </tr>
                        <tr v-for="item in resultadoCitas" :key="item">
                            <td>{{item.usuario.apellido}} {{item.usuario.nombre}}</td>
                            <td>{{item.medico.usuario.apellido +" "+ item.medico.usuario.nombre}}</td>
                            <td class="text-center">00{{item.consultorio.id_consultorio}}</td>
                            <td class="text-center">{{item.consultorio.piso}}</td>
                            <td>{{item.consultorio.descr_consultorio}}</td>
                            <td>{{item.fechaCita}}</td>
                            <td class="text-center">
                                <button class="btn btn-sm btn-warning" @click="editar(item.id)">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil" viewBox="0 0 16 16">
                                        <path d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5 13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z"/>
                                    </svg>
                                </button>
                                <button class="btn btn-sm btn-danger ml-2" @click="eliminar(item.id)">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash3" viewBox="0 0 16 16">
                                        <path d="M6.5 1h3a.5.5 0 0 1 .5.5v1H6v-1a.5.5 0 0 1 .5-.5ZM11 2.5v-1A1.5 1.5 0 0 0 9.5 0h-3A1.5 1.5 0 0 0 5 1.5v1H2.506a.58.58 0 0 0-.01 0H1.5a.5.5 0 0 0 0 1h.538l.853 10.66A2 2 0 0 0 4.885 16h6.23a2 2 0 0 0 1.994-1.84l.853-10.66h.538a.5.5 0 0 0 0-1h-.995a.59.59 0 0 0-.01 0H11Zm1.958 1-.846 10.58a1 1 0 0 1-.997.92h-6.23a1 1 0 0 1-.997-.92L3.042 3.5h9.916Zm-7.487 1a.5.5 0 0 1 .528.47l.5 8.5a.5.5 0 0 1-.998.06L5 5.03a.5.5 0 0 1 .47-.53Zm5.058 0a.5.5 0 0 1 .47.53l-.5 8.5a.5.5 0 1 1-.998-.06l.5-8.5a.5.5 0 0 1 .528-.47ZM8 4.5a.5.5 0 0 1 .5.5v8.5a.5.5 0 0 1-1 0V5a.5.5 0 0 1 .5-.5Z"/>
                                    </svg>
                                </button>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    export default ({
        data() {
        return {
            citas: [],
            seleccionadoPaciente: '',
            seleccionadoMedico: '',
            seleccionadoConsultorio: '',
            idCita: '',
            fecha: '',
            nombre: '',
            apellido: '',
            token: localStorage.getItem("token"),
            actualizando: false,
            resultadoCitas:[],
            resultadoConsultorios:[],
            resultadoMedicos:[],
            resultadoPersonas:[],
            resultadoCitaEdicion: []
        }
    },
    computed: {
        nombresFiltrados() {
            return this.nombres.filter((n) =>
                n.toLowerCase().startsWith(this.prefijo.toLowerCase())
            )
        },
    },
    watch: {
        seleccionado(nombreCompleto) {
            [this.apellido, this.nombre] = nombreCompleto.toString().split(', ');
        }
    },
    methods: {
        crear() {
            if (this.entradaValida) {
                this.crearCita(
                    this.seleccionadoPaciente,
                    this.seleccionadoMedico,
                    this.seleccionadoConsultorio,
                    this.fecha);
            }
        },
        editar(id) {
            this.consultarCitaId(id);
        },

        actualizar() {
            if (this.entradaValida) {
                this.actualizarCita(
                    this.idCita, 
                    this.fecha, 
                    this.seleccionadoConsultorio, 
                    this.seleccionadoMedico, 
                    this.seleccionadoPaciente);
            }
        },

        eliminar(id) { //funcional
            this.eliminarCita(id);
        },
        entradaValida() { //funcional
            return this.seleccionadoPaciente.trim() && this.seleccionadoMedico.trim() && this.seleccionadoConsultorio.trim() && this.fecha.trim();
        },
        async eliminarCita(id) {
            const options = {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer '+ this.token
                }
            };

            fetch('http://localhost:8080/api/citas/'+id, options)
                .then(async (response) => {
                    if (response.status === 200) {
                        console.log("mensaje",response);
                        setTimeout(() => {
                            window.location.reload();
                        }, 1000);
                    }
                });
        },
        async consultarMedicos() {
            const options = {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer '+this.token 
                }
            };

            fetch('http://localhost:8080/api/medicos', options)
                .then(async (response) => {
                    if (!response.ok) {
                        const error = new Error(response.statusText);
                        error.json = response.json();
                        this.mensajeError = error.message;
                        throw error;
                    } else {
                        const data = await response.json();
                        //console.log("medicos",data);
                        this.resultadoMedicos = data;
                    }
                });
        },
        async consultarCitaId(id) {
            const options = {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer '+this.token 
                }
            };

            fetch('http://localhost:8080/api/citas/'+id, options)
                .then(async (response) => {
                    if (!response.ok) {
                        const error = new Error(response.statusText);
                        error.json = response.json();
                        this.mensajeError = error.message;
                        throw error;
                    } else {
                        const data = await response.json();
                        console.log("resultadocitaid",data);
                        this.resultadoCitaEdicion = data;

                        this.seleccionadoMedico = this.resultadoCitaEdicion['medico']['id'];
                        this.seleccionadoPaciente = this.resultadoCitaEdicion['usuario']['id'];
                        this.seleccionadoConsultorio = this.resultadoCitaEdicion['consultorio']['id_consultorio'];
                        this.fecha = this.resultadoCitaEdicion['fechaCita'];
                        this.idCita = this.resultadoCitaEdicion['id'];
                    }
                });
        },
        async consultarPersonas() {
            const options = {
                method: 'GET',
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
                        //console.log("personas",data);
                        this.resultadoPersonas = data;
                    }
                });
        },
        async consultarCitas() {
            const options = {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer '+this.token 
                }
            };

            fetch('http://localhost:8080/api/citas', options)
                .then(async (response) => {
                    if (!response.ok) {
                        const error = new Error(response.statusText);
                        error.json = response.json();
                        this.mensajeError = error.message;
                        throw error;
                    } else {
                        const data = await response.json();
                        //console.log(data);
                        this.resultadoCitas = data;
                    }
                });
        },
        async consultarConsultorio() {
            const options = {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer '+this.token 
                }
            };

            fetch('http://localhost:8080/api/consultorio', options)
                .then(async (response) => {
                    if (!response.ok) {
                        const error = new Error(response.statusText);
                        error.json = response.json();
                        this.mensajeError = error.message;
                        throw error;
                    } else {
                        const data = await response.json();
                        //console.log(data);
                        this.resultadoConsultorios = data;
                    }
                });
        },
        async actualizarCita(idCita, fecha, consultorio, medico, paciente) {
            const options = {
                method: 'PUT',
                body: JSON.stringify(
                    {
                     fechaCita:fecha,
                     consultorio:{id_consultorio:consultorio},
                     medico:{id:medico},
                     usuario:{id:paciente}
                    }
                ),
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer '+this.token
                }
            };

            fetch('http://localhost:8080/api/citas/'+idCita, options)
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
                        alertify.success('Cita actualizada');
                        setTimeout(() => {
                            window.location.reload();
                        }, 1000);
                    }
                });
    },
        async crearCita(paciente,medico,consultorio,hora) {
            console.log({
                     fechaCita:hora,
                     consultorio:{id_consultorio:consultorio},
                     medico:{id:medico},
                     usuario:{id:paciente}
                    });
            const options = {
                method: 'POST',
                body: JSON.stringify(
                    {
                     fechaCita:hora,
                     consultorio:{id_consultorio:consultorio},
                     medico:{id:medico},
                     usuario:{id:paciente}
                    }
                ),
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer '+this.token
                }
            };

            fetch('http://localhost:8080/api/citas', options)
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
                        alertify.success('Cita creada');
                        setTimeout(() => {
                            window.location.reload();
                        }, 1000);
                    }
                });
    },
    },
    mounted() {
        // this.obtenerData();
        this.consultarCitas();
        this.consultarConsultorio();
        this.consultarMedicos(); 
        this.consultarPersonas();
    }
})
</script>