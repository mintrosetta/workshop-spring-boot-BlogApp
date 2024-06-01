package net.javaguide.springboot.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import net.javaguide.springboot.dto.PostDto;
import net.javaguide.springboot.entity.Post;
import net.javaguide.springboot.entity.User;
import net.javaguide.springboot.mapper.PostMapper;
import net.javaguide.springboot.repository.PostRepository;
import net.javaguide.springboot.repository.UserRepository;
import net.javaguide.springboot.service.PostService;
import net.javaguide.springboot.util.SecurityUtil;

@Service
public class PostServiceImpl implements PostService {
	
	private PostRepository postRepository;
	private UserRepository userRepository;
	
	public PostServiceImpl(PostRepository postRepository, UserRepository userRepository) {
		this.postRepository = postRepository;
		this.userRepository = userRepository;
	}

	@Override
	public List<PostDto> findAllPosts() {
		List<Post> posts = this.postRepository.findAll();
		
		return posts.stream().map((post) -> PostMapper.mapToPostDto(post)).collect(Collectors.toList());
	}

	@Override
	public void createPost(PostDto postDto) {
		try {
			String email = SecurityUtil.getCurrentUser().getUsername();
			
			User user = this.userRepository.findByEmail(email);
			
			if (user == null) throw new Exception("User not found when create post.");
			
			Post post = PostMapper.mapToPost(postDto);
			post.setCreatedBy(user);
			
			this.postRepository.save(post);
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
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

	@Override
	public PostDto findPostByUrl(String url) {
		Post post = this.postRepository.findByUrl(url).get();
		
		return PostMapper.mapToPostDto(post);
	}

	@Override
	public List<PostDto> searchPost(String search) {
		List<Post> posts = this.postRepository.searchPosts(search);
		
		return posts.stream().map((post) -> PostMapper.mapToPostDto(post)).collect(Collectors.toList());
	}

}
