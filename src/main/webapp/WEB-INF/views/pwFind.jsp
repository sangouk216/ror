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
<link rel="stylesheet" type="text/css"
	href="/resources/css/pwFind.css">

<body>
	<div class="pwFind">
		<h3>비밀번호 찾기</h3>
			<hr>
			<p id="gingle">비밀번호를 찾고자 하는 아이디와 핸드폰번호를 입력해 주세요.
		<div class="a09">
		
		<input type="text" class="pwinid" id="pwid" placeholder="냉부 아이디">
		<input type="text" class="pwinid" id="phonefind" maxlength="13" minlength="13"  placeholder="핸드폰 번호">
		<button type="submit" class="infobtnid" id="ingo" disabled="true">인증 번호 발송</button>
		<button type="button" class="infobtnid" id="inok" disabled="true">인증 확인</button>
		<input type="text" class="pwinid" id="innum" placeholder="인증번호" readonly="true" value="">
		<input type="hidden" id="innum2" value="">
 		<input type="hidden" id="inres" name="inres" value="0"> 
		
	</div>
		<div>
			<button type="button" class="infobtnpw" id="next" disabled="true">비밀번호 찾기</button>
			<button type="button" class="infobtnid" id="idfind">아이디 찾기</button>
		</div>

		<div class="pwchangemd" id="pwmd">
			<input type="password" class="pwchang" id="imsipw" value="" placeholder="임시 비밀번호"> 
			<input type="password" class="pwchang" id="imsipw2" value="" placeholder="임시 비밀번호 확인"> 
			<button class="infobtnid" id="pwchok">완료</button>
			
			<img class="modal_close_btn" src="/resources/images/btn_quick_close.png">
		</div>
		

	</div>
		<script type="text/javascript" src="/resources/js/pwFind.js"></script>
	
</body>
</html>