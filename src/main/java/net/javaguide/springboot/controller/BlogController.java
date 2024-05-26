package net.javaguide.springboot.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.javaguide.springboot.dto.PostDto;
import net.javaguide.springboot.service.PostService;

@Controller
@RequestMapping("posts")
public class BlogController {
	private PostService postService;
	
	public BlogController(PostService postService) {
		this.postService = postService;
	}
	
	@GetMapping("")
	public String getBlogsView(Model model) {
		List<PostDto> postDtos = this.postService.findAllPosts();
		
		model.addAttribute("posts", postDtos);
		
		return "blog/post-views";
	}
}
