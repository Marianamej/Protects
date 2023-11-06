import { renderBanner } from './renderBanner.js';
import { getBanners,fondos } from './apiBanner.js';

// Se renderizan los banners en el HTML
renderBanner(getBanners)


//!Funcionalidad del slider de banners
// Get all the articles
const banners = document.querySelectorAll('.hero-banner');
banners[0].style = `background: linear-gradient(0deg, rgba(0, 0, 0, 0.35) 0%, rgba(0, 0, 0, 0.35) 100%), url('../assets/fondo-banner-1.webp') no-repeat center/cover, lightgray 50%;`;

// Set the current article index to 0
let currentArticleIndex = 0;

// Set the interval to 5 seconds
const ONE_SECOND_IN_MS = 1000
const interval = ONE_SECOND_IN_MS * 5;

// Create a function to change the current article
function changeArticle() {
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