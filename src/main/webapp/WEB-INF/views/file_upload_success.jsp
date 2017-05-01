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
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<% session.setAttribute( "menuActive", "orderItems" ); %>
<jsp:include page="${request.contextPath}/header">
  <jsp:param name="pageTitle" value="Lista zleceń produkcji"/>
</jsp:include>
<jsp:include page="${request.contextPath}/menu" />

<!-- Main Content -->
<div class="app-container">
  <div class="row">
    <div class="col-xs-12">
      <div class="card">
        <div class="card-header">
            <div class="card-title">Importowanie zamówień z wgranych plików</div>
        </div>
          <div class="card-body no-paddings table-responsive">
	<div>Lista zaimportowanych plików</div>
	<ol>
		<c:forEach items="${files}" var="file">
			<li>${file}</li>
		</c:forEach>
	</ol>
        <c:forEach items="${importedJSONArrays}" var="json">
            <div>${json}</div>
        </c:forEach>
    </div>
    </div>
    </div>
    <div class="row">
    <div class="col-xs-12">
      <div class="card">
        <div class="card-header">
            <div class="card-title">Zawartość pliku</div>
        </div>
          <div class="card-body no-paddings table-responsive">        
            <div>
                ${importedFileContent}
            </div>
          </div>
      </div>
    </div>
  </div>
</div>
</div>
<jsp:include page="${request.contextPath}/footer" />
