package www.dream.bbs.bullitine_board.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import www.dream.bbs.board.controller.PostController;
import www.dream.bbs.board.service.PostService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PostController.class)
@DisplayName("PostController 테스트")
public class PostControllerTest {
	@Autowired
	private MockMvc mvc;

	@Autowired
	private PostService postService;
	
	
	@Autowired
	private ObjectMapper objectMapper;

	
	

	@BeforeEach
	public void setUp() {
		mvc = MockMvcBuilders.standaloneSetup(new PostController(postService))
				.addFilters(new CharacterEncodingFilter("UTF-8", true)) // utf-8 필터 추가
				.build();
	}
/*
	@DisplayName("게시글 목록 테스트")
	@Test
	void listAllPost() {
		try {
			MockHttpServletRequestBuilder rb = get("/post/listAll/000p")
					.accept(MediaType.APPLICATION_JSON)
					.characterEncoding("UTF-8");
			mvc.perform(rb).accept(MediaType.APPLICATION_JSON))
	        .andExpect(status().isOk())
	        .andDo(print());
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
*/
}
