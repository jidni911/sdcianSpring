package com.jidnivai.sdcian.sdcian.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jidnivai.sdcian.sdcian.entity.Product;
import com.jidnivai.sdcian.sdcian.interfaces.ProductServiceInt;
import com.jidnivai.sdcian.sdcian.repository.ProductRepository;

@Service
public class ProductService implements ProductServiceInt {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public Page<Product> getAll(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return productRepository.findAll(pageable);
	}

	

	@Override
	public Product getById(Long id) {
		Optional<Product> optionalProduct = productRepository.findById(id);
		return optionalProduct.isPresent() ? optionalProduct.get() : null;
	}

	@Override
	public Product add(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Product update(Long id, Product product) {
		Product existingProduct = productRepository.findById(id).orElse(null);
		if (existingProduct == null) {
			return null;
		}
		existingProduct.setName(product.getName());
		existingProduct.setPrice(product.getPrice());
		existingProduct.setDescription(product.getDescription());
		existingProduct.setQuantity(product.getQuantity());
		return productRepository.save(existingProduct);
	}

	@Override
	public void delete(Long id) {
		if (productRepository.findById(id).isPresent()) {
			productRepository.deleteById(id);
		}
	}

    @Override
    public Page<Product> getProductsByCategory(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findByCategory("category", pageable);
    }

    @Override
    public Page<Product> search(String name, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findByNameContaining(name, pageable);
    }

}

