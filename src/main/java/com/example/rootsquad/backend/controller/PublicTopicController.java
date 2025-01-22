package com.example.rootsquad.backend.controller;

import com.example.rootsquad.backend.exception.ResourceNotFoundException;
import com.example.rootsquad.backend.model.Topic;
import com.example.rootsquad.backend.service.TopicServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public/api/topic")
public class PublicTopicController {

    @Autowired
    TopicServiceInterface topicService;

    // getting all topics
    @GetMapping
    public ResponseEntity<Object> getTopics() {
        List<Topic> topics = topicService.findAll();

        if (topics.isEmpty())
            throw new ResourceNotFoundException();

        return new ResponseEntity<>(topics, HttpStatus.OK);
    }


    // getting topic by id
    @GetMapping("/{id}")
    public ResponseEntity<Topic> getTopicById(@PathVariable("id") Long id) {
        Topic topic = topicService.findById(id).orElseThrow(()-> new ResourceNotFoundException());

        return new ResponseEntity<>(topic, HttpStatus.OK);
    }

}
