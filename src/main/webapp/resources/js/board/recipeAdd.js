//돌아가기 버튼 클릭시 자유게시판 목록 이동 시작
//var jsonData = JSON.parse('${category}');
console.log(jsonData);

var cate1Arr = new Array();
var cate1Obj = new Object();

for(var i = 0; i < jsonData.length; i++) {
	if(jsonData[i].level == "1") {
		cate1Obj = new Object();
		cate1Obj.cateCode = jsonData[i].cateCode;
		cate1Obj.cateName = jsonData[i].cateName;
		cate1Arr.push(cate1Obj);
	}
}
var cate1Select = $("select.category1");
for(var i = 0; i < cate1Arr.length; i++) {
	cate1Select.append("<option value='"+cate1Arr[i].cateCode+"'>"
		+cate1Arr[i].cateName+"</option>")
}
$(document).on("change", "select.category1", function(){
	var cate2Arr = new Array();
	var cate2Obj = new Object();
// 2차 분류 셀렉트 박스에 삽입할 데이터 준비
	for(var i = 0; i < jsonData.length; i++) {
		if(jsonData[i].level == "2") {
			cate2Obj = new Object();  //초기화
			cate2Obj.cateCode = jsonData[i].cateCode;
			cate2Obj.cateName = jsonData[i].cateName;
			cate2Obj.cateCodeRef = jsonData[i].cateCodeRef;
			cate2Arr.push(cate2Obj);
		}
	}
	var cate2Select = $("select.category2");
//	cate2Select.children().remove();
	$("optionLselected", this).each(function(){
		var selectVal = $(this).val();
		cate2Select.append("<option value=''>전체</option>");
		for(var i = 0; i < cate2Arr.length; i++) {
			cate2Select.append("<option value='" + cate2Arr[i].cateCode + "'>"
				+ cate2Arr[i].cateName + "</option>");
		} 
	});
});

$(document).ready(function() {
    $('.freeUse-backBtn').on("click", function() {
	    //  var recipeNo = $("#recipeNo").val();
	      var form = document.createElement("form");
	       form.setAttribute("charset", "UTF-8");
	       form.setAttribute("method", "POST");
	       form.setAttribute("action", "/board/recipeSearch");
	       form.setAttribute("target", "_self");
	      
	       var hiddenField = document.createElement("input");
	       hiddenField.setAttribute("type", "");
	       hiddenField.setAttribute("name", "");
	       hiddenField.setAttribute("value", "");
	       form.appendChild(hiddenField);
	
	       document.body.appendChild(form);
	       form.submit();
	});
});

function recipeAdd() {
	const data = new FormData();
	data.append("repName", $("#repName").val());
	data.append("repCtnt", $("#repCtnt").val());
  data.append("repMainCd", $('.maincode-choice').val());
	data.append("repMidCd", $("#repMidCd").val());
	data.append("memNo", $("#memNo").val());
	data.append("writer", $("#writer").val());
	
	fetch("/recipe/recipeBoard/insertRecipe", {
        method: "POST",
        credentials: "same-origin",
        body: data
    }).then(function(res){
        if (!res.ok) throw Error(res.status);
        	console.log(data);
            return res.json();
    }).then(function(data) {
        if (data) {
            if (data.RESULT == "SUCCESS") {
		    var form = document.createElement("form");
		       form.setAttribute("charset", "UTF-8");
		       form.setAttribute("method", "POST");
		       form.setAttribute("action", "/recipe/recipeSearch");
		       form.setAttribute("target", "_self");
		      
		       var hiddenField = document.createElement("input");
		       hiddenField.setAttribute("type", "");
		       hiddenField.setAttribute("name", "");
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
	
}
 	 //자유게시판 목록 이동 끝