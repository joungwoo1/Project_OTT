package www.dream.bbs.movie.model;

import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class MovieGenreDTO {
	
	@Id
	private Integer id;
	private String name;
	

	public MovieGenreDTO(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
}
