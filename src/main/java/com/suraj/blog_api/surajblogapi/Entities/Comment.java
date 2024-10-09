package com.suraj.blog_api.surajblogapi.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "commentId")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer commentId;

    @NotEmpty(message = "Comment should not be blank")
    private String content;
    private Date createdDate;

    private String status;

    // @ManyToOne(fetch = FetchType.EAGER)
    // // @JsonManagedReference(value = "user-comments") // Use JsonBackReference on
    // // the child side
    // private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    // @JsonManagedReference(value = "post-comments") // Use JsonBackReference on
    // the child side
    private Post post;

}
