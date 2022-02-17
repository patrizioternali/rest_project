package com.springbootrestapi.springbootrestapi.service;

import com.springbootrestapi.springbootrestapi.payload.PostDTO;
import com.springbootrestapi.springbootrestapi.payload.PostResponse;

import java.util.List;

public interface PostService {
    PostDTO createPost(PostDTO postDTO);
    PostResponse getAllPost(int pageNo, int pageSize, String sortBy, String sortDir);
    PostDTO getPostById(Long id);
    PostDTO updatePost(PostDTO postDTO, Long id);
    void deletePostById(Long id);
}
