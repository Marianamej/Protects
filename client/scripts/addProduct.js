const formulario = document.querySelector('#formulario');

const productos = [];

const procesarDatos = (e) => {
    e.preventDefault();

    const datos = new FormData(e.target);
    const datosCompletos = JSON.stringify(Object.fromEntries(datos.entries()));

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
}

formulario.addEventListener('submit', procesarDatos);

