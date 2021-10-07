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
  <div id="content" class="p-4 p-md-5">
    <jsp:include page="inc/topMenu.jsp"></jsp:include>

    <h3 class="mb-3">
      Dashboard
      <small class="h6">Yönetim Paneli</small>
    </h3>


    <div class="row">
      <div class="col-sm-6">
        <div class="row">
          <div class="col-sm-6">
            <div class="d-grid gap-2">
              <button class="btn btn-primary btn-lg mb-2 text-left custom_btn button1" type="button"> <i class="fas fa-user-plus"></i>
                <a href="customer.jsp" style="color: white">Müşteri Ekle</a>  </button>
              <button class="btn btn-warning btn-lg mb-2 text-left custom_btn button2" type="button"> <i class="fa fa-shopping-basket"></i>
                <a href="products.jsp" style="color: white">Ürün Ekle</a> </button>
              <button class="btn btn-success btn-lg mb-2 text-left custom_btn button3" type="button"><i class="fa fa-shopping-cart"></i>
                <a href="boxAction.jsp" style="color: white">Sipariş Ekle</a> </button>
              <button class="btn btn-danger btn-lg mb-2 text-left  custom_btn button4" type="button"><i class="fa fa-window-maximize"></i>
                <a href="payIn.jsp" style="color: white">Ödeme Girişi</a> </button>
            </div>
          </div>
          <div class="col-sm-6">
            <div class="card mb-3" id="card"
            >
              <div class="card-body d-flex flex-row justify-content-between" style="padding-bottom: .6rem;">
                <div class="card-left">
                  <div class="card-subtitle mb-1 text-muted" style="opacity: .8; margin-bottom: 0;">Toplam</div>
                  <div class="card-title" style="font-weight: 700; margin-bottom: 0; letter-spacing: 0.5px;">Müşteri
                    Hesabı</div>
                </div>
                <div class="card-right">
                  <div style="font-size: 1.8rem;font-weight: 700; color:#22AE78;"><span id="customer_count" > </span></div>
                </div>
              </div>
            </div>
            <div class="card mb-3">
              <div class="card-body d-flex flex-row justify-content-between" style="padding-bottom: .6rem;">
                <div class="card-left">
                  <div class="card-subtitle mb-1 text-muted" style="opacity: .8; margin-bottom: 0;">Toplam</div>
                  <div class="card-title" style="font-weight: 700; margin-bottom: 0; letter-spacing: 0.5px;">Sipariş
                  </div>
                </div>
                <div class="card-right">
                  <div style="font-size: 1.8rem;font-weight: 700; color: #244785;"><span id="order_count"></span></div>
                </div>
              </div>
            </div>
            <div class="card mb-3">
              <div class="card-body d-flex flex-row justify-content-between" style="padding-bottom: .6rem;">
                <div class="card-left">
                  <div class="card-subtitle mb-1 text-muted" style="opacity: .8; margin-bottom: 0;">Toplam</div>
                  <div class="card-title" style="font-weight: 700; margin-bottom: 0; letter-spacing: 0.5px;">Stoktaki
                    Ürünler</div>
                </div>
                <div class="card-right">
                  <div style="font-size: 1.8rem;font-weight: 700;color: #F7B924;"><span id="product_count"></span></div>
                </div>
              </div>
            </div>
          </div>

        </div>
      </div>
      <div class="col-sm-6">
        <div class="card mb-3"
             style="height:140px;border-width:0; box-shadow: 0 0.46875rem 2.1875rem rgb(4 9 20 / 3%), 0 0.9375rem 1.40625rem rgb(4 9 20 / 3%), 0 0.25rem 0.53125rem rgb(4 9 20 / 5%), 0 0.125rem 0.1875rem rgb(4 9 20 / 3%);">
          <div class="card-body">
            <h4 class="card-title badge badge-success mr-1 ml-0 text-white p-2"
                style="font-size:.88rem;background-color: #3AC47D;">KASA</h4>
            <div class="row space-5">
              <div class="col-sm-12 col-md-12" style="height: 20px;">
                <small class="text-muted" style="color: #978e8e;">KASA</small>
                <small class="text-muted">toplam</small>
                <span style="color: black;" ><b > <span id="total"></span> TL</b></span>
              </div>
              <div class="col-6 col-sm-6 col-md-6">
                <div>
                  <small class="text-muted">bu gün giriş</small>
                  <br>
                  <span class="ff-2 fs-14 bold" style="color: black;"><b> + <span id="today_total"></span>  TL </b></span>
                </div>
              </div>
              <div class="col-6 col-sm-6 col-md-6">
                <div>
                  <small class="text-muted">bu gün çıkış</small>
                  <br>
                  <span class="ff-2 fs-14 bold" style="color: black;"><b> - <span id="dailyPayment"></span>  TL </b></span>
                </div>
              </div>
            </div>
          </div>
        </div>

        <div class="card"
             style="height:140px;border-width:0; box-shadow: 0 0.46875rem 2.1875rem rgb(4 9 20 / 3%), 0 0.9375rem 1.40625rem rgb(4 9 20 / 3%), 0 0.25rem 0.53125rem rgb(4 9 20 / 5%), 0 0.125rem 0.1875rem rgb(4 9 20 / 3%);">
          <div class="card-body">
            <h4 class="card-title badge badge-success mr-1 ml-0 text-white p-2"
                style="font-size:.88rem;background-color: #F7B924;">STOK</h4>
            <div class="row space-5">
              <div class="col-sm-12 col-md-12" style="height: 20px;">
                <span style="color: black;"><b><span id="kind"></span></b></span>
                <small class="text-muted">çeşit</small>
                <span style="color: black;"><b><span id="quantity"></span></b> adet</span>
              </div>
              <div class="col-6 col-sm-6 col-md-6">
                <div>
                  <small class="text-muted">maliyet değeri</small>
                  <br>
                  <span class="ff-2 fs-14 bold" style="color: black;"><b> + <span id="purchasePrice"></span> TL </b></span>
                </div>
              </div>
              <div class="col-6 col-sm-6 col-md-6">
                <div>
                  <small class="text-muted">satış değeri</small>
                  <br>
                  <span class="ff-2 fs-14 bold" style="color: black;"><b> + <span id="sellPrice"></span> TL </b></span>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="row mt-5">
      <div class="col-sm-6">
        <div class="main-card mb-3 card mainCart">
          <div class="card-header cardHeader">Son 5
            Stok Ürünü</div>
          <div class="table-responsive">
            <table class="align-middle mb-0 table table-borderless table-striped table-hover">
              <thead>
              <tr>
                <th>#</th>
                <th>Ürün Kodu</th>
                <th>Ürün Adı</th>
                <th>Alış Fiyatı</th>
              </tr>
              </thead>
              <tbody id="tableProductsRow">
              <!-- for loop  -->

              </tbody>
            </table>
          </div>
        </div>
      </div>
      <div class="col-sm-6">
        <div class="main-card mb-3 card"
             style="box-shadow: 0 0.46875rem 2.1875rem rgb(4 9 20 / 3%), 0 0.9375rem 1.40625rem rgb(4 9 20 / 3%), 0 0.25rem 0.53125rem rgb(4 9 20 / 5%), 0 0.125rem 0.1875rem rgb(4 9 20 / 3%);border-width: 0;">
          <div class="card-header"
               style="text-transform: uppercase;color: rgb(13, 27, 62,0.7);font-weight: 700; font-size: 0.88rem;">Son
            Siparişler</div>
          <div class="table-responsive">
            <table class="align-middle mb-0 table table-borderless table-striped table-hover">
              <thead>
              <tr>
                <th>FişNo</th>
                <th>Müşteri Adı</th>
                <th>KDV Fiyatı</th>
                <th>Satış Fiyatı</th>
              </tr>
              </thead>
              <!-- for loop  -->
              <tbody id="tableOrdersRow">

              </tbody>
            </table>
          </div>
        </div>


      </div>
    </div>



  </div>
</div>
</div>
<jsp:include page="inc/js.jsp"></jsp:include>
<script src="js/dashboard.js"></script>
</body>

</html>
