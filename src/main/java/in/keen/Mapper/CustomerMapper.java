package in.keen.Mapper;

import in.keen.DTO.CustomerDTO;
import in.keen.Entity.Product;

public class CustomerMapper {
	public static CustomerDTO mapToCustomerDTO(Product product) {
		return new CustomerDTO(
				product.getProductId(),
				product.getProductName(),
				product.getProductDescription(),
				product.getProductPrice(),
				product.getProductStock(),
				product.getProductImageUrl(),
				product.getCategory().getCategoryName(),
				product.getVendor().getUserName()
		);
	}
}
