//자유게시판 게시글 클릭시 자유게시판 게시글 페이지 이동 시작
$(document).ready(function() {
	// 검색
	$('.searchBtn').on("click", function(e) {
		const data = new FormData();
		data.append("keyword", $("#keyword").val());
		data.append("type", $(".freeboard-search-choice").val());
		console.log("send type :"+$(".freeboard-search-choice").val());
		fetch("/board/boardSearch", {
			method: "POST",
			credentials: "same-origin",
			body: data
		})
		.then(function(res) {
			if (!res.ok) throw Error(res.status);
			console.log(data);
			return res.json();
		})
		.then(function(data) {
			if (data) {
				console.log(data);
				if (data.RESULT == "SUCCESS") {
					console.log(data.BDSC.LIST[0]);
					var boardList = '';
					for (var i = 0; i < data.BDSC.LIST.length; i++) {
						boardList+= '<tr class="free-notice">';
						boardList+= '<td id="pstNo" class="free-bno">'+data.BDSC.LIST[i].PST_NO+'</td>';
						boardList+= '<td class="free-title"><a href="/board/freeBoardInfo?pstNo='+data.BDSC.LIST[i].PST_NO+'">'+data.BDSC.LIST[i].REP_NM+'</a></td>';
						boardList+= '<td class="free-writer">'+data.BDSC.LIST[i].MNG_NM+'</td>';
						boardList+= '<td class="free-regdate"><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value='+data.BDSC.LIST[i].MNG_REG+'</td>';
						boardList+= '<td class="free-viewCnt">'+data.BDSC.LIST[i].VIEW_CNT+'</td>';
						boardList+= '<td class="free-likeCnt">1</td></tr>';
						console.log(data.BDSC.LIST[i].VIEW_CNT);
						console.log(boardList);
					}
					$("tbody").html(boardList);
				} else if (data.RESULT == "FAILURE") {
					alert("오류가 발생했습니다");
				}
			}
		})
		.catch(function(error) {
	
		});
	});// 검색 끝
	
	// 글제목 클릭시 물고가는 값
	$('.free-title').on("click", function() {
		//  var recipeNo = $("#recipeNo").val();
		var form = document.createElement("form");
		form.setAttribute("charset", "UTF-8");
		form.setAttribute("method", "POST");
		form.setAttribute("action", "/board/freeBoardInfo?pstNo=" + $("#pstNo").val());
		form.setAttribute("target", "_self");

		var hiddenField = document.createElement("input");
		hiddenField.setAttribute("type", "hidden");
		hiddenField.setAttribute("name", "repNo");
		hiddenField.setAttribute("value", "");
		form.appendChild(hiddenField);

		document.body.appendChild(form);
		form.submit();
	});// 글제목 클릭시 물고가는 값 끝
	
	// 글쓰기 시작
	$('.freeboard-writeBtn').on("click", function() {
		var form = document.createElement("form");
		form.setAttribute("charset", "UTF-8");
		form.setAttribute("method", "GET");
		form.setAttribute("action", "/board/freeBoardUse");
		form.setAttribute("target", "_self");

		document.body.appendChild(form);
		form.submit();
	});// 글쓰기 끝
});
