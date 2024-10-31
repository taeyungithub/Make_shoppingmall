<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="container my-5">
    <div class="row justify-content-center">
        <div class="col-lg-6 col-md-8 col-sm-10">
            <div class="card shadow-lg border-0 rounded-lg">
                <div class="card-body p-4">
                    <h1 class="h3 mb-3 fw-bold text-center">상품 정보 수정</h1>
                    <form method="post" action="/admin/mypage/updateProductAction.do">
                        <input type="hidden" name="productId" value="${sessionScope.product.productId}">

                        <div class="form-floating mb-3">
                            <input type="text" name="product_name" class="form-control" id="product_name"
                                   placeholder="상품 이름">
                            <label for="product_name">상품 이름 수정</label>
                        </div>

                        <div class="form-floating mb-3">
                            <input type="text" name="product_image" class="form-control" id="product_image"
                                   placeholder="상품 사진"
                                   required>
                            <label for="product_image">상품 사진 수정</label>
                        </div>

                        <div class="form-floating mb-3">
                            <input type="text" name="product_price" class="form-control" id="product_price"
                                   placeholder="상품 가격"
                                   required>
                            <label for="product_price">상품 가격 수정</label>
                        </div>


                        <div class="form-floating mb-3">
                            <input type="text" name="description" class="form-control" id="description"
                                   placeholder="상품 설명"
                                   required>
                            <label for="description">상품 설명 수정</label>
                        </div>

                        <div class="form-floating mb-3">
                            <input type="text" name="stock" class="form-control" id="stock"
                                   placeholder="상품 재고"
                                   required>
                            <label for="stock">상품 재고 수정</label>
                        </div>


                        <div class="d-grid">
                            <button class="btn btn-primary btn-lg mt-4" type="submit">정보 수정</button>
                        </div>

                        <p class="mt-5 mb-0 text-center text-muted">© 2022-2024</p>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
