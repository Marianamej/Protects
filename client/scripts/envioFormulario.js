export function enviarFormulario(){
    const contactForm = document.getElementById("contact-form");//
    const successModal = document.getElementById("success-modal");

    contactForm.addEventListener("submit", function(event) {
        event.preventDefault();

        const nombre = document.getElementById("inputContactNombre").value;
        const email = document.getElementById("inputContactEmail").value;
        const mensaje = document.getElementById("inputContacMensaje").value;

        if (!nombre || !email || !mensaje) {
            alert("Por favor, completa todos los campos.");
            return;
        }

        const formData = new FormData(contactForm);
        fetch("https://formspree.io/f/xdorplqg", {
            method: "POST",
            body: formData,
            headers: {
                "Accept": "application/json"
            }
        })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                contactForm.style.display = "none";
                successMessage.style.display = "block";

            }
        })
        .catch(error => console.error(error));
    });

    closeModalButton.addEventListener("click", function() {
        successModal.style.display = "none";
    });

    backToFormButton.addEventListener("click", function() {
        successModal.style.display = "none";
        contactForm.style.display = "block";
    });
}





