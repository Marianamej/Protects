
const fragmentoProductos = new DocumentFragment();
const templateProducto = document.querySelector('#template__producto').content;
let productosRenderizados = '';

function hayEstrellas(rating) {
    let totalEstrellas = 5;
    let estrellas = '';
    for (let i = 0; i < rating; i++) {
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
    if (precioConDescuentoAplicado === 4) {       
        descuentoProducto = producto.price;
        producto.price *= 0.7; // Reducción del 30% (precio con descuento)
    }else if(precioConDescuentoAplicado === 2){
        descuentoProducto = producto.price;
        producto.price *= 0.8; // Reducción del 20% (precio con descuento)
    }else if(precioConDescuentoAplicado === 3){
        descuentoProducto = producto.price;
        producto.price *= 0.9; // Reducción del 10% (precio con descuento)
    }
    producto.price = producto.price.toLocaleString()
    //Si no tiene descuento, se pasara una cadena vacia, porque no tiene descuento
    return descuentoProducto.toLocaleString();
}

export function separarProductosParaMostrar(numeroProductosInicioMostrar,numeroProductosFinalMostrar) {
    const productosParaMostrar = productos.slice(numeroProductosInicioMostrar, numeroProductosFinalMostrar)
    
    return productosParaMostrar;
   
}

export function renderizadoProductos(arregloProductos) {
    arregloProductos.forEach(producto => {

        const clone = document.importNode(templateProducto, true);
        let imagen;

        //Se verifican el numero de estrellas del producto para renderizarlas
        const estrellas = hayEstrellas(producto.rating)
    
        //Se verifica si el producto tiene descuento para renderizarlo
        const descuentoProducto = hayDescuento(producto,producto.offerId);
    
        //Se verifica si el producto tiene descuento, si es asi el precio del producto sera el precio con descuento, si no, sera el precio normal
        if (descuentoProducto !== ''){
            producto.precio = producto.precioDescuento;
        }

        if( producto.urlsImages === null){
            imagen = "undefined"
        }else{
            imagen = producto.urlsImages[0];
        }
        
        //Se renderiza el producto
        clone.querySelector('.producto').setAttribute("data-id",producto.productId);
        clone.querySelector('.producto__imagen').src = imagen;
        clone.querySelector('.producto__imagen').alt = producto.name;
        clone.querySelector('.producto__imagen').setAttribute("title",producto.name)
        clone.querySelector('.stars').innerHTML = estrellas;
        clone.querySelector('.reviews').textContent = `reviews (${producto.numeroComentarios})`;
        clone.querySelector('.producto__nombre').textContent = producto.name;
        clone.querySelector('.producto__descuento').textContent = descuentoProducto;
        clone.querySelector('.producto__precio').textContent = `$${producto.price}`;

        //Se crea el HTML de cada producto
        fragmentoProductos.appendChild(clone);
    });
    return productosRenderizados = fragmentoProductos;
}