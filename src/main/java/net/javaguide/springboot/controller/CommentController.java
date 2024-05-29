package net.javaguide.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.javaguide.springboot.dto.CommentDto;
import net.javaguide.springboot.service.CommentService;

@Controller
@RequestMapping("comments")
public class CommentController {
	public CommentService commentService;
	
	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}
	
	@PostMapping("/{postUrl}/post")
	public String createComment(
			@PathVariable("postUrl") String postUrl, 
			@ModelAttribute("comment") CommentDto commentDto,
			Model model) {
		this.commentService.createComment(postUrl, commentDto);
		
		return "redirect:/posts/" + postUrl;
	}
}
