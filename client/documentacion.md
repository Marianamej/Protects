
# Tabla de Contenido
* [Construccion CSS](#manejo-del-CSS)
  * [Convenciones](#convenciones)
    * [Metodologia BEM](#metodologia-bem)
        * [Bloques](#bloques)
        * [Elementos](#elementos)
        * [Modificadores](#modificadores)
    * [Sintaxis](#sintaxis)
    * [Imagen](#imagen)
    * [Ejemplos](#ejemplos)
  * [Arquitectura CSS](#arquitectura-itcss)
    * [Subsección 2.1](#subsección-21)
    * [Subsección 2.2](#subsección-22)

# Manejo del CSS
Guia practica para construir el CSS de la aplicacion web.

## Convenciones

Para el manejo del CSS se utilizan las siguientes convenciones:

- Se utiliza la metodología BEM
- Se utiliza el preprocesador SASS
- Se utiliza la arquitectura ITCSS

### Metodologia BEM
Esta metodologia se centra en como escribir las clases del CSS, para que sean mas faciles de leer y entender. BEM significa Block Element Modifier, y se basa en la idea de que todo en el sitio web es un bloque, y que cada bloque puede contener elementos y modificadores.

#### Bloques
Los bloques son los componentes independientes de la interfaz de usuario, como encabezados, botones, formularios, etc. Los bloques pueden contener elementos y modificadores.

#### Elementos
Los elementos son las partes de un bloque que realizan una función específica. Por ejemplo, un botón puede tener elementos como un icono, un texto y un enlace, y se escriben con un doble guion bajo.
* [bloque]__[elemento]
* [bloque]__[elemento]--[modificador]


#### Modificadores
Los modificadores son las variaciones de un bloque o elemento. Por ejemplo, un botón puede tener modificadores como un botón grande, un botón pequeño, un botón rojo, etc, y se escriben con un doble guion.

⚠️Nunca uses solo modificadores en BEM

Las clases modificador siempre deben acompañar a una clase bloque o una clase elemento, no tiene sentido que aparezcan solas:

* [bloque]--[modificador]
* [elemento]--[modificador]

### Sintaxis

* [bloque]
* [bloque]__[elemento]
* [bloque]--[modificador]
* [elemento]--[modificador]
* [bloque]__[elemento]--[modificador]

### imagen
![alt text](https://i.ibb.co/448DQ5g/3.png)

### Ejemplos
Por ejemplo, aquí hay un ejemplo de cómo se vería un botón en HTML y CSS utilizando la metodología BEM:

HTML:

```html
<button class="button button--primary">
  <span class="button__icon">
    <i class="fa fa-search"></i>
  </span>
  <span class="button__text">Search</span>
</button>
```

```html
    <h1 class=”title title--size-regular”>
        Título normal
    </h1>
    <h1 class=”title title--size-big”>
        Título grande
    </h1>
```

Encuentra mas informacion en la [¿Como usar BEM en tus proyectos?](https://webpunk.dev/metodologia-bem-css/)

## Arquitectura ITCSS

* La carpeta de estilos contiene todos los archivos CSS del proyecto.
* La carpeta de configuración contiene variables como colores - tipografias - medidas. 
* La carpeta de herramientas contiene mixins y funciones.
* La carpeta genérica contiene estilos de restablecimiento y normalización. 
* La carpeta de elementos contiene estilos para elementos HTML básicos como botones, formularios, etiquetas contenedores, titulos, etc...
* La carpeta de objetos contiene patrones de diseño reutilizables como cuadrículas y objetos multimedia.
* La carpeta de componentes contiene estilos para componentes específicos de la interfaz de usuario, como encabezados y pies de página. 
* La carpeta de utilidades contiene clases de utilidades para espaciado, texto y otros estilos comunes.

El archivo main.scss es el punto de entrada para el proyecto e importa todos los demás archivos en el orden correcto según la arquitectura ITCSS.

### Herramientas

La carpeta de herramientas en la arquitectura CSS ITCSS es donde almacenaría sus mixins y funciones de Sass. Se trata de fragmentos de código reutilizables que se pueden utilizar en todo el proyecto para ayudarle a escribir CSS más eficiente y fácil de mantener.

Los mixins son una forma de definir un conjunto de reglas CSS que se pueden reutilizar en todo el proyecto. Por ejemplo, puede crear un mixin para una animación CSS3 que utilice en varios lugares de su sitio. En lugar de escribir el mismo código CSS cada vez, simplemente puede llamar al mixin y generará el CSS por usted.

Las funciones son similares a los mixins, pero le permiten realizar cálculos y otras operaciones con sus valores CSS. Por ejemplo, podría crear una función que convierta píxeles a ems o una que calcule la relación de contraste entre dos colores.

Al almacenar sus mixins y funciones en la carpeta de herramientas, puede reutilizarlos fácilmente en todo su proyecto sin tener que duplicar el código. Esto puede ayudarle a escribir CSS más eficiente y fácil de mantener.

#### Mixins vs functions

En Sass, los mixins y las funciones son formas de reutilizar código, pero tienen diferentes propósitos.

Los mixins son una forma de definir un conjunto de reglas CSS que se pueden reutilizar en todo el proyecto. Cuando incluye un mixin en su código Sass, genera las reglas CSS definidas en el mixin. Los mixins pueden aceptar argumentos, lo que le permite personalizar la salida del mixin en función de los valores que ingresa. Los mixins son útiles para definir estilos reutilizables que se pueden aplicar a diferentes elementos de su sitio.

Las funciones, por otro lado, son una forma de realizar cálculos y otras operaciones con sus valores de Sass. Cuando llamas a una función en tu código Sass, devuelve un valor que puedes usar en tu CSS. Las funciones pueden tomar argumentos, lo que le permite realizar cálculos basados ​​en los valores que ingresa. Las funciones son útiles para definir cálculos reutilizables que pueden usarse en todo su código Sass.

En resumen, los mixins se utilizan para definir estilos reutilizables, mientras que las funciones se utilizan para definir cálculos reutilizables.

#### Ejemplos

Mixins:

* Un mixin para una animación CSS3 que utiliza en varios lugares de su sitio.
* Un mixin para un sistema de grid responsivo que utilizas a lo largo de tu proyecto.
* Un mixin para un botón CSS que utiliza en varios lugares de su sitio.

```scss
@mixin animation($name) {
  @keyframes #{$name} {
    @content;
  }
  animation: #{$name} 1s;
}
```

```scss
@mixin grid($columns, $gap) {
  display: grid;
  grid-template-columns: repeat($columns, 1fr);
  grid-gap: $gap;
}
```

```scss
@mixin button($color) {
  display: inline-block;
  padding: 1rem 2rem;
  border: 1px solid $color;
  border-radius: 4px;
  background-color: $color;
  color: white;
  text-decoration: none;
  text-transform: uppercase;
  font-weight: bold;
  cursor: pointer;
}
```

Functions:

* Una función que convierte píxeles a ems, o viceversa.
* Una función que calcula la relación de contraste entre dos colores.
* Una función que genera una paleta de colores basada en un color base y un conjunto de reglas.

```scss
@function px-to-em($px, $base-font-size: 16px) {
  @return ($px / $base-font-size) * 1em;
}
```
```scss
@function contrast($color1, $color2) {
  $l1: luminance($color1) + .05;
  $l2: luminance($color2) + .05;
  @return if($l1 > $l2, $l1 / $l2, $l2 / $l1);
}
```
```scss
@function color-palette($base-color, $rules) {
  $palette: ();
  @each $rule in $rules {
    $palette: map-merge($palette, ($rule: $base-color));
    $base-color: adjust-hue($base-color, $rule);
  }
  @return $palette;
}
```

La clave es identificar áreas donde puedes reutilizar código y crear mixins y funciones que te ayuden a escribir CSS más eficiente y fácil de mantener.

### Elements

La carpeta de elementos en la arquitectura CSS de ITCSS es donde almacenaría estilos para elementos HTML básicos como botones, formularios y tipografía. Estos son estilos que se aplican directamente a elementos HTML y no son específicos de ningún componente o diseño en particular.

El propósito de la capa de elementos es establecer un estilo básico para su sitio. Al definir estilos para elementos HTML básicos, puede asegurarse de que su sitio tenga una apariencia consistente en todas las páginas. Esto puede ayudarle a escribir CSS más eficiente y fácil de mantener al reducir la cantidad de código redundante que necesita escribir.

En la capa de elementos, puede definir estilos para cosas como :
* títulos
* párrafos
* enlaces
* listas 
* elementos de formulario. 

Estos estilos deben mantenerse simples y genéricos, para que puedan ser anulados fácilmente por estilos más específicos en las capas de objetos, componentes y utilidades.


### Components 

La carpeta de componentes en la arquitectura CSS de ITCSS es donde almacenaría estilos para componentes específicos de la interfaz de usuario, como encabezados, pies de página, menús de navegación y tarjetas. Estos son estilos que son específicos de un componente o diseño en particular y no se aplican directamente a elementos HTML.

El propósito de la capa de componentes es definir componentes de UI reutilizables que se pueden usar en todo su sitio. Al definir estilos para componentes específicos, puede asegurarse de que su sitio tenga una apariencia consistente en todas las páginas, al mismo tiempo que facilita el mantenimiento y la actualización de su código.

En la capa de componentes, puede definir estilos para cosas como botones, formularios, modales y controles deslizantes. Estos estilos deberían ser más específicos que los estilos de la capa de elementos, pero aún así ser reutilizables en varias páginas y componentes.

En general, la capa de componentes es una parte importante de la arquitectura ITCSS porque le permite definir componentes de UI reutilizables que se pueden usar en todo su sitio, al mismo tiempo que garantiza la coherencia y la mantenibilidad.

### utilities 

La carpeta de utilidades en la arquitectura CSS de ITCSS es donde almacenaría las clases de utilidades para espaciado, texto y otros estilos comunes. Estos son estilos que no son específicos de ningún componente o diseño en particular, pero que se utilizan en todo el sitio para aplicar estilos comunes.

El propósito de la capa de utilidades es definir clases de utilidades reutilizables que se pueden usar en todo su sitio para aplicar estilos comunes. Al definir clases de utilidad, puedes evitar repetir los mismos estilos una y otra vez en tu CSS, lo que puede ayudarte a escribir código más eficiente y fácil de mantener.

```scss
.u-text-center {
  text-align: center;
} 
``` 

```scss
.u-text-left {
  text-align: left;
} 
``` 

```scss
.u-text-right {
  text-align: right;
}
``` 

En la capa de utilidades, puede definir clases para cosas como **espaciado, alineación del texto, color del texto y color de fondo**. Estas clases deben mantenerse simples y genéricas, para que puedan aplicarse fácilmente a cualquier elemento de su sitio.

En general, la capa de utilidades es una parte importante de la arquitectura ITCSS porque le permite definir clases de utilidades reutilizables que se pueden usar en todo su sitio para aplicar estilos comunes, al mismo tiempo que garantiza la coherencia y la mantenibilidad.

### Objects

La carpeta de objetos en la arquitectura CSS de ITCSS es donde almacenaría estilos para patrones de diseño reutilizables como cuadrículas y objetos multimedia. Estos son estilos que no son específicos de ningún componente o diseño en particular, pero que se utilizan en todo el sitio para crear patrones de diseño consistentes.

El propósito de la capa de objetos es definir patrones de diseño reutilizables que se pueden usar en todo su sitio. Al definir estilos para patrones de diseño específicos, puede asegurarse de que su sitio tenga una apariencia consistente en todas las páginas, al mismo tiempo que facilita el mantenimiento y la actualización de su código.

En la capa de objetos, puede definir estilos para elementos como cuadrículas, objetos multimedia y ayudas de diseño. Estos estilos deberían ser más específicos que los estilos de la capa de elementos, pero aún así ser reutilizables en varias páginas y componentes.

En general, la capa de objetos es una parte importante de la arquitectura ITCSS porque le permite definir patrones de diseño reutilizables que se pueden usar en todo su sitio, al mismo tiempo que garantiza coherencia y mantenibilidad.

### Generic

La carpeta genérica en la arquitectura CSS de ITCSS es donde almacenaría estilos para restablecer y normalizar estilos. Estos son estilos que se aplican a elementos HTML para garantizar que tengan estilos consistentes en diferentes navegadores y dispositivos.

El propósito de la capa genérica es establecer un estilo base consistente para su sitio. Al definir estilos para restablecer y normalizar, puede asegurarse de que su sitio se vea consistente en diferentes navegadores y dispositivos, y que comience con borrón y cuenta nueva para sus propios estilos.

En la capa genérica, puede definir estilos para cosas como restablecer márgenes y relleno, normalizar tamaños de fuente y alturas de línea y restablecer estilos predeterminados para elementos de formulario. Estos estilos deben mantenerse simples y genéricos, de modo que puedan ser anulados fácilmente por estilos más específicos en las capas de objetos, componentes y utilidades.

En general, la capa genérica es una parte importante de la arquitectura ITCSS porque establece un estilo base consistente para su sitio, al mismo tiempo que garantiza que sus propios estilos se construyan sobre una base sólida.

### Order to import

El orden recomendado para importar las capas ITCSS en el archivo main.scss es el siguiente:

Ajustes
Herramientas
Genérico
Elementos
Objetos
Componentes
Utilidades

Este orden garantiza que los estilos más genéricos se definan primero, seguidos de los estilos más específicos. También garantiza que las clases de utilidad se definan en último lugar, de modo que puedan anular cualquier otro estilo definido anteriormente en el archivo.

Es importante tener en cuenta que este es solo un orden recomendado y es posible que deba ajustarlo según los requisitos específicos de su proyecto. Sin embargo, seguir este orden puede ayudarle a escribir CSS más eficiente y fácil de mantener al garantizar que sus estilos estén organizados de manera lógica y consistente.

