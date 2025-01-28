package com.jidnivai.sdcian.sdcian.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.jidnivai.sdcian.sdcian.entity.Cart;
import com.jidnivai.sdcian.sdcian.interfaces.CartServiceInt;
import com.jidnivai.sdcian.sdcian.repository.CartRepository;

@Service
public class CartService implements CartServiceInt {
//TODO rethink cart item

    @Autowired
    private CartRepository cartRepository;
    @Override
    public Page<Cart> getAllCarts(int page, int size) {
        return cartRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public Cart getCartById(Long id) {
        return cartRepository.findById(id).orElseThrow();
    }

    @Override
    public Cart saveCart(Cart newCart) {
        return cartRepository.save(newCart);
    }

    @Override
    public Cart updateCart(Long id, Cart updatedCart) {
        Cart cart = getCartById(id);
        cart.setItems(updatedCart.getItems());
        cart.setUser(updatedCart.getUser());
        return cartRepository.save(cart);
    }

    @Override
    public void deleteCart(Long id) {
        cartRepository.deleteById(id);
    }

    @Override
    public Cart getCartByUser(Long userId) {
        return cartRepository.findByUser_Id(userId).orElseThrow();
    }

}
