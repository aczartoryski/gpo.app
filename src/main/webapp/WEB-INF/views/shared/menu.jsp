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
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<aside class="app-sidebar" id="sidebar">
  <div class="sidebar-header">
    <a class="sidebar-brand" href="/"><span class="highlight">v2.1</span> GKZP</a>
    <button type="button" class="sidebar-toggle">
      <i class="fa fa-times"></i>
    </button>
    
  </div>
  <div class="sidebar-menu">
    <ul class="sidebar-nav">
      <li>
          <a>
              <div class="icon">
                <i class="fa fa-user" aria-hidden="true"></i>
              </div>
              <div class="title">Zalogowany: <c:out value="${user}"/></div>
            </a>      
      </li>
      <li class="dropdown <% if ((session.getAttribute( "menuActive" ))=="orderItems") { %> active <% } %>">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
          <div class="icon">
            <i class="fa fa-tasks" aria-hidden="true"></i>
          </div>
          <div class="title">Zlecenia produkcji</div>
        </a>
        <div class="dropdown-menu">
          <ul>
              <li class="section">
                  <i class="fa fa-bars" aria-hidden="true"></i>
                  <a href="index">Lista domyślna</a>
              </li> 
            <c:forEach var="arrayView" items="${arrayViewList}">
              <li class="section">
                  <i class="fa fa-bars" aria-hidden="true"></i>
                  <a href="listOrders-<c:out value='${arrayView.arrayViewID}' />"><c:out value='${arrayView.arrayViewName}' /></a>
              </li>  
            </c:forEach>
          </ul>
        </div>
      </li>
      <sec:authorize access="hasRole('ADMIN')">
      <li class="<% if ((session.getAttribute( "menuActive" ))=="productionSlots") { %> active <% } %>">
        <a href="productionSlots">
          <div class="icon">
            <i class="fa fa-cubes" aria-hidden="true"></i>
          </div>
          <div class="title">Lista gniazd</div>
        </a>
      </li>
      <li class="<% if ((session.getAttribute( "menuActive" ))=="arrayViews") { %> active <% } %>">
        <a href="arrayViews">
          <div class="icon">
            <i class="fa fa-desktop" aria-hidden="true"></i>
          </div>
          <div class="title">Lista widoków</div>
        </a>
      </li>
      <li class="<% if ((session.getAttribute( "menuActive" ))=="fields") { %> active <% } %>">
        <a href="fieldMappings">
          <div class="icon">
            <i class="fa fa-print" aria-hidden="true"></i>
          </div>
          <div class="title">Mapowanie pól na wydruk</div>
        </a>
      </li>
      </sec:authorize>
      <!--
      <li class="<% if ((session.getAttribute( "menuActive" ))=="maintable") { %> active <% } %>">
        <a href="fieldOnMainScreen">
          <div class="icon">
            <i class="fa fa-map-marker" aria-hidden="true"></i>
          </div>
          <div class="title">Główna tabela</div>
        </a>
      </li>
      -->
      <li>
        <a href="login?logout">
          <div class="icon">
            <i class="fa fa-power-off" aria-hidden="true"></i>
          </div>
          <div class="title">Wyloguj się</div>
        </a>
      </li>

    </ul>
  </div>
  <div class="sidebar-footer">
    
  </div>
</aside>
<script type="text/ng-template" id="sidebar-dropdown.tpl.html">
  <div class="dropdown-background">
    <div class="bg"></div>
  </div>
  <div class="dropdown-container">
    {{list}}
  </div>
</script>
