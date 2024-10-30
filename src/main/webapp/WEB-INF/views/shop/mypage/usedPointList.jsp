<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cfmt" uri="http://nhnacademy.com/cfmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>사용 포인트 내역</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
    <h2 class="mb-4">사용 포인트 내역 / 주문내역</h2>

    <c:choose>
        <c:when test="${not empty orderAddressMap}">
            <table class="table table-bordered table-hover">
                <thead class="thead-light">
                <tr>
                    <th scope="col">주문 ID</th>
                    <th scope="col">상품 ID</th>
                    <th scope="col">수량</th>
                    <th scope="col">총 가격</th>
                    <th scope="col">주문 날짜</th>
                    <th scope="col">주소</th>
                    <th scope="col">주문 삭제</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="entry" items="${orderAddressMap}">
                    <tr class="text-center">
                        <td>${entry.key.orderId}</td>
                        <td>${entry.key.orderedproductId}</td>
                        <td>${entry.key.quantity}</td>
                        <td>${entry.key.totalPrice}원</td>
                        <td>${cfmt:formatDate(entry.key.orderDate,'yyyy-MM-dd HH:mm:ss')}</td>
                        <td>${entry.value.address}</td>
                        <td>
                            <form method="post" action="/mypage/orderDeleteAction.do">
                                <input type="hidden" name="orderId" value="${entry.key.orderId}">
                                <button type="submit" class="btn btn-danger">삭제</button>
                            </form>
                        </td>
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