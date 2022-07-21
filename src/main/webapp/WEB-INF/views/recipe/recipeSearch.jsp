<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- jstl core 쓸때 태그에 c 로 표시. -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- jstl fmt 쓸때 위와 같음. fmt : formatter 형식 맞춰서 표시 -->
<%@ include file="../includes/header.jsp"%>
<link rel="stylesheet" type="text/css"
  href="/resources/css/board/recipeSearch.css">
<title>레시피 조회</title>
  <div id="Recipebody">
    <div class="recipe-header">
      <h1 id="recipe-title">레시피 조회</h1>
      <!-- 검색창 시작 -->
      <div class="search_box">
        <select class="search-choice" name="type">
          <option value="T"
            ${pageMaker.cri.type eq "T"?"selected":"" }>제목</option>
          <option value="C"
            ${pageMaker.cri.type eq "C"?"selected":"" }>내용</option>
          <option value="W"
            ${pageMaker.cri.type eq "W"?"selected":"" }>작성자</option>
          <option value="M"
            ${pageMaker.cri.type eq "M"?"selected":"" }>재료</option>
        </select> <input type="text" maxlength="225" id="keyword"
          name="keyword" value="${pageMaker.cri.keyword }" onkeyup="enterkey()">
        <button type="button" class="searchBtn">
          <img class="recipesearch-text"
            src="/resources/images/icon_search.png">
        </button>
        <form id="actionForm" action="/recipe/recipeSearch">
          <input type="hidden" name="type"
            value="${pageMaker.cri.type }"> <input
            type="hidden" name="keyword"
            value="${pageMaker.cri.keyword }">
        </form>
      </div>
      <!-- 검색창 끝 -->
      <button class="recipe-writeBtn">글쓰기</button>
    </div>
    <!-- 레시피 메뉴 목록 시작 -->
    <ul class="recipe-nav">
      <c:forEach var="recipe" items="${CARD.LIST }">
        <li class="nav-list">
          <button type="button" class="nav-item">${recipe.REP_MAIN_C_CD }</button>
        </li>
      </c:forEach>
    </ul>
    <!-- 레시피 메뉴 목록 끝 -->
    <div class="a0">
      <c:forEach var="recpCard" items="${RECP.LIST }">
        <a href="/recipe/recipeinfo?repNo=${recpCard.REP_NO}">
          <div class="a1 recipeInfo">
            <div class="a2">
              <img src="${recpCard.REP_IMG_PATH}"
                class="recipe-text">
            </div>
            <div class="ap RS-tilte">${recpCard.REP_NM }</div>
            <div class="ap RS-content">${recpCard.REP_CTNT }</div>
            <div class="ap RS-writer">
              <small>${recpCard.MNG_NM }</small>
            </div>
            <div class="ap a6">
              <small>${recpCard.VIEW_CNT }</small>
            </div>
          </div>
        </a>
      </c:forEach>
    </div>
  </div>
<!-- 스크립트 -->
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script type="text/javascript"
  src="/resources/js/board/recipeSearch.js"></script>