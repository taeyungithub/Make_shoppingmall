<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>상품 목록</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
</head>
<body>

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-10">
            <div class="card shadow-sm border-0">
                <div class="card-header bg-primary text-white text-center">
                    <h2><i class="fas fa-list mr-2"></i>상품 목록</h2>
                </div>
                <div class="card-body">
                    <c:if test="${not empty sessionScope.list}">
                        <table class="table table-striped table-hover table-bordered">
                            <thead class="thead-dark">
                            <tr class="text-center">
                                <th scope="col">상품 ID</th>
                                <th scope="col">카테고리 ID</th>
                                <th scope="col">상품 이름</th>
                                <th scope="col">가격</th>
                                <th scope="col">설명</th>
                                <th scope="col">상품 수정</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${sessionScope.list}" var="product">
                                <tr class="text-center">
                                    <td>${product.productId}</td>
                                    <td>${product.categoryId}</td>
                                    <td>${product.productName}</td>
                                    <td>${product.productPrice}</td>
                                    <td>${product.description}</td>
                                    <td>
                                        <a class="btn btn-warning btn-sm" href="/mypage/updateProduct.do?productId=${product.productId}">
                                            <i class="fas fa-edit mr-1"></i>수정
                                        </a>

                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </c:if>

                    <c:if test="${empty sessionScope.list}">
                        <div class="alert alert-info text-center">
                            <i class="fas fa-info-circle"></i> 상품이 없습니다.
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
