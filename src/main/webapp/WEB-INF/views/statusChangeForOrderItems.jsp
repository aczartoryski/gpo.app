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
<% session.setAttribute( "menuActive", "orderItems" ); %>
<jsp:include page="${request.contextPath}/header" />
<jsp:include page="${request.contextPath}/menu" />
<!-- Main Content -->
<form action="saveStatusChangeForOrderItems" method="POST" >
<div class="app-container">
  <div class="row"> 
    <div class="col-xs-12">
      <div class="card">
        <div class="card-header">
            <div class="card-title">Zmień status dla wybranych zamówień</div>
        </div>
        <div class="card-header">
            <div class="card-title">
                <div class="form-group">
                  <label class="col-md-3 control-label">Ustaw status: </label>
                  <div class="col-md-9">
                    <div class="input-group">
                      <span class="input-group-addon"><i class="fa fa-gears" aria-hidden="true"></i></span>
                        <select class="select2" id="orderStatus" name="orderStatus">
                        <c:forEach var="orderStatus" items="${orderStatuses}">
                                <option value="<c:out value='${orderStatus.orderStatusID}' />"><c:out value='${orderStatus.orderStatusName}' /></option>
                        </c:forEach>
                        </select> 
                    </div>
                  </div>
                </div>
                <button type="submit" class="btn btn-primary">Zapisz</button>
                  <button type="button" class="btn btn-default" onClick="document.location.href='index';">Anuluj</button>
            </div>
        </div>
        <div class="card-body no-paddings table-responsive">
          <table class="table table-striped table-bordered table-hover table-condensed primary" cellspacing="0">
            <thead>
                <tr>
                    <th>Numer</th>
                    <th>Termin</th>
                    <th>Wyrób</th>
                </tr>
            </thead>
            <tbody>
                
                <c:forEach var="orderItem" items="${orderItemList}">
                <input type="hidden" id="orderItemID" name="orderItemID" value="<c:out value='${orderItem.orderItemID}' />" >
                <tr>
                    <td><c:out value='${orderItem.orderNumber}' /></td>
                    <td><c:out value='${orderItem.orderItemDueDate}' /></td>
                    <td><c:out value='${orderItem.orderItemName}' /></td>
                </tr>
                </c:forEach>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
    
</form>
<jsp:include page="${request.contextPath}/scripts" />   
<jsp:include page="${request.contextPath}/footer" />
