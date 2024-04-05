<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>semim main</title>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
</head>
<body>
	<h1>semim main</h1>

	<div>
		<button class="btn join">회원가입</button>
	</div>
	<div>
		<button class="btn login">로그인</button>
	</div>
	<div>
		<button class="btn mypage">마이페이지</button>
	</div>
	<div>
		<button class="btn board">게시판</button>
	</div>

	<script>
		$(loadedHandler);

		function loadedHandler() {
			$(".btn.join").on("click", btnJoinClickHandler);
			$(".btn.login").on("click", btnLoginClickHandler);
			$(".btn.mypage").on("click", btnMypageClickHandler);
			$(".btn.board").on("click", btnBoardClickHandler);
		}

		function btnJoinClickHandler() {
			location.href = "${pageContext.request.contextPath}/join";
		}
		function btnLoginClickHandler() {
			location.href = "${pageContext.request.contextPath}/login";
		}
		function btnMypageClickHandler() {
			location.href = "${pageContext.request.contextPath}/mypage";
		}
		function btnBoardClickHandler() {
			location.href = "${pageContext.request.contextPath}/board";
		}
	</script>


</body>
</html>