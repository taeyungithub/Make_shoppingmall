<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>상품 등록</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>


<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card shadow-sm">
                <div class="card-header bg-primary text-white">
                    <h3 class="card-title mb-0">상품 등록</h3>
                </div>
                <div class="card-body">
                    <form method="post" action="/mypage/addProductAction.do">
                        <div class="form-group">
                            <label for="categoryID">카테고리:</label>
                            <input type="text" class="form-control" id="categoryID" name="categoryID" placeholder="카테고리를 입력해주세요(1: 의류/잡화, 2: 뷰티, 3: 식품, 4: 주방/생활용품, 5:가전디지털)" required>
                        </div>

                        <div class="form-group">
                            <label for="productName">상품 이름:</label>
                            <input type="text" class="form-control" id="productName" name="productName" placeholder="상품 이름을 입력해주세요" required>
                        </div>

                        <div class="form-group">
                            <label for="productImage">상품 이미지 URL:</label>
                            <input type="url" class="form-control" id="productImage" name="productImage" placeholder="이미지 URL을 입력해주세요" required>
                        </div>

                        <div class="form-group">
                            <label for="productPrice">가격:</label>
                            <input type="number" class="form-control" id="productPrice" name="productPrice" placeholder="가격을 입력해주세요" required>
                        </div>

                        <div class="form-group">
                            <label for="description">상품 설명:</label>
                            <textarea class="form-control" id="description" name="description" rows="3" placeholder="상품 설명을 입력해주세요"></textarea>
                        </div>

                        <div class="form-group">
                            <label for="stock">재고:</label>
                            <textarea class="form-control" id="stock" name="stock" rows="3" placeholder="재고를 입력해주세요"></textarea>
                        </div>

                        <button type="submit" class="btn btn-primary btn-block">상품 등록</button>
                    </form>

                    <c:if test="${requestScope.successAdd == true}">
                        <div class="alert alert-success mt-3">
                            <strong>성공!</strong> 상품이 성공적으로 등록되었습니다.
                        </div>
                    </c:if>

                    <c:if test="${requestScope.errorAdd == true}">
                        <div class="alert alert-danger mt-3">
                            <strong>오류!</strong> 상품이 등록되지 않았습니다.
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
