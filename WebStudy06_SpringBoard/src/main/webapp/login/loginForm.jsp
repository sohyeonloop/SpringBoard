<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>

<%--
	String savedId = Optional.ofNullable(request.getCookies()) // 여기까지는 (쿠기가)있다면 이라는 조건
			.map(ca->    //ca가 받는건 쿠키의 배열
				Stream.of(ca) //가변형 파라미터 이게뭐지 ?
							.filter(c->"idCookie".equals(c.getName())) // 하나만 받아서 걸러내기 getName-> 한개의 쿠키 이름을 가져오기(idCookie라는 이름을 가진)
							.findFirst() //쿠키객체를 받아옴 근데 우린 값만 받아와야됨 
							.map(Cookie::getValue) // fc : 찾은 쿠키가 들어감 -> {값을 찾음}  fc-> {fc.getValue()} 이거랑 동일한 문법 줄여서 쓸수있는 방법!!
							.orElse(null) // 없으면 null을 반환 
						).orElse(null);  // 이건 Stream에 대한 orElse ()->{} 이거일때 {}이게 한문장이면 {} 이거 생략 가능 () 얘도 생략가능 빨리 익숙해지기 
--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.error {
		color : red;
	}
</style>
</head>
<body>
<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION }">
	<div class="error">
		${SPRING_SECURITY_LAST_EXCEPTION.message} 
		<c:remove var="SPRING_SECURITY_LAST_EXCEPTION" scope="session"/>
	</div>
</c:if>


<form action="${pageContext.request.contextPath }/login/loginProcess" method="post">
	<ul>
		<li>
			아이디 : <input type="text" name="memId" value="${cookie.idCookie.value}"/>
			<input type="checkbox" name="idSave" ${not empty cookie.idCookie ? "checked":""}/>아이디저장 
		</li>
		<li>
			비밀번호 : <input type="text" name="memPass" />
		</li>
		<li>
<!-- 		최프 할때는 꺼놓고 쓰기  -->
<%-- 			<security:csrfInput/> --%>
			<button type="submit">로그인</button>
		</li>
	</ul>
</form>

</body>
</html>