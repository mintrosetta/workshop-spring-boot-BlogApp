package net.javaguide.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import net.javaguide.springboot.dto.CommentDto;
import net.javaguide.springboot.dto.PostDto;
import net.javaguide.springboot.service.CommentService;
import net.javaguide.springboot.service.PostService;

@Controller
@RequestMapping("comments")
public class CommentController {
	public CommentService commentService;
	public PostService postService;
	
	public CommentController(CommentService commentService, PostService postService) {
		this.commentService = commentService;
		this.postService = postService;
	}
	
	@PostMapping("/{postUrl}/post")
	public String createComment(
			@PathVariable("postUrl") String postUrl, 
			@Valid @ModelAttribute("comment") CommentDto commentDto,
			BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			PostDto postDto = this.postService.findPostByUrl(postUrl);
			
			model.addAttribute("post", postDto);
			model.addAttribute("comment", commentDto);
			
			return "blog/post-view";
		}
		
		this.commentService.createComment(postUrl, commentDto);
		
		return "redirect:/posts/" + postUrl;
	}
}
