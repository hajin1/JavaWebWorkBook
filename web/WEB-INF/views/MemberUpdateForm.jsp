<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<html>
<head>
    <title>회원 정보</title>
</head>
<body>
<h1>회원 정보</h1>

<form action="update.do" method="post">
    번호: <input type="text" name="no" value='${member.no}'> <br>
    이름: <input type="text" name="name" value='${member.name}'> <br>
    이메일: <input type="text" name="email" value='${member.email}'><br>
    가입일: '${member.createDate}'<br>
    <input type="submit" value="저장">
    <input type="button" value="삭제" onclick="location.href='delete?no=${member.no}'">
    <input type="button" value="취소" onclick="location.href='list'">
</form>

</body>
</html>