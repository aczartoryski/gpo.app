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
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% request.setCharacterEncoding("UTF-8"); %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page import="com.app.gpo.model.ProductionSlot"%>
<%@page import="com.app.gpo.model.OrderItem"%>

<!DOCTYPE html>
<%
    // OrderItem orderItem = (OrderItem) model.get("orderItem");
    // ProductionSlot productionSlot = (ProductionSlot) model.get("productionSlot");
    //String orderTitle = " Zamówienie numer: "+orderItem.getorderNumber();
    //String productionSlotTitle = " Gniazdo produkcyjne: ("+productionSlot.getProductionSlotNumber()+") "+productionSlot.getProductionSlotDescription();
%>
<html>
    <head>
    <head>
        <title>GKZP</title>
        <meta name="description" content="Generator Kart Zleceń Produkcji">
        <meta name="author" content="Artur Czartoryski"> 
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta charset="utf-8"  />
        <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
        <link rel="stylesheet" type="text/css" href="resources/assets/css/vendor.css">
        <link rel="stylesheet" type="text/css" href="resources/assets/css/printout.css">
    </head>
</head>
<body>
    <div class="page">
        <div style="text-align: right; ">Karta produkcji wygenerowana:  <c:out value='${SimpleDateFormat("yyyy-MM-dd HH:mm").format(Date())}' /></div>
        <hr>
        <div><span class="list-group-item list-group-item-info">Zamówienie numer: <c:out value='${orderItem.orderNumber}' /> | Termin: <c:out value='${orderItem.orderItemDueDate}' /> |
            Gniazdo produkcyjne: (<c:out value='${productionSlot.productionSlotNumber}' />) <c:out value='${productionSlot.productionSlotDescription}' /></span></div>
            <c:choose>
                <c:when test="${productionSlot.productionSlotNumber eq 3}">
        <div>
            <table class="table-bordered table-condensed" cellspacing="1" width="100%">

                <tbody>
                    <tr>
                        <th>Wyrób</th>
                        <td colspan="9"><c:out value='${orderItem.orderItemName}' /></td>
                        <c:forEach var="fieldMapping" items="${productionSlot.fieldMappings}" varStatus="theCount">
                        <c:if test="${theCount.index % 5 == 0}"> </tr> </c:if>
                        <th><c:out value='${fieldMapping.field.fieldLabel}' /></th>
                        <td>
                        <c:forEach var="orderItemfield" items="${orderItem.orderItemFields}">
                            <c:if test="${fieldMapping.field.fieldID eq orderItemfield.field.fieldID}">
                                <c:out value='${orderItemfield.fieldText}' />
                            </c:if>    
                        </c:forEach>
                        </td>
                        
                        </c:forEach>
                    </tr>
                </tbody>
            </table>
        </div>
            </c:when>
            <c:otherwise>
            <div>
            <table class="table-bordered table-condensed" cellspacing="1" width="100%">
                <thead>
                    <tr>
                        <th>Wyrób</th>
                        <c:forEach var="fieldMapping" items="${productionSlot.fieldMappings}">
                        <th><c:out value='${fieldMapping.field.fieldLabel}' /></th>
                        </c:forEach>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td><c:out value='${orderItem.orderItemName}' /></td>
                        <c:forEach var="fieldMapping" items="${productionSlot.fieldMappings}">
                        <td>
                        <c:forEach var="orderItemfield" items="${orderItem.orderItemFields}">
                            <c:if test="${fieldMapping.field.fieldID eq orderItemfield.field.fieldID}">
                                <c:out value='${orderItemfield.fieldText}' />
                            </c:if>    
                        </c:forEach>
                        </td>
                        </c:forEach>
                    </tr>
                </tbody>
            </table>
        </div>       
            </c:otherwise>
            </c:choose>             
    </div>
</body>
</html>
