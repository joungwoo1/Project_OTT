package www.dream.bbs.webclient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import www.dream.bbs.movie.model.MovieGenreDTO;
import www.dream.bbs.movie.model.MovieVO;
import www.dream.bbs.movie.service.GenreService;
import www.dream.bbs.movie.service.MovieService;

@Service
@PropertySource("classpath:application.properties")
public class WebClient4Movies {
	
	@Value("${tmdb-admin-key}")
	private String api_key;

	@Autowired
	private MovieService movieService;

	@Autowired
	private GenreService genreService;

	@Autowired
	private WebClient4MovieDetails webClient4MovieDetails;

	@Autowired
	private WebClient4Actor webClient4Actor;
	
	@Scheduled(fixedDelay = 30000)
	public void loadMovie() {

		List<MovieGenreDTO> listGenre = genreService.listGenreInfo();

		for (MovieGenreDTO genre : listGenre) {
			int genreIds = genre.getId();
			String genreNames = genre.getName();

			// themoviedb.org에서 movie/popular 에 따른 정보 획득
			// https://api.themoviedb.org/3/movie/popular?api_key=a158e2a9424bc69fec449dcaeb82aba8&language=ko&page={i}
			// https://api.themoviedb.org/3/discover/movie?api_key=2a98cbe1fa65b5daaabc0522192e19f3&language=ko&with_genres=
			WebClient webClient = WebClient.builder().baseUrl("https://api.themoviedb.org")
					.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
			
			List<MovieVO> movieLists = new ArrayList<>();
			// int totalPages = (int)map.get("total_pages"); //추후 데이터가 허용되어 더 많은 값을 받을 수
			// 페이지를 1부터 500까지 순차적으로 돌린다.
			for (int pageNo = 1; pageNo <= 500; pageNo++) { // 500페이지까지만 제공받음
				// 영화 인기리스트 Url
				String uri = "/3/discover/movie?api_key=" + api_key + "&language=ko&with_genres=" + genreIds
						+ "&page=" + pageNo;
				String resultMovie = webClient.get().uri(uri).retrieve().bodyToMono(String.class).block();

				Map<String, Object> map = this.jsonToMap(resultMovie);
				map = this.jsonToMap(resultMovie);
				
				// 위에 URL에서 받은 것을 List로 만든다.
				List movies = (List) map.get("results");
				// 그걸 오브젝트 obj라 하고, Map으로 만든다.
				for (Object obj : movies) {
					Map movie = (Map) obj;
					// 영화 성인등급여부. 영화 ID, 영화 TITLE, 영화 줄거리, 영화 투표횟수, 영화 투표평균평점
					String backdropPath = (String) movie.get("backdrop_path");
					Integer tmdbId = (Integer) movie.get("id");
					String movieOverView = (String) movie.get("overview");
					String posterPath = (String) movie.get("poster_path");
					String movieTitle = (String) movie.get("title");
					Integer movieVoteCount = (Integer) movie.get("vote_count");

					// instanceof를 이용한 true, false 확인 후 if문으로 상황 맞춰 형변환.
					// Integer를 Double이 될 수 있도록, 기본 값을 0.0으로 주고 마지막에 1.0을 곱한다.
					Double moviePopularity = 0.0;
					Object ObjectPopularity = movie.get("popularity");
					if (ObjectPopularity instanceof Double) {
						moviePopularity = ((Double) movie.get("popularity"));
					} else if (ObjectPopularity instanceof Integer) {
						Integer popularity = (Integer) movie.get("popularity");
						moviePopularity = popularity * 1.0;
					}
					Double movieVoteAverage = 0.0;
					Object ObjectVoteAverage = movie.get("vote_average");
					if (ObjectVoteAverage instanceof Double) {
						movieVoteAverage = ((Double) movie.get("vote_average"));
					} else if (ObjectVoteAverage instanceof Integer) {
						Integer voteAverage = (Integer) movie.get("vote_average");
						movieVoteAverage = voteAverage * 1.0;
					}
					
					MovieVO movieVO = new MovieVO(backdropPath, genreNames, tmdbId, movieOverView,
							moviePopularity, posterPath, movieTitle, movieVoteAverage, movieVoteCount);

					movieVO.setMovieDetailVOList(webClient4MovieDetails.loadMovieDetails(tmdbId));
					movieLists.add(movieVO);

					webClient4Actor.loadActor(tmdbId);
				}
				movieService.saveMovie(movieLists);
			}
		}
	}

	private Map<String, Object> jsonToMap(String json) {
		ObjectMapper objectMapper = new ObjectMapper();
		TypeReference<Map<String, Object>> typeReference = new TypeReference<Map<String, Object>>() {
		};
		try {
			return objectMapper.readValue(json, typeReference);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new HashMap<>();
	}

}