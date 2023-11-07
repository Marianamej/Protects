package com.gamertx.persistence.entity.products_view;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gamertx.persistence.entity.EnumContent;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "caracteristicas")
public class Caracteristica {
    @Id
    @Column(name = "id_caracteristica")
    private int id;

    @Enumerated(EnumType.STRING)
    private EnumContent.caracteristicas tipo;

    private String descripcion;

    private Short orden;

    @JsonIgnore
    @OneToMany(mappedBy = "caracteristica",fetch=FetchType.EAGER)
    private List<Especificacion> especificacions;
}
