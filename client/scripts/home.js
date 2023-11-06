import { fetchProducts, fetchProductsByCategory } from './requestProducts.js';

// Seleccionar elementos del DOM
const contenedorFiltroRapido = document.querySelector('#filtroProductosHome');
const filtroProductosHome = document.querySelector('#filtradoHome');
const contenidoPagina = document.querySelector('.main__container');
const slider = document.querySelector(".productos__slider");
const botonAtras = document.querySelector(".filtro__flechas--atras");
const botonAdelante = document.querySelector(".filtro__flechas--adelante");

// Variables y constantes
let posicionSlide = 0;
let contexto = 1;
const anchoDePantalla = window.innerWidth;
const productosContext = {};

// Renderizar productos
async function renderProducts() {
  await fetchProducts();
  await renderFilteredProducts();
}

// Renderizar productos filtrados
async function renderFilteredProducts() {
  await fetchProductsByCategory(contexto);
  sliderProductosFiltrados();
}

// Función para cambiar el contexto
function cambiarContexto(context) {
  contexto = context;
  renderFilteredProducts();
}

// Funcionalidad del filtrado con botones
filtroProductosHome.addEventListener('click', (e) => {
  const esBoton = e.target.tagName === 'BUTTON';
  const estaSeleccionado = e.target.classList.contains('button--blue-active');

  if (esBoton && !estaSeleccionado) {
    contenedorFiltroRapido.innerHTML = "";
    const selectedContext = e.target.textContent;
    if (selectedContext in productosContext) {
      contexto = productosContext[selectedContext];
      renderFilteredProducts();
      cambiarEstadoBotones(e.target);
    }
  }
});

function cambiarEstadoBotones(elemento) {
  const botones = document.querySelectorAll('.button--blue-active');
  botones.forEach(boton => boton.classList.remove('button--blue-active'));
  elemento.classList.add('button--blue-active');
}

// Slider de productos filtrados
function sliderProductosFiltrados() {
  const anchoProducto = document.querySelector(".producto").offsetWidth;
  const maxSlides = slider.children.length - (anchoDePantalla > 1407 ? 4 : 3);

  botonAtras.addEventListener("click", () => {
    posicionSlide = Math.max(posicionSlide - (anchoDePantalla > 1407 ? 4 : 3), 0);
    updateSlider();
  });

  botonAdelante.addEventListener("click", () => {
    posicionSlide = Math.min(posicionSlide + (anchoDePantalla > 1407 ? 4 : 3), maxSlides);
    updateSlider();
  });

  function updateSlider() {
    const translateX = posicionSlide * -(anchoProducto + 24); // 25% de ancho por tarjeta
    slider.style.transform = `translateX(${translateX}px)`;
  }

  // Inicializa el slider
  updateSlider();
}

// Funcionalidad para ver el producto en detalle
contenidoPagina.addEventListener('click', (e) => {
  if (e.target.classList.contains('overlay')) {
    const producto = e.target.parentElement.parentElement;
    const idProducto = producto.dataset.id;
    localStorage.setItem('idProducto', idProducto);
    window.location.href = 'single-product.html';
  }
});

// Mapea los nombres de contextos a valores
productosContext["Laptop"] = 1;
productosContext["Monitor"] = 2;
productosContext["PC"] = 3;

// Inicializar la página
renderProducts();
