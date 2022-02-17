package com.springbootrestapi.springbootrestapi.controller;

import com.springbootrestapi.springbootrestapi.payload.CommentDTO;
import com.springbootrestapi.springbootrestapi.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "CRUD REST APIs for Comment resources")
@RestController
@RequestMapping
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @ApiOperation(value = "Create comment REST API")
    @PostMapping("/api/v1/posts/{postId}/comments")
    public ResponseEntity<CommentDTO> createComment(@PathVariable("postId") Long id,
                                                    @Valid @RequestBody CommentDTO commentDTO) {
        return new ResponseEntity<>(commentService.createComment(id, commentDTO), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get comments by post id REST API")
    @GetMapping("/api/v1/posts/{postId}/comments")
    public List<CommentDTO> getCommentsByPostId(@PathVariable("postId") Long postId) {
        return commentService.getCommentsByPostId(postId);
    }

    @ApiOperation(value = "Get comment by post and comment id REST API")
    @GetMapping("/api/v1/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDTO> getCommentById(@PathVariable("postId") Long postId,
                                                     @PathVariable("commentId") Long commentId) {
        CommentDTO commentDTO = commentService.getCommentById(postId, commentId);
        return new ResponseEntity<>(commentDTO, HttpStatus.OK);
    }

    @ApiOperation(value = "Update comment by post and comment id REST API")
    @PutMapping("/api/v1/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDTO> updateComment(@Valid @RequestBody CommentDTO commentDTO,
                                                    @PathVariable("postId") Long postId,
                                                    @PathVariable("commentId") Long commentId) {
        CommentDTO updatedCommentDTO = commentService.updateComment(postId, commentId, commentDTO);
        return new ResponseEntity<>(updatedCommentDTO, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete comment by post and comment id REST API")
    @DeleteMapping("/api/v1/posts/{postId}/comments/{commentId}")
    public ResponseEntity<String> deleteCommentById(@PathVariable("postId") Long postId,
                                                    @PathVariable("commentId") Long commentId) {
        commentService.deleteCommentById(postId, commentId);
        return ResponseEntity.ok("Comment has been deleted.");
    }
}
