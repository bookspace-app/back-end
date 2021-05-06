package com.example.bookspace.models;

<<<<<<< HEAD
import java.time.LocalDate;
=======
import java.time.LocalDateTime;
>>>>>>> development
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

<<<<<<< HEAD
import com.example.bookspace.Inputs.CommentInput;
=======

>>>>>>> development

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

<<<<<<< HEAD
    @Column(name = "content", nullable = false)
=======
    @Column(name = "content", nullable = false, length = 5000)
>>>>>>> development
    private String content;

    @Column(name = "dop", nullable = false)
    private LocalDateTime dop = LocalDateTime.now();

    @Column(name = "likes")
    private Long likes;


    @ManyToOne
<<<<<<< HEAD
    @JoinColumn(name = "author_id", nullable = false)
=======
    @JoinColumn(name = "authorId", nullable =  false)
>>>>>>> development
    private User author;

    @ManyToMany(mappedBy = "likedComments")
    private List<User> likedBy = new ArrayList<>();

    @ManyToMany(mappedBy = "dislikedComments")
    private List<User> dislikedBy = new ArrayList<>();

    @ManyToOne
<<<<<<< HEAD
    @JoinColumn(name = "publication_id", nullable = false)
=======
    @JoinColumn(name = "publicationId", nullable = false)
>>>>>>> development
    private Publication publication;

    @ManyToOne
    @JoinColumn(name = "parentId", nullable = true)
    private Comment parent = null;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> replies = new ArrayList<>();

    @ManyToMany
    @JoinTable (
        name = "commentMentions", 
        joinColumns = @JoinColumn(name = "commentId"),
        inverseJoinColumns = @JoinColumn(name = "userId")
    )
    private List<User> commentMentions = new ArrayList<>();


    public Comment() {

    }

    public Comment(String content, User author, Publication publication) {
        this.content = content;
        this.author = author;
        this.publication = publication;
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getDop() {
        return this.dop;
    }

    public void setDop(LocalDateTime dop) {
        this.dop = dop;
    }

<<<<<<< HEAD
    public Comment(String content, User author, Publication publication) {
        this.content = content;
        this.author = author;
        this.publication = publication;
        this.dop = LocalDate.now();
        this.likes = 0L;
        this.votedBy = new ArrayList<>();
        this.answers = new ArrayList<>();
    }

    public Comment(String content, User author, Publication publication, Comment parent) {
        this.content = content;
        this.author = author;
        this.publication = publication;
        this.parent = parent;
        this.dop = LocalDate.now();
        this.likes = 0L;
        this.votedBy = new ArrayList<>();
        this.answers = new ArrayList<>();
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getDop() {
        return this.dop;
    }

    public void setDop(LocalDate dop) {
        this.dop = dop;
    }

    public User getAuthor() {
        return this.author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public List<User> getVotedBy() {
        return this.votedBy;
    }

    public void setVotedBy(List<User> votedBy) {
        this.votedBy = votedBy;
    }

    public Publication getPublication() {
        return this.publication;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
    }

    public Comment getParent() {
        return this.parent;
    }

    public void setParent(Comment parent) {
        this.parent = parent;
    }

    public List<Comment> getAnswers() {
        return this.answers;
    }

    public void setAnswers(List<Comment> answers) {
        this.answers = answers;
    }


    public Long getLikes() {
        return this.likes;
    }

    public void setLikes(Long likes) {
        this.likes = likes;
    }

    public void addReply(Comment comment) {
        this.answers.add(comment);
=======
    public User getAuthor() {
        return this.author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public List<User> getLikedBy() {
        return this.likedBy;
    }

    public void setLikedBy(List<User> likedBy) {
        this.likedBy = likedBy;
    }

    public void addLikedBy(User u) {
        this.likedBy.add(u);
    }

    public void removeLikeBy(User u) {
        this.likedBy.remove(u);
    }

    public List<User> getDislikedBy() {
        return this.dislikedBy;
    }

    public void setDislikedBy(List<User> dislikedBy) {
        this.dislikedBy = dislikedBy;
    }

    public void addDislikedBy(User u) {
        this.dislikedBy.add(u);
    }

    public void removeDislikedBy(User u) {
        this.dislikedBy.remove(u);
    }

    public Publication getPublication() {
        return this.publication;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
    }

    public Comment getParent() {
        return this.parent;
    }

    public void setParent(Comment parent) {
        this.parent = parent;
    }

    public List<Comment> getReplies() {
        return this.replies;
    }

    public void setReplies(List<Comment> replies) {
        this.replies = replies;
>>>>>>> development
    }


    public List<User> getCommentMentions() {
        return this.commentMentions;
    }

    public void setCommentMentions(List<User> commentMentions) {
        this.commentMentions = commentMentions;
    }
    



  
}
