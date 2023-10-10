import {productos} from './products.js';
const formulario = document.querySelector('#formulario');

const procesarDatos = (e) => {
    e.preventDefault();

    const datos = new FormData(e.target);
    const datosCompletos = Object.fromEntries(datos.entries());

    //ALERTA
    if(productos.push(datosCompletos)){
        Swal.fire({
            position: 'center',
            icon: 'success',
            title: 'Tu producto se ha añadido exitosamente',
            showConfirmButton: false,
            timer: 2000
          })
    }
    
    // Reseteo del formulario
    e.target.reset();
    console.log(productos);
}

formulario.addEventListener('submit', procesarDatos);

