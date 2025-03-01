package com.example.CRUD_BASIC.controller;

import com.example.CRUD_BASIC.dto.PostForm;
import com.example.CRUD_BASIC.entity.Post;
import com.example.CRUD_BASIC.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class BoardController {

    @Autowired
    PostRepository postRepository;

    // 홈 화면
    @GetMapping("/home")
    public String home(Model model) {
        Iterable<Post> list = postRepository.findAll();
        model.addAttribute("PostList", list);
        return "board/home";
    }

    // Create(게시글 생성)
    @GetMapping("/home/new")
    public String newWriting() {
        return "board/new";
    }

    // Create (게시글 생성)
    @PostMapping("/home/create")
    public String create(PostForm form) {
        log.info(form.toString());
        // 1. DTO -> 엔티티
        Post post = form.toEntity();
        // 2. DB에 엔티티 담기
        postRepository.save(post);
        log.info(post.toString());
        return "redirect:/home";
    }

    // Read (게시글 읽기)
    @GetMapping("/home/{id}")
    public String readWriting(@PathVariable Long id, Model model) {
        // id에 해당하는 엔티티 찾기
        Post post = postRepository.findById(id).orElse(null);
        // 엔티티를 모델에 담는다.
        model.addAttribute("Post", post);
        return "board/show";
    }

}