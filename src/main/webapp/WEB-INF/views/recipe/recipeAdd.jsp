<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- jstl core 쓸때 태그에 c 로 표시. -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- jstl fmt 쓸때 위와 같음. fmt : formatter 형식 맞춰서 표시 -->
<%@ include file="../includes/header.jsp"%>

<link rel="stylesheet" type="text/css" href="/resources/css/board/recipeAdd.css">
<script>

</script>
<div class="recipe-header">

	<h1 id="recipe-title">레시피 게시판</h1>
	
</div>
<form method="post" action="insert" >
	<div class="recipe-body">
		<h2 id="recipe-subtitle">레시피 작성 페이지</h2>
			<div class="recipeRegister-title">
					<p>메뉴분류</p>
					<select class="maincode-choice" name="type">
						<option value="">볶음밥</option>
						<option value="">분식</option>
						<option value="">수육</option>
						<option value="">튀김</option>
						<option value="">찌개</option>
						<option value="">구이</option>
						<option value="">조림</option>
						<option value="">국/탕</option>
					</select> 
			</div>
			<div class="recipeRegister-title">
				<p>제목</p><input type="text" id="repName" placeholder="제목을 작성하세요" value=""/>
			</div>
		
			<div class="recipeRegister-title">
				<p>재료</p><input type="text" id="repMidCd" value="${RECP.repMidCd }">
			</div>
			<div class="recipe-content">
				<p>내용</p><textarea id="repCtnt" placeholder="내용을 작성하세요" ></textarea>
			</div>
			<div class="recipe-addfile">
				<p>첨부파일</p>
				<button type="button" class="recipe-addfileBtn">선택</button>
			</div>
			<form id="actionForm" action="/recipe/recipeSearch">
<!-- 			로그인시큐리티 적용시 임의 벨류값 지우고 아래 인풋값 사용 -->
<%-- 				<input type="hidden" id="memNo" value="${RECP.memNo }"> --%>
				<input type="hidden" id="memNo" value="6">
				<input type="hidden" id="writer" value="${RECP.writer }">
			</form>
			<button type="button" class="recipe-saveBtn" onclick="recipeAdd()">글 등록하기</button>
			<button type="button" class="recipe-backBtn" onclick="location.href='./recipeSearch'">돌아가기</button>
	</div>
</form>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="/resources/js/board/recipeAdd.js"></script>