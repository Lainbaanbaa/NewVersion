package com.group_buy_item.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Group_Buy_ItemJDBCDAO implements Group_Buy_ItemDAO_interface{

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/ba_rei?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";
	
	
	private static final String INSERT_STMT = "INSERT INTO GROUP_BUY_ITEM (GBITEM_NAME, GBITEM_CONTENT, GBITEM_PRICE, GBITEM_STATUS, GBITEM_STARTDATE, GBITEM_ENDDATE, GBITEM_TYPE) VALUES (?, ?, ?, ?, ?, ?, ?)";

	private static final String GET_ALL_STMT = "SELECT GBITEM_ID,GBITEM_NAME, GBITEM_CONTENT, GBITEM_PRICE, GBITEM_STATUS, GBITEM_STARTDATE, GBITEM_ENDDATE , GBITEM_TYPE FROM GROUP_BUY_ITEM order by GBITEM_ID";

	private static final String GET_ONE_STMT = "SELECT GBITEM_ID,GBITEM_NAME, GBITEM_CONTENT, GBITEM_PRICE, GBITEM_STATUS, GBITEM_STARTDATE, GBITEM_ENDDATE, GBITEM_TYPE FROM GROUP_BUY_ITEM where GBITEM_ID = ?";

	private static final String DELETE = "DELETE FROM GROUP_BUY_ITEM where GBITEM_ID = ?";

	private static final String UPDATE = "UPDATE GROUP_BUY_ITEM set GBITEM_NAME=?, GBITEM_CONTENT=?, GBITEM_PRICE=?, GBITEM_STATUS=?, GBITEM_STARTDATE=?, GBITEM_ENDDATE=?, GBITEM_TYPE=? where GBITEM_ID = ?";

	
	
	
	@Override
	public void insert(Group_Buy_ItemVO Group_Buy_ItemVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, Group_Buy_ItemVO.getGbitem_name());
			pstmt.setString(2, Group_Buy_ItemVO.getGbitem_content());
			pstmt.setInt(3, Group_Buy_ItemVO.getGbitem_price());
			pstmt.setInt(4, Group_Buy_ItemVO.getGbitem_status());
			pstmt.setDate(5, Group_Buy_ItemVO.getGbitem_startdate());
			pstmt.setDate(6, Group_Buy_ItemVO.getGbitem_enddate());
			pstmt.setInt(7, Group_Buy_ItemVO.getGbitem_type());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void update(Group_Buy_ItemVO Group_Buy_ItemVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, Group_Buy_ItemVO.getGbitem_name());
			pstmt.setString(2, Group_Buy_ItemVO.getGbitem_content());
			pstmt.setInt(3, Group_Buy_ItemVO.getGbitem_price());
			pstmt.setInt(4, Group_Buy_ItemVO.getGbitem_status());
			pstmt.setDate(5, Group_Buy_ItemVO.getGbitem_startdate());
			pstmt.setDate(6, Group_Buy_ItemVO.getGbitem_enddate());
			pstmt.setInt(7, Group_Buy_ItemVO.getGbitem_type());
			pstmt.setInt(8, Group_Buy_ItemVO.getGbitem_id());
			
			pstmt.executeUpdate();
			
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void delete(Integer gbitem_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, gbitem_id);

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public Group_Buy_ItemVO findByPrimaryKey(Integer gbitem_id) {
		Group_Buy_ItemVO Group_Buy_ItemVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, gbitem_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Group_Buy_ItemVO = new Group_Buy_ItemVO();
				Group_Buy_ItemVO.setGbitem_id(rs.getInt("gbitem_id"));
				Group_Buy_ItemVO.setGbitem_name(rs.getString("gbitem_name"));
				Group_Buy_ItemVO.setGbitem_content(rs.getString("gbitem_content"));
				Group_Buy_ItemVO.setGbitem_price(rs.getInt("gbitem_price"));
				Group_Buy_ItemVO.setGbitem_status(rs.getInt("gbitem_status"));
				Group_Buy_ItemVO.setGbitem_startdate(rs.getDate("gbitem_startdate"));
				Group_Buy_ItemVO.setGbitem_enddate(rs.getDate("gbitem_enddate"));
				Group_Buy_ItemVO.setGbitem_type(rs.getInt("gbitem_type"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return Group_Buy_ItemVO;
	}

	@Override
	public List<Group_Buy_ItemVO> getAll() {
		List<Group_Buy_ItemVO> list = new ArrayList<Group_Buy_ItemVO>();
		Group_Buy_ItemVO Group_Buy_ItemVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Group_Buy_ItemVO = new Group_Buy_ItemVO();
				Group_Buy_ItemVO.setGbitem_id(rs.getInt("gbitem_id"));
				Group_Buy_ItemVO.setGbitem_name(rs.getString("gbitem_name"));
				Group_Buy_ItemVO.setGbitem_content(rs.getString("gbitem_content"));
				Group_Buy_ItemVO.setGbitem_price(rs.getInt("gbitem_price"));
				Group_Buy_ItemVO.setGbitem_status(rs.getInt("gbitem_status"));
				Group_Buy_ItemVO.setGbitem_startdate(rs.getDate("gbitem_startdate"));
				Group_Buy_ItemVO.setGbitem_enddate(rs.getDate("gbitem_enddate"));
				Group_Buy_ItemVO.setGbitem_type(rs.getInt("gbitem_type"));
				list.add(Group_Buy_ItemVO); 
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	public static void main(String[] args) {

		Group_Buy_ItemJDBCDAO dao = new Group_Buy_ItemJDBCDAO();

		// 新增
//		Group_Buy_ItemVO gbiVO1 = new Group_Buy_ItemVO();
//		gbiVO1.setGbitem_name("外出摺疊碗-綠色");
//		gbiVO1.setGbitem_content("摺疊碗");
//		gbiVO1.setGbitem_price(80);
//		gbiVO1.setGbitem_status(1);
//		gbiVO1.setGbitem_startdate(java.sql.Date.valueOf("2022-10-16"));
//		gbiVO1.setGbitem_enddate(java.sql.Date.valueOf("2023-12-30"));
//		gbiVO1.setGbitem_type(1);
//		dao.insert(gbiVO1);
		
		// 修改
		Group_Buy_ItemVO gbiVO2 = new Group_Buy_ItemVO();
		
		gbiVO2.setGbitem_name("外出");
		gbiVO2.setGbitem_content("摺疊碗");
		gbiVO2.setGbitem_price(999);
		gbiVO2.setGbitem_status(1);
		gbiVO2.setGbitem_startdate(java.sql.Date.valueOf("2022-10-16"));
		gbiVO2.setGbitem_enddate(java.sql.Date.valueOf("2023-12-30"));
		gbiVO2.setGbitem_id(16); //PK
		gbiVO2.setGbitem_type(1); 
		dao.update(gbiVO2);

		// 刪除
//		dao.delete(20);
//		dao.delete(21);
//		dao.delete(22);
//		 查詢
//		Group_Buy_ItemVO gbiVO3 = dao.findByPrimaryKey(16);
//		System.out.print(gbiVO3.getGbitem_id() + ",");
//		System.out.print(gbiVO3.getGbitem_name() + ",");
//		System.out.print(gbiVO3.getGbitem_content() + ",");
//		System.out.print(gbiVO3.getGbitem_price() + ",");
//		System.out.print(gbiVO3.getGbitem_status() + ",");
//		System.out.print(gbiVO3.getGbitem_startdate() + ",");
//		System.out.println(gbiVO3.getGbitem_enddate() + ",");
//		System.out.println(gbiVO3.getGbitem_type());
//		System.out.println("---------------------");

		// 查詢
		List<Group_Buy_ItemVO> list = dao.getAll();
		for (Group_Buy_ItemVO aGbi : list) {
			System.out.print(aGbi.getGbitem_id() + ",");
			System.out.print(aGbi.getGbitem_name() + ",");
			System.out.print(aGbi.getGbitem_content() + ",");
			System.out.print(aGbi.getGbitem_price() + ",");
			System.out.print(aGbi.getGbitem_status() + ",");
			System.out.print(aGbi.getGbitem_startdate() + ",");
			System.out.print(aGbi.getGbitem_enddate() + ",");
			System.out.print(aGbi.getGbitem_type());
			System.out.println();
		}
	}
}
