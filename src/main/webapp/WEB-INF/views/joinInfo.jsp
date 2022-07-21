<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- jstl core 쓸때 태그에 c 로 표시. -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- jstl fmt 쓸때 위와 같음. fmt : formatter 형식 맞춰서 표시 -->
<%@ include file="includes/header.jsp"%>

<link rel="stylesheet" type="text/css" href="/resources/css/join.css">
<link rel="shortcut icon" href="#">
<body>

	<div class="wrap_wrap">

		<div class="form_wrap">

			<div class="form_section">
			
				<div class="label_coat">
					<label for="id" class="form_label">아이디</label>
				</div>
				<div class="input_coat">
					<input type="text" class="input_box" id="userId" name="custid"
						placeholder="아이디를 입력하세요" onfocus="this.placeholder=''"
						onblur="this.placeholder='아이디를 입력하세요'" required>
					 <button type="button" id="ck_id" class="btn ck_btn">중복확인</button> 
				</div>
			</div>

			<!-- 모달 시작 -->
			<div class="modal" id="modal">
				<div class="modal_body">
					<div class="modal_msg" id="modal_msg">
						<span>${modalMsg }</span>
					</div>
				</div>
				<img class="modal_close_btn"
					src="/resources/images/btn_quick_close.png">
				<button type="button" id="toggle_btn" class="btn confirm_btn">사용하기</button>
			</div>
			<!-- 모달 끝 -->

			<div class="form_section">
				<div class="label_coat">
					<label for="pw1" class="form_label">비밀번호</label>
					<p id="error_msg" class="msg"></p>
				</div>
				<div class="input_coat">
					<input type="password" class="input_box" id="userPw1"
						name="cust_pw" placeholder="비밀번호를 입력하세요" onfocus="this.placeholder=''"
						onblur="this.placeholder='비밀번호를 입력하세요'" required>
				</div>
			</div>
			<div class="form_section">
				<div class="label_coat">
					<label for="pw2" class="form_label">비밀번호 확인</label>
					<p id="error_msg" class="msg"></p>
				</div>
				<div class="input_coat">
					<input type="password" class="input_box" id="userPw2"
						name="userPw2" placeholder="비밀번호를 입력하세요" onfocus="this.placeholder=''"
						onblur="this.placeholder='비밀번호를 입력하세요'" required>
				</div>
			</div>
			<div class="form_section">
				<div class="label_coat">
					<label for="nickName" class="form_label">닉네임</label>
				</div>
				<div class="input_coat">
					<input type="text" class="input_box" id="userNm" name="userNm"
						placeholder="닉네임을 입력하세요" onfocus="this.placeholder=''"
						onblur="this.placeholder='닉네임을 입력하세요'" required>
					<button type="button" id="ck_nickName" class="btn ck_btn">중복확인</button>
				</div>
			</div>
			<div class="form_section">
				<div class="label_coat">
					<label for="phoneNum" class="form_label">핸드폰번호</label>
				</div>
				<div class="input_coat">
					<input type="tel" class="input_box" id="userPhone" name="cust_tel"
						placeholder="전화번호를 입력하세요" onfocus="this.placeholder=''"
						onblur="this.placeholder='전화번호를 입력하세요'" 
						maxlength="13" required>
<!-- 					<button type="button" id="ck_phone" class="btn ck_btn">인증하기</button> -->
				</div>
			</div>
			<br>
      <input type="hidden" id="mngNm" value="SYSTEM">
			<div class="join_coat">
				<button type="submit" id="join_btnid" class="btn join_btn">가입하기</button>
			</div>
		</div>
	</div>

<!-- 	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script> -->
	<script type="text/javascript" src="/resources/js/join.js"></script>

</body>
