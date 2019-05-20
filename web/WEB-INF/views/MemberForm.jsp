<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<html>
<head>
    <title>회원 등록</title>
</head>
<body>

<h1>회원 등록</h1>
<form action="add.do" method="post">
    이름: <input type="text" name="name"><br>
    이메일: <input type="text" name="email"><br>
    비밀번호: <input type="password" name="password"><br>
<input type="submit" value="추가">
<input type="reset" value="취소">
</form>

</body>
</html>