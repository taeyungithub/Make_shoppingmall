package com.nhnacademy.shoppingmall.controller.shopping;

import com.nhnacademy.shoppingmall.address.domain.Address;
import com.nhnacademy.shoppingmall.address.repository.impl.AddressRepositoryImpl;
import com.nhnacademy.shoppingmall.address.service.AddressService;
import com.nhnacademy.shoppingmall.address.service.impl.AddressServiceImpl;
import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.user.domain.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RequestMapping(method = RequestMapping.Method.GET, value = "/shopping/cart.do")
public class CartGETController implements BaseController {

    AddressService addressService = new AddressServiceImpl(new AddressRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        String userId = user.getUserId();
        log.info("userId = {}", userId);

        List<Address> addresses =  addressService.getAddressesByUserId(userId);
        session.setAttribute("addresses", addresses);
        log.info("addresses = {}", addresses);

        log.info("장바구니 이동");
        return "shop/shopping/cart";
    }
}
