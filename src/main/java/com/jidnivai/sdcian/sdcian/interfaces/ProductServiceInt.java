package com.jidnivai.sdcian.sdcian.interfaces;


import org.springframework.data.domain.Page;

import com.jidnivai.sdcian.sdcian.dto.NewProductDto;
import com.jidnivai.sdcian.sdcian.dto.ProductDto;
import com.jidnivai.sdcian.sdcian.security.services.UserDetailsImpl;

public interface ProductServiceInt {

    Page<ProductDto> getAll(int page, int size);

    ProductDto getById(Long id);

    ProductDto add(NewProductDto newProductDto, UserDetailsImpl user);

    ProductDto update(Long id, ProductDto product);

    void delete(Long id);


    Page<ProductDto> getProductsByCategory( int page, int size);

    Page<ProductDto> search(String name, int page, int size);

    Page<ProductDto> sellerSearch(String name, int page, int size, Long id);


}
