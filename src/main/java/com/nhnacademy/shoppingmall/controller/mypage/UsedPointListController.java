package com.nhnacademy.shoppingmall.controller.mypage;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.order.domain.Order;
import com.nhnacademy.shoppingmall.order.repository.Impl.OrderRepositoryImpl;
import com.nhnacademy.shoppingmall.order.service.Impl.OrderServiceImpl;
import com.nhnacademy.shoppingmall.order.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RequestMapping(method = RequestMapping.Method.GET,value = "/mypage/usedPointList.do")
public class UsedPointListController implements BaseController {

    OrderService orderService = new OrderServiceImpl(new OrderRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String userId = req.getParameter("userId");

        List<Order> orders = orderService.getOrderByUserId(userId);
        log.info("orders: {}", orders);

        req.setAttribute("orders", orders);

        return "shop/mypage/usedPointList";
    }
}
