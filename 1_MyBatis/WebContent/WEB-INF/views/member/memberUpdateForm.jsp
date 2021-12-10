<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, member.model.vo.Member"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%-- <%
	Member loginUser = (Member)session.getAttribute("loginUser");
	String d = loginUser.getBirthDay().toString();
	System.out.println(d);
%> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#infoTable{margin: auto;}
</style>
</head>
<body>
	<jsp:include page="../common/menubar.jsp"/>
	<h1 align="center">내 정보 수정</h1>
	<form action="${ contextPath }/mupdate.me" method="post">
		<table id="infoTable">
			<tr>
				<td width="100px">* 아이디</td>
				<td>${ loginUser.userId }</td>
			</tr>
			<tr>
				<td>* 이름</td>
				<td><input type="text" name="userName" value="${ loginUser.userName }" required></td>
			</tr>
			<tr>
				<td>* 닉네임</td>
				<td><input type="text" name="nickName" value="${ loginUser.nickName }" required></td>
			</tr>
			<tr>
				<td> &nbsp;&nbsp;이메일</td>
				<td><input type="email" name="email" value="${ loginUser.email }"></td> <!-- el은 null일때 null이 출력되지 않고, 아무 값도 반환하지 않음  -->
			</tr>
			<tr>
				<td> &nbsp;&nbsp;생년월일</td>
				<td>
					<select name="year">
						<c:forEach begin="<%= new GregorianCalendar().get(Calendar.YEAR) - 100 %>" end="<%= new GregorianCalendar().get(Calendar.YEAR) %>" var="i">
							
							<c:if test="${fn:substring(loginUser.birthDay, 0, 4) == i}">
								<option value="${ i }" selected>${ i }</option>
							</c:if>
							<c:if test="${fn:substring(loginUser.birthDay, 0, 4) != i}">
								<option value="${ i }">${ i }</option>
							</c:if>
						</c:forEach>
					</select>
					<select name="month">
						<c:forEach begin="1" end="12" var="i">
							<c:if test="${fn:substring(loginUser.birthDay, 5, 7) == i}">
								<option value="${ i }" selected>${ i }</option>
							</c:if>
							<c:if test="${fn:substring(loginUser.birthDay, 5, 7) != i}">
								<option value="${ i }">${ i }</option>
 							</c:if>
						</c:forEach>
					</select>
					<select name="date">
						<c:forEach begin="1" end="31" var="i"> <!-- 윤년 고려 위해 조건문 추가해줘야함. 이 방식 대신, 캘린더 제공하는 것이 나음(maxDate 0 오늘날짜)-->
							<c:if test="${fn:substring(loginUser.birthDay, 8, 10) == i}">
								<option value="${ i }" selected>${ i }</option>
							</c:if>
							<c:if test="${fn:substring(loginUser.birthDay, 8, 10) != i}">
								<option value="${ i }">${ i }</option>
							</c:if>
						</c:forEach>
					</select>		
				</td>
			</tr>
			<tr>
				<td> &nbsp;&nbsp;전화번호</td>
				<td><input type="text" name="phone" value="${ loginUser.phone }"></td>
			</tr>
			<tr>
				<td> &nbsp;&nbsp;주소</td>
				<td><input type="text" name="address" value="${ loginUser.address }"></td>
			</tr>
			<tr>
				<td> &nbsp;&nbsp;성별</td>
				<td>
					<input type="radio" name="gender" value="M" <c:if test="${ loginUser.gender == 'M'}">checked</c:if>>남자 &nbsp;&nbsp;
					<input type="radio" name="gender" value="F" <c:if test="${ loginUser.gender == 'F'}">checked</c:if>>여자
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						<br>
						<input type="submit" value="수정">
						<button type="reset">취소</button>
					</div>
				</td>
			</tr>
		</table>
	</form>
<%--  	<script>
		// 이 방식 말고 jstl로 생년월일 간단하게 선택되게 할 수 있음
		var birthDay = '<%= loginUser.getBirthDay() %>';
		
		var bSplit = birthDay.split('-');
		var year = bSplit[0];
		var month = Number(bSplit[1]); // Number로 형변환해줘야 09인 것과 9 비교 가능
		var date = Number(bSplit[2]);
		
 		//if(month.charAt(0) == '0') {
		//	month = month.charAt(1);
		//}
		
		var yearOp = document.getElementsByClassName('yearOp');
		for(var i in yearOp) {
			if (yearOp[i].value == year) {
				yearOp[i].selected = "selected";
			}
		}
		
		var monthOp = document.getElementsByClassName('monthOp');
		for(var i in monthOp) {
			if (monthOp[i].value == month) {
				monthOp[i].selected = "selected";
			}
		}
		
		var dateOp = document.getElementsByClassName('dateOp');
		for(var i in dateOp) {
			if (dateOp[i].value == date) {
				dateOp[i].selected = "selected";
			}
		}		
	</script> --%>
</body>
</html>