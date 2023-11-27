package www.dream.bbs.movie.model;

import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class MovieImgDTO {

	@Id
	private Integer id;
	private String title;
	private String filePath;
	private Double width;
	private Double height;
	private Double AspectRatio;
	
	public MovieImgDTO(Integer id, String title, String filePath, Double width, Double height, Double aspectRatio) {
		this.id = id;
		this.title = title;
		this.filePath = filePath;
		this.width = width;
		this.height = height;
		this.AspectRatio = aspectRatio;
	}
}