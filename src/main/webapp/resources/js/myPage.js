
var text = "";
var f_target = "";
//console.log($("#userName").parent().parent().children(".label_coat").children('p'));
// 조건에 딸린 알림창을 표시하는 함수
function commMessege(text, f_target) {
  alert(text);
  f_target.focus();
}

$(document).ready(function() {
  //정보 수정 버튼
  $('#userSaveBtn').on('click', function() {

    if ($("#custNm2").val() == null) {
      return alert("닉네임을 입력해주세요.");
    }
    if ("#custPw2" == null) {
      return alert("비밀번호를 입력해 주세요.");
    }
    if ("#custPw" == null) {
      return alert("비밀번호 확인을 입력해 주세요.");
    }
    if ("#custTel" == null) {
      return alert("전화번호를 입력해 주세요.");
    }
    // 변수에 담아서 전송
    const data = new URLSearchParams();
    data.append("custNm", $.trim($("#custNm2").val()));
    data.append("custPw", $.trim($("#custPw").val()));
    data.append("custTel", $.trim($("#custTel").val()));
    data.append("custId", $.trim($("#custId").val()));
    //fetch("서버에 보낼 URL넣는란"
    fetch("/ror/modifyUser", {
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

          alert("정보수정 완료");
          window.location.reload(); //새로고침
          
        } else if (data.RESULT == "FAILURE") {
          alert("저장 오류");
        }
      }
    }).catch(function(error) {
      alert("연결 실패:mypage");
    });

  });
  //정보 수정 끝

  // 핸드폰번호 자동 하이픈 펑션
  $("#custTel").on("keyup", function() {
    $(this).val($(this).val().replace(/[^0-9]/g, "")
      .replace(/(^02|^0505|^1[0-9]{3}|^0[0-9]{2})([0-9]+)?([0-9]{4})$/, "$1-$2-$3").replace("--", "-"));
  });
  // $("#chgInfo") = 정보수정버튼
  $("#chgInfo").on("click", function() {
    /*닉네임*/
    if ($("#custNm").val() == "") {
      commMessege("닉네임을 입력해주세요.", $("#custNm"));
      $("#custNm").parent().parent().children(".label_coat").find("p").text("닉네임을 입력해주세요.").css("display", "block");
      return;
    }
    if (!/^[가-힣a-zA-Z\s]+$/.test($('#custNm').val())) {
      commMessege("올바르지 않은 이름 입니다.", $("#custNm"));
      $("#custNm").parent().parent().children(".label_coat").find("p").text("올바르지 않은 이름 입니다.").css("display", "block");
      return;
    }
    if ($("#custNm").val().length < 2) {
      commMessege("닉네임이 너무 짧습니다. 다시 입력해주세요. ※최소 2글자", $("#custNm"));
      $("#custNm").parent().parent().children(".label_coat").find("p").text("닉네임을 두글자 이상 입력해주세요.").css("display", "block");
      return;
    }
    if ($("#custNm").val().length > 10) {
      commMessege("닉네임이 너무 깁니다. 다시 입력해주세요. ※최대 10글자", $("#custNm"));
      $("#custNm").parent().parent().children(".label_coat").find("p").text("닉네임을 열글자 이하로 입력해주세요.").css("display", "block");
      return;
    }
    if ($('body').data('idCheck') == "0" || "") {
      commMessege("사용중인 닉네임 입니다.", $("#custNm"));
      $("#custNm").parent().parent().children(".label_coat").find("p").text("사용중인 닉네임 입니다.").css("display", "block");
      return;
    }
    if ($('body').data('idCheck') == "1") {
      commMessege("사용가능한 닉네임 입니다.", $("#custNm"));
      $("#custNm").parent().parent().children(".label_coat").find("p").text("사용가능한 닉네임 입니다.").css("display", "block").css("color", "green");
      return;
    }
    // 비밀번호
    if ($("#custPw").val() == "") {
      commMessege("비밀번호를 입력해주세요.", $("#custPw"));
      $("#custPw").parent().parent().children(".label_coat").find("p").text("비밀번호를 입력해주세요.").css("display", "block");
      return;
    }
    if ($("#custPw").val().length < 6) {
      commMessege("비밀번호는 6자리 이상이어야 합니다.", $("#custPw"));
      $("#custPw").parent().parent().children(".label_coat").find("p").text("비밀번호를 6자리 이상 입력해주세요.").css("display", "block");
      return;
    }
    if ($("#custPw").val().length > 16) {
      commMessege("비밀번호는 16자리 이하이어야 합니다.", $("#custPw"));
      $("#custPw").parent().parent().children(".label_coat").find("p").text("비밀번호를 16자리 이하로 입력해주세요.").css("display", "block");
      return;
    }
    if (!/(?=.*\d{1,50})(?=.*[!@#$%\^&*]{1,50})(?=.*[a-zA-Z]{1,50}).{6,50}$/.test(String($("#custPw").val()))) {
      commMessege("비밀번호는 숫자, 특문 각 1회 이상, 영문은 2개 이상 사용하여 6자리 이상이어야 합니다.", $("#custPw"));
      $("#custPw").parent().parent().children(".label_coat").find("p").text("비밀번호를 조건에 맞게 입력해주세요.").css("display", "block");
      return;
    }
    if (/(?=.*\d{1,50})(?=.*[!@#$%\^&*]{1,50})(?=.*[a-zA-Z]{1,50}).{6,50}$/.test(String($("#custPw").val()))) {
      commMessege("사용가능한 비밀번호 입니다.", $("#custPw"));
      $("#custPw").parent().parent().children(".label_coat").find("p").text("사용가능한 비밀번호 입니다.").css("display", "block").css("color", "green");
      return;
    }
    // 비밀번호 확인
    if ($("#custPw2").val() == "") {
      commMessege("비밀번호를 확인해주세요.", $("#custPw2"));
      $("#custPw2").parent().parent().children(".label_coat").find("p").text("비밀번호 확인을 입력해주세요.").css("display", "block");
      return;
    }
    if ($("#custPw").val() != $("#custPw2").val()) {
      commMessege("입력하신 비밀번호와 비밀번호 확인이 다릅니다.", $("#custPw2"));
      $("#custPw2").parent().parent().children(".label_coat").find("p").text("비밀번호를 확인해주세요.").css("display", "block");
      return;
    }
    if ($("#custPw").val() == $("#custPw2").val()) {
      commMessege("비밀번호가 일치합니다.", $("#custPw2"));
      $("#custPw2").parent().parent().children(".label_coat").find("p").text("비밀번호가 일치합니다.").css("display", "block").css("color", "green");
      return;
    }
    if ($("#custTel").val() == "") {
      commMessege("휴대전화번호를 입력해주세요.", $("#custTel"));
      $("#custTel").parent().parent().children(".label_coat").find("p").text("휴대전화번호를 입력해주세요.").css("display", "block");
      return;
    }
    if (!/^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$/.test($('#custTel').val())) {
      commMessege("휴대전화번호를 확인해주세요.", $("#custTel"));
      $("#custTel").parent().parent().children(".label_coat").find("p").text("휴대전화번호를 확인해주세요.").css("display", "block");
      return;
    }
    if ($("#send").val() == "") {
      commMessege("인증번호를 입력해주세요.", $("#custTel"));
      $("#userPhone").parent().parent().children(".label_coat").find("p").text("인증번호를 입력해주세요.").css("display", "block");
      return;
    }
    if ($('body').data('numCheck') == "0" || "") {
      commMessege("확인 버튼을 눌러주세요.", $("#custTel"));
      $("#custTel").parent().parent().children(".label_coat").find("p").text("확인 버튼을 눌러주세요.").css("display", "block");
      return;
    }
    if ($('body').data('numCheck') == "1") {
      commMessege("핸드폰 인증 성공", $("#custTel"));
      $("#custTel").parent().parent().children(".label_coat").find("p").text("핸드폰 인증 완료").css("display", "block").css("color", "green");
      return;
    }

  });
});