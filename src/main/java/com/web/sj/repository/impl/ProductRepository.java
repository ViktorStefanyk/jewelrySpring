package com.web.sj.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.web.sj.domain.Category;
import com.web.sj.domain.Gemstone;
import com.web.sj.domain.Gender;
import com.web.sj.domain.Image;
import com.web.sj.domain.Material;
import com.web.sj.domain.Product;
import com.web.sj.domain.Type;
import com.web.sj.repository.IProductRepository;

@Repository
public class ProductRepository implements IProductRepository {
	
	public NamedParameterJdbcTemplate namedParameter;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.namedParameter = new NamedParameterJdbcTemplate(dataSource);
	}
	
	private static final class MapperForAllProducts implements RowMapper<Product> {
		public Product mapRow(ResultSet rs, int rowNum) throws SQLException{
			Product product = new Product();
			Image image = new Image();
			product.setProductId(rs.getInt("productId"));
			product.setProductName(rs.getString("productName"));
			product.setProductArticleId(rs.getString("productArticleId"));
			product.setProductPrice(rs.getInt("productPrice"));
			product.setProductQuantity(rs.getInt("productQuantity"));
			image.setLink(rs.getString("link"));
			product.setImage(image);
			return product;
		}
	}
	
	public List<Product> getAllProducts() {
		String sql = "SELECT p.productId,"
				+ "			 p.productArticleId, "
				+ "			 p.productName, "
				+ "			 p.productPrice, "
				+ "			 pi.link, "
				+ "			 p.productQuantity "
				+ "	 FROM product p INNER JOIN product_images pi ON pi.productId = p.productId "
				+ "	 WHERE pi.imageIdentificationInDB ='1' AND productQuantity > 0 ";
		List<Product> list = namedParameter.query(sql, new MapperForAllProducts());
		return list;
	}
	
	private static final class ProductMapperById implements RowMapper<Product> {
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
	
	public Product getDetailsOfProductByProductId(Integer productId) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("productId", productId);
		
		String sql = "SELECT 	p.productId, "
				+ "				p.productArticleId, "
				+ "				p.productDescription, "
				+ "				p.productName, "
				+ "				p.productPrice, "
				+ "				p.productWeight, "
				+ "				pt.typeName as productType, "
				+ "				pm.materialName as productMaterial, "
				+ "				pc.categoryName as productCategory, "
				+ "				pg.gemstoneName as productGemstone, "
				+ "				pgg.genderName as productGender, "
				+ " 			pi.link, "
				+ "				p.productQuantity "
				+ "	 FROM product p INNER JOIN producttype  pt ON pt.typeId = p.productType "
				+ "					INNER JOIN productmaterial pm ON pm.materialId = p.productMaterial "
				+ "					INNER JOIN productcategory pc ON pc.categoryId = p.productCategory "
				+ "					INNER JOIN productgemstone pg ON pg.gemstoneId = p.productGemstone "
				+ "					INNER JOIN productgender pgg ON pgg.genderId = p.productGender "
				+ "					INNER JOIN product_images pi ON pi.productId = p.productId "
				+ " WHERE p.productId = :productId AND pi.imageIdentificationInDB ='1' ";
		return namedParameter.queryForObject(sql, paramSource, new ProductMapperById());
	}
	
	private static final class MapperForUpdateProduct implements RowMapper<Product> {
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
			
			material.setMaterialId(rs.getInt("productMaterial"));
			category.setCategoryId(rs.getInt("productCategory"));
			gemstone.setGemstoneId(rs.getInt("productGemstone"));
			gender.setGenderId(rs.getInt("productGender"));
			type.setTypeId(rs.getInt("productType"));
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
	
	
	public Product getProductByProductIdForUpdate(Integer productId) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("productId", productId);
		
		String sql = "SELECT 	p.productId, "
				+ "				p.productArticleId, "
				+ "				p.productDescription, "
				+ "				p.productName, "
				+ "				p.productPrice, "
				+ "				p.productWeight, "
				+ "				p.productType, "
				+ "				p.productMaterial, "
				+ "				p.productCategory, "
				+ "				p.productGemstone, "
				+ "				p.productGender, "
				+ " 			pi.link,"
				+ "				p.productQuantity "
				+ "	 FROM product p INNER JOIN product_images pi ON pi.productId = p.productId "
				+ " WHERE p.productId = :productId AND pi.imageIdentificationInDB ='1' ";
		return namedParameter.queryForObject(sql, paramSource, new MapperForUpdateProduct());
	}
	
	private SqlParameterSource getSqlParameterByModel(Product product) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		if (product != null) {
			paramSource.addValue("productId", product.getProductId());
			paramSource.addValue("productArticleId", product.getProductArticleId());
			paramSource.addValue("productName", product.getProductName());
			paramSource.addValue("productDescription", product.getProductDescription());
			paramSource.addValue("productPrice", product.getProductPrice());
			paramSource.addValue("productWeight", product.getProductWeight());
			paramSource.addValue("productQuantity", product.getProductQuantity());
			paramSource.addValue("productCategory", product.getCategory().getCategoryId());
			paramSource.addValue("productType", product.getType().getTypeId());
			paramSource.addValue("productGemstone", product.getGemstone().getGemstoneId());
			paramSource.addValue("productMaterial", product.getMaterial().getMaterialId());
			paramSource.addValue("productGender", product.getGender().getGenderId());
		}
		return paramSource;
	}
	
	public void insertNewProduct(Product product) {
		String sql = "INSERT INTO product(productDescription, "
				+ "						  productName, "
				+ "						  productArticleId, "
				+ "						  productCategory, "
				+ "					      productType,"
				+ "						  productGemstone, "
				+ "						  productMaterial,"
				+ "						  productPrice, "
				+ "						  productWeight,"
				+ "						  productGender,"
				+ "						  productQuantity) "
				+ "			 VALUES (   :productDescription, "
				+ "						:productName, "
				+ "						:productArticleId,"
				+ "						:productCategory, "
				+ "						:productType, "
				+ "						:productGemstone, "
				+ "						:productMaterial, "
				+ "						:productPrice, "
				+ "						:productWeight,"
				+ "					    :productGender, "
				+ "						:productQuantity)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		this.namedParameter.update(sql, getSqlParameterByModel(product), keyHolder);
		Integer productId = keyHolder.getKey().intValue();
		product.setProductId(productId);
	}
	
	public void deleteProduct(Integer productId) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("productId", productId);
		String sql = "DELETE p, pi FROM product p INNER JOIN product_images pi ON p.productId = pi.productId WHERE p.productId = :productId ";
		namedParameter.update(sql, paramSource);
	}

	@Override
	public void updateProduct(Product product) {
		String sql = "UPDATE product  "+
					 "SET productArticleId=:productArticleId, "+
					 "	  productCategory = :productCategory, "+
					 "	  productType = :productType,  "+
					 "	  productDescription = :productDescription,  "+
					 "    productName = :productName,  "+
					 "	  productPrice = :productPrice,  "+
					 "    productMaterial = :productMaterial, "+
					 "	  productGemstone = :productGemstone,  "+
					 "	  productWeight = :productWeight,  "+
					 "	  productGender = :productGender,  "+
					 "	  productQuantity = :productQuantity  "+
					 "WHERE productId = :productId; ";
		namedParameter.update(sql, getSqlParameterByModel(product));
	}
	
	

	
}
