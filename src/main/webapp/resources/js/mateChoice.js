$(document).ready(function() {
	$(".myMat-middleClass ul li button").hide();
	$(".myMat-subCategory ul li button").hide();
	$(".myMat-searchPopup").hide();

	var category = $(".myMat-mainCategory ul li button");
	var middleCategory = $(".myMat-middleClass ul li button");

	$(category).click(function() {
		
		  const data = new FormData();
		  data.append("mateCCd", $(this).text());
		 // data.append("ntcctnt", $("#ntcctnt").val());
			fetch("/ror/material/getMateMidList", {
		    method: "POST",
		    credentials: "same-origin",
		    body: data
		  }).then(function(res) {
		    if (!res.ok) throw Error(res.status);
		    return res.json();
		  }).then(function(data) {
	
      //      if (data.RESULT == "INVALID") {
      //     		 alert("인벨리드");
      //      } else
		      if (data.RESULT == "SUCCESS") {
				//	<li><button type="button">${MATE.MID_C_CD}</button></li>
					var midList = '';
						console.log(data.LIST);
						for(var i=0; i < data.LIST.length; i++){
							midList += '<li><button type="button" onclick="midCategory(this)">'+data.LIST[i].MID_C_CD+'</button></li>';
							//midList += '<li><button type="button">'+data.LIST[i].MID_C_CD+'</button></li>';
						}
					$(".myMat-middleClass").children("ul").html(midList);
					console.log(midList);
					console.log($(".myMat-middleClass").children("ul"));
					$(middleCategory).toggle();
				 middleCategory = $(".myMat-middleClass ul li button");
		      } else if (data.RESULT == "FAILURE") {
		    
		        alert("저장 오류");
		      }
	
		});
	}); // 대분류 카테고리 -> 중분류 카테고리 열기.


//var k;
//var temp;
//$(".search").keyup(function(){
//	k = $(this).val();
//	$(".myMat-searchPopup").show();
//	temp = $(".search:contains('" + k + "')");
//})
//$(".searchBtn").click(function(){
//	$(".myMat-searchPopup").show(function(){
//		("#popupSearchText").temp.show();
//	});                                                                                                                                                                                               
//});





//	$(".myMat-search button").on("click", function(){
//		$(".myMat-searchPopup").show();
//	});
	// 팝업창 열기.
	
	$(".finalResult").click(function(){
		$(".myMat-putInMatList").append(
			"<li><button type='button' onclick='clickMat(this)' value='" 
				+ $(this).text() + "'>"
				+ $(this).text()
				+ "<p class='remove'>x</p></button></li>");
		$(".finalResult").prop("disabled",true);
		console.log($(".myMat-subCategory ul li button").attr("disabled"));
		
		$("p.remove").click(function(){
			$(this).parents("li").remove();
				$(".finalResult").prop("disabled",false);
				console.log($(".finalResult").attr("disabled"));
		});
	});
	// 팝업창에서 메뉴 선택시, 담긴 재료 목록에 재료 들어가고 중복 클릭 방지.
	
	$(".myMat-searchPopup .myMat-closeBtn").click(function(){
		$(".myMat-searchPopup").hide();
	});
	// 팝업창 닫기.
	
	$(".allRemove").click(function(){
		$("#myMat-putList li").remove();
	});
	// 전체 선택해제 누르면 담긴 재료 모두 다 삭제.

	$(".myMat-matCloseBtn button").click(function(){
		alert("저장이 완료되었습니다.");
	});
	// 저장 버튼 누르면 저장.
	
}); // document ready end.


function midCategory(m){
		const data = new FormData();
		  data.append("midCCd", $(m).text());
		 // data.append("ntcctnt", $("#ntcctnt").val());
			fetch("/ror/material/getMateNmList", {
		    method: "POST",
		    credentials: "same-origin",
		    body: data
		  }).then(function(res) {
		    if (!res.ok) throw Error(res.status);
		    return res.json();
		  }).then(function(data) {
	
      //      if (data.RESULT == "INVALID") {
      //     		 alert("인벨리드");
      //      } else
		      if (data.RESULT == "SUCCESS") {
				//	<li><button type="button">${MATE.MID_C_CD}</button></li>
					var NmList = '';
						console.log(data.LIST);
						for(var i=0; i < data.LIST.length; i++){
							NmList += '<li><button type="button" onclick="subCategory(this)">'+data.LIST[i].MAT_NM+'</button></li>';
						}
					$(".myMat-subCategory").children("ul").html(NmList);
					console.log(NmList);
					console.log($(".myMat-subCategory").children("ul"));
					$(".myMat-subCategory ul li button").addClass();
				
		      } else if (data.RESULT == "FAILURE") {
		    
		        alert("저장 오류");
		      }
	
		});
} // 중분류에서 소분류 카테고리로 이동.

function subCategory(s){
		$(".myMat-putInMatList").append(
			"<li><button type='button' onclick='clickMat(this)' value='" 
				+ $(s).text() + "'>"
				+ $(s).text()
				+ "<p class='remove' onclick='removex(this)'>x</p></button></li>");
		$(s).addClass("chooseColor");
		// 소분류 눌렀을 때 담긴 재료 목록으로 append.
		// append 되면서 눌린 소분류 "chooseColor" 클래스 추가(색 변화).
		
		
		if($(".myMat-subCategory ul li button").hasClass("chooseColor")) {
			$(".myMat-subCategory ul li button.chooseColor").prop("disabled",true);
			console.log($(".myMat-subCategory ul li button").attr("disabled"));
		}
		// 버튼에 "chooseColor" 있으면(색깔 있으면) 소분류 버튼 클릭 중지.
		
//		$("p.remove").click(function(){
//			$(this).parents("li").remove();
//			$(".myMat-subCategory ul li button").prop("disabled",false);
//			console.log($(".myMat-subCategory ul li button").attr("disabled"));
			// 여기까진 돌아감.
			
//			$(".chooseColor").each(function(i, item) {
//				if ($(item).attr("type") == "button") {
//					if ($(item).text() === $(s).text()) {
//						if()
//						$(item).removeClass("chooseColor");
//						console.log("디스",this);
//						console.log("아이템",item);
//					}
//				}
//			});
			// 여기 안 돌아감.

//		}); // x 버튼 눌렀을 때 부모인 li 지워지기. "chooseColor" 없으면 클릭 활성화.
 
 // subCategory Click end.
} // 대분류 눌렀을 때 중분류 나왔다 들어감, 중분류 눌렀을 때 소분류 나왔다 들어감.
	// *** 대분류별 중,소분류 다르게 나오게 수정 해아 함.

