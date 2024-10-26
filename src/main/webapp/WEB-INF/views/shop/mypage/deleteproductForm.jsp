<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>상품 삭제</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>


<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card shadow-sm">
                <div class="card-header bg-danger text-white">
                    <h3 class="card-title mb-0">상품 삭제</h3>
                </div>
                <div class="card-body">
                    <form method="post" action="/mypage/deleteProductAction.do">
                        <div class="form-group">
                            <label for="productID">상품 ID:</label>
                            <input type="text" class="form-control" id="productID" name="productID" placeholder="삭제할 상품 ID를 입력해주세요" required>
                        </div>
                        <button type="submit" class="btn btn-danger btn-block">상품 삭제</button>
                    </form>

                    <c:if test="${requestScope.successMessage == true}">
                        <div class="alert alert-success mt-3">
                            <strong>성공!</strong> 상품이 성공적으로 삭제되었습니다.
                        </div>
                    </c:if>

                    <c:if test="${requestScope.errorMessage == true}">
                        <div class="alert alert-danger mt-3">
                            <strong>오류!</strong> 상품을 찾을 수 없습니다.
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
