package com.mipt.reddit.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "posts")
@Data
@Builder
public class Post {
    @ManyToOne
    User owner;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String title;
    String content;
}
