<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- jstl core 쓸때 태그에 c 로 표시. -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- jstl fmt 쓸때 위와 같음. fmt : formatter 형식 맞춰서 표시 -->
<%@ include file="../includes/header.jsp"%>
<link rel="stylesheet" type="text/css"
	href="/resources/css/board/freeBoardInfo.css">
<div class="freeInfo-header">
	<h1 id="freeInfo-title">자유 게시판</h1>
</div>
<div class="free-body">
<c:forEach var="freeInfo" items="${FRIN.LIST }">
	<input type="hidden" id="pstNo" name="pstNo" value="${freeInfo.PST_NO }"/>
	<div class="free-header">
		<div class="free-writer">작성자:${freeInfo.MNG_NM }</div>
		<div class="free-title">제목:${freeInfo.REP_NM }</div>
		<div class="free-sideGroup">
		<div class="free-viewCnt">조회수:${freeInfo.VIEW_CNT }</div>
		<div class="free-regdate">작성일:<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${freeInfo.MNG_REG }"/></div>
		</div>
	</div>
	<div>
		<div class="free-content">
			<div>${freeInfo.PST_CTNT }</div>
		</div>
	</div>

		<div class="free-addfile">
			<p>*첨부파일*</p>
			<div>1.첨부파일예시</div>
			<div>2.첨부파일예시</div>
		</div>
	</c:forEach>
</div>


<div class="freeBtnGroup">
	<button type="button" class="free-list">목록</button>
	<button type="button" class="free-modify">수정</button>
	<button type="button" class="free-delete">삭제</button>
</div>


<div class="freeReply">
	<p>댓글</p>
	<div class="freeReplyInfo">
		<div class="freeReply-writer">작성자</div>
		<div class="freeReply-date">작성일</div>
		<div class="freeReply-content">내용</div>
	</div>
<!-- 	<div class="freeReplyInfo"> -->
<!-- 		<div class="freeReply-writer">작성자(ㅇㅅㅇ)</div> -->
<!-- 		<div class="freeReply-date">(2022-06-08)</div> -->
<!-- 		<div class="freeReply-content">요거트맛 노맛</div> -->
<!-- 	</div> -->
	<div class="freeReply-text">
  <c:forEach var="userInfo" items="${USERINFO.LIST }">
		<div class="login-user">${userInfo.CUST_NM }</div>
    </c:forEach>
		<textarea></textarea>
		<button type="button" class="freeReplyBtn">등록</button>
	</div>
</div>
<script type="text/javascript" src="/resources/js/board/freeBoardInfo.js"></script>
</body>
</html>