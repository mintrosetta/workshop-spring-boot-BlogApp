package net.javaguide.springboot.service.impl;

import org.springframework.stereotype.Service;

import net.javaguide.springboot.dto.CommentDto;
import net.javaguide.springboot.entity.Comment;
import net.javaguide.springboot.entity.Post;
import net.javaguide.springboot.mapper.CommentMapper;
import net.javaguide.springboot.repository.CommentRepository;
import net.javaguide.springboot.repository.PostRepository;
import net.javaguide.springboot.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	private CommentRepository commentRepository;
	private PostRepository postRepository;
	
	public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository) {
		this.commentRepository = commentRepository;
		this.postRepository = postRepository;
	}
	
	@Override
	public void createComment(String postUrl, CommentDto commentDto) {
		Post post = this.postRepository.findByUrl(postUrl).get();

		Comment comment = CommentMapper.mapToComment(commentDto);
		comment.setPost(post);
		
		this.commentRepository.save(comment);
	}

}
