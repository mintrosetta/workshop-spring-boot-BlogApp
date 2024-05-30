package net.javaguide.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.javaguide.springboot.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByName(String name);
}
