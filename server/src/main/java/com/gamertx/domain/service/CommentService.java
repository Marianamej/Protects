package com.gamertx.domain.service;

import com.gamertx.domain.dto.Comment;
import com.gamertx.persistence.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private ComentarioRepository repository;

    public List<Comment> getAll(){
        return repository.getAll();
    }

    public Comment write(Comment comment){
        return repository.write(comment);
    }

}
