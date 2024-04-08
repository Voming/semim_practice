<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>semim main</title>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
</head>
<body>
	<!-- 확인용 -->
	[[${loginInfo}]] [[<%=session.getAttribute("loginInfo")%>]]

	<h1>semim main</h1>
	<form id="frm-logout">
		<c:choose>
			<c:when test="${empty loginInfo}">
				<div>
					<button type="button" class="btn join">회원가입</button>
				</div>
				<div>
					<button type="button" class="btn login">로그인</button>
				</div>
			</c:when>
			<c:otherwise>
				<div>
					<button type="button" class="btn logout">로그아웃</button>
				</div>
				<div>
					<button type="button" class="btn mypage">마이페이지</button>
				</div>
			</c:otherwise>
		</c:choose>
		<div>
			<button type="button" class="btn board">게시판</button>
		</div>
	</form>
	<!-- 
	<form id="frm-logout">
		<div>
			<button class="btn join">회원가입</button>
		</div>
		<div>
			<button class="btn login">로그인</button>
		</div>
		<div>
			<button class="btn logout">로그아웃</button>
		</div>
		<div>
			<button class="btn mypage">마이페이지</button>
		</div>
		<div>
			<button class="btn board">게시판</button>
		</div>
	</form> -->

	<script>
		$(loadedHandler);

		function loadedHandler() {
			$(".btn.join").on("click", btnJoinClickHandler);
			$(".btn.login").on("click", btnLoginClickHandler);
			$(".btn.logout").on("click", btnLogoutClickHandler);
			$(".btn.mypage").on("click", btnMypageClickHandler);
			$(".btn.board").on("click", btnBoardClickHandler);
		}

		function btnJoinClickHandler() {
			location.href = "${pageContext.request.contextPath}/join";
		}
		function btnLoginClickHandler() {
			location.href = "${pageContext.request.contextPath}/login";
		}
		function btnLogoutClickHandler() {
			//get 방식으로 사용하지 않음
			//location.href = "${pageContext.request.contextPath}/logout";
			//
			
			alert("로그아웃 되었습니다.")
			var frmlogout = document.getElementById("frm-logout");
			frmlogout.action = "${pageContext.request.contextPath}/logout";
			frmlogout.method = "post";  //화면을 불러오는것이 아님, post 방식이어야함
			frmlogout.submit();
		}
		function btnMypageClickHandler() {
			location.href = "${pageContext.request.contextPath}/mypage";
		}
		function btnBoardClickHandler() {
			location.href = "${pageContext.request.contextPath}/board/list";
		}
	</script>


</body>
</html>