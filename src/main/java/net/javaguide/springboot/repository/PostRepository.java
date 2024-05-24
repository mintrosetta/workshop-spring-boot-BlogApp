package net.javaguide.springboot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import net.javaguide.springboot.entity.Post;

// Entity, Data type primary key
public interface PostRepository extends JpaRepository<Post, Long> {
	// spring data jpa will create query by method name
	Optional<Post> findByUrl(String url);
}
