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

import com.web.sj.domain.Type;
import com.web.sj.repository.ITypeRepository;

@Repository
public class TypeRepository implements ITypeRepository {
	
	public NamedParameterJdbcTemplate namedParameter;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.namedParameter = new NamedParameterJdbcTemplate(dataSource);
	}
	
	private static final class TypeMapper implements RowMapper<Type> {
		public Type mapRow(ResultSet rs, int rowNum) throws SQLException {
			Type type = new Type();
			type.setTypeId(rs.getInt("typeId"));
			type.setTypeName(rs.getString("typeName"));
			type.setTypeLink(rs.getString("typeLink"));
			return type;
		}
	}
	
	private static final class AvailableTypeMapper implements RowMapper<Type> {
		public Type mapRow(ResultSet rs, int rowNum) throws SQLException {
			Type type = new Type();
			type.setTypeId(rs.getInt("typeId"));
			type.setTypeName(rs.getString("typeName"));
			type.setTypeLink(rs.getString("typeLink"));
			type.setTypeQuantity(rs.getInt("typeQuantity"));
			return type;
		}
	}

	public List<Type> getTypes() {
		String sql = "SELECT pt.typeId, pt.typeName, pt.typeLink "
				+ "FROM producttype pt ";
		List<Type> list = namedParameter.query(sql, new TypeMapper());
		return list;
	}

	public List<Type> availableTypeByParameter(String productCategory) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("productCategory", productCategory);
		String sql = "SELECT pt.typeId, pt.typeName, pt.typeLink, count(*) as typeQuantity "
				+ "FROM producttype pt INNER JOIN product p ON p.productType = pt.typeId "
				+ "WHERE p.productCategory = (SELECT pc.categoryId FROM productcategory pc WHERE pc.categoryLink = :productCategory )"
				+ "GROUP BY pt.typeId, pt.typeName, pt.typeLink";
		List<Type> list = namedParameter.query(sql, paramSource, new AvailableTypeMapper());
		return list;
	}

}
