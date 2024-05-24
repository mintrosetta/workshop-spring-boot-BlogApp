package net.javaguide.springboot.mapper;

import net.javaguide.springboot.dto.PostDto;
import net.javaguide.springboot.entity.Post;

public class PostMapper {
	
	// map Post entity to PostDto
	public static PostDto mapToPostDto(Post post) {
		PostDto dto = PostDto.builder()
				.id(post.getId())
				.title(post.getTitle())
				.url(post.getUrl())
				.content(post.getContent())
				.shortDescription(post.getShortDescription())
				.createdOn(post.getCreatedOn())
				.updatedOn(post.getUpdatedOn())
				.build();
		
		return dto;
	}
	
	// map post dto to post entity 
	public static Post mapToPost(PostDto dto) {
		Post post = Post.builder()
				.id(dto.getId())
				.title(dto.getTitle())
				.content(dto.getContent())
				.url(dto.getUrl())
				.shortDescription(dto.getShortDescription())
				.createdOn(dto.getCreatedOn())
				.updatedOn(dto.getUpdatedOn())
				.build();
		
		return post;
	}
}
