<%-- 
    Created on : April 2017
    Author     : Artur Czartoryski
    By downloading/using this software you agree to the following user license terms:

    Allow:
    - Personal and Commercial Use.
    - Modification.
    The MIT License (MIT)

    Copyright (c) [2017] [Artur Czartoryski]

    Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), 
    to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, 
    and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, 
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
    DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
    OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

    This software is basing on Flat Admin Bootstrap Templates copied from https://github.com/tui2tone/flat-admin-bootstrap-templates.
    Other lib that use in this software is on their own License.
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
  <title>GKZP</title>
  <meta name="description" content="Generator Kart Zleceń Produkcji">
  <meta name="author" content="Artur Czartoryski"> 
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta charset="utf-8"  />
  <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
  <link rel="stylesheet" type="text/css" href="resources/assets/css/vendor.css">
  <link rel="stylesheet" type="text/css" href="resources/assets/css/flat-admin.css">

  <!-- Theme -->
  <link rel="stylesheet" type="text/css" href="resources/assets/css/theme/blue-sky.css">
  <link rel="stylesheet" type="text/css" href="resources/assets/css/theme/blue.css">
  <link rel="stylesheet" type="text/css" href="resources/assets/css/theme/red.css">
  <link rel="stylesheet" type="text/css" href="resources/assets/css/theme/yellow.css">
  <link rel="stylesheet" type="text/css" href="resources/assets/css/jquery-ui.css">
  <link rel="stylesheet" type="text/css" href="resources/assets/css/sortable.css">
  <link rel="stylesheet" type="text/css" href="resources/assets/css/datatable-scrollX.css">
</head>
<body>
  <div class="app app-default">

<div class="app-container app-login">
  <div class="flex-center">
    <div class="app-header"></div>
    <div class="app-body">
      <div class="loader-container text-center">
          <div class="icon">
            <div class="sk-folding-cube">
                <div class="sk-cube1 sk-cube"></div>
                <div class="sk-cube2 sk-cube"></div>
                <div class="sk-cube4 sk-cube"></div>
                <div class="sk-cube3 sk-cube"></div>
              </div>
            </div>
          <div class="title">Loguję się do systemu...</div>
      </div>
      <div class="app-block">
      <div class="app-form">
        <div class="form-header">
          <div class="app-brand"><span class="highlight">v1</span> GKZP</div>
        </div>
        <c:if test="${param.error != null}">
                                <div class="alert alert-danger">
                                    <p>Invalid username and password.</p>
                                </div>
                            </c:if>
                            <c:if test="${param.logout != null}">
                                <div class="alert alert-success">
                                    <p>You have been logged out successfully.</p>
                                </div>
                            </c:if>
        <form action="${pageContext.request.contextPath}/login" method="POST">
            <div class="input-group">
              <span class="input-group-addon" id="basic-addon1">
                <i class="fa fa-user" aria-hidden="true"></i></span>
              <input type="text" class="form-control" placeholder="Użytkownik" aria-describedby="basic-addon1" id="username" name="username">
            </div>
            <div class="input-group">
              <span class="input-group-addon" id="basic-addon2">
                <i class="fa fa-key" aria-hidden="true"></i></span>
              <input type="password" class="form-control" placeholder="Hasło" aria-describedby="basic-addon2" id="password" name="password">
            </div>
            <div class="text-center">
                <input type="submit" class="btn btn-success btn-submit" value="Zaloguj się">
            </div>
        </form>
      </div>
      </div>
    </div>
    <div class="app-footer">
    </div>
  </div>
</div>

  </div>

  <script type="text/javascript" src="resources/js/jquery-2.2.4.js"></script>
    <script type="text/javascript" src="resources/js/bootstrap.js"></script>

</body>
</html>