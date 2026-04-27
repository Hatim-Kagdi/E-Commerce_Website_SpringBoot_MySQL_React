package in.keen.Controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import in.keen.DTO.CartDTO;
import in.keen.Entity.Cart;
import in.keen.Mapper.CartMapper;
import in.keen.Service.CartService;

@RestController
@RequestMapping("/cart")
@CrossOrigin("http://localhost:3000")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@PostMapping("/product")
	public ResponseEntity<?> addProductToCart(@RequestBody Cart cart){
		boolean add = cartService.addToCart(cart);
		if(add) {
			return ResponseEntity.ok(Map.of("message", "Product Added to Cart"));
		}else {
			return ResponseEntity.status(404).body(Map.of("message" , "Product Not Added to Cart!"));
		}
	}
	
	@GetMapping("/products/{userId}")
	public ResponseEntity<?> getCartItemsByUser(@PathVariable int userId){
		List<CartDTO> dto = cartService.getCartItemsByUser(userId);
		return ResponseEntity.ok(dto);
	}
	
	@PutMapping("/update/{cartId}/{productQuantity}")
	private ResponseEntity<?> updateCartItem(@PathVariable int cartId, @PathVariable int productQuantity){
		boolean update = cartService.updateCartItem(cartId, productQuantity);
		if(update) {
			return ResponseEntity.ok(Map.of("message","Cart item updated!"));
		}else {
			return ResponseEntity.status(404).body(Map.of("message", "Cart item not updated!"));
		}
	}
	
	@DeleteMapping("/remove/{cartId}")
	private ResponseEntity<?> deleteCartItem(@PathVariable int cartId){
		boolean delete = cartService.deleteCartItem(cartId);
		if(delete) {
			return ResponseEntity.ok(Map.of("message", "Cart item removed!"));
		}else {
			return ResponseEntity.status(404).body(Map.of("mesasge" , "Cart item not removed!"));
		}
	}
}
