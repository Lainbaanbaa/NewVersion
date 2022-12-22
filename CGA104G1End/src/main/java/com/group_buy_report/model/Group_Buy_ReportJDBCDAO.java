package com.group_buy_report.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class Group_Buy_ReportJDBCDAO implements Group_Buy_ReportDAO_interface{

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/ba_rei?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";
	
	
	private static final String INSERT_STMT = 
			"INSERT INTO GROUP_BUY_REPORT (GBORDER_ID, MEM_ID, FREP_CONTENT, EMP_ID) VALUES (?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT GBFREP_ID,GBORDER_ID,MEM_ID, FREP_CONTENT, FREP_TIME, FREP_STATUS, FREP_RESULT, EMP_ID FROM GROUP_BUY_REPORT order by GBFREP_ID";
	private static final String GET_ONE_STMT = 
			"SELECT GBFREP_ID,GBORDER_ID,MEM_ID, FREP_CONTENT, FREP_TIME, FREP_STATUS, FREP_RESULT, EMP_ID FROM GROUP_BUY_REPORT where GBFREP_ID = ?";
	private static final String DELETE = 
			"DELETE FROM GROUP_BUY_REPORT where GBFREP_ID = ?";
	private static final String UPDATE = 
			"UPDATE GROUP_BUY_REPORT set GBORDER_ID = ? , MEM_ID=?, FREP_CONTENT=?,  FREP_RESULT=?, EMP_ID=? where GBFREP_ID = ?";
	private static final String UPDATESTATUS0 = 
			"UPDATE GROUP_BUY_REPORT set FREP_STATUS=0 ,FREP_RESULT=? where GBORDER_ID=? and MEM_ID = ? ";
	private static final String UPDATESTATUS1 = 
			"UPDATE GROUP_BUY_REPORT set FREP_STATUS=1 ,FREP_RESULT=? where GBORDER_ID=? and MEM_ID = ? ";

	
	
	
	@Override
	public void insert(Group_Buy_ReportVO Group_Buy_ReportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, Group_Buy_ReportVO.getGborder_id());
			pstmt.setInt(2, Group_Buy_ReportVO.getMem_id());
			pstmt.setString(3, Group_Buy_ReportVO.getFrep_content());
			pstmt.setInt(4, Group_Buy_ReportVO.getEmp_id());
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
	public void update(Group_Buy_ReportVO Group_Buy_ReportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, Group_Buy_ReportVO.getGborder_id());
			pstmt.setInt(2, Group_Buy_ReportVO.getMem_id());
			pstmt.setString(3, Group_Buy_ReportVO.getFrep_content());
//			pstmt.setInt(4, Group_Buy_ReportVO.getFrep_status());
			pstmt.setInt(4, Group_Buy_ReportVO.getFrep_result());
			pstmt.setInt(5, Group_Buy_ReportVO.getEmp_id());
			pstmt.setInt(6, Group_Buy_ReportVO.getGbfrep_id());

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
	public void updatestatus0(Integer frep_result,Integer gborder_id,Integer mem_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATESTATUS0);
			

			pstmt.setInt(1, frep_result);
			pstmt.setInt(2, gborder_id);
			pstmt.setInt(3, mem_id);
			
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

	
	public void updatestatus1(Integer frep_result,Integer gborder_id,Integer mem_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATESTATUS1);
			
			
			pstmt.setInt(1, frep_result);
			pstmt.setInt(2, gborder_id);
			pstmt.setInt(3, mem_id);
			
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
	
//	public void updatestatus1(Integer frep_result,Integer gbfrep_id,Integer emp_id) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		
//		try {
//			
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(UPDATESTATUS0);
//			
//			
//			pstmt.setInt(1, frep_result);
//			pstmt.setInt(2, gbfrep_id);
//			pstmt.setInt(3, emp_id);
//			
//			pstmt.executeUpdate();
//			
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. "
//					+ e.getMessage());
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
//	
	
	@Override
	public void delete(Integer gbfrep_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, gbfrep_id);

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
	public Group_Buy_ReportVO findByPrimaryKey(Integer gbfrep_id) {
		Group_Buy_ReportVO Group_Buy_ReportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, gbfrep_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Group_Buy_ReportVO = new Group_Buy_ReportVO();
				Group_Buy_ReportVO.setGbfrep_id(rs.getInt("gbfrep_id"));
				Group_Buy_ReportVO.setGborder_id(rs.getInt("gborder_id"));				
				Group_Buy_ReportVO.setMem_id(rs.getInt("mem_id"));
				Group_Buy_ReportVO.setFrep_content(rs.getString("frep_content"));
				Group_Buy_ReportVO.setFrep_time(rs.getTimestamp("frep_time"));				
				Group_Buy_ReportVO.setFrep_status(rs.getInt("frep_status"));
				Group_Buy_ReportVO.setFrep_result(rs.getInt("frep_result"));
				Group_Buy_ReportVO.setEmp_id(rs.getInt("emp_id"));
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
		return Group_Buy_ReportVO;
	}

	@Override
	public List<Group_Buy_ReportVO> getAll() {
		List<Group_Buy_ReportVO> list = new ArrayList<Group_Buy_ReportVO>();
		Group_Buy_ReportVO Group_Buy_ReportVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Group_Buy_ReportVO = new Group_Buy_ReportVO();
				Group_Buy_ReportVO.setGbfrep_id(rs.getInt("gbfrep_id"));
				Group_Buy_ReportVO.setGborder_id(rs.getInt("gborder_id"));	
				Group_Buy_ReportVO.setMem_id(rs.getInt("mem_id"));
				Group_Buy_ReportVO.setFrep_content(rs.getString("frep_content"));
				Group_Buy_ReportVO.setFrep_time(rs.getTimestamp("frep_time"));	
				Group_Buy_ReportVO.setFrep_status(rs.getInt("frep_status"));
				Group_Buy_ReportVO.setFrep_result(rs.getInt("frep_result"));
				Group_Buy_ReportVO.setEmp_id(rs.getInt("emp_id"));
				list.add(Group_Buy_ReportVO); 
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
		return list;
	}
	public static void main(String[] args) {

		Group_Buy_ReportJDBCDAO dao = new Group_Buy_ReportJDBCDAO();

		// 新增
		Group_Buy_ReportVO gbrVO1 = new Group_Buy_ReportVO();
		gbrVO1.setGborder_id(1);
		gbrVO1.setMem_id(1);
		gbrVO1.setFrep_content("ggg");
		gbrVO1.setFrep_status(0);
		gbrVO1.setFrep_result(0);
		gbrVO1.setEmp_id(1);
		dao.insert(gbrVO1);

//		// 修改
//		Group_Buy_ReportVO gbrVO2 = new Group_Buy_ReportVO();
//		gbrVO2.setMem_id(1);
//		gbrVO2.setFrep_content("xxxxggxxxx");
//		gbrVO2.setFrep_status(1);
//		gbrVO2.setFrep_result(2);
//		gbrVO2.setEmp_id(1);
//		gbrVO2.setGbfrep_id(5);
//		dao.update(gbrVO2);
//
//		// 刪除
//		dao.delete(4);

//		// 查詢
//		Group_Buy_ReportVO gbrVO3 = dao.findByPrimaryKey(1);
//		System.out.print(gbrVO3.getGbfrep_id() + ",");
//		System.out.print(gbrVO3.getMem_id() + ",");
//		System.out.print(gbrVO3.getFrep_content() + ",");
//		System.out.print(gbrVO3.getFrep_status() + ",");
//		System.out.print(gbrVO3.getFrep_result() + ",");
//		System.out.println(gbrVO3.getEmp_id());
//		System.out.println("---------------------");
//
//		// 查詢
//		List<Group_Buy_ReportVO> list = dao.getAll();
//		for (Group_Buy_ReportVO aGbr : list) {
//			System.out.print(aGbr.getGbfrep_id() + ",");
//			System.out.print(aGbr.getGborder_id() + ",");
//			System.out.print(aGbr.getMem_id() + ",");
//			System.out.print(aGbr.getFrep_content() + ",");
//			System.out.print(aGbr.getFrep_time() + ",");
//			System.out.print(aGbr.getFrep_status() + ",");
//			System.out.print(aGbr.getFrep_result() + ",");
//			System.out.print(aGbr.getEmp_id());
//			System.out.println();
//		}
	}

}
