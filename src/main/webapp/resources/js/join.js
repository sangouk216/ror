/**
 * 
 */
$(document).ready(function() {
	/*아이디 중복검사*/
	$('#ck_id').on('click', function() {
		const data = new URLSearchParams();

		data.append("custId", $.trim($("#userId").val()));

		//페치문
		fetch("/join/idCheckJoin", {
			method: "POST",
			credentials: "same-origin",
			body: data
		})
			.then(function(res) {
				if (!res.ok)
					throw Error(res.status);
				return res.json();
			})
			.then(function(data) {
				if (data) {
					if (data.RESULT == "INVALID") {
						alert(data.MSG);
						// swal({html: true, title: data.MSG, html: data.MSG, icon: "warning"});
					} else if (data.RESULT == "SUCCESS") {
						alert("사용 가능한 ID 입니다");
					} else if (data.RESULT == "FAILURE") {
						alert("통신 중 오류가 발생했습니다");
					}
				}
			}).catch(function(error) {
			});
	});

  /*닉네임 중복검사*/
  $('#ck_nickName').on('click', function() {
    const data = new URLSearchParams();

    data.append("custNm", $.trim($("#userNm").val()));

    //페치문
    fetch("/join/nmCheckJoin", {
      method: "POST",
      credentials: "same-origin",
      body: data
    })
      .then(function(res) {
        if (!res.ok)
          throw Error(res.status);
        return res.json();
      })
      .then(function(data) {
        if (data) {
          if (data.RESULT == "INVALID") {
            alert(data.MSG);
            // swal({html: true, title: data.MSG, html: data.MSG, icon: "warning"});
          } else if (data.RESULT == "SUCCESS") {
            alert("사용 가능한 닉네임 입니다");
          } else if (data.RESULT == "FAILURE") {
            alert("통신 중 오류가 발생했습니다");
          }
        }
      }).catch(function(error) {
      });
  });

	signup.init();
	// 핸드폰번호 자동 하이픈 펑션
	$("#userPhone").on("keyup", function() {
		$(this).val($(this).val().replace(/[^0-9]/g, "")
			.replace(/(^02|^0505|^1[0-9]{3}|^0[0-9]{2})([0-9]+)?([0-9]{4})$/, "$1-$2-$3").replace("--", "-"));
	});
	// 아래 $(document).ready 안의 pw 관련 스크립트는 조건에 따른 에러메세지 출력으로, 
	// 회원가입 조건에 대한 코드가 아닌 메세지 노출 코드이다
	// 비밀번호 공백인 상태로 포커스아웃했을 때 에러메세지 노출, 처음에는 숨겨져있다
	$("#userPw1").on("blur", (function() {
		if ($("#userPw1").val() == "") {
			$("#userPw1").parent().parent().children(".label_coat")
				.find("p").text("비밀번호를 입력해주세요.").css("display", "block");
			return;
		}
		if ($("#userPw1").val().length < 6) {
			$("#userPw1").parent().parent().children(".label_coat")
				.find("p").text("비밀번호는 6자 이상 입력해주세요.")
				.css("display", "block");
			return;
		}
		if ($("#userPw1").val().length > 16) {
			$("#userPw1").parent().parent().children(".label_coat")
				.find("p").text("비밀번호는 16자리 이하로 입력해주세요.")
				.css("display", "block");
			return;
		}
	}));
	// 비밀번호1의 유효성검사메세지 1.실패
	$("#userPw1").keyup(function() {
		let userPw1 = $("#userPw1").val();
		let userPw2 = $("#userPw2").val();
		if (!/(?=.*\d{1,50})(?=.*[!@#$%\^&*]{1,50})(?=.*[a-zA-Z]{1,50}).{6,50}$/
			.test(String($("#userPw1").val()))) {
			$("#userPw1").parent().parent().children(".label_coat")
				.find("p").text("비밀번호는 숫자, 특문 각 1회 이상, 영문은 2개 이상 사용하여 6자리 이상이어야 합니다.")
				.css("display", "block");
			return;
		}
		// 비밀번호1의 유효성검사메세지 2.통과
		if (/(?=.*\d{1,50})(?=.*[!@#$%\^&*]{1,50})(?=.*[a-zA-Z]{1,50}).{6,50}$/
			.test(String($("#userPw1").val()))) {
			$("#userPw1").parent().parent().children(".label_coat")
				.find("p").text("사용가능한 비밀번호 입니다.")
				.css("display", "block").css("color", "green");
			return;
		}
	});
	// 비밀번호2가 비밀번호1과 일치하지 않을때 에러메세지 노출, 처음에는 숨겨져있다
	$("#userPw2").keyup(function() {
		let userPw1 = $("#userPw1").val();
		let userPw2 = $("#userPw2").val();
		if (userPw1 != userPw2) {
			$("#userPw2").parent().parent().children(".label_coat")
				.find("p").text("비밀번호가 일치하지 않습니다.")
				.css("display", "block").css("color", "red");
			// 일치하는경우
		} else {
			$("#userPw2").parent().parent().children(".label_coat")
				.find("p").text("비밀번호가 일치합니다.")
				.css("display", "block").css("color", "green");
		}
	});
	// 숫자만입력
	$("#userPhone").bind("keypress", function(e) {
		signup.check.checkDigit(e);
	});
	// 한글입력방지
	$("#userId, #userPhone").css("ime-mode", "disabled");
	$("#userId").bind('input', function() {
		signup.isCheckedOverlapId = false;
	});
	//	// 버튼활성화
	//	$(function() {
	//		//  $('#ck_id').on('click', signup.check.id);
	//		$('#ck_nickName').on('click', signup.check.name);
	//		$('#ck_phone').on('click', signup.check.phone);
	//		$('.confirm_btn').on('click', signup.api);
	//	});

	//	// 중복확인 버튼 눌렀을때 인풋값 공백이면 모달의 사용하기버튼을 숨기고 그게 아니라면 보여준다.
	//	$(function() {
	//		const ckBtn = $($('#ck_id'), $('#ck_nickName')); //중복확인
	//		const toggleBtn = $('#toggle_btn'); //사용하기
	//		inputVal = $('input[name="userId"]').val(), $('input[name="userName"]').val();
	//		if ((ckBtn).on('click')) {
	//			inputVal == "";
	//			($('#modal')).find(toggleBtn).hide();
	//		} else {
	//			find(toggleBtn).show();
	//			return true;
	//		}
	//	});
	//	// 가입하기 버튼 눌렀을때 boolean true면 가입되고 로그인하기 버튼 노출, 그게 아니라면 실패
	//	$('.join_btn').on('click', function() {
	//		if (
	//			isValidId == true &&
	//			isValidPw == true &&
	//			isValidPhone == true &&
	//			isCheckedOverlapId == true &&
	//			isCheckedOverlapName == true
	//		) {
	//			signup.api.send();
	//			$('#modal_msg').text("회원가입이 완료되었습니다.");
	//			$('#toggle_btn').text("로그인하기");
	//		} else {
	//			if (!isValidId) {
	//				return $('#modal_msg').text("아이디를 중복확인 하세요.");
	//				$('.confirm_btn').hide();
	//			}
	//			if (!isValidPw2) {
	//				return $('#modal_msg').text("비밀번호를 확인하세요.");
	//				$('.confirm_btn').hide();
	//			}
	//			if (!isCheckedOverlapId) {
	//				return $('#modal_msg').text("아이디 중복확인을 하세요.");
	//				$('.confirm_btn').hide();
	//			}
	//			if (!isCheckedOverlapName) {
	//				return $('#modal_msg').text("닉네임 중복확인을 하세요.");
	//				$('.confirm_btn').hide();
	//			}
	//			if (!isValidPhone) {
	//				return $('#modal_msg').text("휴대폰번호 본인인증을 해주세요.");
	//				$('.confirm_btn').hide();
	//			}
	/*회원가입*/
	$('#join_btnid').on('click', function() {
		const data = new URLSearchParams();

		data.append("custId", $.trim($("#userId").val())); 
		data.append("custPw", $.trim($("#userPw1").val()));
		data.append("custNm", $.trim($("#userNm").val()));
		data.append("custTel", $.trim($("#userPhone").val()));
		data.append("mngNm", $.trim($("#mngNm").val()));
	
		//페치문
		fetch("/join/userJoin", {
			method: "POST",
			credentials: "same-origin",
			body: data
		})
			.then(function(res) {
				if (!res.ok)
					throw Error(res.status);
				return res.json();
			})
			.then(function(data) {
				if (data) {
					if (data.RESULT == "INVALID") {
						alert(data.MSG);
						// swal({html: true, title: data.MSG, html: data.MSG, icon: "warning"});
					} else if (data.RESULT == "SUCCESS") {
						alert("회원가입이 되었습니다.");
					} else if (data.RESULT == "FAILURE") {
						alert("통신 중 오류가 발생했습니다");
					}
				}
			}).catch(function(error) {
				// open_modal("modal","프로그램 선택","연결 중 오류가 발생했습니다 [" + error.message + "]");
			});
		//		}
	});
}); /*$(document).ready 끝*/

//$('.ck_btn').on('click', function() {
//	// 모달창 띄우기
//	modal('modal');
//});
//$('.join_btn').on('click', function() {
//	// 모달창 띄우기
//	modal('modal');
//});
// 버튼활성화
// 모달
function modal(id) {
	const zIndex = 9999;
	const modal = $('#modal');
	const bg = $('<div>')
		.css({
			position: 'fixed',
			zIndex: zIndex,
			left: '0px',
			top: '0px',
			width: '100%',
			height: '100%',
			overflow: 'auto',
			backgroundColor: 'rgba(0,0,0,0.4)'
		})
		.appendTo('body');

	modal
		.css({
			position: 'fixed',
			boxShadow: '0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19)',
			zIndex: zIndex + 1,
			top: '50%',
			left: '50%',
			transform: 'translate(-50%, -50%)',
			msTransform: 'translate(-50%, -50%)',
			webkitTransform: 'translate(-50%, -50%)'
		})
		.show()
		.find('.modal_close_btn')
		.on('click', function() {
			bg.remove();
			modal.hide();
		});
}

// 회원가입 조건 양식 시작
const signup = {
	init: function() {
		signup.check.init();
	}
}
// boolean 체크
signup.check = {
	isValidId: false,
	isValidPw1: false,
	isValidPw2: false,
	isValidPhone: false,
	isCheckedOverlapId: false,
	isCheckedOverlapName: false,
	init: function() {
		signup.check.isValidId = false;
		signup.check.isValidPw1 = false;
		signup.check.isValidPw2 = false;
		signup.check.isValidPhone = false;
		signup.check.isCheckedOverlapId = false;
		signup.check.isCheckedOverlapName = false;
	},
	id: function() {
		signup.check.isValidId = false;
		const userId = $.trim($("#userId").val());
		const confirm = $('.confirm_btn');
		if (userId == "") {
			$('#modal_msg').text("아이디를 입력해 주세요.");
			confirm.hide();
			return false;
		}
		//        if (!signup.check.validateId(userId)) {
		//            $('#modal_msg').text("아이디는 띄어쓰기 없이 6~10자리의 영문자와 숫자 조합과 '_'만 가능합니다.");
		//           confirm.hide();
		//            return false;
		//        }
		/* 사용가능, 컨펌버튼 노출 예 */
		if (userId == 'stringg') {
			$('#modal_msg').text("사용 가능한 아이디 입니다.");
			alert("stringg")
			confirm.show();
			return signup.check.isValidId = true;
		}
	},
	pw: function() {
		signup.check.isValidPw1 = false;
		signup.check.isValidPw2 = false;
		if ($("#userPw1").val() == "") {
			return false;
		}
		if ($("#userPw1").val().length < 6 || $("#userPw1").val().length > 16) {
			return false;
		}

		/* 유효성 */
		if (!/(?=.*\d{1,50})(?=.*[!@#$%\^&*]{1,50})(?=.*[a-zA-Z]{1,50}).{6,50}$/.test(String(userPw1))) {
			return false;
		}
		// 통과
		if (/(?=.*\d{1,50})(?=.*[!@#$%\^&*]{1,50})(?=.*[a-zA-Z]{1,50}).{6,50}$/.test(String(userPw1))) {
			signup.check.isValidPw1 = true;
		}
		// 비밀번호1과 2가 같지않을 때
		if (userPw1 != userPw2) {
			return false;
			// 일치하는경우
		}
		if (userPw1 == userPw2) {
			signup.check.isValidPw2 = true;
		}
	},
	phone: function() {
		/* signup.check.isValidPhone = false;
		const len = $.trim($('#userPhone').val()).length;
		const userPhone = $.trim($('#userPhone').val());
		const hpNo1 = "", hpNo2 = "", hpNo3 = "";
		let regExp = /[^0-9]/;
		const confirm = $('.confirm_btn');
		if (regExp.test(userPhone)) {
			$('#hpCheckMessage').show();
			return false;
		}
		if (len < 3) {
			$('#hpCheckMessage').show();
			return false;
		}
		const tempNo = userPhone.substring(3, len);
		if (tempNo.length < 5) {
			$('#hpCheckMessage').show();
			return false;
		}
		hpNo1 = userPhone.substring(0, 3);
		hpNo2 = tempNo.substring(0, tempNo.length - 4);
		hpNo3 = tempNo.substring(tempNo.length - 4, tempNo.length);
	  */

		signup.check.isValidPhone = false;
		const confirm = $('.confirm_btn');
		if ($("#userPhone").val() == "") {
			return $('#modal_msg').text("핸드폰번호를 입력하세요");
			confirm.hide();
		}

		if (!/^(01[016789]{1}|02|0[3-9]{1}[0-9]{1})-?[0-9]{3,4}-?[0-9]{4}$/.test($("#userPhone").val())) {
			signup.check.isValidPhone = false;
			return false;
		} else {
			signup.check.isValidPhone = true;
			return true;
		}
	},
	name: function() {
		const userName = $.trim($('#userName').val());
		const confirm = $('.confirm_btn');
		if (userName == '') {
			$('#modal_msg').text("name을 입력해주세요.");
			confirm.hide();
			return false;
		}
		if (userName == '사용중') {
			$('#modal_msg').text("사용중인 닉네임입니다.");
			confirm.hide();
			return true;
		}
		if (userName == 'name') {
			$('#modal_msg').text("사용가능한 닉네임입니다.");
			confirm.show();
			return true;
		}
	},
	validateId: function(val) {
		const confirm = $('.confirm_btn');
		if (signup.check.sTextByteLen(val) < 6 || signup.check.sTextByteLen(val) > 10) {
			confirm.hide();
			return false;
		}
		else {
			let regExp = /[^0-9A-Za-z_]/;
			confirm.hide();
			return (!regExp(val));
		}
	},
	sTextByteLen: function(sText) {
		let sTextLen = 0;
		for (var i = 0; i < sText.length; i++) {
			if (sText.charCodeAt(i) > 128) {
				sTextLen += 2;
			}
			else {
				sTextLen += 1;
			}
		}
		return sTextLen;
	},
	checkDigit: function(e) {
		if (e.which && (e.which > 47 && e.which < 58 || e.which == 8)) {
		}
		else {
			e.preventDefault();
		}
	}
}
// 서버에 요청해서 중복인지 아닌지 체크하는 함수들 모음
// url은 현재 가상으로 둔다
signup.api = {
	url: "",
	// 아이디 중복체크
//	isCheckedOverlapId: function() {
//		const userId = $.trim($("#userId").val());
//		confirm = $('.confirm_btn'); //사용하기버튼
//		const confirm = $('.confirm_btn');
//		const url = "/userInfo/isCheckLoginId";
//		const model = { userId: userId };
//		signup.check.isCheckedOverlapId = false;
//		if (userId == '') {
//			$('#modal_msg').text('아이디를 입력하세요');
//			confirm.hide();
//			return;
//		}
//		if (!signup.check.validateId(userId)) {
//			$('#modal_msg').text("아이디는 띄어쓰기 없이 6~10자리의 영문자와 숫자 조합과 '_'만 가능합니다.");
//			confirm.hide();
//			return;
//		}
//		ajaxService.Post(
//			url,
//			model,
//			function(data) {
//				if (typeofdata == 'undefined' || data == null) {
//					confirm.hide();
//					return $('#modal_msg').text("다시 한 번 시도해주세요.");
//				}
//				if (data.ErrorCode == 0) {
//					$('#modal_msg').text("사용 가능한 아이디입니다.");
//					signup.check.isCheckedOverlapId = true;
//					confirm.show();
//					return true;
//				}
//				else {
//					$('#modal_msg').text("이미 사용중인 아이디입니다.");
//					confirm.hide();
//					return false;
//				}
//			});
//	},
	// 닉네임 중복체크
	isCheckedOverlapName: function() {
		const userName = $.trim($("#userName").val());
		const confirm = $('.confirm_btn');
		const url = "/userInfo/isCheckLoginName";
		const model = { userName: userName };
		signup.check.isCheckedOverlapName = false;
		if (userName == '') {
			$('#modal_msg').text('닉네임을 입력하세요');
			confirm.hide();
			return;
		}
//		ajaxService.Post(
//			url,
//			model,
//			function(data) {
//				if (typeofdata == 'undefined' || data == null) {
//					return $('#modal_msg').text("다시 한 번 시도해주세요.");
//					confirm.hide();
//				}
//				if (data.ErrorCode == 0) {
//					$('#modal_msg').text("사용 가능한 닉네임입니다.");
//					signup.check.isCheckedOverlapName = true;
//					confirm.show();
//					return true;
//				}
//				else {
//					$('#modal_msg').text("이미 사용중인 닉네임입니다.");
//					confirm.hide();
//					return false;
//				}
//			});
	},
	// 디비에 정보 전송
	send: function() {
		if (!signup.check.isValidId) {
			return $('#modal_msg').text("아이디 입력이 잘못 되었습니다.");
		}
		else if (!signup.check.isValidPw1 || !signup.check.isValidPw2) {
			return $('#modal_msg').text("비밀번호 입력이 잘못 되었습니다.");
		}
		else if (!signup.check.isValidPhone) {
			return $('#modal_msg').text("핸드폰번호 입력이 잘못 되었습니다.");
		}
		if (!signup.check.isCheckedOverlapId) {
			return $('#modal_msg').text("아이디 중복확인을 해주세요.");
		}
		if (!signup.check.isCheckedOverlapName) {
			return $('#modal_msg').text("닉네임 중복확인을 해주세요.");
		}
		if ($.trim($('#userName').val()) == '') {
			return $('#modal_msg').text("닉네임을 입력해 주세요.");
		}
		// 변수에 담아서 전송
		const userPhone = $.trim($('#userPhone').val());
		const tempNo = userPhone.substring(3, userPhone.length);
		const hpNo1 = hpNo.substring(0, 3);
		const hpNo2 = tempNo.substring(0, tempNo.length - 4);
		const hpNo3 = tempNo.substring(tempNo.length - 4, tempNo.length);
		const model = {
			userId: $.trim($('#userId').val()),
			userPw: $.trim($('#userPw2').val()),
			userName: $.trim($('#userName').val()),
			userPhone: hpNo1 + "-" + hpNo2 + "-" + hpNo3
		};
		$('body').val(JSON.stringify(model));

		//페치문
		fetch("서버에 보낼 URL넣는란", {
			method: "POST",
			credentials: "same-origin",
			body: data
		})
			.then(function(res) {
				if (!res.ok)
					throw Error(res.status);
				return res.json();
			})
			.then(function(data) {
				if (data) {
					if (data.RESULT == "INVALID") {
						swal({ html: true, title: data.MSG, html: data.MSG, icon: "warning" });
					} else if (data.RESULT == "SUCCESS11") {
						alert("성공");
					} else if (data.RESULT == "FAILURE") {
						alert("저장 중 오류가 발생했습니다");
					}
				}
			}).catch(function(error) {
				// open_modal("modal","프로그램 선택","연결 중 오류가 발생했습니다 [" + error.message + "]");
			});
	}
}


//회원가입 버튼 클릭시 로그인 페이지로 이동시작
$('#join_btnid').on("click", function() {
	alert("가입을 축하합니다.");

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
//회원가입 버튼 클릭시 로그인 페이지로 이동끝