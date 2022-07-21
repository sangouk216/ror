
$('.footer-material').click(function() {
  $(this).toggleClass("button-active");
  $(this).toggleClass("button-fontcolor")


  /*버튼 클릭시 재료바구니 추가*/
  /*재료클릭시 버튼색,글자색,append */
  $(".choice-FtMaterial").append("<button type='text' class='footer-material button-active button-fontcolor' onclick='removebutton(this)' value= '" + $(this).text() + "'>" + $(this).text() + "</button>");


  var childmnmaterial = $(this).text();
  if (!$(this).hasClass("button-active")) {
    ($(".choice-FtMaterial").children()).each(function(i, item) {
      if (childmnmaterial == $(item).text()) {
        $(item).remove();
      }
    });
  }
});
/*재료칸 안에 있는 버튼을 눌러도 제거*/
function removebutton(a) {
  a.remove();
  $(".button-active").each(function(i, item) {
    if ($(item).attr("type") == "button") {
      if ($(item).text() == $(a).text()) {
        $(item).removeClass("button-active").removeClass("button-fontcolor");
      }
    }
  });

}
$(document).ready(function() {
  

  $('#ft-read1').click(function() {
    $('.footer-terms1').slideToggle();
  });
  $('#ft-read2').click(function() {
    $('.footer-terms2').slideToggle();
  });
  $('#ft-read3').click(function() {
    $('.footer-terms3').slideToggle();
  });
  $('#ft-read4').click(function() {
    $('.footer-terms4').slideToggle();
  });
  $(".FtMaterial_plus").click(function() {
    // $(".footer-material").removeClass("button-active").removeClass("button-fontcolor")
    $('.button-active').each(function(i, item) {
      if ($(item).val() == $(a).val()) {
        $(item).removeClass("button-active").removeClass("button-fontcolor");
      }
    });
  });

  $("#foot-EntBtn").on("click", function() {
    $(this).parent().toggleClass("show--active");
    $("#recipe-hide").show();
    $("#foot-EntBtn").text(($("#recipe-wrapper").hasClass("show--active"))
      ? '선택한 재료 닫기' : '선택한 재료 열기')
    $(".footer-ul").toggleClass("is--slied");
  });

  //슬라이드 관련 코드 시작
  $("#ftClick").on("click", function() {
    $(this).parent().toggleClass("is--active");
    $("#footer-choice").show();
    $(this).text(($(this).text() == '재료조합 닫기') ? '재료조합 열기' : '재료조합 닫기')
    $("#recipe-wrapper").toggleClass("is--show")
    if (!$(this).parent().hasClass("is--active")) {
      $("#recipe-wrapper").removeClass("show--active");
    };
    $("#foot-EntBtn").text(($("#recipe-wrapper").hasClass("show--active"))
      ? '선택한 재료 닫기' : '선택한 재료 열기');
    if (!$("recipe-wrappers").hasClass("show--active")) {
      $(".footer-ul").removeClass("is--slied");
    };
    //여기에도 들어가야 선택재료슬라이드가 올라간 채로 닫혀도 열기로 text 변경
  });
  // 슬라이드 관련 코드 끝

 //레시피 조회 클릭시 레시피 게시판 이동 시작
    $('#foot-RecipeBtn').on("click", function() {
alert("구현 예정");
  var form = document.createElement("form");
  form.setAttribute("charset", "UTF-8");
  form.setAttribute("method", "POST");
  form.setAttribute("action", "/recipe/recipeSearch");
  form.setAttribute("target", "_self");

  var hiddenField = document.createElement("input");
  hiddenField.setAttribute("type", "hidden");
  hiddenField.setAttribute("name", "");
  hiddenField.setAttribute("value", "");
//  hiddenField.setAttribute("name", "type");
//  hiddenField.setAttribute("value", "M");
//  hiddenField.append("type","M");
//  hiddenField.append("keyword", ($(".choice-FtMaterial").children().text()));
  form.appendChild(hiddenField);

  document.body.appendChild(form);
  form.submit();


	  /*const data = new FormData();
    data.append("keyword", ($(".choice-FtMaterial").children().text()));
    data.append("type","M");
    fetch("/recipe/recipeSearch2", {
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
          console.log(data.PICK.LIST[0]);
          console.log( "키워드 : "+($(".choice-FtMaterial").children().text()));
          
          for (var i = 0; i < data.PICK.LIST.length; i++) {
            repMenuList1 +='<a href="/recipe/recipeinfo?repNo='+data.PICK.LIST[i].REP_NO+'">';
            repMenuList1 +='<div class="a1 recipeInfo">';
            repMenuList1 +='<div class="a2"><img src="'+data.PICK.LIST[i].REP_IMG_PATH+'" class="recipe-text"></div>';
            repMenuList1 +='<div class="ap RS-tilte">'+data.PICK.LIST[i].REP_NM+'</div>';
            repMenuList1 +='<div class="ap RS-content">'+data.PICK.LIST[i].REP_CTNT+'</div>';
            repMenuList1 +='<div class="ap RS-writer"><small>'+data.PICK.LIST[i].MNG_NM+'</small></div>';
            repMenuList1 +='<div class="ap a6"><small>'+data.PICK.LIST[i].VIEW_CNT+'</small></div>';
            repMenuList1 +='</div>';
            repMenuList1 +='</a>';
            console.log(data.PICK.LIST[i]);
            console.log(repMenuList1);
        }
        location.href='/recipe/recipeSearch'
        $(".a0").html(repMenuList1);
      } else if (data.RESULT == "FAILURE") {
        alert("오류가 발생했습니다");
      }
    }
  })
  .catch(function(error) {

  });
  });*/
//	      var form = document.createElement("form");
//	       form.setAttribute("charset", "UTF-8");
//	       form.setAttribute("method", "GET");
//	       form.setAttribute("action", "/ror/recipe/recipeSearch");
//	       form.setAttribute("target", "_self");
//	      
//	       var hiddenField = document.createElement("input");
//	       hiddenField.setAttribute("type", "hidden");
//	       hiddenField.setAttribute("name", "");
//	       hiddenField.setAttribute("value", "");
//	       form.appendChild(hiddenField);
//	
//	       document.body.appendChild(form);
//	       form.submit();
//
// 	 });
 	 //레시피 게시판 이동 끝
 	 });
}); //도큐레디 끝