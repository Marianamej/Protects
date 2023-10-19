import { productos } from "./Json/products.js";

//Se crea un fragmento para evitar el reflow al renderizar los productos
const fragmentoProductos = new DocumentFragment();
let productosRenderizados;

function hayEstrellas(numeroEstrellas) {
    let totalEstrellas = 5;
    let estrellas = '';
    for (let i = 0; i < numeroEstrellas; i++) {
        estrellas += '<ion-icon class="fs-xs star-active" name="star"></ion-icon>';
        totalEstrellas--;
    }

    for (let i = 0; i < totalEstrellas; i++) {
        estrellas += '<ion-icon class="fs-xs star" name="star"></ion-icon>';
    }
    return estrellas;
}

function hayDescuento(producto,precioConDescuentoAplicado) {
    let descuentoProducto = '';

    //Verificamos si el producto tiene descuento, si es asi el contenido que se mostrara subrayado sera el precio del producto, porque tiene descuento
    if (precioConDescuentoAplicado) {
        descuentoProducto = `<p class="tx-through fs-xs producto__descuento">$${producto.precio}</p>`;
    }

    //Si no tiene descuento, se pasara una cadena vacia, porque no tiene descuento
    return descuentoProducto;
}

export function separarProductosParaMostrar(numeroProductosInicioMostrar,numeroProductosFinalMostrar) {
    const productosParaMostrar = productos.slice(numeroProductosInicioMostrar, numeroProductosFinalMostrar)
    return productosParaMostrar;
}

export function renderizadoProductos(arregloProductos) {
    arregloProductos.forEach(producto => {

        //Se verifican el numero de estrellas del producto para renderizarlas
        const estrellas = hayEstrellas(producto.numeroEstrellas)
    
        //Se verifica si el producto tiene descuento para renderizarlo
        const descuentoProducto = hayDescuento(producto,producto.precioDescuento);
    
        //Se verifica si el producto tiene descuento, si es asi el precio del producto sera el precio con descuento, si no, sera el precio normal
        if (descuentoProducto !== ''){
            producto.precio = producto.precioDescuento;
        }
        
        //Se crea el HTML de cada producto
        const productoCardElement = document.createElement('div');
        productoCardElement.classList.add('producto');
        productoCardElement.innerHTML = 
            `
            <div class="producto__main-content left">
                <img class="producto__imagen" src=${producto.imagen} alt="Esta en una imagen">
                <div class="overlay overlayA"></div>
                <div class="overlay overlayB"></div>
                <div class="producto__actions">
                    <div class="center producto__iconos-interactivos"><img class="iconos-interactivos__iconos" src="../assets/icons/icon-corazon.svg" alt=""></div>
                    <div class="center producto__iconos-interactivos"><img class="iconos-interactivos__iconos"  src="../assets/icons/icon-stats.svg" alt=""></div>
                </div>
                
                <div class="producto__calificacion center">
                    <div class="stars">
                        ${estrellas}
                    </div>
                    <p class="reviews">reviews (${producto.numeroReviews})</p>
                </div>
                <h3 class="fs-xs">${producto.nombre}</h3>
            </div>
            <div class="producto__info-compra">
                ${descuentoProducto}
                <p class="ft-bold producto__precio">$${producto.precio}</p>
                <button class="boton__añadir-carrito button--white">Add al carrito</button>
            </div>
            `;
    
        fragmentoProductos.appendChild(productoCardElement);
    });
    return productosRenderizados = fragmentoProductos;
}