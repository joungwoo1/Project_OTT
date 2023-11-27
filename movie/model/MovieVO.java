package www.dream.bbs.movie.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import www.dream.bbs.board.model.BoardVO;
import www.dream.bbs.fileattachment.model.MappedTableDef;
import www.dream.bbs.fileattachment.model.dto.AttachFileDTO;
import www.dream.bbs.framework.property.anno.TargetProperty;

@Getter
@Setter
@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class MovieVO extends MovieEvalVO implements MappedTableDef {
	public String getMappedTableName() {
		return "T_Eval";
	}

	private BoardVO boardVO;
	private MovieDTO movieDTO;
	@TargetProperty
	private String title;
	private String genre;
	private String ageLimit;
	private int readCnt;

	/** DTO로 활용되는 속성 추가적 정의부분. */
	private List<String> listTag;

	private List<AttachFileDTO> listAttachFile;

	public void incReadCnt() {
		readCnt++;
	}
}
