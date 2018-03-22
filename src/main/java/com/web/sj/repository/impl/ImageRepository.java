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

import com.web.sj.domain.Image;
import com.web.sj.repository.IImageRepository;

@Repository
public class ImageRepository implements IImageRepository {
	
	public NamedParameterJdbcTemplate namedParameter;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.namedParameter = new NamedParameterJdbcTemplate(dataSource);
	}
	
	private static final class ImageMapper implements RowMapper<Image> {
		public Image mapRow(ResultSet rs, int rowNum) throws SQLException {
			Image image = new Image();
			image.setLink(rs.getString("link"));
			image.setImageIdentificationInDB(rs.getString("imageIdentificationInDB"));
			return image;
		}
	}

	public void addMainImage(Integer productId, String id, String link, String deletehash, String imageIdentificationInDB) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("productId", productId);
		paramSource.addValue("id", id);
		paramSource.addValue("link", link);
		paramSource.addValue("deletehash", deletehash);
		paramSource.addValue("imageIdentificationInDB", imageIdentificationInDB);
		
		
		String sql = "INSERT INTO product_images(productId, id, link, deletehash, imageIdentificationInDB) "+
					  "Value(:productId, :id, :link, :deletehash, :imageIdentificationInDB)";
		
		namedParameter.update(sql, paramSource);
	}

	public List<Image> getAllImagesByProductId(Integer productId) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("productId", productId);
		
		String sql = "SELECT link, imageIdentificationInDB FROM product_images WHERE productId = :productId ";
		List<Image> list = namedParameter.query(sql, paramSource, new ImageMapper());
		return list;
	}

}
