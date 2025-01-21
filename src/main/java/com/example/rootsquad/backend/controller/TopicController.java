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
@RequestMapping("/topic")
public class TopicController {

    @Autowired
    TopicServiceInterface topicService;

    // adding new topic
    @PostMapping
    public ResponseEntity<Topic> addTopic (@RequestBody Topic topic) {
        Topic savedTopic = topicService.save(topic);

        return new ResponseEntity<>(savedTopic, HttpStatus.CREATED);
    }

    // updating topic
    @PutMapping("/{id}")
    public ResponseEntity<Topic> updateTopic (@PathVariable("id") Long id, @RequestBody Topic topic) {
        Topic updatedTopic = topicService.findById(id).map(foundTopic -> {
            foundTopic.setName(topic.getName());

            return topicService.save(foundTopic);
        }).orElseThrow(()-> new ResourceNotFoundException());

        return new ResponseEntity<>(updatedTopic, HttpStatus.OK);
    }

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


    // deleting topic by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Topic> deleteTopicById(@PathVariable("id") Long id) {
        Topic deletedTopic = topicService.findById(id).map(foundTopic-> {
            topicService.delete(id);

            return foundTopic;
        }).orElseThrow(()-> new ResourceNotFoundException());

        return new ResponseEntity<>(deletedTopic, HttpStatus.OK);
    }
}
