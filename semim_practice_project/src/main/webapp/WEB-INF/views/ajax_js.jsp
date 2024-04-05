<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
	
	
	</script>

</body>
</html>