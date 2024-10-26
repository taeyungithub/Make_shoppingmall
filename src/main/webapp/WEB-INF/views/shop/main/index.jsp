<%@ page import="com.nhnacademy.shoppingmall.product.domain.Product" %>
<%@ page import="com.nhnacademy.shoppingmall.common.page.Page" %>
<%@ page import="com.nhnacademy.shoppingmall.product.service.impl.ProductServiceImpl" %>
<%@ page import="com.nhnacademy.shoppingmall.product.repository.impl.ProductRepositoryImpl" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    int pageSize = 9;
    int currentPage = 1;
    if (request.getParameter("currentPage") != null) {
        currentPage = Integer.parseInt(request.getParameter("currentPage"));
    }
    Page<Product> pageProducts = new ProductServiceImpl(new ProductRepositoryImpl()).getProductPageList(pageSize, currentPage);
    long totalCount = pageProducts.getTotalCount();
    long totalPage = (totalCount + pageSize - 1) / pageSize;

    int beginPage = (currentPage - 1) / pageSize * pageSize + 1;
    long endPage = Math.min(totalPage, beginPage + pageSize - 1);
%>

<div class="container mt-4">
    <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-4">
        <% for (Product product : pageProducts.getContent()) { %>
        <div class="col">
            <div class="card h-100 shadow-sm">
                <img src="<%= product.getProductImage() %>" class="card-img-top" alt="상품 이미지" style="height: 300px; object-fit: cover;">
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

    <div class="d-flex justify-content-center mt-4">
        <ul class="pagination">
            <% for (int i = beginPage; i <= endPage; i++) { %>
            <li class="page-item <%= currentPage == i ? "active" : "" %>">
                <a class="page-link" href="?currentPage=<%= i %>"><%= i %></a>
            </li>
            <% } %>
        </ul>
    </div>
</div>
