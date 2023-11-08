const fragmentoProductos = new DocumentFragment();
const templateProducto = document.querySelector('#template__producto--carrito').content;
let productosRenderizados = '';
let productosEspecificos = []
const contenedor = document.querySelector('.contenedor__carrito-compras');
let url;

export async function renderizadoProductosCarrito(arregloProductos) {
    for (const producto of arregloProductos) {
        // Declarar la variable URL para cada producto
        const url = `http://localhost:8090/gamertx/products/${producto.itemId}`;

        try {
            // Realiza una solicitud GET a la URL
            const response = await fetch(url, {
                method: 'GET',
            });

            if (response.ok || response.status === 302) {
                // Maneja la respuesta si es exitosa
                const data = await response.json();
                productosEspecificos.push(data)
                // Puedes hacer algo con 'data' aquí
            } else {
                // Maneja el caso en que la solicitud no sea exitosa
                console.error(`Error en la solicitud: ${response.status}`);
            }
        } catch (err) {
            // Maneja errores de red u otros errores
            console.error(err);
        }
    }
    construccionProductos(productosEspecificos);
}

function construccionProductos(productos) {
    console.log(productos);
    productos.forEach(producto => {
        const clone = document.importNode(templateProducto, true);
        
        //Se renderiza el producto
        clone.querySelector('.producto__carrito-detalle--imagen').src = producto.urlsImages[0];
        clone.querySelector('.producto__carrito-detalle--imagen').alt = producto.name;
        clone.querySelector('.producto__carrito-detalle--descripcion').textContent = producto.description;
        clone.querySelector('.producto__carrito-detalle--precioUnitario').textContent = producto.price;
        clone.querySelector('.producto__carrito-detalle--total').textContent = 1;

        //Se crea el HTML de cada producto
        fragmentoProductos.appendChild(clone);
    });
    contenedor.append(fragmentoProductos)
}