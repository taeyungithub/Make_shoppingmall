<%@ page import="com.nhnacademy.shoppingmall.product.domain.Product" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="container mt-5">
  <div class="card mb-3 shadow-lg">
    <div class="row no-gutters">
      <div class="col-md-6">
        <img src="${requestScope.product.productImage}" class="card-img-top img-fluid" alt="상품 이미지" style="height: 100%; object-fit: cover;">
      </div>
      <div class="col-md-6">
        <div class="card-body">
          <h2 class="card-title text-primary"><strong>${requestScope.product.productName}</strong></h2>
          <p class="card-text text-muted">${requestScope.product.description}</p>
          <h3 class="text-success mt-4"><strong>${requestScope.product.productPrice}원</strong></h3>
          <p class="text-muted">상품 번호: <span>${requestScope.product.productId}</span></p>

          <div class="d-flex justify-content-end mt-4">
            <button class="btn btn-lg btn-primary w-100" type="submit">구매하기</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>