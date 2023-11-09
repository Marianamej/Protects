const hamburguerMenu = document.getElementById('hamburguer-menu');
const menuMobile = document.getElementById('menu-mobile');
const closeMobileMenu = document.getElementById('mobile-menu-close');
const state = document.querySelector("#authorizationOptions");
const steteMobile = document.querySelector("#stateMobile")
const contenidoPagina = document.querySelector('body');

const fragment = document.createDocumentFragment();
const optionsMobile = document.createElement('div')
optionsMobile.classList.add("mobile-menu__buttons", "space-between");

//Estado Autorizado | No autorizado
const options = document.createElement('div');
options.classList.add("header__user");

//Estado Autorizado Mobile | No autorizado Mobile


// URL del servidor y plantillas HTML
const img=localStorage.getItem("foto")

const URL = "http://localhost:8090/gamertx/register";
const userRegisterHTML = `
        <div class="icon user__icon user__icon-register shopping-car">
            <a href="./carrito-compras.html">
            <img src="../assets/icons/icon-shopping-cart.svg" alt="">
            </a>
        </div>
        
            <div class="icon user__icon user__icon-register user_avatar">
            <a href="./profile.html">
            <img src="${img}" alt="">
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

const userRegisterMenuHTML = `<button class="button--white">Ayuda</button>
<button class="button--red">Perfil</button>`;

const userUnregisterMenuHTML = `<button class="button--white">Ayuda</button>
<button class="button--red">Iniciar sesion</button>`;

// Función para actualizar las opciones de la barra de navegación
function updateNavigationOptions(html, htmlMobile) {
    options.innerHTML = html;
    fragment.innerHTML = ''; // Limpiar fragmento antes de agregar contenido
    fragment.append(options);
    state.append(fragment);
    optionsMobile.innerHTML = htmlMobile
    steteMobile.append(optionsMobile)
}

// Función para verificar el estado y el token del usuario
function checkState() {
    const token = localStorage.getItem("token");
    if (!token) {
        updateNavigationOptions(userUnregisterHTML,userUnregisterMenuHTML);
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
            updateNavigationOptions(userRegisterHTML,userRegisterMenuHTML);
        } else if (response.status === 500) {
            updateNavigationOptions(userUnregisterHTML,userUnregisterMenuHTML);
        } else {
            updateNavigationOptions(userUnregisterHTML,userUnregisterMenuHTML);
            throw new Error('Sesión expirada');
        }
    })
    .catch(error => {
        console.error('Error:', error);
    });
}

// Iniciar verificación del estado
checkState();

//info Funcionalidad del menu en version mobile
hamburguerMenu.addEventListener('click', () => {
    menuMobile.classList.toggle('hide');
});

closeMobileMenu.addEventListener('click', () => {
    menuMobile.classList.toggle('hide');
});

// Funcionalidad ver el producto en detalle
contenidoPagina.addEventListener('click', (e) => {
    if (e.target.classList.contains('overlay')) {
      const producto = e.target.parentElement.parentElement;
      const idProducto = producto.dataset.id;
  
      localStorage.setItem('idProducto', idProducto);
      window.location.href = 'single-product.html';

    }else if(e.target.classList.contains('category-id')){
        console.log("Click");
        const categoria = e.target.outerText;
        localStorage.setItem('Categoria', categoria);
    }
  });