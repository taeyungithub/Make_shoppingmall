<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.nhnacademy.shoppingmall.product.domain.Product" %>
<%@ page import="com.nhnacademy.shoppingmall.common.page.Page" %>
<%@ page import="com.nhnacademy.shoppingmall.product.service.impl.ProductServiceImpl" %>
<%@ page import="com.nhnacademy.shoppingmall.product.repository.impl.ProductRepositoryImpl" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    int pageSize = 9;
    int currentPage = 1;
    int categoryId = request.getParameter("categoryId") != null ? Integer.parseInt(request.getParameter("categoryId")) : 0;
    if (request.getParameter("currentPage") != null) {
        currentPage = Integer.parseInt(request.getParameter("currentPage"));
    }

    ProductServiceImpl productService = new ProductServiceImpl(new ProductRepositoryImpl());
    Page<Product> pageProducts;
    if (categoryId > 0) {
        pageProducts = productService.getProductPageListByCategory(pageSize, currentPage, categoryId);
    } else {
        pageProducts = productService.getProductPageList(pageSize, currentPage);
    }

    long totalCount = pageProducts.getTotalCount();
    long totalPage = (totalCount + pageSize - 1) / pageSize;

    int beginPage = (currentPage - 1) / pageSize * pageSize + 1;
    long endPage = Math.min(totalPage, beginPage + pageSize - 1);
%>

<div class="container mt-4">
    <!-- 카테고리 버튼 -->
    <div class="mb-4 text-center">
        <a href="?categoryId=1" class="btn <%= categoryId == 1 ? "btn-primary" : "btn-outline-primary" %>">의류/잡화</a>
        <a href="?categoryId=2" class="btn <%= categoryId == 2 ? "btn-primary" : "btn-outline-primary" %>">뷰티</a>
        <a href="?categoryId=3" class="btn <%= categoryId == 3 ? "btn-primary" : "btn-outline-primary" %>">식품</a>
        <a href="?categoryId=4" class="btn <%= categoryId == 4 ? "btn-primary" : "btn-outline-primary" %>">주방/생활용품</a>
        <a href="?categoryId=5" class="btn <%= categoryId == 5 ? "btn-primary" : "btn-outline-primary" %>">가전디지털</a>
    </div>

    <!-- 메인 상품 목록 -->
    <div class="product-list w-75">
        <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-4">
            <% for (Product product : pageProducts.getContent()) { %>
            <div class="col">
                <div class="card h-100 shadow-sm">
                    <img src="<%= product.getProductImage() %>" class="card-img-top" alt="상품 이미지"
                         style="height: 300px; object-fit: cover;">
                    <div class="card-body">
                        <h5 class="card-title"><%= product.getProductName() %></h5>
                        <p class="card-text text-muted"><%= product.getDescription() %></p>
                        <p class="card-text"><strong>가격:</strong> <span class="text-primary"><%= product.getProductPrice() %>원</span></p>
                        <form action="/product/productView.do" method="post">
                            <input type="hidden" name="productID" value="<%= product.getProductId() %>">
                            <button type="submit" class="btn btn-outline-primary btn-sm">자세히 보기</button>
                        </form>
                    </div>
                </div>
            </div>
            <% } %>
        </div>

        <!-- 페이지네이션 -->
        <div class="d-flex justify-content-center mt-4">
            <ul class="pagination">
                <% for (int i = beginPage; i <= endPage; i++) { %>
                <li class="page-item <%= currentPage == i ? "active" : "" %>">
                    <a class="page-link" href="?currentPage=<%= i %>&categoryId=<%= categoryId %>"><%= i %></a>
                </li>
                <% } %>
            </ul>
        </div>
    </div>

    <!-- 최근 본 상품 - 오른쪽 사이드바 -->
    <div class="recent-products ms-4 w-25">
        <h3 class="text-center mb-4">최근 본 상품</h3>
        <table class="table table-striped table-bordered">
            <c:forEach var="product" items="${sessionScope.recentProducts}">
                <tr>
                    <td class="text-center">
                        <img src="${product.productImage}" alt="최근 본 상품 이미지" class="img-fluid rounded" style="height: 80px; width: 80px; object-fit: cover;">
                    </td>
                    <td>
                        <strong>${product.productName}</strong><br>
                        <span class="text-primary fw-bold">${product.productPrice}원</span><br>
                        <form action="/product/productView.do" method="post" class="mt-2">
                            <input type="hidden" name="productID" value="${product.productId}">
                            <button type="submit" class="btn btn-primary btn-sm w-100">자세히 보기</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

<style>
    .recent-products {
        max-width: 250px;
        position: sticky;
        top: 10px;
    }
</style>
