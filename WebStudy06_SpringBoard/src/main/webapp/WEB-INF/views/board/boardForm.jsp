<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script
	src="${pageContext.request.contextPath }/resources/js/ckeditor/ckeditor.js"></script>
<form:form method="post" modelAttribute="board"
	enctype="multipart/form-data">
	<form:hidden path="boNo" />
	<table class="table table-border">
		<tr>
			<th>글제목</th>
			<td><form:input path="boTitle" class="form-control" /> <form:errors
					path="boTitle" class="errors" /></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td><form:input path="boWriter" class="form-control" /> <form:errors
					path="boWriter" class="errors" /></td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td><form:input path="boPass" class="form-control" /> <form:errors
					path="boPass" class="errors" /></td>
		</tr>
		<tr>
			<th><spring:message code="freeboard.boMail" /></th>
			<td><form:input path="boMail" class="form-control" type="mail" />
				<form:errors path="boMail" class="errors" /></td>
		</tr>
		<tr>
			<th>IP주소</th>
			<td><form:hidden path="boIp" class="form-control"
					value="${pageContext.request.remoteAddr }" />
				${pageContext.request.remoteAddr } <form:errors path="boIp"
					class="errors" /></td>
		</tr>

		<tr>
			<th>첨부파일</th>
			<td><input type="file" name="boFiles" multiple /> <input
				type="file" name="boFiles" multiple /> <input type="file"
				name="boFiles" multiple /></td>
		</tr>

		<tr>
			<th>내용</th>
			<td><form:input path="boContent" class="form-control" /> <form:errors
					path="boContent" class="errors" /></td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="등록"
				class="btn btn-success" /> <input type="reset" value="취소"
				class="btn btn-danger" /></td>
		</tr>
	</table>
</form:form>
<script type="text/javascript">
	CKEDITOR
			.replace(
					'boContent',
					{
						filebrowserImageUploadUrl : '${pageContext.request.contextPath }/board/uploadImage.do',
					});
</script>