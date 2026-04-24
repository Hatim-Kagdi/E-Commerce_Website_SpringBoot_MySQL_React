package in.keen.Mapper;

import in.keen.DTO.CategoryDTO;
import in.keen.Entity.Category;

public class CategoryMapper {
	public static CategoryDTO mapToCategoryDTO(Category category) {
		return new CategoryDTO(
				category.getCategoryId(), 
				category.getCategoryDescription(),
				category.getCategoryName(),
				category.getCreatedAt(), 
				category.getUpdatedAt()
		);
	}
}
