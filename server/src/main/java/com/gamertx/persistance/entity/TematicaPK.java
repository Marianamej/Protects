package com.gamertx.persistance.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class TematicaPK implements Serializable {
    @Column(name = "id_post")
    private Integer idPost;
    @Column(name = "id_label")
    private Integer idLabel;
}
