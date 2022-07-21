<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- jstl core 쓸때 태그에 c 로 표시. -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- jstl fmt 쓸때 위와 같음. fmt : formatter 형식 맞춰서 표시 -->
<%@ include file="includes/header.jsp"%>

<link rel="stylesheet" href="/resources/css/login.css"/>

<div class="login-container">
	<h1 style="color: #204A3F">ID 로그인</h1>
	<form class="loginForm" action="<c:url value="/ror/custLoginProcess"/>" method="post">
		<input class="loginInput" id="custId" name="id"type="text" placeholder="아이디" />
		<input class="loginInput" id="custPw" name="pw"type="password"
			placeholder="비밀번호" />

<!-- 		<div class="rememberLogin"> -->
<!-- 			<label> <input class="rememberLoginInput" type="checkbox" -->
<!-- 				value="rememberIdPw" /> 비밀번호 기억하기 -->
<!-- 			</label> -->
<!-- 		</div> -->

		<button class="loginTryBtn" type="submit">로그인</button>
	</form>
	<div class="searchInfo">
		<span class="infofind">아이디 / 비밀번호 찾기</span>
		&nbsp;|&nbsp;
		<span class="joinBtn">회원가입</span>
	</div>
</div>




<script src="/resources/js/login.js"></script>
</body>
</html>
