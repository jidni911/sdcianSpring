package com.jidnivai.sdcian.sdcian.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;

import com.jidnivai.sdcian.sdcian.entity.Product;

public interface ProductServiceInt {

    List<Product> getAll();

    Product getById(Long id);

    Product add(Product product);

    Product update(Long id, Product product);

    void delete(Long id);


    Page<Product> getProductsByCategory( int page, int size);

    Page<Product> search(String name, int page, int size);

}
