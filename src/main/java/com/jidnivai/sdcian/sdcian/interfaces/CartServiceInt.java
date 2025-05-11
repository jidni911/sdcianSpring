package com.jidnivai.sdcian.sdcian.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;

import com.jidnivai.sdcian.sdcian.dto.CartDto;
import com.jidnivai.sdcian.sdcian.entity.User;
import com.jidnivai.sdcian.sdcian.entity.shop.CartItem;

public interface CartServiceInt {

    Page<CartDto> getAllCarts(int page, int size, User user);

    CartDto getCartById(User user);

    CartDto addtoCart(Long productId, User user);

    void deleteFromCart(Long[] productId, User user);

    void checkout(Long[] productIds, User user);

    CartDto setQuantity(Long itemId, int quantity, User user);

    List<CartItem> getCartItems(List<Long> productIds, User user);


}
