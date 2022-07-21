$(document).ready(function(e) {
  //등록하기 버튼
  $('.popupok').on('click', function() {

    if ($(".popup-title").val() == null) {
      return alert("제목을 입력해 주세요.");
    }
    if (".popup-content" == null) {
      return alert("내용을 입력해 주세요.");
    }
    // 변수에 담아서 전송
    const data = new URLSearchParams();
    data.append("ntc_title", $("#ntctitle").val());
    data.append("ntc_ctnt", $("#ntcctnt").val());
    //fetch("서버에 보낼 URL넣는란"
    fetch("/admin/popup/addPopupMngCh", {
      method: "POST",
      credentials: "same-origin",
      body: data
    }).then(function(res) {
      if (!res.ok) throw Error(res.status);
      return res.json();
    }).then(function(data) {
      //      if (data.RESULT == "INVALID") {
      //        swal({ html: true, title: data.MSG, html: data.MSG, icon: "warning" });
      //      } else
      if (data.RESULT == "SUCCESS") {
//        window.location.href = 'http://localhost:9632/admin/popup/popupMng';
        alert("공지사항 등록 되었습니다.");
        var form = document.createElement("form");
        form.setAttribute("charset", "UTF-8");
        form.setAttribute("method", "GET");
        form.setAttribute("action", "/admin/popup/popupMng");
        form.setAttribute("target", "_self");
        /*var hiddenField = document.createElement("input");
        hiddenField.setAttribute("type", "hidden");
        hiddenField.setAttribute("name", "cpnNo");
        hiddenField.setAttribute("value", cpnNo);
        form.appendChild(hiddenField);*/
 
        document.body.appendChild(form);
        form.submit();
        

      } else if (data.RESULT == "FAILURE") {
        alert("저장 오류");
      }
    }).catch(function(Error) {
      alert("연결 실패1233");
    });

  });
});