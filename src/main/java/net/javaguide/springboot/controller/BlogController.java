package net.javaguide.springboot.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@GetMapping("{postUrl}")
	public String getBlogView(@PathVariable("postUrl") String url, Model model) {
		PostDto postDto = this.postService.findPostByUrl(url);
		
		model.addAttribute("post", postDto);
		
		return "blog/post-view";
	}
	
	@GetMapping("search")
	public String searchPosts(Model model, @RequestParam(value = "search") String search) {
		List<PostDto> posts = this.postService.searchPost(search);
		
		model.addAttribute("posts", posts);
		
		return "blog/post-views";
	}
}
