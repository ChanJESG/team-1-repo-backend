package com.example.rootsquad.backend.controller;

import com.example.rootsquad.backend.dto.PostDto;
import com.example.rootsquad.backend.exception.ResourceNotFoundException;
import com.example.rootsquad.backend.model.Category;
import com.example.rootsquad.backend.model.Post;
import com.example.rootsquad.backend.model.Topic;
import com.example.rootsquad.backend.model.User;
import com.example.rootsquad.backend.service.*;
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
@RequestMapping("/public/api/post")                     // TODO change back to restricted after auth complete
public class RestrictedPostController {
    @Autowired
    PostServiceInterface postService;
    @Autowired
    CategoryServiceInterface categoryService;
    @Autowired
    TopicServiceInterface topicService;
    @Autowired
    UserServiceInterface userService;

    @Value("${file.upload-dir}")
    private String uploadDir;


    // TODO validation for image type
    // adding new post
    @PostMapping
    public ResponseEntity<Post> addPost(@RequestParam("postData") String postData, @Nullable @RequestParam("image") MultipartFile image) throws IOException, ResourceNotFoundException {
        ObjectMapper objectMapper = new ObjectMapper();
        PostDto postDto = objectMapper.readValue(postData, PostDto.class);

        // && image.getContentType().equalsIgnoreCase("jpeg/jpg/png")
        if(image != null) {
            String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
            String filePath = uploadDir + File.separator + fileName;
            File imageFile = new File(filePath);
            image.transferTo(imageFile.toPath());

            postDto.setImageUrl(filePath);
        }

        Category category = categoryService.findById(postDto.getCategoryId()).orElseThrow(()-> new ResourceNotFoundException("Category not found."));
        Topic topic = topicService.findById(postDto.getTopicId()).orElseThrow(()-> new ResourceNotFoundException("Topic not found."));
        User user = userService.findById(postDto.getUserId()).orElseThrow(()-> new ResourceNotFoundException("User not found."));

        Post savedPost = new Post();

        savedPost.setTitle(postDto.getTitle());
        savedPost.setDescription(postDto.getDescription());
        savedPost.setCategory(category);
        savedPost.setTopic(topic);
        savedPost.setUser(user);
        savedPost.setImageUrl(postDto.getImageUrl());

        return new ResponseEntity<>(postService.save(savedPost), HttpStatus.CREATED);
    }

    // updating a post
    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable("id") Long id, @RequestParam("postData") String postData) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        PostDto postDto = objectMapper.readValue(postData, PostDto.class);

        Post updatedPost = postService.findById(id).map(foundPost-> {
            foundPost.setTitle(postDto.getTitle());
            foundPost.setDescription(postDto.getDescription());

            return postService.update(foundPost);
        }).orElseThrow(()-> new ResourceNotFoundException());

        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
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
    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable("id") Long id) {
        Post post = postService.findById(id).orElseThrow(()-> new ResourceNotFoundException());

        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    // getting posts by category Id
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<Object> getPostByCategoryId(@PathVariable("categoryId") Long categoryId) {
        List<Post> postList = postService.findByCategoryId(categoryId);

        return new ResponseEntity<>(postList, HttpStatus.OK);
    }

    // getting posts by topic Id
    @GetMapping("/topic/{topicId}")
    public ResponseEntity<Object> getPostByTopicId(@PathVariable("topicId") Long topicId) {
        List<Post> postList = postService.findByTopicId(topicId);

        return new ResponseEntity<>(postList, HttpStatus.OK);
    }

    // get posts by user Id
    @GetMapping("/user/{userId}")
    public ResponseEntity<Object> getPostByUserId(@PathVariable("userId") Long userId) {
        List<Post> postList = postService.findByUserId(userId);

        return new ResponseEntity<>(postList, HttpStatus.OK);
    }

    // get posts by query
    @GetMapping("/search/{searchTerm}")
    public ResponseEntity<Object> getPostByTitleContainingOrDescriptionContaining(@PathVariable("searchTerm") String searchTerm, @PathVariable("searchTerm") String searchTerm2) {
        List<Post> postList = postService.findByTitleContainingOrDescriptionContaining(searchTerm, searchTerm2);

        return new ResponseEntity<>(postList,HttpStatus.OK);
    }

    // get posts by query and topic id
    @GetMapping("/search/{searchTerm}/topic/{topicId}")
    public ResponseEntity<Object> findByTitleContainingOrDescriptionContainingAndTopicIdIs(@PathVariable("searchTerm") String searchTerm, @PathVariable("searchTerm") String searchTerm2, @PathVariable("topicId") Long topicId) {
        List<Post> postList = postService.findByTitleContainingOrDescriptionContainingAndTopicIdIs(searchTerm, searchTerm2, topicId);

        return new ResponseEntity<>(postList, HttpStatus.OK);
    }

    // get posts by query and category id
    @GetMapping("/search/{searchTerm}/category/{categoryId}")
    public ResponseEntity<Object> findByTitleContainingOrDescriptionContainingAndCategoryIdIs(@PathVariable("searchTerm") String searchTerm, @PathVariable("searchTerm") String searchTerm2, @PathVariable("categoryId") Long categoryId) {
        List<Post> postList = postService.findByTitleContainingOrDescriptionContainingAndCategoryIdIs(searchTerm, searchTerm2, categoryId);

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
