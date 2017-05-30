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
<% session.setAttribute( "menuActive", "arrayViews" ); %>
<jsp:include page="${request.contextPath}/header" />
<jsp:include page="${request.contextPath}/menu" />
<div class="app-container">
  <div class="row">
    <div class="col-md-12">
      <div class="card">
        <div class="card-header">Dodawanie nowego widoku tabeli</div>
        <div class="card-body">
            <form:form method="POST" modelAttribute="arrayView" action="${pageContext.request.contextPath}/updateArrayView">
            <input id="productionSlotID" name="arrayViewID" type="hidden" class="form-control" value="0" />
            <div class="row">
              <div class="col-md-6">
                <div class="form-group">
                  <label class="col-md-3 control-label">Nazwa</label>
                  <div class="col-md-6">
                    <div class="input-group">
                      <span class="input-group-addon">#</span>
                      <input id="arrayViewName" name="arrayViewName" type="text" class="form-control" aria-label="Nazwa widoku" placeholder="Nazwa widoku" />
                    </div>
                  </div>
                </div>
              </div>  
              <div class="col-md-6">
                <div class="form-group">
                    <label class="col-md-3 control-label">Opis</label>
                    <div class="col-md-9">
                      <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-file-text-o" aria-hidden="true"></i></span>
                        <input id="arrayViewDescription" name="arrayViewDescription" type="text" class="form-control" aria-label="Opis widoku" placeholder="Opis widoku" />
                      </div>
                    </div>
                </div>
              </div>
              <div class="col-md-12">
                <div class="form-group">
                    <div class="col-md-25">
                    <div class="checkbox">
                        <input type="checkbox" id="arrayViewForClosed" name="arrayViewForClosed" class="form-control" <c:if test="${arrayView.arrayViewForClosed eq 'True'}">checked</c:if> >
                        <label class="col-md-7 control-label" for="arrayViewForClosed">Czy widok pozycji zamówień tylko ze statusem "Zakończone" ?</label>
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
                  <button type="submit" class="btn btn-primary">Zapisz</button>
                  <button type="button" class="btn btn-default" onClick="document.location.href='arrayViews';">Anuluj</button>
                </div>
              </div>
            </div>
          </div>
          </form:form>
        </div>
      </div>
    </div>
<jsp:include page="${request.contextPath}/scripts" />   
<jsp:include page="${request.contextPath}/footer" />
