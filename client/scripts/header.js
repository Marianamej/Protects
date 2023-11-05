// Seleccionar elementos del DOM
const state = document.querySelector("#authorizationOptions");
const fragment = document.createDocumentFragment();
const options = document.createElement('div');
options.classList.add("header__user");

// URL del servidor y plantillas HTML
const URL = "http://localhost:8090/gamertx/register";
const userRegisterHTML = `
        <div class="icon user__icon user__icon-register shopping-car">
            <a href="./login.html">
            <img src="../assets/icons/icon-shopping-cart.svg" alt="">
            </a>
        </div>
        <div class="icon user__icon user__icon-register user_avatar">
            <a href="./login.html">
            <img src="https://unavatar.io/dribbble/omidnikrah" alt="">
            </a>
        </div>
`;
const userUnregisterHTML = `
    <div class="icon user__icon user__icon--unregister">
        <a href="./login.html">
            <img src="../assets/icons/icon-profile.svg" alt="">
        </a>
    </div>
    <ul class="user__options">
        <li><a class="user__link ft-bold" href="./login.html">Login</a></li>
        <li class="user__link ft-bold">/</li>
        <li><a class="user__link ft-bold" href="./register.html">Register</a></li>
    </ul>
`;

console.log();

// Función para actualizar las opciones de la barra de navegación
function updateNavigationOptions(html) {
    options.innerHTML = html;
    fragment.innerHTML = ''; // Limpiar fragmento antes de agregar contenido
    fragment.append(options);
    state.append(fragment);
}

// Función para verificar el estado y el token del usuario
function checkState() {
    const token = localStorage.getItem("token");

    if (!token) {
        updateNavigationOptions(userUnregisterHTML);
        return;
    }

    validateToken(token);
}

// Función para validar el token en el servidor
function validateToken(token) {
    const headers = {
        'Authorization': `Bearer ${JSON.parse(token)}`
    };

    fetch(URL, {
        method: 'GET',
        headers: headers
    })
    .then(response => {
        if (response.ok) {
            options.classList.add("header__user--register");
            updateNavigationOptions(userRegisterHTML);
        } else if (response.status === 500) {
            updateNavigationOptions(userUnregisterHTML);
            localStorage.removeItem("token");
        } else {
            localStorage.removeItem("token");
            updateNavigationOptions(userUnregisterHTML);
            throw new Error('Sesión expirada');
        }
    })
    .catch(error => {
        console.error('Error:', error);
    });
}

// Iniciar verificación del estado
checkState();
