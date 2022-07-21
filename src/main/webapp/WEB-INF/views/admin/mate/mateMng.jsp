<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- jstl core 쓸때 태그에 c 로 표시. -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="../../includes/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<link rel="stylesheet" type="text/css"
	href="/resources/css/admin/mate/mateMng.css">
<body>
	<div class="materialManager">
		<P class="pon">재료관리-관리자</P>
		<div class="manager-choice">
			<div class="manager-ul">
				<c:forEach var="footerList" items="${MATE.LIST}" varStatus="status">
					<div id="mn-liList1" class="mn-li">
						<label class="mn-label"> <strong>${footerList.MAIN_C_CD }</strong>
							<button class="mn-read" id="mn-read1" type="button">전체보기▽</button>
						</label>
						<div class="manager-terms manager-terms1">
							<c:forEach var="mateBtn" items="${FOOTERLIST.LIST }">
								<c:if test="${footerList.MAIN_C_CD eq mateBtn.MAIN_C_CD}">
									<button type="button" class="manager-material" id="mateMngSo"
										value="">${mateBtn.MAT_NM }</button>
								</c:if>
							</c:forEach>
						</div>
					</div>
				</c:forEach>
				<!-- 				<div name='fish' id="mn-liList2" class="mn-li"> -->
				<!-- 					<label class="mn-label"> <strong>어류(생선,조개 등) </strong> -->
				<!-- 						<button class="mn-read" id="mn-read2" type="button"> -->

				<!-- 							전체보기▽</button> -->
				<!-- 					</label> -->
				<!-- 					<div class="manager-terms manager-terms2"> -->
				<!-- 						<button type="button" class="manager-material">고등어</button> -->
				<!-- 						<button type="button" class="manager-material">광어</button> -->
				<!-- 						<button type="button" class="manager-material">꽁치</button> -->
				<!-- 						<button type="button" class="manager-material">참치</button> -->
				<!-- 						<button type="button" class="manager-material">연어</button> -->
				<!-- 						<button type="button" class="manager-material">돌돔</button> -->
				<!-- 					</div> -->
				<!-- 				</div> -->
				<!-- 				<div name='veg' id="mn-liList3" class="mn-li"> -->
				<!-- 					<label class="mn-label"> <strong>채소</strong> -->
				<!-- 						<button class="mn-read" id="mn-read3" type="button">전체보기▽</button> -->
				<!-- 					</label> -->
				<!-- 					<div class="manager-terms manager-terms3"> -->
				<!-- 						<button type="button" class="manager-material">봄동</button> -->
				<!-- 						<button type="button" class="manager-material">취나물</button> -->
				<!-- 						<button type="button" class="manager-material">양배추</button> -->
				<!-- 						<button type="button" class="manager-material">배추</button> -->
				<!-- 						<button type="button" class="manager-material">대파</button> -->
				<!-- 						<button type="button" class="manager-material">쪽파</button> -->
				<!-- 					</div> -->
				<!-- 				</div> -->
				<!-- 				<div name='veg2' id="mn-liList4" class="mn-li"> -->
				<!-- 					<label class="mn-label"> <strong>야채</strong> -->
				<!-- 						<button class="mn-read" id="mn-read4" type="button">전체보기▽</button> -->
				<!-- 					</label> -->
				<!-- 					<div class="manager-terms manager-terms4"> -->
				<!-- 						<button type="button" class="manager-material">봄동</button> -->
				<!-- 						<button type="button" class="manager-material">취나물</button> -->
				<!-- 						<button type="button" class="manager-material">양배추</button> -->
				<!-- 						<button type="button" class="manager-material">배추</button> -->
				<!-- 						<button type="button" class="manager-material">대파</button> -->
				<!-- 						<button type="button" class="manager-material">쪽파</button> -->
				<!-- 					</div> -->
				<!-- 				</div> -->
				<!-- 			</div> -->
				<hr>
				<div id="managerdown">
					<div id="change_modal">
						<!-- <p class="choosemeterial">선택한 재료</p> -->
						<div>
							<input type="text" id="changematerial" class="changemn"
								placeholder="수정할 값">
						</div>
						<button type="button" id="change">수정</button>
						<img class="modal_close_btn"
							src="/resources/images/btn_quick_close.png">
					</div>
					<div id="plus_modal">

						<button class="plusmeterial" id="plus_material" type="button">
							추가할 분류창▽</button>
						<div class="plus_group">
						<c:forEach var="footerList" items="${MATE.LIST}"
							varStatus="status">
							<div id="mn-liList1" class="mn-li">
						<button type="button" class="plus_group_label" name="plus_group_label1">
								<label class="mn-label"> <strong>${footerList.MAIN_C_CD }</strong>
								</label>
						</button>
							</div>
						</c:forEach>
<!-- 							<button type="button" class="plus_group_label" -->
<!-- 								name="plus_group_label1">육류</button> -->
<!-- 							<button type="button" class="plus_group_label" -->
<!-- 								name="plus_group_label1">채소</button> -->
<!-- 							<button type="button" class="plus_group_label" -->
<!-- 								name="plus_group_label1">과일</button> -->
<!-- 							<button type="button" class="plus_group_label" -->
<!-- 								name="plus_group_label1">생선</button> -->
<!-- 							<button type="button" class="plus_group_label" -->
<!-- 								name="plus_group_label1">유제품</button> -->
<!-- 							<button type="button" class="plus_group_label" -->
<!-- 								name="plus_group_label1">육류</button> -->
<!-- 							<button type="button" class="plus_group_label" -->
<!-- 								name="plus_group_label1">육류</button> -->
<!-- 							<button type="button" class="plus_group_label" -->
<!-- 								name="plus_group_label1">육류</button> -->
<!-- 							<button type="button" class="plus_group_label" -->
<!-- 								name="plus_group_label1">육류</button> -->
<!-- 							<button type="button" class="plus_group_label" -->
<!-- 								name="plus_group_label1">육류</button> -->


						</div>

						<input type="text" class="plusplusmn"
							placeholder="추가할 재료를 입력해 주세요.">
						<button type="button" id="plus">추가</button>
						<img class="modal_close_btn"
							src="/resources/images/btn_quick_close.png">
					</div>
					<div id="delete_modal">
						<p class="delete_ask">삭제하시겠습니까?</p>
						<div class="yesno">
							<button type="button" class="yes">예</button>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="no">아니요</button>
						</div>
						<img class="modal_close_btn"
							src="/resources/images/btn_quick_close.png">
					</div>
					<div id="changeover_modal">
						<div class="modal-body">
							<div class="modal-message">
								<div class="modalchange-text">
									<div class="change-text">
										개수가 초과하였습니다. <br>1개씩 가능합니다.
									</div>
								</div>
								<img class="modal_close_btn"
									src="/resources/images/btn_quick_close.png">
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- 수정,추가,삭제 버튼 -->
			<div class="ch">
				<div class="mychoose">
					<div id="choosetext">선택한 재료</div>
					<div id="choosemnid" class="choosemn"></div>
				</div>
				<div class="ch1">
					<button type="button" class="but_plus">추가</button>
				</div>
				<div class="ch2">
					<button type="button" class="but_change" disabled='disabled'>수정(택1)</button>

					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="but_delete" disabled='disabled'>삭제(택1)</button>
				</div>
			</div>
		</div>

		<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
		<script type="text/javascript"
			src="/resources/js/admin/mate/mateMng.js"></script>
</body>
</html>