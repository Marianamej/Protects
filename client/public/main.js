const hamburguerMenu = document.getElementById('hamburguer-menu');
const menuMobile = document.getElementById('menu-mobile');
const closeMobileMenu = document.getElementById('mobile-menu-close');

hamburguerMenu.addEventListener('click', () => {
    menuMobile.classList.toggle('hide');
});

closeMobileMenu.addEventListener('click', () => {
    menuMobile.classList.toggle('hide');
});


// document.addEventListener("DOMContentLoaded", enviarFormulario);
