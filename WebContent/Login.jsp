<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン画面</title>
</head>
<body>
<h2>ログイン画面</h2>
個人IDとパスワードを入力してください<br><br>
${LoginError}
<form action="LoginServlet" method="post">
<input type="hidden" name="login" value="login">
個人ID：<input type="text" name="id" maxlength="6"><br><br>
パスワード：<input type="password" name="password"><br><br>
<input type="submit" value="ログイン">
</form>
</body>
</html>