package com.example.CRUD_BASIC.repository;

import com.example.CRUD_BASIC.entity.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {
}
