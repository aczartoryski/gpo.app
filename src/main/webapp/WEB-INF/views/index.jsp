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

<!-- Main Content -->
<div class="app-container">
  <div class="row">
    <div class="col-xs-12">
      <div class="card">
        <div class="card-header">
          <div class="card-title">Lista zleceń produkcji
              <c:if test="${not empty message}">
                    <div class="alert alert-warning alert-dismissible" role="alert" style="width: 50%;">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                    <c:out value='${message}' />
                    </div>
              </c:if>
            <div>
                <a>
                    <span class="badge badge-info badge-icon">
                        <i class="fa fa-file-pdf-o" aria-hidden="true"></i>
                        <span>Drukuj karty dla zaznaczonych</span>
                    </span>
                </a>
                <a>
                    <span class="badge badge-info badge-icon">
                        <i class="fa fa-print" aria-hidden="true"></i>
                        <span>Drukuj etykiety dla zaznaczonych</span>
                    </span>
                </a>
                <a href="importOrderItems">
                    <span class="badge badge-warning badge-icon">
                        <i class="fa fa-folder-open-o" aria-hidden="true"></i>
                        <span>Importuj zamówienia</span>
                    </span>
                </a>
            </div>
          </div>
        </div>
        <div class="card-body no-paddings table-responsive">
            <div style="text-align: center; margin-top: 0;">
                        <a class="toggle-vis" data-column="1">
                            <span class="badge badge-success badge-icon">
                                <i class="fa fa-plus-square" aria-hidden="true"></i>
                                <span>Nr</span>
                            </span>
                        </a>
                        <a class="toggle-vis" data-column="2">
                            <span class="badge badge-success badge-icon">
                                <i class="fa fa-plus-square" aria-hidden="true"></i>
                                <span>Termin</span>
                            </span>
                        </a>
                        <a class="toggle-vis" data-column="3">
                            <span class="badge badge-success badge-icon">
                                <i class="fa fa-plus-square" aria-hidden="true"></i>
                                <span>Wyrób</span>
                            </span>
                        </a>
                        <a class="toggle-vis" data-column="4">
                            <span class="badge badge-success badge-icon">
                                <i class="fa fa-plus-square" aria-hidden="true"></i>
                                <span>Data zmiany statusu</span>
                            </span>
                        </a>
                        <a class="toggle-vis" data-column="5">
                            <span class="badge badge-success badge-icon">
                                <i class="fa fa-plus-square" aria-hidden="true"></i>
                                <span>Status</span>
                            </span>
                        </a>
                        <c:forEach var="f4Table" items="${fieldsForTable}" varStatus="loop">
                        <a class="toggle-vis" data-column="<c:out value="${6 + loop.index}" />">
                            <span class="badge badge-success badge-icon">
                                <i class="fa fa-plus-square" aria-hidden="true"></i>
                                <span><c:out value='${f4Table.fieldLabel}' /></span>
                            </span>
                        </a>
                        </c:forEach>
                    </div>
          <table class="datatable table-striped table-bordered table-hover table-condensed primary dt-responsive nowrap" cellspacing="0" width="100%" id="datatable1">
            <thead>
                <tr>
                    <th>Akcje</br>
                    <a class="select-all">
                            <span class="badge badge-info badge-icon">
                                <i class="fa fa-check" aria-hidden="true"></i>
                                <span>Zaznacz wszystkie</span>
                            </span></br>
                    </a>
                    <a class="deselect-all">
                        <span class="badge badge-info badge-icon">
                                <i class="fa fa-close" aria-hidden="true"></i>
                                <span>Anuluj zaznaczenie</span>
                        </span>
                    </a>
                    
                    </th>
                    <th>Nr<div><input type="search" class="form-control input-sm" style="font-size: 10px; width: 100px;" placeholder="filter..." /></div></th>
                    <th>Termin<div><input type="search" class="form-control input-sm" style="font-size: 10px; width: 100px;" placeholder="filter..." /></div></th>
                    <th>Wyrób<div><input type="search" class="form-control input-sm" style="font-size: 10px; width: 100px;" placeholder="filter..." /></div></th>
                    <th>Data zmiany statusu<div><input type="search" class="form-control input-sm" style="font-size: 10px; width: 100px;" placeholder="filter..." /></div></th>
                    <th>Status<div><input type="search" class="form-control input-sm" style="font-size: 10px; width: 100px;" placeholder="filter..." /></div></th>
                    <c:forEach var="f4Table" items="${fieldsForTable}">
                    <th><c:out value='${f4Table.fieldLabel}' /><div><input type="search" class="form-control input-sm" style="font-size: 10px; width: 100px;" placeholder="filter..." /></div></th>
                    </c:forEach>
                </tr>
            </thead>
    <tbody>
        <c:forEach var="oItem" items="${orderItemList}">
        <tr>
            <td>
              <a href="printOrderItem-<c:out value='${oItem.orderItemID}' />" target="_blank">
                <span class="badge badge-primary badge-icon">
                    <i class="fa fa-file-pdf-o" aria-hidden="true"></i>
                </span>
              </a>
              <a href="printProductionLabel-<c:out value='${oItem.orderItemID}' />">
                <span class="badge badge-primary badge-icon">
                    <i class="fa fa-print" aria-hidden="true"></i>
                </span>
              </a>
              <a href="viewOrderItem-<c:out value='${oItem.orderItemID}' />">
                <span class="badge badge-info badge-icon">
                    <i class="fa fa-search" aria-hidden="true"></i>
                </span>
              </a>
              <a href="editOrderItem-<c:out value='${oItem.orderItemID}' />">
                <span class="badge badge-success badge-icon">
                    <i class="fa fa-edit" aria-hidden="true"></i>
                </span>
              </a>
              <a href="deleteOrderItem-<c:out value='${oItem.orderItemID}' />" onclick="return confirm('Czy na pewno skasować ?')" >
                <span class="badge badge-danger badge-icon">
                    <i class="fa fa-trash" aria-hidden="true"></i>
                </span>
              </a>
            </td>
            <td class="">
                <div class="checkbox" >
                    <input type="checkbox" id="checkbox<c:out value='${oItem.orderItemID}' />">&nbsp;&nbsp;
                    <label for="checkbox<c:out value='${oItem.orderItemID}' />">
                        <c:out value='${oItem.orderNumber}' />
                    </label>
                </div>
            </td>
            <td><c:out value='${oItem.orderItemDueDate}' /></td>
            <td><c:out value='${oItem.orderItemName}' /></td>
            <td><c:out value='${oItem.orderStatusDate}' /></td>
            <td>
                    <c:choose>
                        <c:when test="${oItem.orderStatus.orderStatusName == 'Nowe'}">
                           <span class="label label-info">
                        </c:when>
                        <c:when test="${oItem.orderStatus.orderStatusName == 'Zakończone'}">
                            <span class="label label-success">
                        </c:when>
                        <c:when test="${oItem.orderStatus.orderStatusName == 'W toku'}">
                            <span class="label label-warning">
                        </c:when>
                        <c:when test="${oItem.orderStatus.orderStatusName == 'Przywrócone'}">
                            <span class="label label-primary">
                        </c:when>
                        <c:when test="${oItem.orderStatus.orderStatusName == 'Anulowane'}">
                            <span class="label label-danger">
                        </c:when>
                        <c:otherwise>
                            <span class="label label-info">
                        </c:otherwise>
                    </c:choose>
                    <c:out value='${oItem.orderStatus.orderStatusName}' />
                </span></td>
                
                
            <c:forEach var="f4Table" items="${fieldsForTable}">
                <td>
                <c:forEach var="orderItemfield" items="${oItem.orderItemFields}">
                    <c:if test="${f4Table.fieldID eq orderItemfield.field.fieldID}">
                        <c:out value='${orderItemfield.fieldText}' />
                    </c:if>    
                </c:forEach>
                </td>
            </c:forEach>
        </tr>
        </c:forEach>
    </tbody>
</table>
        </div>
      </div>
    </div>
  </div>
</div>            
<jsp:include page="${request.contextPath}/footer" />
