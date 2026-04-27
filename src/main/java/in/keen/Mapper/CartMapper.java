package in.keen.Mapper;

import in.keen.DTO.CartDTO;
import in.keen.Entity.Cart;

public class CartMapper {
	public static CartDTO mapToCartDTO(Cart cart) {
		CartDTO dto = new CartDTO();
		dto.setCartId(cart.getCartId());
		dto.setProductQuantity(cart.getProductQuantity());
		dto.setProductTotal(cart.getProductQuantity() * cart.getProduct().getProductPrice());
		dto.setProductId(cart.getProduct().getProductId());
		dto.setProductName(cart.getProduct().getProductName());
		dto.setProductPrice(cart.getProduct().getProductPrice());
		dto.setProductStock(cart.getProduct().getProductStock());
		return dto;
	}
}
