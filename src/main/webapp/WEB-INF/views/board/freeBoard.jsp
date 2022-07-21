<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- jstl core 쓸때 태그에 c 로 표시. -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- jstl fmt 쓸때 위와 같음. fmt : formatter 형식 맞춰서 표시 -->
<%@ include file="../includes/header.jsp"%>

<link rel="stylesheet" type="text/css"
	href="/resources/css/board/freeBoard.css">

<div class="freeboard-header">
	<h1 id="freeboard-title">자유 게시판</h1>
	
	
	 <!-- 검색창 시작  -->
  <div class="freeboard-search_box">
    <select class="freeboard-search-choice" name="type">
      <option value="T" ${pageMaker.cri.type eq "T"?"selected":"" }>제목</option>
      <option value="W" ${pageMaker.cri.type eq "W"?"selected":"" }>작성자</option>
    </select> 
    <input type="text"  maxlength="225" id="keyword" name="keyword" value="${pageMaker.cri.keyword }">
    <button type="button" class="searchBtn">
      <img class="freeboard-search-text" src="/resources/images/icon_search.png">
    </button>
    <form id="actionForm" action="/board/freeBoard">
      <input type="hidden" name="type" value="${pageMaker.cri.type }">
      <input type="hidden" name="keyword" value="${pageMaker.cri.keyword }">
    </form>
  </div>
  <!-- 검색창 끝 -->
	
	<button class="freeboard-writeBtn">
	글쓰기
	</button>
</div>
<table>
	<colgroup>
		<col class="size01" data-alias="number">
		<col class="size02" data-alias="subject">
		<col class="size03" data-alias="writer">
		<col class="size04" data-alias="date">
		<col class="size05" data-alias="hit">
		<col class="size06" data-alias="req">
	</colgroup>
	<thead>
		<tr>
			<td>글번호</td>
			<td>제목</td>
			<td>작성자</td>
			<td>등록일</td>
			<td>조회수</td>
			<td>추천수</td>
		</tr>
	</thead>
	<tbody>
  <div class="board-cover">
		<c:forEach var="board" items="${FRBD.LIST }">
			<input type="hidden" id="pstNo" value="${board.PST_NO}"/>
			<tr class="free-notice">
				<td id="pstNo" class="free-bno">${board.PST_NO}</td>
				<td class="free-title"><a href="/board/freeBoardInfo?pstNo=${board.PST_NO}">${board.REP_NM }</a></td>
				<td class="free-writer">${board.MNG_NM }</td>
				<td class="free-regdate">${board.MNG_REG }</td>
				<td class="free-viewCnt">${board.VIEW_CNT }</td>
				<td class="free-likeCnt">1</td>
			</tr>
		</c:forEach>
   </div>
	</tbody>
</table>
<!-- <div class="freeboard-paging"> -->
<!-- 	<ul> -->
<!-- 		<li>이전</li> -->
<!-- 		<li>1</li> -->
<!-- 		<li>2</li> -->
<!-- 		<li>3</li> -->
<!-- 		<li>4</li> -->
<!-- 		<li>5</li> -->
<!-- 		<li>6</li> -->
<!-- 		<li>7</li> -->
<!-- 		<li>8</li> -->
<!-- 		<li>9</li> -->
<!-- 		<li>10</li> -->
<!-- 		<li>다음</li> -->
<!-- 	</ul> -->
<!-- </div> -->

<script type="text/javascript" src="/resources/js/board/freeBoard.js"></script>
</body>
</html>