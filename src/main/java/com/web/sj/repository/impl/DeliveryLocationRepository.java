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

import com.web.sj.domain.DeliveryLocation;
import com.web.sj.repository.IDeliveryLocationRepository;

@Repository
public class DeliveryLocationRepository implements IDeliveryLocationRepository {
	
	public NamedParameterJdbcTemplate namedParameter;
	
	@Autowired
	private void setDataSource(DataSource dataSource) {
		this.namedParameter = new NamedParameterJdbcTemplate(dataSource);
	}
	
	private static final class SettlementMapper implements RowMapper<DeliveryLocation> {
		public DeliveryLocation mapRow(ResultSet rs, int rowNum) throws SQLException {
			DeliveryLocation location = new DeliveryLocation();
			location.setSettlementId(rs.getInt("settlementId"));
			location.setSettlement(rs.getString("settlement"));
			
			return location;
		}
	}
	
	private static final class CitiesMapper implements RowMapper<DeliveryLocation> {
		public DeliveryLocation mapRow(ResultSet rs, int rowNum) throws SQLException {
			DeliveryLocation location = new DeliveryLocation();
			location.setSettlement(rs.getString("settlement"));
			
			return location;
		}
	}

	public List<DeliveryLocation> findSettlement(String settlement) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("settlement", "%" + settlement + "%");
		String sql = "SELECT settlementId, settlement FROM settlement_ukraine WHERE settlement LIKE :settlement ";
		List<DeliveryLocation> list = namedParameter.query(sql, paramSource, new CitiesMapper());
		return list;
	}

	public List<DeliveryLocation> getAllSettlements() {
		String sql = "SELECT settlementId, settlement FROM settlement_ukraine";
		return (List<DeliveryLocation>) namedParameter.query(sql, new SettlementMapper());
	}

}
