import {separarProductosParaMostrar} from './renderCardProduct.js';
import {renderizadoProductos} from './renderCardProduct.js';
import {traerComentarios} from './renderDetailProducts.js';
import {renderizadoComentarios} from './renderDetailProducts.js';

// !Constantes para el renderizado de productos y comentarios
const numeroComentarios = document.querySelector('#numeroComentarios');
const seccionComentarios = document.querySelector('.producto-descripcion__comentarios');
const contenedorUltimasUnidades = document.querySelector('.ultimas-unidades');

//! Constantes para la animacion entre secciones
const barraNavegacion = document.querySelector('.producto-descripcion__options');
const contenedorSecciones = document.querySelector('.producto-descripcion__container');
const secciones = contenedorSecciones.querySelectorAll('#producto-descripcion__seccion');

//Se obtiene el id del producto del cual se van a renderizar sus comentarios
const idDetallesProducto = localStorage.getItem("idProducto")
const comentarios = traerComentarios(idDetallesProducto);
numeroComentarios.textContent = comentarios.length;
seccionComentarios.appendChild(renderizadoComentarios(comentarios))

// Se renderizan los productos sugeridos en el HTML
contenedorUltimasUnidades.append(renderizadoProductos(separarProductosParaMostrar(0,8)));

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