package com.web.sj.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.web.sj.domain.Category;
import com.web.sj.repository.ICategoryRepository;

@Repository
public class CategoryRepository implements ICategoryRepository {
	
	public NamedParameterJdbcTemplate namedParameter;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.namedParameter = new NamedParameterJdbcTemplate(dataSource);
	}
	
	private static final class CategoryMapper implements RowMapper<Category> {
		public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
			Category category = new Category();
			category.setCategoryId(rs.getInt("categoryId"));
			category.setCategoryName(rs.getString("categoryName"));
			category.setCategoryLink(rs.getString("categoryLink"));
			return category;
		}
	}

	public List<Category> getCategories() {
		String sql = "SELECT categoryId, categoryName, categoryLink FROM productcategory";
		List<Category> list = namedParameter.query(sql, new CategoryMapper());
		return list;
	}

}
