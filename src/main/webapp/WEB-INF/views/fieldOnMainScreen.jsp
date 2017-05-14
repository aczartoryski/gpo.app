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
<% session.setAttribute( "menuActive", "maintable" ); %>
<jsp:include page="${request.contextPath}/header">
  <jsp:param name="pageTitle" value="Lista pól widocznych na głównej stronie"/>
</jsp:include>
<jsp:include page="${request.contextPath}/menu">
  <jsp:param name="menuActive" value="maintable"/>  
</jsp:include>
<!-- Main Content -->
<div class="app-container">
  <div class="row"> 
    <div class="col-xs-12">
      <div class="card">
        <div class="card-header">
          <div class="card-title">Lista pól widocznych na głównej stronie
          <form:form action="fieldOnMainScreen" method="POST" modelAttribute="fieldOnMainScreenForm">
   
          <div class="col-md-9">
                        <button type="submit" class="btn btn-primary">Zapisz</button>
          </div>    
          <c:if test="${not empty message}">
                    <div class="alert alert-warning alert-dismissible" role="alert" style="width: 50%;">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                    <c:out value='${message}' />
                    </div>
              </c:if>  
          </div>
        </div>
        <div class="card-body no-paddings table-responsive">
          <table class="datatable table table-striped table-bordered table-hover table-condensed primary" cellspacing="0" width="100%" id="datatable1">
    <thead>
        <tr>
            <th></th>
            <th>ID</th>
            <th>VALUEID</th>
            <th>Nazwa pola</th>
            <th>Widoczne ?</th>
        </tr>
    </thead>
    <tbody>
            
        <c:forEach var="field" items="${fieldOnMainScreenForm.fieldList}" varStatus="status">
        
        <tr>
            <td style="width: 2px;">${status.count}</td>
            <td><c:out value='${field.fieldOriginID}' />
            
            <form:hidden path="fieldList[${status.index}].fieldID" />
            <form:hidden path="fieldList[${status.index}].fieldOriginID" />
            </td>
            <td><c:out value='${field.fieldValueID}' />
            
            <form:hidden path="fieldList[${status.index}].fieldValueID" />
            </td>
            <td><c:out value='${field.fieldLabel}' />
                <form:hidden path="fieldList[${status.index}].fieldLabel" />
            </td>
            <td>
                <form:checkbox path="fieldList[${status.index}].fieldShownInMainTable" />
            </td>
        </tr>
        </c:forEach>
    </tbody>
</table>
        </div>
        </form:form>
      </div>
    </div>
  </div>
<jsp:include page="${request.contextPath}/scripts" />             
<jsp:include page="${request.contextPath}/footer" />
