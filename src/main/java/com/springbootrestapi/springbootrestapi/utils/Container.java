package com.springbootrestapi.springbootrestapi.utils;

import com.springbootrestapi.springbootrestapi.entity.Comment;
import com.springbootrestapi.springbootrestapi.entity.Post;

public class Container {

    private Post post;
    private Comment comment;

    public Container(Post post, Comment comment) {
        this.post = post;
        this.comment = comment;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }
}
