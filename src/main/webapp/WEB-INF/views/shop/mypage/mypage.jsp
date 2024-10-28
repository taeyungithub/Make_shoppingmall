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
                            <strong>이름:</strong> ${sessionScope.user.userName}
                        </div>
                        <div class="col-md-6 mx-auto mb-3">
                            <strong>생년월일:</strong> ${sessionScope.user.userBirth}
                        </div>
                        <div class="col-md-6 mx-auto mb-3">
                            <strong>권한:</strong> ${sessionScope.user.userAuth}
                        </div>
                        <div class="col-md-6 mx-auto mb-3">
                            <strong>포인트:</strong> ${sessionScope.user.userPoint}
                        </div>
                        <div class="col-md-6 mx-auto mb-3">
                            <strong>가입일:</strong> ${cfmt:formatDate(sessionScope.user.createdAt, 'yyyy-MM-dd')}
                        </div>
                        <div class="col-md-6 mx-auto mb-3">
                            <strong>최근 접속일:</strong>
                            <c:choose>
                                <c:when test="${not empty sessionScope.user.latestLoginAt}">
                                    ${cfmt:formatDate(sessionScope.user.latestLoginAt, 'yyyy-MM-dd HH:mm:ss')}
                                </c:when>
                                <c:otherwise>
                                    최근 접속일 없음
                                </c:otherwise>
                            </c:choose>

                        </div>
                    </div>
                    <div class="text-center">
                        <a class="btn btn-warning mt-3" href="/mypage/update.do">정보 수정</a>
                    </div>
                </div>
            </div>
        </div>

        <c:if test="${sessionScope.user != null && sessionScope.user.userAuth == 'ROLE_ADMIN'}">
            <div class="col-md-8 mx-auto mt-4">
                <div class="card border-0 shadow-lg">
                    <div class="card-header bg-danger text-white text-center">
                        <h3>관리자 페이지</h3>
                    </div>
                    <div class="card-body text-center">
                        <a href="/mypage/adminPage.do" class="btn btn-danger">관리자 페이지로 이동</a>
                    </div>
                </div>
            </div>
        </c:if>
    </div>
</div>
