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
List<WorkSchedule> list = (List<WorkSchedule>) session.getAttribute("searchResult");
System.out.println("list size:" + list.size());
%>
<%-- SimpleDateFormatの定義 --%>
<% 
	SimpleDateFormat ysdf = new SimpleDateFormat("yyyy");	//年
	SimpleDateFormat msdf = new SimpleDateFormat("MM");		//月
	SimpleDateFormat dsdf = new SimpleDateFormat("dd");		//日
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="Login.css">
<title>検索結果</title>
</head>
<body>
<h2>検索結果</h2>
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
<%-- <span style="border: 1px solid #000000">日付昇順</span> <br> --%>
<table border="1">
<tr>
<th>日付</th><th>氏名</th><th>シメイ</th><th>勤務状況</th>
<% 
for(WorkSchedule ws : list){
	Date date = ws.getDate();
	String year = ysdf.format(date);
	String month = msdf.format(date);
	String day = dsdf.format(date);
	if(ws.getStatusName() != null){
%>
</tr>
<tr>
<td><%= year%>年<%= month%>月<%= day%>日</td><td><%= ws.getNameKanji()%></td><td><%= ws.getNameKana()%></td><td><%= ws.getStatusName()%></td>
</tr>
<% 
	}
}
%>
</table>
<a href="ScheduleSearch.jsp">検索画面へ</a>
</div>
</div>
</body>
</html>