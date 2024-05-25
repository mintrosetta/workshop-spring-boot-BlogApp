package net.javaguide.springboot.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.javaguide.springboot.dto.PostDto;
import net.javaguide.springboot.service.PostService;

@Controller
@RequestMapping("/admin/posts")
public class PostController {
	
	private PostService postService;
	
	public PostController(PostService postService) {
		this.postService = postService;
	}
	
	@GetMapping("")
	public String getPosts(Model model) {
		List<PostDto> posts = this.postService.findAllPosts();
		
		model.addAttribute("posts", posts);
		
		return "admin/posts";
	}
	
	@GetMapping("create")
	public String createPost(Model model) {
		PostDto post = new PostDto();
		
		model.addAttribute("post", post);
		
		return "admin/post-create";
	}
	
}
