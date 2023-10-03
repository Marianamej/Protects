export function enviarFormulario(){
    const contactForm = document.getElementById("contact-form");//
    const successModal = document.getElementById("success-modal");
    const closeModalButton = document.getElementById("close-modal");

    contactForm.addEventListener("submit", function(event) {
        event.preventDefault();

        const nombre = document.getElementById("inputContactNombre").value;
        const email = document.getElementById("inputContactEmail").value;
        const mensaje = document.getElementById("inputContacMensaje").value;

        // Comprobacion de que los campos se han llenado
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
                console.log("Aqui toy");
                successModal.style.display = "block";
            }
        })
        .catch(error => console.error(error));
    });

    closeModalButton.addEventListener("click", function() {
        successModal.style.display = "none";
    });
}