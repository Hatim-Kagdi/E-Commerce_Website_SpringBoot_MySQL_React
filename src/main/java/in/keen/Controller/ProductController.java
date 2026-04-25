package in.keen.Controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import in.keen.DTO.ProductDTO;
import in.keen.Entity.Product;
import in.keen.Service.ProductService;



@RestController
@RequestMapping("/vendor")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@PostMapping(value = "/product", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<ProductDTO> addProduct(
			@RequestPart("product") String productJson,
			@RequestPart("image") MultipartFile imageFile)
	throws IOException {
		
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
		ProductDTO productdto = objectMapper.readValue(productJson, ProductDTO.class);
		
		String fileName = imageFile.getOriginalFilename();
		Path path = Paths.get("uploads/" + fileName);
		
		// StandardCopyOption.REPLACE_EXISTING prevents crashes on duplicate filenames
		Files.copy(imageFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
		
		productdto.setProductImageUrl(fileName);
		
		return ResponseEntity.ok((productService.addProduct(productdto)));
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
	
	@PutMapping(value ="/product/{productId}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<?> updateProduct(
			@RequestPart("product") String newProductJson,
			@RequestPart(value ="image", required = false) MultipartFile imageFile,
			@PathVariable int productId) throws IOException{
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
		ProductDTO dto = objectMapper.readValue(newProductJson , ProductDTO.class);
		
		if (imageFile != null && !imageFile.isEmpty()) {
	        String fileName = imageFile.getOriginalFilename();
	        Path path = Paths.get("uploads/" + fileName);
	        Files.copy(imageFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
	        dto.setProductImageUrl(fileName);
	    }
		
		ProductDTO updatedProduct = productService.updateProduct(dto, productId);
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
