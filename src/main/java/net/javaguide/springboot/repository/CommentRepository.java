package net.javaguide.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import net.javaguide.springboot.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
	@Query(value = "SELECT c.* FROM comments c \n" + //
				"INNER JOIN posts p ON c.post_id = p.id\n" + //
				"WHERE p.created_by = :userId", nativeQuery = true)
	List<Comment> findCommentsByPost(Long userId);
}
