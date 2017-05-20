<%-- 
    Document   : 403
    Created on : 2017-05-17, 18:35:33
    Author     : Artur Czartoryski <artur at czartoryski.wroclaw.pl>
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Access Denied - ProgrammingFree</title>
</head>
<body>
<h1>You do not have permission to access this page!
</h1>
<form action="/logout" method="post">
          <input type="submit" value="Sign in as different user" /> 
          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>   
</body>
</html>
