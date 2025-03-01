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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        // 리다이렉트
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

    // Update (게시글 수정)
    @GetMapping("/home/{id}/edit")
    public String editWriting(@PathVariable Long id, Model model) {
        Post post = postRepository.findById(id).orElse(null);
        model.addAttribute("Post", post);
        return "board/edit";
    }

    // Update (게시글 수정)
    @PostMapping("/home/update")
    public String updateWriting(PostForm form) {
        // 1. DTO -> 엔티티
        Post postEntity = form.toEntity();
        // 2. 기존에 있던 게시글을 수정.
        Post check = postRepository.findById(postEntity.getId()).orElse(null);
        if(check != null) postRepository.save(postEntity);

        // 리다이렉트
        return "redirect:/home";
    }

    // Delete (게시글 삭제)
    @GetMapping("/home/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr) {
        Post target = postRepository.findById(id).orElse(null);

        if(target != null) {
            postRepository.delete(target);
            // 리다이렉트 시점에 한 번만 사용할 데이터
            rttr.addFlashAttribute("msg", "게시글이 삭제되었습니다.");
        }
        return "redirect:/home";
    }

}