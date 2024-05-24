package net.javaguide.springboot.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data // create getter, setter, toString, hashCode
@Builder
public class PostDto {
	private Long id;
	private String title;
	private String url;
	private String content;
	private String shortDescription;
	private LocalDateTime createdOn;
	private LocalDateTime updatedOn;
}
