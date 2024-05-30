package net.javaguide.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.javaguide.springboot.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);
}
