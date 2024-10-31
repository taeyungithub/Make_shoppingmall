<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>사용자 목록</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body class="bg-light">
<div class="container mt-5">
    <h2 class="mb-4">사용자 목록</h2>

    <div class="mb-5">
        <h3>관리자</h3>
        <table class="table table-bordered table-hover">
            <thead class="thead-light">
            <tr>
                <th>사용자 ID</th>
                <th>이름</th>
                <th>생년월일</th>
                <th>포인트</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="admin" items="${admins}">
                <tr>
                    <td>${admin.userId}</td>
                    <td>${admin.userName}</td>
                    <td>${admin.userBirth}</td>
                    <td>${admin.userPoint}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <div>
        <h3>일반 사용자</h3>
        <table class="table table-bordered table-hover">
            <thead class="thead-light">
            <tr>
                <th>사용자 ID</th>
                <th>이름</th>
                <th>생년월일</th>
                <th>포인트</th>
                <th>정보보기</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="user" items="${generalUsers}">
                <tr>
                    <td>${user.userId}</td>
                    <td>${user.userName}</td>
                    <td>${user.userBirth}</td>
                    <td>${user.userPoint}</td>
                    <td>
                        <form method="post" action="/admin/mypage/userInfo.do">
                            <input type="hidden" name="userId" value="${user.userId}">
                            <button type="submit" class="btn btn-warning">보기</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>
