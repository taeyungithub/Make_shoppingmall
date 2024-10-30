package com.nhnacademy.shoppingmall.controller.mypage.user;

import com.nhnacademy.shoppingmall.address.domain.Address;
import com.nhnacademy.shoppingmall.address.repository.impl.AddressRepositoryImpl;
import com.nhnacademy.shoppingmall.address.service.AddressService;
import com.nhnacademy.shoppingmall.address.service.impl.AddressServiceImpl;
import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.order.domain.Order;
import com.nhnacademy.shoppingmall.order.repository.Impl.OrderRepositoryImpl;
import com.nhnacademy.shoppingmall.order.service.Impl.OrderServiceImpl;
import com.nhnacademy.shoppingmall.order.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequestMapping(method = RequestMapping.Method.GET, value = "/mypage/usedPointList.do")
public class UsedPointListController implements BaseController {

    private final OrderService orderService = new OrderServiceImpl(new OrderRepositoryImpl());
    private final AddressService addressService = new AddressServiceImpl(new AddressRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String userId = req.getParameter("userId");

        List<Order> orders = orderService.getOrderByUserId(userId);
        log.info("orders: {}", orders);

        // Order와 해당 Address를 매핑하는 Map 생성
        Map<Order, Address> orderAddressMap = new HashMap<>();
        for (Order order : orders) {
            int addressId = order.getAddressId();
            addressService.getAddressById(addressId).ifPresent(address -> orderAddressMap.put(order, address));
        }

        req.setAttribute("orderAddressMap", orderAddressMap);

        return "shop/mypage/usedPointList";
    }
}
