import { renderizadoProductos } from './renderCardProduct.js';
const contenedorUltimasUnidades = document.querySelector('#productosHomeUltimasUnidades');

const options = { method: 'GET' };
let productos = [];
let productosCategoria = [];

async function fetchData(url, container) {
  try {
    const response = await fetch(url, options);
    const data = await response.json();

    if (container === contenedorUltimasUnidades ) {
      productos.push(...data.content);
      container.innerHTML = '';
      container.append(renderizadoProductos(productos));
    } else {
      productosCategoria.push(...data.content);
      renderAndDisplayCategoryProducts(container);
    }

    return data
  } catch (err) {
    console.error(err);
  }
}

export function fetchProducts(contenedor,pageNo=0,size=12) {
  return fetchData(`http://localhost:8090/gamertx/products?pageNo=${pageNo}&size=${size}`, contenedor, 0, 8);
}

export async function fetchProductsByCategory(contexto,contenedor,pageNo=0,size=12) {
  return await fetchData(`http://localhost:8090/gamertx/products/category/${contexto}?pageNo=${pageNo}&size=${size}`, contenedor, 0, 0);
}

function renderAndDisplayCategoryProducts(container) {
  const productosHtml = renderizadoProductos(productosCategoria);
  container.innerHTML = '';
  container.append(productosHtml);
  productosCategoria = []
}