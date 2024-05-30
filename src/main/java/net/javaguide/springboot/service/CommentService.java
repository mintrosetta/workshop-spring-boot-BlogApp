package net.javaguide.springboot.service;

import java.util.List;

import net.javaguide.springboot.dto.CommentDto;

public interface CommentService {
	void createComment(String postUrl, CommentDto commentDto);

	List<CommentDto> findAllComments();
}
