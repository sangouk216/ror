/**
 * 
 */



/*재료 종류(육류,어류 등)클릭시 재료 내려오기*/
$(document).ready(function() {
	$('#mn-read1').click(function() {
		$('.manager-terms1').slideToggle();
	});
	$('#mn-read2').click(function() {
		$('.manager-terms2').slideToggle();
	});
	$('#mn-read3').click(function() {
		$('.manager-terms3').slideToggle();
	});
	$('#mn-read4').click(function() {
		$('.manager-terms4').slideToggle();
	});
});
//재료 관리자 삭제
$(".yes").on('click', function() {

	// if($("#custNm").val() == "") return alert("이름을 입력해주세요.");

	//fetch
	const data = new URLSearchParams();
	data.append("matNm", $.trim($("#choosemnid").children().text()));

	fetch("/admin/deleteMateMng", {
		method: "POST",
		credentials: "same-origin",
		body: data
	}).then(function(res) {
		if (!res.ok) throw Error(res.status);
		return res.json();
	}).then(function(data) {
		if (data) {
			if (data.RESULT == "INVALID") {
				alert(data.MSG);
			} else if (data.RESULT == "SUCCESS") {
				window.location.reload(); //새로고침
				//					var form = document.createElement("form");
				//					form.setAttribute("charset", "UTF-8");
				//					form.setAttribute("method", "POST");
				//					form.setAttribute("action", "/admin/mate/mateMng");
				//					form.setAttribute("target", "_self");


				//console.log(data.ASD);

			} else if (data.RESULT == "FAILURE") {
				alert("삭제 실패되었습니다.");
				// open_modal("modal","","회원가입 중 오류가 발생했습니다. 잠시 후 다시 시도해주세요.");
			}
		}
	}).catch(function(error) {
		alert("연결 중 오류가 발생했습니다 [" + error.message + "]");

	});

});
//재료 관리자 수정
$("#change").on('click', function() {

	//fetch
	const data = new URLSearchParams();
	data.append("matNm", $.trim($(".plusplusmn").val()));
	data.append("matNo", $.trim($("#matNo").val()));

	fetch("/admin/changeMateMng", {
		method: "POST",
		credentials: "same-origin",
		body: data
	}).then(function(res) {
		if (!res.ok) throw Error(res.status);
		return res.json();
	}).then(function(data) {
		if (data) {
			if (data.RESULT == "INVALID") {
				alert(data.MSG);
			} else if (data.RESULT == "SUCCESS") {
				window.location.reload(); //새로고침
				//					var form = document.createElement("form");
				//					form.setAttribute("charset", "UTF-8");
				//					form.setAttribute("method", "POST");
				//					form.setAttribute("action", "/admin/mate/mateMng");
				//					form.setAttribute("target", "_self");


				//console.log(data.ASD);

			} else if (data.RESULT == "FAILURE") {
				alert("삭제 실패되었습니다.");
				// open_modal("modal","","회원가입 중 오류가 발생했습니다. 잠시 후 다시 시도해주세요.");
			}
		}
	}).catch(function(error) {
		alert("연결 중 오류가 발생했습니다 [" + error.message + "]");

	});

});
$("#plus").on('click', function() {

	
	const data = new URLSearchParams();
	//console.log($(".button-fontcolor").text());
	data.append("matNm", $.trim($(".plusplusmn").val()));
  data.append("mainCd", $.trim($(".button-fontcolor").text()));
//	data.append("matNo", $.trim($("#matNo").val()));

	fetch("/admin/plusMateMng", {
		method: "POST",
		credentials: "same-origin",
		body: data
	}).then(function(res) {
		if (!res.ok) throw Error(res.status);
		return res.json();
	}).then(function(data) {
		if (data) {
			if (data.RESULT == "INVALID") {
				alert(data.MSG);
			} else if (data.RESULT == "SUCCESS") {
				window.location.reload(); //새로고침
				
			} else if (data.RESULT == "FAILURE") {
				alert("삭제 실패되었습니다.");
				// open_modal("modal","","회원가입 중 오류가 발생했습니다. 잠시 후 다시 시도해주세요.");
			}
		}
	}).catch(function(error) {
		alert("연결 중 오류가 발생했습니다 [" + error.message + "]");

	});

});

/*재료클릭시 버튼색,글자색,append */
$('.manager-material').click(function() {
	$(this).toggleClass("button-active");
	$(this).toggleClass("button-fontcolor");
	$("#choosemnid").append("<button type='text' class='manager-material button-active button-fontcolor' onclick='removeJeaLyue(this)' value= '" + $(this).text() + "'>" + $(this).text() + "</button>");

	var childmnmaterial = $(this).text();
	if (!$(this).hasClass("button-active")) {
		($("#choosemnid").children()).each(function(i, item) {
			//console.log($(item).text());
			//console.log(childmnmaterial);
			if (childmnmaterial == $(item).text()) {
				$(item).remove();
			}
		});
	}
	countSel();
});

