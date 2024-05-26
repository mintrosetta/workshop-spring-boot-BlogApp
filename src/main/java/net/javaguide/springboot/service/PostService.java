package net.javaguide.springboot.service;

import java.util.List;

import net.javaguide.springboot.dto.PostDto;

public interface PostService {
	List<PostDto> findAllPosts();
	void createPost(PostDto postDto);
	PostDto findPostById(Long id);
	void updatePost(PostDto postDto);
	void deletePost(Long postId);
}
