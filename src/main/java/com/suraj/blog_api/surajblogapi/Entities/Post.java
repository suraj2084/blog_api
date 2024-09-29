package com.suraj.blog_api.surajblogapi.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int p_id;

    private String p_title;
    private String p_imageUrl;
    private String Content;
    // private Date date;

    @ManyToOne
    private Category category;

    @ManyToOne
    private User user;
}
