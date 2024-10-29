<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>사용 포인트 내역</title>
  <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
  <h2 class="mb-4">사용 포인트 내역 / 주문내역 </h2>

  <c:choose>
    <c:when test="${not empty sessionScope.userOrders}">
      <table class="table table-bordered table-hover">
        <thead class="thead-light">
        <tr>
          <th scope="col">주문 ID</th>
          <th scope="col">상품 ID</th>
          <th scope="col">수량</th>
          <th scope="col">총 가격</th>
          <th scope="col">주문 날짜</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="order" items="${sessionScope.userOrders}">
          <tr class="text-center">
            <td>${order.orderId}</td>
            <td>${order.OrderedproductId}</td>
            <td>${order.quantity}</td>
            <td>${order.totalPrice}원</td>
            <td>${order.orderDate}</td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </c:when>
    <c:otherwise>
      <div class="alert alert-info" role="alert">
        사용한 포인트 내역이 없습니다.
      </div>
    </c:otherwise>
  </c:choose>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
