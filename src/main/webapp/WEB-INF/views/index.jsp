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
<%@ taglib prefix="c"       uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"     uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form"    uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"     uri="http://www.springframework.org/security/tags" %>
<% session.setAttribute( "menuActive", "orderItems" ); %>
<html lang="pl">
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
<!-- Start class="app app-default" -->
<div class="app app-default">
<jsp:include page="${request.contextPath}/menu" />
<!-- Main Content -->
<form action="selectProductionSlotMultiple" method="POST" id="indexform">
    <!-- Start class="app-container" -->
    <div class="app-container">
        <div class="row">
            <div class="col-xs-12">
                <div class="card">
                    <div class="card-header">
                        <div class="card-title">Lista zleceń produkcji - wszystkie pola
                            <c:if test="${not empty message}">
                                <div class="alert alert-warning alert-dismissible" role="alert" style="width: 50%;">
                                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                    <span aria-hidden="true">&times;</span></button>
                                    <c:out value='${message}' />
                                </div>
                            </c:if>
                                <div>
                                    <a href="#" onClick="$('form').submit();">
                                        <span class="badge badge-info badge-icon" >
                                            <i class="fa fa-print" aria-hidden="true"></i>
                                            <span>Drukuj karty dla zaznaczonych</span>
                                        </span>
                                    </a>
                                    <a href="#" id="printProductionLabels" onClick='$("#indexform").attr("action", "printProductionLabels");$("#indexform").submit();' target="_blank">
                                        <span class="badge badge-info badge-icon">
                                            <i class="fa fa-barcode" aria-hidden="true"></i>
                                            <span>Drukuj etykiety dla zaznaczonych</span>
                                        </span>
                                    </a>
                                    <a href="importOrderItems">
                                        <span class="badge badge-warning badge-icon">
                                            <i class="fa fa-folder-open-o" aria-hidden="true"></i>
                                            <span>Importuj zamówienia</span>
                                        </span>
                                    </a>
                                    <br/><br/>
                                    <sec:authorize access="hasRole('ADMIN')">
                                    <a href="#" id="deleteOrderItemsAction" name="deleteOrderItemsAction" onClick='$("#indexform").attr("action", "deleteOrderItems");$("#indexform").submit();'>
                                        <span class="badge badge-danger badge-icon">
                                            <i class="fa fa-trash" aria-hidden="true"></i>
                                            <span>Usuń zamówienia</span>
                                        </span>
                                    </a>
                                    </sec:authorize>
                                    <a href="#" id="statusChangeForOrderItems" onClick='$("#indexform").attr("action", "statusChangeForOrderItems");$("#indexform").submit();'>
                                        <span class="badge badge-primary badge-icon">
                                            <i class="fa fa-random" aria-hidden="true"></i>
                                            <span>Zmień status zamówień</span>
                                        </span>
                                    </a>
                                </div>
                        </div>
                    </div>
                    <div class="wrapper1">
                        <div class="dtwrapper1"></div>
                    </div>
                    <div class="wrapper2">
                        <div class="dtwrapper2">
          <table class="datatable table-striped table-bordered table-hover table-condensed primary dt-responsive nowrap" cellspacing="0" width="100%" id="datatable1">
            <thead>
                <tr>
                    <th>Akcje</br>
                        <a class="select-all">
                                <span class="badge badge-info badge-icon">
                                    <i class="fa fa-check" aria-hidden="true"></i>
                                    <span>Wszystkie</span>
                                </span></br>
                        </a>
                        <a class="deselect-all">
                            <span class="badge badge-info badge-icon">
                                    <i class="fa fa-close" aria-hidden="true"></i>
                                    <span>Anuluj</span>
                            </span>
                        </a>
                    </th>
                    <th>Nr<div><input type="search" class="form-control input-sm" style="font-size: 10px; width: 100px;" placeholder="filter..." /></div></th>
                    <th>Termin<div><input type="search" class="form-control input-sm" style="font-size: 10px; width: 100px;" placeholder="filter..." /></div></th>
                    <th>Wyrób<div><input type="search" class="form-control input-sm" style="font-size: 10px; width: 100px;" placeholder="filter..." /></div></th>
                    <th>Data zmiany statusu<div><input type="search" class="form-control input-sm" style="font-size: 10px; width: 100px;" placeholder="filter..." /></div></th>
                    <th>Status<div><input type="search" class="form-control input-sm" style="font-size: 10px; width: 100px;" placeholder="filter..." /></div></th>

                    <c:forEach var="f4Table" items="${fieldsForTable}">
                    <th>(<c:out value='${f4Table.fieldOriginID}' />) <c:out value='${f4Table.fieldLabel}' />
                        <div><input type="search" class="form-control input-sm" style="font-size: 10px; width: 100px;" placeholder="filter..." /></div></th>
                    </c:forEach>

                </tr>
            </thead>
            <tbody>
            <c:forEach var="oItem" items="${orderItemList}" varStatus="status">
                        <tr>
                            <td>

                              <a href="selectProductionSlot-<c:out value='${oItem.orderItemID}' />" title='Drukuj kartę zlecenia'>
                                <span class="badge badge-primary badge-icon">
                                    <i class="fa fa-print" aria-hidden="true"></i>
                                </span>
                              </a>

                              <a href="printProductionLabel-<c:out value='${oItem.orderItemID}' />" title='Drukuj etykietę zlecenia'>
                                <span class="badge badge-primary badge-icon">
                                    <i class="fa fa-barcode" aria-hidden="true"></i>
                                </span>
                              </a>
                              <a href="viewOrderItem-<c:out value='${oItem.orderItemID}' />" title='Podgląd zamówienia'>
                                <span class="badge badge-info badge-icon">
                                    <i class="fa fa-search" aria-hidden="true"></i>
                                </span>
                              </a>
                              <a href="editOrderItem-<c:out value='${oItem.orderItemID}' />" title='Edytuj zamówienie'>
                                <span class="badge badge-success badge-icon">
                                    <i class="fa fa-edit" aria-hidden="true"></i>
                                </span>
                              </a>
                              <sec:authorize access="hasRole('ADMIN')">
                              <a href="deleteOrderItem-<c:out value='${oItem.orderItemID}' />" onclick="return confirm('Czy na pewno skasować ?')" title='Usuń zlzcenie'>
                                <span class="badge badge-danger badge-icon">
                                    <i class="fa fa-trash" aria-hidden="true"></i>
                                </span>
                              </a>
                              </sec:authorize>

                            </td>
                            <td>
                                <input type="checkbox" id="orderItemID" name="orderItemID" value="<c:out value='${oItem.orderItemID}' />">&nbsp;&nbsp;
                                        <c:out value='${oItem.orderNumber}' />
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
                                </span>

                            </td>

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
</form>
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
<!-- End class="app-container" -->
</div>
<!-- End class="app app-default" -->
</div>
<script type="text/javascript" src="resources/js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="resources/js/bootstrap.js"></script>
<script type="text/javascript" src="resources/js/jquery.dataTables.js"></script>
<script type="text/javascript" src="resources/js/dataTables.bootstrap.js"></script>
<script type="text/javascript" src="resources/js/dataTables.buttons.js"></script>
<script type="text/javascript" src="resources/js/buttons.bootstrap.js"></script>
<script type="text/javascript" src="resources/js/buttons.colVis.js"></script>
<script type="text/javascript" src="resources/js/buttons.flash.min.js"></script>
<script type="text/javascript" src="resources/js/buttons.html5.min.js"></script>
<script type="text/javascript" src="resources/js/jszip.min.js"></script>
<script type="text/javascript" src="resources/js/buttons.print.js"></script>
<script src="resources/assets/js/index.config.js" type="text/javascript"></script>
<script src="resources/assets/js/datatable-scrollx.js" type="text/javascript"></script>
</body>
</html>
