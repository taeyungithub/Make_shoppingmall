<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>주소 관리</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h2>주소 관리</h2>

    <form action="/addressPageAction.do" method="post" class="mb-4">
        <input type="hidden" name="action" value="add">
        <div class="input-group">
            <input type="text" name="address" placeholder="새 주소 입력" class="form-control" required>
            <button type="submit" class="btn btn-primary">추가</button>
        </div>
    </form>


    <table class="table table-bordered">
        <thead>
        <tr>
            <th>주소 ID</th>
            <th>주소</th>
            <th>수정</th>
            <th>삭제</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="address" items="${sessionScope.addresses}">
            <tr>
                <td>${address.addressId}</td>
                <td>${address.address}</td>
                <td>
                    <form action="/addressPageAction.do" method="post" class="d-inline">
                        <input type="hidden" name="action" value="edit">
                        <input type="hidden" name="addressId" value="${address.addressId}">
                        <input type="text" name="address" value="${address.address}" class="form-control d-inline"
                               style="width: 60%;" required>
                        <button type="submit" class="btn btn-secondary btn-sm">수정</button>
                    </form>
                </td>
                <td>
                    <form action="/addressPageAction.do" method="post" class="d-inline">
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="addressId" value="${address.addressId}">
                        <button type="submit" class="btn btn-danger btn-sm">삭제</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