/* && ($('childmnmaterial').click(function()))*/
function removeJeaLyue(a) {
	a.remove();
	$(".button-active").each(function(i, item) {
		if ($(item).attr("type") == "button") {
			if ($(item).text() == $(a).text()) {
				$(item).removeClass("button-active").removeClass("button-fontcolor");
			}
		}
	});
	countSel();
}
/*수정,삭제,추가 버튼 클릭시 모달창 뜨게 하기*/
function modal(id) {
	var zIndex = 9999;
	var modal = $('#' + id);

	// 모달 div 뒤에 희끄무레한 레이어
	var bg = $('<div>')
		.css({
			position: 'fixed',
			zIndex: zIndex,
			left: '0px',
			top: '0px',
			width: '100%',
			height: '100%',
			overflow: 'auto',

			// 레이어 색깔
			backgroundColor: 'rgba(0,0,0,0.4)'
		})
		.appendTo('body');

	modal.css({
		position: 'fixed',
		boxShadow: '0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0,0.19)',

		// 시꺼먼 레이어 보다 한칸 위에 보이기
		zIndex: zIndex + 1,

		// div center 정렬
		top: '50%',
		left: '50%',
		transform: 'translate(-50%, -50%)',
		msTransform: 'translate(-50%, -50%)',
		webkitTransform: 'translate(-50%, -50%)'
	})
		.show()
		// 닫기 버튼 처리, 시꺼먼 레이어와 모달 div 지우기
		.find('.modal_close_btn')
		.on('click', function() {
			bg.remove();
			modal.hide();
		});
}


/*$('#plus_material').click(function(){
	  $('.plus_group').slideToggle();
		 $('.plus_group_label').click(function() {
		   $(this).toggleClass("button-active");
		   $(this).toggleClass("button-fontcolor");
			 
		 });
});*/

$("#plus_material").click(function() {
	$(".plus_group").slideToggle();
});

$(".plus_group_label").click(function() {
	$(this).addClass("button-active");
	$(this).addClass("button-fontcolor");
	if ($(this).hasClass("button-active")) {
		$(".plus_group_label").removeClass("button-active").removeClass("button-fontcolor");
		$(this).addClass("button-active").addClass("button-fontcolor");
	}
});
// 수정,삭제,추가로 넘어가는 모달창 띄우기
$('.but_plus').on('click', function() {

	modal('plus_modal');

});
//$('.but_delete').on('click', function() {
//
//	modal('delete_modal');
//
//});
$('.but_change').on('click', function() {
	if (($("#choosemnid").children()).length < 2) {
		modal('change_modal');
	} else if (($("#choosemnid").children()).length > 1) {
		modal('changeover_modal');
	}
});
$('.but_delete').on('click', function() {
	if (($("#choosemnid").children()).length < 2) {
		modal('delete_modal');
	} else if (($("#choosemnid").children()).length > 1) {
		modal('changeover_modal');
	}
});
/*모달창 다르게 띄우기 위해 클린한 버튼 갯수 세기*/
function countSel() {
	//console.log(($("#choosemnid").children()).length);
	if (($("#choosemnid").children()).length > 0) {
		$(".but_change").prop("disabled", false);
		$(".but_delete").prop("disabled", false);
	} else {
		$(".but_change").prop("disabled", true);
		$(".but_delete").prop("disabled", true);
	}
}

//준영코드 삭제바람
//회원가입
$("#jjyzz").on('click', function() {

	//유효성 검사

	//이름
	if ($("#custNm").val() == "") return alert("이름을 입력해주세요.");

	//fetch
	const data = new URLSearchParams();

	data.append("userNm", $.trim($("#custNm").val()));


	fetch("/admin/mate/getMateList", {
		method: "POST",
		credentials: "same-origin",
		body: data
	}).then(function(res) {
		if (!res.ok) throw Error(res.status);
		return res.json();
	}).then(function(data) {
		if (data) {
			if (data.RESULT == "INVALID") {
				alert(data.MSG);
			} else if (data.RESULT == "SUCCESS") {
				alert("성공");
				console.log(data.ASD);
				$("#custNm3").val(data.ASD);
			} else if (data.RESULT == "FAILURE") {
				alert("실패");
				// open_modal("modal","","회원가입 중 오류가 발생했습니다. 잠시 후 다시 시도해주세요.");
			}
		}
	}).catch(function(error) {
		alert("연결 중 오류가 발생했습니다 [" + error.message + "]");

	});

});