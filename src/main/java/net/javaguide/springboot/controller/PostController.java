package net.javaguide.springboot.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
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
	
	// @ModelAttribute read form data and set the value to field in object
	// @Valid
	@PostMapping("create")
	public String createPost(@Valid @ModelAttribute("post") PostDto postDto, BindingResult result, Model model) {
		if (result.hasErrors())
		{
			model.addAttribute("post", postDto);
			return "admin/post-create";
		}
		
		postDto.setUrl(getUrl(postDto.getTitle()));
		
		this.postService.createPost(postDto);
		
		return "redirect:/admin/posts";
	}
	
	private static String getUrl(String title) {
		// FROM: OOPS Concept Explained in Java TO: oops-concept-explained-in-java
		String tempTitle = title.trim().toLowerCase();
		String url = tempTitle.replaceAll("\\s+", "-").replaceAll("[^A-Za-z0-9]", "-");
		
		return url;
	}
}
