package com.jidnivai.sdcian.sdcian.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.jidnivai.sdcian.sdcian.dto.CartDto;
import com.jidnivai.sdcian.sdcian.dto.UserDto;
import com.jidnivai.sdcian.sdcian.entity.User;
import com.jidnivai.sdcian.sdcian.entity.shop.Cart;
import com.jidnivai.sdcian.sdcian.entity.shop.CartItem;
import com.jidnivai.sdcian.sdcian.entity.shop.Product;
import com.jidnivai.sdcian.sdcian.interfaces.CartServiceInt;
import com.jidnivai.sdcian.sdcian.repository.CartRepository;
import com.jidnivai.sdcian.sdcian.repository.ProductRepository;
import com.jidnivai.sdcian.sdcian.repository.UserRepository;


@Service
public class CartService implements CartServiceInt {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;
    @Override
    public Page<CartDto> getAllCarts(int page, int size, User user) {

        if (user.getRoles().stream().anyMatch(role -> role.getName().equals("ROLE_ADMIN"))){ 
            Page<Cart> carts = cartRepository.findAll(PageRequest.of(page, size));
            return carts.map(cart -> {
                CartDto cartDto = new CartDto();
                BeanUtils.copyProperties(cart, cartDto);
                UserDto userDto = new UserDto();
                BeanUtils.copyProperties(cart.getUser(), userDto);
                cartDto.setUserDto(userDto);
                return cartDto;
            });
        } else{
            return null;
        }

        
    }

    @Override
    public CartDto getCartById(User user) {
        // User user = userRepository.findById(id).orElseThrow();
        Optional<Cart> optionalCart = cartRepository.findById(user.getId());
        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();
            CartDto cartDto = new CartDto();
            BeanUtils.copyProperties(cart, cartDto);
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(cart.getUser(), userDto);
            cartDto.setUserDto(userDto);
            return cartDto;
        } else {
            Cart cart = new Cart();
            cart.setId(user.getId());
            cart.setUser(user);
            cart =  cartRepository.save(cart);
            CartDto cartDto = new CartDto();
            BeanUtils.copyProperties(cart, cartDto);
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(cart.getUser(), userDto);
            cartDto.setUserDto(userDto);
            return cartDto;
        }

    }
    @Override
    public CartDto addtoCart(Long productId, User user) {
        Cart cart = cartRepository.findById(user.getId()).orElseThrow();// Getting the cart
        CartItem cartItem = new CartItem();
        cartItem.setProduct(productRepository.findById(productId).orElseThrow());
        cartItem.setQuantity(1);
        cartItem.setCart(cart);
        cart.getItems().add(cartItem);
        cart = cartRepository.save(cart);//Updated cart
        CartDto cartDto = new CartDto();
        BeanUtils.copyProperties(cart, cartDto);
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(cart.getUser(), userDto);
        cartDto.setUserDto(userDto);
        return cartDto;
    }

    @Override
    public void deleteFromCart(Long[] itemids, User user) {
        Cart cart = cartRepository.findById(user.getId()).orElseThrow();
        for (Long itemid : itemids) {
            cart.getItems().removeIf(item -> item.getId().equals(itemid));
        }
        cartRepository.save(cart);
    }

    @Override
    public void checkout(Long[] productIds, User user) {
        //TODO generate order
        deleteFromCart(productIds, user);
    }

    @Override
    public CartDto setQuantity(Long itemId, int quantity, User user) {
        Cart cart = cartRepository.findById(user.getId()).orElseThrow();
        CartItem cartItem = cart.getItems().stream().filter(item -> item.getId().equals(itemId)).findFirst().orElseThrow();
        Product product = cartItem.getProduct();
        if (quantity >0 && quantity < product.getQuantity()) {
            cartItem.setQuantity(quantity);
            cart = cartRepository.save(cart);
        }
        CartDto cartDto = new CartDto();
        BeanUtils.copyProperties(cart, cartDto);
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(cart.getUser(), userDto);
        cartDto.setUserDto(userDto);
        return cartDto;
    }

    @Override
    public List<CartItem> getCartItems(List<Long> itemIds, User user) {
        Cart cart = cartRepository.findById(user.getId()).orElseThrow();
        return cart.getItems().stream().filter(item -> itemIds.contains(item.getId())).toList();
    }

}
