package com.itemType.model;

import org.json.JSONArray;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemTypeJDBCDAO implements ItemTypeDAOInterface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/ba_rei?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "wendy1016";

	private static final String INSERT_STMT = "INSERT INTO item_type (itemt_name) values (?)";
	private static final String GET_ALL_STMT = "SELECT itemt_id,itemt_name FROM item_type order by itemt_id;";
	private static final String GET_ONE_STMT = "SELECT itemt_id,itemt_name FROM item_type where itemt_id = ?;";
	private static final String DELETE = "DELETE FROM item_type where itemt_id = ?";
	private static final String UPDATE = "UPDATE item_type set  itemt_name=? where itemt_id = ?";

	@Override
	public void insert(ItemTypeVO itemTypeVO) {
		try (Connection con = DriverManager.getConnection(url, userid, passwd);
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
		try (Connection con = DriverManager.getConnection(url, userid, passwd);
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
		try (Connection con = DriverManager.getConnection(url, userid, passwd);
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
		try (Connection con = DriverManager.getConnection(url, userid, passwd);
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
		try (Connection con = DriverManager.getConnection(url, userid, passwd);
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

	public static void main(String[] args) {
		ItemTypeJDBCDAO dao = new ItemTypeJDBCDAO();
		// 新增
//		ItemTypeVO itemTypeVO1 = new ItemTypeVO();
//		itemTypeVO1.setItemt_name("老鼠飼料");
//		dao.insert(itemTypeVO1);


		// 修改
//		ItemTypeVO itemTypeVO2 = new ItemTypeVO();
//		itemTypeVO2.setItemt_name("狗狗清潔用品");
//		itemTypeVO2.setItemt_id(1);
//
//		dao.update(itemTypeVO2);

		// 刪除
//		dao.delete(22);


		//查詢
//		ItemTypeVO TypeVO3=dao.findByPrimaryKey(2);
//		System.out.print(TypeVO3.getItemt_id() + ",");
//		System.out.print(TypeVO3.getItemt_name() );

		// 查詢全部
		List<ItemTypeVO> list =dao.getAll();
		for(ItemTypeVO itemtype:list) {
			System.out.print(itemtype.getItemtId() + ",");
			System.out.print(itemtype.getItemtName() );
			System.out.println("");

		}

	}
}
