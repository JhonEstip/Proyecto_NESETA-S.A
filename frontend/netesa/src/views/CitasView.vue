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
                <div class="col-sm-3">
                   <button class="btn btn-sm" @click="consultarPersonas">BUSCAR</button>
                </div>
                
            </div>
            <div class="row">
                <div class="col-sm-12">
                    <table class="table table-sm table-bordered table-striped">
                        <tr>
                            <td>Paciente</td>
                            <td>Medico</td>
                            <td>Consultorio</td>
                            <td>Piso</td>
                            <td>Area</td>
                            <td>Fecha Hora</td>
                            <td>Opciones</td>
                        </tr>
                        <tr v-for="item in resultadoPersonas" :key="item">
                            <td>Neider Neme Virguez</td>
                            <td>Alfredo Vargas</td>
                            <td class="text-center">001</td>
                            <td class="text-center">2</td>
                            <td>Pediatria 1</td>
                            <td>{{item.fechaCita}}</td>
                            <td class="text-center">
                                <button class="btn btn-sm btn-warning">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil" viewBox="0 0 16 16">
                                        <path d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5 13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z"/>
                                    </svg>
                                </button>
                                <button class="btn btn-sm btn-danger">
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
                    }
                });
        }
    },
    mounted() {
        this.obtenerData();
    }
})
</script>