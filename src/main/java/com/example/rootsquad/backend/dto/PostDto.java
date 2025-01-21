package com.example.rootsquad.backend.dto;

public class PostDto {

    private String title;

    private String description;

    private Long categoryId;

    private Long topicId;

    private String imageUrl;

    public PostDto() {
    }

    public PostDto(String title, String description, Long categoryId, Long topicId, String imageUrl) {
        this.title = title;
        this.description = description;
        this.categoryId = categoryId;
        this.topicId = topicId;
        this.imageUrl = imageUrl;
    }

    public PostDto(String title, String description) {
        this.title = title;
        this.description = description;
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

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
