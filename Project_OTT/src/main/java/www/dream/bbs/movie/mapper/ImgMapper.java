package www.dream.bbs.movie.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import www.dream.bbs.movie.model.MovieImgDTO;

@Mapper	// Container에 담기도록 지정
public interface ImgMapper {
	
	//영화 이미지를 저장, 영화file_Path 확인 후 중복 시 패스시키는 부분
	public int saveImg(MovieImgDTO movieImgDTO);
	//영화 ListID를 찾는다.
	public boolean findByImgId(int id);
	//영화 id를 통해 검색하여, 그 영화 맞는 포스트를 출력한다(React로 넘김)
	public List<MovieImgDTO> listMoviePost(Integer id);
}

