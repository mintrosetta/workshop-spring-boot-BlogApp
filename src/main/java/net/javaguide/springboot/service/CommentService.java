package net.javaguide.springboot.service;

import net.javaguide.springboot.dto.CommentDto;

public interface CommentService {
	void createComment(String postUrl, CommentDto commentDto);
}
