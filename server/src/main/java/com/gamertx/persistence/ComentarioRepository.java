package com.gamertx.persistence;

import com.gamertx.domain.dto.Comment;
import com.gamertx.domain.repository.CommentRepository;
import com.gamertx.persistence.crud.ComentarioCrudRepository;
import com.gamertx.persistence.entity.users_view.Comentario;
import com.gamertx.persistence.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ComentarioRepository implements CommentRepository {
    @Autowired
    ComentarioCrudRepository crud;
    @Autowired
    CommentMapper mapper;
    @Override
    public List<Comment> getAll() {
        List<Comentario> comentarios = (List<Comentario>) crud.findAll();
        return mapper.toComments(comentarios);
    }


    @Override
    public Optional<List<Comment>> getByProduct(int id) {
        Optional<List<Comentario>> comentariosOptional = crud.findAllByIdProducto(id);

        return comentariosOptional.map(comentarios -> comentarios.stream()
                .map(comentario -> mapper.toComment(comentario))
                .collect(Collectors.toList()));
    }

    @Override
    public List<Comment> getByRating() {
        return null;
    }

    @Override
    public List<Comment> getByDate() {
        return null;
    }

    @Override
    public Comment write(Comment comment) {
        Comentario comentario = mapper.toComentario(comment);
        return mapper.toComment(crud.save(comentario));
    }
}
