//package com.group_join.model;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//
//public class Group_JoinJDBCDAO {
//
//	String driver = "com.mysql.cj.jdbc.Driver";
//	String url = "jdbc:mysql://localhost:3306/ba_rei?serverTimezone=Asia/Taipei";
//	String userid = "root";
//	String passwd = "password";
//	
//	private static final String INSERT_STMT = 
//			"INSERT INTO GROUP_JOIN (GB_ID, MEM_ID, GBPAY_STATUS, PICKUP_STATUS, DELIVER_STATUS) VALUES (?, ?, ?, ?, ?)";
//	private static final String GET_ALL_STMT = 
//			"SELECT GB_ID, MEM_ID, GBPAY_STATUS, PICKUP_STATUS, DELIVER_STATUS FROM GROUP_JOIN order by GB_ID";
//	private static final String GET_ONE_STMT = 
//			"SELECT GB_ID, MEM_ID, GBPAY_STATUS, PICKUP_STATUS, DELIVER_STATUS FROM GROUP_JOIN where GB_ID = ?";
//	private static final String DELETE = 
//			"DELETE FROM GROUP_JOIN where GB_ID = ?";
//	private static final String UPDATE = 
//			"UPDATE GROUP_JOIN set  GBPAY_STATUS=?, PICKUP_STATUS=?, DELIVER_STATUS=? where (GB_ID = ?) and (MEM_ID = ?)";
//
//	public void insert(Group_JoinVO Group_JoinVO) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(INSERT_STMT);
//
//			pstmt.setInt(1, Group_JoinVO.getGb_id());
//			pstmt.setInt(2, Group_JoinVO.getMem_id());
//			pstmt.setInt(3, Group_JoinVO.getGbpay_status());
//			pstmt.setInt(4, Group_JoinVO.getPickup_status());
//			pstmt.setInt(5, Group_JoinVO.getDeliver_status());
//
//			pstmt.executeUpdate();
//
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. "
//					+ e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//	}
//
//	public void update(Group_JoinVO Group_JoinVO) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(UPDATE);
//
//			pstmt.setInt(1, Group_JoinVO.getGbpay_status());
//			pstmt.setInt(2, Group_JoinVO.getPickup_status());
//			pstmt.setInt(3, Group_JoinVO.getDeliver_status());
//			pstmt.setInt(4, Group_JoinVO.getGb_id());
//			pstmt.setInt(5, Group_JoinVO.getMem_id());
//
//			pstmt.executeUpdate();
//
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. "
//					+ e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//	}
//
//
//	@Override
//	public void updatePay(Group_JoinVO Group_JoinVO) {
//
//	}
//
//	@Override
//	public void updatePickup(Group_JoinVO Group_JoinVO) {
//
//	}
//
//	@Override
//	public void updateDeliver(Group_JoinVO Group_JoinVO) {
//
//	}
//
//	@Override
//	public void deleteGj(Integer gb_id, Integer mem_id) {
//
//	}
//
//	@Override
//	public Group_JoinVO findByPrimaryKey(Integer gb_id, Integer mem_id) {
//		return null;
//	}
//
//	@Override
//	public List<Group_JoinVO> findBygbid(Integer gb_id) {
//		return null;
//	}
//
//	public void delete(Integer gb_id) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(DELETE);
//
//			pstmt.setInt(1, gb_id);
//
//			pstmt.executeUpdate();
//
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. "
//					+ e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//	}
//
//	
//	public Group_JoinVO findByPrimaryKey(Integer gb_id) {
//		
//		Group_JoinVO Group_JoinVO = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(GET_ONE_STMT);
//
//			pstmt.setInt(1, gb_id);
//
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				Group_JoinVO = new Group_JoinVO();
//				Group_JoinVO.setGb_id(rs.getInt("gb_id"));
//				Group_JoinVO.setMem_id(rs.getInt("mem_id"));
//				Group_JoinVO.setGbpay_status(rs.getInt("gbpay_status"));
//				Group_JoinVO.setPickup_status(rs.getInt("pickup_status"));
//				Group_JoinVO.setDeliver_status(rs.getInt("deliver_status"));
//			}
//
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. "
//					+ e.getMessage());
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//		return Group_JoinVO;
//	}
//
//	public List<Group_JoinVO> getAll() {
//		
//		List<Group_JoinVO> list = new ArrayList<Group_JoinVO>();
//		Group_JoinVO Group_JoinVO = null;
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(GET_ALL_STMT);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				Group_JoinVO = new Group_JoinVO();
//				Group_JoinVO.setGb_id(rs.getInt("gb_id"));
//				Group_JoinVO.setMem_id(rs.getInt("mem_id"));
//				Group_JoinVO.setGbpay_status(rs.getInt("gbpay_status"));
//				Group_JoinVO.setPickup_status(rs.getInt("pickup_status"));
//				Group_JoinVO.setDeliver_status(rs.getInt("deliver_status"));
//				list.add(Group_JoinVO);
//			}
//
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. "
//					+ e.getMessage());
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//		return list;
//	}
//
//	@Override
//	public List<Group_JoinVO> getAll(Map<String, String[]> map) {
//		return null;
//	}
//
//	public static void main(String[] args) {
//
//		Group_JoinJDBCDAO dao = new Group_JoinJDBCDAO();
//
//		// 新增
////		Group_JoinVO gjVO1 = new Group_JoinVO();
////		gjVO1.setGb_id(6);
////		gjVO1.setMem_id(3);
////		gjVO1.setGbpay_status(1);
////		gjVO1.setPickup_status(1);
////		gjVO1.setDeliver_status(1);
////		dao.insert(gjVO1);
//
////		 修改
////		Group_JoinVO gjVO2 = new Group_JoinVO();
////		gjVO2.setGbpay_status(2);
////		gjVO2.setPickup_status(2);
////		gjVO2.setDeliver_status(2);
////		gjVO2.setGb_id(6);
////		gjVO2.setMem_id(3);
////		dao.update(gjVO2);
//
////		// 刪除
//		dao.delete(6);
//
////		// 查詢
////		Group_JoinVO gjVO3 = dao.findByPrimaryKey(1);
////		System.out.print(gjVO3.getGb_id() + ",");
////		System.out.print(gjVO3.getMem_id() + ",");
////		System.out.print(gjVO3.getGbpay_status() + ",");
////		System.out.print(gjVO3.getPickup_status() + ",");
////		System.out.print(gjVO3.getDeliver_status());
////		System.out.println("---------------------");
//
//		// 查詢
//		List<Group_JoinVO> list = dao.getAll();
//		for (Group_JoinVO aGj : list) {
//			System.out.print(aGj.getGb_id() + ",");
//			System.out.print(aGj.getMem_id() + ",");
//			System.out.print(aGj.getGbpay_status() + ",");
//			System.out.print(aGj.getPickup_status() + ",");
//			System.out.print(aGj.getDeliver_status());
//			System.out.println();
//		}
//	}
//}
