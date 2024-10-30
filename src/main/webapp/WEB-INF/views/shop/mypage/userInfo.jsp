<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cfmt" uri="http://nhnacademy.com/cfmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<h1 class="text-center my-4">마이 페이지</h1>

<div class="container">
    <div class="row">
        <div class="col-md-8 mx-auto">
            <div class="card border-0 shadow-lg">
                <div class="card-header bg-primary text-white text-center">
                    <h2>유저 정보</h2>
                </div>
                <div class="card-body">
                    <div class="row text-center">
                        <div class="col-md-6 mx-auto mb-3">
                            <strong>이름:</strong> ${requestScope.user.userName}
                        </div>
                        <div class="col-md-6 mx-auto mb-3">
                            <strong>생년월일:</strong> ${requestScope.user.userBirth}
                        </div>
                        <div class="col-md-6 mx-auto mb-3">
                            <strong>권한:</strong> ${requestScope.user.userAuth}
                        </div>
                        <div class="col-md-6 mx-auto mb-3">
                            <strong>포인트:</strong> ${requestScope.user.userPoint}
                        </div>
                        <div class="col-md-6 mx-auto mb-3">
                            <strong>가입일:</strong> ${cfmt:formatDate(requestScope.user.createdAt, 'yyyy-MM-dd')}
                        </div>
                        <div class="col-md-6 mx-auto mb-3">
                            <strong>최근 접속일:</strong>
                            <c:choose>
                                <c:when test="${not empty requestScope.user.latestLoginAt}">
                                    ${cfmt:formatDate(requestScope.user.latestLoginAt, 'yyyy-MM-dd HH:mm:ss')}
                                </c:when>
                                <c:otherwise>
                                    최근 접속일 없음
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
