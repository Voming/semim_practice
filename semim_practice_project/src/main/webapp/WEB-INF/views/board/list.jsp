<%@page import="kh.mclass.semim.board.model.dto.BoardListDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>semim board list</title>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<style>
.board.grid {
	display: grid;
	grid-template-columns: 1fr 5fr 3fr 2fr 1fr;
}

.board.grid>div {
	display: flex;
	border: 1px solid black;
	text-align: center;
}

ul {
	list-style-type: none;
}

ul>li {
	display: inline-block;
	padding: 0 10px;
}
</style>
</head>
<body>
	<h1>semim board list</h1>
	[[ 로그인 정보 : ${sssLogin} ]]
	<br>[[ map : ${map.dtolist }]]
	<br> [[ totalPageCount : ${map.totalPageCount }]]
	<br> [[ startPageNum : ${map.startPageNum }]]
	<br> [[ endPageNum : ${map.endPageNum }]]
	<br>

<div><button type="button" class="btn write" >글쓰기</button></div>
<div class="board grid">
<c:choose>
	<c:when test="${empty map.dtolist }">
	글 없어요.
	</c:when>
	<c:otherwise>
		<c:forEach items="${map.dtolist }" var="vo" varStatus="vs">
			<div>${vo.boardId }</div>
			<div><a href="${pageContext.request.contextPath }/board/read?id=${vo.boardId }">${vo.subject }</a></div>
			<div>${vo.writeTime }</div>
			<div>${vo.boardWriter }</div>
			<div>${vo.readCount }</div>
		</c:forEach>
	</c:otherwise>
</c:choose>
</div>
<div >
	<ul>
	<c:if test="${map.startPageNum > 1}">
		<li><a href="${pageContext.request.contextPath }/board/list?page=${map.startPageNum-1 }">  &lt;&lt; </a></li>
	</c:if>
	<c:forEach begin="${map.startPageNum }" end="${map.endPageNum }" var="page">
		<c:if test="${map.currentPageNum == page }">
		<li><strong>${page }</strong></li>
		</c:if>
		<c:if test="${map.currentPageNum != page }">
		<li><a href="${pageContext.request.contextPath }/board/list?page=${page }">${page }</a></li>
		</c:if>
	</c:forEach>
	<c:if test="${map.endPageNum < map.totalPageCount }">
		<li><a href="${pageContext.request.contextPath }/board/list?page=${map.endPageNum+1 }">  &gt;&gt; </a></li>
	</c:if>
	</ul>
</div>



	<script>
		$(loadedHandler);

		function loadedHandler() {
			$(".btn.write").on("click", btnWriteClickHandler);
		}

		function btnWriteClickHandler() {
			//var loginInfo = ${loginInfo}0;// 오류발생  el이나 jstl 에서 사용하는 건 반드시 따옴표 안에!!
			var loginInfo = "${loginInfo}";

			if (loginInfo) {
				var result = confirm("로그인이 되어 글쓰기가능합니다.이동하시겠습니까");
				if (result) {
					location.href = "${pageContext.request.contextPath}/board/write";
				} else {
					location.href = "${pageContext.request.contextPath}/board/list";
				}
			} else {
				var result = confirm("로그인이 되야어 글쓰기가능합니다.이동하시겠습니까");
				if (result) {
					location.href = "${pageContext.request.contextPath}/login?prePage=write";
				} else {
					location.href = "${pageContext.request.contextPath}/board/list";
				}

			}

		}
	</script>
</body>
</html>