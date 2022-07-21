package kr.ror.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Criteria {
	// Criteria : 페이징 처리 기준을 갖는 밸류 오브젝트.

	private int pageNum; // 현재 페이지 번호.
	private int amount; // 페이지당 게시물 수.
	private String type; // 검색 타입. 내용(c)+제목(t)+작성자(w)
	private String keyword; // 검색어. 예) 새로

	public String[] getTypeArr() {
		// 검색 타입 배열 가져오기.
		return type == null ? new String[] {} : type.split("");
//		return type == null ? new String[] {} : type.split("");
		// 삼항연산자는 (조건)?참:거짓;
		// 검색타입이 널이라면 비여있는 문자열 배열을 만들고,
		// 그렇지 않다면, 검색타입을 한글자씩 잘라서 문자열 배열로 만듦.
	}

	public Criteria() {
		this(1, 10); // 아래쪽 전달값 2개 생성자 호출.
	}

	public Criteria(int pageNum, int amount) {

		this.pageNum = pageNum;
		this.amount = amount;
	}
}
