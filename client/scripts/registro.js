const email = document.querySelector("#email");
const username = document.querySelector("#username");
const password = document.querySelector("#password");
const passwordConfirmation = document.querySelector("#confirmPassword");
const contenedorInputs = document.querySelector(".form__inputs");
const mensajeError = document.querySelector("#mensajeError");
const registerButton = document.querySelector("#registerButton");
const URL = 'http://localhost:8090/gamertx/register';

contenedorInputs.addEventListener("click", limpiarError);

registerButton.addEventListener("click", creacionUsuario);

function limpiarError(e) {
    const elemento = e.target;
    if (elemento.classList.contains("register-error")) {
        elemento.classList.remove("register-error");
        const placeholder = elemento.getAttribute("type") === "text" ? "nombre de usuario" : 
                          elemento.getAttribute("type") === "email" ? "correo electrónico" : "contraseña";
        elemento.setAttribute("placeholder", `Escribe tu ${placeholder}`);
        mensajeError.textContent = ""
    }
}

function asignarRol(email) {
    emailAdmin = "@gamertx.com"
    if(email.includes(emailAdmin)){
        return "ADMIN"
    }
    return "CUSTOMER"
}

function creacionUsuario() {
    const resultadoValidacion = validaciones();
    if (resultadoValidacion === true) {
        const usuario = {
            email: email.value,
            password: password.value,
            username: username.value,
            role: asignarRol(email.value)
        };
        peticionAlServidor(usuario);
    } else {
        if (resultadoValidacion.camposVacios) {
            resultadoValidacion.camposVacios.forEach(element => {
                actualizarCampoError(element, resultadoValidacion.error);
            });
        } else {
            const camposConError = resultadoValidacion.camposImplicados;
            camposConError.forEach(element => {
                mensajeError.textContent = resultadoValidacion.error;
                element.classList.add("register-error");
            });
        }
    }
}

function errorTipado(err,...campos) {
    camposImplicados = [...campos]
    console.log(camposImplicados);
    return {
        error: err,
        camposImplicados
    };
}

function actualizarCampoError(elemento, error) {
    elemento.setAttribute("placeholder", error);
    elemento.classList.add("register-error");
}

function esCorreoElectronico(correo) {
    const patron = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
    return patron.test(correo)
}

function validaciones() {
    const emailValue = email.value;
    const usernameValue = username.value;
    const passwordValue = password.value;
    const passwordConfirmationValue = passwordConfirmation.value;

    const hayCamposEnBlanco = (emailValue === "" || usernameValue === "" || passwordValue === "" || passwordConfirmationValue === "")

    if (hayCamposEnBlanco) {
        const camposVacios = [email, username, password, passwordConfirmation];
    
        const camposVaciosSinContenido = camposVacios.filter(element => element.value != "");
    
        camposVaciosSinContenido.forEach(element => {
            const indiceAEliminar = camposVacios.indexOf(element);
            camposVacios.splice(indiceAEliminar, 1);
        });
    
        return {
            error: "El campo no puede estar vacío",
            camposVacios,
        };
    }
    else if (!esCorreoElectronico(emailValue)){
        return errorTipado("El correo no es valido",email)
    }
    else if (passwordValue !== passwordConfirmationValue) {
        return errorTipado("Las contraseñas deben ser iguales",password,passwordConfirmation)
    }else if(passwordValue.length < 12){
        return errorTipado("La contraseña debe tener al menos 12 caracteres inclutendo 3 numeros",password)
    }
    else if (usernameValue.length < 6){
        return errorTipado("El nombre de usuario debe tener al menos 6 caracteres",username)
    }
    return true;
}

function peticionAlServidor(usuario) {
    fetch(URL, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(usuario)
    })
        .then(response => {
            if (response.status === 201) {
                crearCarrito(usuario.email)
                Swal.fire({
                    position: 'center',
                    icon: 'success',
                    title: `Cuenta creada con exito`,
                    showConfirmButton: false,
                    timer: 2000
                  })
                setTimeout(() => {
                    window.location.href = 'login.html'
                }, 2000);
            } else if (response.status === 400) {
                return response.text();
            }else if (response.status === 409) {
                Swal.fire({
                    title: 'El correo ya esta registrado',
                    text: "¿Deseas iniciar sesion?",
                    icon: 'warning',
                    showCancelButton: true,
                    confirmButtonColor: '#3085d6',
                    cancelButtonColor: '#d33',
                    confirmButtonText: 'Iniciar Sesion'
                  }).then((result) => {
                    if (result.isConfirmed) {
                        window.location.href = 'login.html'
                    }
                  })
            }
            else {
                console.log("Error inesperado: " + response.status);
            }
        })
        .then(text => {
            if (text) {
                console.log(text);
            }
        })
        .catch(error => {
            console.error("Error en la solicitud:", error);
        });
}

function crearCarrito(nombreUsuario) {
    // Datos a enviar en el cuerpo de la solicitud
    const data = {
        userEmail: nombreUsuario,
        purchaseDate: "2023-10-29T15:30:00",
        deliveryDate: "2023-10-29T16:00:00",
        total: 0,
        status: "Preparacion",
        items: []
    };

    // URL del servidor
    const url = 'http://localhost:8090/gamertx/orders/save';

    // Opciones de la solicitud
    const options = {
        method: 'POST',  // Método HTTP POST
        headers: {
            'Content-Type': 'application/json'  // Tipo de contenido en el cuerpo
        },
        body: JSON.stringify(data)  // Convertir los datos a formato JSON
    };

    // Realizar la solicitud POST
    fetch(url, options)
        .then(response => {
            if (response.ok) {
                return response.json();  // Procesar la respuesta como JSON
            } else {
                throw new Error('Error al realizar la solicitud POST');
            }
        })
        .then(data => {
            console.log(data);  // Hacer algo con los datos de respuesta
        })
        .catch(error => {
            console.error(error);  // Manejar errores
        });
}

function togglePW(){
    document.querySelector('.eye').classList.toggle('slash');
    let password = document.querySelector('#password');
    
    if(password.getAttribute('type') === 'password'){
      password.setAttribute('type', 'text');
    } else {
      password.setAttribute('type', 'password');
    }
}