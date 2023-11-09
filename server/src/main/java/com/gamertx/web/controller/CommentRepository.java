package com.gamertx.web.controller;

import com.gamertx.domain.dto.Comment;
import com.gamertx.domain.dto.Details;
import com.gamertx.domain.service.CommentService;
import com.gamertx.domain.service.DetailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/comment")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class CommentRepository {
    @Autowired
    private CommentService service;
    @GetMapping()
    public ResponseEntity<List<Comment>> getAll(){
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<Comment> write(@Valid @RequestBody Comment comment){
        return new ResponseEntity<>(service.write(comment), HttpStatus.OK);
    }
}
