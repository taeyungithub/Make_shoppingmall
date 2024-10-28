package com.nhnacademy.shoppingmall.controller.mypage;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.product.repository.impl.ProductRepositoryImpl;
import com.nhnacademy.shoppingmall.product.service.ProductService;
import com.nhnacademy.shoppingmall.product.service.impl.ProductServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(method = RequestMapping.Method.POST, value = "/mypage/updateProductAction.do")
public class UpdateProductPOSTController implements BaseController {

    private final ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession(false);
        Product product = (Product) session.getAttribute("product");
        log.info(" product : {}", product);

        int productId = product.getProductId();
        int categoryId = product.getCategoryId();
        String productName = req.getParameter("product_name");
        String productImage = req.getParameter("product_image");
        long productPrice = Long.parseLong(req.getParameter("product_price"));
        String description = req.getParameter("description");
        int stock = Integer.parseInt(req.getParameter("stock"));

        product = new Product(productId, categoryId, productName, productImage, productPrice, description, stock);
        productService.updateProduct(product);
        session.setAttribute("product", product);
        log.info("상품 수정 완료");
        return "shop/mypage/admin_page";
    }
}
