package com.web.sj.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.web.sj.domain.Category;
import com.web.sj.domain.Gemstone;
import com.web.sj.domain.Gender;
import com.web.sj.domain.Image;
import com.web.sj.domain.Material;
import com.web.sj.domain.Product;
import com.web.sj.domain.Type;
import com.web.sj.repository.IFilterProductRepository;

@Repository
public class FilterProductRepository implements IFilterProductRepository {
	
	public NamedParameterJdbcTemplate namedParameter;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.namedParameter = new NamedParameterJdbcTemplate(dataSource);
	}
	
	private static final class ProductMapper implements RowMapper<Product> {
		public Product mapRow(ResultSet rs, int rowNum) throws SQLException{
			Product product = new Product();
			Material material = new Material();
			Category category = new Category();
			Gemstone gemstone = new Gemstone();
			Gender gender = new Gender();
			Type type = new Type();
			Image image = new Image();
			product.setProductId(rs.getInt("productId"));
			product.setProductArticleId(rs.getString("productArticleId"));
			product.setProductName(rs.getString("productName"));
			product.setProductDescription(rs.getString("productDescription"));
			product.setProductWeight(rs.getFloat("productWeight"));
			product.setProductPrice(rs.getInt("productPrice"));
			product.setProductQuantity(rs.getInt("productQuantity"));
			
			material.setMaterialName(rs.getString("productMaterial"));
			category.setCategoryName(rs.getString("productCategory"));
			gemstone.setGemstoneName(rs.getString("productGemstone"));
			gender.setGenderName(rs.getString("productGender"));
			type.setTypeName(rs.getString("productType"));
			image.setLink(rs.getString("link"));
						
			
			product.setMaterial(material);
			product.setCategory(category);
			product.setGemstone(gemstone);
			product.setGender(gender);
			product.setType(type);
			product.setImage(image);
			
			return product;
		}
	}
	
	private static final class AvailableQuantityMapper implements RowMapper<Product> {
		public Product mapRow(ResultSet rs, int rowNum) throws SQLException{
			Product product = new Product();
			product.setProductQuantity(rs.getInt("productQuantity"));
			return product;
		}
	}
	

	public List<Product> getProductsByParameter(String productCategory, List<Integer> productGemstones, 
												List<Integer> productType, List<Integer> productMaterial,
												Integer productMinPrice, Integer productMaxPrice,
												List<Integer> size, List<Integer> productGender) {
		
		MapSqlParameterSource paramSource = new MapSqlParameterSource(); 
		paramSource.addValue("productCategory", productCategory);
		paramSource.addValue("productGemstone", productGemstones);
		paramSource.addValue("productType", productType);
		paramSource.addValue("productMaterial", productMaterial);
		paramSource.addValue("productMinPrice", productMinPrice);
		paramSource.addValue("productMaxPrice", productMaxPrice);
		paramSource.addValue("size", size);
		paramSource.addValue("productGender", productGender);
		
		String baseQuery = "SELECT p.productId, "
				+ "			 p.productArticleId, "
				+ "			 p.productName, "
				+ "			 p.productDescription, "
				+ "			 pc.categoryName AS productCategory, "
				+ "			 pt.typeName AS productType, "
				+ "			 p.productPrice, "
				+ "			 pg.gemstoneName AS productGemstone, "
				+ "			 pm.materialName AS productMaterial, "
				+ "			 p.productWeight,"
				+ "			 pi.link,"
				+ "			 pgg.genderName AS productGender,"
				+ "			 p.productQuantity "
				+ "	 FROM product p INNER JOIN productcategory pc ON pc.categoryId = p.productCategory"
				+ "					INNER JOIN productgemstone pg ON pg.gemstoneId = p.productGemstone"
				+ "					INNER JOIN producttype pt ON pt.typeId = p.productType"
				+ "					INNER JOIN productmaterial pm ON pm.materialId = p.productMaterial"
				+ "					INNER JOIN product_images pi ON pi.productId = p.productId"
				+ "					INNER JOIN productgender pgg ON pgg.genderId = p.productGender";
		List<String> conditions = new ArrayList<String>();
		
		if (productCategory != null) {
			conditions.add(" pc.categoryLink = :productCategory AND pi.imageIdentificationInDB ='1' ");
		}
		
		if (productGemstones != null) {
			conditions.add(" pg.gemstoneId IN (:productGemstone) ");				
		}
		
		if (productType != null) {
			conditions.add(" pt.typeId IN (:productType) ");
		}
		
		if (productMaterial != null) {
			conditions.add(" pm.materialId IN (:productMaterial) ");
		}
		
		if (productMinPrice != null && productMaxPrice == null) {
			conditions.add(" p.productPrice >= :productMinPrice ");
		}
		
		if (productMinPrice == null && productMaxPrice != null) {
			conditions.add(" p.productPrice <= :productMaxPrice ");
		}
		
		if (productMinPrice != null && productMaxPrice != null) {
			conditions.add(" p.productPrice between :productMinPrice AND :productMaxPrice ");
		}
		
		if (productGender != null ) {
			conditions.add(" pgg.genderId IN (:productGender) ");
		}
		
		if (size != null) {
			if (productCategory.equals("ring")) {
				conditions.add(" p.productId IN (SELECT prs.productId FROM product_ringsize prs WHERE prs.ringSizeId IN (:size) ) ");
			}
			
			if (productCategory.equals("chains")) {
				conditions.add(" p.productId IN (SELECT pcs.productId FROM product_chainssize pcs WHERE pcs.chainsSizeId IN (:size) ) ");
			}
			
			if (productCategory.equals("pendants")) {
				conditions.add(" p.productId IN (SELECT pps.productId FROM product_pendantsize pps WHERE pps.pendantSizeId IN (:size) ) ");
			}
		}
		
		if (!conditions.isEmpty()) {
			baseQuery+= " WHERE ";
			String sql_and = " AND ";
			String orderBy = " ORDER BY productQuantity DESC ";
			baseQuery+= String.join(sql_and, conditions);
			baseQuery+= orderBy;
		}
		
		return namedParameter.query(baseQuery, paramSource, new ProductMapper());
	}


	@Override
	public List<Product> getAvailableQuantityProducts(String productCategory, List<Integer> productGemstones,
			List<Integer> productType, List<Integer> productMaterial, Integer productMinPrice, Integer productMaxPrice,
			List<Integer> size, List<Integer> productGender) {
		
		MapSqlParameterSource paramSource = new MapSqlParameterSource(); 
		paramSource.addValue("productCategory", productCategory);
		paramSource.addValue("productGemstone", productGemstones);
		paramSource.addValue("productType", productType);
		paramSource.addValue("productMaterial", productMaterial);
		paramSource.addValue("productMinPrice", productMinPrice);
		paramSource.addValue("productMaxPrice", productMaxPrice);
		paramSource.addValue("size", size);
		paramSource.addValue("productGender", productGender);
		
		String baseQuery = "SELECT COUNT( * ) AS productQuantity "
				+ "	 FROM product p INNER JOIN productcategory pc ON pc.categoryId = p.productCategory"
				+ "					INNER JOIN productgemstone pg ON pg.gemstoneId = p.productGemstone"
				+ "					INNER JOIN producttype pt ON pt.typeId = p.productType"
				+ "					INNER JOIN productmaterial pm ON pm.materialId = p.productMaterial"
				+ "					INNER JOIN product_images pi ON pi.productId = p.productId"
				+ "					INNER JOIN productgender pgg ON pgg.genderId = p.productGender";
		List<String> conditions = new ArrayList<String>();
		
		if (productCategory != null) {
			conditions.add(" pc.categoryLink = :productCategory AND pi.imageIdentificationInDB ='1' ");
		}
		
		if (productGemstones != null) {
			conditions.add(" pg.gemstoneId IN (:productGemstone) ");				
		}
		
		if (productType != null) {
			conditions.add(" pt.typeId IN (:productType) ");
		}
		
		if (productMaterial != null) {
			conditions.add(" pm.materialId IN (:productMaterial) ");
		}
		
		if (productMinPrice != null && productMaxPrice == null) {
			conditions.add(" p.productPrice >= :productMinPrice ");
		}
		
		if (productMinPrice == null && productMaxPrice != null) {
			conditions.add(" p.productPrice <= :productMaxPrice ");
		}
		
		if (productMinPrice != null && productMaxPrice != null) {
			conditions.add(" p.productPrice between :productMinPrice AND :productMaxPrice ");
		}
		
		if (productGender != null ) {
			conditions.add(" pgg.genderId IN (:productGender) ");
		}
		
		if (size != null) {
			if (productCategory.equals("ring")) {
				conditions.add(" p.productId IN (SELECT prs.productId FROM product_ringsize prs WHERE prs.ringSizeId IN (:size) ) ");
			}
			
			if (productCategory.equals("chains")) {
				conditions.add(" p.productId IN (SELECT pcs.productId FROM product_chainssize pcs WHERE pcs.chainsSizeId IN (:size) ) ");
			}
			
			if (productCategory.equals("pendants")) {
				conditions.add(" p.productId IN (SELECT pps.productId FROM product_pendantsize pps WHERE pps.pendantSizeId IN (:size) ) ");
			}
		}
		
		if (!conditions.isEmpty()) {
			baseQuery+= " WHERE ";
			String sql_and = " AND ";
			baseQuery+= String.join(sql_and, conditions);
		}
		
		return namedParameter.query(baseQuery, paramSource, new AvailableQuantityMapper());
	}

}
