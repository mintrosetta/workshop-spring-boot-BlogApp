package net.javaguide.springboot.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "username", nullable = false)
	private String username;
	
	@Column(name = "email", nullable = false, unique = true)
	private String email;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(
		name = "users_roles", 
		joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")}, // name = 'column that store id of this entity', referenceColumnName = 'Column of this entity that ref from join table'
		inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
	) 
	private List<Role> roles = new ArrayList<>();
}
