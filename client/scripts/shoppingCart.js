import { renderizadoProductosCarrito } from './renderShoppingCart.js';
const contenedor = document.querySelector(".contenedor__carrito-compras")

  let token = localStorage.getItem("token")
  let objetosPrivados =[]

  async function fetchDataPrivate(url,container) {
    let headers = { 'Authorization': `Bearer ${JSON.parse(token)}`};
    try {
      const response = await fetch(url, {
        method: 'GET',
        headers: headers
    });
      const data = await response.json();
  
      objetosPrivados.push(...data)
      console.log(objetosPrivados[0].items);
      renderAndDisplayProductsInShoppingCard(container);
      return data
  
    } catch (err) {
      console.error(err);
    }
  }

    async function fetchProductsInShoppingCard(email,contenedor) {
    return await fetchDataPrivate(`http://localhost:8090/gamertx/orders/${email}`,contenedor);
  }

  async function renderAndDisplayProductsInShoppingCard(container) {
    const productosHtml = renderizadoProductosCarrito(objetosPrivados[0].items);
    container.innerHTML = '';
    container.append(productosHtml);
    objetosPrivados = []
  }

fetchProductsInShoppingCard("brayanandresveragar@gmail.com",contenedor)