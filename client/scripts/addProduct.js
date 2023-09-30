const formulario = document.querySelector('#formulario');

// boton.addEventListener('click', (e) => {
//     alert("Producto agregado con exito")
//     e.preventDefault();

//     const datos = new FormData(formulario);

//     // Este metodo lo que hace es transformar una lista de pares clave valor en un objeto, estos pares clave valor se obtienen de un objeto FormData que saca los datos del input del formulario
//     const datosCompletos = Object.fromEntries(datos.entries());

//     console.log(datosCompletos);
// });

const procesarDatos = (e) => {
    e.preventDefault();

    const datos = new FormData(e.target);
    const datosCompletos = JSON.stringify(Object.fromEntries(datos.entries()));

    // Create a new Blob object with the JSON data
    const blob = new Blob([datosCompletos], { type: 'application/json' });
    // Save the Blob object to a file using FileSaver.js
    saveAs(blob, 'data.json');
    
    console.log(datosCompletos);
}

formulario.addEventListener('submit', procesarDatos);

