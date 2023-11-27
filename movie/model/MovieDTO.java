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
public class MovieDTO {

	private boolean adult;
	@Id
	private Integer id;
	private String genreNames;
	private String title;
	private Double popularity;
	private String releaseDate;
	private String overview;
	private Double voteAverage;
	private Integer voteCount;

	public MovieDTO(Integer id) {
		this.id = id;
	}
	
	public MovieDTO(Boolean adult, Integer id, String genreNames, String title, Double popularity,
			String releaseDate, String overview, Double voteAverage, Integer voteCount) {
		this.adult = adult;
		this.id = id;
		this.genreNames = genreNames;
		this.title = title;
		this.popularity = popularity;
		this.releaseDate = releaseDate;
		this.overview = overview;
		this.voteAverage = voteAverage;
		this.voteCount = voteCount;
	}
	
}