package com.example.rootsquad.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

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
    @Lob
    @Column
    @NotBlank(message = "Please enter a post description.")
    private String description;
    @Column
    private long likes;
    @Column
    @NotBlank(message = "Please enter a datetime.")
    private LocalDateTime datetime;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "post")
    private List<Comment> commentList;

    @ManyToOne
    @JoinColumn(name = "topic_id", referencedColumnName = "id")
    @JsonIgnoreProperties("postList")
    private Topic topic;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    @JsonIgnoreProperties("postList")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Post() {
    }

    public Post(String title, String description, LocalDateTime datetime) {
        this.title = title;
        this.description = description;
        this.datetime = datetime;
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

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
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
}
