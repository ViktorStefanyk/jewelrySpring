package com.web.sj.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.sj.domain.Category;
import com.web.sj.domain.Gemstone;
import com.web.sj.domain.Gender;
import com.web.sj.domain.Image;
import com.web.sj.domain.JewelrySize;
import com.web.sj.domain.Material;
import com.web.sj.domain.Product;
import com.web.sj.domain.Type;
import com.web.sj.repository.ICategoryRepository;
import com.web.sj.repository.IGemstoneRepository;
import com.web.sj.repository.IGenderRepository;
import com.web.sj.repository.IImageRepository;
import com.web.sj.repository.IMaterialRepository;
import com.web.sj.repository.IProductRepository;
import com.web.sj.repository.ISizeRepository;
import com.web.sj.repository.ITypeRepository;
import com.web.sj.service.IProductService;

@Service
public class ProductService implements IProductService {
	
	@Autowired public IProductRepository productRepository;
	@Autowired public ICategoryRepository categoryRepository;
	@Autowired public IGemstoneRepository gemstoneRepository;
	@Autowired public IGenderRepository genderRepository;
	@Autowired public IMaterialRepository materialRepository;
	@Autowired public ITypeRepository typeRepository;
	@Autowired public ISizeRepository sizeRepository;
	@Autowired public IImageRepository imageRepository;

	public List<Product> getAllProducts() {
		return productRepository.getAllProducts();
	}
	
	public void insertNewProduct(Product product) {
		int productQuantity = 0;
		productRepository.insertNewProduct(product);
		if (product.getCategory().getCategoryId() != 2) {
			for(JewelrySize jewelrySize : product.getProductSizes()) {
				if(jewelrySize != null) {
					sizeRepository.inserSizeByCategory(product.getProductId(), product.getCategory().getCategoryId(), jewelrySize.getSizeId());
					productQuantity++;
				}
			}
		} else {
			productQuantity = 1;			
		}
		product.setProductQuantity(productQuantity);
		productRepository.updateProduct(product);
	}
	
	public void updateProduct(Product product) {
		int productQuantity = 0;
		if(product.getProductSizes() != null) {
			sizeRepository.deleteSizesByProductId(product.getProductId(), product.getCategory().getCategoryId().toString());
		}
		
		if(product.getCategory().getCategoryId() != 2) {
			for(JewelrySize jewelrySize : product.getProductSizes()) {
				if (jewelrySize != null) {
					sizeRepository.inserSizeByCategory(product.getProductId(), product.getCategory().getCategoryId(), jewelrySize.getSizeId());
					productQuantity++;
				}	
			}
		} else {
			productQuantity=1;
		}
		product.setProductQuantity(productQuantity);
		productRepository.updateProduct(product);
	}

	public void deleteProductByProductId(Integer productId) {
		Product product = new Product();
		product = productRepository.getDetailsOfProductByProductId(productId);
		if (!product.getCategory().getCategoryName().equals("Серьги")) {
			sizeRepository.deleteSizesByProductId(productId, product.getCategory().getCategoryName());
		}
		productRepository.deleteProduct(productId);
	}

	public List<Category> getAllCategories() {
		return categoryRepository.getCategories();
	}

	public List<Gemstone> getAllGemstones() {
		return gemstoneRepository.getGemstones();
	}

	public List<Gender> getAllGenders() {
		return genderRepository.getGenders();
	}

	public List<Material> getAllMaterial() {
		return materialRepository.getMaterial();
	}

	public List<Type> getAllType() {
		return typeRepository.getTypes();
	}

	public List<JewelrySize> getSizesByCategory(String productCategory) {
		return sizeRepository.getAllSizesByCategory(productCategory);
	}
	
	public void addMainImage(Integer productId, String id, String link, String deletehash, String imageIdentificationInDB) {
		imageRepository.addMainImage(productId, id, link, deletehash, imageIdentificationInDB);
	}
	
	public List<JewelrySize> getSizesByProductId(Integer productId) {
		Product product = new Product();
		product = productRepository.getDetailsOfProductByProductId(productId);
		String category = product.getCategory().getCategoryName();
		List<JewelrySize> jewelrySize = new ArrayList<JewelrySize>();
		if (!category.equals("Серьги")) {
			jewelrySize = sizeRepository.getAllSizesByProductIdAndCategory(productId, category);
		}
		return jewelrySize;
	}

	public Product getDetailsOfProductByProductId(Integer productId) {
		return productRepository.getDetailsOfProductByProductId(productId);
	}

	public List<Image> getAllImagesByProductId(Integer productId) {
		return imageRepository.getAllImagesByProductId(productId);
	}
	
	public Product getProductByProductIdForUpdate(Integer productId) {
		return productRepository.getProductByProductIdForUpdate(productId);
	}

}
