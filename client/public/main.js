
import { enviarFormulario } from "../scripts/envioFormulario.js"
import { renderBanner } from '../renderBanner.js';
import { getBanners,fondos } from '../apiBanner.js';


const hamburguerMenu = document.getElementById('hamburguer-menu');
const menuMobile = document.getElementById('menu-mobile');
const closeMobileMenu = document.getElementById('mobile-menu-close');

hamburguerMenu.addEventListener('click', () => {
    menuMobile.classList.toggle('hide');
});

closeMobileMenu.addEventListener('click', () => {
    menuMobile.classList.toggle('hide');
});


document.addEventListener("DOMContentLoaded", enviarFormulario);

renderBanner(getBanners)

// Get all the articles
const banners = document.querySelectorAll('.hero-banner');

// Set the current article index to 0
let currentArticleIndex = 0;

// Set the interval to 5 seconds
const ONE_SECOND_IN_MS = 1000
const interval = ONE_SECOND_IN_MS * 5;

// Create a function to change the current article
function changeArticle() {
  console.log("Hola");
  // Hide the current article
  banners[currentArticleIndex].classList.remove('active');

  // Increment the current article index
  currentArticleIndex++;

  // If the current article index is greater than or equal to the number of banners, reset it to 0
  if (currentArticleIndex >= banners.length) {
    currentArticleIndex = 0;
  }

  // Show the next article
  banners[currentArticleIndex].style = `background: linear-gradient(0deg, rgba(0, 0, 0, 0.35) 0%, rgba(0, 0, 0, 0.35) 100%), url(${fondos[currentArticleIndex]}) no-repeat center/cover, lightgray 50%;`;
  banners[currentArticleIndex].classList.add('active');
}

// Call the changeArticle function every 5 seconds
setInterval(changeArticle, interval);
