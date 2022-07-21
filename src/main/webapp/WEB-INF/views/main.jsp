<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- jstl core 쓸때 태그에 c 로 표시. -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- jstl fmt 쓸때 위와 같음. fmt : formatter 형식 맞춰서 표시 -->
<%@ include file="includes/header.jsp"%>
<%@ include file="admin/popup/popupShow.jsp"%>
<link rel="stylesheet" type="text/css" href="/resources/css/main.css" />
<hr>
<h3>여기가 시작 선</h3>

<!-- 실시간검색어 시작 -->
<div class="live">
	<dl id="rank-list">
		<dt>실시간 급상승 검색어</dt>
		<dd>
			<ol>
        <c:forEach var="recpCard" items="${RECP.LIST }" begin="0" end="9">
  				<li><a href="/recipe/recipeinfo?repNo=${recpCard.REP_NO }">${recpCard.REP_NM }</a></li>
        </c:forEach>
			</ol>
		</dd>
	</dl>
</div>
<!-- 실시간검색어 끝 -->
<div class="main">
	<div class="all-wrap">
		<!-- 술라이드 시작 -->
		<div class="slide-wrap">
			<h2 class="section__h2">" 추천메뉴 "</h2>
			<div id="slideShow">
				<ul class="slides">
          <c:forEach var="likeCard" items="${LCNT.LIST }">
            <div>< ${likeCard.REP_NM } ></div>
            <a href="/recipe/recipeinfo?repNo=${likeCard.REP_NO }">
					  <li><img class="mainBestMenuImg" src="${likeCard.REP_IMG_PATH }" alt="추천 메뉴"></li>
            </a>
          </c:forEach>
				</ul>
<!-- 				<p class="controller"> -->
<!-- 					<span class="prev">&lt;</span> <span class="next">&gt;</span> -->
<!-- 				</p> -->
			</div>
		</div>
		<!-- 슬라이드 종료 -->
		<hr>
		<!-- 금주의 랭킹 카드 시작 -->
		<section class="card-wrap section gmarket">
			<div class="container">
				<h2 class="section__h2">" 이번주 레시피 "</h2>
				<p class="section__desc"></p>
				<div class="card__inner">
            <c:forEach var="viewCard" items="${VCNT.LIST }" begin="0" end="3">
            <a class="card__btn" href="/recipe/recipeinfo?repNo=${viewCard.REP_NO }">
					<article class="card">
						<figure class="card__header">
							<img class="card__img" src="${viewCard.REP_IMG_PATH }"	alt="이미지1">
						</figure>
  						<div class="card__body">
    							<h5 class="card__title">${viewCard.REP_NM }</h5>
    							<p class="card__desc">${viewCard.REP_CTNT }</p>
    							 자세히 보기 <svg width="52"
    									height="8" viewBox="0 0 52 8" fill="none"
    									xmlns="http://www.w3.org/2000/svg">
    	                        <path
    										d="M51.3536 4.35355C51.5488 4.15829 51.5488 3.84171 51.3536 3.64645L48.1716 0.464466C47.9763 0.269204 47.6597 0.269204 47.4645 0.464466C47.2692 0.659728 47.2692 0.976311 47.4645 1.17157L50.2929 4L47.4645 6.82843C47.2692 7.02369 47.2692 7.34027 47.4645 7.53553C47.6597 7.7308 47.9763 7.7308 48.1716 7.53553L51.3536 4.35355ZM0 4.5H51V3.5H0V4.5Z"
    										fill="#5B5B5B" />
    	                    </svg>
    							
  						</div>
					</article>
          </a>
            </c:forEach>
				</div>
			</div>
		</section>
		<!-- 금주의 랭킹 카드 종료 -->
		<hr>
		<!-- 인기 식재료 카드 시작 -->
		<section class="card-wrap section gmarket">
			<div class="container">
				<h2 class="section__h2">" 이런 재료는 어때? "</h2>
				<p class="section__desc"></p>
				<div class="card__inner">
					<c:forEach var="mateCard" items="${MATERANK.LIST }" begin="0" end="3">
  					<article class="card">
  						<figure class="card__header">
  							<img class="card__img" src="/resources/images/favicon.png"
  								alt="이미지2">
  						</figure>
  						<div class="card__body">
  							<h3 class="card__title">${mateCard.MAT_NM }</h3>
  						</div>
  					</article>
					</c:forEach>
				</div>
			</div>
		</section>
		<!-- 인기 식재료 카드 종료 -->
		<hr>

		<!-- 계절 추천 레시피 시작 -->
		<div>
		</div>
		<!-- 계절 추천 레시피 종료 -->
	</div>
</div>
<script type="text/javascript" src="/resources/js/main.js"></script>
<%@ include file="includes/footer.jsp"%>
