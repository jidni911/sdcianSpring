package com.jidnivai.sdcian.sdcian.interfaces;

import org.springframework.data.domain.Page;

import com.jidnivai.sdcian.sdcian.entity.Cart;

public interface CartServiceInt {

    Page<Cart> getAllCarts(int page, int size);

    Cart getCartById(Long id);

    Cart saveCart(Cart newCart);

    Cart updateCart(Long id, Cart updatedCart);

    void deleteCart(Long id);

    Cart getCartByUser(Long userId);


}
