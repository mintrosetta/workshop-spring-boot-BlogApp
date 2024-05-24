package net.javaguide.springboot.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter // create all getter
@Setter // create all setter
@NoArgsConstructor // create default constructor
@AllArgsConstructor // create constructor with all field
@Builder // create builder pattern for this class
@Entity
@Table(name = "posts", schema = "myblogapp")
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "title", nullable = false)
	private String title;
	
	@Column(name = "url")
	private String url;
	
	@Lob // use when data should be big
	@Column(name = "content")
	private String content;
	
	@Column(name = "short_description")
	private String shortDescription;
	
	@Column(name = "created_on")
	@CreationTimestamp // create timestamp when create data
	private LocalDateTime createdOn;
	
	@Column(name = "updated_on")
	@UpdateTimestamp // create timestamp when update data
	private LocalDateTime updatedOn;
}
