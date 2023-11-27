package www.dream.bbs.party.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import www.dream.bbs.party.mapper.Movie4PartyMapper;
import www.dream.bbs.party.model.Person4PartyDTO;
import www.dream.bbs.party.model.TmdbCompanyVO;
@Service
public class Movie4PartyService {

	@Autowired
	private Movie4PartyMapper movie4PartyMapper;
	
	//Party 쿼리에 들어가는 영화 제작사 정보.
	public int listAllComp(List<TmdbCompanyVO> movie4pList) {
		String descrim = "producer";
		List<String> listIdString = movie4pList.stream().map(obj -> obj.getStringId()).collect(Collectors.toList());
		List<String> listExisting = movie4PartyMapper.findExistings(listIdString, descrim);
		List<TmdbCompanyVO> listNew = movie4pList.stream().filter(obj -> !listExisting.contains(obj.getStringId()))
				.collect(Collectors.toList());
		return movie4PartyMapper.listAllComp(listNew);
	}
	
	//Party 쿼리에 들어가는 영화 관계자들 정보.
	public int listAllCredit(List<Person4PartyDTO> creditLists) {
		String descrim = "person";
		List<String> listIdString = actorLists.stream().map(obj -> obj.getPersonId()).collect(Collectors.toList());
		List<String> listExisting = movie4PartyMapper.findExistings(listIdString, descrim);
		List<Person4PartyDTO> listNew = actorLists.stream().filter(obj -> !listExisting.contains(obj.getPersonId()))
				.collect(Collectors.toList());
		return movie4PartyMapper.insertAllActor(listNew);
		
	}
}
