$(document).ready(function(){
	$(".memNo").val("memNo").prop("selected",function(){
		$(".inputText").on("keyup",function(){
			var textInput=$(this).val();
			$(".user-refSubmit").click(function(){	
				$(".user-userInfoBody").filter(function(){
					$(this).toggle($(this).text().indexOf(textInput) > -1)
				});
			});
		});
	});
	$("#userRefSubmit").on("click",function(){
	 alert("기능 구현 예정");
	})
	
});
$("#userphone").on("keyup", function() {
	$(this).val($(this).val().replace(/[^0-9]/g, "")
		.replace(/(^02|^0505|^1[0-9]{3}|^0[0-9]{2})([0-9]+)?([0-9]{4})$/, "$1-$2-$3").replace("--", "-"));
});

//$("#user1-joinDate").on("keyup", function() {
//	$(this).val($(this).val().replace(/[^0-9]/g, "")
//		.replace(/(^02|^0505|^1[0-9]{3}|^0[0-9]{2})([0-9]+)?([0-9]{4})$/, "$1-$2-$3").replace("--", "-"));
//});