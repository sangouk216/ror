/**
 * 
 */
//돌아가기 버튼 클릭시 해당글 readonly 로 이동(조회)
$(document).ready(function() {
	// 취소하면 다시 글읽기상태
	$("#backBtn").on("click", function(){
		location.href = '/recipe/recipeSearch'
	});
	$("comment-button-delete").on("click", function(){
		e.preventDefault();
        recipeDelete();
	});
});

// 수정하기 submit
function recipeUpdate() {
	const data = new FormData();
	data.append("repName", $("#repName").val());
	data.append("repCtnt", $("#repCtnt").val());
	data.append("repMainCd", $("#repMainCd").val());
	data.append("repMidCd", $("#repMidCd").val());
//	data.append("memNo", $("#memNo").val());
	data.append("writer", $("#writer").val());
	data.append("repNo", $("#repNo").val());
	fetch("/recipe/modifyRecipe2", {
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
				form.setAttribute("action", "/recipe/recipeinfo?repNo="+$("#repNo").val());
				form.setAttribute("target", "_self");
				
				document.body.appendChild(form);
				form.submit();
			} else if (data.RESULT == "FAILURE") {
				alert("오류가 발생했습니다");
			}
		}
	}).catch(function(error) {
	
	});
}
function recipeDelete() {
	const data = new FormData();
	data.append("repNo", $("#repNo").val());
	console.log("찍히나"+data);
	fetch("/recipe/deleteRecipe", {
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
				form.setAttribute("action", "/recipe/recipeSearch");
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
}

// 아래 댓글
function printName()  {
	const name = document.getElementById('name').value;
	document.getElementById("result").innerText = name;
}

//즐겨찾기 좋아요
$(document).ready(function(){
	$("#bookmark-show").show();
	$("#bookmark-hide").hide();
	$("#bookmark-show").click(function(){
		$("#bookmark-show").hide();
		$("#bookmark-hide").show();
	});
	$("#bookmark-hide").click(function(){
		$("#bookmark-show").show();
		$("#bookmark-hide").hide();
	});
});
$(document).ready(function(){
	$("#bookmark-show1").show();
	$("#bookmark-hide1").hide();
	$("#bookmark-show1").click(function(){
		$("#bookmark-show1").hide();
		$("#bookmark-hide1").show();
	});
	$("#bookmark-hide1").click(function(){
		$("#bookmark-show1").show();
		$("#bookmark-hide1").hide();
	});
});