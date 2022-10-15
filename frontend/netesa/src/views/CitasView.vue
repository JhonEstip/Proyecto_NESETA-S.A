<template>
    <div class="container-x px-4">
        <div class="contenedor1">
            <div class="row pb-1 mt-4 d-flex justify-content-between">
                <div class="col-sm-10"><h3>REPORTE DE CITAS PARA -{{}}-</h3></div>
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
                                <select name="" id="" class="form-control form-control-sm">
                                    <option value="">Pacientes...</option>
                                </select>
                            </td>
                            <td>
                                <select name="" id="" class="form-control form-control-sm">
                                    <option value="">Medicos...</option>
                                </select>
                            </td>
                            <td>
                                <select name="" id="" class="form-control form-control-sm">
                                    <option value="">Consultorio...</option>
                                </select>
                            </td>
                            <td></td>
                            <td></td>
                            <td><input type="text" class="form-control form-control-sm" placeholder=""></td>
                            <td class="text-center"><button class="btn btn-sm btn-info">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-folder-plus"
                                        viewBox="0 0 16 16">
                                        <path
                                            d="m.5 3 .04.87a1.99 1.99 0 0 0-.342 1.311l.637 7A2 2 0 0 0 2.826 14H9v-1H2.826a1 1 0 0 1-.995-.91l-.637-7A1 1 0 0 1 2.19 4h11.62a1 1 0 0 1 .996 1.09L14.54 8h1.005l.256-2.819A2 2 0 0 0 13.81 3H9.828a2 2 0 0 1-1.414-.586l-.828-.828A2 2 0 0 0 6.172 1H2.5a2 2 0 0 0-2 2zm5.672-1a1 1 0 0 1 .707.293L7.586 3H2.19c-.24 0-.47.042-.683.12L1.5 2.98a1 1 0 0 1 1-.98h3.672z" />
                                        <path
                                            d="M13.5 10a.5.5 0 0 1 .5.5V12h1.5a.5.5 0 1 1 0 1H14v1.5a.5.5 0 1 1-1 0V13h-1.5a.5.5 0 0 1 0-1H13v-1.5a.5.5 0 0 1 .5-.5z" />
                                    </svg>
                                </button></td>
                        </tr>
                        <tr v-for="item in resultadoPersonas" :key="item">
                            <td>{{item.usuario.apellido}} {{item.usuario.nombre}}</td>
                            <td>{{item.medico.usuario.apellido +" "+ item.medico.usuario.nombre}}</td>
                            <td class="text-center">00{{item.consultorio.id_consultorio}}</td>
                            <td class="text-center">{{item.consultorio.piso}}</td>
                            <td>{{item.consultorio.descr_consultorio}}</td>
                            <td>{{item.fechaCita}}</td>
                            <td class="text-center">
                                <button class="btn btn-sm btn-warning">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil" viewBox="0 0 16 16">
                                        <path d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5 13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z"/>
                                    </svg>
                                </button>
                                <button class="btn btn-sm btn-danger ml-2">
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
            nombres: [],
            seleccionado: '',
            prefijo: '',
            nombre: '',
            apellido: '',
            token: 'pendiente',
            mensajeError: '',
            actualizando: false,
            resultadoPersonas:[]
        }
    },
    computed: {
        nombresFiltrados() {
            return this.nombres.filter((n) =>
                n.toLowerCase().startsWith(this.prefijo.toLowerCase())
            )
        },
        botonCrearDeshabilitado() {
            return this.actualizando;
        },
        botonActualizarDeshabilitado() {
            return !this.actualizando;
        }
    },
    watch: {
        seleccionado(nombreCompleto) {
            [this.apellido, this.nombre] = nombreCompleto.toString().split(', ');
        }
    },
    methods: {
        crear() {
            if (this.entradaValida()) {
                const nombreCompleto = `${this.apellido}, ${this.nombre}`
                if (!this.nombres.includes(nombreCompleto)) {
                    this.nombres.push(nombreCompleto)
                    this.nombre = this.apellido = '';
                    mensajeError = '';
                    this.actualizando = false;
                    this.$forceUpdate();
                }
            } else {
                mensajeError = "Por favor ingrese todos los datos para crear la persona.";
            }
        },
        actualizar() {
            if (this.entradaValida() && this.seleccionado) {
                const i = this.nombres.indexOf(this.seleccionado);
                this.nombres[i] = this.seleccionado = `${this.apellido}, ${this.nombre}`;
                this.seleccionado = this.nombre = this.apellido = '';
                mensajeError = '';
                this.actualizando = false;
                this.$forceUpdate();
            } else {
                mensajeError = "Por favor ingrese todos los datos para actualizar la información.";
            }
        },
        eliminar() {
            if (this.seleccionado) {
                const i = this.nombres.indexOf(this.seleccionado);
                this.nombres.splice(i, 1);
                this.seleccionado = this.nombre = this.apellido = '';
                mensajeError = '';
                this.actualizando = false;
            }
        },
        entradaValida() {
            return this.nombre.trim() && this.apellido.trim();
        },
        async consultarPersonas() {
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
                        console.log(data);
                        this.resultadoPersonas = data;
                    }
                });
        },
        async obtenerData() {
            const options = {
                method: 'POST',
                body: JSON.stringify({ id: 1 }),
                headers: {
                    'Content-Type': 'application/json'
                    /* , 'Authorization': 'Bearer '+this.token */
                }
            };

            fetch('http://localhost:8080/api/auth', options)
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
                        this.consultarPersonas();
                    }
                });
        }
    },
    mounted() {
        this.obtenerData();
    }
})
</script>