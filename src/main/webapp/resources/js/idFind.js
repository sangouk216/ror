/**
 * 
 */
$(document).ready(function() {
	$("#ingo").on("click", function() {

		//휴대전화번호 유효성 검사
		//fetch
		const data = new URLSearchParams();
		data.append("custTel", $.trim($("#phnumidfi").val()));

		fetch("/join/idFind", {
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

	$("#idfindd").on("click", function() {

		//fetch
		const data = new URLSearchParams();
		data.append("custTel", $.trim($("#phnumidfi").val()));
		data.append("custId", $.trim($("#idshow").val()));

		fetch("/join/modalId", {
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
					modal("show");
		     		$('#idshow').html(data.data.custId);
					

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

$('.infobtnpw').on("click", function() {

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

$('#dlehdlogin').on("click", function() {

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

//핸드폰번호 입력시 인증번호 확인 버튼 활성화 시키기
$(function() {

	$("#phnumidfi").on('input', function() {
		if ($("#phnumidfi").val() == '') {
			$("#ingo").attr("disabled", true)
		}
		else {
			$("#ingo").attr("disabled", false)

		}
	})
});
//
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
		$("#idfindd").attr("disabled", false)
		$("#inres").val("1")
		alert("인증이 완료되었습니다. 아이디 찾기 버튼을 클릭해주세요")
	}
	else {
		$("#inres").val("0")
		alert("인증번호를 확인해주세요");
	}
	console.log($('#inres').val());
	console.log($('#innum').val());
});
//

//// 
//$("#idfindd").on("click", function() {
//	if ($("#inres").val() == "1") {
//		//$("#inok").attr("disabled", false)
//		modal("idchangemd");
//	}
//	else if ($("#inres").val() == "0") {
//
//	}
//
//});
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
$("#idfindd").on("click", function() {
	modal("idchangemd");
});


// 핸드폰번호 자동 하이픈 펑션
$("#phnumidfi").on("keyup", function() {
	$(this).val($(this).val().replace(/[^0-9]/g, "")
		.replace(/(^02|^0505|^1[0-9]{3}|^0[0-9]{2})([0-9]+)?([0-9]{4})$/, "$1-$2-$3").replace("--", "-"));
});
//

