/* 헤더 실시간 메뉴 */
$(document).ready(function() {
    var count = $('#rank-list li').length;
    var height = $('#rank-list li').height();

    function step(index) {
        $('#rank-list ol').delay(1000).animate({
            top: -height * index,
        }, 500, function() {
            step((index + 1) % count);
        });
    }
    step(1);
    
    //헤더 로고 클릭시 메인페이지 이동 시작
    $('.home-icon').on("click", function() {
	    //  var recipeNo = $("#recipeNo").val();
	      
	      var form = document.createElement("form");
	       form.setAttribute("charset", "UTF-8");
	       form.setAttribute("method", "GET");
	       form.setAttribute("action", "/main");
	       form.setAttribute("target", "_self");
	      
	       var hiddenField = document.createElement("input");
	       hiddenField.setAttribute("type", "hidden");
	       hiddenField.setAttribute("name", "");
	       hiddenField.setAttribute("value", "");
	       form.appendChild(hiddenField);
	
	       document.body.appendChild(form);
	       form.submit();

 	 });
 	 //메인페이지 이동 끝
 	 
 	//마이페이지 버튼 클릭시 마이페이지 이동 시작
    $('#moveMyPage').on("click", function() {
	    //  var recipeNo = $("#recipeNo").val();
	      
	      var form = document.createElement("form");
	       form.setAttribute("charset", "UTF-8");
	       form.setAttribute("method", "POST");
	       form.setAttribute("action", "/ror/myPage");
	       form.setAttribute("target", "_self");
	      
	       var hiddenField = document.createElement("input");
	       hiddenField.setAttribute("type", "hidden");
	       hiddenField.setAttribute("name", "");
	       hiddenField.setAttribute("value", "");
	       form.appendChild(hiddenField);
	
	       document.body.appendChild(form);
	       form.submit();

 	 });
 	 //마이페이지 이동 끝
 	 
 	 //재료정리 버튼 클릭시 재료정리 이동 시작
    $('#nav-materOrgBtn').on("click", function() {
	      
	      var form = document.createElement("form");
	       form.setAttribute("charset", "UTF-8");
	       form.setAttribute("method", "GET");
	       form.setAttribute("action", "/ror/material/mateChoice");
	       form.setAttribute("target", "_self");
	      
	       var hiddenField = document.createElement("input");
	       hiddenField.setAttribute("type", "hidden");
	       hiddenField.setAttribute("name", "");
	       hiddenField.setAttribute("value", "");
	       form.appendChild(hiddenField);
	
	       document.body.appendChild(form);
	       form.submit();

 	 });
 	 //재료정리 이동 끝
 	 
 	 //관리자 메뉴 재료 관리 클릭시 재료 관리 관리자 페이지 이동 시작
    $('#materialMGBtn').on("click", function() {
	    //  var recipeNo = $("#recipeNo").val();
	      
	      var form = document.createElement("form");
	       form.setAttribute("charset", "UTF-8");
	       form.setAttribute("method", "GET");
	       form.setAttribute("action", "/admin/mate/mateMng");
	       form.setAttribute("target", "_self");
	      
	       var hiddenField = document.createElement("input");
	       hiddenField.setAttribute("type", "hidden");
	       hiddenField.setAttribute("name", "");
	       hiddenField.setAttribute("value", "");
	       form.appendChild(hiddenField);
	
	       document.body.appendChild(form);
	       form.submit();
 	 });
 	 //재료 관리 관리자 페이지 이동 끝
 	 
 	//관리자 메뉴 사용자관리 클릭시 사용자 관리 관리자 페이지 이동 시작
    $('#userMGBtn').on("click", function() {
	//  var recipeNo = $("#recipeNo").val();
	      
	      var form = document.createElement("form");
	       form.setAttribute("charset", "UTF-8");
	       form.setAttribute("method", "GET");
	       form.setAttribute("action", "/admin/user/userCheckMng");
	       form.setAttribute("target", "_self");
	      
	       var hiddenField = document.createElement("input");
	       hiddenField.setAttribute("type", "hidden");
	       hiddenField.setAttribute("name", "");
	       hiddenField.setAttribute("value", "");
	       form.appendChild(hiddenField);
	
	       document.body.appendChild(form);
	       form.submit();
 	 });
 	 //사용자 관리 페이지 이동 끝
 	 
 	 //레시피 게시글 클릭시 레시피 게시판 이동 시작
    $('.nav-recipeboard').on("click", function() {
	    //  var recipeNo = $("#recipeNo").val();
	      
        var form = document.createElement("form");
         form.setAttribute("charset", "UTF-8");
         form.setAttribute("method", "GET");
         form.setAttribute("action", "/recipe/recipeSearch");
         form.setAttribute("target", "_self");
        
         var hiddenField = document.createElement("input");
         hiddenField.setAttribute("type", "hidden");
         hiddenField.setAttribute("name", "");
         hiddenField.setAttribute("value", "");
         form.appendChild(hiddenField);
  
         document.body.appendChild(form);
         form.submit();

 	 });
 	 //레시피 게시글 이동 끝
 	 
 	//자유 게시판 클릭시 자유 게시판 이동 시작
    $('.nav-freeboard').on("click", function() {
	    //  var recipeNo = $("#recipeNo").val();
	      
	      var form = document.createElement("form");
	       form.setAttribute("charset", "UTF-8");
	       form.setAttribute("method", "GET");
	       form.setAttribute("action", "/board/freeBoard");
	       form.setAttribute("target", "_self");
	      
	       var hiddenField = document.createElement("input");
	       hiddenField.setAttribute("type", "hidden");
	       hiddenField.setAttribute("name", "");
	       hiddenField.setAttribute("value", "");
	       form.appendChild(hiddenField);
	
	       document.body.appendChild(form);
	       form.submit();

 	 });
 	 //자유 게시판 이동 끝
 	 
 	  	 //로그인 버튼 클릭시 로그인 페이지 이동 시작
    $('.nav-loginBtn').on("click", function() {
	    //  var recipeNo = $("#recipeNo").val();
	      
	      var form = document.createElement("form");
	       form.setAttribute("charset", "UTF-8");
	       form.setAttribute("method", "GET");
	       form.setAttribute("action", "/ror/login");
	       form.setAttribute("target", "_self");
	      
	       var hiddenField = document.createElement("input");
	       hiddenField.setAttribute("type", "hidden");
	       hiddenField.setAttribute("name", "");
	       hiddenField.setAttribute("value", "");
	       form.appendChild(hiddenField);
	
	       document.body.appendChild(form);
	       form.submit();

 	 });
 	 //로그인 페이지 이동 끝
 	 
 	 //팝업 공지사항 관리자 버튼 클릭
 	 $('#popupMGBtn').on("click", function() {
	    //  var recipeNo = $("#recipeNo").val();
	      
	      var form = document.createElement("form");
	       form.setAttribute("charset", "UTF-8");
	       form.setAttribute("method", "GET");
	       form.setAttribute("action", "/admin/popup/popupMng");
	       form.setAttribute("target", "_self");
	      
	       var hiddenField = document.createElement("input");
	       hiddenField.setAttribute("type", "hidden");
	       hiddenField.setAttribute("name", "");
	       hiddenField.setAttribute("value", "");
	       form.appendChild(hiddenField);
	
	       document.body.appendChild(form);
	       form.submit();

 	 });
 	 //팝업 관리자 페이지 이동 끝
 	 
});