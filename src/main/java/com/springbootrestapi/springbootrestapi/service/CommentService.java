package com.springbootrestapi.springbootrestapi.service;

import com.springbootrestapi.springbootrestapi.payload.CommentDTO;

import java.util.List;

public interface CommentService {
    CommentDTO createComment(Long id, CommentDTO commentDTO);
    List<CommentDTO> getCommentsByPostId(Long postId);
    CommentDTO getCommentById(Long postId, Long commentId);
    CommentDTO updateComment(Long postId, Long commentId, CommentDTO commentDTO);
    void deleteCommentById(Long postId, Long commentId);
}
