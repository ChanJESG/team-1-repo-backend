package com.example.rootsquad.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    @NotBlank(message = "Please enter a title.")
    private String title;
    @Column(columnDefinition = "TEXT")
    @NotBlank(message = "Please enter a post description.")
    private String description;
    private long likes;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dateTimeCreation;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dateTimeUpdate;

    private String imageUrl;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> commentList;

    @ManyToOne
    @JoinColumn(name = "topic_id", foreignKey = @ForeignKey(name = "fk_topic_id"), referencedColumnName = "id")
    @JsonIgnoreProperties("postList")
    private Topic topic;

    @ManyToOne
    @JoinColumn(name = "category_id", foreignKey = @ForeignKey(name = "fk_category_id"), referencedColumnName = "id")
    @JsonIgnoreProperties("postList")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_user_id"), referencedColumnName = "id")
    @JsonIncludeProperties({"id", "userName"})
    private User user;

    public Post() {
    }

    public Post(String title, String description, Topic topic, Category category) {
        this.title = title;
        this.description = description;
        this.topic = topic;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getLikes() {
        return likes;
    }

    public void setLikes(long likes) {
        this.likes = likes;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getDateTimeCreation() {
        return dateTimeCreation;
    }

    public void setDateTimeCreation(LocalDateTime dateTimeCreation) {
        this.dateTimeCreation = dateTimeCreation;
    }

    public LocalDateTime getDateTimeUpdate() {
        return dateTimeUpdate;
    }

    public void setDateTimeUpdate(LocalDateTime dateTimeUpdate) {
        this.dateTimeUpdate = dateTimeUpdate;
    }
}
