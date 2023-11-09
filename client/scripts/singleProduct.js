import {renderizadoProductos} from './renderCardProduct.js';
import { fetchProducts } from './requestProducts.js';
import {renderizadoComentarios,hayEstrellas} from './renderDetailProducts.js';
const templateSingleProduct = document.querySelector('#template-producto-general').content;
const especificaciones = document.querySelector(".producto-descripcion__lista-caracteristicas");
const descripcionDetallada = document.querySelector(".producto-descripcion__details-info")
//Se obtiene el id del producto del cual se van a renderizar sus comentarios
let id= localStorage.getItem('idProducto');
const idProducto = Number(localStorage.getItem("idProducto"))

// !Constantes para el renderizado de productos y comentarios
const numeroComentarios = document.querySelector('#numeroComentarios');
const seccionComentarios = document.querySelector('.producto-descripcion__comentarios');
const contenedorUltimasUnidades = document.querySelector('.ultimas-unidades');

//! Constantes para la animacion entre secciones
const barraNavegacion = document.querySelector('.producto-descripcion__options');
const contenedorSecciones = document.querySelector('.producto-descripcion__container');
const secciones = contenedorSecciones.querySelectorAll('#producto-descripcion__seccion');

//carta del productos
async function fetchCardProduct(idProducto) {
    
    try {
      const response = await fetch(`http://localhost:8090/gamertx/products/${idProducto}`);
      
      const product = await response.json();
      name(product)
      visualizacionDescripcion(product)
    } catch (error) {
      console.error(error);
    }
}
 
function name(product) {
    const singleProduct = document.querySelector('.singleProduct');
    const clone = document.importNode(templateSingleProduct, true);

    //Se verifican el numero de estrellas del producto para renderizarlas
    const estrellas = hayEstrellas(product.rating)
            
    //Se renderiza el comentario
    clone.querySelector('.img').src = product.urlsImages[0];
    clone.querySelector('.title').textContent = product.name;
    clone.querySelector('.precio').innerHTML = `$${product.price.toLocaleString()}`;
    clone.querySelector('.descripcion_single_product').textContent = product.description;
    clone.querySelector(".rating-product").innerHTML = estrellas;

    //Se crea el HTML de cada producto
    singleProduct.appendChild(clone);
}

function visualizacionDescripcion(producto) {
    let textoOriginal = producto.description;
    let parrafos = textoOriginal.split('//');
    let parrafo = `<h3>Informacion detallada </h3>`

    for (let index = 0; index < 2; index++) {
        parrafo+= `<p class="fs-xs">${parrafos[index]}</p>`
    }
    descripcionDetallada.innerHTML = parrafo
}

// Se renderizan los productos sugeridos en el HTML
fetchProducts(contenedorUltimasUnidades,1,12);

function detallesDelProducto() {
    // URL del servidor
  const url = `http://localhost:8090/gamertx/details/${idProducto}`;

  // Realizar la solicitud GET
  fetch(url)
      .then(response => {
          if (response.ok) {
              return response.json(); // Procesar la respuesta como JSON
          } else {
              throw new Error('Error al realizar la solicitud GET');
          }
      })
      .then(data => {
        console.log(data);
          creacionCaracteristicas(data.especificaciones)
          numeroComentarios.textContent = data.comentarios.length;
          seccionComentarios.appendChild(renderizadoComentarios(data.comentarios))
          console.log(data); // Hacer algo con los datos de respuesta
      })
      .catch(error => {
          console.error(error); // Manejar errores
      });
}

// Animacion entre cambio de secciones
const seccionIds = ['descripcion', 'calificacion', 'comentario'];
barraNavegacion.addEventListener('click', (e) => {
    // Verifica si el elemento clicado es un <li> y si su ID está en el array seccionIds
    if (e.target.tagName === 'LI' && seccionIds.includes(e.target.id)) {
        
        secciones.forEach(seccion => seccion.classList.remove('detail-active'));

        const index = seccionIds.indexOf(e.target.id);

        // Si se encuentra el índice, agrega la clase 'detail-active' a la sección correspondiente
        if (index !== -1) {
            secciones[index].classList.add('detail-active');
        }
    }
});

function creacionCaracteristicas(caracteristicas) {
    let items = "";
    for (let index = 0; index < 7; index++) {
        items += `<li class="fs-xs">${caracteristicas[index]}</li>`
    }
    especificaciones.innerHTML = items
}

const butonComentar = document.querySelector("#writeComment")
const textoComentario = document.querySelector("#textoComentario")

butonComentar.addEventListener("click",() => {
    if (localStorage.getItem("email")) {
        const email = localStorage.getItem("email")
        newComment(email)
    }
})


function newComment(email) {
    const url = 'http://localhost:8090/gamertx/comment/new';
    const fechaActual = new Date().toISOString().split('T')[0];
    const data = {
        email: email,
        productId:idProducto,
        text: textoComentario.value,
        date: fechaActual, //2023-10-03
        rating: 5,
        likes: 1
    };

    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
    .then(response => {
        if (!response.ok) {
            Swal.fire({
                position: 'center',
                icon: 'error',
                title: `No se ha podido publicar el comentario`,
                showConfirmButton: true,
                timer: 2000
            })
            return
        }
        Swal.fire({
            position: 'center',
            icon: 'success',
            title: `Comentario publicado satisfactoriamente`,
            showConfirmButton: false,
            timer: 2000
        })
        textoComentario.value = ""
        detallesDelProducto()
    })
    .then(result => console.log(result))
    .catch(error => console.error('Error:', error));
}


fetchCardProduct(id)  
detallesDelProducto()