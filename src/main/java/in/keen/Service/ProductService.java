package in.keen.Service;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.keen.DTO.ProductDTO;
import in.keen.Entity.*;
import in.keen.Mapper.ProductMapper;
import in.keen.Repository.*;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Transactional
	public ProductDTO addProduct(Product product) {
		Category category = categoryRepository.findById(product.getCategory().getCategoryId())
				.orElseThrow(() -> new RuntimeException("Category Not FOund!"));
		
		User vendor = userRepository.findById(product.getVendor().getUserId())
				.orElseThrow(() -> new RuntimeException("Vendor Not FOund!"));
		
		product.setCategory(category);
		product.setVendor(vendor);
		
		Product productSaved = productRepository.save(product);
		return ProductMapper.mapToProductDTO(productSaved);
	}

	public List<ProductDTO> getProductsByVendor(String email) {
		List<Product> list = productRepository.findByVendorUserEmail(email);
		return list.stream()
				.map((products) -> ProductMapper.mapToProductDTO(products))
				.collect(Collectors.toList());
	}

	public ProductDTO getProductById(int productId) {
		Optional<Product> productOpt = productRepository.findById(productId);
		if(productOpt.isEmpty()) {
			throw new RuntimeException("Product Not Found!");
		}
		return ProductMapper.mapToProductDTO(productOpt.get());
	}
	
	@Transactional
	public boolean deleteProduct(int productId) {
		Optional<Product> productOpt = productRepository.findById(productId);
		if(productOpt.isEmpty()) {
			throw new RuntimeException("Product Not Found!");
		}
		productRepository.delete(productOpt.get());
		return true;
	}
	
	@Transactional
	public ProductDTO updateProduct(Product newProduct, int productId) {
		Optional<Product> productOpt = productRepository.findById(productId);
		if(productOpt.isEmpty()) {
			throw new RuntimeException("Product not found!");
		}
		
		Product existingProduct = productOpt.get();
		existingProduct.setProductName(newProduct.getProductName());
		existingProduct.setProductDescription(newProduct.getProductDescription());
		existingProduct.setProductPrice(newProduct.getProductPrice());
		existingProduct.setProductStock(newProduct.getProductStock());
		existingProduct.setProductImageUrl(newProduct.getProductImageUrl());
		
		if(newProduct.getVendor() != null) {
			User actualVendor = userRepository.findById(newProduct.getVendor().getUserId())
					.orElseThrow(() -> new RuntimeException("User not Found!"));
			existingProduct.setVendor(actualVendor);
		}
		
		if(newProduct.getCategory() != null) {
			Category actualCategory = categoryRepository.findById(newProduct.getCategory().getCategoryId())
					.orElseThrow(() -> new RuntimeException("Category Not FOund!"));
		existingProduct.setCategory(actualCategory);
		}
		
		Product product = productRepository.save(existingProduct);
		return ProductMapper.mapToProductDTO(product);
	}
}
