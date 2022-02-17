package com.springbootrestapi.springbootrestapi.service.impl;

import com.springbootrestapi.springbootrestapi.entity.Comment;
import com.springbootrestapi.springbootrestapi.entity.Post;
import com.springbootrestapi.springbootrestapi.exception.BlogAPIException;
import com.springbootrestapi.springbootrestapi.exception.ResourceNotFoundException;
import com.springbootrestapi.springbootrestapi.payload.CommentDTO;
import com.springbootrestapi.springbootrestapi.repository.CommentRepository;
import com.springbootrestapi.springbootrestapi.repository.PostRepository;
import com.springbootrestapi.springbootrestapi.service.CommentService;
import com.springbootrestapi.springbootrestapi.utils.Container;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CommentDTO createComment(Long id, CommentDTO commentDTO) {
        Comment comment = mapToEntity(commentDTO);
        Post post = retrievePostObject(id);

        comment.setPost(post);
        Comment newComment = commentRepository.save(comment);

        return mapToDTO(newComment);
    }

    @Override
    public List<CommentDTO> getCommentsByPostId(Long postId) {
        List<Comment> commentList = commentRepository.findByPostId(postId);
        return commentList.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public CommentDTO getCommentById(Long postId, Long commentId) {
        Container container = checkCommentIsValid(postId, commentId);
        return mapToDTO(container.getComment());
    }

    @Override
    public CommentDTO updateComment(Long postId, Long commentId, CommentDTO commentDTO) {
        Container container = checkCommentIsValid(postId, commentId);
        container.getComment().setName(commentDTO.getName());
        container.getComment().setBody(commentDTO.getBody());
        container.getComment().setEmail(commentDTO.getEmail());

        Comment comment = commentRepository.save(container.getComment());

        return mapToDTO(comment);
    }

    @Override
    public void deleteCommentById(Long postId, Long commentId) {
        Container container = checkCommentIsValid(postId, commentId);
        commentRepository.delete(container.getComment());
    }

    private Post retrievePostObject(Long postId) {
        return postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", postId));
    }

    private Comment retrieveCommentObject(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException("Comment", "id", commentId));
    }

    private Container checkCommentIsValid(Long postId, Long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", postId));

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException("Comment", "id", commentId));

        if (!comment.getPost().getId().equals(post.getId())) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        } else {
            return new Container(post, comment);
        }
    }

    private CommentDTO mapToDTO(Comment comment) {
        return modelMapper.map(comment, CommentDTO.class);
    }

    private Comment mapToEntity(CommentDTO commentDTO) {
        return modelMapper.map(commentDTO, Comment.class);
    }
}
