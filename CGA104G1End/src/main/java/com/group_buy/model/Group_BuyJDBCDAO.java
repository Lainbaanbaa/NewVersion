package com.group_buy.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Group_BuyJDBCDAO implements Group_BuyDAO_interface{

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/ba_rei?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";
	
	
	private static final String INSERT_STMT = 
			"INSERT INTO GROUP_BUY (MEM_ID, GBITEM_ID, GB_MIN, GB_AMOUNT, GBSTART_DATE, GBEND_DATE,GB_STATUS, GB_PRICE, GB_NAME) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT GB_ID, MEM_ID, GBITEM_ID, GB_MIN, GB_AMOUNT, GBSTART_DATE, GBEND_DATE,GB_STATUS,GB_PRICE,GB_NAME FROM GROUP_BUY order by GB_ID";
	private static final String GET_ONE_STMT = 
			"SELECT GB_ID, MEM_ID, GBITEM_ID, GB_MIN, GB_AMOUNT, GBSTART_DATE, GBEND_DATE,GB_STATUS,GB_PRICE,GB_NAME FROM GROUP_BUY where GB_ID = ?";
	private static final String DELETE = 
			"DELETE FROM GROUP_BUY where GB_ID = ?";
	private static final String UPDATE = 
			"UPDATE GROUP_BUY set MEM_ID=?, GBITEM_ID=?, GB_MIN=?, GB_AMOUNT=?, GBSTART_DATE=?, GBEND_DATE=?, GB_STATUS=?, GB_PRICE=?, GB_NAME=? where GB_ID = ?";
	private static final String GET_ALL_STMT_MEMID = 
			"SELECT GB_ID, MEM_ID, GBITEM_ID, GB_MIN, GB_AMOUNT, GBSTART_DATE, GBEND_DATE,GB_STATUS,GB_PRICE,GB_NAME FROM GROUP_BUY WHERE MEM_ID = ? order by GB_ID";
	private static final String UPDATE_GB_PRICE = 
			"UPDATE `ba_rei`.`GROUP_BUY` SET `GB_PRICE` = ? WHERE (`GB_ID` = ?)";
	private static final String GET_LAST =
			"SELECT * FROM ba_rei.GROUP_BUY  order by GB_ID desc limit 1";
	private static final String UPDATE_GB_AMOUNT = 
			"UPDATE `ba_rei`.`GROUP_BUY` SET `GB_AMOUNT` = ? WHERE (`GB_ID` = ?)";
	private static final String UPDATE_GB_STATUS = 
			"UPDATE `ba_rei`.`GROUP_BUY` SET `GB_STATUS` = ? WHERE (`GB_ID` = ?);";
	private static final String GROUP_BUY_LEFT_JOIN_GROUP_BUY_ITEM_GET_ALL = 
			"SELECT * FROM ba_rei.GROUP_BUY left join GROUP_BUY_ITEM on GROUP_BUY.GBITEM_ID = GROUP_BUY_ITEM.GBITEM_ID order by GB_ID;";
	private static final String GROUP_BUY_LEFT_JOIN_GROUP_BUY_ITEM_GET_ALL_WHERE_MEMID = 
			"SELECT * FROM ba_rei.GROUP_BUY left join GROUP_BUY_ITEM on GROUP_BUY.GBITEM_ID = GROUP_BUY_ITEM.GBITEM_ID WHERE MEM_ID = ? order by GB_ID;";
	
	//查詢團購狀態等於0的(團購尚未開始)，而且團購開始時間大於現在時間(為了要更改狀態為團購進行中)
	private static final String GET_ALL_STMT_GB_STATUS_NEED_CHANGE_TO_IN_PROGRESS = 
			"SELECT GB_ID, MEM_ID, GBITEM_ID, GB_MIN, GB_AMOUNT, GBSTART_DATE, GBEND_DATE,GB_STATUS,GB_PRICE,GB_NAME FROM ba_rei.GROUP_BUY WHERE GB_STATUS = 0 AND GBSTART_DATE < NOW();";
	
	//查詢團購狀態等於1的(團購進行中)，而且現在時間>=團購結束時間(為了要更改狀態為團購關閉OR結束)
	private static final String GET_ALL_STMT_GB_STATUS_NEED_CHANGE_TO_END = 
			"SELECT GB_ID, MEM_ID, GBITEM_ID, GB_MIN, GB_AMOUNT, GBSTART_DATE, GBEND_DATE,GB_STATUS,GB_PRICE,GB_NAME FROM ba_rei.GROUP_BUY WHERE GB_STATUS = 1 AND GBEND_DATE < NOW()";
	//
	private static final String SEARCH_MEM_ID_EXIST_IN_GROUP_BUY = 
			"SELECT MEM_ID FROM GROUP_BUY WHERE MEM_ID = ?";
	
	
	@Override
	public void insert(Group_BuyVO Group_BuyVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, Group_BuyVO.getMem_id());
			pstmt.setInt(2, Group_BuyVO.getGbitem_id());
			pstmt.setInt(3, Group_BuyVO.getGb_min());
			pstmt.setInt(4, Group_BuyVO.getGb_amount());
			pstmt.setTimestamp(5, Group_BuyVO.getGbstart_date());
			pstmt.setTimestamp(6, Group_BuyVO.getGbend_date());
			pstmt.setInt(7, Group_BuyVO.getGb_status());
			pstmt.setInt(8, Group_BuyVO.getGb_price());
			pstmt.setString(9, Group_BuyVO.getGb_name());

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
	public void update(Group_BuyVO Group_BuyVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, Group_BuyVO.getMem_id());
			pstmt.setInt(2, Group_BuyVO.getGbitem_id());
			pstmt.setInt(3, Group_BuyVO.getGb_min());
			pstmt.setInt(4, Group_BuyVO.getGb_amount());
			pstmt.setTimestamp(5, Group_BuyVO.getGbstart_date());
			pstmt.setTimestamp(6, Group_BuyVO.getGbend_date());
			pstmt.setInt(7, Group_BuyVO.getGb_status());
			pstmt.setInt(8, Group_BuyVO.getGb_price());
			pstmt.setString(9, Group_BuyVO.getGb_name());
			pstmt.setInt(10, Group_BuyVO.getGb_id());

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
	public void delete(Integer gb_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, gb_id);

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
	public Group_BuyVO CheckByMemID(Integer mem_id) {
		Group_BuyVO Group_BuyVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(SEARCH_MEM_ID_EXIST_IN_GROUP_BUY);

			pstmt.setInt(1, mem_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Group_BuyVO = new Group_BuyVO();
				Group_BuyVO.setMem_id(rs.getInt("mem_id"));
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
		return Group_BuyVO;
	}
	
	@Override
	public Group_BuyVO findByPrimaryKey(Integer gb_id) {
		Group_BuyVO Group_BuyVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, gb_id);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Group_BuyVO = new Group_BuyVO();
				Group_BuyVO.setGb_id(rs.getInt("gb_id"));
				Group_BuyVO.setMem_id(rs.getInt("mem_id"));
				Group_BuyVO.setGbitem_id(rs.getInt("gbitem_id"));
				Group_BuyVO.setGb_min(rs.getInt("gb_min"));
				Group_BuyVO.setGb_amount(rs.getInt("gb_amount"));
				Group_BuyVO.setGbstart_date(rs.getTimestamp("gbstart_date"));
				Group_BuyVO.setGbend_date(rs.getTimestamp("gbend_date"));
				Group_BuyVO.setGb_status(rs.getInt("gb_status"));
				Group_BuyVO.setGb_price(rs.getInt("gb_price"));
				Group_BuyVO.setGb_name(rs.getString("gb_name"));
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
		return Group_BuyVO;
	}
	
	@Override
	public Group_BuyVO findLast() {
		Group_BuyVO Group_BuyVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_LAST);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Group_BuyVO = new Group_BuyVO();
				Group_BuyVO.setGb_id(rs.getInt("gb_id"));
				Group_BuyVO.setMem_id(rs.getInt("mem_id"));
				Group_BuyVO.setGbitem_id(rs.getInt("gbitem_id"));
				Group_BuyVO.setGb_min(rs.getInt("gb_min"));
				Group_BuyVO.setGb_amount(rs.getInt("gb_amount"));
				Group_BuyVO.setGbstart_date(rs.getTimestamp("gbstart_date"));
				Group_BuyVO.setGbend_date(rs.getTimestamp("gbend_date"));
				Group_BuyVO.setGb_status(rs.getInt("gb_status"));
				Group_BuyVO.setGb_price(rs.getInt("gb_price"));
				Group_BuyVO.setGb_name(rs.getString("gb_name"));
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
		return Group_BuyVO;
	}

	@Override
	public List<Group_BuyVO> getAll() {
		List<Group_BuyVO> list = new ArrayList<Group_BuyVO>();
		Group_BuyVO Group_BuyVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Group_BuyVO = new Group_BuyVO();
				Group_BuyVO.setGb_id(rs.getInt("gb_id"));
				Group_BuyVO.setMem_id(rs.getInt("mem_id"));
				Group_BuyVO.setGbitem_id(rs.getInt("gbitem_id"));
				Group_BuyVO.setGb_min(rs.getInt("gb_min"));
				Group_BuyVO.setGb_amount(rs.getInt("gb_amount"));
				Group_BuyVO.setGbstart_date(rs.getTimestamp("gbstart_date"));
				Group_BuyVO.setGbend_date(rs.getTimestamp("gbend_date"));
				Group_BuyVO.setGb_status(rs.getInt("gb_status"));
				Group_BuyVO.setGb_price(rs.getInt("gb_price"));
				Group_BuyVO.setGb_name(rs.getString("gb_name"));
				list.add(Group_BuyVO); 
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
	@Override
	public List<Group_BuyVO> joinGBIGetAll() {
		List<Group_BuyVO> list = new ArrayList<Group_BuyVO>();
		Group_BuyVO Group_BuyVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GROUP_BUY_LEFT_JOIN_GROUP_BUY_ITEM_GET_ALL);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Group_BuyVO = new Group_BuyVO();
				Group_BuyVO.setGb_id(rs.getInt("gb_id"));
				Group_BuyVO.setMem_id(rs.getInt("mem_id"));
				Group_BuyVO.setGbitem_id(rs.getInt("gbitem_id"));
				Group_BuyVO.setGb_min(rs.getInt("gb_min"));
				Group_BuyVO.setGb_amount(rs.getInt("gb_amount"));
				Group_BuyVO.setGbstart_date(rs.getTimestamp("gbstart_date"));
				Group_BuyVO.setGbend_date(rs.getTimestamp("gbend_date"));
				Group_BuyVO.setGb_status(rs.getInt("gb_status"));
				Group_BuyVO.setGb_price(rs.getInt("gb_price"));
				Group_BuyVO.setGb_name(rs.getString("gb_name"));
				Group_BuyVO.setGbitem_name(rs.getString("gbitem_name"));
				Group_BuyVO.setGbitem_content(rs.getString("gbitem_content"));
				Group_BuyVO.setGbitem_price(rs.getInt("gbitem_price"));
				Group_BuyVO.setGbitem_status(rs.getInt("gbitem_status"));
				Group_BuyVO.setGbitem_startdate(rs.getDate("gbitem_startdate"));
				Group_BuyVO.setGbitem_enddate(rs.getDate("gbitem_enddate"));
				Group_BuyVO.setGbitem_type(rs.getInt("gbitem_type"));
				
				list.add(Group_BuyVO); 
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
	
	public List<Group_BuyVO> joinGBIGetAllWhereMemID(Integer mem_id) {
		List<Group_BuyVO> list = new ArrayList<Group_BuyVO>();
		Group_BuyVO Group_BuyVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GROUP_BUY_LEFT_JOIN_GROUP_BUY_ITEM_GET_ALL_WHERE_MEMID);
			pstmt.setInt(1, mem_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Group_BuyVO = new Group_BuyVO();
				Group_BuyVO.setGb_id(rs.getInt("gb_id"));
				Group_BuyVO.setMem_id(rs.getInt("mem_id"));
				Group_BuyVO.setGbitem_id(rs.getInt("gbitem_id"));
				Group_BuyVO.setGb_min(rs.getInt("gb_min"));
				Group_BuyVO.setGb_amount(rs.getInt("gb_amount"));
				Group_BuyVO.setGbstart_date(rs.getTimestamp("gbstart_date"));
				Group_BuyVO.setGbend_date(rs.getTimestamp("gbend_date"));
				Group_BuyVO.setGb_status(rs.getInt("gb_status"));
				Group_BuyVO.setGb_price(rs.getInt("gb_price"));
				Group_BuyVO.setGb_name(rs.getString("gb_name"));
				Group_BuyVO.setGbitem_name(rs.getString("gbitem_name"));
				Group_BuyVO.setGbitem_content(rs.getString("gbitem_content"));
				Group_BuyVO.setGbitem_price(rs.getInt("gbitem_price"));
				Group_BuyVO.setGbitem_status(rs.getInt("gbitem_status"));
				Group_BuyVO.setGbitem_startdate(rs.getDate("gbitem_startdate"));
				Group_BuyVO.setGbitem_enddate(rs.getDate("gbitem_enddate"));
				Group_BuyVO.setGbitem_type(rs.getInt("gbitem_type"));
				
				list.add(Group_BuyVO); 
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
	
	
	
	@Override
	public List<Group_BuyVO> getAll2InProgress() {
		List<Group_BuyVO> list = new ArrayList<Group_BuyVO>();
		Group_BuyVO Group_BuyVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT_GB_STATUS_NEED_CHANGE_TO_IN_PROGRESS);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Group_BuyVO = new Group_BuyVO();
				Group_BuyVO.setGb_id(rs.getInt("gb_id"));
				Group_BuyVO.setMem_id(rs.getInt("mem_id"));
				Group_BuyVO.setGbitem_id(rs.getInt("gbitem_id"));
				Group_BuyVO.setGb_min(rs.getInt("gb_min"));
				Group_BuyVO.setGb_amount(rs.getInt("gb_amount"));
				Group_BuyVO.setGbstart_date(rs.getTimestamp("gbstart_date"));
				Group_BuyVO.setGbend_date(rs.getTimestamp("gbend_date"));
				Group_BuyVO.setGb_status(rs.getInt("gb_status"));
				Group_BuyVO.setGb_price(rs.getInt("gb_price"));
				Group_BuyVO.setGb_name(rs.getString("gb_name"));
				list.add(Group_BuyVO); 
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
	@Override
	public List<Group_BuyVO> getAll2End() {
		List<Group_BuyVO> list = new ArrayList<Group_BuyVO>();
		Group_BuyVO Group_BuyVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT_GB_STATUS_NEED_CHANGE_TO_END);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Group_BuyVO = new Group_BuyVO();
				Group_BuyVO.setGb_id(rs.getInt("gb_id"));
				Group_BuyVO.setMem_id(rs.getInt("mem_id"));
				Group_BuyVO.setGbitem_id(rs.getInt("gbitem_id"));
				Group_BuyVO.setGb_min(rs.getInt("gb_min"));
				Group_BuyVO.setGb_amount(rs.getInt("gb_amount"));
				Group_BuyVO.setGbstart_date(rs.getTimestamp("gbstart_date"));
				Group_BuyVO.setGbend_date(rs.getTimestamp("gbend_date"));
				Group_BuyVO.setGb_status(rs.getInt("gb_status"));
				Group_BuyVO.setGb_price(rs.getInt("gb_price"));
				Group_BuyVO.setGb_name(rs.getString("gb_name"));
				list.add(Group_BuyVO); 
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
	
	
	
	@Override
	public List<Group_BuyVO> findByMemID(Integer mem_id) {
		Group_BuyVO Group_BuyVO = null;
		List<Group_BuyVO> list = new ArrayList<Group_BuyVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT_MEMID);

			pstmt.setInt(1, mem_id);

			rs = pstmt.executeQuery();

			
			while (rs.next()) {
				Group_BuyVO = new Group_BuyVO();
				Group_BuyVO.setGb_id(rs.getInt("gb_id"));
				Group_BuyVO.setMem_id(rs.getInt("mem_id"));
				Group_BuyVO.setGbitem_id(rs.getInt("gbitem_id"));
				Group_BuyVO.setGb_min(rs.getInt("gb_min"));
				Group_BuyVO.setGb_amount(rs.getInt("gb_amount"));
				Group_BuyVO.setGbstart_date(rs.getTimestamp("gbstart_date"));
				Group_BuyVO.setGbend_date(rs.getTimestamp("gbend_date"));
				Group_BuyVO.setGb_status(rs.getInt("gb_status"));
				Group_BuyVO.setGb_price(rs.getInt("gb_price"));
				Group_BuyVO.setGb_name(rs.getString("gb_name"));
				list.add(Group_BuyVO);
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
	
	@Override
	public void updateGbprice(Group_BuyVO Group_BuyVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_GB_PRICE);

			pstmt.setInt(1, Group_BuyVO.getGb_price());
			pstmt.setInt(2, Group_BuyVO.getGb_id());

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
	public void updateGbAmount(Group_BuyVO Group_BuyVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_GB_AMOUNT);
			
			pstmt.setInt(1, Group_BuyVO.getGb_amount());
			pstmt.setInt(2, Group_BuyVO.getGb_id());
			
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
	public void updateGbStatus(Group_BuyVO Group_BuyVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_GB_STATUS);
			
			pstmt.setInt(1, Group_BuyVO.getGb_status());
			pstmt.setInt(2, Group_BuyVO.getGb_id());
			
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
	
	
	
	
	

	
//	
	public static void main(String[] args) {

		Group_BuyJDBCDAO dao = new Group_BuyJDBCDAO();

		// 新增
//		Group_BuyVO gbVO1 = new Group_BuyVO();
//		gbVO1.setMem_id(3);
//		gbVO1.setGbitem_id(4);
//		gbVO1.setGb_min(4);
//		gbVO1.setGb_amount(90);
//		gbVO1.setGbstart_date(Timestamp.valueOf("2022-10-16 00:00:00.0"));
//		gbVO1.setGbend_date(Timestamp.valueOf("2023-12-30 23:59:59"));
//		gbVO1.setGb_status(1);
//		gbVO1.setGb_price(999);
//		gbVO1.setGb_name("xxxx");
//		
//		dao.insert(gbVO1);

//		 修改
//		Group_BuyVO gbVO2 = new Group_BuyVO();
//		gbVO2.setMem_id(1);
//		gbVO2.setGbitem_id(4);
//		gbVO2.setGb_min(4);
//		gbVO2.setGb_amount(200180);
//		gbVO2.setGbstart_date(Timestamp.valueOf("2022-10-17 00:00:00.0"));
//		gbVO2.setGbend_date(Timestamp.valueOf("2023-12-29 23:59:59"));
//		gbVO2.setGb_status(1);
//		gbVO2.setGb_price(777);
//		gbVO2.setGb_name("yyyy");
//		gbVO2.setGb_id(112);
//		dao.update(gbVO2);

//		// 刪除
//		dao.delete(59);
//
		// 修改GbStatus
//		Group_BuyVO gbVO08 = new Group_BuyVO();
//		gbVO08.setGb_status(2);
//		gbVO08.setGb_id(118);
//		dao.updateGbStatus(gbVO08);
	
		
		
		// 查詢
//		Group_BuyVO gbVO3 = dao.findByPrimaryKey(118);
//		System.out.print(gbVO3.getGb_id() + ",");
//		System.out.print(gbVO3.getMem_id() + ",");
//		System.out.print(gbVO3.getGbitem_id() + ",");
//		System.out.print(gbVO3.getGb_min() + ",");
//		System.out.print(gbVO3.getGb_amount() + ",");
//		System.out.print(gbVO3.getGbstart_date() + ",");
//		System.out.print(gbVO3.getGbend_date() + ",");
//		System.out.println(gbVO3.getGb_status() + ",");
//		System.out.println(gbVO3.getGb_price()+ ",");
//		System.out.println(gbVO3.getGb_name());
//		System.out.println("---------------------");
//	 以MEM_ID查詢
//		Group_BuyVO gbVO4 = dao.CheckByMemID(9);
//		System.out.print(gbVO3.getMem_id());
//		System.out.println("---------------------");

		// 查詢
//		List<Group_BuyVO> list = dao.getAll();
//		for (Group_BuyVO aGb : list) {
//			System.out.print(aGb.getGb_id() + ",");
//			System.out.print(aGb.getMem_id() + ",");
//			System.out.print(aGb.getGbitem_id() + ",");
//			System.out.print(aGb.getGb_min() + ",");
//			System.out.print(aGb.getGb_amount() + ",");
//			System.out.print(aGb.getGbstart_date() + ",");
//			System.out.print(aGb.getGbend_date() + ",");
//			System.out.print(aGb.getGb_status() + ",");
//			System.out.print(aGb.getGb_price()  + ",");
//			System.out.print(aGb.getGb_name());
//			System.out.println();
//		}
		//JOIN GroupBuyItem查詢
//		List<Group_BuyVO> list = dao.joinGBIGetAll();
//		for (Group_BuyVO aGb : list) {
//			System.out.print(aGb.getGb_id() + ",");
//			System.out.print(aGb.getMem_id() + ",");
//			System.out.print(aGb.getGbitem_id() + ",");
//			System.out.print(aGb.getGb_min() + ",");
//			System.out.print(aGb.getGb_amount() + ",");
//			System.out.print(aGb.getGbstart_date() + ",");
//			System.out.print(aGb.getGbend_date() + ",");
//			System.out.print(aGb.getGb_status() + ",");
//			System.out.print(aGb.getGb_price()  + ",");
//			System.out.print(aGb.getGbitem_name()  + ",");
//			System.out.print(aGb.getGbitem_content()  + ",");
//			System.out.print(aGb.getGbitem_price()  + ",");
//			System.out.print(aGb.getGbitem_status()  + ",");
//			System.out.print(aGb.getGbitem_startdate()  + ",");
//			System.out.print(aGb.getGbitem_enddate()  + ",");
//			System.out.print(aGb.getGbitem_type()  + ",");
//			System.out.println();
//		}
		//JOIN GroupBuyItem查詢 抓MEMID
//		List<Group_BuyVO> list = dao.joinGBIGetAllWhereMemID(10);
//		for (Group_BuyVO aGb : list) {
//			System.out.print(aGb.getGb_id() + ",");
//			System.out.print(aGb.getMem_id() + ",");
//			System.out.print(aGb.getGbitem_id() + ",");
//			System.out.print(aGb.getGb_min() + ",");
//			System.out.print(aGb.getGb_amount() + ",");
//			System.out.print(aGb.getGbstart_date() + ",");
//			System.out.print(aGb.getGbend_date() + ",");
//			System.out.print(aGb.getGb_status() + ",");
//			System.out.print(aGb.getGb_price()  + ",");
//			System.out.print(aGb.getGbitem_name()  + ",");
//			System.out.print(aGb.getGbitem_content()  + ",");
//			System.out.print(aGb.getGbitem_price()  + ",");
//			System.out.print(aGb.getGbitem_status()  + ",");
//			System.out.print(aGb.getGbitem_startdate()  + ",");
//			System.out.print(aGb.getGbitem_enddate()  + ",");
//			System.out.print(aGb.getGbitem_type()  + ",");
//			System.out.println();
//		}
	//查詢團購狀態等於0的(團購尚未開始)，而且團購開始時間大於現在時間(為了要更改狀態為團購進行中)
//		List<Group_BuyVO> list = dao.getAll2InProgress();
//		for (Group_BuyVO aGb : list) {
//			System.out.print(aGb.getGb_id() + ",");
//			System.out.print(aGb.getMem_id() + ",");
//			System.out.print(aGb.getGbitem_id() + ",");
//			System.out.print(aGb.getGb_min() + ",");
//			System.out.print(aGb.getGb_amount() + ",");
//			System.out.print(aGb.getGbstart_date() + ",");
//			System.out.print(aGb.getGbend_date() + ",");
//			System.out.print(aGb.getGb_status() + ",");
//			System.out.print(aGb.getGb_price()  + ",");
//			System.out.print(aGb.getGbitem_name()  + ",");
//			System.out.print(aGb.getGbitem_content()  + ",");
//			System.out.print(aGb.getGbitem_price()  + ",");
//			System.out.print(aGb.getGbitem_status()  + ",");
//			System.out.print(aGb.getGbitem_startdate()  + ",");
//			System.out.print(aGb.getGbitem_enddate()  + ",");
//			System.out.print(aGb.getGbitem_type()  + ",");
//			System.out.println();
//		}
		//查詢團購狀態等於1的(團購進行中)，而且現在時間>=團購結束時間(為了要更改狀態為團購關閉OR結束)
//		List<Group_BuyVO> list11 = dao.getAll2End();
//		for (Group_BuyVO aGb : list) {
//			System.out.print(aGb.getGb_id() + ",");
//			System.out.print(aGb.getMem_id() + ",");
//			System.out.print(aGb.getGbitem_id() + ",");
//			System.out.print(aGb.getGb_min() + ",");
//			System.out.print(aGb.getGb_amount() + ",");
//			System.out.print(aGb.getGbstart_date() + ",");
//			System.out.print(aGb.getGbend_date() + ",");
//			System.out.print(aGb.getGb_status() + ",");
//			System.out.print(aGb.getGb_price()  + ",");
//			System.out.print(aGb.getGbitem_name()  + ",");
//			System.out.print(aGb.getGbitem_content()  + ",");
//			System.out.print(aGb.getGbitem_price()  + ",");
//			System.out.print(aGb.getGbitem_status()  + ",");
//			System.out.print(aGb.getGbitem_startdate()  + ",");
//			System.out.print(aGb.getGbitem_enddate()  + ",");
//			System.out.print(aGb.getGbitem_type()  + ",");
//			System.out.println();
//		}
		
		
		
		
		// 修改Gbprice
//		Group_BuyVO gbVO5 = new Group_BuyVO();
//		gbVO5.setGb_price(888);
//		gbVO5.setGb_id(92);
//		dao.updateGbprice(gbVO5);
	
	// 修改GbAmount
//		Group_BuyVO gbVO7 = new Group_BuyVO();
//		gbVO7.setGb_amount(120);
//		gbVO7.setGb_id(9);
//		dao.updateGbAmount(gbVO7);

	
		
		// 以mem_id查詢
//		List<Group_BuyVO> list2 = dao.findByMemID(1);
//		for (Group_BuyVO gbVO8 : list2) {
//			System.out.print(gbVO8.getGb_id() + ",");
//			System.out.print(gbVO8.getMem_id() + ",");
//			System.out.print(gbVO8.getGbitem_id() + ",");
//			System.out.print(gbVO8.getGb_min() + ",");
//			System.out.print(gbVO8.getGb_amount() + ",");
//			System.out.print(gbVO8.getGbstart_date() + ",");
//			System.out.print(gbVO8.getGbend_date() + ",");
//			System.out.print(gbVO8.getGb_status() + ",") ;
//			System.out.print(gbVO8.getGb_price() + ",");
//			System.out.print(gbVO8.getGb_name());
//			System.out.println();
//		}
//		
		
//	 查詢
//	Group_BuyVO gbVO6 = dao.findLast();
//	System.out.print(gbVO6.getGb_id() + ",");
//	System.out.print(gbVO6.getMem_id() + ",");
//	System.out.print(gbVO6.getGbitem_id() + ",");
//	System.out.print(gbVO6.getGb_min() + ",");
//	System.out.print(gbVO6.getGb_amount() + ",");
//	System.out.print(gbVO6.getGbstart_date() + ",");
//	System.out.print(gbVO6.getGbend_date() + ",");
//	System.out.println(gbVO6.getGb_status() + ",");
//	System.out.println(gbVO6.getGb_price());
//	System.out.println(gbVO6.getGb_name());
//	System.out.println("---------------------");
//	
//	
	}

}
