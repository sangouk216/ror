<%@page import="kr.ror.common.dao.CommonDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- jstl core 쓸때 태그에 c 로 표시. -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- jstl fmt 쓸때 위와 같음. fmt : formatter 형식 맞춰서 표시 -->
<%@ include file="../includes/header.jsp"%>

<link rel="stylesheet" type="text/css" href="/resources/css/board/recipeUpdate.css">
<script>

</script>
<div class="recipe-header">
  <h1 id="recipe-title">레시피 수정</h1>
  <form method="post" action="/board/recipeBoard/updateRecipe">
  	<c:forEach var="load" items="${LOAD.LIST }">
  		<div class="recipe-body">
      <h2 id="recipe-subtitle">레시피 수정 페이지</h2>
      <div class="recipeRegister-title"><p>메뉴분류</p>
          <select class="maincode-choice" name="type" value="${load.repMainCd }">
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
      
<%--   			<input type="hidden" id="repMainCd" value="${load.repMainCd }"> --%>
<%--   			<input type="hidden" id="repMidCd" value="${load.repREP_NM }"> --%>
<%--   			<input type="hidden" id="memNo" value="${load.memNo }"> --%>
<%--   			<input type="hidden" id="writer" readonly="readonly" value="${load.MOD_NM }"> --%>
  			<input type="hidden" id="repNo" readonly="readonly" value="${load.REP_NO }">
        
        <div class="recipeRegister-title">
  				<p>제목</p>
  				<input type="text" id="repName" placeholder="제목을 작성하세요" value="${load.REP_NM }"/>
  		  </div>
      </div>
  		<div class="recipeRegister-title">
        <p>재료</p>
        <input type="text" id="repMidCd" value="${load.MATE_NM }"/>
      </div>
      <div class="recipe-content">
  			<p>내용</p>
  			<textarea id="repCtnt" placeholder="내용을 작성하세요">${load.REP_CTNT }</textarea>
  		</div>
  		<div class="recipe-addfile">
  			<p>첨부파일</p>
  			<button type="button" class="recipe-addfileBtn">선택</button>
  		</div>
  		<button type="submit" class="recipe-saveBtn" onclick="recipeUpdate()">글 수정하기</button>
  		<button type="button" class="recipe-backBtn" id="backBtn">돌아가기</button>
  	</c:forEach>
  </form>
</div>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="/resources/js/board/recipe.js"></script>