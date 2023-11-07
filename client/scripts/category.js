import {fetchProductsByCategory} from './requestProducts.js';

const cards = document.querySelector(".lista__productos");
const texto= document.getElementById("text-buttons_category")
const contenedorBotonesPaginado = document.querySelector("#number")
const tituloPage = document.querySelector("h1")
const nombreCategoria = localStorage.getItem("Categoria")
const opcionesFiltrado = document.querySelector(".categoria__filtro")
const menuConFiltros = document.querySelector(".lista__opciones-filtrado")
let botonesNumeroPaginas;

const contexto = {
  "Laptops" : 1,
  "Monitors" : 2,
  "Desktop PC" : 3,
}

let numeroPaginas;
const itemsPerPage = 16;
let currentPage = 0;

tituloPage.textContent = nombreCategoria

const avanzar = document.getElementById("avanzar");
const retroceder = document.getElementById("retroceder");

avanzar.addEventListener("click", () => cambiarPagina("avanzar"));
retroceder.addEventListener("click", () => cambiarPagina("retroceder"));

async function llamadaServidor (currentPage) {
  try {
    const response = await fetchProductsByCategory(contexto[nombreCategoria], cards,currentPage,itemsPerPage);
    actualizarInformacionPagina(response);
  } catch (error) {
    console.error(error);
  }
}

function actualizarInformacionPagina(response) {
  numeroPaginas = response.pagesTotal;
  texto.innerText = `Se encontraron ${response.sizeContent} productos de  ${response.productsTotal}`
  crearBotonesPaginado(numeroPaginas)
}

function crearBotonesPaginado(numeroPaginas) {
  let botonesPaginado = ''
  for (let index = 0; index < numeroPaginas; index++) {
    botonesPaginado += `<span class="page-number " id="number" >${index+1}</span>`;
  }
  contenedorBotonesPaginado.innerHTML = botonesPaginado
  estilarBotones(currentPage)
}

function estilarBotones(posicion) {
  botonesNumeroPaginas = document.querySelectorAll('.page-number');
  botonesNumeroPaginas[posicion].style.color  = "var(--watermelon)";
  botonesNumeroPaginas[posicion].style.border = "1px solid var(--watermelon)";
}

function cambiarPagina(accion) {
  if(validaciones(accion)){
    if(accion === "avanzar"){
      currentPage+=1
     }else{
      currentPage-=1
     } 
    window.scrollTo(0, 0);
    llamadaServidor(currentPage)
  }
}

function validaciones (accion){
  if(accion === "retroceder" && currentPage == 0){
    return false
  }else if(accion === "avanzar" && currentPage === numeroPaginas-1){
    return false
  }
  return true
}

function desactivarBoton(boton) {
  if (currentPage == 0 || currentPage === numeroPaginas-1) {
    boton.style.color  = "black";
    boton.style.backgroundColor  = "grey";
    boton.style.border = "transparent";
  }else{
    boton.style.color  = "var(--watermelon)";
    boton.style.backgroundColor  = "transparent";
    boton.style.border = "1px solid var(--watermelon)";
  }
}

opcionesFiltrado.addEventListener("click", filtros)

function filtros() {
  console.log("Hola");
  menuConFiltros.classList.toggle("active")
}

llamadaServidor(currentPage)
