<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="entities.Admin" %>
<jsp:useBean id="util" class="utils.Util"></jsp:useBean>
<% Admin adm = util.isLogin(request, response); %>

<!doctype html>
<html lang="en">

<head>
  <title>Kasa Yönetimi / Ödeme Çıkışı</title>
  <jsp:include page="inc/css.jsp"></jsp:include>
</head>

<body>

<div class="wrapper d-flex align-items-stretch">
  <jsp:include page="inc/sideBar.jsp"></jsp:include>
  <div id="content" class="p-4 p-md-5">
    <jsp:include page="inc/topMenu.jsp"></jsp:include>
    <h3 class="mb-3">
      Kasa Yönetimi
      <small class="h6">Ödeme Çıkışı</small>
    </h3>

    <div class="main-card mb-3 card mainCart">
      <div class="card-header cardHeader">Ödeme Ekle</div>

      <form class="row p-3" id="add_payOut">

        <div class="col-md-3 mb-3">
          <label for="payOutTitle" class="form-label">Başlık</label>
          <input type="text" name="payOutTitle" id="payOutTitle" class="form-control" required />
        </div>

        <div class="col-md-3 mb-3">
          <label for="payOutType" class="form-label">Ödeme Türü</label>
          <select class="form-select" name="payOutType" id="payOutType" required>
            <option value="">Ödeme Türünü Seçiniz</option>
            <option value="0">Nakit</option>
            <option value="1">Kredi Kartı</option>
            <option value="2">Havale</option>
            <option value="3">EFT</option>
            <option value="4">Banka Çeki</option>
          </select>
        </div>

        <div class="col-md-3 mb-3">
          <label for="payOutTotal" class="form-label">Ödeme Tutarı</label>
          <input type="number" name="payOutTotal" id="payOutTotal" class="form-control" required />
        </div>

        <div class="col-md-3 mb-3">
          <label for="payOutDetail" class="form-label">Ödeme Detay</label>
          <input type="text" name="payOutDetail" id="payOutDetail" class="form-control" required />
        </div>

        <div class="btn-group col-md-3 " role="group">
          <button type="submit" class="btn btn-outline-primary mr-1">Gönder</button>
          <button onclick="fncPayoutReset()" type="button" class="btn btn-outline-primary">Temizle</button>
        </div>

      </form>
    </div>


    <div class="main-card mb-3 card mainCart">
      <div class="card-header cardHeader">Ödeme Çıkış Listesi</div>

      <div class="row mt-3" style="padding-right: 15px; padding-left: 15px;">
        <div class="col-sm-3"></div>
        <div class="col-sm-3"></div>
        <div class="col-sm-3"></div>
        <div class="col-sm-3">
        </div>



      </div>
      <div class="table-responsive">
        <table class="align-middle mb-0 table table-borderless table-striped table-hover">
          <thead>
          <tr>
            <th>Id</th>
            <th>Başlık</th>
            <th>Ödeme Türü</th>
            <th>Ödeme Detayı</th>
            <th>Ödeme Tutarı</th>
            <th>Tarih</th>

          </tr>
          </thead>
          <tbody id="tableRow">
          <!-- for loop  -->

          </tr>
          </tbody>
        </table>
      </div>
    </div>


  </div>
</div>
</div>
<jsp:include page="inc/js.jsp"></jsp:include>
<script src="js/payout.js"></script>
</body>

</html>
