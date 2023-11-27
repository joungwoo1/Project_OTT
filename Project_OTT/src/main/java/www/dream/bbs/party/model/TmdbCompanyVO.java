package www.dream.bbs.party.model;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class TmdbCompanyVO {
    	//GSON 방식 이름 변경해서 하는 것.
    	@SerializedName("id")
    	int companyId;
    	String stringId = Integer.toString(companyId);
    	@SerializedName("logo_path")
    	String logoPath;
    	@SerializedName("name")
    	String name;
}