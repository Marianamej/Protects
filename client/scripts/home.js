import { renderBanner } from './renderBanner.js';
import { getBanners,fondos } from './apiBanner.js';
import {separarProductosParaMostrar} from './renderCardProduct.js';
import {renderizadoProductos} from './renderCardProduct.js';
import {filtrarProductos} from './filtradoProducts.js';


// Selecciono los contenedores de los productos del HTML
const contenedorUltimasUnidades = document.querySelector('#productosHomeUltimasUnidades');
const contenedorFiltroRapido = document.querySelector('#filtroProductosHome');
const filtroProductosHome = document.querySelector('#filtradoHome');


// Se renderizan los banners en el HTML
renderBanner(getBanners)

// Se renderizan los productos en el HTML
contenedorUltimasUnidades.append(renderizadoProductos(separarProductosParaMostrar(0,8)));


//!Funcionalidad del slider de banners
// Get all the articles
const banners = document.querySelectorAll('.hero-banner');
banners[0].style = `background: linear-gradient(0deg, rgba(0, 0, 0, 0.35) 0%, rgba(0, 0, 0, 0.35) 100%), url('../assets/fondo-banner-1.jpg') no-repeat center/cover, lightgray 50%;`;

// Set the current article index to 0
let currentArticleIndex = 0;

// Set the interval to 5 seconds
const ONE_SECOND_IN_MS = 1000
const interval = ONE_SECOND_IN_MS * 5;

// Create a function to change the current article
function changeArticle() {
  console.log("Hola");
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
    filtrarPorBoton(e.target.textContent)
})

function filtrarPorBoton(categoria){
  let productosFiltrados = filtrarProductos(categoria);
  console.log(productosFiltrados);
  let rederizadoProductosFiltrados = renderizadoProductos(productosFiltrados);
  contenedorFiltroRapido.append(rederizadoProductosFiltrados);
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