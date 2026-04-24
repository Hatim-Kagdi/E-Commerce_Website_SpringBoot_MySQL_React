package in.keen.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import in.keen.DTO.ProductDTO;
import in.keen.Entity.Product;
import in.keen.Service.ProductService;

@RestController
@RequestMapping("/vendor")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@PostMapping("/product")
	public ResponseEntity<ProductDTO> addProduct(@RequestBody Product product) {
		return ResponseEntity.ok((productService.addProduct(product)));
	}
	
	@GetMapping("/products")
	public ResponseEntity<List<ProductDTO>> getVendorProducts(Authentication auth){
		String email = auth.getName();
		List<ProductDTO> list = productService.getProductsByVendor(email);
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/product/{productId}")
	public ResponseEntity<?> getProductById(@PathVariable int productId){
		ProductDTO productdto = productService.getProductById(productId);
		return ResponseEntity.ok(productdto);
	}
	
	@PutMapping("/product/{productId}")
	public ResponseEntity<?> updateProduct(@RequestBody Product newProduct,
			@PathVariable int productId){
		ProductDTO updatedProduct = productService.updateProduct(newProduct, productId);
		return ResponseEntity.ok(updatedProduct);
	}
	
	@DeleteMapping("/product/{productId}")
	public ResponseEntity<?> deleteProduct(@PathVariable int productId){
		boolean delete = productService.deleteProduct(productId);
		if(delete) {
			return ResponseEntity.ok(Map.of("message","Product Deleted!"));
		}else {
			return ResponseEntity.status(404).body(Map.of("message","Product Not Deleted!"));
		}
	}
}
