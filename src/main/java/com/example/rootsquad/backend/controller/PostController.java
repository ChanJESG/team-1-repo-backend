package com.example.rootsquad.backend.controller;

import com.example.rootsquad.backend.dto.PostDto;
import com.example.rootsquad.backend.exception.ResourceNotFoundException;
import com.example.rootsquad.backend.model.Category;
import com.example.rootsquad.backend.model.Post;
import com.example.rootsquad.backend.model.Topic;
import com.example.rootsquad.backend.service.CategoryServiceInterface;
import com.example.rootsquad.backend.service.PostServiceInterface;
import com.example.rootsquad.backend.service.TopicServiceInterface;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    PostServiceInterface postService;
    @Autowired
    CategoryServiceInterface categoryService;
    @Autowired
    TopicServiceInterface topicService;

    @Value("${file.upload-dir}")
    private String uploadDir;

    // adding new post
    @PostMapping
    public ResponseEntity<Post> addPost(@RequestParam("postData") String postData, @Nullable @RequestParam("image") MultipartFile image) throws IOException, ResourceNotFoundException {
        ObjectMapper objectMapper = new ObjectMapper();
        PostDto postDto = objectMapper.readValue(postData, PostDto.class);

        if(image != null) {
            String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
            File imageFile = new File(uploadDir + File.separator + fileName);
            image.transferTo(imageFile.toPath());

            postDto.setImageUrl(String.format("/%s/%s",uploadDir, fileName));
        }

        Category category = categoryService.findById(postDto.getCategoryId()).orElseThrow(()-> new ResourceNotFoundException());
        Topic topic = topicService.findById(postDto.getTopicId()).orElseThrow(()-> new ResourceNotFoundException());

        Post savedPost = new Post();

        savedPost.setTitle(postDto.getTitle());
        savedPost.setDescription(postDto.getDescription());
        savedPost.setCategory(category);
        savedPost.setTopic(topic);

        return new ResponseEntity<>(postService.save(savedPost), HttpStatus.CREATED);
    }

    // getting all posts
    @GetMapping
    public ResponseEntity<Object> getPosts() {
        List<Post> postList = postService.findAll();

        if(postList.isEmpty())
            throw new ResourceNotFoundException();

        return new ResponseEntity<>(postList, HttpStatus.OK);
    }

    // getting post by Id
    @GetMapping("/post={id}")
    public ResponseEntity<Post> getPostById(@PathVariable("id") Long id) {
        Post post = postService.findById(id).orElseThrow(()-> new ResourceNotFoundException());

        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    // getting posts by category Id
    @GetMapping("/category={categoryId}")
    public ResponseEntity<Object> getPostByCategoryId(@PathVariable("categoryId") Long categoryId) {
        List<Post> postList = postService.findByCategoryId(categoryId);

        return new ResponseEntity<>(postList, HttpStatus.OK);
    }

    // getting posts by topic Id
    @GetMapping("/topic={topicId}")
    public ResponseEntity<Object> getPostByTopicId(@PathVariable("topicId") Long topicId) {
        List<Post> postList = postService.findByTopicId(topicId);

        return new ResponseEntity<>(postList, HttpStatus.OK);
    }

    // deleting a post
    @DeleteMapping("/post={id}")
    public ResponseEntity<Post> deletePost (@PathVariable("id") Long id) {
        Post deletedPost = postService.findById(id).orElseThrow(()-> new ResourceNotFoundException());
        postService.delete(id);

        return new ResponseEntity<>(deletedPost, HttpStatus.OK);
    }
}
