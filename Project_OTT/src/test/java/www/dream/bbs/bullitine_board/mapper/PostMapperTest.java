package www.dream.bbs.bullitine_board.mapper;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import www.dream.bbs.board.mapper.PostMapper;
import www.dream.bbs.board.model.PostVO;
import www.dream.bbs.framework.model.PagingDTO;

@ExtendWith(SpringExtension.class)
@MybatisTest

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PostMapperTest {
	@Autowired
	private PostMapper postMapper;

	@Test
	@DisplayName("listAllPost Test")
	public void listAllPost() {

		try {
			List<PostVO> l = postMapper.listAllPost("000p", new PagingDTO(0));
			for (PostVO p : l) {
				System.out.println(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
