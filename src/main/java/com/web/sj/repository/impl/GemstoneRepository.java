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

import com.web.sj.domain.Gemstone;
import com.web.sj.repository.IGemstoneRepository;

@Repository
public class GemstoneRepository implements IGemstoneRepository {
	
	public NamedParameterJdbcTemplate namedParameter;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.namedParameter = new NamedParameterJdbcTemplate(dataSource);
	}
	
	private static final class AvailableGemstoneMapper implements RowMapper<Gemstone> {
		public Gemstone mapRow(ResultSet rs, int rowNum) throws SQLException {
			Gemstone gemstone = new Gemstone();
			gemstone.setGemstoneId(rs.getInt("gemstoneId"));
			gemstone.setGemstoneName(rs.getString("gemstoneName"));
			gemstone.setGemstoneLink(rs.getString("gemstoneLink"));
			gemstone.setGemstoneQuantity(rs.getInt("gemstoneQuantity"));
			return gemstone;
		}
	}
	
	private static final class GemstoneMapper implements RowMapper<Gemstone> {
		public Gemstone mapRow(ResultSet rs, int rowNum) throws SQLException {
			Gemstone gemstone = new Gemstone();
			gemstone.setGemstoneId(rs.getInt("gemstoneId"));
			gemstone.setGemstoneName(rs.getString("gemstoneName"));
			gemstone.setGemstoneLink(rs.getString("gemstoneLink"));
			return gemstone;
		}
	}	

	public List<Gemstone> getGemstones() {
		
		String sql = "SELECT pg.gemstoneId, pg.gemstoneName, pg.gemstoneLink "
				+ "FROM productgemstone pg ";
		List<Gemstone> list = namedParameter.query(sql, new GemstoneMapper());
		return list;
	}

	public List<Gemstone> availableGemstoneByParameter(String productCategory) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("productCategory", productCategory);
		
		String sql = "SELECT pg.gemstoneId, pg.gemstoneName, pg.gemstoneLink, count(*) as gemstoneQuantity "
				+ "FROM productgemstone pg INNER JOIN product p ON pg.gemstoneId = p.productGemstone "
				+ "WHERE p.productCategory = (SELECT pc.categoryId FROM productcategory pc WHERE pc.categoryLink = :productCategory ) "
				+ "GROUP BY pg.gemstoneId, pg.gemstoneName, pg.gemstoneLink";
		List<Gemstone> list = namedParameter.query(sql, paramSource, new AvailableGemstoneMapper());
		return list;
	}

}
