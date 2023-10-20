import { productos } from "./Json/products.js";
import { renderizadoProductos } from "./renderCardProduct.js";
const productosFiltrados = [];

export function filtrarProductos(parametroFiltrado) {
    productos.filter(producto => {
        if(producto.categoria === parametroFiltrado){
            productosFiltrados.push(producto);
        }
    })
    return productosFiltrados;
}
