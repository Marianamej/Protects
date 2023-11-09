export const fondos = ["../assets/fondo-banner-1.webp","../assets/fondo-banner-3.avif","../assets/fondo-banner-4.webp"];
const imgProducto = ["../assets/img-banner-2.webp","../assets/img-banner-3.webp"];
export const getBanners = []


function creacionBanner(imgFondo,temporada,descuento,descripcion,imgProducto) {
    const nuevoBanner = new Map();

    nuevoBanner.set("fondo",imgFondo);
    nuevoBanner.set("temporada",temporada);
    nuevoBanner.set("descuento",descuento);
    nuevoBanner.set("descripcion",descripcion);
    nuevoBanner.set("producto",imgProducto);

    getBanners.push(nuevoBanner);
}

creacionBanner(fondos[0],"Verano 2023","-20%","Sabemos como las grandes cosas funcionan a pequeña escala",imgProducto[0]);
creacionBanner(fondos[1],"Verano 2023","-15%","Descuento en todos los productos",imgProducto[1]);

