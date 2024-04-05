<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>semim join</title>
</head>
<body>
	<h1>semim join</h1>
	<form action="${pageContext.request.contextPath}/join" method="post">
		<div>
			아이디:<input type="text" name="id">
			<button class="btn checkid">중복확인</button>
		</div>
		<div>
			비밀번호:<input type="password" name="pwd">
		</div>
		<div>
			이메일:<input type="text" name="email">
		</div>
		<div>
			<button type="submit">회원가입</button>
		</div>
	</form>

	<div class="member-list"></div>

	<script>
	//아이디 중복 체크===========================================
	&(loadedHandler);
	function loadedHandler(){
		$(".btn.chechid").on("click", btnCheckIdClickHandler);
	}
	
	function btnCheckIdClickHandler(){
		var idVal = $("[name=id]").val();
		
		$.ajax({
			url:"${pageContext.request.contextPath}/checkid",  //ajax 로 처리하러감, 화면이동은 아님
			method: "post",
			data : {id:idVal}
			success : function(result) {
				console.log(result);
				console.log(typeof result);
				
				if(result > 0){
					alret("사용불가!!!!!!!");
				} else {
					alret("사용가능~~~~");
				}
			}
			error : function(request, status, error) {
				alert("code: " + request.status + "\n" + "message: "
						+ request.responseText + "\n" + "error: "
						+ error);
			}
		});
		
	}
	
	</script>

</body>
</html>