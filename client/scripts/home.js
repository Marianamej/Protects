import { renderBanner } from './renderBanner.js';
import { getBanners,fondos } from './apiBanner.js';
import {renderizadoProductos} from './renderCardProduct.js';
import {filtrarProductos} from './filtradoProducts.js';


// Selecciono los contenedores de los productos del HTML
const contenedorUltimasUnidades = document.querySelector('#productosHomeUltimasUnidades');
const contenedorFiltroRapido = document.querySelector('#filtroProductosHome');
const filtroProductosHome = document.querySelector('#filtradoHome');
const contenidoPagina = document.querySelector('.main__container');

const options = { method: 'GET' };
let productos = [];

async function fetchData() {
  try {
    const response = await fetch('http://localhost:8090/gamertx/products', options);
    const data = await response.json();
    const content = data.content
    // Agregar los nuevos productos al arreglo existente
    productos.push(...content);
     // Luego de agregar los productos, ejecutar la función para separar los productos
    const productosSeleccionados = await separarProductosParaMostrar(0, 8);
    contenedorUltimasUnidades.append(renderizadoProductos(productosSeleccionados));
  } catch (err) {
    console.error(err);
  }
}

async function separarProductosParaMostrar(numeroProductosInicioMostrar,numeroProductosFinalMostrar) {
  const productosParaMostrar = productos.slice(numeroProductosInicioMostrar, numeroProductosFinalMostrar)
  return productosParaMostrar;
}

fetchData()

// Se renderizan los banners en el HTML
renderBanner(getBanners)

// Se renderizan los productos en el HTML


//!Funcionalidad del slider de banners
// Get all the articles
const banners = document.querySelectorAll('.hero-banner');
banners[0].style = `background: linear-gradient(0deg, rgba(0, 0, 0, 0.35) 0%, rgba(0, 0, 0, 0.35) 100%), url('../assets/fondo-banner-1.webp') no-repeat center/cover, lightgray 50%;`;

// Set the current article index to 0
let currentArticleIndex = 0;

// Set the interval to 5 seconds
const ONE_SECOND_IN_MS = 1000
const interval = ONE_SECOND_IN_MS * 5;

// Create a function to change the current article
function changeArticle() {
  // Hide the current article
  banners[currentArticleIndex].classList.remove('active');

  // Increment the current article index
  currentArticleIndex++;

  // If the current article index is greater than or equal to the number of banners, reset it to 0
  if (currentArticleIndex >= banners.length) {
    currentArticleIndex = 0;
  }

  // Show the next article
  banners[currentArticleIndex].style = `background: linear-gradient(0deg, rgba(0, 0, 0, 0.35) 0%, rgba(0, 0, 0, 0.35) 100%), url(${fondos[currentArticleIndex]}) no-repeat center/cover, lightgray 50%;`;
  banners[currentArticleIndex].classList.add('active');
}

// Call the changeArticle function every 5 seconds
setInterval(changeArticle, interval);

//!Funcionalidad del filtrado de productos
filtrarPorBoton('Laptop');

filtroProductosHome.addEventListener('click', (e) => {
  const esBoton = e.target.tagName === 'BUTTON';
  const estaSeleccionado = e.target.classList.contains('button--blue-active');

    if(esBoton && ! estaSeleccionado){
      contenedorFiltroRapido.innerHTML = "";
      filtrarPorBoton(e.target.textContent)
      cambiarEstadoBotones(e.target);
    }
})

function cambiarEstadoBotones(elemento) {
  const botones = document.querySelectorAll('.button--blue-active');
  botones.forEach(boton => boton.classList.remove('button--blue-active'));
  elemento.classList.add('button--blue-active');
}

function filtrarPorBoton(categoria){
  let productosFiltrados = filtrarProductos(categoria);
  let rederizadoProductosFiltrados = renderizadoProductos(productosFiltrados);
  contenedorFiltroRapido.append(rederizadoProductosFiltrados);
  productosFiltrados.length = 0
} 


// ! Slider de productos Filtrados

const slider = document.querySelector(".productos__slider");
const botonAtras = document.querySelector(".filtro__flechas--atras");
const botonAdelante = document.querySelector(".filtro__flechas--adelante");
let posicionSlide = 0;
const anchoProducto = document.querySelector(".producto").offsetWidth;
const anchoDePantalla = window.innerWidth;

botonAtras.addEventListener("click", () => {
  posicionSlide = Math.max(posicionSlide - (anchoDePantalla > 1407 ? 4 : 3), 0);
  updateSlider();
});

botonAdelante.addEventListener("click", () => {
  const maxSlides = slider.children.length - (anchoDePantalla > 1407 ? 4 : 3);
  posicionSlide = Math.min(posicionSlide + (anchoDePantalla > 1407 ? 4 : 3), maxSlides);
  updateSlider();
});

function updateSlider() {
  const translateX = posicionSlide * -(anchoProducto + 24); // 25% de ancho por tarjeta
  slider.style.transform = `translateX(${translateX}px)`;
}

// Inicializa el slider
updateSlider();

//! Funcionalidad ver el producto en detalle
contenidoPagina.addEventListener('click', (e) => {
  
  if(e.target.classList.contains('overlay')){
    const producto = e.target.parentElement.parentElement;
    const idProducto = producto.dataset.id;

    localStorage.setItem('idProducto', idProducto);
    window.location.href = 'single-product.html';
  }
});