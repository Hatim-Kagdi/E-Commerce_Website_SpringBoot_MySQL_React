package in.keen.Mapper;

import in.keen.DTO.OrderDTO;
import in.keen.Entity.Order;

public class OrderMapper {
	public static OrderDTO mapToCustomerOrderDTO(Order order) {
		OrderDTO dto = new OrderDTO();
		
		dto.setOrderId(order.getOrderId());
		dto.setProductName(order.getProduct().getProductName());
		dto.setProductPrice(order.getAmount());
		dto.setUserName(order.getVendor().getUserName());
		dto.setUserEmail(order.getVendor().getUserEmail());
		dto.setUserAddress(order.getVendor().getUserAddress());
		dto.setUserMobileNumber(order.getVendor().getUserMobileNumber());
		dto.setQuantity(order.getQuantity());
		dto.setOrderStatus(order.getStatus());
		dto.setOrderTotal(order.getTotal());
		dto.setOrderDate(order.getCreatedAt());
		
		return dto;
	}
	
	public static OrderDTO mapToVendorOrderDTO(Order order) {
		OrderDTO dto = new OrderDTO();
		
		dto.setOrderId(order.getOrderId());
		dto.setProductName(order.getProduct().getProductName());
		dto.setProductPrice(order.getAmount());
		dto.setUserName(order.getCustomer().getUserName());
		dto.setUserEmail(order.getCustomer().getUserEmail());
		dto.setUserAddress(order.getCustomer().getUserAddress());
		dto.setUserMobileNumber(order.getCustomer().getUserMobileNumber());
		dto.setQuantity(order.getQuantity());
		dto.setOrderStatus(order.getStatus());
		dto.setOrderTotal(order.getTotal());
		dto.setOrderDate(order.getCreatedAt());
		
		return dto;
	}
}
