<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>오류 발생</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body class="bg-light">
<div class="container mt-5">
    <div class="alert alert-danger text-center">
        <h2>오류가 발생했습니다</h2>
        <p>
            <c:choose>
                <c:when test="${not empty errorMessage}">
                    ${errorMessage} <!-- 예외 메시지 출력 -->
                </c:when>
                <c:otherwise>
                    예기치 않은 오류가 발생했습니다. 잠시 후 다시 시도해주세요.
                </c:otherwise>
            </c:choose>
        </p>
        <a href="/" class="btn btn-primary mt-3">홈으로 돌아가기</a>
    </div>
</div>
</body>
</html>
