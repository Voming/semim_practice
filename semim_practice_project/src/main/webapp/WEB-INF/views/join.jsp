<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>semim join</title>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
</head>
<body>
	<h1>Semim Join</h1>

	<form action="${pageContext.request.contextPath }/join" method="post">
		<div>
			아이디:<input type="text" name="id">
			<button type="button" class="btn checkid">중복확인</button>
		</div>
		<div>
			패스워드:<input type="password" name="pwd">
		</div>
		<div>
			이메일:<input type="text" name="email">
		</div>
		<div>
			<button type="submit">회원가입</button>
		</div>
	</form>

	<div class="member-list"></div>

	<!-- 꼬다리 스크립트위치  -->
	<script>
		$(loadedHandler);
		function loadedHandler() {
			//event 등록
			$(".btn.checkid").on("click", btnCheckidClickHandler);
		}
		function btnCheckidClickHandler() {
			var idVal = $("[name=id]").val();
			$.ajax({
				url : "${pageContext.request.contextPath }/checkid",
				method : "post",
				data : { ///////////// contentType 은 data의 자료형
					cid : $("[name=id]").val(),
				},
				dataType: "json",   //json출력할때 안넣어주면 오류 발생
				///////////// dataType은 success의 result 의 자료형
				//		,dataType : "json"
				
				success : function(result) {  //checkid에서 처리 후 받은 결과값
					console.log(result);
					if (result > 0) {
						alert("사용불가!! 다른아이디를 사용해주세요.");
					} else {
						alert("사용가능");
					} 

					
					console.log(typeof result);
					//[ {},{}]  //모든 멤버 조회
			 		/* var htmlVal = '';
					console.log(result);
					$.each(result, function(){
						console.log(this.memEmail);
						// 백틱
						htmlVal += '<div>'+this.memEmail+'</div>';
					});
					$(".member-list").html(htmlVal);
					console.log(htmlVal); */
					 
				},
				error : function(request, status, error) {
					alert("code: " + request.status + "\n" + "message: "
							+ request.responseText + "\n" + "error: " + error);
				}
			});

		}
	</script>
</body>
</html>