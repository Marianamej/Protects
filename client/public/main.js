const hamburguerMenu = document.getElementById('hamburguer-menu');
const menuMobile = document.getElementById('menu-mobile');
const closeMobileMenu = document.getElementById('mobile-menu-close');

hamburguerMenu.addEventListener('click', () => {
    menuMobile.classList.toggle('hide');
});

closeMobileMenu.addEventListener('click', () => {
    menuMobile.classList.toggle('hide');
});


// Get all the articles
const articles = document.querySelectorAll('.hero-banner');
const imagenesSlider = ["fondo-banner.png","fondo-banner-2.jpg", "fondo-banner-3.avif"]

// Set the current article index to 0
let currentArticleIndex = 0;

// Set the interval to 5 seconds
const ONE_SECOND_IN_MS = 1000
const interval = ONE_SECOND_IN_MS * 5;

// Create a function to change the current article
function changeArticle() {
  console.log("Hola");
  // Hide the current article
  articles[currentArticleIndex].classList.remove('active');

  // Increment the current article index
  currentArticleIndex++;

  // If the current article index is greater than or equal to the number of articles, reset it to 0
  if (currentArticleIndex >= articles.length) {
    currentArticleIndex = 0;
  }

  // Show the new current article
  articles[currentArticleIndex].style.background = `linear-gradient(0deg, rgba(0, 0, 0, 0.35) 0%, rgba(0, 0, 0, 0.35) 100%), url('../assets/${imagenesSlider[currentArticleIndex]}'), lightgray 50%;`
  articles[currentArticleIndex].classList.add('active');
}

// Call the changeArticle function every 5 seconds
setInterval(changeArticle, interval);