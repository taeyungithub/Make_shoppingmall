<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container my-5">

    <div class="row">
        <div class="col-md-3">
            <div class="card shadow-sm">
                <div class="card-header bg-primary text-white">
                    <h5 class="mb-0">관리자 메뉴</h5>
                </div>
                <ul class="nav flex-column list-group list-group-flush">
                    <li class="nav-item list-group-item">
                        <a class="nav-link text-dark" href="/mypage/productList.do">상품 목록</a>
                    </li>
                    <li class="nav-item list-group-item">
                        <a class="nav-link text-dark" href="/mypage/addProduct.do">상품 추가</a>
                    </li>
                    <li class="nav-item list-group-item">
                        <a class="nav-link text-dark" href="/mypage/deleteProduct.do">상품 삭제</a>
                    </li>
                    <li class="nav-item list-group-item">
                        <a class="nav-link text-dark" href="/mypage/adminOrderList.do">주문 관리</a>
                    </li>
                </ul>
            </div>
        </div>

        <div class="col-md-9">
            <div class="card shadow-sm">
                <div class="card-body">
                    <h3 class="card-title">관리자 기능</h3>
                    <p class="card-text">왼쪽 메뉴를 선택하여 필요한 관리 기능을 사용하세요.</p>
                </div>
            </div>
        </div>
    </div>
</div>
