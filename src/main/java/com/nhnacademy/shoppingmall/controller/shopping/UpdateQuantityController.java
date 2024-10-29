package com.nhnacademy.shoppingmall.controller.shopping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import java.util.Map;

@RequestMapping(method = RequestMapping.Method.POST, value = "/updateQuantityAction.do")
public class UpdateQuantityController implements BaseController {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        Map<Integer, Integer> quantityMap = (Map<Integer, Integer>) session.getAttribute("quantityMap");
        Map<Integer, Integer> stockMap = (Map<Integer, Integer>) session.getAttribute("stockMap");

        int productId = Integer.parseInt(req.getParameter("productId"));
        String action = req.getParameter("action");

        if (quantityMap != null && quantityMap.containsKey(productId)) {
            int currentQuantity = quantityMap.get(productId);
            int stockQuantity = stockMap.getOrDefault(productId, Integer.MAX_VALUE);

            if ("increase".equals(action) && currentQuantity < stockQuantity) {
                quantityMap.put(productId, currentQuantity + 1);
            } else if ("decrease".equals(action) && currentQuantity > 1) {
                quantityMap.put(productId, currentQuantity - 1);
            }
        }

        session.setAttribute("quantityMap", quantityMap);  // 업데이트된 수량을 다시 세션에 저장
        return "redirect:/shopping/cart.do";  // 장바구니 페이지로 리다이렉트
    }
}
