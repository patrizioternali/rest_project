package com.springbootrestapi.springbootrestapi.controller;

import com.springbootrestapi.springbootrestapi.payload.PostDTO;
import com.springbootrestapi.springbootrestapi.payload.PostResponse;
import com.springbootrestapi.springbootrestapi.service.PostService;
import com.springbootrestapi.springbootrestapi.utils.AppConstans;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(value = "CRUD REST APIs for Post resources")
@RestController
@RequestMapping
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // create blog post
    @ApiOperation(value = "Create Post REST API")
    @PostMapping("/api/v1/posts")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PostDTO> createPost(@Valid @RequestBody PostDTO postDTO) {
        return new ResponseEntity<>(postService.createPost(postDTO), HttpStatus.CREATED);
    }

    // get all posts rest api
    @ApiOperation(value = "Get all Post REST API")
    @GetMapping("/api/v1/posts")
    public PostResponse getAllPost(
            @RequestParam(value = "pageNo", defaultValue = AppConstans.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstans.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstans.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstans.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ) {
        return postService.getAllPost(pageNo, pageSize, sortBy, sortDir);
    }

    // get post by id
    @ApiOperation(value = "Get Post by id REST API")
    @GetMapping(value = "/api/v1/posts/{id}", produces = "application/vnd.javaguides.v1+json")
    public ResponseEntity<PostDTO> getPostByIdV1(@PathVariable("id") Long id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @ApiOperation(value = "Update Post by id REST API")
    @PutMapping("/api/v1/posts/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PostDTO> updatePost(@Valid @RequestBody PostDTO postDTO, @PathVariable("id") Long id) {
        PostDTO updatedPostDto = postService.updatePost(postDTO, id);
        return new ResponseEntity<>(updatedPostDto, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete Post by id REST API")
    @DeleteMapping("/api/v1/posts/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deletePost(@PathVariable("id") Long id) {
        postService.deletePostById(id);
        return new ResponseEntity<>("Post entity deleted succesfully", HttpStatus.OK);
    }

}
