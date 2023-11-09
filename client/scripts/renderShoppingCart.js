const fragmentoProductos = new DocumentFragment();
const templateProducto = document.querySelector('#template__producto--carrito').content;
const contenedor = document.querySelector('.contenedor__carrito-compras');

export function construccionProductos(productos) {
    console.log(productos);
    productos.forEach(producto => {
        const clone = document.importNode(templateProducto, true);
        
        if(producto.urlsImages === null){
           
        }

        //Se renderiza el producto
        clone.querySelector('.producto__carrito-detalle--imagen').src = producto.urlsImages[0];
        clone.querySelector('.producto__carrito-detalle--imagen').alt = producto.name;
        clone.querySelector('.producto__carrito-detalle--descripcion').textContent = producto.description;
        clone.querySelector('.producto__carrito-detalle--precioUnitario').textContent = producto.price.toLocaleString();
        clone.querySelector('.producto__carrito-detalle--total').textContent = 1;

        //Se crea el HTML de cada producto
        fragmentoProductos.appendChild(clone);
        
    });
    return fragmentoProductos
}