package com.springbootrestapi.springbootrestapi.payload;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@ApiModel(description = "Comment model information")
@Data
public class CommentDTO {

    @ApiModelProperty(value = "Blog comment id")
    private Long id;
    @ApiModelProperty(value = "Blog comment name")
    @NotEmpty(message = "Name must not be empty")
    private String name;
    @ApiModelProperty(value = "Blog comment email")
    @NotEmpty(message = "Email must not be empty")
    @Email(message = "Email address must be a correct format")
    private String email;
    @ApiModelProperty(value = "Blog comment body")
    @NotEmpty(message = "Body must not be empty")
    @Size(min = 10, message = "Comment body must be minimum 10 characters")
    private String body;
}
