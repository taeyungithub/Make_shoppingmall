package com.nhnacademy.shoppingmall.controller.mypage.address;

import com.nhnacademy.shoppingmall.address.domain.Address;
import com.nhnacademy.shoppingmall.address.repository.AddressRepository;
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
@RequestMapping(method = RequestMapping.Method.POST, value = "/addressPageAction.do")
public class AddressPOSTController implements BaseController {
    private final AddressService addressService = new AddressServiceImpl(new AddressRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String action = req.getParameter("action");
        User user = (User) req.getSession().getAttribute("user");
        String userId = user.getUserId();

        switch (action) {
            case "add":
                String newAddress = req.getParameter("address");
                addressService.addAddress(new Address(0, newAddress, userId));
                break;

            case "edit":
                int addressIdToEdit = Integer.parseInt(req.getParameter("addressId"));
                String updatedAddress = req.getParameter("address");
                addressService.updateAddress(new Address(addressIdToEdit, updatedAddress, userId));
                break;

            case "delete":
                int addressIdToDelete = Integer.parseInt(req.getParameter("addressId"));
                addressService.deleteAddress(addressIdToDelete);
                break;
        }

        return "redirect:/mypage/addressPage.do";
    }
}
