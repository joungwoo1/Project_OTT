package www.dream.bbs.movie.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import www.dream.bbs.framework.model.MasterEntity;
import www.dream.bbs.framework.property.anno.TargetProperty;
import www.dream.bbs.user.model.UserVO;

@Getter
@Setter
@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class MovieEvalVO extends MasterEntity {
	@TargetProperty // Proxy를 통한 접근 시 null이 나오는 현상
	private UserVO writer;
	@TargetProperty
	private String content;
	private String writerId;
	private float starScore;
	private MovieEvalVO myEval;
	// 대댓글 구조 만들기는 어떻게?
	private List<MovieEvalVO> listEval = new ArrayList<>();

	public String extractParentId() {
		String myId = super.getId();
		int len = myId.length();
		return myId.substring(0, len - ID_LENGTH);
	}
	
	public void appendReply(MovieEvalVO eval) {
		listEval.add(eval);
	}

	public MovieEvalVO(String id, UserVO writer, String content, String writerId, float starScore, MovieEvalVO myEval,
			List<MovieEvalVO> listEval) {
		super(id);
		this.writer = writer;
		this.content = content;
		this.writerId = writerId;
		this.starScore = starScore;
		this.myEval = myEval;
		this.listEval = listEval;
	}
}
