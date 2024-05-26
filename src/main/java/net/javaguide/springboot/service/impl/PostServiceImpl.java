package net.javaguide.springboot.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import net.javaguide.springboot.dto.PostDto;
import net.javaguide.springboot.entity.Post;
import net.javaguide.springboot.mapper.PostMapper;
import net.javaguide.springboot.repository.PostRepository;
import net.javaguide.springboot.service.PostService;

@Service
public class PostServiceImpl implements PostService {
	
	private PostRepository postRepository;
	
	public PostServiceImpl(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	@Override
	public List<PostDto> findAllPosts() {
		List<Post> posts = this.postRepository.findAll();
		
		return posts.stream().map((post) -> PostMapper.mapToPostDto(post)).collect(Collectors.toList());
	}

	@Override
	public void createPost(PostDto postDto) {
		Post post = PostMapper.mapToPost(postDto);
		
		this.postRepository.save(post);
	}

	@Override
	public PostDto findPostById(Long id) {
		Post post = this.postRepository.findById(id).get();
		return PostMapper.mapToPostDto(post);
	}

	@Override
	public void updatePost(PostDto postDto) {
		Post post = PostMapper.mapToPost(postDto);
		
		this.postRepository.save(post);
	}

	@Override
	public void deletePost(Long postId) {
		this.postRepository.deleteById(postId);	
	}

}
