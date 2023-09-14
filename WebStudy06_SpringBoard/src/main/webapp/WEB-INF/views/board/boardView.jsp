<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<table class="table table-bordered">
	<tr>
		<th><spring:message code="freeboard.boNo" /></th>
		<td>${board.boNo }</td>
	</tr>
	<tr>
		<th><spring:message code="freeboard.boTitle" /></th>
		<td>${board.boTitle }</td>
	</tr>
	<tr>
		<th><spring:message code="freeboard.boWriter" /></th>
		<td>${board.boWriter }</td>
	</tr>
	<!-- 	<tr> -->
	<!-- 		<th>아이피</th> -->
	<%-- 		<td>${board.boIp }</td> --%>
	<!-- 	</tr> -->
	<tr>
		<th><spring:message code="freeboard.boMail" /></th>
		<td>${board.boMail }</td>
	</tr>
	<!-- 	<tr> -->
	<!-- 		<th>비밀번호</th> -->
	<%-- 		<td>${board.boPass }</td> --%>
	<!-- 	</tr> -->
	<tr>
		<th>첨부파일</th>
		<td><c:if
				test="${not empty  board.fileGroup and not empty board.fileGroup.detailList}">
				<c:forEach items="${board.fileGroup.detailList }" var="fileDetail">
					<c:url value="/board/download.do" var="downloadURL">
						<c:param name="atchFileId" value="${fileDetail.atchFileId }" />
						<c:param name="fileSn" value="${fileDetail.fileSn }" />
					</c:url>
					<a href="${downloadURL }">${fileDetail.orignlFileNm }</a>
				</c:forEach>
			</c:if></td>
	</tr>
	<tr>
		<th><spring:message code="freeboard.boContent" /></th>
		<td>${board.boContent }</td>
	</tr>
	<tr>
		<th><spring:message code="freeboard.boDate" /></th>
		<td>${board.boDate }</td>
	</tr>
	<tr>
		<th><spring:message code="freeboard.boHit" /></th>
		<td>${board.boHit }</td>
	</tr>
	<tr>
		<td><a class="btn btn-danger" data-bs-toggle="modal"
			data-bs-target="#exampleModal">삭제</a></td>
	</tr>
</table>

<!-- Modal -->
<div class="modal fade" data-url="${viewURL }" id="exampleModal"
	tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h1 class="modal-title fs-5" id="exampleModalLabel">비밀번호 확인</h1>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			
			<form method="post" action="<c:url value='/board/boardDelete.do'/>">
				<div class="modal-body">
				<p>비밀번호 확인</p>
				<p>
					<input type="hidden" name="boNo" value="${freeboard.boNo }" /> <input
						type="password" name="boPass" class="form-control" /></p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Close</button>
					<button type="submit" class="btn btn-danger">삭제</button>
				
				</div>
			</form>
		</div>
	</div>
</div>
