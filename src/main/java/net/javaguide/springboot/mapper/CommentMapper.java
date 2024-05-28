package net.javaguide.springboot.mapper;

import net.javaguide.springboot.dto.CommentDto;
import net.javaguide.springboot.entity.Comment;

public class CommentMapper {
	public static CommentDto mapToCommentDto(Comment comment) {
		CommentDto commentDto = CommentDto.builder()
				.id(comment.getId())
				.name(comment.getName())
				.email(comment.getEmail())
				.content(comment.getContent())
				.createdOn(comment.getCreatedOn())
				.updatedOn(comment.getUpdatedOn())
				.build();
		
		return commentDto;
	}
	
	public static Comment mapToComment(CommentDto dto) {
		return Comment.builder()
				.id(dto.getId())
				.name(dto.getName())
				.email(dto.getEmail())
				.content(dto.getContent())
				.createdOn(dto.getCreatedOn())
				.updatedOn(dto.getUpdatedOn())
				.build();
	}
}
