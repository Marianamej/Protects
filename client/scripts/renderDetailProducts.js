//Se crea un fragmento para evitar el reflow al renderizar los productos
const fragmentoComentarios = new DocumentFragment();
const templateComentario = document.querySelector('#template__comentario').content;
let comentariosRenderizados;

export function hayEstrellas(rating) {
    let totalEstrellas = 5;
    let estrellas = '';
    for (let i = 0; i < rating; i++) {
        estrellas += '<ion-icon class="fs-xs star-active" name="star"></ion-icon>';
        totalEstrellas--;
    }

    for (let i = 0; i < totalEstrellas; i++) {
        estrellas += '<ion-icon class="fs-xs star" name="star"></ion-icon>';
    }
    return estrellas;
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
            const estrellas = hayEstrellas(comentario.rating)
            
            //Se renderiza el comentario
            clone.querySelector('.img-user').src = comentario.img;
            clone.querySelector('.personal-review__user-name').textContent = comentario.email;
            clone.querySelector('.stars').innerHTML = estrellas;
            clone.querySelector('.personal-review__comentario').textContent = comentario.text;
    
            //Se crea el HTML de cada producto
            fragmentoComentarios.appendChild(clone);
        });
    }
    return comentariosRenderizados = fragmentoComentarios;
}