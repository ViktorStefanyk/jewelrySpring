package com.web.sj.domain;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.http.entity.mime.FormBodyPart;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {
	
	public Integer productId;
	
	
	public String productArticleId;
	
	@Size(min=2, max=32, message="{Size.Product.productName.validation}")
	@NotNull(message= "{NotNull.Product.productName.validation}")
	public String productName;
	
	
	@Size(min=2, max=300, message="{Size.Product.productDescription.validation}")
	@NotNull(message= "{NotNull.Product.productDescription.validation}")
	public String productDescription;
		
	@Min(value=0, message="{Min.Product.productPrice.validation}")
	@NotNull(message= "{NotNull.Product.productPrice.validation}")
	public Integer productPrice;
	
	@Min(value=0, message="{Min.Product.productWeight.validation}")
	@NotNull(message= "{NotNull.Product.productWeight.validation}")
	public Float productWeight;
	
	public Integer productMinPrice;
	public Integer productMaxPrice;
//	@JsonIgnore
	public MultipartFile productMainImage;
//	@JsonIgnore
	public List<MultipartFile> productAdditionalImages;
	
	@JsonIgnore
	public MultipartFile productImage;
	
	public Integer productQuantity;
	
	
	public List<JewelrySize> productSizes;
	public JewelrySize productSize;
	public Material material;
	public Gender gender;
	public Gemstone gemstone;
	public Category category;
	public Type type;
	public Image image;
	
	
	public Product() {
		super();
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public Integer getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Integer productPrice) {
		this.productPrice = productPrice;
	}

	public Float getProductWeight() {
		return productWeight;
	}

	public void setProductWeight(Float productWeight) {
		this.productWeight = productWeight;
	}


	public MultipartFile getProductMainImage() {
		return productMainImage;
	}

	public void setProductMainImage(MultipartFile productMainImage) {
		this.productMainImage = productMainImage;
	}

	public List<MultipartFile> getProductAdditionalImages() {
		return productAdditionalImages;
	}

	public void setProductAdditionalImages(List<MultipartFile> productAdditionalImages) {
		this.productAdditionalImages = productAdditionalImages;
	}
	
	public Integer getProductMinPrice() {
		return productMinPrice;
	}

	public void setProductMinPrice(Integer productMinPrice) {
		this.productMinPrice = productMinPrice;
	}

	public Integer getProductMaxPrice() {
		return productMaxPrice;
	}

	public void setProductMaxPrice(Integer productMaxPrice) {
		this.productMaxPrice = productMaxPrice;
	}

	public String getProductArticleId() {
		return productArticleId;
	}

	public void setProductArticleId(String productArticleId) {
		this.productArticleId = productArticleId;
	}

	public List<JewelrySize> getProductSizes() {
		return productSizes;
	}

	public void setProductSizes(List<JewelrySize> productSizes) {
		this.productSizes = productSizes;
	}

	public Integer getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(Integer productQuantity) {
		this.productQuantity = productQuantity;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Gemstone getGemstone() {
		return gemstone;
	}

	public void setGemstone(Gemstone gemstone) {
		this.gemstone = gemstone;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public JewelrySize getProductSize() {
		return productSize;
	}

	public void setProductSize(JewelrySize productSize) {
		this.productSize = productSize;
	}

	public MultipartFile getProductImage() {
		return productImage;
	}

	public void setProductImage(MultipartFile productImage) {
		this.productImage = productImage;
	}

	
}
