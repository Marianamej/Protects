const hamburguerMenu = document.getElementById('hamburguer-menu');
const menuMobile = document.getElementById('menu-mobile');
const closeMobileMenu = document.getElementById('mobile-menu-close');

hamburguerMenu.addEventListener('click', () => {
    menuMobile.classList.toggle('hide');
});

closeMobileMenu.addEventListener('click', () => {
    menuMobile.classList.toggle('hide');
});

// if(window.innerWidth > 768) {
//     menuMobile.classList.add('hide');
// }

// Formulario

document.addEventListener("DOMContentLoaded", function() {
    const contactForm = document.getElementById("contact-form");
    const successModal = document.getElementById("success-modal");
    const closeModalButton = document.getElementById("close-modal");

    contactForm.addEventListener("submit", function(event) {
        event.preventDefault();

        const nombre = document.getElementById("nombre").value;
        const email = document.getElementById("email").value;
        const mensaje = document.getElementById("mensaje").value;

        if (!nombre || !email || !mensaje) {
            alert("Por favor, completa todos los campos.");
            return;
        }

        const formData = new FormData(contactForm);
        fetch("https://formspree.io/f/xdorpkld", {
            method: "POST",
            body: formData,
            headers: {
                "Accept": "application/json"
            }
        })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                successModal.style.display = "block";
            }
        })
        .catch(error => console.error(error));
    });

    closeModalButton.addEventListener("click", function() {
        successModal.style.display = "none";
    });
});