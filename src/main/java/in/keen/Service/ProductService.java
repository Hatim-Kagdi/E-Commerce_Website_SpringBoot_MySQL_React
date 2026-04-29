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
	public ProductDTO addProduct(ProductDTO productdto) {
		
		Product product = ProductMapper.mapToProduct(productdto);
		
		Category category = categoryRepository.findById(productdto.getCategory().getCategoryId())
				.orElseThrow(() -> new RuntimeException("Category Not FOund!"));
		
		User vendor = userRepository.findById(productdto.getVendor().getUserId())
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
	public ProductDTO updateProduct(ProductDTO newProductDTO, int productId) {
		Optional<Product> productOpt = productRepository.findById(productId);
		if(productOpt.isEmpty()) {
			throw new RuntimeException("Product not found!");
		}
		
		Product existingProduct = productOpt.get();
		existingProduct.setProductName(newProductDTO.getProductName());
		existingProduct.setProductDescription(newProductDTO.getProductDescription());
		existingProduct.setProductPrice(newProductDTO.getProductPrice());
		existingProduct.setProductStock(newProductDTO.getProductStock());
		
		if (newProductDTO.getProductImageUrl() != null && !newProductDTO.getProductImageUrl().isEmpty()) {
		    existingProduct.setProductImageUrl(newProductDTO.getProductImageUrl());
		}
		
		if(newProductDTO.getVendor() != null) {
			User actualVendor = userRepository.findById(newProductDTO.getVendor().getUserId())
					.orElseThrow(() -> new RuntimeException("User not Found!"));
			existingProduct.setVendor(actualVendor);
		}
		
		if(newProductDTO.getCategory() != null) {
			Category actualCategory = categoryRepository.findById(newProductDTO.getCategory().getCategoryId())
					.orElseThrow(() -> new RuntimeException("Category Not FOund!"));
		existingProduct.setCategory(actualCategory);
		}
		
		Product product = productRepository.save(existingProduct);
		return ProductMapper.mapToProductDTO(product);
	}
	
	//ADMIN MODULE
	public List<ProductDTO> getVendorProductDetails(int userId) {
		List<Product> list = productRepository.findByVendorUserId(userId);
		return list.stream()
				.map((productItem) -> ProductMapper.mapToProductDTO(productItem))
				.collect(Collectors.toList());
	}
}
