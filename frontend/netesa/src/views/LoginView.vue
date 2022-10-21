<template>
    <div class="container">

        <!-- Outer Row -->
        <div class="row justify-content-center">

            <div class="col-xl-10 col-lg-12 col-md-9">

                <div class="card o-hidden border-0 shadow-lg my-5">
                    <div class="card-body p-0">
                        <!-- Nested Row within Card Body -->
                        <div class="row">
                            
                            <div class="col-lg-12">
                                <div class="p-5">
                                    <div class="text-center">
                                        <h1 class="h4 text-gray-900 mb-4">Bienvenido a NETESA!</h1>
                                    </div>
                                        <div class="form-group">
                                            <input type="email" class="form-control form-control-user" id="exampleInputEmail" aria-describedby="emailHelp" placeholder="Ingresa tu email..." v-model="user">
                                        </div>
                                        <div class="form-group">
                                            <input type="password" class="form-control form-control-user" id="exampleInputPassword" placeholder="Contraseña" v-model="pass">
                                        </div>
                                        <!-- <div class="form-group">
                                            <div class="custom-control custom-checkbox small">
                                                <input type="checkbox" class="custom-control-input" id="customCheck">
                                                <label class="custom-control-label" for="customCheck">Remember
                                                    Me</label>
                                            </div>
                                        </div> -->
                                        <button class="btn btn-primary btn-user btn-block" @click="obtenerData">Ingresar</button>                    
                                    <hr>
                                    <div class="text-center">
                                        <a class="small" href="forgot-password.html">Olvidaste contraseña?</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>

        </div>

    </div>
</template>

<script>
export default {
    data() {
        return {
            mensajeError: '',
            user: "",
            pass: ""
        }
    },
    methods: {
        async obtenerData() {
            const options = {
                method: 'POST',
                body: JSON.stringify({ email: this.user, password: this.pass }),
                headers: {
                    'Content-Type': 'application/json'
                    /* , 'Authorization': 'Bearer '+this.token */
                }
            };

            fetch('http://132.145.163.148:8080/netesa_sa/api/auth', options)
                .then(async (response) => {
                    if (!response.ok) {
                        const error = new Error(response.statusText);
                        error.json = response.json();
                        this.mensajeError = error.message;
                        throw error;
                    } else {
                        const data = await response.json();
                        console.log(data);
                        if(data.access != false){
                            localStorage.setItem("token", data.access)
                            localStorage.setItem("user", JSON.stringify(data.user))
                            this.$router.push('home')
                        }else{
                            Swal.fire({
                            icon: 'error',
                            title: 'Oops...',
                            text: data.msg,
                        })
                        }                       
                        // this.$router.push('home')
                    }
                });
        }
    },
}
</script>

<style>

</style>