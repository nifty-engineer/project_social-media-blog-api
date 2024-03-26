package com.example.layeredstructuring.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDTO {

    private Integer comment_id;

    @NotBlank(message = "Name field should have an input")
    private String guest_name; // change to commenter_name

    @NotBlank(message = "Email field should have an input")
    private String guest_email; // change to commenter_email

    @NotBlank(message = "Comment text should have an input")
    private String comment_text;

    private Long time_posted_epoch;

    private Long time_updated_epoch;

}
