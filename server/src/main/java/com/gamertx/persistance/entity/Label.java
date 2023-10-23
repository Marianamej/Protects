package com.gamertx.persistance.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Label {
    @Id
    @Column(name = "id_label")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLabel;
    private String nombre;

    @OneToMany(mappedBy = "label")
    private List<Tematica> tematicas;
}
