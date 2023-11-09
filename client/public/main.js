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

// URL del servidor y plantillas HTML
const img=localStorage.getItem("foto")

const URL = "http://localhost:8090/gamertx/register";

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

    const email = localStorage.getItem("email")
    options.classList.add("header__user--register");
    usuario(email,token);
}

// // Función para validar el token en el servidor
// function validateToken(token) {
//     const headers = {
//         'Authorization': `Bearer ${JSON.parse(token)}`
//     };

//     fetch(URL, {
//         method: 'GET',
//         headers: headers
//     })
//     .then(response => {
//         if (response.ok) {
//             const email = localStorage.getItem("email")
            
//             usuario(email,token)
//         }else {
//             updateNavigationOptions(userUnregisterHTML,userUnregisterMenuHTML);
//             throw new Error('Sesión expirada');
//         }
//     })
//     .catch(error => {
//         console.error('Error:', error);
//         updateNavigationOptions(userUnregisterHTML,userUnregisterMenuHTML);
//     });
// }

function usuario(email,token) {
    // URL del servidor
    const url = `http://localhost:8090/gamertx/register/${email}`;

    // Opciones de la solicitud con encabezados de autorización
    const options = {
        method: 'GET',  // Método HTTP GET
        headers: {
            'Authorization': `Bearer ${JSON.parse(token)} `,  // Agregar el token de autorización
        },
    };

    // Realizar la solicitud GET
    fetch(url, options)
        .then(response => {
            if (response.ok) {
                return response.json();  // Procesar la respuesta como JSON
            } else {
                throw new Error('Error al realizar la solicitud GET');
            }
        })
        .then(data => {

            const userRegisterHTML = `
                <div class="icon user__icon user__icon-register shopping-car">
                    <a href="./carrito-compras.html">
                    <img src="../assets/icons/icon-shopping-cart.svg" alt="">
                    </a>
                </div>
                <div class="icon user__icon user__icon-register user_avatar">
                    <a href="./profile.html">
                    <img src=${data.imgProfile} alt="">
                    </a>
                </div>
            `;

            updateNavigationOptions(userRegisterHTML,userRegisterMenuHTML);
        })
        .catch(error => {
            console.error(error);  // Manejar errores
        });
}

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
    if(e.target.classList.contains('boton__añadir-carrito') && localStorage.getItem("token")){
        const producto = e.target.parentElement.parentElement;
        const idProducto = producto.dataset.id;

        const email = localStorage.getItem("email")
        añadirCarrito(email,idProducto)
    }else if((e.target.classList.contains('boton__añadir-carrito') || e.target.classList.contains('button_add')) && !localStorage.getItem("token")){
        Swal.fire({
            position: 'center',
            icon: 'error',
            title: `Debes iniciar sesion`,
            showConfirmButton: true,
            timer: 1000
        })
    }
  });

  function añadirCarrito(email,id) {
    // URL del servidor
    const url = `http://localhost:8090/gamertx/orders/add/${email}`;
    let token = localStorage.getItem("token")

    // Definir los datos a enviar en el cuerpo de la solicitud
    const data = {
        itemId: Number(id),
        quantity: 1,
        total: null
    };

    // Opciones de la solicitud
    const options = {
        method: 'PUT',  // Método HTTP PUT
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${JSON.parse(token)}`
        },
        body: JSON.stringify(data)
    };

    // Realizar la solicitud PUT
    fetch(url, options)
    .then(response => {
        if (response.ok) {
            return response.json();
        } else {
            Swal.fire({
                position: 'center',
                icon: 'success',
                title: `El producto ya se encuentra en el carrito`,
                showConfirmButton: false,
                timer: 1000
            })
        }
    })
    .then(data => {
        // console.log(data);  // Hacer algo con los datos de respuesta
    })
    .catch(error => {
        console.log(error);  // Manejar errores
    });
  }

    // Iniciar verificación del estado
    checkState();