<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<% 
request.setCharacterEncoding("UTF-8");
%>
<%-- アプリ実行日の特定 --%>
<% 
//日付オブジェクト
Date date = new Date();

//SiimpleDateFormat
//年
SimpleDateFormat ysdf = new SimpleDateFormat();
String year = ysdf.format(date);
//月
SimpleDateFormat msdf = new SimpleDateFormat();
String month = msdf.format(date);
//日
SimpleDateFormat dsdf = new SimpleDateFormat();
String day = dsdf.format(date);
%>
<%-- 月のプルダウン用メソッド--%>
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
<title>出勤予定検索</title>
</head>
<body>
<h2>出勤予定検索</h2>
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
<form action="ScheduleSearchServlet" method="post">
氏名：<input type="text" name="nameKanji"> <br>
シメイ：<input type="text" name="nameKana"> <br>
<%-- 
日付：
<select name="startYear">
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
</select>年
<select name="startMonth">
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
><%= i%></option>
<%
}
%>
</select>
月
<select name="startDay">
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
--%> 
日付：<input type="text" name="startYear" maxlength="4">年<input type="text" name="startMonth" maxlength="2">月<input type="text" name="startDay" maxlength="2">日～ <br>
<input type="text" name="finishYear" maxlength="4">年<input type="text" name="finishMonth" maxlength="2">月<input type="text" name="finishDay" maxlength="2">日 <br>
<input type="submit" value="検索">
</form>
</div>
</div>
</body>
</html>