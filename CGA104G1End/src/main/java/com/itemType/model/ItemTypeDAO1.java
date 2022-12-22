package com.itemType.model;

import org.json.JSONArray;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ItemTypeDAO1 implements ItemTypeDAOInterface {
	private static final String INSERT_STMT = "INSERT INTO item_type (itemt_name) values (?)";
	private static final String GET_ALL_STMT = "SELECT itemt_id,itemt_name FROM item_type order by itemt_id;";
	private static final String GET_ONE_STMT = "SELECT itemt_id,itemt_name FROM item_type where itemt_id = ?;";
	private static final String DELETE = "DELETE FROM item_type where itemt_id = ?";
	private static final String UPDATE = "UPDATE item_type set  itemt_name=? where itemt_id = ?";
	private static DataSource ds = null;

	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BaRei");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void insert(ItemTypeVO itemTypeVO) {
		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(INSERT_STMT)) {

			pstmt.setString(1, itemTypeVO.getItemtName());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		}

	}

	@Override
	public void update(ItemTypeVO itemTypeVO) {
		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(UPDATE)) {

			pstmt.setString(1, itemTypeVO.getItemtName());
			pstmt.setInt(2, itemTypeVO.getItemtId());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		}

	}

	@Override
	public void delete(Integer itemtId) {
		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(DELETE)) {

			pstmt.setInt(1, itemtId);

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		}

	}

	@Override
	public ItemTypeVO findByPrimaryKey(Integer itemtId) {
		ItemTypeVO itemTypeVO = null;
		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(GET_ONE_STMT, ResultSet.TYPE_SCROLL_INSENSITIVE)) {
			pstmt.setInt(1, itemtId);
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					itemTypeVO = new ItemTypeVO();
					itemTypeVO.setItemtId(rs.getInt("itemt_id"));
					itemTypeVO.setItemtName(rs.getString("itemt_name"));

				}
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		}

		return itemTypeVO;
	}

	@Override
	public List<ItemTypeVO> getAll() {
		List<ItemTypeVO> list = new ArrayList<ItemTypeVO>();
		ItemTypeVO itemTypeVO = null;
		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(GET_ALL_STMT, ResultSet.TYPE_SCROLL_INSENSITIVE)) {
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					itemTypeVO = new ItemTypeVO();
					itemTypeVO.setItemtId(rs.getInt("itemt_id"));
					itemTypeVO.setItemtName(rs.getString("itemt_name"));

					list.add(itemTypeVO);
				}
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		}
		return list;

	}

	@Override
	public JSONArray getAllJS() {
		return null;
	}
}
