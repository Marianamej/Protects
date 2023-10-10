import { productos } from './products.js';

//renderizado 
document.addEventListener("DOMContentLoaded", function () {
  const cards = document.querySelector(".cards");
  const texto=document.getElementById("text-buttons_category")
texto.innerText=`total products found ${productos.length}`
  const avanzar = document.getElementById("avanzar");
  const retroceder = document.getElementById("retroceder");
  const itemsPerPage = 10;
  let currentPage = 1;
 
//logica de paginado 
  function renderPage() {
    const indexOfLastItem = currentPage * itemsPerPage;
    const indexOfFirstItem = indexOfLastItem - itemsPerPage;
    const currentItems = productos.slice(indexOfFirstItem, indexOfLastItem);

    cards.innerHTML = "";
    //creacion de las cartas 
    currentItems.forEach((producto) => {
      const nuevoDiv = document.createElement("div");
      nuevoDiv.className = "producto left";

      const img = document.createElement("img");
      img.className = "card-img-top";
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
      nuevoDiv.appendChild(img);
      nuevoDiv.appendChild(producto__actions);
      producto__actions.appendChild(center1);
      center1.appendChild(imagencenter1);
      producto__actions.appendChild(center2);
      center2.appendChild(imagencenter2);
      nuevoDiv.appendChild(producto__calificacion);
      producto__calificacion.appendChild(stars);
      producto__calificacion.appendChild(reviews);
      nuevoDiv.appendChild(h3);
      nuevoDiv.appendChild(p);
      nuevoDiv.appendChild(p2);
      nuevoDiv.appendChild(button);
    });

    avanzar.disabled = currentPage === 1;
    retroceder.disabled = indexOfLastItem >= productos.length;
  }
//funcion para avanzar 
  avanzar.addEventListener("click", () => {
    if (currentPage > 1) {
      currentPage--;
      renderPage();
    }
  });
//funcion para retroceder 
  retroceder.addEventListener("click", () => {
    const indexOfLastItem = currentPage * itemsPerPage;
    if (indexOfLastItem < productos.length) {
      currentPage++;
      renderPage();
    }
  });

  renderPage(); 
});
