<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- jstl core 쓸때 태그에 c 로 표시. -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- jstl fmt 쓸때 위와 같음. fmt : formatter 형식 맞춰서 표시 -->
<%@ include file="../includes/header.jsp"%>
<link rel="stylesheet" type="text/css" href="/resources/css/mateChoice.css">


 <title>재료 넣기</title>
 
<!-- 재료넣기 시작. -->
<div class="myMatmanage">
	<h2>재료 넣기</h2>
	<div class=myMat-search>
		<input type="text" class="search"/>
		<button type="submit" class="searchBtn">검색</button>
	</div>
	<!-- 검색 끝. -->

	<!-- 재료넣기 박스 시작. -->
	<div class="myMat-main">
		<div class="myMat-category">
			<div class="myMat-mainCategoryDiv">
				대분류
				<div class="myMat-mainCategory">
					<ul>
						<c:forEach var="mateList" items="${MATE.LIST}" varStatus="status">
							<li><button type="button">${mateList.MAIN_C_CD}</button></li>
						</c:forEach>
					</ul>
					
				</div>
			</div>
			<div class="myMat-middleClassDiv">
				중분류
				<div class="myMat-middleClass">
					<ul>
					</ul>
				</div>
			</div>
			<div class="myMat-subCategoryDiv">
				소분류
				<div class="myMat-subCategory">
					<ul class="subCategoryUl">
					</ul>
				</div>
			</div>
		
		<div class="myMat-putIn">
			<div class="myMat-putInAll">
				<div class="myMat-putInAllNM">
					<div class="myMat-putInName">
						<h3>담긴 재료</h3>
						<button type="reset" name="all" class="allRemove" value="전체 선택해제">전체 선택해제</button>
					</div>
					<div class="myMat-putInMat">
						<ul class="myMat-putInMatList" id="myMat-putList">
							<li>
								<button type="button" value="돼지고기(목살)">
									돼지고기(목살)
									<p class="remove">x</p>
								</button>
							</li>
							<li><button type="button" value="소세지">
									소세지
									<p class="remove">x</p>
								</button></li>
							<li>
								<button type="button" value="우유">
									우유
									<p class="remove">x</p>
								</button>
							</li>
							<li>
								<button type="button" value="사과">
									사과
									<p class="remove">x</p>
								</button>
							</li>
							<li>
								<button type="button" value="토마토">
									토마토
									<p class="remove">x</p>
								</button>
							</li>
							<li>
								<button type="button" value="소고기(등심)">
									소고기(등심)
									<p class="remove">x</p>
								</button>
							</li>
						</ul>
					</div>
				</div>
				<div class="myMat-chooseRef">
					<button type="button" class="coldRoom" onclick='goColdRoom(this)'>냉장실에 넣기</button>
					<button type="button" class="freezer" onclick='goFreezer(this)'>냉동실에 넣기</button>
				</div>
			</div>

			<div class="myMat-putInRef">
				<div class="myMat-coldRoom">
					<h4>냉장 보관실</h4>

					<ul class="myMat-coldRoomList">
						<li><button type="button" value="소세지">
								소세지
								<p class="remove">x</p>
							</button></li>
					</ul>
				</div>
				<div class="myMat-freezer">
					<h4>냉동 보관실</h4>
					<ul class="myMat-freezerList">
						<li><button type="button" value="소고기(등심)">
								소고기(등심)
								<p class="remove">x</p>
							</button></li>
					</ul>
				</div>
			</div>

		</div>


	</div>
	<!-- 재료넣기 박스 끝. -->
	
	<div class="myMat-searchPopup">
		<input onkeyup="filter()" type="text" id="popupSearchText" placeholder="재료 검색"/>
		<button type="submit">검색</button>
		<br>
		<p>검색 결과 : </p>
		<div class="myMat-searchResult">
		<c:forEach var="mateList" items="${MATE.LIST}" varStatus="status">
		대분류: ${mateList.MAIN_C_CD} &gt; 중분류: ${mateList.MID_C_CD} &gt; 소분류: &nbsp; <button type="button" class="finalResult">${mateList.MAT_NM}</button>
		</c:forEach>
		</div>
		
		<div class="myMat-closeBtn">
			<button type="button">[닫기 x]</button>
		</div>
	</div>
	<div class="myMat-matCloseBtn">
		<button type="button">저장</button>
	</div>
	
	
	
	
	</div>
</div>



<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="/resources/js/mateChoice.js"></script>

