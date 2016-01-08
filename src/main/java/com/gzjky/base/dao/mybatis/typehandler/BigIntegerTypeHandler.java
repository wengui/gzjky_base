package com.gzjky.base.dao.mybatis.typehandler;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

/**
 * 
 * BigDecimal to BigInteger type handler
 * 
 */
@SuppressWarnings("rawtypes")
public class BigIntegerTypeHandler extends BaseTypeHandler {

	public void setNonNullParameter(PreparedStatement ps, int i,
			Object parameter, JdbcType jdbcType) throws SQLException {
		ps.setBigDecimal(i, new BigDecimal((BigInteger) parameter));
	}

	public Object getNullableResult(ResultSet rs, String columnName)
			throws SQLException {

		if (rs.wasNull())
			return null;
		return rs.getBigDecimal(columnName).toBigInteger();
	}

	public Object getNullableResult(CallableStatement cs, int columnIndex)
			throws SQLException {

		if (cs.wasNull())
			return null;
		return cs.getBigDecimal(columnIndex).toBigInteger();
	}

	@Override
	public Object getNullableResult(ResultSet rs, int columnIndex)
			throws SQLException {
		if (rs.wasNull())
			return null;
		return rs.getBigDecimal(columnIndex).toBigInteger();
	}
}
