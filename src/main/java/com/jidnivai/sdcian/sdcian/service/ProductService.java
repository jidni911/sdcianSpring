package com.jidnivai.sdcian.sdcian.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jidnivai.sdcian.sdcian.dto.NewProductDto;
import com.jidnivai.sdcian.sdcian.dto.ProductDto;
import com.jidnivai.sdcian.sdcian.entity.Product;
import com.jidnivai.sdcian.sdcian.entity.User;
import com.jidnivai.sdcian.sdcian.interfaces.ProductServiceInt;
import com.jidnivai.sdcian.sdcian.repository.ProductRepository;
import com.jidnivai.sdcian.sdcian.security.services.UserDetailsImpl;

@Service
public class ProductService implements ProductServiceInt {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private FileService fileService;

	@Override
	public Page<ProductDto> getAll(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		// return productRepository.findAll(pageable);

		Page<Product> products = productRepository.findAll(pageable);
		return products.map(product -> {
			ProductDto productDto = new ProductDto();
			BeanUtils.copyProperties(product, productDto);
			productDto.setSeller(product.getSeller().toDto());
			return productDto;
		});
	}

	

	@Override
	public ProductDto getById(Long id) {
		Optional<Product> optionalProduct = productRepository.findById(id);
		if (optionalProduct.isEmpty()) {
			return null;
		} else {
			Product product = optionalProduct.get();
			ProductDto productDto = new ProductDto();
			BeanUtils.copyProperties(product, productDto);
			productDto.setSeller(product.getSeller().toDto());
			return productDto;
		}
	}

	@Override
	public ProductDto add(NewProductDto newProductDto, UserDetailsImpl user) {

		Product product = new Product();
		BeanUtils.copyProperties(newProductDto, product);

		product.setMainImage(fileService.getImage(newProductDto.getMainImageId()));
		product.setGalleryImages(fileService.getImages(newProductDto.getGalleryImagesId()));
		product.setSeller(userService.getUser(user.getId(), user.getId()));
		product.setAddedDate(LocalDateTime.now());
		product =  productRepository.save(product);

		ProductDto productDto = new ProductDto();
		BeanUtils.copyProperties(product, productDto);
		productDto.setSeller(product.getSeller().toDto());
		return productDto;

		// return productRepository.save(product);
	}

	@Override
	public ProductDto update(Long id, ProductDto productDto) {
		Product existingProduct = productRepository.findById(id).orElse(null);
		if (existingProduct == null) {
			return null;
		}

		BeanUtils.copyProperties(productDto, existingProduct);

		Product updatedProduct = productRepository.save(existingProduct);

		ProductDto updatedProductDto = new ProductDto();
		BeanUtils.copyProperties(updatedProduct, updatedProductDto);
		updatedProductDto.setSeller(updatedProduct.getSeller().toDto());
		return updatedProductDto;
	}

	@Override
	public void delete(Long id) {
		if (productRepository.findById(id).isPresent()) {
			productRepository.deleteById(id);
		}
	}

    @Override
    public Page<ProductDto> getProductsByCategory(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        // return productRepository.findByCategory("category", pageable);

		Page<Product> products = productRepository.findByCategory("category", pageable);
		return products.map(product -> {
			ProductDto productDto = new ProductDto();
			BeanUtils.copyProperties(product, productDto);
			productDto.setSeller(product.getSeller().toDto());
			return productDto;
		});
    }

    @Override
    public Page<ProductDto> search(String name, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        // return productRepository.findByNameContaining(name, pageable);

		Page<Product> products = productRepository.findByNameContaining(name, pageable);
		return products.map(product -> {
			ProductDto productDto = new ProductDto();
			BeanUtils.copyProperties(product, productDto);
			productDto.setSeller(product.getSeller().toDto());
			return productDto;
		});
    }



	@Override
	public Page<ProductDto> sellerSearch(String name, int page, int size, Long id) {
		User user = userService.getUser(id, id);
		Pageable pageable = PageRequest.of(page, size);
        // return productRepository.findByNameContaining(name, pageable);

		Page<Product> products = productRepository.findBySellerAndNameContaining(user,name, pageable);
		return products.map(product -> {
			ProductDto productDto = new ProductDto();
			BeanUtils.copyProperties(product, productDto);
			productDto.setSeller(product.getSeller().toDto());
			return productDto;
		});
	}

}

