package www.dream.bbs.movie.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import www.dream.bbs.movie.mapper.ImgMapper;
import www.dream.bbs.movie.model.MovieDTO;
import www.dream.bbs.movie.model.MovieImgDTO;

@Service
public class ImgService {

	@Autowired
	private ImgMapper imgMapper;

	// 영화 아이디를 통한 중복검사 // 이미지를 받는데, 중복되면 안돼기때문.
	public int saveImg(MovieImgDTO movieImgDTO) {
		int id = movieImgDTO.getId();
		boolean isNew = imgMapper.findByImgId(id);
		if (isNew)
			return imgMapper.saveImg(movieImgDTO);
		else {
			return 0;
		}
	}
	//영화 id로 검색해서 구분하여 포스트를 출력하는 기능(React로 넘김)
	public List<MovieImgDTO> listMoviePost(Integer id) {
		List<MovieImgDTO> postList = imgMapper.listMoviePost(id);
		return postList;
	}

}
