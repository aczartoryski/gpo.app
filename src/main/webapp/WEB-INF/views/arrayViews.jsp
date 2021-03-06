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
<% session.setAttribute( "menuActive", "arrayViews" ); %>
<jsp:include page="${request.contextPath}/header" />
<jsp:include page="${request.contextPath}/menu" />
<!-- Main Content -->
<div class="app-container">
  <div class="row"> 
    <div class="col-xs-12">
      <div class="card">
        <div class="card-header">
          <div class="card-title">Lista widoków tabel
            <div>
              <a href="newArrayView">
                <span class="badge badge-info badge-icon">
                    <i class="fa fa-plus" aria-hidden="true"></i>
                    <span>Nowy widok</span>
                </span>
              </a>
              <c:if test="${not empty message}">
                    <div class="alert alert-warning alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                    <c:out value='${message}' />
                    </div>
              </c:if> 
            </div>
          </div>
          
        </div>
        <div class="card-body no-paddings table-responsive">
          <table class="datatable table table-striped table-bordered table-hover table-condensed primary" cellspacing="0" width="100%" id="datatable1">
            <thead>
                <tr>
                    <th>Akcje</th>
                    <th>Nazwa</th>
                    <th>Opis</th>
                    <th>Widok dla statusu "Zakończone"?</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="arrayView" items="${arrayViewList}">
                <tr>
                    <td style="width: 20%">
                      <a href="editArrayView-<c:out value='${arrayView.arrayViewID}' />"> 
                        <span class="badge badge-info badge-icon">
                            <i class="fa fa-edit" aria-hidden="true"></i>
                            <span>Edytuj</span>
                        </span>
                      </a>
                      <a href="deleteArrayView-<c:out value='${arrayView.arrayViewID}' />" 
                         onclick="return confirm('Czy na pewnoc hcesz skasować ?')">
                        <span class="badge badge-danger badge-icon">
                            <i class="fa fa-trash" aria-hidden="true"></i>
                            <span>Usuń</span>
                        </span>
                      </a>
                         </br></br>
                      <a href="editFieldMappingToView-<c:out value='${arrayView.arrayViewID}' />">
                        <span class="badge badge-info badge-icon">
                            <i class="fa fa-gears" aria-hidden="true"></i>
                            <span>Edytuj mapowanie pól</span>
                        </span>
                      </a>
                    </td>
                   <td><c:out value='${arrayView.arrayViewName}' /></td>
                    <td><c:out value='${arrayView.arrayViewDescription}' /></td>
                    <td>
                        <c:if test="${arrayView.arrayViewForClosed eq 'True'}">Tak</c:if>
                        <c:if test="${arrayView.arrayViewForClosed != 'True'}">Nie</c:if>
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
