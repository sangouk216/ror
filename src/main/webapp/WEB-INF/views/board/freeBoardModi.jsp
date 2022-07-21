<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- jstl core 쓸때 태그에 c 로 표시. -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- jstl fmt 쓸때 위와 같음. fmt : formatter 형식 맞춰서 표시 -->
<%@ include file="../includes/header.jsp"%>
<link rel="stylesheet" type="text/css"
  href="/resources/css/board/freeBoardUse.css">
<div class="freeUse-header">
  <h1 id="freeUse-title">자유 게시판</h1>
  <div class="freeUse-body">
    <h2 id="freeUse-subtitle">글 수정페이지</h2>
    <c:forEach var="freeInfo" items="${FRIN.LIST }">
      <input type="hidden" id="pstNo" value="${freeInfo.PST_NO }" />
      <input type="hidden" id="modifyer"
        value="${freeInfo.MNG_NM }" />
      <div class="freeUseRegister-title">
        <p>제목</p>
        <input type="text" id="pstNm" placeholder="제목을 작성하세요"
          value="${freeInfo.REP_NM }">
      </div>
      <div class="freeUse-content">
        <p>내용</p>
        <textarea id="pstCtnt" placeholder="내용을 작성하세요">${freeInfo.PST_CTNT }</textarea>
      </div>
      <div class="freeUse-addfile">
        <p>첨부파일</p>
        <button type="button" class="freeUse-addfileBtn">선택</button>
      </div>
      <div class="freeUseMemNo">
        <input type="hidden" id="memNo" value="6">
      </div>
      <div class="freeUseWriter">
        <input type="hidden" id="writer" value="writer">
      </div>
    </c:forEach>
    <button type="button" class="freeUse-saveBtn"
      onclick="freeModify()">글 수정하기</button>
    <button type="button" class="freeUse-backBtn"
      onclick="location.href='./freeBoard'">돌아가기</button>
  </div>
</div>
<script type="text/javascript"
  src="/resources/js/board/freeBoardInfo.js"></script>
</body>
</html>