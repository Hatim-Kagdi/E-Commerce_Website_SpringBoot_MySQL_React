package in.keen.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.keen.DTO.CustomerDTO;
import in.keen.Entity.Product;
import in.keen.Mapper.CustomerMapper;
import in.keen.Repository.ProductRepository;

@Service
public class CustomerService {
	
	@Autowired
	private ProductRepository productRepository;

	public List<CustomerDTO> getAllProductsForCustomer() {
		List<Product> list = productRepository.findAll();
		return list.stream()
				.map((product) -> CustomerMapper.mapToCustomerDTO(product))
				.collect(Collectors.toList());
	}
}
