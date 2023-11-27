package www.dream.bbs.webclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.google.gson.Gson;

import www.dream.bbs.movie.model.TmdbMovieDetailVO;
import www.dream.bbs.movie.service.MovieService;
import www.dream.bbs.party.model.TmdbCompanyVO;
import www.dream.bbs.party.model.TmdbCreditsVO;
import www.dream.bbs.party.service.Movie4PartyService;

@Service
@PropertySource("classpath:application.properties")
public class WebClient4MovieDetailsGSON {

	@Value("${tmdb-admin-key}")
	private String api_key;

	@Autowired
	private MovieService movieService;

	@Autowired
	private Movie4PartyService movie4PartyService;

	public TmdbMovieDetailVO loadMovieDetails(Integer movieId) {

		// themoviedb.org에서 영화 세부 정보 받는 곳.
		// https://api.themoviedb.org/3/movie/{id//299,054}/?api_key=2a98cbe1fa65b5daaabc0522192e19f3
		WebClient webClient = WebClient.builder().baseUrl("https://api.themoviedb.org")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
		/**
		 * 여기서 부터는 영화 상세 정보 받는 곳.
		 */
		String uri = "/3/movie/" + movieId + "?api_key=" + api_key;
		String resultDetail = webClient.get().uri(uri).retrieve().bodyToMono(String.class).block();

		Gson gson = new Gson();
		TmdbMovieDetailVO detail = gson.fromJson(resultDetail, TmdbMovieDetailVO.class);
		// 영화 세부 정보 데이터 값 받는 곳.
		System.out.println("Budget: " + detail.getBudget());
		System.out.println("IMDB ID: " + detail.getImdbId());

		// 상세 정보 속 컴퍼티 정보, 컴퍼니 VO 속 리스트에서 컴퍼니 ID와 Logo, Name값을 가져온다.
		TmdbCreditsVO credits = gson.fromJson(resultDetail, TmdbCreditsVO.class);
		for (TmdbCompanyVO company : credits.getCompanies()) {
			System.out.println("Company Id: " + company.getCompanyId());
			System.out.println("Company LogoPath: " + company.getLogoPath());
			System.out.println("Company Name: " + company.getName());
		}

		System.out.println("Revenue: " + detail.getRevenue());
		System.out.println("Runtime: " + detail.getRuntime());
		System.out.println("Status: " + detail.getStatus());
		System.out.println("tagline: " + detail.getTagline());
		
		return detail;
	}

}