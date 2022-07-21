<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- jstl core 쓸때 태그에 c 로 표시. -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- jstl fmt 쓸때 위와 같음. fmt : formatter 형식 맞춰서 표시 -->

<link rel="stylesheet" type="text/css" href="/resources/css/main.css" />

<div id="divpop">
  <div class="pop-left-area"></div>
  <div class="pop-center-area">
    <table class="pop-tbl">
      <tbody class="pop-tbl-card">
        <tr>
          <td class="tbl-head">
            <div class="konggee">공지사항</div>
            <button role="button" class="tbl-head-info">${NTC.NTC_TITLE}</button>
            <div class="board-date">게시일:${NTC.MNG_REG}</div>
          </td>
        </tr>
        <tr>
          <td class="tbl-body">
            <div class="tbl-body-info">${NTC.NTC_CTNT}</div>
          </td>
        </tr>
        <tr>
          <td class="tbl-btt-ctl">
            <form name="notice_form">
              <div class="pop-chk-space">
                <input type="checkbox" name="chkbox" value="checkbox" onclick='closeWin()'>
                <div class="pop-chk-text">하루동안 이 창을 열지 않음</div>
              </div>
              <div>
                <button type="button" class="pop-close-btn" onclick='PopupHide()'>
                  <B>닫기</B>
                </button>
              </div>
            </form>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
  <div class="pop-right-area"></div>
</div>
<!-- POPUP 팝업 -->
<script type="text/javascript" src="/resources/js/main.js"></script>
</body>
</html>