$(document).ready(function() {
	$(".free-list").on("click", function(){
		location.href = '/board/freeBoard';
	});
//	$(".free-modify").on("click", function(){
//		freeModify();
//	});
	$(".free-delete").on("click", function(){
		freeDelete();
	});
		  	 //자유게시판 글쓰기버튼 클릭시 자유게시판 글쓰기 페이지 이동 시작
    $('.free-modify').on("click", function() {
	    //  var recipeNo = $("#recipeNo").val();
	      const data = new FormData();
	data.append("pstNo", $("#pstNo").val());
	data.append("pstNm", $("#pstNm").val());
	data.append("pstCtnt", $("#pstCtnt").val());
	data.append("writer", $("#writer").val());
	      var form = document.createElement("form");
	       form.setAttribute("charset", "UTF-8");
	       form.setAttribute("method", "POST");
	       form.setAttribute("action", "/board/freeBoardModi?pstNo="+$("#pstNo").val());
	       form.setAttribute("target", "_self");
	      
	       var hiddenField = document.createElement("input");
	       hiddenField.setAttribute("type", "hidden");
	       hiddenField.setAttribute("name", "");
	       hiddenField.setAttribute("value", "");
	       form.appendChild(hiddenField);
	
	       document.body.appendChild(form);
	       form.submit();

 	 });
});

function freeModify() {
	const data = new FormData();
	data.append("pstNo", $("#pstNo").val());
	data.append("pstNm", $("#pstNm").val());
	data.append("pstCtnt", $("#pstCtnt").val());
	data.append("writer", $("#writer").val());
	fetch("/board/modify", {
		method: "POST",
		credentials: "same-origin",
		body: data
	}).then(function(res){
		if (!res.ok) throw Error(res.status);
			console.log(data);
			return res.json();
	}).then(function(data) {
		if (data) {
			if (data.RESULT == "SUCCESS") {
				var form = document.createElement("form");
				form.setAttribute("charset", "UTF-8");
				form.setAttribute("method", "POST");
				form.setAttribute("action", "/board/freeBoardInfo?pstNo="+$("#pstNo").val());
				form.setAttribute("target", "_self");
				
//				var hiddenField = document.createElement("input");
//				hiddenField.setAttribute("type", "");
//				hiddenField.setAttribute("name", "");
//				hiddenField.setAttribute("value", "");
//				form.appendChild(hiddenField);
				document.body.appendChild(form);
				form.submit();
			} else if (data.RESULT == "FAILURE") {
				alert("오류가 발생했습니다");
			}
		}
	}).catch(function(error) {
	
	});
}
function freeDelete() {
	const data = new FormData();
	data.append("pstNo", $("#pstNo").val());
	console.log("찍히나"+data);
	fetch("/board/delete", {
		method: "POST",
		credentials: "same-origin",
		body: data
	}).then(function(res){
		if (!res.ok) throw Error(res.status);
			console.log(data);
			return res.json();
	}).then(function(data) {
		if (data) {
			if (data.RESULT == "SUCCESS") {
				var form = document.createElement("form");
				form.setAttribute("charset", "UTF-8");
				form.setAttribute("method", "POST");
				form.setAttribute("action", "/board/freeBoard");
				form.setAttribute("target", "_self");
				
				var hiddenField = document.createElement("input");
				hiddenField.setAttribute("type", "");
				hiddenField.setAttribute("name", "");
				hiddenField.setAttribute("value", "");
				form.appendChild(hiddenField);
				document.body.appendChild(form);
				console.log("찍히나"+data);
				form.submit();
			} else if (data.RESULT == "FAILURE") {
				alert("오류가 발생했습니다");
			}
		}
	}).catch(function(error) {
	
	});

 	 //자유게시판 글쓰기 페이지 이동 끝
}