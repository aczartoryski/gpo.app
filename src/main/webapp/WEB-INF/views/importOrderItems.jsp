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
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<% session.setAttribute( "menuActive", "orderItems" ); %>
<jsp:include page="${request.contextPath}/header">
  <jsp:param name="pageTitle" value="Lista zleceń produkcji"/>
</jsp:include>
<jsp:include page="${request.contextPath}/menu" />

<!-- Start Main Content -->
<div class="app-container">
  <div class="row">
    <div class="col-xs-12">
      <div class="card">
        <div class="card-header">
            <div class="card-title">Import plików zamówień</div>
        </div>
          <div class="card-body no-paddings">
          <form:form method="POST" action="importOrderItemsSuccess" modelAttribute="uploadForm" enctype="multipart/form-data">

	<div>Wybierz pliki do zaimportowania</div>
        
	<table id="fileTable">
		<tr>
			<td><input multiple name="files" type="file" class="btn btn-primary" /></td>
		</tr>
	</table>
	<br/>
        <input type="submit" class="btn btn-submit" value="Zaimportuj"/>
        
        </form:form>    
          </div>
      </div>
    </div>
  </div>

<!-- End Main Content -->
    <footer class="app-footer">
        <div class="row">
            <div class="col-xs-12">
                <div class="footer-copyright">© 2017 Copyright Artur Czartoryski</div>
            </div>
        </div>
        <div class="wrapper">
            <span class="pull-right">v1.0<a href="#">
            <i class="fa fa-long-arrow-up"></i></a></span>
        </div>
    </footer>
    </div>  
<!-- End class="app app-default" -->
</div>
<script type="text/javascript" src="resources/assets/js/vendor.js"></script>
<script type="text/javascript" src="resources/assets/js/app.js"></script>
<script type="text/javascript" src="resources/assets/js/jquery-ui.js"></script>
<script type="text/javascript" src="resources/assets/js/jquery.form.js"></script>

</body>
</html>
