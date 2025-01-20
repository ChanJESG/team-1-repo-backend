package com.example.rootsquad.backend.repository;

import com.example.rootsquad.backend.model.Category;
import org.springframework.data.repository.ListCrudRepository;

public interface CategoryRepository extends ListCrudRepository<Category, Long> {
}
