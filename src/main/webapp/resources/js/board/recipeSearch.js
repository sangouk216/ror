function enterkey() {
  if (window.event.keyCode == 13) {
      // 엔터키가 눌렸을 때
    }
}

$(document).ready(function() {
  
	$('.searchBtn').on("click", function(e) {
		const data = new FormData();
		data.append("keyword", $("#keyword").val());
		data.append("type", $(".search-choice").val());
		console.log("type222 :"+$(".search-choice").val());
//		if(searchYN =='1') {
//          recipeService.getSearchList(cri);
//          trigger( eventType [, extraParameters] )
//      }
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
					var repMenuList1 = '';
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
				$(".a0").html(repMenuList1);
			} else if (data.RESULT == "FAILURE") {
				alert("오류가 발생했습니다");
			}
		}
	})
	.catch(function(error) {

	});
});

	/* 레시피대분류 버튼 */
	$('.nav-item').on("click", function() {
		const data = new FormData();
		data.append("CARD", $(this).text());
		fetch("/recipe/getRecipeMainCd", {
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
					if (data.RESULT == "SUCCESS") {
						var repMenuList = '';
						for (var i = 0; i < data.LIST.length; i++) {
							repMenuList +='<div class="a1 recipeInfo"><div class="a2"><img src="/resources/images/apple.png" class="recipe-text"></div>';
              repMenuList +='<div class="ap RS-tilte"><a href="/recipe/recipeinfo?repNo='+data.LIST[i].REP_NO+'">'+data.LIST[i].REP_NM+'</a></div>';
              repMenuList +='<div class="ap RS-content">' + data.LIST[i].REP_CTNT + '</div>';
              repMenuList +='<div class="ap RS-writer"><small>' + data.LIST[i].MNG_NM + '</small></div>';
              repMenuList +='<div class="ap a6"><small>' + data.LIST[i].VIEW_CNT + '</small></div></div>';
              console.log("data.LIST" + data.LIST[i]);
						}
						$(".a0").chlidren("div").html(repMenuList);
						console.log(repMenuList);
						console.log($(".a0").chlidren("div"));
						$(".a0 div").addClass(".a0");
					} else if (data.RESULT == "FAILURE") {
						alert("오류가 발생했습니다");
					}
				}
			})
			.catch(function(error) {

			});
	}); /* 레시피대분류 버튼 끝 */

  // 레시피 카드 눌렀을때 & 물고가는값
  $('.a1 recipeInfo').on("click", function() {
   const data = new FormData();
    fetch("recipe/recipeBoard/readRecipe", {
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
          form.setAttribute("action", "/recipe/recipeinfo?repNo=" + "${repNo}");
          form.setAttribute("target", "_self");
      
          var hiddenField = document.createElement("input");
          hiddenField.setAttribute("type", "hidden");
          hiddenField.setAttribute("name", "repNo");
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
  });// 레시피 카드 눌렀을때 끝

	// 글쓰기 시작
  $('.recipe-writeBtn').on("click", function() {
    var form = document.createElement("form");
    form.setAttribute("charset", "UTF-8");
    form.setAttribute("method", "GET");
    form.setAttribute("action", "/recipe/recipeAdd");
    form.setAttribute("target", "_self");

    document.body.appendChild(form);
    form.submit();
  });// 글쓰기 끝
});
