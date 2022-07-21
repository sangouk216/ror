<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- jstl core 쓸때 태그에 c 로 표시. -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- jstl fmt 쓸때 위와 같음. fmt : formatter 형식 맞춰서 표시 -->
<%@ include file="../includes/header.jsp"%>

<link rel="stylesheet" type="text/css" href="/resources/css/includes/header.css">
<link rel="stylesheet" type="text/css" href="/resources/css/board/recipe.css">

<main>
	<div class="recipe-border">
	<c:forEach var="recipeInfo" items="${INFO.LIST }">
	<input type="hidden" id="repNo" value="${recipeInfo.REP_NO }"/>
		<div class="uptime">작성일 : <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${recipeInfo.MNG_REG }"/></div>
		<div class="nav-right">
			<span>즐겨찾기 ${recipeInfo.VIEW_CNT } <img id="bookmark-show" src="/resources/images/star.png"></span>
			<span><img id="bookmark-hide" src="/resources/images/star1.png"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<span>좋아요 ${recipeInfo.LIKE_CNT }<img id="bookmark-show1" src="/resources/images/heart.png"></span>
			<span><img id="bookmark-hide1"src="/resources/images/heart1.png"></span>
		</div>
		<div class="recipe">
			<div>
				<img src="${recipeInfo.REP_IMG_PATH }" id="main-image">
			</div>
			<div>작성자 : ${recipeInfo.MNG_NM }</div>
			<div class="recipe-title">
				<h2>제목 : ${recipeInfo.REP_NM }</h2>
				<p id="recipe-sub-title">한 줄 설명 : ${recipeInfo.REP_CTNT }<br>
			</div>
			
			<h3 style="border-bottom : 1px solid #ffda33;">#재료#</h3>
				<div class="item">
					<div class="item-section">
						<div class="section">
							${recipeInfo.MATE_NM }
						</div>	
					</div>
				</div>

		   <div class="recipe-order">
      <h3>#요리 순서#</h3>
      ${recipeInfo.REP_CTNT }
      </div>
      </div>
      <div class="cover-recipe-button">
        <button class="recipe-button-update" type="button" onclick="location.href='./recipeUpdate?repNo=${recipeInfo.REP_NO}'">수정</button>
        <button class="recipe-button-delete" type="button" onclick="recipeDelete()">삭제</button>
        <button class="recipe-button-delete" type="button" onclick="location.href='/recipe/recipeSearch'">목록</button>
      </div>
      
      <!-- 댓글 등록창 시작 -->
      <div class="comment"><h2 id="comment-top">댓글</h2></div>
      <div class="comment-a">
      <!-- <div class="comment-id"> -->
        <input type="hidden"  id="memNo" value="${recipeInfo.MEM_NO }">
        <input class="comment-input" id="name" type="text" 
        placeholder="댓글 달기" onfocus="this.placeholder=''" onblur="this.placeholder='댓글 달기'">
      <!-- </div> -->
        <button class="comment-button" type="button">등록</button>
      </div>
      <!-- 댓글 등록창 끝 -->
      
      <!-- 댓글 목록 시작  -->
      <div class="comment-id">
        <div>사용자 아이디</div>
        <div id='result'>리플</div>
<!--         <button class="recipe-button-update" type="button" onclick="">수정</button> -->
<!--         <button class="recipe-button-delete" type="button" onclick="">삭제</button> -->
      </div>
      <!-- 댓글 목록 끝  -->
    </c:forEach>
	</div>
</main>
	<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
	<script type="text/javascript" src="/resources/js/board/recipe.js"></script>
