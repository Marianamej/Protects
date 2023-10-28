import { productos } from "./Json/products.js";

//Se crea un fragmento para evitar el reflow al renderizar los productos
const fragmentoComentarios = new DocumentFragment();
const templateComentario = document.querySelector('#template__comentario').content;
let comentariosRenderizados;

//Funcion para traer los comentarios del objeto producto
export function traerComentarios(posicionProducto) {
    const comentarios = productos[posicionProducto - 1].comentarios;
    return comentarios;
}

//Funcion para renderizar los comentarios del objeto producto

export function renderizadoComentarios(arregloComentarios) {

    if (arregloComentarios.length == 0) {
        const div = document.createElement('div');
        div.classList.add('center');
        const p = document.createElement('p');
        p.classList.add('fs-sm',"ft-medium");

        p.textContent = "No hay comentarios para este producto";
        div.appendChild(p); // Agregamos el párrafo al div
        fragmentoComentarios.appendChild(div); // Agregamos el div al fragmento

        comentariosRenderizados = fragmentoComentarios;
    }else{
        arregloComentarios.forEach(comentario => {
            const clone = document.importNode(templateComentario, true);
    
            //Se verifican el numero de estrellas del producto para renderizarlas
        
            
            //Se renderiza el comentario
            clone.querySelector('.img-user').src = "../assets/profile-image.webp";
            clone.querySelector('.personal-review__user-name').textContent = comentario.nombre;
            clone.querySelector('.personal-review__comentario').textContent = comentario.comentario;
    
            //Se crea el HTML de cada producto
            fragmentoComentarios.appendChild(clone);
        });
    }

    return comentariosRenderizados = fragmentoComentarios;
}