package www.dream.bbs.movie.model;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class TmdbMovieResultVO {
    	//GSON 방식 이름 변경해서 하는 것.
    	@SerializedName("backdrop_path")
    	String backPath;
    	@SerializedName("id")
    	int id;
    	@SerializedName("genreNames")
    	String genreNames;
    	@SerializedName("original_language")
    	String olLanguage;
    	@SerializedName("original_title")
    	String olTitle;
    	@SerializedName("overview")
    	String overview;
    	@SerializedName("popularity")
    	Double popularity;
    	@SerializedName("poster_path")
    	String posterPath;
    	@SerializedName("title")
    	String title;
    	@SerializedName("vote_average")
    	Double voteAverage;
    	@SerializedName("vote_count")
    	Integer voteCount;

}