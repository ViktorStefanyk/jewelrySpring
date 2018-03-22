package com.web.sj.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.web.sj.domain.JewelrySize;
import com.web.sj.repository.ISizeRepository;

@Repository
public class SizeRepository implements ISizeRepository {
	
	private NamedParameterJdbcTemplate namedParameter;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.namedParameter = new NamedParameterJdbcTemplate(dataSource);
	}
	
	private static final class AvailableSizeMapper implements RowMapper<JewelrySize> {
		public JewelrySize mapRow(ResultSet rs, int rowNum) throws SQLException {
			JewelrySize jewelrySize = new JewelrySize();
			jewelrySize.setSizeId(rs.getInt("sizeId"));
			jewelrySize.setSizeName(rs.getFloat("sizeName"));
			jewelrySize.setSizeQuantity(rs.getInt("sizeQuantity"));
			return jewelrySize;
		}
	}
		
	private static final class SizeMapper implements RowMapper<JewelrySize> {
		public JewelrySize mapRow(ResultSet rs, int rowNum) throws SQLException {
			JewelrySize jewelrySize = new JewelrySize();
			jewelrySize.setSizeId(rs.getInt("sizeId"));
			jewelrySize.setSizeName(rs.getFloat("sizeName"));
			return jewelrySize;
		}
	}
	

	public List<JewelrySize> getAvailableSizesByCategory(String productCategory) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource(); 
		paramSource.addValue("productCategory", productCategory);
		
		String sql = null;
		
		if (productCategory.equals("ring")) {
			sql = "SELECT prs.ringSizeId as sizeId, "
					+ "   rs.ringSize as sizeName, "
					+ "   count(prs.ringSizeId) as sizeQuantity "
				+ "			FROM product_ringsize prs INNER JOIN product p ON p.productId = prs.productId "
				+ "									  INNER JOIN ringsize rs ON rs.ringSizeId = prs.ringSizeId "
				+ " 		WHERE p.productCategory IN (SELECT pc.categoryId "
				+ "										FROM productcategory pc "
				+ "										WHERE pc.categoryLink = :productCategory) "
				+ " 		GROUP BY prs.ringSizeId, rs.ringSize";
		}
		
		if (productCategory.equals("pendants")) {
			 sql = "SELECT pps.pendantSizeId as sizeId, "
					+ "	  ps.pendantSize as sizeName, "
					+ "	  count(pps.pendantSizeId) as sizeQuantity "
					+ "		FROM product_pendantsize pps INNER JOIN product p ON p.productId = pps.productId "
					+ "									 INNER JOIN pendantsize ps ON ps.pendantSizeID = pps.pendantSizeId "
					+ "		WHERE p.productCategory IN (SELECT pc.categoryId"
					+ "									FROM productcategory pc"
					+ "									WHERE pc.categoryLink = :productCategory) "
					+ "		GROUP BY pps.pendantSizeId, ps.pendantSize";
		}
		
		if (productCategory.equals("chains")) {
			sql = "SELECT pcs.chainsSizeId as sizeId,"
					+ "   cs.chainsSize as sizeName, "
					+ "	  count(cs.chainsSizeId) as sizeQuantity "
					+ "			FROM product_chainssize pcs INNER JOIN product p ON p.productId = pcs.productId"
					+ "										INNER JOIN chainssize cs ON cs.chainsSizeId = pcs.chainsSizeId"
					+ "			WHERE p.productCategory IN (SELECT pc.categoryId"
					+ "										FROM productcategory pc"
					+ "										WHERE pc.categoryLink = :productCategory) "
					+ "			GROUP BY pcs.chainsSizeId, cs.chainsSize";
		}
		
		return namedParameter.query(sql, paramSource, new AvailableSizeMapper());
	}

	public List<JewelrySize> getAllSizesByProductIdAndCategory(Integer productId, String productCategory) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource(); 
		paramSource.addValue("productCategory", productCategory);
		paramSource.addValue("productId", productId);
		String sql = null;
		if (productCategory.equals("Кольца")) {
			sql = "SELECT prs.ringSizeId as sizeId, "
					+ "   rs.ringSize as sizeName "
					+ "		FROM product_ringsize prs INNER JOIN product p ON p.productId = prs.productId "
					+ "									  INNER JOIN ringsize rs ON rs.ringSizeId = prs.ringSizeId "
					+ " 	WHERE p.productCategory IN (SELECT pc.categoryId "
					+ "										FROM productcategory pc "
					+ "										WHERE pc.categoryName = :productCategory)"
					+ "		AND p.productId = :productId "
					+ " 	GROUP BY prs.ringSizeId, rs.ringSize";
		}
		
		if (productCategory.equals("Подвески")) {
			 sql = "SELECT pps.pendantSizeId as sizeId, "
					+ "	  ps.pendantSize as sizeName "
					+ "		FROM product_pendantsize pps INNER JOIN product p ON p.productId = pps.productId "
					+ "									 INNER JOIN pendantsize ps ON ps.pendantSizeID = pps.pendantSizeId "
					+ "		WHERE p.productCategory IN (SELECT pc.categoryId"
					+ "									FROM productcategory pc"
					+ "									WHERE pc.categoryName = :productCategory)"
					+ "		AND p.productId = :productId "
					+ "		GROUP BY pps.pendantSizeId, ps.pendantSize";
		}
		
		if (productCategory.equals("Цепочки")) {
			sql = "SELECT pcs.chainsSizeId as sizeId,"
					+ "   cs.chainsSize as sizeName "
					+ "		FROM product_chainssize pcs INNER JOIN product p ON p.productId = pcs.productId"
					+ "										INNER JOIN chainssize cs ON cs.chainsSizeId = pcs.chainsSizeId"
					+ "		WHERE p.productCategory IN (SELECT pc.categoryId"
					+ "										FROM productcategory pc"
					+ "										WHERE pc.categoryName = :productCategory)"
					+ "		AND p.productId = :productId "
					+ "		GROUP BY pcs.chainsSizeId, cs.chainsSize";
		}
		
		return namedParameter.query(sql, paramSource, new SizeMapper());
	}

	public void deleteSizesByProductId(Integer productId, String productCategory) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("productId", productId);
		paramSource.addValue("productCategory", productCategory);
		String sql = null;
		if(productCategory.equals("Кольца") || productCategory.equals("1")) {
			sql = "DELETE FROM product_ringsize WHERE productId = :productId ";
		}
		if(productCategory.equals("Цепочки") || productCategory.equals("4")) {
			sql = "DELETE FROM product_chainssize WHERE productId = :productId ";
		}
		if(productCategory.equals("Подвески") || productCategory.equals("3")) {
			sql = "DELETE FROM product_pendantsize WHERE productId = :productId "; 
		}
		namedParameter.update(sql, paramSource);
	}

	public List<JewelrySize> getAllSizesByCategory(String productCategory) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource(); 
		paramSource.addValue("productCategory", productCategory);
		
		String sql = null;
		
		if (productCategory.equals("1")) {
			sql = "SELECT ringSizeId as sizeId, "
				+ "   	  ringSize as sizeName "
				+ "FROM ringsize ";
		}
		
		if (productCategory.equals("3")) {
			 sql = "SELECT pendantSizeId as sizeId, "
				+ "	   	   pendantSize as sizeName "
				+ " FROM pendantsize";
		}
		
		if (productCategory.equals("4")) {
			sql = "SELECT chainsSizeId as sizeId,"
				+ "   	  chainsSize as sizeName "
				+ "FROM chainssize";
		}
		return namedParameter.query(sql, paramSource, new SizeMapper());
	}

	public void inserSizeByCategory(Integer productId, Integer productCategory, Integer sizeId) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("productId", productId);
		paramSource.addValue("sizeId", sizeId);
		paramSource.addValue("productCategory", productCategory);
		String sql = null;
		if (productCategory == 1) {
			sql = "INSERT INTO product_ringsize(productId, ringSizeId) VALUE (:productId, :sizeId)";
		}
		if (productCategory == 4) {
			sql = "INSERT INTO product_chainssize(productId, chainsSizeId) VALUE (:productId, :sizeId)";
		}
		if (productCategory == 3) {
			sql = "INSERT INTO product_pendantsize(productId, pendantSizeId) VALUE(:productId, :sizeId)";
		}
		namedParameter.update(sql, paramSource);
	}

}
