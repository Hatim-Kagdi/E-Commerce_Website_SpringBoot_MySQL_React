package in.keen.Controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import in.keen.DTO.CategoryDTO;
import in.keen.Entity.Category;
import in.keen.Service.CategoryService;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/category")
	public ResponseEntity<CategoryDTO> addCategory(@RequestBody Category category) {
		System.out.println("Category received : " + category.getCategoryName());
		 CategoryDTO categorySaved = categoryService.addCategory(category);
		 return ResponseEntity.ok(categorySaved);
	}
	
	@GetMapping("/categories")
	public ResponseEntity<List<CategoryDTO>> getAllCategory(){
		return ResponseEntity.ok(categoryService.getAllCategory());
	}
	
	@GetMapping("/category/{categoryId}")
	public ResponseEntity<?> getCategoryById(@PathVariable int categoryId){
		CategoryDTO categorydto = categoryService.getCategoryById(categoryId);
		if(categorydto != null) {
			return ResponseEntity.ok(categorydto);
		}else {
			return ResponseEntity.status(404).body("Category Not Found!");
		}
	}
	
	@PutMapping("/category/{categoryId}")
	public ResponseEntity<?> updateCategory(@RequestBody Category newCategory, 
			@PathVariable int categoryId){
		CategoryDTO category = categoryService.updateCategory(newCategory, categoryId);
		if(category != null) {
			return ResponseEntity.ok(category);
		}else {
			return ResponseEntity.status(400).body(Map.of("message" , "Update Failed!"));
		}
	}
	
	@DeleteMapping("category/{categoryId}")
	public ResponseEntity<?> deleteCategory(@PathVariable int categoryId){
		boolean delete = categoryService.deleteCategory(categoryId);
		if(delete) {
			return ResponseEntity.ok(Map.of("message" , "Category Deleted"));
		}else {
			return ResponseEntity.status(404).body(Map.of("message" , "Category Not Deleted"));
		}
	}
}
