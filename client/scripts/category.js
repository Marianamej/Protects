import { productos } from './Json/products.js';
import {renderizadoProductos} from './renderCardProduct.js';

const cards = document.querySelector(".lista__productos");
const texto=document.getElementById("text-buttons_category")
const page_number= document.querySelector("#number")


texto.innerText=`total products found ${productos.length}`

const avanzar = document.getElementById("avanzar");
const retroceder = document.getElementById("retroceder");
const itemsPerPage = 16;
let currentPage = 1;

//logica de paginado 
function renderPage() {
  const indexOfLastItem = productos.length - (currentPage - 1) * itemsPerPage;
  const indexOfFirstItem = productos.length - currentPage * itemsPerPage;
  const currentItems = productos.slice(indexOfFirstItem, indexOfLastItem);

  page_number.innerHTML=currentPage;
 
  if(currentItems.length == 0 ){
    Swal.fire({
      icon: 'error',
      title: 'Oops...',
      text: 'No hay mas productos por mostrar',
      footer: '<a href="">Regresar</a>'
    })
  }
  else{
    cards.append(renderizadoProductos(currentItems));
  }
  
  avanzar.disabled = indexOfLastItem >= productos.length;
  retroceder.disabled = currentPage === 1;
}

//renderizado 
document.addEventListener("DOMContentLoaded", function () {
//funcion para avanzar 
  retroceder.addEventListener("click", () => {
    if (currentPage > 1) {
      currentPage--;
      renderPage();
      window.scrollTo(0, 0);
    }
  });
//funcion para retroceder 
  avanzar.addEventListener("click", () => {
    const indexOfLastItem = currentPage * itemsPerPage;
    if (indexOfLastItem < productos.length) {
      currentPage++;
      renderPage();
      window.scrollTo(0, 0);
    }
  });

  renderPage(); 

  //logica del menu 


  const botonmenu = document.querySelector(".ejemplo");
  const menu = document.getElementById("menu");
  
  botonmenu.addEventListener("click", () => {
    if (menu.style.display === "block") {
      menu.style.display = "none";
    } else {
      menu.style.display = "block";
    }
  });
  menu.style.display = "none";

});