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
import com.web.sj.repository.ISearchRepository;

@Repository
public class SearchRepository implements ISearchRepository {
	
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
			
			material.setMaterialName(rs.getString("productMaterial"));
			category.setCategoryName(rs.getString("productCategory"));
			gemstone.setGemstoneName(rs.getString("productGemstone"));
			gender.setGenderName(rs.getString("productGender"));
			type.setTypeName(rs.getString("productType"));
			image.setLink(rs.getString("productImageName"));
						
			
			product.setMaterial(material);
			product.setCategory(category);
			product.setGemstone(gemstone);
			product.setGender(gender);
			product.setType(type);
			product.setImage(image);
			
			return product;
		}
	}

	public List<Product> fullSearch(String searchField) {
		
		MapSqlParameterSource paramSource = new MapSqlParameterSource(); 
		paramSource.addValue("searchField", searchField);
		
		String sql = "SELECT p.productId,"
				+ "			 p.productArticleId, "
				+ "			 p.productName, "
				+ "			 p.productDescription, "
				+ "			 pc.categoryName AS productCategory, "
				+ "			 pt.typeName AS productType, "
				+ "			 p.productPrice, "
				+ "			 pg.gemstoneName AS productGemstone, "
				+ "			 pm.materialName AS productMaterial, "
				+ "			 p.productWeight,"
				+ "			 pi.imageName AS productImageName "
				+ "	 FROM product p INNER JOIN productcategory pc ON pc.categoryId = p.productCategory"
				+ "					INNER JOIN productgemstone pg ON pg.gemstoneId = p.productGemstone"
				+ "					INNER JOIN producttype pt ON pt.typeId = p.productType"
				+ "					INNER JOIN productmaterial pm ON pm.materialId = p.productMaterial"
				+ "					INNER JOIN product_images pi ON pi.productId = p.productId"
				+ "	 WHERE pi.imageRole ='main' ";
		List<String> conditions = new ArrayList<String>();
		
		if(searchField != null) {
			conditions.add(" productName LIKE '%:searchField%' ") ;
		}
		
		if(!conditions.isEmpty()) {
			sql+= String.join(" AND ", conditions);
		}
		
		return namedParameter.query(sql, paramSource, new ProductMapper());
	}

}
