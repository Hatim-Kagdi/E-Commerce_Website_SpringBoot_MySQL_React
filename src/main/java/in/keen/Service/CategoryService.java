package in.keen.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.keen.DTO.CategoryDTO;
import in.keen.Entity.Category;
import in.keen.Mapper.CategoryMapper;
import in.keen.Repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Transactional
	public CategoryDTO addCategory(Category category) {
		return CategoryMapper.mapToCategoryDTO(categoryRepository.save(category));
	}

	public List<CategoryDTO> getAllCategory() {
		List<Category> list = categoryRepository.findAll();
		return list.stream()
				.map((category) -> CategoryMapper.mapToCategoryDTO(category))
				.collect(Collectors.toList());
	}

	public CategoryDTO getCategoryById(int categoryId) {
			Optional<Category> categoryOpt = categoryRepository.findById(categoryId);
			return CategoryMapper.mapToCategoryDTO(categoryOpt.get());
	}

	@Transactional
	public boolean deleteCategory(int categoryId) {
		Optional<Category> categoryOpt = categoryRepository.findById(categoryId);
		if(categoryOpt.isPresent()) {
			Category category = categoryOpt.get();
			categoryRepository.delete(category);
			return true;
		}
		return false;
	}
	
	@Transactional
	public CategoryDTO updateCategory(Category newCategory, int categoryId) {
		Optional<Category> categoryOpt = categoryRepository.findById(categoryId);
		
		if(categoryOpt.isEmpty()) {
			throw new RuntimeException("Category Not Found!");
		}
		
		Category existingCategory = categoryOpt.get();
		existingCategory.setCategoryName(newCategory.getCategoryName());
		existingCategory.setCategoryDescription(newCategory.getCategoryDescription());
		
		Category category = categoryRepository.save(existingCategory);
		
		return CategoryMapper.mapToCategoryDTO(category);
	}
}
