package com.jidnivai.sdcian.sdcian.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jidnivai.sdcian.sdcian.dto.CartDto;
import com.jidnivai.sdcian.sdcian.entity.shop.CartItem;
import com.jidnivai.sdcian.sdcian.interfaces.CartServiceInt;
import com.jidnivai.sdcian.sdcian.security.services.UserDetailsImpl;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    @Autowired
    CartServiceInt cartService;

    @GetMapping("/all")
    public Page<CartDto> getAllCarts(@RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size,
            @AuthenticationPrincipal UserDetailsImpl user) {
        try {
            return cartService.getAllCarts(page, size, user.getUser());
        } catch (Exception e) {
            System.out.println("CartController: " + e.getMessage());
            return null;
        }
    }

    @GetMapping
    public CartDto getCartById(@AuthenticationPrincipal UserDetailsImpl user) {
        try {
            return cartService.getCartById(user.getUser());
        } catch (Exception e) {
            System.out.println("CartController: " + e.getMessage());
            return null;
        }
    }

    @PostMapping("/add/{productId}")
    public CartDto addtoCart(@PathVariable Long productId, @AuthenticationPrincipal UserDetailsImpl user) {
        try {
            return cartService.addtoCart(productId, user.getUser());
        } catch (Exception e) {
            System.out.println("CartController: " + e.getMessage());
            return null;
        }
    }

    @DeleteMapping("/remove")
    public void removeFromCart(@RequestParam Long[] itemid, @AuthenticationPrincipal UserDetailsImpl user) {
        try {
            cartService.deleteFromCart(itemid, user.getUser());
        } catch (Exception e) {
            System.out.println("CartController: " + e.getMessage());
        }
    }

    @GetMapping("/setQuantity/{itemId}/{quantity}")
    public CartDto setQuantity(@PathVariable Long itemId, @PathVariable int quantity,
            @AuthenticationPrincipal UserDetailsImpl user) {
        try {
            return cartService.setQuantity(itemId, quantity, user.getUser());
        } catch (Exception e) {
            System.out.println("CartController: " + e.getMessage());
            return null;
        }
    }

    @PostMapping("/checkout")
    public void checkout(@RequestBody Long[] productIds, @AuthenticationPrincipal UserDetailsImpl user) {
        try {
            cartService.checkout(productIds, user.getUser());
        } catch (Exception e) {
            System.out.println("CartController: " + e.getMessage());
        }
    }

    @GetMapping("/items")
    public List<CartItem> getCartItems(
            @RequestParam List<Long> itemIds,
            @AuthenticationPrincipal UserDetailsImpl user) {
        try {
            return cartService.getCartItems(itemIds, user.getUser());
        } catch (Exception e) {
            System.out.println("CartController: " + e.getMessage());
            return null;
        }
    }

}

