document.addEventListener("DOMContentLoaded", function () {
    const emailInput = document.querySelector("#email");
    const usernameInput = document.querySelector("#username");
    const passwordInput = document.querySelector("#password");
    const confirmPasswordInput = document.querySelector("#confirmPassword");
    const successMessage = document.querySelector("#successMessage");

    const emailError = document.querySelector("#emailError");
    const usernameError = document.querySelector("#usernameError");
    const passwordError = document.querySelector("#passwordError");
    const confirmPasswordError = document.querySelector("#confirmPasswordError");

    const registerButton = document.querySelector("#registerButton");

    registerButton.addEventListener("click", function () {
        successMessage.innerHTML = "";
        emailError.innerHTML = "";
        usernameError.innerHTML = "";
        passwordError.innerHTML = "";
        confirmPasswordError.innerHTML = "";

        const email = emailInput.value;
        const username = usernameInput.value;
        const password = passwordInput.value;
        const confirmPassword = confirmPasswordInput.value;

        let fieldsValid = true;

        if (!email) {
            emailError.innerHTML = "Campo obligatorio";
            fieldsValid = false;
        }

        if (!username) {
            usernameError.innerHTML = "Campo obligatorio";
            fieldsValid = false;
        }

        if (!password) {
            passwordError.innerHTML = "Campo obligatorio";
            fieldsValid = false;
        }

        if (!confirmPassword) {
            confirmPasswordError.innerHTML = "Campo obligatorio";
            fieldsValid = false;
        }

        if (fieldsValid) {
            if (password !== confirmPassword) {
                passwordError.innerHTML = "Las contraseñas no coinciden";
                confirmPasswordError.innerHTML = "Las contraseñas no coinciden";
            } else {
                passwordError.innerHTML = "";
                confirmPasswordError.innerHTML = "";

                const Users = JSON.parse(localStorage.getItem('users')) || [];
                const isUserRegistered = Users.find(user => user.email === email);

                if (isUserRegistered) {
                    alert('El usuario ya está registrado!');
                } else {
                    Users.push({ name: username, email: email, password: password });
                    localStorage.setItem('users', JSON.stringify(Users));

                    // successMessage.innerHTML = "Registro satisfactorio. ¡Bienvenido!";
                    Swal.fire({
                        position: 'center',
                        icon: 'success',
                        title: `Usuario registrado satisfactoriamente. ¡Bienvenido!`,
                        showConfirmButton: false,
                        timer: 2000
                      })

                    emailInput.value = "";
                    usernameInput.value = "";
                    passwordInput.value = "";
                    confirmPasswordInput.value = "";

                    setTimeout(() => {
                        window.location.href = 'login.html'
                    }, 2000);
                }
            }
        }
    });
});

