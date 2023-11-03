package com.gamertx.domain.repository;

import com.gamertx.domain.dto.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {
    List<Comment> getAll();
    Optional<List<Comment>> getByProduct(int id);
    List<Comment> getByRating();
    List<Comment> getByDate();
    Comment postComment(Comment comment);
}
