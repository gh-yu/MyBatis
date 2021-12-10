<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#pwdTable{margin: auto;}
</style>
</head>
<body>
	<jsp:include page="../common/menubar.jsp"/>
	<h1 align="center">비밀번호 수정</h1>
	<form action="${ contextPath }/mPwdUpdate.me" method="post" onsubmit="return checkSub();">
		<table id="pwdTable">
			<tr>
				<td>* 현재 비밀번호</td>
				<td>
					<input type="password" id="userPwd" name="userPwd" required>
					<span id="checkPwd"></span>
				</td>
			</tr>
			<tr>
				<td>* 새 비밀번호</td>
				<td><input type="password" id="newPwd" name="newPwd" required onchange="checkPwd()"></td>
			</tr>
			<tr>
				<td>* 새 비밀번호 확인</td>
				<td>
					<input type="password" id="newPwd2" name="newPwd2" required onchange="checkPwd()">
					<span id="checkPwd2"></span>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						<br>
						<input type="submit" value="수정하기">
						<button type="reset">취소</button>
					</div>
				</td>
			</tr>
		</table>
	</form>
	<script>
		var userPwd = document.getElementById('userPwd');
		var newPwd = document.getElementById('newPwd');
		var newPwd2 = document.getElementById('newPwd2');
		var checkPwd2 = document.getElementById('checkPwd2');
		
		newPwd2.onchange = function() {
			if (newPwd.value != newPwd2.value) {
				checkPwd2.innerText = '비밀번호가 일치하지 않습니다.';
			} else {
				checkPwd2.innerText = '비밀번호가 일치합니다.';
			}
		}
		
	</script>
</body>
</html>