package com.jidnivai.sdcian.sdcian.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jidnivai.sdcian.sdcian.dto.NewProductDto;
import com.jidnivai.sdcian.sdcian.dto.ProductDto;
import com.jidnivai.sdcian.sdcian.interfaces.ProductServiceInt;
import com.jidnivai.sdcian.sdcian.security.services.UserDetailsImpl;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductServiceInt productService;

    @GetMapping
    public Page<ProductDto> getProducts(@RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size,
            @AuthenticationPrincipal UserDetailsImpl user) {
        try {
            return productService.getAll(page, size);

        } catch (Exception e) {
            System.out.println("ProductController: " + e.getMessage());
            return null;
        }
    }

    @GetMapping("/{id}")
    public ProductDto getById(@PathVariable Long id) {
        try {
            return productService.getById(id);

        } catch (Exception e) {
            System.out.println("ProductController: " + e.getMessage());
            return null;
        }
    }

    @PostMapping()
    public ProductDto add(@RequestBody NewProductDto newProductDto, @AuthenticationPrincipal UserDetailsImpl user) {
        // System.out.println(user);
        try {
            return productService.add(newProductDto, user);

        } catch (Exception e) {
            System.out.println("ProductController: " + e.getMessage());
            return null;
        }
    }

    @PutMapping("/{id}")
    public ProductDto update(@PathVariable Long id, @RequestBody ProductDto product) {
        try {
            return productService.update(id, product);

        } catch (Exception e) {
            System.out.println("ProductController: " + e.getMessage());
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        try {
            productService.delete(id);

        } catch (Exception e) {
            System.out.println("ProductController: " + e.getMessage());
        }
    }

    @GetMapping("/search/{name}")
    public Page<ProductDto> search(@PathVariable String name,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {
        try {
            return productService.search(name, page, size);

        } catch (Exception e) {
            System.out.println("ProductController: " + e.getMessage());
            return null;
        }
    }

    @GetMapping("/sellerSearch/{name}")
    public Page<ProductDto> sellerSearch(@PathVariable String name,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size,
            @AuthenticationPrincipal UserDetailsImpl user) {
        try {
            return productService.sellerSearch(name, page, size, user.getUser());

        } catch (Exception e) {
            System.out.println("ProductController: " + e.getMessage());
            return null;
        }
    }

    // sellerSearch

}
