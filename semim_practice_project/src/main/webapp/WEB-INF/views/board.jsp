<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>semim board list</title>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
</head>
<body>
<h1>semim board list</h1>
[[ 로그인 정보 ${loginInfo}]]


[[${dtolist}]]
[[<%=request.getAttribute("dtolist") %>]]

<div>
<button type="button" class="btn write">글쓰기</button>
<!--  onclick="location.href = '${pageContext.request.contextPath}/board/write'"로 화면 이동도 가능 -->
</div>


<script>
$(loadedHandler);

function loadedHandler() {
	$(".btn.write").on("click", btnWriteClickHandler);
}

function btnWriteClickHandler() {
	//var loginInfo = ${loginInfo}0;// 오류발생  el이나 jstl 에서 사용하는 건 반드시 따옴표 안에!!
	var loginInfo = "${loginInfo}";
	
	if(loginInfo){	
		var result = confirm("로그인이 되어 글쓰기가능합니다.이동하시겠습니까");
		if(!result){
			location.href = "${pageContext.request.contextPath}/board/list";
		}else{
			location.href = "${pageContext.request.contextPath}/board/write";
		}
	}else{
		location.href = "${pageContext.request.contextPath}/login";
	}
	
}
</script>
</body>
</html>