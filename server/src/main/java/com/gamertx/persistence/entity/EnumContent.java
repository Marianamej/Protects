package com.gamertx.persistence.entity;

import java.lang.reflect.Array;
import java.util.Arrays;

public class EnumContent {
    public enum  OpcionesPago{
        BANCARIA,
        TARJETA,
        PAYPAL
    }

    public enum nombresCategoria{
        Laptop,
        Monitor,
        Desktop_PC, // Valor con espacio entre comillas dobles
        Complementos,
        Partes_PC
    }

    public enum estadoActual{
        Entregado,
        Enviado,
        Preparacion
    }

    public enum caracteristicas{
        Hardware,
        Software,
        Rendimiento,
        Almacenamiento
    }

}
