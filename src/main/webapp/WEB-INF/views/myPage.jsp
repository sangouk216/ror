<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- jstl core 쓸때 태그에 c 로 표시. -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- jstl fmt 쓸때 위와 같음. fmt : formatter 형식 맞춰서 표시 -->
<%@ include file="includes/header.jsp"%>
<link rel="stylesheet" type="text/css" href="/resources/css/myPage.css" />

<body>
<c:forEach var="userInfo" items="${USERINFO.LIST }">
	<div class="all_wrap">
		<div class="myForm_wrap">
			<div class="myForm">
				<div class="photo_coat">
					<img class="photo" src="/resources/images/profile.jpg">
				</div>
				<p class="photo_msg" >${userInfo.CUST_ID }</p>
        <input type="hidden" id="custId" name="custId" value="${userInfo.CUST_ID }" readonly>
				<div class="coat_section" id="nickName_section">
					<div class="label_coat">
						<label>닉네임</label>
					</div>
					<div class="input_coat">
						<input type="text" id="custNm2" name="custNm" class="input_box"  placeholder=""
							onfocus="this.placeholder='닉네임을 입력하세요'"
							onblur="this.placeholder=''" required
              value="${userInfo.CUST_NM }">
					</div>
				</div>
				<div class="coat_section">
					<div class="label_coat">
						<label>비밀번호</label>
					</div>
					<div class="input_coat">
						<input type="password" id="custPw2" class="input_box" 
							placeholder="" onfocus="this.placeholder='비밀번호를 입력하세요'"
							onblur="this.placeholder=''" required>
					</div>
				</div>
				<div class="coat_section">
					<div class="label_coat">
						<label class="err_label">비밀번호 확인</label>
						<p id="error_msg">비밀번호가 일치하지 않습니다</p>
					</div>
					<div class="input_coat">
						<input type="password" id="custPw" name="custPw" class="input_box"
							placeholder="" onfocus="this.placeholder='비밀번호를 입력하세요'"
							onblur="this.placeholder=''" required>
					</div>
				</div>
				<div class="coat_section">
					<div class="label_coat">
						<label>핸드폰번호</label>
					</div>
					<div class="input_coat">
						<input type="tel" id="custTel" name="custTel" class="input_box" 
            placeholder="" onfocus="this.placeholder='핸드폰번호를 입력하세요'"
							onblur="this.placeholder=''" required value="${userInfo.CUST_TEL }">
					</div>
				</div>
				<div class="button_coat">
					<button type="submit" id="userSaveBtn">정보수정</button>
				</div>
			</div>

		</div>
	</div>
</c:forEach>
<script type="text/javascript" src="/resources/js/myPage.js"></script>
</body>
</html>