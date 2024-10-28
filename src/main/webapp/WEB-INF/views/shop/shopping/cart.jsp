<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.nhnacademy.shoppingmall.product.domain.Product" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>장바구니</title>
  <!-- Bootstrap CSS -->
  <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
  <h2 class="mb-4">장바구니</h2>
  <c:choose>
    <c:when test="${not empty sessionScope.cart}">
      <div class="row">
        <c:forEach var="product" items="${sessionScope.cart}">
          <div class="col-md-6 mb-4">
            <div class="card h-100 shadow-sm">
              <div class="row no-gutters">
                <div class="col-md-4">
                  <img src="${product.productImage}" class="card-img" alt="상품 이미지" style="height: 100%; object-fit: cover;">
                </div>
                <div class="col-md-8">
                  <div class="card-body">
                    <h5 class="card-title text-primary">${product.productName}</h5>
                    <p class="card-text text-muted">${product.description}</p>
                    <p class="card-text text-success font-weight-bold">${product.productPrice}원</p>
                    <form action="/deleteFromCartAction.do" method="post">
                      <input type="hidden" name="productId" value="${product.productId}">
                      <button type="submit" class="btn btn-danger btn-sm mt-3">삭제</button>
                    </form>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </c:forEach>
      </div>
    </c:when>
    <c:otherwise>
      <div class="alert alert-info" role="alert">
        장바구니에 상품이 없습니다.
      </div>
    </c:otherwise>
  </c:choose>
</div>

<!-- Bootstrap JS 및 jQuery -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
