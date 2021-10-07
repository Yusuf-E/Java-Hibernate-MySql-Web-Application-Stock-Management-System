<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="entities.Admin" %>
<jsp:useBean id="util" class="utils.Util"></jsp:useBean>
<% Admin adm = util.isLogin(request, response); %>

<!doctype html>
<html lang="en">

<head>
    <title>Yönetim</title>
    <jsp:include page="inc/css.jsp"></jsp:include>
</head>

<body>
<div class="wrapper d-flex align-items-stretch">
    <jsp:include page="inc/sideBar.jsp"></jsp:include>
    <!-- Page Content  -->
    <div id="content" class="p-4 p-md-5">
        <jsp:include page="inc/topMenu.jsp"></jsp:include>
        <h3 class="mb-3">
            Satış Yap
            <small class="h6">Satış Yönetim Paneli</small>
        </h3>

        <div class="main-card mb-3 card mainCart">
            <div class="card-header cardHeader">Yeni Satış</div>

            <form class="row p-3" id="box_add_form">


                <div class="col-md-3 mb-3">
                    <label for="cname" class="form-label">Müşteriler</label>
                    <select onchange="allBox()" id="cname" class="selectpicker" data-width="100%" data-show-subtext="true" data-live-search="true">

                    </select>
                </div>

                <div class="col-md-3 mb-3">
                    <label for="pname" class="form-label">Ürünler</label>
                    <select  onchange="changeQuantity()" id="pname" class="selectpicker" data-width="100%" data-show-subtext="true" data-live-search="true">

                    </select>
                </div>


                <div class="col-md-3 mb-3">
                    <label for="count" class="form-label">Adet</label>
                    <input type="number" min="1" max="100" name="count" id="count" class="form-control" />
                </div>


                <div class="col-md-3 mb-3">
                    <label for="bNo" class="form-label">Fiş No</label>
                    <input type="text" min="1" max="100" name="bNo" id="bNo" class="form-control" />
                </div>

                <div class="btn-group col-md-3 " role="group">
                    <button  type="submit" class="btn btn-outline-primary mr-1">Ekle</button>
                </div>
            </form>
        </div>


        <div class="main-card mb-3 card mainCart">

                <div class="card-header cardHeader">
                    <div class="row">
                        <div class="col-sm-6">
                            <h5 style="color: black">Sepet Ürünleri</h5>
                        </div>
                        <div class="col-sm-6">
                            <div style="float: right">
                                <label id="total">100</label>
                                <label>TL</label>
                            </div>

                        </div>

                    </div>

                </div>





            <div class="table-responsive">
                <table class="align-middle mb-0 table table-borderless table-striped table-hover">
                    <thead>
                    <tr>
                        <th>BId</th>
                        <th>Ürün</th>
                        <th>Fiyat</th>
                        <th>Sayı</th>
                        <th>Müşteri</th>
                        <th>Fiş No</th>
                        <th class="text-center" style="width: 55px;" >Sil</th>
                    </tr>
                    </thead>
                    <tbody id="tableRow">
                    <!-- for loop  -->

                    </tbody>
                </table>
            </div>
        </div>

        <div class="btn-group col-md-3 " role="group">
            <button onclick="fncSaveBox()" type="submit" class="btn btn-outline-primary mr-1">Satışı Tamamla</button>
        </div>

    </div>
</div>

<jsp:include page="inc/js.jsp"></jsp:include>
<script src="js/box_action.js"></script>
</body>

</html>