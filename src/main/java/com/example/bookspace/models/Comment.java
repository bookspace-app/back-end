package com.example.bookspace.models;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table (name = "comments")
public class Comment {
    @Id
    @SequenceGenerator(
        name = "comment_sequence", 
        sequenceName = "comment_sequence", 
        allocationSize = 1
    )

    @GeneratedValue(
        strategy = GenerationType.SEQUENCE, 
        generator = "comment_sequence"
    )
    private Long id;

    @Column(name = "content")
    private String content;

    @Column(name = "dop")
    private LocalDateTime dop;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @ManyToMany(mappedBy = "likedComments")
    private List<User> likedBy;

    @ManyToMany(mappedBy = "dislikedComments")
    private List<User> dislikedBy;

    @ManyToOne
    @JoinColumn(name = "publication_id")
    private Publication publication;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Comment parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> answers;


    // public Comment() {
    //     this.id = 2L;
    // }

  
}
