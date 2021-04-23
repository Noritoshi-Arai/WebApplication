<%@ page language="java" contentType="text/html; UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<% 
request.setCharacterEncoding("UTF-8");
%>
<%
List<Date> nullList = (List<Date>) request.getAttribute("nullList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="Login.css">
<title>トップページ</title>
</head>
<body>
<h2>${staff.nameKanji}のページ</h2>
<div align="right"><a href="LogoutServlet">ログアウト</a></div><br>
<div id="wrapper">
<div class="left-column">
メニュー<br>
・<a href="LoginServlet">トップページ</a><br>
・<a href="WorkScheduleServlet">出勤予定入力</a><br>
・<a href="ScheduleSearch.jsp">出勤予定検索</a><br>
・<a href="WorkResultInputServlet">勤務実績入力</a><br>
・<a href="RestServlet">休暇取得状況</a><br>
</div>
<div class="right-column">
<h3>メッセージ</h3>
<div style="padding: 10px; margin-bottom: 10px; border: 1px solid #333333;">
${restMsg} <br>
${nullMsg} <br>
<% 
if(nullList != null){
for(Date date : nullList){
	SimpleDateFormat msdf = new SimpleDateFormat("MM");
	SimpleDateFormat dsdf = new SimpleDateFormat("dd");
	String month = msdf.format(date);
	String day = dsdf.format(date);
%>
<%= month%>月<%= day%>日の勤務実績が未反映です。<br>
<% 
}
}
%>
</div>
</div>
</div>
</body>
</html>