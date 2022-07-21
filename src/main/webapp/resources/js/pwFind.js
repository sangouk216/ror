/**
 * 
 */

$(document).ready(function() {

	$("#ingo").on("click", function() {

		//휴대전화번호 유효성 검사
		//fetch
		const data = new URLSearchParams();
		data.append("custTel", $.trim($("#phonefind").val()));
		data.append("custId", $.trim($("#pwid").val()));

		fetch("/join/pwFind", {
			method: "POST",
			credentials: "same-origin",
			body: data
		}).then(function(res) {
			if (!res.ok) throw Error(res.status);
			return res.json();
		}).then(function(data) {
			if (data) {
				if (data.RESULT == "INVALID") {
					alert(data.MSG);//
				} else if (data.RESULT == "SUCCESS") {
					alert(data.data.checkNum);
					$("#innum2").val(data.data.checkNum);
					console.log($("#innum2").val());
					//히든값 수정해서 인증 완료여부 처리해야합니다 유효성검사도 해야합니다
				} else if (data.RESULT == "FAILURE") {
					alert("실패");
					// open_modal("modal","","회원가입 중 오류가 발생했습니다. 잠시 후 다시 시도해주세요.");
				}
			}
		}).catch(function(error) {
			alert("연결 중 오류가 발생했습니다 [" + error.message + "]");

		});


	});

	//비밀번호 찾기 페이지에서 완료 버튼 눌렀을떄
	$("#pwchok").on("click", function() {

		//fetch
		const data = new URLSearchParams();
		data.append("custId", $.trim($("#pwid").val()));
		data.append("custPw", $.trim($("#imsipw").val()));

		fetch("/join/imsiPwCh", {
			method: "POST",
			credentials: "same-origin",
			body: data
		}).then(function(res) {
			if (!res.ok) throw Error(res.status);
			return res.json();
		}).then(function(data) {
			if (data) {
				if (data.RESULT == "INVALID") {
					alert("data.MSG");//
				} else if (data.RESULT == "SUCCESS") {
					alert("비밀번호가 변경되었습니다.")
					//히든값 수정해서 인증 완료여부 처리해야합니다 유효성검사도 해야합니다
				} else if (data.RESULT == "FAILURE") {
					alert("실패");
					// open_modal("modal","","회원가입 중 오류가 발생했습니다. 잠시 후 다시 시도해주세요.");
				}
			}
//		}).catch(function(error) {
//			alert("연결 중 오류가 발생했습니다 [" + error.message + "]");

		});
		if ($("#imsipw").val() == $("#imsipw2").val()) {

		}
		else {
			alert("임시비밀번호가 일치하지 않습니다. 정확히 입력해 주세요.")
		}
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
});
// 핸드폰번호 자동 하이픈 펑션
$("#phonefind").on("keyup", function() {
	$(this).val($(this).val().replace(/[^0-9]/g, "")
		.replace(/(^02|^0505|^1[0-9]{3}|^0[0-9]{2})([0-9]+)?([0-9]{4})$/, "$1-$2-$3").replace("--", "-"));
});
//})

//	$("#pwchok").on("click", function() {
//		if ($("#imsipw").val() == $("#imsipw2").val()) {
//
//		}
//		else {
//			alert("임시비밀번호가 일치하지 않습니다. 정확히 입력해 주세요.")
//		}
//	});

//모달창
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
//모달창 띄우기
$('.infobtnpw').on('click', function() {
	modal('pwmd');
});

//핸드폰번호 입력값 유무에 따른 버튼 활성화,비활성화
$(function() {

	$("#phonefind").on('input', function() {
		if ($("#phonefind").val() == '' && $("#pwid").val() == '')
			$("#ingo").attr("disabled", true)
		else if ($("#phonefind").val() == '' || $("#pwid").val() == '')
			//	    alert("아이디와 핸드폰 번호를 정확히 입력해 주세요")&&
			$("#ingo").attr("disabled", true)
		else
			$("#ingo").attr("disabled", false) &&
				$("#innum").attr("readonly", false);
	})
});
//})
//인증번호 입력시 인증번호 확인 버튼 활성화 시키기
$(function() {

	$("#innum").on('input', function() {
		if ($("#innum").val() == '') {
			$("#inok").attr("disabled", true)
		}
		else {
			$("#inok").attr("disabled", false)
		}
	})
});
//
//인증확인 버튼 클릭시 일치할때 불일치 할떄 결과값 value에 넣어주기 
$("#inok").on("click", function() {
	if ($("#innum").val() == $("#innum2").val()) {
		$("#next").attr("disabled", false)
		$("#inres").val("1")
		alert("인증이 완료되었습니다. 비밀번호 찾기 버튼을 클릭해주세요")
	}
	else {
		$("#inres").val("0")
		alert("인증번호를 확인해주세요");
	}
	console.log($('#inres').val());
	console.log($('#innum').val());
});
//
// 
$("#next").on("click", function() {
	if ($("#inres").val() == "1") {
		//$("#inok").attr("disabled", false)
		modal('pwmd');
	}
	else if ($("#inres").val() == "0") {

	}

});

$('#idfind').on("click", function() {

	var form = document.createElement("form");
	form.setAttribute("charset", "UTF-8");
	form.setAttribute("method", "GET");
	form.setAttribute("action", "/join/idFind");
	form.setAttribute("target", "_self");

	var hiddenField = document.createElement("input");
	hiddenField.setAttribute("type", "hidden");
	hiddenField.setAttribute("name", "");
	hiddenField.setAttribute("value", "");
	form.appendChild(hiddenField);

	document.body.appendChild(form);
	form.submit();

});



