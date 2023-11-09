package com.gamertx.persistence.mapper;

import com.gamertx.domain.dto.Comment;
import com.gamertx.persistence.entity.users_view.Comentario;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-09T11:17:15-0500",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.3.jar, environment: Java 21 (Amazon.com Inc.)"
)
@Component
public class CommentMapperImpl implements CommentMapper {

    @Override
    public Comment toComment(Comentario comentario) {
        if ( comentario == null ) {
            return null;
        }

        Comment comment = new Comment();

        comment.setText( comentario.getTexto() );
        comment.setDate( comentario.getFecha() );
        comment.setRating( comentario.getCalificacion() );
        comment.setEmail( comentario.getEmailUsuario() );
        comment.setProductId( comentario.getIdProducto() );
        comment.setLikes( comentario.getLikes() );

        return comment;
    }

    @Override
    public List<Comment> toComments(List<Comentario> comentarios) {
        if ( comentarios == null ) {
            return null;
        }

        List<Comment> list = new ArrayList<Comment>( comentarios.size() );
        for ( Comentario comentario : comentarios ) {
            list.add( toComment( comentario ) );
        }

        return list;
    }

    @Override
    public Comentario toComentario(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        Comentario comentario = new Comentario();

        comentario.setTexto( comment.getText() );
        comentario.setFecha( comment.getDate() );
        comentario.setCalificacion( comment.getRating() );
        comentario.setEmailUsuario( comment.getEmail() );
        comentario.setIdProducto( comment.getProductId() );
        comentario.setLikes( comment.getLikes() );

        return comentario;
    }

    @Override
    public List<Comentario> toComentarios(List<Comment> comments) {
        if ( comments == null ) {
            return null;
        }

        List<Comentario> list = new ArrayList<Comentario>( comments.size() );
        for ( Comment comment : comments ) {
            list.add( toComentario( comment ) );
        }

        return list;
    }
}
