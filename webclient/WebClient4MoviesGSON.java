package www.dream.bbs.webclient;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.google.gson.Gson;

import www.dream.bbs.movie.model.MovieGenreDTO;
import www.dream.bbs.movie.model.TmdbMovieDataVO;
import www.dream.bbs.movie.model.TmdbMovieResultVO;
import www.dream.bbs.movie.model.TmdbReleaseDataVO;
import www.dream.bbs.movie.service.GenreService;

@Service
@PropertySource("classpath:application.properties")
public class WebClient4MoviesGSON {

	@Value("${tmdb-admin-key}")
	private String api_key;

	@Autowired
	private GenreService genreService;

	@Autowired
	private WebClient4MovieDetailsGSON webClient4MovieDetailGSON;

	@Autowired
	private WebClient4CreditGSON webClient4CreditGSON;

	@Autowired
	private WebClient4MovieReleaseDateGSON webClient4MovieReleaseDateGSON;

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

			// int totalPages = (int)map.get("total_pages"); //추후 데이터가 허용되어 더 많은 값을 받을 수
			// 페이지를 1부터 500까지 순차적으로 돌린다.
			for (int pageNo = 1; pageNo <= 500; pageNo++) { // 500페이지까지만 제공받음
				// 영화 인기리스트 Url
				String uri = "/3/discover/movie?api_key=" + api_key + "&language=ko&with_genres=" + genreIds + "&page="
						+ pageNo;
				String resultMovie = webClient.get().uri(uri).retrieve().bodyToMono(String.class).block();

				Gson gson = new Gson();
				TmdbMovieDataVO movie = gson.fromJson(resultMovie, TmdbMovieDataVO.class);

				for (TmdbMovieResultVO result : movie.getResults()) {
					System.out.println("BackDrop Path: " + result.getBackPath());
					Integer id = result.getId();
					System.out.println("Movie ID: " + id);
					System.out.println("Genre Names: " + genreNames);
					System.out.println("Original Language: " + result.getOlLanguage());
					System.out.println("Original Title: " + result.getOlTitle());
					System.out.println("Overview: " + result.getOverview());
					System.out.println("Popularity: " + result.getPopularity());
					System.out.println("Poster Path: " + result.getPosterPath());
					System.out.println("Title: " + result.getTitle());
					System.out.println("Vote Average: " + result.getVoteAverage());
					System.out.println("Vote Count: " + result.getVoteCount());

					webClient4MovieDetailGSON.loadMovieDetails(id);
					webClient4CreditGSON.loadCredit(id);

					TmdbMovieDataVO.setTmdbMovieDetailVO(webClient4MovieDetailGSON.loadMovieDetails(id));
					TmdbReleaseDataVO.setTmdbReleaseDataVO(webClient4MovieReleaseDateGSON.loadRelease(id));

				}
			}
		}
	}
}