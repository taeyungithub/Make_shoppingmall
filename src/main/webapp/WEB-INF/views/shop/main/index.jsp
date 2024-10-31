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
    <div class="mb-4 text-center">
        <a href="?categoryId=1" class="btn <%= categoryId == 1 ? "btn-primary" : "btn-outline-primary" %>">의류/잡화</a>
        <a href="?categoryId=2" class="btn <%= categoryId == 2 ? "btn-primary" : "btn-outline-primary" %>">뷰티</a>
        <a href="?categoryId=3" class="btn <%= categoryId == 3 ? "btn-primary" : "btn-outline-primary" %>">식품</a>
        <a href="?categoryId=4" class="btn <%= categoryId == 4 ? "btn-primary" : "btn-outline-primary" %>">주방/생활용품</a>
        <a href="?categoryId=5" class="btn <%= categoryId == 5 ? "btn-primary" : "btn-outline-primary" %>">가전디지털</a>
    </div>

    <hr class="my-4">

    <div class="recent-products-horizontal d-flex align-items-center mb-4 overflow-auto">
        <h4 class="me-3">최근 본 상품</h4>
        <c:forEach var="product" items="${sessionScope.recentProducts}">
            <div class="recent-product-item text-center me-3">
                <img src="${product.productImage}" alt="최근 본 상품 이미지" class="img-fluid rounded"
                     style="height: 100px; width: 100px; object-fit: cover;">
                <p class="mb-1 text-truncate" style="max-width: 100px; font-size: 0.9em;">
                    <strong>${product.productName}</strong></p>
                <span class="text-primary fw-bold" style="font-size: 0.8em;">${product.productPrice}원</span><br>
                <form action="/product/productView.do" method="post" class="mt-2">
                    <input type="hidden" name="productID" value="${product.productId}">
                    <button type="submit" class="btn btn-primary btn-sm">자세히 보기</button>
                </form>
            </div>
        </c:forEach>
    </div>

    <hr class="my-4">

    <div class="product-list">
        <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-4">
            <% for (Product product : pageProducts.getContent()) { %>
            <div class="col">
                <div class="card h-100 shadow-sm">
                    <img src="<%= product.getProductImage() %>" class="card-img-top" alt="상품 이미지"
                         style="height: 300px; object-fit: cover;">
                    <div class="card-body">
                        <h5 class="card-title"><%= product.getProductName() %>
                        </h5>
                        <p class="card-text text-muted"><%= product.getDescription() %>
                        </p>
                        <p class="card-text"><strong>가격:</strong> <span
                                class="text-primary"><%= product.getProductPrice() %>원</span></p>
                        <form action="/product/productView.do" method="post">
                            <input type="hidden" name="productID" value="<%= product.getProductId() %>">
                            <button type="submit" class="btn btn-outline-primary btn-sm">자세히 보기</button>
                        </form>
                    </div>
                </div>
            </div>
            <% } %>
        </div>

        <div class="d-flex justify-content-center mt-4">
            <ul class="pagination">
                <% for (int i = beginPage; i <= endPage; i++) { %>
                <li class="page-item <%= currentPage == i ? "active" : "" %>">
                    <a class="page-link" href="?currentPage=<%= i %>&categoryId=<%= categoryId %>"><%= i %>
                    </a>
                </li>
                <% } %>
            </ul>
        </div>
    </div>
</div>

<style>
    .recent-products-horizontal {
        white-space: nowrap;
        overflow-x: auto;
        padding: 10px 0;
    }

    .recent-product-item {
        width: 120px;
    }

    .recent-product-item img {
        margin-bottom: 5px;
    }

    .product-list {
        margin-top: 20px;
    }
</style>
