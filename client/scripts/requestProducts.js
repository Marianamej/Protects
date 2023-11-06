import { renderizadoProductos } from './renderCardProduct.js';
const contenedorUltimasUnidades = document.querySelector('#productosHomeUltimasUnidades');
const contenedorFiltroRapido = document.querySelector('#filtroProductosHome');

const options = { method: 'GET' };
let productos = [];
let productosCategoria = [];

async function fetchData(url, container, start, end) {
  try {
    const response = await fetch(url, options);
    const data = await response.json();

    if (container === contenedorUltimasUnidades) {
      productos.push(...data.content);
    } else {
      productosCategoria.push(...data);
      renderAndDisplayCategoryProducts();
    }

    if (container) {
      const productosParaMostrar = productos.slice(start, end);
      container.innerHTML = '';
      container.append(renderizadoProductos(productosParaMostrar));
    }
  } catch (err) {
    console.error(err);
  }
}

export function fetchProducts(contenedor) {
  return fetchData('http://localhost:8090/gamertx/products', contenedor, 0, 8);
}

export async function fetchProductsByCategory(contexto) {
  await fetchData(`http://localhost:8090/gamertx/products/category/${contexto}`, null, 0, 0);
}

function renderAndDisplayCategoryProducts() {
  const productosHtml = renderizadoProductos(productosCategoria);
  contenedorFiltroRapido.innerHTML = '';
  contenedorFiltroRapido.append(productosHtml);
}
