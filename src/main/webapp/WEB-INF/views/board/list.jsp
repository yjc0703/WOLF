<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page session="false"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>List</title>
<!-- Bootstrap -->
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="<c:url value='/css/bootstrap.min.css' />" rel="stylesheet">
<link href="<c:url value='/css/bootstrap-responsive.min.css' />" rel="stylesheet">
<link href="<c:url value='/css/docs.css' />" rel="stylesheet">
<script type="text/javascript">
<!--
function fnWrite() {
	window.location.href = "<c:url value='/board/write' />";	
}
//-->
</script>
</head>
<body>
<h1>목록</h1>
<div class="bs-docs-grid">
	<div class="row">
		<div class="span9">
			<table class="table">
			<thead>
			<tr>
				<th>NO</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회수</th>
			</tr>
			</thead>
<c:forEach var="board" items="${boardList}" varStatus="loop">
			<tr>
				<td>${board.seq}</td>
				<td>${board.title}</td>
				<td>${board.writer}</td>
				<td>${board.regDate}</td>
				<td>${board.cnt}</td>
			</tr>
</c:forEach>
			</table>
		</div>
		<div class="span1 offset8">
			<button class="btn" onclick="window.location.herf=fnWrite();">새글</button>
		</div>
	</div>
</div>
</body>
</html>