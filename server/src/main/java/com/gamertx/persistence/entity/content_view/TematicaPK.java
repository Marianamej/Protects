package com.gamertx.persistence.entity.content_view;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class TematicaPK implements Serializable {
    @Column(name = "id_post")
    private Integer idPost;
    @Column(name = "id_label")
    private Integer idLabel;
}
