export function renderBanner(banners) {
    const bannerContenedor = document.querySelector("#bannerContainer");

    
    banners.forEach(banner => {
        const bannerItem = document.createElement("article");
        bannerItem.classList.add("hero-banner","center");
        bannerItem.innerHTML = `
            <div class="hero-banner__info left">
                <p class="fs-sm hero-banner__temporada">${banner.get("temporada")}</p>
                <h3 class="hero-baner__title ft-medium">${banner.get("descuento")}</h3>
                <p class="fs-md hero-baner__descripcion">${banner.get("descripcion")}</p>
                <button class="button--red ff-secondary">Ver más</button>
            </div>
            <div class="hero-banner__img">
                <img src="${banner.get("producto")}" alt="">
            </div>`;

        bannerContenedor.appendChild(bannerItem);
    });
}