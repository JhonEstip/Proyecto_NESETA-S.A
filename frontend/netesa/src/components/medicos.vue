<template>
  <div class="container w-100 mt-3 d-flex" v-for="item in resultadoConsultaId" :key="item">
    <div class="separador1 w-50" style="font-size: 1.5rem;">
        <div class="row">
            <div class="col-sm-3">Paciente:</div>
            <div class="col-sm-9 text-uppercase"><b><code>{{item.id_usuario.apellido +" "+item.id_usuario.nombre}}</code></b></div>
        </div>
        <div class="row">
            <div class="col-sm-3">Documento: </div>
            <div class="col-sm-9"><b><code>{{item.id_usuario.tipodocumento.tipoDocumento +" - "+item.id_usuario.numeroDocumento}}</code></b></div>
        </div>
        <div class="row">
            <div class="col-sm-3">Genero: </div>
            <div class="col-sm-9 text-uppercase"><b><code>{{item.id_usuario.genero.descripcionGenero}}</code></b></div>
        </div>
        <div class="row">
            <div class="col-sm-3">Ciudad: </div>
            <div class="col-sm-9 text-uppercase"><b><code>{{item.id_usuario.ciudad.ciudad}}</code></b></div>
        </div>
    </div>
    <div class="separador2 w-50">
        <div class="row">
            <div class="col-sm-12">
                <h4><code>Sintomas</code></h4>
                <textarea v-model="diagnostico" cols="30" rows="5" class="form-control">{{}}</textarea>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-12">
                <h4><code>Diagnostico</code></h4>
                <textarea v-model="registro" cols="30" rows="5" class="form-control"></textarea>
            </div>
        </div>
        <div class="row">
        <div class="col-sm-4">
            <label for="">
                <code>¿Requiere otra cita?</code>
            </label>
            <select v-model="seleccionadoCita" class="form-control form-control-sm">
                <option value="SI">SI</option>
                <option value="NO" selected>NO</option>
            </select>
        </div>
        <div class="col-sm-8 d-flex align-items-end" @click="actualizarHistoria"><button class="btn btn-sm btn-lg btn-block btn-warning">Actualizar</button></div>
        </div>
    </div>
  </div>
</template>

<script>
export default ({
    data() {
        return {
            token: localStorage.getItem("token"),
            resultadoCitaEdicion: [],
            url_data: null,
            resultadoConsultaId: [],
            diagnostico:'',
            registro:'',
            seleccionadoCita:''
        }
    },
    methods: {
        async actualizarHistoria() {
            let id_cita = this.resultadoConsultaId.sort(
                                function (a, b) {
                                    return parseFloat(b['id']) - parseFloat(a['id']);
                                }
                            )[0]['id'];
            const options = {
                method: 'PATCH',
                body: JSON.stringify(
                    {
                     siguiente_cita:"NO",
                     diagnostico:this.diagnostico,
                     registro_medico:this.registro_medico,
                    }
                ),
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer '+this.token
                }
            };

            fetch('http://localhost:8080/api/historia/'+id_cita, options)
                .then(async (response) => {
                    // console.log("respuesta",response)
                    if (!response.ok) {
                        const error = new Error(response.statusText);
                        error.json = response.json();
                        this.mensajeError = error.message;
                        throw error;
                    } else {
                        const data = await response.json();
                        console.log("actualizado",data);
                        Swal.fire(
                            'Consulta cargada',
                            'netesa s.a.s',
                            'success'
                            );
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

            fetch('http://localhost:8080/api/historia/'+this.url_data, options)
                .then(async (response) => {
                    if (!response.ok) {
                        const error = new Error(response.statusText);
                        error.json = response.json();
                        this.mensajeError = error.message;
                        throw error;
                    } else {
                        const data = await response.json();
                        //this.resultadoPersonas = data;
                    }
                });
        },
        async consultarCitaId() {
            const options = {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer '+this.token 
                }
            };

            fetch('http://localhost:8080/api/historia', options)
                .then(async (response) => {
                    if (!response.ok) {
                        const error = new Error(response.statusText);
                        error.json = response.json();
                        this.mensajeError = error.message;
                        throw error;
                    } else {
                        const data = await response.json();
                        console.log("data sin filtro",data);
                        let filtro = data.filter(c => c.id_cita.id == this.url_data);
                        this.resultadoConsultaId = filtro;
                        this.diagnostico = filtro[0]['registro_medico'];
                        this.registro = filtro[0]['diagnostico'];
                        this.seleccionadoCita = filtro[0]['siguiente_cita'];
                        //console.log("1",this.diagnostico,"2",this.registro,"3",this.seleccionadoCita);
                        //console.log("filtrado",filtro);
                    }
                });
        },
    },
    mounted(){
        this.url_data=this.$route.params.id;
        this.consultarCitaId()
    }
})
</script>