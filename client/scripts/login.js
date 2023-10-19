
const loginButton = document.querySelector('#login__button')

loginButton.addEventListener('click', (e)=>{
    e.preventDefault()
    
    const email = document.querySelector('#email').value
    const password = document.querySelector('#password').value

    const users = JSON.parse(localStorage.getItem('users')) || []

    const validUser = users.find(user => user.email === email && user.password === password)

    if(!validUser){
        return alert('Usuario y/o contraseña incorrectos!')
    }

    Swal.fire({
        position: 'center',
        icon: 'success',
        title: `Bienvenido ${validUser.name}`,
        showConfirmButton: false,
        timer: 2000
      })
    // alert(`Bienvenido ${validUser.name}`)
    localStorage.setItem('login_success', JSON.stringify(validUser))
    setTimeout(() => {
        window.location.href = 'home-auth.html'
    }, 2000);
})