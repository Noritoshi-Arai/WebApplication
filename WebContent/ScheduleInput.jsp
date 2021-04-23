<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="object.WorkSchedule" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<% 
request.setCharacterEncoding("UTF-8");
%>
<%
List<WorkSchedule> wsList = (List<WorkSchedule>) session.getAttribute("WorkSchedule");
%>
<%-- wsList1内の年を取得 --%>
<%
WorkSchedule workSchedule = wsList.get(0);
Date date = workSchedule.getDate();
SimpleDateFormat ysdf = new SimpleDateFormat("yyyy");
String year = ysdf.format(date);
%>
<%-- wsList内の月を取得 --%>
<%
SimpleDateFormat msdf = new SimpleDateFormat("MM");
String month = msdf.format(date);
%>
<%-- 月のプルダウン用メソッド --%>
<%! 
String changeInteger(int i){
	String num = "";
	if(i <= 9){
		num = "0" + Integer.toString(i);
	}else{
		num = Integer.toString(i);
	}
	return num;
}
%>
<%-- 月のプルダウン用変数 --%>
<%! 
String iToString = "";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="Login.css">
<title>出勤予定入力画面</title>
</head>
<body>
<h2>出勤予定入力</h2>
<div align="right"><a href="Logout.jsp">ログアウト</a></div><br>
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
<form action="WorkScheduleServlet" method="post">
<input type="hidden" name="page" value="change">
<select name="year">
<option value="2020"
<%
if(year.equals("2020")){
%>
selected
<%
}
%>
>2020</option>
<option value="2021"
<%
if(year.equals("2021")){
%>
selected
<%
}
%>
>2021</option>
</select>
<select name="month">
<%
for(int i = 1; i <= 12; i++){
	iToString = changeInteger(i);
%>
<option value=<%= iToString%>
<%
if(month.equals(iToString)){
%>
selected
<%
}
%>
><%= i%>月</option>
<%
}
%>
</select>
<input type="submit" value="日付変更">  &nbsp; &nbsp; &nbsp; &nbsp;
<span style="border: 1px solid #000000">氏名　|　${staff.nameKanji}　</span>
</form>
<form action="WorkScheduleServlet" method="post">
<input type="hidden" name="page" value="register">
<table border="1">
<tr>
<th>月</th><th>日</th><th>曜</th><th>勤務状況</th>
</tr>
<%
for(WorkSchedule ws : wsList){
	//日にちの格納
	date = ws.getDate();
	month = msdf.format(date);	//月
	SimpleDateFormat dsdf = new SimpleDateFormat("dd"); //日
	String day = dsdf.format(date);
	SimpleDateFormat esdf = new SimpleDateFormat("E");	//曜日
	String dotw = esdf.format(date);
%>
<tr>
<td><%= month%></td><td><%= day%></td><td><%= dotw%></td><td>

<select name="status">
<% 
if(ws.getStatusName() == null){
%>
<option value="0">勤務形態を入力してください</option>
<option value="1">前スラ</option>
<option value="2">通常</option>
<option value="3">後スラ</option>
<option value="4">在宅</option>
<option value="5">定休</option>
<% 
}else{
%>
<option value="1" 
<% 
if(ws.getStatus() == 1){
%>
selected
<% 
}
%>
>前スラ</option>
<option value="2" 
<% 
if(ws.getStatus() == 2){
%>
selected
<% 
}
%>
>通常</option>
<option value="3" 
<% 
if(ws.getStatus() == 3){
%>
selected
<% 
}
%>
>後スラ</option>
<option value="4" 
<% 
if(ws.getStatus() == 4){
%>
selected
<% 
}
%>
>在宅</option>
<option value="5" 
<% 
if(ws.getStatus() == 5){
%>
selected
<% 
}
%>
>定休</option>
<%
}
%>
</select>
</td>
</tr>
<%
}
%>
</table>
<input type="submit" value="登録">
</form>
</div>
</div>
</body>
</html>