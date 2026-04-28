package in.keen.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.Transactional;

import in.keen.DTO.OrderDTO;
import in.keen.Entity.*;
import in.keen.Mapper.OrderMapper;
import in.keen.Repository.*;

@Service
public class OrderService {
	
	@Autowired private OrderRepository orderRepository;
	@Autowired private CartRepository cartRepository;
	@Autowired private ProductRepository productRepository;
	
	//CUSTOMER MODULE
	@Transactional
	public void checkOut(int userId) {
		List<Cart> cartItems = cartRepository.findByCustomerUserId(userId);
		
		if(cartItems.isEmpty()) {
			throw new RuntimeException("Cart not found!");
		}
		
		for(Cart item : cartItems) {
			Product product = item.getProduct();
			
			if(product.getProductStock() < item.getProductQuantity()) {
				throw new RuntimeException("Not enough stock !");
			}
			
			Order order = new Order();
			order.setCustomer(item.getCustomer());
			order.setProduct(product);
			order.setVendor(product.getVendor());
			order.setQuantity(item.getProductQuantity());
			order.setAmount(product.getProductPrice());
			order.setTotal(item.getProductQuantity() * product.getProductPrice());
			order.setStatus("PLACED");
			orderRepository.save(order);
			
			product.setProductStock(product.getProductStock() - item.getProductQuantity());
			productRepository.save(product);
			
			cartRepository.delete(item);
		}
	}

	public List<OrderDTO> getPlacedOrder(int userId) {
		List<Order> list = orderRepository.findByCustomerUserId(userId);
			
		return list.stream()
				.map((orderItem) -> OrderMapper.mapToCustomerOrderDTO(orderItem))
				.collect(Collectors.toList());
	}
	
	@Transactional
	public void cancelOrder(int orderId) {
		Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("NOt found!"));
		
		Product product = productRepository.findById(order.getProduct().getProductId())
				.orElseThrow(() -> new RuntimeException("Not Found!"));
		product.setProductStock(product.getProductStock() + order.getQuantity());
		productRepository.save(product);
		
		order.setStatus("CANCELLED");
		orderRepository.save(order);
		orderRepository.deleteById(orderId);
	}
	
	//VENDOR MODULE
	public List<OrderDTO> getOrderForVendor(int userId) {
		List<Order> list = orderRepository.findByVendorUserId(userId);
		
		return list.stream()
				.map((orderItem) -> OrderMapper.mapToVendorOrderDTO(orderItem))
				.collect(Collectors.toList());
	}
	
	@Transactional
	public boolean shipOrder(int orderId) {
		Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Not Found!"));
		order.setStatus("SHIPPED");
		orderRepository.save(order);
		return true;
	}
}
