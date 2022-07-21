function menuBar(){
  
  $("#logoutBtn").on('click',function(){
    alert("로그아웃 되었습니다.");
  });

 	if($("#nav-body").hasClass("sidenav-toggled")){
   		$(".nav-allSlide").removeClass("is-show");
   		$("#nav-body").removeClass("sidenav-toggled");
	}else{
    	$(".nav-allSlide").addClass("is-show");
    	$("#nav-body").addClass("sidenav-toggled");
	}
}
$(".container-fluid").click(function(e) {
	if(!$(e.target).hasClass("nav-menuInfo")){
		$(".nav-allSlide").removeClass("is-show");
   		$("#nav-body").removeClass("sidenav-toggled");
	}
});

	function setCookie( name, value, expiredays ) { 
		var todayDate = new Date(); 
		todayDate.setDate( todayDate.getDate() + expiredays ); 
		document.cookie = escape(name) + "=" + escape( value ) + "; path=/; expires=" + todayDate.toGMTString(); 
	}
 
$(document).ready(function () {
  $('.popup').show();
  cookiedata = document.cookie;
  if ( cookiedata.indexOf("maindiv=done") < 0 ){ //쿠키 변경 여부 불러오기
    document.all['divpop'].style.visibility = "visible";
  }
  else {
    document.all['divpop'].style.display = "none";
  }
  PopupBgDisplay();
});

function closeWin() {
  setCookie("maindiv", "done", 1); //쿠키값 변경
  PopupHide();
}

function PopupHide() { //팝업창 지우기
  $('#divpop').hide();
  PopupBgDisplay(); 
}

function PopupBgDisplay() { //팝업창이 없어진 경우 배경(popupbg) 지우기
  cookiedata1 = document.cookie;
  if (cookiedata1.indexOf("divpop=done") > 0)
  {
    $("#divpop").hide();
  }
}
