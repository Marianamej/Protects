import { productos } from "./Json/products.js";

//Se crea un fragmento para evitar el reflow al renderizar los productos
const fragmentoProductos = new DocumentFragment();

//Se genera un nuevo array con solo los primeros 8 elementos del array de productos.
const productosUltimasUnidades = productos.slice(0, 8);

function hayEstrellas(numeroEstrellas) {
    let estrellas = '';
    for (let i = 0; i < numeroEstrellas; i++) {
        estrellas += '<ion-icon class="fs-xs star-active" name="star"></ion-icon>';
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

productosUltimasUnidades.forEach(producto => {

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
    productoCardElement.classList.add('producto', 'left');
    productoCardElement.innerHTML = 
        `<img src=${producto.imagen} alt="Esta en una imagen">
        <div class="producto__actions">
            <div class="center"><img  src="../assets/icons/icon-corazon.svg" alt=""></div>
            <div class="center"><img  src="../assets/icons/icon-stats.svg" alt=""></div>
        </div>
        
        <div class="producto__calificacion center">
            <div class="stars">
                ${estrellas}
            </div>
            <p class="reviews">reviews (${producto.numeroReviews})</p>
        </div>
        <h3 class="fs-xs">${producto.nombre}</h3>
        ${descuentoProducto}
        <p class="ft-bold producto__precio">$${producto.precio}</p>
        <button class="boton__añadir-carrito button--white">Add al carrito</button>`;

    fragmentoProductos.appendChild(productoCardElement);
});

export const productosRenderizados = fragmentoProductos;