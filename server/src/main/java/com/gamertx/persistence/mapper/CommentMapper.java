package com.gamertx.persistence.mapper;

import com.gamertx.domain.dto.Comment;
import com.gamertx.persistence.entity.users_view.Comentario;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Mappings({
            @Mapping(source = "texto",target = "text"),
            @Mapping(source = "fecha",target = "date"),
            @Mapping(source = "calificacion",target = "rating"),
            @Mapping(source = "emailUsuario",target = "email"),
            @Mapping(source = "idProducto",target = "productId"),
            @Mapping(source = "usuario",target = "user")
    })
    Comment toComment (Comentario comentario);

    @InheritInverseConfiguration
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "titulo", ignore = true)
    @Mapping(target = "producto", ignore = true)
    Comentario toComentario(Comment comment);
}
