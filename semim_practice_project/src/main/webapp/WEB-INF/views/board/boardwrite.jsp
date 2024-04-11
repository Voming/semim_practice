<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>semim board write</title>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<jsp:include page="/WEB-INF/views/common/common_function.jsp" />
</head>
<body>
	<form id="frm-write">
		<div>
			<label>제목</label><input type="text" name="subject">
			<!-- submit 이라면 required로 null체크 가능  -->
		</div>
		<div>
			<label>내용</label>
			<textarea name="content">여기</textarea>
		</div>
		<button type="button" class="btn write">글쓰기</button>
	</form>

	<script>
		$(loadedHandler);

		function loadedHandler() {
			$(".btn.write").on("click", btnWriteClickHandler);
		}

		function btnWriteClickHandler() {
			/* 로그인 체크 */
			
			if (checkLogin("로그인이 되어야 글쓰기가 가능합니다", "write")) {
				return;
			} else {

			}

			/* 글쓰기 */
			console.log($("[name=content]").val()); //textarea에 입력한 값을 나타냄
			console.log($("[name=content]").val().trim().length);
			/*console.log($("[name=content]").html()); //이미 들어있는 값 나타냄
			console.log($("[name=content]").text()); //이미 들어있는 값 나타냄 */

			if ($("[name=subject]").val().trim().length == 0) {
				alert("빈 문자열만 입력할 수 없습니다. 제목을 작성해주세요");
				return;
			}
			if ($("[name=content]").val().trim().length == 0) {
				alert("빈 문자열만 입력할 수 없습니다. 내용을 작성해주세요");
				return;
			}

			var frm = document.getElementById("frm-write");
			frm.method = "post"; //content길이가 길 예정 한글 3, 영문자 1 바이트
			frm.action = "${pageContext.request.contextPath}/board/write";
			frm.submit(); //값을 보내야 controller에서 인식함
		}
	</script>
</body>
</html>