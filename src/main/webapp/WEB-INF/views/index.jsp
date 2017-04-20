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
                        <a class="select-all">
                            <span class="badge badge-info badge-icon">
                                <i class="fa fa-check" aria-hidden="true"></i>
                                <span>Zaznacz wszystkie</span>
                            </span>
                        </a>
                        <a class="deselect-all">
                            <span class="badge badge-info badge-icon">
                                <i class="fa fa-close" aria-hidden="true"></i>
                                <span>Anuluj zaznaczenie</span>
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
                                <span>Status</span>
                            </span>
                        </a>
                        <a class="toggle-vis" data-column="5">
                            <span class="badge badge-success badge-icon">
                                <i class="fa fa-plus-square" aria-hidden="true"></i>
                                <span>Data zmiany statusu</span>
                            </span>
                        </a>
                        <a class="toggle-vis" data-column="6">
                            <span class="badge badge-success badge-icon">
                                <i class="fa fa-plus-square" aria-hidden="true"></i>
                                <span>Wielkość skrzynki</span>
                            </span>
                        </a>
                        <a class="toggle-vis" data-column="7">
                            <span class="badge badge-success badge-icon">
                                <i class="fa fa-plus-square" aria-hidden="true"></i>
                                <span>Kolor lameli</span>
                            </span>
                        </a>
                        <a class="toggle-vis" data-column="8">
                            <span class="badge badge-success badge-icon">
                                <i class="fa fa-plus-square" aria-hidden="true"></i>
                                <span>Cięcie profili</span>
                            </span>
                        </a>
                        <a class="toggle-vis" data-column="9">
                            <span class="badge badge-success badge-icon">
                                <i class="fa fa-plus-square" aria-hidden="true"></i>
                                <span>Kolor skrzynki</span>
                            </span>
                        </a>
                        <a class="toggle-vis" data-column="10">
                            <span class="badge badge-success badge-icon">
                                <i class="fa fa-plus-square" aria-hidden="true"></i>
                                <span>Kolor prowadnic</span>
                            </span>
                        </a>
                        <a class="toggle-vis" data-column="11">
                            <span class="badge badge-success badge-icon">
                                <i class="fa fa-plus-square" aria-hidden="true"></i>
                                <span>Cięcie prowadnic</span>
                            </span>
                        </a>
                    </div>
          <table class="datatable table-striped table-bordered table-hover table-condensed primary dt-responsive nowrap" cellspacing="0" width="100%" id="datatable1">
            <thead>
                <tr>
                    <th>Akcje</th>
                    <th>Nr<div><input type="search" class="form-control input-sm" style="font-size: 10px; width: 100px;" placeholder="filter..." /></div></th>
                    <th>Termin<div><input type="search" class="form-control input-sm" style="font-size: 10px; width: 100px;" placeholder="filter..." /></div></th>
                    <th>Wyrób<div><input type="search" class="form-control input-sm" style="font-size: 10px; width: 100px;" placeholder="filter..." /></div></th>
                    <th>Data zmiany statusu<div><input type="search" class="form-control input-sm" style="font-size: 10px; width: 100px;" placeholder="filter..." /></div></th>
                    <th>Status<div><input type="search" class="form-control input-sm" style="font-size: 10px; width: 100px;" placeholder="filter..." /></div></th>
                    <th>Wielkość skrzynki<div><input type="search" class="form-control input-sm" style="font-size: 10px; width: 100px;" placeholder="filter..." /></div></th>
                    <th>Kolor lameli<div><input type="search" class="form-control input-sm" style="font-size: 10px; width: 100px;" placeholder="filter..." /></div></th>
                    <th>Cięcie profili<div><input type="search" class="form-control input-sm" style="font-size: 10px; width: 100px;" placeholder="filter..." /></div></th>
                    <th>Kolor skrzynki<div><input type="search" class="form-control input-sm" style="font-size: 10px; width: 100px;" placeholder="filter..." /></div></th>
                    <th>Kolor prowadnic<div><input type="search" class="form-control input-sm" style="font-size: 10px; width: 100px;" placeholder="filter..." /></div></th>
                    <th>Cięcie prowadnic<div><input type="search" class="form-control input-sm" style="font-size: 10px; width: 100px;" placeholder="filter..." /></div></th>
                </tr>
            </thead>
    <tbody>
        <tr>
            <td>
              <a>
                <span class="badge badge-primary badge-icon">
                    <i class="fa fa-file-pdf-o" aria-hidden="true"></i>
                </span>
              </a>
              <a>
                <span class="badge badge-primary badge-icon">
                    <i class="fa fa-print" aria-hidden="true"></i>
                </span>
              </a>
              <a href="pozycjaPodejrzyj.html">
                <span class="badge badge-info badge-icon">
                    <i class="fa fa-search" aria-hidden="true"></i>
                </span>
              </a>
              <a href="pozycjaEdytuj.html">
                <span class="badge badge-success badge-icon">
                    <i class="fa fa-edit" aria-hidden="true"></i>
                </span>
              </a>
              <a>
                <span class="badge badge-warning badge-icon">
                    <i class="fa fa-trash" aria-hidden="true"></i>
                </span>
              </a>
            </td>
            <td class="">
                <div class="checkbox" >
                    <input type="checkbox" id="checkbox1">&nbsp;&nbsp;
                    <label for="checkbox1">
                        45950#1#3
                    </label>
                </div>
            </td>
            <td>2017/04/20</td>
            <td>Brama garażowa Profil 52</td>
            <td></td>
            <td><span class="label label-info">Nowe</span></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td style="width: 105px">
              <a>
                <span class="badge badge-primary badge-icon">
                    <i class="fa fa-file-pdf-o" aria-hidden="true"></i>
                </span>
              </a>
              <a>
                <span class="badge badge-primary badge-icon">
                    <i class="fa fa-print" aria-hidden="true"></i>
                </span>
              </a>
              <a href="pozycjaPodejrzyj.html">
                <span class="badge badge-info badge-icon">
                    <i class="fa fa-search" aria-hidden="true"></i>
                </span>
              </a>
              <a href="pozycjaEdytuj.html">
                <span class="badge badge-success badge-icon">
                    <i class="fa fa-edit" aria-hidden="true"></i>
                </span>
              </a>
              <a>
                <span class="badge badge-warning badge-icon">
                    <i class="fa fa-trash" aria-hidden="true"></i>
                </span>
              </a>
            </td>
            <td><div class="checkbox">
                    <input type="checkbox" id="checkbox2">&nbsp;&nbsp;
                    <label for="checkbox2">
                        45950#2#3
                    </label>
            </td>
            <td>2017/04/20</td>
            <td>Brama garażowa Profil 52</td>
            <td></td>
            <td><span class="label label-info">Nowe</span></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td style="width: 105px">
              <a>
                <span class="badge badge-primary badge-icon">
                    <i class="fa fa-file-pdf-o" aria-hidden="true"></i>
                </span>
              </a>
              <a>
                <span class="badge badge-primary badge-icon">
                    <i class="fa fa-print" aria-hidden="true"></i>
                </span>
              </a>
              <a href="pozycjaPodejrzyj.html">
                <span class="badge badge-info badge-icon">
                    <i class="fa fa-search" aria-hidden="true"></i>
                </span>
              </a>
              <a href="pozycjaEdytuj.html">
                <span class="badge badge-success badge-icon">
                    <i class="fa fa-edit" aria-hidden="true"></i>
                </span>
              </a>
              <a>
                <span class="badge badge-warning badge-icon">
                    <i class="fa fa-trash" aria-hidden="true"></i>
                </span>
              </a>
            </td>
            <td>
                <div class="checkbox">
                    <input type="checkbox" id="checkbox3">&nbsp;&nbsp;
                    <label for="checkbox3">
                        45950#3#3
                    </label>
            </td>
            <td>2017/04/20</td>
            <td>Brama garażowa Profil 52</td>
            <td></td>
            <td><span class="label label-info">Nowe</span></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td style="width: 105px">
              <a>
                <span class="badge badge-primary badge-icon">
                    <i class="fa fa-file-pdf-o" aria-hidden="true"></i>
                </span>
              </a>
              <a>
                <span class="badge badge-primary badge-icon">
                    <i class="fa fa-print" aria-hidden="true"></i>
                </span>
              </a>
              <a href="pozycjaPodejrzyj.html">
                <span class="badge badge-info badge-icon">
                    <i class="fa fa-search" aria-hidden="true"></i>
                </span>
              </a>
              <a href="pozycjaEdytuj.html">
                <span class="badge badge-success badge-icon">
                    <i class="fa fa-edit" aria-hidden="true"></i>
                </span>
              </a>
              <a>
                <span class="badge badge-warning badge-icon">
                    <i class="fa fa-trash" aria-hidden="true"></i>
                </span>
              </a>
            </td>
            <td>
                <div class="checkbox">
                    <input type="checkbox" id="checkbox4">&nbsp;&nbsp;
                    <label for="checkbox4">
                        45956#1#3
                    </label>
            </td>
            <td>2017/04/12</td>
            <td>Brama garażowa Profil 51</td>
            <td>2017/04/01</td>
            <td><span class="label label-danger">Anulowane</span></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td style="width: 105px">
              <a>
                <span class="badge badge-primary badge-icon">
                    <i class="fa fa-file-pdf-o" aria-hidden="true"></i>
                </span>
              </a>
              <a>
                <span class="badge badge-primary badge-icon">
                    <i class="fa fa-print" aria-hidden="true"></i>
                </span>
              </a>
              <a href="pozycjaPodejrzyj.html">
                <span class="badge badge-info badge-icon">
                    <i class="fa fa-search" aria-hidden="true"></i>
                </span>
              </a>
              <a href="pozycjaEdytuj.html">
                <span class="badge badge-success badge-icon">
                    <i class="fa fa-edit" aria-hidden="true"></i>
                </span>
              </a>
              <a>
                <span class="badge badge-warning badge-icon">
                    <i class="fa fa-trash" aria-hidden="true"></i>
                </span>
              </a>
            </td>
            <td>
                <div class="checkbox">
                    <input type="checkbox" id="checkbox5">&nbsp;&nbsp;
                    <label for="checkbox5">
                        45956#2#3
                    </label>
            </td>
            <td>2017/04/12</td>
            <td>Brama garażowa Profil 51</td>
            <td>2017/04/05</td>
            <td><span class="label label-warning">W toku</span></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>

        </tr>
        <tr>
            <td style="width: 105px">
              <a>
                <span class="badge badge-primary badge-icon">
                    <i class="fa fa-file-pdf-o" aria-hidden="true"></i>
                </span>
              </a>
              <a>
                <span class="badge badge-primary badge-icon">
                    <i class="fa fa-print" aria-hidden="true"></i>
                </span>
              </a>
              <a href="pozycjaPodejrzyj.html">
                <span class="badge badge-info badge-icon">
                    <i class="fa fa-search" aria-hidden="true"></i>
                </span>
              </a>
              <a href="pozycjaEdytuj.html">
                <span class="badge badge-success badge-icon">
                    <i class="fa fa-edit" aria-hidden="true"></i>
                </span>
              </a>
              <a>
                <span class="badge badge-warning badge-icon">
                    <i class="fa fa-trash" aria-hidden="true"></i>
                </span>
              </a>
            </td>
            <td>
                <div class="checkbox">
                    <input type="checkbox" id="checkbox6">&nbsp;&nbsp;
                    <label for="checkbox6">
                        45956#3#3
                    </label>
            </td>
            <td>2017/04/12</td>
            <td>Brama garażowa Profil 51</td>
            <td>2017/04/04</td>
            <td><span class="label label-primary">Przywrócone</span></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td style="width: 105px">
              <a>
                <span class="badge badge-primary badge-icon">
                    <i class="fa fa-file-pdf-o" aria-hidden="true"></i>
                </span>
              </a>
              <a>
                <span class="badge badge-primary badge-icon">
                    <i class="fa fa-print" aria-hidden="true"></i>
                </span>
              </a>
              <a href="pozycjaPodejrzyj.html">
                <span class="badge badge-info badge-icon">
                    <i class="fa fa-search" aria-hidden="true"></i>
                </span>
              </a>
              <a href="pozycjaEdytuj.html">
                <span class="badge badge-success badge-icon">
                    <i class="fa fa-edit" aria-hidden="true"></i>
                </span>
              </a>
              <a>
                <span class="badge badge-warning badge-icon">
                    <i class="fa fa-trash" aria-hidden="true"></i>
                </span>
              </a>
            </td>
            <td>
                <div class="checkbox">
                    <input type="checkbox" id="checkbox7">&nbsp;&nbsp;
                    <label for="checkbox7">
                        45953#1#3
                    </label>
            </td>
            <td>2017/04/20</td>
            <td>Brama garażowa Profil 52</td>
            <td>2017/04/18</td>
            <td><span class="label label-success">Zakończone</span></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
    </tbody>
</table>
        </div>
      </div>
    </div>
  </div>
</div>            
<jsp:include page="${request.contextPath}/footer" />
