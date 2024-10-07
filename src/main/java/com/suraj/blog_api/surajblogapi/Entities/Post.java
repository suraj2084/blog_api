package com.suraj.blog_api.surajblogapi.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer p_id;

    private String p_title;
    private String p_imageUrl;
    private String content;
    private Date addDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonManagedReference
    private Category category;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonManagedReference
    private User user;
}
