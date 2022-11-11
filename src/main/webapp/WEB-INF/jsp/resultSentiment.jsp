<%@page import="java.util.Optional"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
	Optional<String> message = 
		Optional.ofNullable((String) request.getAttribute("message"));
	Optional<String> message2 = 
		Optional.ofNullable((String) request.getAttribute("message2"));
	Optional<String> message3 = 
		Optional.ofNullable((String) request.getAttribute("message3"));




%>

<body>
<H1>ConfidenceScores</H1>

<H3>positive：<%= message.orElse("ERROR") %></H3>
<H3>neutral:<%= message2.orElse("ERROR") %></H3>
<H3>negative:<%= message3.orElse("ERROR") %></H3>

<h3></h3>
</body>
</html>