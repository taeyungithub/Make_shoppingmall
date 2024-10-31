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
                    <div class="d-flex justify-content-center gap-3 mt-3">
                        <a class="btn btn-warning" href="/mypage/update.do">정보 수정</a>

                        <a class="btn btn-warning" href="/addressPage.do">주소 관리</a>

                        <%--                        <a class="btn btn-warning" href="/mypage/usedPointList.do">포인트 사용이력</a>--%>
                        <form method="get" action="/mypage/usedPointList.do">
                            <div class="form-group">
                                <input type="hidden" name="userId" value="${sessionScope.user.userId}">
                            </div>
                            <button type="submit" class="btn btn-warning">사용 포인트 내역 / 주문내역</button>
                        </form>

                        <form method="post" action="/mypage/deleteUser.do">
                            <div class="form-group">
                                <input type="hidden" name="userId" value="${sessionScope.user.userId}">
                            </div>
                            <button type="submit" class="btn btn-danger">회원 삭제</button>
                        </form>

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
