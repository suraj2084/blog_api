package com.suraj.blog_api.surajblogapi.Entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long c_id;
    @NotEmpty(message = "Category title must not be empty.")
    @Size(min = 3, message = "Category Title min = 3 char.")
    private String c_title;
    private String c_description;
    // // Many categories can belong to one user
    // @ManyToOne
    // @JoinColumn(name = "user_id", nullable = false)
    // private User user;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)

    private List<Post> posts = new ArrayList<>();

}
