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
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<% session.setAttribute( "menuActive", "fields" ); %>
<jsp:include page="${request.contextPath}/header" />
<jsp:include page="${request.contextPath}/menu" />
<div class="app-container">

  <div class="row"> 
    <div class="col-xs-12">
      <div class="card">
        <div class="card-header">
          <div class="card-title">Edycja mapowania pól karty produkcyjnej dla gniazda 
                 <c:out value='${productionSlot.productionSlotNumber}' /> 
                (<c:out value='${productionSlot.productionSlotDescription}' />)</div>
        </div>
        <div class="card-body no-paddings table-responsive">
          <table class="table table-bordered table-condensed" cellspacing="0" width="100%">
            <thead> 
                <tr>
                    <th>Dostępne pola</th>
                    <th>Układ na karcie zlecenia</th>
                </tr>
            </thead>
              <tbody>
                  <tr>
                      <td width="50%">
                        <ul id="sortable1" class="connectedSortable">

                            <c:forEach var="f" items="${fieldList}">

                                    <li class="ui-state-default">
                                    (<c:out value='${f.fieldOriginID}' />) 
                                     <c:out value='${f.fieldLabel}' />
                                    </li>

                              </c:forEach>

                        </ul>
                      </td>
                      <td width="50%">
                        <ul id="notsortable" >
                          
                          <li class="ui-state-highlight ui-state-disabled">Nr zamówienia</li>
                          <li class="ui-state-highlight ui-state-disabled">Wyrób</li>
                        </ul>
                        <ul id="sortable2" class="connectedSortable">
                            <c:forEach var="fM" items="${fieldMappingList}">
                                <li class="ui-state-default">
                                 (<c:out value='${fM.field.fieldOriginID}' />) 
                                  <c:out value='${fM.field.fieldLabel}' />
                                </li>
                            </c:forEach>
                        </ul>
                      </td>
                  </tr>
              </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
  <div class="row"> 
            <div class="form-footer">
              <div class="form-group">
                <div class="col-md-9 col-md-offset-1">
                  <button type="submit" class="btn btn-primary">Zapisz</button>
                  <div id="seializeTable"></div>
                  <button type="button" class="btn btn-default">Anuluj</button>
                  <button type="button" class="btn btn-danger">Usuń</button>
                </div>
              </div>
            </div>
          </div>
</div>

  </div>
<script type="text/javascript" src="resources/assets/js/vendor.js"></script>
<script type="text/javascript" src="resources/assets/js/app.js"></script>
<script type="text/javascript" src="resources/assets/js/jquery-ui.js"></script>
<script type="text/javascript" src="resources/assets/js/sortable.config.js"></script>
</body>
</html>
