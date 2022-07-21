<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- jstl core 쓸때 태그에 c 로 표시. -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- jstl fmt 쓸때 위와 같음. fmt : formatter 형식 맞춰서 표시 -->
<%@ include file="../../includes/header.jsp"%>

<link rel="stylesheet" type="text/css"
	href="/resources/css/admin/user/userCheckMng.css">

<div class="userCheckPage">

	<div id="pagehead">
		<h1>사용자 관리 페이지</h1>
		<hr>
	</div>
	<div class="user1-checkbox">


		<div class="usersun" id="user1-checkRef">
			<select name="user-check" id="user-check">
				<option value="memNo" class="memNo">회원번호</option>
				<option value="id" class="id">id</option>
			</select> <input type="text" value="" class="inputText">
		</div>

		<div class="usersun" id="user1-state">
			<p>회원상태</p>
			<select name="user-checkState" id="user-checkState">
				<option value="stateAll">전체</option>
				<option value="activity">활동상태</option>
				<option value="domancy">휴면상태</option>
			</select>
		</div>

		<div class="usersun" id="user1-name">
			<p>이름</p>
			<input type="text" value="">
		</div>
		<div class="usersun" id="user1-phone">
			<p>휴대전화번호</p>
			<input type="text" id="userphone" value="">
		</div>

		<div class="usersun" id="user1-joinDate">
			<p>가입일자</p>
			<input type="text" id="user1-joinDate" value="">
		</div>
	</div>
	<div id="user1-button">
		<button type="button" class="user-refSubmit" id="userRefSubmit">조회</button>
	</div>

	<!-- 유저 체크박스 끝. -->

	<!-- 유저 테이블 시작. -->
	<div class="user2-userState">
		<table class="user-info">
			<thead class="user-userInfoHead">
				<tr>
					<td class="user-no">NO</td>
					<td class="user-condition">회원상태</td>
					<td class="user-name">닉네임</td>
					<td class="user-phone">휴대전화번호</td>
					<td class="user-joinDate">가입일자</td>
					<td class="user-latelyConnectDate">최근접속일자</td>
					<td class="user-dormancyDate">휴면일자</td>
					<td class="user-secDate">탈퇴일자</td>
					<td class="user-registrantDate">등록자/등록일자</td>
				</tr>
			</thead>
			<tbody class="user-userInfoBody">
      <c:forEach var="userList" items="${USERLIST.LIST }">
				<tr>
					<td>${userList.MEM_NO }</td>
					<td>${userList.CUST_ST }</td>
					<td>${userList.CUST_NM }</td>
					<td>${userList.CUST_TEL }</td>
					<td>${userList.CUST_REG }</td>
					<td>${userList.MOD_REG }</td>
					<td>N</td>
					<td>${userList.USE_YN }</td>
					<td>${userList.MNG_NM } / ${userList.MNG_REG }</td>
				</tr>
      </c:forEach>
<!-- 				<tr> -->
<!-- 					<td>2</td> -->
<!-- 					<td>휴면중</td> -->
<!-- 					<td>안상욱</td> -->
<!-- 					<td>010-1234-5678</td> -->
<!-- 					<td>2022-05-01</td> -->
<!-- 					<td>2022-05-05</td> -->
<!-- 					<td>N</td> -->
<!-- 					<td>N</td> -->
<!-- 					<td>admin1 / 2022-05-02</td> -->
<!-- 				</tr> -->

<!-- 				<tr> -->
<!-- 					<td>3</td> -->
<!-- 					<td>활동중</td> -->
<!-- 					<td>이은혜</td> -->
<!-- 					<td>010-9876-5432</td> -->
<!-- 					<td>2022-05-01</td> -->
<!-- 					<td>2022-05-05</td> -->
<!-- 					<td>N</td> -->
<!-- 					<td>N</td> -->
<!-- 					<td>admin1 / 2022-05-02</td> -->
<!-- 				</tr> -->

<!-- 				<tr> -->
<!-- 					<td>4</td> -->
<!-- 					<td>활동중</td> -->
<!-- 					<td>이은지</td> -->
<!-- 					<td>010-7587-8577</td> -->
<!-- 					<td>2022-05-01</td> -->
<!-- 					<td>2022-05-05</td> -->
<!-- 					<td>N</td> -->
<!-- 					<td>N</td> -->
<!-- 					<td>admin1 / 2022-05-02</td> -->
<!-- 				</tr> -->

<!-- 				<tr> -->
<!-- 					<td>5</td> -->
<!-- 					<td>활동중</td> -->
<!-- 					<td>김소연</td> -->
<!-- 					<td>010-8765-4321</td> -->
<!-- 					<td>2022-05-01</td> -->
<!-- 					<td>2022-05-05</td> -->
<!-- 					<td>N</td> -->
<!-- 					<td>N</td> -->
<!-- 					<td>admin1 / 2022-05-02</td> -->
<!-- 				</tr> -->
			</tbody>
		</table>

	</div>







</div>

<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript"
	src="/resources/js/admin/user/userCheckMng.js"></script>


</body>
</html>