<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>semim board read</title>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<jsp:include page="/WEB-INF/views/common/common_function.jsp" />
</head>
<body>
	<h1>semim board read</h1>
	<div>
		<div>${dto.subject }</div>
		<div>${dto.content }</div>
		<div>${dto.readCount }</div>
	</div>

	<form id="frm-reply">
		<div>
			<label>댓글</label> <input type="text" name="content" required>
			<button type="button" class="btn reply">댓글달기</button>
		</div>
	</form>
	
	<script>
	$(loadedHandler);
	function loadedHandler(){
		//event등록
		$(".btn.reply").on("click", btnReplyClickHandler);
	}
	function btnReplyClickHandler(){
		if(checkLogin("로그인이 되어야 글쓰기가 가능합니다.\n 로그인 페이지로 이동하시겠습니까?", "wirte")){
			return;
		}
		
	}
	
	</script>

</body>
</html>