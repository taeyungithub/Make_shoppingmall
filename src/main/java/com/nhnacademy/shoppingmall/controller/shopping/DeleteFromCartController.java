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

@Slf4j
@RequestMapping(method = RequestMapping.Method.POST, value = "/deleteFromCartAction.do")
public class DeleteFromCartController implements BaseController {
    ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        int productId = Integer.parseInt(req.getParameter("productId"));
        HttpSession session = req.getSession();
        List<Product> cart = (List<Product>) session.getAttribute("cart");

        Iterator<Product> iterator = cart.iterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (product.getProductId() == productId) {
                iterator.remove();
                break;
            }
        }

        session.setAttribute("cart", cart);
        log.info("삭제완료");

        return "redirect:/shopping/cart.do";
    }

}
