package com.nhnacademy.shoppingmall.controller.shopping;

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

import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Slf4j
@RequestMapping(method = RequestMapping.Method.POST, value = "/deleteFromCartAction.do")
public class DeleteFromCartController implements BaseController {
    ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        int productId = Integer.parseInt(req.getParameter("productId"));

        HttpSession session = req.getSession();
        List<Product> cart = (List<Product>) session.getAttribute("cart");
        Map<Integer, Integer> quantityMap = (Map<Integer, Integer>) session.getAttribute("quantityMap");

        if (quantityMap != null && quantityMap.containsKey(productId)) {
            int requestStock = quantityMap.get(productId);

            Iterator<Product> iterator = cart.iterator();
            while (iterator.hasNext()) {
                Product product = iterator.next();
                if (product.getProductId() == productId) {
                    product.setStock(product.getStock() + requestStock);
                    productService.updateProduct(product);
                    iterator.remove();
                    break;
                }
            }

            quantityMap.remove(productId);
        }

        session.setAttribute("cart", cart);
        log.info("장바구니에서 삭제 완료 및 재고 복구: 제품 ID={}, 복구 수량={}", productId, quantityMap.get(productId));

        return "redirect:/shopping/cart.do";
    }

}
