import { productos } from './products.js';

//renderizado 
document.addEventListener("DOMContentLoaded", function () {
  const cards = document.querySelector(".lista__productos");
  const texto=document.getElementById("text-buttons_category")
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

    cards.innerHTML = "";
    //creacion de las cartas 
    currentItems.forEach((producto) => {
      const nuevoDiv = document.createElement("div");
      nuevoDiv.className = "producto left";

      const img = document.createElement("img");
      //img.className = "card-img-top";
      img.src = producto.imagen;
      img.alt = "imagen";

      const producto__actions = document.createElement("div");
      producto__actions.className = "producto__actions";

      const center1 = document.createElement("div");
      center1.className = "center";

      const imagencenter1 = document.createElement("img");
      imagencenter1.src = "../assets/icons/icon-corazon.svg";
      imagencenter1.className = "center";

      const center2 = document.createElement("div");
      center2.className = "center";

      const imagencenter2 = document.createElement("img");
      imagencenter2.src = "../assets/icons/icon-stats.svg";
      imagencenter2.className = "center";

      const producto__calificacion = document.createElement("div");
      producto__calificacion.className = "producto__calificacion center";

      const stars = document.createElement("div");
      stars.className = "stars";
     //creacion de las estrellas de la carta dependiendo de la puntuacion 
      for (let i = 0; i < producto.numeroEstrellas; i++) {
        const ionIcon = document.createElement("ion-icon");
        ionIcon.className = "fs-xs star-active";
        ionIcon.name = "star";
        stars.appendChild(ionIcon);
      }

      const reviews = document.createElement("p");
      reviews.className = "reviews";
      reviews.innerText = `reviews (${producto.numeroEstrellas})`;

      const h3 = document.createElement("h3");
      h3.className = "fs-xs";
      h3.innerText = producto.nombre;

      const p = document.createElement("p");
      p.className = "tx-through fs-xs producto__descuento";
      p.innerText = producto.precioDescuento;

      const p2 = document.createElement("p");
      p2.className = "ft-bold producto__precio";
      p2.innerText = producto.precio;

      const button = document.createElement("button");
      button.className = "boton__añadir-carrito button--white";
      button.innerText = "Add al carrito";

      cards.appendChild(nuevoDiv);
      nuevoDiv.append(img,producto__actions);
      center1.appendChild(imagencenter1);
      center2.appendChild(imagencenter2);
      producto__actions.append(center1,center2);
      nuevoDiv.appendChild(producto__calificacion);
      producto__calificacion.appendChild(stars,reviews);
      nuevoDiv.append(h3,p,p2,button);
    });

    avanzar.disabled = indexOfLastItem >= productos.length;
    retroceder.disabled = currentPage === 1;
  }
//funcion para avanzar 
  retroceder.addEventListener("click", () => {
    if (currentPage > 1) {
      currentPage--;
      renderPage();
    }
  });
//funcion para retroceder 
  avanzar.addEventListener("click", () => {
    const indexOfLastItem = currentPage * itemsPerPage;
    if (indexOfLastItem < productos.length) {
      currentPage++;
      renderPage();
    }
  });

  renderPage(); 
});
