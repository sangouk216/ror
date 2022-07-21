//회원가입 버튼 클릭시 회원가입 페이지 이동 시작
$('.joinBtn').on("click", function() {
	//  var recipeNo = $("#recipeNo").val();

	var form = document.createElement("form");
	form.setAttribute("charset", "UTF-8");
	form.setAttribute("method", "GET");
	form.setAttribute("action", "/join/joinInfo");
	form.setAttribute("target", "_self");

	var hiddenField = document.createElement("input");
	hiddenField.setAttribute("type", "hidden");
	hiddenField.setAttribute("name", "");
	hiddenField.setAttribute("value", "");
	form.appendChild(hiddenField);

	document.body.appendChild(form);
	form.submit();

});
//회원가입 페이지 이동 끝

//아이디 비밀번호 찾기 클릭시 회원가입 페이지 이동 시작
$('.infofind').on("click", function() {
	//  var recipeNo = $("#recipeNo").val();

	var form = document.createElement("form");
	form.setAttribute("charset", "UTF-8");
	form.setAttribute("method", "GET");
	form.setAttribute("action", "/join/pwFind");
	form.setAttribute("target", "_self");

	var hiddenField = document.createElement("input");
	hiddenField.setAttribute("type", "hidden");
	hiddenField.setAttribute("name", "");
	hiddenField.setAttribute("value", "");
	form.appendChild(hiddenField);

	document.body.appendChild(form);
	form.submit();

});
 	 //아이디 비밀번호 찾기 페이지 이동 끝