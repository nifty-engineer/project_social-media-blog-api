package com.example.layeredstructuring.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "comment")
public class Comment {

    @Column(name = "comment_id")
    @Id
    @GeneratedValue
    private Integer comment_id;

    @Column
    @NotNull
    private String guest_name;

    @Column
    @NotNull
    private String guest_email;

    @Column
    private String comment_text;


    @Column
    private Long time_posted_epoch;

    @Column(name = "time_updated_epoch")
    private Long time_updated_epoch;

    @ManyToOne
    @JoinColumn(name = "post_id")
    @NotNull
    private Post post;

}
