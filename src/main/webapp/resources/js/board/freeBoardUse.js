

$(document).ready(function() {
	//돌아가기 버튼 클릭시 자유게시판 목록 이동 시작
	$('.freeUse-saveBtn').on("click", function() {
		console.log("글쓰기 성공");
		const data = new FormData();
		console.log(memNo + pstNm + pstCtnt + writer);
		data.append("memNo", $("#memNo").val());
		data.append("pstNm", $("#pstNm").val());
		data.append("pstCtnt", $("#pstCtnt").val());
		data.append("writer", $("#writer").val());
		fetch("/board/insert", {
			method: "POST",
			credentials: "same-origin",
			body: data
		}).then(function(res) {
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
					form.submit();

				} else if (data.RESULT == "FAILURE") {
					alert("오류가 발생했습니다");
				}
			}
		}).catch(function(error) {

		});
	});
	$('.freeUse-backBtn').on("click", function() {
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
	//자유게시판 목록 이동 끝
});
