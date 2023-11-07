import {fetchProductsByCategory} from './requestProducts.js';

const cards = document.querySelector(".lista__productos");
const texto= document.getElementById("text-buttons_category")
const contenedorBotonesPaginado = document.querySelector("#number")
const tituloPage = document.querySelector("h1")
const nombreCategoria = localStorage.getItem("Categoria")
const opcionesFiltrado = document.querySelector(".categoria__filtro")
const menuConFiltros = document.querySelector(".lista__opciones-filtrado")

const contexto = {
  "Laptops" : 1,
  "Monitors" : 2,
  "Desktop PC" : 3,
}

const itemsPerPage = 16;
let currentPage = 0;

tituloPage.textContent = nombreCategoria

const avanzar = document.getElementById("avanzar");
const retroceder = document.getElementById("retroceder");

avanzar.addEventListener("click", () => cambiarPagina("avanzar"));
retroceder.addEventListener("click", () => cambiarPagina("retroceder"));

async function llamadaServidor (currentPage) {
  console.log(currentPage);
  try {
    const response = await fetchProductsByCategory(contexto[nombreCategoria], cards,currentPage);
    actualizarInformacionPagina(response);
  } catch (error) {
    console.error(error);
  }
}

function actualizarInformacionPagina(response) {
  let numeroPaginas = response.pagesTotal;
  texto.innerText = `Se encontraron ${response.sizeContent} productos de  ${response.productsTotal}`
  crearBotonesPaginado(numeroPaginas)
}

function crearBotonesPaginado(numeroPaginas) {
  let botonesPaginado = ''
  for (let index = 0; index < numeroPaginas; index++) {
    botonesPaginado += `<span class="page-number " id="number" >${index+1}</span>`;
  }
  contenedorBotonesPaginado.innerHTML = botonesPaginado
}

function cambiarPagina(accion) {
  if(validaciones(accion)){
    (accion === "avanzar")? currentPage+=1 : currentPage-=1
    llamadaServidor(currentPage)
  }
}

function validaciones (accion){
  if(accion === "retroceder" && currentPage == 0){
    return false
  }else if(accion === "avanzar" && currentPage === totalPaginas-1){
    return false
  }
  return true
}

opcionesFiltrado.addEventListener("click", filtros)

function filtros() {
  console.log("Hola");
  menuConFiltros.classList.toggle("active")
}

llamadaServidor(currentPage)
