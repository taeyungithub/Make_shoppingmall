package com.nhnacademy.shoppingmall.controller.mypage.order;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.order.domain.Order;
import com.nhnacademy.shoppingmall.order.repository.Impl.OrderRepositoryImpl;
import com.nhnacademy.shoppingmall.order.service.Impl.OrderServiceImpl;
import com.nhnacademy.shoppingmall.order.service.OrderService;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.user.service.UserService;
import com.nhnacademy.shoppingmall.user.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(method = RequestMapping.Method.POST, value = "/mypage/orderDeleteAction.do")
public class deleteFromOrderController implements BaseController {

    OrderService orderService = new OrderServiceImpl(new OrderRepositoryImpl());
    UserService userService = new UserServiceImpl(new UserRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        int orderId = Integer.parseInt(req.getParameter("orderId"));
        Order order = orderService.getOrder(orderId);
        long price = order.getTotalPrice();
        log.info("orderId: {}, price: {}", orderId, price);

        orderService.cancelOrder(orderId);
        log.info("삭제완료");

        String userId = order.getOrdereduserId();
        User user = userService.getUser(userId);
        log.info("userId: {}, user: {}", userId, user);
        long Point = user.getUserPoint();
        long result = (long) (Point + price - (price * 0.1));

        user.setUserPoint((int) result);
        log.info("userPoint: {}", result);
        userService.updateUser(user);
        log.info("포인트 복구완료");

        return "redirect:/mypage/adminOrderList.do";
    }
}
