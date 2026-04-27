package in.keen.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.keen.DTO.CartDTO;
import in.keen.Entity.Cart;
import in.keen.Entity.Product;
import in.keen.Entity.User;
import in.keen.Mapper.CartMapper;
import in.keen.Repository.*;

@Service
public class CartService {

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private UserRepository userRepository;

	@Transactional
	public boolean addToCart(Cart cart) {

		Product product = productRepository.findById(cart.getProduct().getProductId())
				.orElseThrow(() -> new RuntimeException("Product not found!"));

		if (product.getProductStock() < cart.getProductQuantity()) {
			throw new RuntimeException("No stock available!");
		}

		Optional<Cart> existingCartItem = cartRepository.findByCustomerUserIdAndProductProductId(cart.getCustomer().getUserId(), cart.getProduct().getProductId());

		if (existingCartItem.isPresent()) {
			Cart existingCart = existingCartItem.get();
			existingCart.setProductQuantity(existingCart.getProductQuantity() + cart.getProductQuantity());
			cartRepository.save(existingCart);
		} else {
			User user = userRepository.findById(cart.getCustomer().getUserId())
					.orElseThrow(() -> new RuntimeException("User not Found!"));

			Cart newCart = new Cart();
			newCart.setCustomer(user);
			newCart.setProduct(product);
			newCart.setProductQuantity(cart.getProductQuantity());
			cartRepository.save(newCart);
		}
		return true;
	}

	public List<CartDTO> getCartItemsByUser(int userId) {
		List<Cart> list = cartRepository.findByCustomerUserId(userId);
		return list.stream()
		.map((cart) -> CartMapper.mapToCartDTO(cart))
		.collect(Collectors.toList());
	}
	
	@Transactional
	public boolean updateCartItem(int cartId, int productQuantity) {
		Cart cart = cartRepository.findById(cartId)
				.orElseThrow(() -> new RuntimeException("Item not found!"));
		
		if(cart.getProduct().getProductStock() < productQuantity) {
			throw new RuntimeException("Insufficient Stock!");
		}
		
		cart.setProductQuantity(productQuantity);
		cartRepository.save(cart);
		return true;
	}
	
	@Transactional
	public boolean deleteCartItem(int cartId) {
		cartRepository.deleteById(cartId);
		return true;
	}
}
