package in.keen.DTO;

import java.time.LocalDateTime;

public class CategoryDTO {
	
	private int categoryId;
	private String categoryDescription;
	private String categoryName;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	
	public CategoryDTO(int categoryId, String categoryDescription, String categoryName, LocalDateTime createdAt,
			LocalDateTime updatedAt) {
		super();
		this.categoryId = categoryId;
		this.categoryDescription = categoryDescription;
		this.categoryName = categoryName;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}


	public int getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}


	public String getCategoryDescription() {
		return categoryDescription;
	}


	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}


	public String getCategoryName() {
		return categoryName;
	}


	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}


	public LocalDateTime getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}


	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}


	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public CategoryDTO() {
	}
}
