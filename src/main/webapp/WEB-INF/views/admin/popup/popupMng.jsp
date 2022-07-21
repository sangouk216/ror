<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="../../includes/header.jsp"%>

<link rel="stylesheet" type="text/css"
  href="/resources/css/admin/popup/popupMng.css">
<body>

  <div class="popupmanager">
    <h2>공지 사항</h2>
    <div>공지 제목: <input value="${NTC.NTC_TITLE}" name="ntc_title" disabled></div>
    <div>공지 작성자: <input value="${NTC.MNG_NM}" name="mng_nm" disabled></div>
    <div>공지 게시일: <input value="${NTC.MNG_REG}" name="mng_reg" disabled></div>
    <div class="popup">
   
  <textarea class="popup-content" disabled>${NTC.NTC_CTNT}
  </textarea>
  <c:forEach var="ntcList" items="${NTCList.LIST}" varStatus="status">
  <div class="dasdad">
      ${ntcList.NTC_CTNT}
   <!-- <a <c:if test="${not empty optImgList.IMG_URL || not empty optImgList.POP_IMG_URL }">href="
   ${optImgList.IMG_URL}${optImgList.POP_IMG_URL}
   "</c:if> 
   <c:if test="${optImgList.DB_YN eq '1' }">onclick="grntReq('grntReq',${optImgList.SEQ_NO})"</c:if>
   > -->
   
   <!-- </a> -->
  </div>
</c:forEach>

    </div>
    <div class="popup-button">
      <button type="button" class="popup-change">수정</button>
      <button type="button" id="popup-off" class="popup-off">공지사항
        숨기기</button>
    </div>
  </div>
  <script type="text/javascript"
    src="/resources/js/admin/popup/popupMng.js"></script>
</body>
