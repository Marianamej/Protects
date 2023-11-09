import { construccionProductos } from './renderShoppingCart.js';
const contenedor = document.querySelector(".contenedor__carrito-compras")
let email = localStorage.getItem("email")

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
      renderAndDisplayProductsInShoppingCard(container);
      return data
    } catch (err) {
      console.error(err);
    }
  }

    async function fetchProductsInShoppingCard(email,contenedor) {
      return await fetchDataPrivate(`http://localhost:8090/gamertx/orders/items/${email}`,contenedor);
  }

  async function renderAndDisplayProductsInShoppingCard(container) {
    const productosHtml = construccionProductos(objetosPrivados);
    container.innerHTML = '';
    container.append(productosHtml);
    objetosPrivados = []
  }

fetchProductsInShoppingCard(email,contenedor)