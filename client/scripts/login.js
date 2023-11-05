const loginButton = document.querySelector('#login__button')
const password = document.querySelector("#password");
const email = document.querySelector("#email");
const URL = 'http://localhost:8090/gamertx/auth/login';

loginButton.addEventListener('click', peticionAlServidor)

function peticionAlServidor() {
    const credenciales = {
        email: email.value,
        password: password.value,
    };
    fetch(URL, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(credenciales)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('La solicitud no se completó correctamente.');
        }
        return response.json();
    })
    .then(data => {
        let token = data.jwt

        Swal.fire({
            position: 'center',
            icon: 'success',
            title: `Bienvenido`,
            showConfirmButton: false,
            timer: 2000
        })

        localStorage.setItem('token', JSON.stringify(token))
        setTimeout(() => {
            window.location.href = 'home.html'
        }, 2000);

    })
    .catch(error => {
        console.error('Error:', error);
    });
}