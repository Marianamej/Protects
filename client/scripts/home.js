import {fetchProducts,fetchProductsByCategory} from './requestProducts.js'


// Selecciono los contenedores de los productos del HTML
const contenedorFiltroRapido = document.querySelector('#filtroProductosHome');
const filtroProductosHome = document.querySelector('#filtradoHome');
const contenidoPagina = document.querySelector('.main__container');
const slider = document.querySelector(".productos__slider");
const botonAtras = document.querySelector(".filtro__flechas--atras");
const botonAdelante = document.querySelector(".filtro__flechas--adelante");
let posicionSlide = 0;
let contexto = 1;
const anchoDePantalla = window.innerWidth;

//Renderizado de Last Units
fetchProducts();

// Llama a la función para obtener los productos de la categoría
fetchProductsByCategory(contexto).then(() => sliderProductosFiltrados())



// //!Funcionalidad del filtrado con botones

filtroProductosHome.addEventListener('click', (e) => {
  const esBoton = e.target.tagName === 'BUTTON';
  const estaSeleccionado = e.target.classList.contains('button--blue-active');

    if(esBoton && ! estaSeleccionado){
      contenedorFiltroRapido.innerHTML = "";

      if(e.target.textContent === "Laptop"){
        contexto = 1
      }else if (e.target.textContent === "Monitor"){
        contexto = 2
      }else{
        contexto = 3
      }

      fetchProductsByCategory(contexto)
      cambiarEstadoBotones(e.target);
    }
})

function cambiarEstadoBotones(elemento) {
  const botones = document.querySelectorAll('.button--blue-active');
  botones.forEach(boton => boton.classList.remove('button--blue-active'));
  elemento.classList.add('button--blue-active');
}


// ! Slider de productos Filtrados

function sliderProductosFiltrados() {
  let anchoProducto = document.querySelector(".producto").offsetWidth;

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
}



//! Funcionalidad ver el producto en detalle
contenidoPagina.addEventListener('click', (e) => {
  
  if(e.target.classList.contains('overlay')){
    const producto = e.target.parentElement.parentElement;
    const idProducto = producto.dataset.id;

    localStorage.setItem('idProducto', idProducto);
    window.location.href = 'single-product.html';
  }
});