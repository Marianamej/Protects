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

function creacionUsuario() {
    const resultadoValidacion = validaciones();
    if (resultadoValidacion === true) {
        const usuario = {
            email: email.value,
            password: password.value,
            username: username.value,
            role: 'CUSTOMER'
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

function errorTipado(...campos) {
    camposImplicados = [...campos]
    console.log(camposImplicados);
    return {
        error: "No es un correo valido",
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
        return errorTipado(email)
    }
    else if (passwordValue !== passwordConfirmationValue) {
        return errorTipado(password,passwordConfirmation)
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
                console.log("Usuario Creado con éxito");
            } else if (response.status === 400) {
                return response.text();
            } else {
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