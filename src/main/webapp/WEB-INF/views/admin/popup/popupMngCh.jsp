<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="../../includes/header.jsp"%>

<link rel="stylesheet" type="text/css"
  href="/resources/css/admin/popup/popupMngCh.css">
<body>
<!-- <form method="post" action="/popupMngCh"> -->
  <div class="popupmanagerchange">
    <h2>공지 사항</h2>
    <div>공지 제목: <input value="${NTC.NTC_TITLE}" class="popup-title" id="ntctitle" name="ntc_title"></div>
    <c:forEach var="userInfo" items="${USERINFO.LIST }">
    <div>공지 작성자: <input value="${userInfo.CUST_NM }" id="mngnm" readonly="readonly" name="mng_nm"></div>
    </c:forEach>
    <div>공지 게시일: <input value="${NTC.MNG_REG}" id="mngreg" readonly="readonly" name="mng_reg"></div>
    <div class="popup">
    
      <textarea class="popup-content" id="ntcctnt" name="ntc_ctnt">${NTC.NTC_CTNT}</textarea>
    </div>
    <div class="popupokbutton">
      <button type="submit" class="popupok">등록</button>
<!--       <button type="submit" class="popupok" formmethod="post" formaction="">등록</button> -->
    </div>
  </div>
  <script type="text/javascript"
    src="/resources/js/admin/popup/popupMngCh.js"></script>
<!--     </form> -->
</body>
