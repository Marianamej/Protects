import {separarProductosParaMostrar} from './renderCardProduct.js';
import {renderizadoProductos} from './renderCardProduct.js';
import {traerComentarios} from './renderDetailProducts.js';
import {renderizadoComentarios} from './renderDetailProducts.js';




 

//carta del productos
async function fetchCardProduct(idProducto) {
    
    try {
      const response = await fetch(`http://localhost:8090/gamertx/products/${idProducto}`);
      
      const product = await response.json();
      
      const singleProduct = document.querySelector('.singleProduct');
      //imagen del producto
      const img_container = document.querySelector(".img_container");
      const img = document.createElement("img");
      img.className = "img";
      img.src = product.urlsImages[0];
      
      const butosn = document.createElement('div'); 
      butosn.classList = "butosn";
      
      const buton_add = document.createElement('button');
      buton_add.innerText = "Buy";
      buton_add.className="buton_add"
      
      const buton_fav = document.createElement('img');
      buton_fav.src = "../assets/icons/icon-favorite.svg";
      buton_fav.className="butons_actions"
      
      const buton_car = document.createElement('img');
      buton_car.src = "../assets/icons/icon-carrito.svg";
      buton_car.className="butons_actions"
      
      singleProduct.appendChild(img_container);
      img_container.appendChild(img);
      img_container.appendChild(butosn);
      butosn.appendChild(buton_add);
      butosn.appendChild(buton_fav);
      butosn.appendChild(buton_car);
      
      //nombre
      const tex_container=document.querySelector(".tex_container")
      const title=document.createElement("h3")
      title.innerText=product.name
      title.className="title"
      
      
      const stars=document.createElement("div")
      for (let i = 0; i < product.rating; i++) {
          const ionIcon = document.createElement("ion-icon");
          ionIcon.className = "fs-xs star-active";
          ionIcon.name = "star";
          stars.appendChild(ionIcon);
      }
      
      

      const precio=document.createElement('p')
      precio.innerText=`$ ${product.price}`
      precio.className="precio"
      
      const stock=document.createElement("p")
      stock.innerText="Availability  : in stock"
      stock.className="stock"
      
      const descripcion=document.createElement("p")
      descripcion.className="descripcion_single_product"
      descripcion.innerText=product.description
      
      const linea_horizontal=document.createElement("div")
      linea_horizontal.className="linea-horizontal"


      
      
      const arraycolors=product.color.split(",")
      
      for (const colorCode of arraycolors) {
        const colorDiv = document.createElement("div");
        colorDiv.className = "colorProduct";
        colorDiv.style.backgroundColor= colorCode;
      
        tex_container.appendChild(colorDiv);
      }
     
      
      
      tex_container.appendChild(title)
      tex_container.appendChild(stars)
      tex_container.appendChild(precio)
      tex_container.appendChild(stock)
      tex_container.appendChild(descripcion)
      tex_container.appendChild(linea_horizontal)
      
      
      
    } catch (error) {
      console.error(error);
    }

    

  }
  let id= localStorage.getItem('idProducto');
  fetchCardProduct(id)  


  // !Constantes para el renderizado de productos y comentarios
const numeroComentarios = document.querySelector('#numeroComentarios');
const seccionComentarios = document.querySelector('.producto-descripcion__comentarios');
const contenedorUltimasUnidades = document.querySelector('.ultimas-unidades');

//! Constantes para la animacion entre secciones
const barraNavegacion = document.querySelector('.producto-descripcion__options');
const contenedorSecciones = document.querySelector('.producto-descripcion__container');
const secciones = contenedorSecciones.querySelectorAll('#producto-descripcion__seccion');

//Se obtiene el id del producto del cual se van a renderizar sus comentarios
const idDetallesProducto = localStorage.getItem("idProducto")
const comentarios = traerComentarios(idDetallesProducto);
numeroComentarios.textContent = comentarios.length;
seccionComentarios.appendChild(renderizadoComentarios(comentarios))

// Se renderizan los productos sugeridos en el HTML
contenedorUltimasUnidades.append(renderizadoProductos(separarProductosParaMostrar(0,8)));

// Animacion entre cambio de secciones
const seccionIds = ['descripcion', 'calificacion', 'comentario'];
barraNavegacion.addEventListener('click', (e) => {
    // Verifica si el elemento clicado es un <li> y si su ID está en el array seccionIds
    if (e.target.tagName === 'LI' && seccionIds.includes(e.target.id)) {
        
        secciones.forEach(seccion => seccion.classList.remove('detail-active'));

        const index = seccionIds.indexOf(e.target.id);

        // Si se encuentra el índice, agrega la clase 'detail-active' a la sección correspondiente
        if (index !== -1) {
            secciones[index].classList.add('detail-active');
        }
    }
});
