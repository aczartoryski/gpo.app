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
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<% session.setAttribute( "menuActive", "orderItems" ); %>
<jsp:include page="${request.contextPath}/header">
  <jsp:param name="pageTitle" value="Podgląd pozycji zlecenia produkcji"/>
</jsp:include>
<jsp:include page="${request.contextPath}/menu" />

<!-- Main Content -->
<div class="app-container">
  <div class="row">
    <div class="col-md-12">
      <div class="card">
        <div class="card-header">Podgląd pozycji zlecenia produkcji</div>
        <div class="card-body">
            <div class="row">
              <div class="col-md-6">
                <form:form method="POST" commandName="orderItem" action="${pageContext.request.contextPath}/updateOrderItem">
                <input type="hidden" name="orderItemID" id="orderItemID" value="${orderItem.orderItemID}" />
                <div class="form-group">
                  <label class="col-md-3 control-label">Numer</label>
                  <div class="col-md-9">
                    <div class="input-group">
                      <span class="input-group-addon">#</span>
                      <form:input path="orderNumber" class="form-control" readonly="true" aria-label="Numer zamówienia" />
                    </div>
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-md-3 control-label">Status</label>
                  <div class="col-md-9">
                    <div class="input-group">
                      <span class="input-group-addon"><i class="fa fa-gears" aria-hidden="true"></i></span>
                      <form:input path="orderStatus.orderStatusName" readonly="true" class="form-control" aria-label="Status" />
                    </div>
                  </div>
                </div>
              </div>
              <div class="col-md-6">
                <div class="form-group">
                    <label class="col-md-3 control-label">Termin</label>
                    <div class="col-md-9">
                      <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-calendar" aria-hidden="true"></i></span>
                        <form:input path="orderItemDueDate" readonly="true" class="form-control" aria-label="Termin zamówienia" />
                      </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-3 control-label">Wyrób</label>
                    <div class="col-md-9">
                      <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-server" aria-hidden="true"></i></span>
                        <form:input path="orderItemName" readonly="true" class="form-control" aria-label="Wyrób" />
                      </div>
                    </div>
                </div>
              </div>
            </div>
           </div>
           <div class="row"> 
            <div class="form-footer">
              <div class="form-group">
                <div class="col-md-9 col-md-offset-1">
                  <button type="button" class="btn btn-primary" onClick="document.location.href='editOrderItem-<c:out value="${orderItemID}" />'">Edytuj</button>
                  <button type="button" class="btn btn-default" onClick="document.location.href='index';">Anuluj</button>
                  <sec:authorize access="hasRole('ADMIN')">
                  <button type="button" class="btn btn-danger" onclick="if (confirm('Czy na pewno skasować ?')) {document.location.href='deleteOrderItem-<c:out value="${orderItemID}" />'}">Usuń</button>
                  </sec:authorize>
                </div>
              </div>
            </div>
          </div>
        </div>
        </form:form>
      </div>
    </div>
<jsp:include page="${request.contextPath}/scripts" />   
<jsp:include page="${request.contextPath}/footer" />
