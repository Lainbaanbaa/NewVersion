//package com.group_buy_report.model;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.*;
//
//import javax.naming.Context;
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
//import javax.sql.DataSource;
//
//
//public class Group_Buy_ReportDAO implements Group_Buy_ReportDAO_interface {
//
//	private static DataSource ds = null;
//	static {
//		try {
//			Context ctx = new InitialContext();
//			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/hikariCP-BaRei");
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	private static final String INSERT_STMT = 
//			"INSERT INTO GROUP_BUY_REPORT (MEM_ID, FREP_CONTENT, FREP_STATUS, FREP_RESULT, EMP_ID) VALUES (?, ?, ?, ?, ?)";
//	private static final String GET_ALL_STMT = 
//			"SELECT GBFREP_ID,MEM_ID, FREP_CONTENT, FREP_STATUS, FREP_RESULT, EMP_ID FROM GROUP_BUY_REPORT order by GBFREP_ID";
//	private static final String GET_ONE_STMT = 
//			"SELECT GBFREP_ID,MEM_ID, FREP_CONTENT, FREP_STATUS, FREP_RESULT, EMP_ID FROM GROUP_BUY_REPORT where GBFREP_ID = ?";
//	private static final String DELETE = 
//			"DELETE FROM GROUP_BUY_REPORT where GBFREP_ID = ?";
//	private static final String UPDATE = 
//			"UPDATE GROUP_BUY_REPORT set MEM_ID=?, FREP_CONTENT=?, FREP_STATUS=?, FREP_RESULT=?, EMP_ID=? where GBFREP_ID = ?";
//	
//	
//	@Override
//	public void insert(Group_Buy_ReportVO Group_Buy_ReportVO) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(INSERT_STMT);
//
//			pstmt.setInt(1, Group_Buy_ReportVO.getGborder_id());
//			pstmt.setInt(2, Group_Buy_ReportVO.getMem_id());
//			pstmt.setString(3, Group_Buy_ReportVO.getFrep_content());
//			pstmt.setInt(4, Group_Buy_ReportVO.getFrep_status());
//			pstmt.setInt(5, Group_Buy_ReportVO.getFrep_result());
//			pstmt.setInt(6, Group_Buy_ReportVO.getEmp_id());
//
//			pstmt.executeUpdate();
//
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
//	@Override
//	public void update(Group_Buy_ReportVO Group_Buy_ReportVO) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(UPDATE);
//
//			pstmt.setInt(1, Group_Buy_ReportVO.getGborder_id());
//			pstmt.setInt(2, Group_Buy_ReportVO.getMem_id());
//			pstmt.setString(3, Group_Buy_ReportVO.getFrep_content());
//			pstmt.setInt(4, Group_Buy_ReportVO.getFrep_status());
//			pstmt.setInt(5, Group_Buy_ReportVO.getFrep_result());
//			pstmt.setInt(6, Group_Buy_ReportVO.getEmp_id());
//			pstmt.setInt(7, Group_Buy_ReportVO.getGbfrep_id());
//			pstmt.executeUpdate();
//
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
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
//	@Override
//	public void delete(Integer gbfrep_id) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(DELETE);
//
//			pstmt.setInt(1, gbfrep_id);
//
//			pstmt.executeUpdate();
//
//			// Handle any driver errors
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
//	@Override
//	public Group_Buy_ReportVO findByPrimaryKey(Integer gbfrep_id) {
//		Group_Buy_ReportVO Group_Buy_ReportVO = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(GET_ONE_STMT);
//
//			pstmt.setInt(1, gbfrep_id);
//
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				Group_Buy_ReportVO = new Group_Buy_ReportVO();
//				Group_Buy_ReportVO.setGborder_id(rs.getInt("gborder_id"));
//				Group_Buy_ReportVO.setGbfrep_id(rs.getInt("gbfrep_id"));
//				Group_Buy_ReportVO.setMem_id(rs.getInt("mem_id"));
//				Group_Buy_ReportVO.setFrep_content(rs.getString("frep_content"));
//				Group_Buy_ReportVO.setFrep_time(rs.getTimestamp("frep_time"));
//				Group_Buy_ReportVO.setFrep_status(rs.getInt("frep_status"));
//				Group_Buy_ReportVO.setFrep_result(rs.getInt("frep_result"));
//				Group_Buy_ReportVO.setEmp_id(rs.getInt("emp_id"));
//			}
//
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
//		return Group_Buy_ReportVO;
//	}
//	@Override
//	public List<Group_Buy_ReportVO> getAll() {
//		List<Group_Buy_ReportVO> list = new ArrayList<Group_Buy_ReportVO>();
//		Group_Buy_ReportVO Group_Buy_ReportVO = null;
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(GET_ALL_STMT);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				Group_Buy_ReportVO = new Group_Buy_ReportVO();
//				Group_Buy_ReportVO.setGborder_id(rs.getInt("gborder_id"));
//				Group_Buy_ReportVO.setGbfrep_id(rs.getInt("gbfrep_id"));
//				Group_Buy_ReportVO.setMem_id(rs.getInt("mem_id"));
//				Group_Buy_ReportVO.setFrep_content(rs.getString("frep_content"));
//				Group_Buy_ReportVO.setFrep_time(rs.getTimestamp("frep_time"));
//				Group_Buy_ReportVO.setFrep_status(rs.getInt("frep_status"));
//				Group_Buy_ReportVO.setFrep_result(rs.getInt("frep_result"));
//				Group_Buy_ReportVO.setEmp_id(rs.getInt("emp_id"));
//				list.add(Group_Buy_ReportVO); 
//			}
//
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
//}
