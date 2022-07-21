
<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- jstl core 쓸때 태그에 c 로 표시. -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- 로그인시 개인정보 가져오기 위해서 임포트 -->
<%@ taglib uri="http://www.springframework.org/security/tags"
  prefix="sec"%>
<%@ page import="kr.ror.controller.MainController"%>
<%
// request.setCharacterEncoding("UTF-8");
// // 쿠키 지정 name, value
// Cookie cookie = new Cookie("abc", "123");
// // 쿠키의 유효시간 설정
// cookie.setMaxAge(60*60);
// // 쿠키 추가
// response.addCookie(cookie);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport"
  content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<title>냉장고 속 레시피</title>
<link rel="stylesheet" type="text/css"
  href="/resources/css/slide.css" />
<link rel="stylesheet" type="text/css"
  href="/resources/css/includes/footer.css" />
<link rel="stylesheet" type="text/css"
  href="/resources/css/includes/header.css" />
<link rel="stylesheet" type="text/css"
  href="/resources/css/main.css" />
<link rel="stylesheet" type="text/css"
  href="/resources/css/popUp.css" />
<link rel="shortcut icon" href="/resources/images/favicon.png">
</head>
<div class="headermenu">
  <div class="logo">
    <img class="home-icon" src="/resources/images/logo.png">
    <a href=""></a>
  </div>
  <div>
    <!-- 		<span class="slide-btn" onclick="ToggleBtn()">  -->
    <span class="slide-btn"><a onclick="menuBar();"
      href="javascript:"> <img class="menu-icon"
        src="/resources/images/slide2.png">
    </a> </span>
  </div>
</div>
<main class="nav-body" id="nav-body">
  <!-- 전체 감싸는 BODY 부분 -->
  <div class="nav-allSlide" id="nav-menu">
    <div class="nav-menuLayer" id="nav-menuLayer">
      <!-- 레이어 부분(왼쪽 슬라이드 부분만) -->
      <nav class="nav-menuInfo">
        <!-- 실질적 영역 -->
        <div class="nav-menuInfoHeader">
          <!-- 사용자 정보 비로그인시 여기를 변환(사진,닉네임, 마이페이지, 로그아웃 부분) -->
          <div class="nav-userInfo">
            <!-- 비로그인시 로그인 버튼 표시-->
            <sec:authorize access="isAnonymous()">
<%--               <a href="<c:url value="/ror/login" />"> --%>
              <button type="button" class="nav-loginBtn">로그인</button>
<!--               </a> -->
              <div class="nav-menuInfoFooter">
                <div class="nav-dropdown">
                  <button class="boardBtn nav-recipeboard">레시피
                    게시판</button>
                  <button class="boardBtn nav-freeboard">자유
                    게시판</button>
                </div>
              </div>
            </sec:authorize>
            <!-- 마이페이지, 로그아웃 -->
            <sec:authorize access="isAuthenticated()">
              <div>
                <div>
                  <div class="nav-MenuInfoLogin">
                    <div class="nav-linkIcon" id="moveMyPage">
                      <button type="button" class="navBtn"
                        name="nav-mypage">마이페이지</button>
                    </div>
                    <!-- 로그인 시 로그아웃 버튼 표시 -->
                    <div class="nav-linkIcon" id="moveLogout">
                      <form class="logoutForm"
                        action="<c:url value="/ror/logout"/>">
                        <button type="submit" class="navBtn"
                          id="logoutBtn" name="nav-logout">로그아웃</button>
                      </form>
                      <!--버튼 누르면 권한사라지고 로그인/회원가입페이지 반환 -->
                    </div>
                  </div>
                  <div class="nav-userImage">
                    <div class="userImage">
                      <!-- <img id="userIcon" src="/resources/images/basic_user.png"> -->
                      <img id="userIcon"
                        src="/resources/images/profile.jpg">
                    </div>
                    <div class="nav-userName">
                      <c:forEach var="userInfo"
                        items="${USERINFO.LIST }">
                        <input type="hidden" id="memNo"
                          name="memNo" value="${userInfo.MEM_NO }">
<%--                         <div id="custId">(${userInfo.CUST_ID })</div> --%>
                        <div id="custNm" class="nav-nameBtn">${userInfo.CUST_NM }</div>
                        <div id="custReg"
                          style="margin-bottom: 5px">가입일:${userInfo.CUST_REG }</div>
                      </c:forEach>
                      <div>환영합니다!</div>
                    </div>
                    <!-- DB 유저이름 가져오기 -->
                  </div>
                </div>
