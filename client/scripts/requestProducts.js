import {renderizadoProductos} from './renderCardProduct.js';

//! Funcion para obtener todos los productos
const contenedorUltimasUnidades = document.querySelector('#productosHomeUltimasUnidades');
const contenedorFiltroRapido = document.querySelector('#filtroProductosHome');
const options = { method: 'GET' };
let productos = [];

export async function fetchProducts() {
  try {
    const response = await fetch('http://localhost:8090/gamertx/products', options);
    const data = await response.json();
    const content = data.content;

    // Agregar los nuevos productos al arreglo existente
    productos.push(...content);

    // Luego de agregar los productos, ejecutar la función para separar los productos
    const productosSeleccionados = await separarProductosParaMostrar(0, 8);
    contenedorUltimasUnidades.append(renderizadoProductos(productosSeleccionados));
  } catch (err) {
    console.error(err);
  }
}

async function separarProductosParaMostrar(numeroProductosInicioMostrar, numeroProductosFinalMostrar) {
    const productosParaMostrar = productos.slice(numeroProductosInicioMostrar, numeroProductosFinalMostrar);
    return productosParaMostrar;
}


//! Funcion para obtener los productos por categoria

let productosCategoria = []

export async function fetchProductsByCategory(contexto) {
    try {
      const response = await fetch(`http://localhost:8090/gamertx/products/category/${contexto}`, options);
      const data = await response.json();
        
      // Agregar los nuevos productos al arreglo existente
      productosCategoria.push(...data);
      let rederizadoProductosFiltrados = renderizadoProductos(productosCategoria);
      contenedorFiltroRapido.append(rederizadoProductosFiltrados);
      productosCategoria = []
    } catch (err) {
      console.error(err);
    }
  }
