package com.example.rootsquad.backend.service;

import com.example.rootsquad.backend.model.Topic;
import com.example.rootsquad.backend.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicService implements TopicServiceInterface{

    @Autowired
    private TopicRepository topicRepository;


    @Override
    public Topic save(Topic topic) {
        return topicRepository.save(topic);
    }

    @Override
    public List<Topic> findAll() {
        return topicRepository.findAll();
    }

    @Override
    public Topic update(Topic topic) {
        return topicRepository.save(topic);
    }

    @Override
    public void delete(Long id) {
        topicRepository.deleteById(id);
    }

    @Override
    public Optional<Topic> findById(Long id) {
        return topicRepository.findById(id);
    }
}
