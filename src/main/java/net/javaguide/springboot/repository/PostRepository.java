package net.javaguide.springboot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.javaguide.springboot.entity.Post;

// Entity, Data type primary key
public interface PostRepository extends JpaRepository<Post, Long> {
	// spring data jpa will create query by method name
	Optional<Post> findByUrl(String url);
	
	@Query("SELECT p FROM Post p WHERE p.title LIKE CONCAT('%', :search, '%') OR p.shortDescription LIKE CONCAT('%', :search, '%')")
	List<Post> searchPosts(String search);
}
