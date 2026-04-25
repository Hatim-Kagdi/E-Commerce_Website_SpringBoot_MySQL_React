package in.keen.Mapper;

import in.keen.DTO.ProductDTO;
import in.keen.Entity.Product;

public class ProductMapper {
	public static ProductDTO mapToProductDTO(Product product) {
		return new ProductDTO(
				product.getProductId(),
				product.getProductName(),
				product.getProductDescription(),
				product.getProductPrice(),
				product.getProductStock(),
				product.getProductImageUrl(),
				product.getCreatedAt(),
				product.getUpdatedAt(),
				product.getCategory().getCategoryName(),
				product.getVendor().getUserName()
		);
	}
	
	public static Product mapToProduct(ProductDTO dto) {
	    Product product = new Product();
	    product.setProductName(dto.getProductName());
	    product.setProductDescription(dto.getProductDescription());
	    product.setProductPrice(dto.getProductPrice());
	    product.setProductStock(dto.getProductStock());
	    product.setProductImageUrl(dto.getProductImageUrl());
	    // IDs are handled in the Service layer after this call
	    return product;
	}
}
