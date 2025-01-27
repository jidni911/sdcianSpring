package com.jidnivai.sdcian.sdcian.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jidnivai.sdcian.sdcian.entity.Cart;

@RestController
@RequestMapping("/carts")
public class CartController {

    private List<Cart> carts = new ArrayList<>();

    @GetMapping
    public List<Cart> getAllCarts() {
        return carts;
    }

    @GetMapping("/{id}")
    public Cart getCartById(@PathVariable int id) {
        return carts.stream().filter(cart -> cart.getId() == id).findFirst().orElse(null);
    }

    @PostMapping
    public Cart createCart(@RequestBody Cart newCart) {
        carts.add(newCart);
        return newCart;
    }

    @PutMapping("/{id}")
    public Cart updateCart(@PathVariable int id, @RequestBody Cart updatedCart) {
        for (int i = 0; i < carts.size(); i++) {
            if (carts.get(i).getId() == id) {
                carts.set(i, updatedCart);
                return updatedCart;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteCart(@PathVariable int id) {
        carts.removeIf(cart -> cart.getId() == id);
    }
}



