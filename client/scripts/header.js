const state = document.querySelector("#authorizationOptions")
const fragment = document.createDocumentFragment();

const options = document.createElement('div');
options.classList.add("header__user")

if(!localStorage.getItem("token")){

     const html = `
        <div class="icon user__icon user__icon--unregister">
            <a href="./login.html">
            <img src="../assets/icons/icon-profile.svg" alt="">
            </a>
        </div>
        <ul class="user__options">
            <li><a class="user__link ft-bold" href="./login.html">Login</a></li>
            <li class="user__link ft-bold">/</li>
            <li><a class="user__link ft-bold" href="./register.html">Register</a></li>
        </ul>
    `
    options.innerHTML=html
    fragment.append(options)
}else{
    const html = `
        <div class="icon user__icon user__icon-register shopping-car">
            <a href="./login.html">
            <img src="../assets/icons/icon-shopping-cart.svg" alt="">
            </a>
        </div>
        <div class="icon user__icon user__icon-register user_avatar">
            <a href="./login.html">
            <img src="https://unavatar.io/dribbble/omidnikrah" alt="">
            </a>
        </div>
    `
    options.innerHTML=html
    fragment.append(options)
}

state.append(fragment)