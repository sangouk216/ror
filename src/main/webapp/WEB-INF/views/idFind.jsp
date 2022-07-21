<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- jstl core 쓸때 태그에 c 로 표시. -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="includes/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<link rel="stylesheet" type="text/css" href="/resources/css/idFind.css">

<body>
	<div class="idFind">
		<h3>아이디 찾기</h3>
		<hr>
		<p id="gingle">아이디를 찾고자 하는 닉네임과 핸드폰번호를 입력해 주세요.
		<div class="a09">

			<input type="text" class="idinid" id="phnumidfi" maxlength="13"
				placeholder="핸드폰 번호">
			<button type="button" class="infobtn" id="ingo" disabled="true">인증번호
				발송</button>
			<button type="button" class="infobtnid" id="inok" disabled="true">인증확인</button>
			<input type="text" class="idinid" id="innum" placeholder="인증번호">
			<input type="hidden" id="innum2" value=""> <input
				type="hidden" id="inres" name="inres" value="0">

		</div>
		<div>
			<button type="button" class="infobtnpw" id="next">비밀번호 찾기</button>
			<button type="button" class="infobtnd" id="idfindd" disabled="true">아이디
				찾기</button>
		</div>


		<div id="idchangemd">
			<div id="idsh">
				<p>아이디는</p>
				<p id="idshow"></p>
				<p>입니다</p>
			</div>
			<button class="infobtnid" id="dlehdlogin">로그인</button>

			<img class="modal_close_btn"
				src="/resources/images/btn_quick_close.png">
		</div>

	</div>
	<script type="text/javascript" src="/resources/js/idFind.js"></script>

</body>
</html>