function removex(x) {
	$(x).parents("li").remove();
	$(".myMat-subCategory ul li button").prop("disabled",false);
	console.log($(".myMat-subCategory ul li button").attr("disabled"));
//	$(".chooseColor").each(function(i, item) {
//		if ($(item).attr("type") == "button") {
//			if ($(item).text() === $(x).text()) {
//				$(item).removeClass("chooseColor");
//				console.log("디스",this);
//				console.log("아이템",item);
//			}
//		}
//	});
	var nmList = $(".myMat-putInMat ul li button");
	$(".chooseColor").each(function(i,item){
		if($(item).attr("type")=="button") {
			if($(item).text() === nmList.text()) {
				$(item).removeClass("chooseColor");
				console.log(item);
				console.log(nmList);
			}
		}
	});
}



function clickMat(a){

	console.log($(a));
	if($(a).hasClass("choose")){
		$(a).removeClass("choose");
		$(a).parents("li").removeClass("choose");
		$(a).children('p').removeClass("choose");
	} else{
		$(a).addClass("choose");
		$(a).parents("li").addClass("choose");
		$(a).children('p').addClass("choose");
	}
};
// 담긴 재료 목록 클릭했을 때 "choose"가 있는 상태면(색이 있는 상태면) "choose" 클래스 제거,
// "choose" 없는 상태면 "choose" 클래스 추가.


function goColdRoom(c){
	var b = $("ul li button.choose");
	var g = $("ul li.choose");
	var sp = $("#myMat-putList li button.choose p");
	
			$(".myMat-coldRoomList").append(g);
				//$(b).removeClass("choose");
				$(b).children('p').removeClass("choose");
				$(b).remove(".myMat-putInMatList");
				$(b).css({
					"width": "max-content",
					"border-radius": "3px",
					"font-size": "1.8rem",
					"color": "#4c6048",
					"float": "left",
					"background-color": "#f1ffee",
					"border": "1px solid #4c6048",
				});
				$(sp).css({
					"font-size": "2rem",
					"color": "#4c6048",
					"background-color": "#f1ffee",
					"border": "none",
					"float": "right",
					"height": "2.3rem",
					"width": "1.9rem",
					"border-radius": "inherit"
				});
				$(sp).mouseover(function (){
					$(this).css({
						"color":"#f1ffee",
						"border":"none",
						"background-color":"#4C6048"
					});
				});
				$(sp).mouseout(function(){
					$(this).css({
						"color":"#4c6048",
						"border":"none",
						"background-color":"#f1ffee"
					});
				});
		$("p.remove").click(function(){
			$(this).parents("li").remove();
				$(".myMat-coldRoomList li button").prop("disabled",false);
				$(".myMat-coldRoomList li button").removeClass("chooseColor");
				console.log($(".myMat-coldRoomList li button").attr("disabled"));
		});
};
// 냉장실에 넣기 버튼 넣었을 때 "choose" 클래스 있는 애들이 냉장 보관실로 넘어가고, "choose" 클래스 없애고,
// 담긴 재료에 냉장 보관실로 넘긴 목록 삭제. css가 안 먹어서 일일히 넣어줌...

function goFreezer(f){
	var b = $("ul li button.choose");
	var g = $("ul li.choose");
	var sp = $("#myMat-putList li button.choose p.choose")
	
			$(".myMat-freezerList").append(g);
				$(b).removeClass("choose");
				$(b).children('p').removeClass("choose");
				$(b).remove(".myMat-putInMatList");
				$(b).css({
					"width": "max-content",
					"border-radius": "3px",
					"font-size": "1.8rem",
					"color": "#4c6048",
					"float": "left",
					"background-color": "#f1ffee",
					"border": "1px solid #4c6048",
				});
				$(sp).css({
					"font-size": "2rem",
					"color": "#4c6048",
					"background-color": "#f1ffee",
					"border": "none",
					"float": "right",
					"height": "2.3rem",
					"width": "1.9rem",
					"border-radius": "inherit"
				});
				$(sp).mouseover(function (){
					$(this).css({
						"color":"#f1ffee",
						"border":"none",
						"background-color":"#4C6048"
					});
				});
				$(sp).mouseout(function(){
					$(this).css({
						"color":"#4c6048",
						"border":"none",
						"background-color":"#f1ffee"
					});
				});
		$("p.remove").click(function(){
			$(this).parents("li").remove();
				$(".myMat-freezerList li button").prop("disabled",false);
				$(".myMat-freezerList li button").removeClass("chooseColor");
				console.log($(".myMat-freezerList li button").attr("disabled"));
		});
};
//냉동실에 넣기 버튼 넣었을 때 "choose" 클래스 있는 애들이 냉동 보관실로 넘어가고, "choose" 클래스 없애고,
//담긴 재료에 냉동 보관실로 넘긴 목록 삭제. css가 안 먹어서 일일히 넣어줌...