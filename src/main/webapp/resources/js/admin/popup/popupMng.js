/**
 * 
 */

const subs = document.getElementById("popup-off")

subs.addEventListener("click", function() {
   if (subs.innerText === '공지사항 숨기기') {
      subs.innerText = '공지사항 보이기';
   } else subs.innerText = '공지사항 숨기기';
});




$('.manager-material').click(function() {
   $(this).toggleClass("button-active");
   $(this).toggleClass("button-fontcolor");
});


//팝업 공지사항 관리자 버튼 클릭
$('.popup-change').on("click", function() {

   var form = document.createElement("form");
   form.setAttribute("charset", "UTF-8");
   form.setAttribute("method", "POST");
   form.setAttribute("action", "/admin/popup/popupMngCh");
   form.setAttribute("target", "_self");

   var hiddenField = document.createElement("input");
   hiddenField.setAttribute("type", "hidden");
   hiddenField.setAttribute("name", "");
   hiddenField.setAttribute("value", "1");
   form.appendChild(hiddenField);

   document.body.appendChild(form);
   form.submit();

});
     //팝업 관리자 페이지 이동 끝