<!--                 <div class="nav-materOrg"> -->
<!--                   재료정리 기능 -->
<!--                   <button id="nav-materOrgBtn" name="MeterOrg">재료정리</button> -->
<!--                 </div> -->
              </div>
              <div class="nav-menuInfoFooter">
                <div class="nav-dropdown">
                  <button class="boardBtn nav-recipeboard">레시피
                    게시판</button>
                  <button class="boardBtn nav-freeboard">자유
                    게시판</button>
                </div>
                <!-- 즐겨찾기 -->
                <!--           <div class="nav-favo"> -->
                <!--             <form class="nav-dropdown-header likeboard"> -->
                <!--               <select class="slideMenu-select"> -->
                <!--                 <option value="none">즐겨찾기</option> -->
                <!--                 <option value="">즐겨찾기 링크1</option> -->
                <!--                 <option value="">즐겨찾기 링크2</option> -->
                <!--                 <option value="">즐겨찾기 링크3</option> -->
                <!--                 <option value="">즐겨찾기 링크4</option> -->
                <!--               </select> -->
                <!--               <button class="nav-linkBtn">이동</button> -->
                <!--             </form> -->
                <!--           </div> -->
                <!-- 즐겨찾기 끝 -->
              </div>
              <!-- 관리자 시작-->
              <hr>
              <div class="nav-MGhead">관리자 페이지</div>
              <div class="nav-MGmenuInfo">
                <button type="button" class="nav-MGBtn"
                  id="userMGBtn">사용자 관리</button>
                <!-- <div class="MGcommon userMG-CNT">
						<button type="button" class="userMG1">아이디 관리</button>
						<button type="button" class="userMG2">비밀번호 관리</button>
					</div> -->
                <button type="button" class="nav-MGBtn"
                  id="materialMGBtn">재료 관리</button>
                <!-- 					<div class="MGcommon materialMG-CNT"> -->
                <!-- 						<button type="button" class="materialMG1">재료1</button> -->
                <!-- 						<button type="button" class="materialMG2">재료2</button> -->
                <!-- 					</div> -->
                <!-- 					<button type="button" class="nav-MGBtn" id="recipeMGBtn">레시피 메뉴 관리</button> -->
                <!--<div class="MGcommon recipeMG-CNT">
						 <button type="button" class="recipeMG1">레시피 메뉴 관리</button>
						<button type="button" class="recipeMG2">레시피 내용 관리</button>
					</div> -->
                <button type="button" class="nav-MGBtn"
                  id="popupMGBtn">공지 사항 관리</button>
                <!-- <div class="MGcommon popupMG-CNT">
						<button type="button" class="popupMG1">팝업 공지 관리</button>
						<button type="button" class="popupMG2">자유 게시판 공지 관리</button>
						<button type="button" class="popupMG3">레시피 게시판 공지 관리</button>
						<button type="button" class="popupMG4">배너광고 관리</button>
					</div> -->
              </div>
            </sec:authorize>
          </div>
        </div>
      </nav>
    </div>
    <!-- 관리자 끝-->
  </div>
</main>
<!--   	        		사용자 정보 비로그인시 변환되는 값
						<div class="nav-menu-info-header"> 
                    	<div class="user-image">
                  			<img id="user-icon"src="resources/images/icon1.png" width="100" height="100">
 						</div>
                        <div class="user-info">
                        	<div class="user-name">닉네임</div> DB 유저이름 가져오기
                        	  <div class="nav-menu-info-login">
                            	<div class="nav-link-icon">
                            	<a class="nav-link" href="mypage.html">마이페이지</a>
                            	</div>
                            	<div class="nav-link-icon">
                            	<a class="nav-link">로그아웃</a> 버튼 누르면 권한사라지고 로그인/회원가입페이지 반환
                            	</div>
                            </div>
                        </div>
  	        		</div> -->
<div class="container-fluid">
  <!-- 메뉴 슬라이드 끝 -->
  <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
  <script type="text/javascript"
    src="/resources/js/includes/header.js"></script>
  <script type="text/javascript" src="/resources/js/slide.js"></script>