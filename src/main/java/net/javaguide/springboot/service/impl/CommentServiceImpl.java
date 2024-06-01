package net.javaguide.springboot.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import net.javaguide.springboot.dto.CommentDto;
import net.javaguide.springboot.entity.Comment;
import net.javaguide.springboot.entity.Post;
import net.javaguide.springboot.entity.User;
import net.javaguide.springboot.mapper.CommentMapper;
import net.javaguide.springboot.repository.CommentRepository;
import net.javaguide.springboot.repository.PostRepository;
import net.javaguide.springboot.repository.UserRepository;
import net.javaguide.springboot.service.CommentService;
import net.javaguide.springboot.util.SecurityUtil;

@Service
public class CommentServiceImpl implements CommentService {

	private CommentRepository commentRepository;
	private PostRepository postRepository;
	private UserRepository userRepository;
	
	public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, UserRepository userRepository) {
		this.commentRepository = commentRepository;
		this.postRepository = postRepository;
		this.userRepository = userRepository;
	}
	
	@Override
	public void createComment(String postUrl, CommentDto commentDto) {
		Post post = this.postRepository.findByUrl(postUrl).get();

		Comment comment = CommentMapper.mapToComment(commentDto);
		comment.setPost(post);
		
		this.commentRepository.save(comment);
	}

	@Override
	public List<CommentDto> findAllComments() {
		List<Comment> comments = this.commentRepository.findAll();
		return comments.stream().map(comment -> CommentMapper.mapToCommentDto(comment)).collect(Collectors.toList());
	}

	@Override
	public void removeComment(Long id) {
		this.commentRepository.deleteById(id);
		
	}

	@Override
	public List<CommentDto> findCommentsByPost() {
		String email = SecurityUtil.getCurrentUser().getUsername();
		
		User createdBy = this.userRepository.findByEmail(email);

		Long userId = createdBy.getId();

		List<Comment> comments = this.commentRepository.findCommentsByPost(userId);
		
		return comments.stream().map(comment -> CommentMapper.mapToCommentDto(comment)).collect(Collectors.toList());
	}

}
