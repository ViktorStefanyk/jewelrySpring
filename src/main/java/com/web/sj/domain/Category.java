package com.web.sj.domain;

public class Category {
	
	public Integer categoryId;
	public Integer oldCategoryId;
	public String categoryName;
	public String categoryLink;
	
	public Category() {
		super();
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getCategoryLink() {
		return categoryLink;
	}
	public void setCategoryLink(String categoryLink) {
		this.categoryLink = categoryLink;
	}
	
	public Integer getOldCategoryId() {
		return oldCategoryId;
	}
	public void setOldCategoryId(Integer oldCategoryId) {
		this.oldCategoryId = oldCategoryId;
	}
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categoryId == null) ? 0 : categoryId.hashCode());
		return result;
	}
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		if (categoryId == null) {
			if (other.categoryId != null)
				return false;
		} else if (!categoryId.equals(other.categoryId))
			return false;
		return true;
	}
	

}
