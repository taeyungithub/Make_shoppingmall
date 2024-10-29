<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>상품 검색 결과</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
  <h2 class="mb-4">상품 검색 결과</h2>

  <c:choose>
    <c:when test="${not empty products}">
      <div class="row row-cols-1 row-cols-md-3 g-4">
        <c:forEach var="product" items="${products}">
          <div class="col">
            <div class="card h-100 shadow-sm">
              <img src="${product.productImage}" class="card-img-top" alt="${product.productName}" style="height: 300px; object-fit: cover;">
              <div class="card-body">
                <h5 class="card-title">${product.productName}</h5>
                <p class="card-text text-muted">${product.description}</p>
                <p class="card-text"><strong>가격:</strong> <span class="text-primary">${product.productPrice}원</span></p>
                <form action="/product/productView.do" method="post">
                  <input type="hidden" name="productID" value="${product.productId}">
                  <button type="submit" class="btn btn-outline-primary btn-sm">자세히 보기</button>
                </form>
              </div>
            </div>
          </div>
        </c:forEach>
      </div>
    </c:when>
    <c:otherwise>
      <div class="alert alert-info" role="alert">
        검색 결과가 없습니다.
      </div>
    </c:otherwise>
  </c:choose>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
