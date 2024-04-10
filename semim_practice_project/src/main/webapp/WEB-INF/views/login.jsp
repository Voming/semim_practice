<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>semim login</title>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
</head>
<body>
	<h1>semim login</h1>
	<fieldset>
		<legend>ajax 방식 로그인</legend>
		<form action="${pageContext.request.contextPath}/login" method="post">
			<div>
				<label>아이디</label><input type="text" name="id">
			</div>
			<div>
				<label>비밀번호</label><input type="password" name="pwd">
			</div>
			<div>
				<input type="submit" value="로그인">
			</div>
		</form>
	</fieldset>



	<fieldset>
		<legend>form 태그 방식 로그인</legend>
		<form id="frm-Login">
			<div>
				<label>아이디</label><input type="text" name="id">
			</div>
			<div>
				<label>비밀번호</label><input type="password" name="pwd">
			</div>
			<div>
				<input type="button" value="로그인" class="btn submit">
			</div>
		</form>
	</fieldset>

	<script>
		$(loadedHandler);
		
		function loadedHandler() {
			$("#frm-Login .btn.submit").on("click", frmclickHandler);

		}

		function frmclickHandler() {
			console.log("클릭");
			console.log($("#frm-Login").serialize());
			
			$.ajax({
				url:"${pageContext.request.contextPath }/login",
				method : "post",
				data : $("#frm-Login").serialize(),
				/* {id : $("[name=id]").val(),
					pwd : $("[name=pwd]").val()} */
			
				success : function(result){
					console.log(result);
					if(result == 1){
						alert("반갑습니다");
						var prePage = "${prePage}";
						
						//만약 prePage가 write면 글쓰기 하려다가 로그인 안되어있어 하게 된 것 -> 다시 글쓰기로 이동
						if(prePage == "write" ){
							location.href = "${pageContext.request.contextPath}/board/write";
						}else{
							location.href = "${pageContext.request.contextPath}/main";
						}
						
					}else{
						alert("아이디와 비밀번호가 일치하지 않습니다.\n 다시 확인하고 로그인해주세요.");
						$("[name=pwd]").val('');
					}
					
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