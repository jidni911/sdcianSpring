package com.jidnivai.sdcian.sdcian.interfaces;

import org.springframework.data.domain.Page;

import com.jidnivai.sdcian.sdcian.dto.CartDto;

public interface CartServiceInt {

    Page<CartDto> getAllCarts(int page, int size, Long userId);

    CartDto getCartById(Long id);

    CartDto addtoCart(Long productId, Long id);

    void deleteFromCart(Long[] productId, Long id);

    void checkout(Long[] productIds, Long id);

    CartDto setQuantity(Long itemId, int quantity, Long id);


}
