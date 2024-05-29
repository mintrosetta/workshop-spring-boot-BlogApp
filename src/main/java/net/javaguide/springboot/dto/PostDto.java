package net.javaguide.springboot.dto;

import java.time.LocalDateTime;
import java.util.Set;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // create getter, setter, toString, hashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
	private Long id;
	
	@NotEmpty(message = "Title should not be empty")
	private String title;
	
	private String url;
	
	@NotEmpty(message = "content should not be empty")
	private String content;
	
	@NotEmpty(message = "Short Description should not be empty")
	private String shortDescription;
	
	private LocalDateTime createdOn;
	
	private LocalDateTime updatedOn;
	
	private Set<CommentDto> comments;
}
