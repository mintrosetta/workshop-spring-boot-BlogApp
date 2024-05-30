package net.javaguide.springboot.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import net.javaguide.springboot.dto.CommentDto;
import net.javaguide.springboot.dto.PostDto;
import net.javaguide.springboot.service.CommentService;
import net.javaguide.springboot.service.PostService;

@Controller
@RequestMapping("admin/posts")
public class PostController {
	
	private PostService postService;
	private CommentService commentService;
	
	public PostController(PostService postService, CommentService commentServiec) {
		this.postService = postService;
		this.commentService = commentServiec;
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
	
	@GetMapping("{postId}/edit")
	public String editPost(Model model, @PathVariable("postId") Long id) {
		PostDto post = this.postService.findPostById(id);
		
		model.addAttribute("post", post);
		
		return "admin/post-edit";
	}
	
	@PostMapping("{postId}/edit")
	public String updatePost(
			@PathVariable("postId") Long id, 
			@ModelAttribute("post") PostDto postDto, 
			Model model, 
			BindingResult result) {
		if (result.hasErrors())
		{
			model.addAttribute("post", postDto);
			return "admin/post-edit";
		}
		
		this.postService.updatePost(postDto);
		
		return "redirect:/admin/posts";
	}
	
	@GetMapping("{postId}/delete")
	public String deletePost(@PathVariable("postId") Long id) {
		this.postService.deletePost(id);
		
		return "redirect:/admin/posts";
	}
	
	@GetMapping("{postUrl}/view")
	public String viewPost(@PathVariable("postUrl") String postUrl, Model model) {
		PostDto postDto = this.postService.findPostByUrl(postUrl);
		
		model.addAttribute("post", postDto);
		
		return "admin/post-view";
	}
	
	@GetMapping("search")
	public String searchPost(@RequestParam(value = "search") String search, Model model) {
		List<PostDto> posts = this.postService.searchPost(search);
		
		model.addAttribute("posts", posts);
		
		return "admin/posts";
	}
	
	@GetMapping("comments")
	public String postComments(Model model) {
		List<CommentDto> comments = this.commentService.findAllComments();
		
		model.addAttribute("comments", comments);
		
		return "admin/comments";
	}
	
	@GetMapping("comments/{commentId}/remove")
	public String removeComment(@PathVariable("commentId") Long id, Model model) {
		this.commentService.removeComment(id);
		
		return "redirect:/admin/posts/comments";
	}
	
	private static String getUrl(String title) {
		// FROM: OOPS Concept Explained in Java TO: oops-concept-explained-in-java
		String tempTitle = title.trim().toLowerCase();
		String url = tempTitle.replaceAll("\\s+", "-").replaceAll("[^A-Za-z0-9]", "-");
		
		return url;
	}
}
