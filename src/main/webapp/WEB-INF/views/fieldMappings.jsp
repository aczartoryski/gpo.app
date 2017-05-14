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
<% session.setAttribute( "menuActive", "fields" ); %>
<jsp:include page="${request.contextPath}/header">
  <jsp:param name="pageTitle" value="Lista mapowania pól na karty zleceń"/>
</jsp:include>
<jsp:include page="${request.contextPath}/menu">
  <jsp:param name="menuActive" value="zamowienia"/>  
</jsp:include>
<!-- Main Content -->
<div class="app-container">
  <div class="row"> 
    <div class="col-xs-12">
      <div class="card">
        <div class="card-header">
          <div class="card-title">Lista mapowania pól na karty zleceń
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
            <th>Akcje</th>
            <th>Mapowanie dla gniazda</th>
            <th>Nazwa pola  (ID / VALUEID)</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="pS" items="${productionSlotList}">
        <tr>
            <td style="width: 20%">
                <a href="editFieldMapping-<c:out value='${pS.productionSlotID}' />"> 
                <span class="badge badge-info badge-icon">
                    <i class="fa fa-edit" aria-hidden="true"></i>
                    <span>Edytuj</span>
                </span>
              </a>
              <a href="deleteFieldMapping-<c:out value='${pS.productionSlotID}' />" onclick="return confirm('Czy na pewnoc hcesz skasować ?')">
                <span class="badge badge-danger badge-icon">
                    <i class="fa fa-trash" aria-hidden="true"></i>
                    <span>Czyść</span>
                </span>
              </a>
            </td>
            <td><c:out value='${pS.productionSlotNumber}' /> 
                (<c:out value='${pS.productionSlotDescription}' />)</td>
            <td>
              <ul>
                  <li style="list-style: none">(0/1) Nr zamówienia</li>
                  <li style="list-style: none">(0/2) Wyrób</li>
                  <c:forEach var="fM" items="${pS.fieldMappings}">
                      <li style="list-style: none">
                          (<c:out value='${fM.field.fieldOriginID}' /><c:if test="${not empty fM.field.fieldValueID}" >/<c:out value='${fM.field.fieldValueID}' /></c:if>) 
                          <c:out value='${fM.field.fieldLabel}' /></li>    
                  </c:forEach>  
              </ul>
            </td>
        </tr>
        </c:forEach>
    </tbody>
</table>
        </div>
      </div>
    </div>
  </div>
<jsp:include page="${request.contextPath}/scripts" />              
<jsp:include page="${request.contextPath}/footer" />
