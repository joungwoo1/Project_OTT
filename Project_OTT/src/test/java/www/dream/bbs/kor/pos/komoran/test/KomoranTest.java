package www.dream.bbs.kor.pos.komoran.test;

import java.util.List;

import www.dream.bbs.framework.nlp.pos.service.NounExtractor;

public class KomoranTest {

	public static void main(String[] args) {
        String document = "우리집에는 강아지 네모가 있습니다. 네모는 밝은 성격입니다.";

        List<String> nounList = NounExtractor.extractNoun(document);
        for(String noun : nounList) {
            System.out.println(noun);
        }
	}
}
