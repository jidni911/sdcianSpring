package com.jidnivai.sdcian.sdcian.interfaces;


import org.springframework.data.domain.Page;

import com.jidnivai.sdcian.sdcian.dto.NewProductDto;
import com.jidnivai.sdcian.sdcian.dto.ProductDto;
import com.jidnivai.sdcian.sdcian.entity.Product;
import com.jidnivai.sdcian.sdcian.security.services.UserDetailsImpl;

public interface ProductServiceInt {

    Page<Product> getAll(int page, int size);

    Product getById(Long id);

    ProductDto add(NewProductDto newProductDto, UserDetailsImpl user);

    Product update(Long id, Product product);

    void delete(Long id);


    Page<Product> getProductsByCategory( int page, int size);

    Page<Product> search(String name, int page, int size);


}
