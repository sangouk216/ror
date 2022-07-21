<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- jstl core 쓸때 태그에 c 로 표시. -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- jstl fmt 쓸때 위와 같음. fmt : formatter 형식 맞춰서 표시 -->
</div>
<!-- /.container-fluid -->
<div id="footerbody">
  <div id="garim"></div>
  <div class="footre-move">
    <footer id="menu-wrapper" class="main-footer">
      <button type="button" id="ftClick" class="slideIn">재료조합
        열기</button>
      <div class="footer-choice">
      
        <ul class="footer-ul">
          <c:forEach var="footerList" items="${MATE.LIST}" varStatus="status">
            <li id="ft-liList ft-liList1" class="ft-li">
            <label class="ft-label"> 
              <strong id="mainCd">${footerList.MAIN_C_CD }</strong>
              <button class="ft-read" id="ft-read1" type="button">전체보기▽</button>
            </label>
              <div class="footer-terms footer-terms1">
              
                <c:forEach var="mateBtn" items="${FOOTERLIST.LIST }">
                  <c:if test="${footerList.MAIN_C_CD eq mateBtn.MAIN_C_CD}">
                    <button type="button" class="footer-material">${mateBtn.MAT_NM }</button>
                  </c:if>
                </c:forEach>
                
              </div>
              </li>
              </c:forEach>
        </ul>

        <div id="recipe-move">
          <div id="recipe-wrapper">
            <button type="button" class="foot-Ent" id="foot-EntBtn">선택한
              재료 열기</button>
            &nbsp;
            <button type="button" class="foot-Ent"
              id="foot-RecipeBtn">레시피 조회</button>
            <div class="choice-FtMaterial">
            </div>
          </div>
        </div>
      </div>
    </footer>
  </div>
</div>
<!-- 스크립트 -->
<script type="text/javascript"
  src="/resources/js/includes/Footer.js"></script>
</html>
