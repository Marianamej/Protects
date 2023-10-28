package com.gamertx.persistence.entity.content_view;

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

    @OneToMany(mappedBy = "label",fetch=FetchType.EAGER)
    private List<Tematica> tematicas;
}
