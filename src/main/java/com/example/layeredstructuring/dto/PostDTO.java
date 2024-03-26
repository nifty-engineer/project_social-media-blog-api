package com.example.layeredstructuring.dto;

import com.example.layeredstructuring.entity.UserAccount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {

    private Integer post_id;

    private UserAccount posted_by;

    @NotBlank(message = "Enter a valid title")
    @Length(max = 254, message = "Title is too long")
    private String post_title;

    @NotBlank(message = "Enter a valid text")
    private String post_text;

    @NotBlank(message = "Enter a valid summary")
    @Length(max = 254, message = "Summary is too long")
    private String post_summary;

    private Long time_posted_epoch;

    private Long time_updated_epoch;

    private Set<CommentDTO> comments;

}
