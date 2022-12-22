
package com.verify_code.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Verify_codeJDBCDAO implements Verify_codeDAO_interface{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/Ba_Rei?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";

	private static final String INSERT_STMT = 
		"INSERT INTO verify_code (mem_id,vc_code) VALUES (?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT vc_id,mem_id,vc_code,vc_time FROM verify_code order by vc_id ";
	private static final String GET_ONE_STMT  = 
		"SELECT vc_id,mem_id,vc_code,vc_time FROM verify_code where vc_id = ?";
	private static final String DELETE = 
		"DELETE FROM verify_code where mem_id = ?";
	private static final String UPDATE = 
		"UPDATE verify_code set mem_id=?, vc_code=?  where vc_id = ?";
	private static final String GET_ONE_CHECK = 
		"SELECT * FROM VERIFY_CODE WHERE VC_ID = (select MAX(VC_ID) FROM VERIFY_CODE WHERE mem_id = ?)";
	

	@Override
	public void insert(Verify_codeVO verify_codeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, verify_codeVO.getMem_id());
			pstmt.setString(2, verify_codeVO.getVc_code());
//			pstmt.setTimestamp(3, verify_codeVO.getVc_time());


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
	public void update(Verify_codeVO verify_codeVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, verify_codeVO.getMem_id());
			pstmt.setString(2, verify_codeVO.getVc_code());
//			pstmt.setTimestamp(3, verify_codeVO.getVc_time());
			pstmt.setInt(3, verify_codeVO.getVc_id());


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
	public void delete(Integer mem_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, mem_id);

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
	public Verify_codeVO findByPrimaryKey(Integer vc_id) {
		
		Verify_codeVO verify_codeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_CHECK);

			pstmt.setInt(1, vc_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				verify_codeVO = new Verify_codeVO();
				verify_codeVO.setVc_id(rs.getInt("vc_id"));
				verify_codeVO.setMem_id(rs.getInt("mem_id"));
				verify_codeVO.setVc_code(rs.getString("vc_code"));
				verify_codeVO.setVc_time(rs.getTimestamp("vc_time"));


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
		return verify_codeVO;
	}

	@Override
	public List<Verify_codeVO> getAll() {
		List<Verify_codeVO> list = new ArrayList<Verify_codeVO>();
		Verify_codeVO verify_codeVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				verify_codeVO = new Verify_codeVO();

				verify_codeVO.setVc_id(rs.getInt("vc_id"));
				verify_codeVO.setMem_id(rs.getInt("mem_id"));
				verify_codeVO.setVc_code(rs.getString("vc_code"));
				verify_codeVO.setVc_time(rs.getTimestamp("vc_time"));

				list.add(verify_codeVO); // Store the row in the list
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
	
	
	@Override
	public Verify_codeVO checkVerify_code(Integer mem_id) {
		
		Verify_codeVO verify_codeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_CHECK);

			pstmt.setInt(1, mem_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				verify_codeVO = new Verify_codeVO();

				verify_codeVO.setVc_id(rs.getInt("vc_id"));
				verify_codeVO.setMem_id(rs.getInt("mem_id"));
				verify_codeVO.setVc_code(rs.getString("vc_code"));
				verify_codeVO.setVc_time(rs.getTimestamp("vc_time"));


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
		return verify_codeVO;
	}

	
	public static void main(String[] args) {
		Verify_codeJDBCDAO dao = new Verify_codeJDBCDAO();

		
//		// 新增
//		Verify_codeVO verify_codeVO1 = new Verify_codeVO();
//		verify_codeVO1.setMem_id(2);
//		verify_codeVO1.setVc_code("test");	
//
//		dao.insert(verify_codeVO1);
//
//	
//		// 修改
//		Verify_codeVO verify_codeVO2 = new Verify_codeVO();
//		verify_codeVO2.setMem_id(2);
//		verify_codeVO2.setVc_code("test");	
//		verify_codeVO2.setVc_id(1);
//		dao.update(verify_codeVO2);
//
//		// 刪除
//		dao.delete(4);
//
//		// 查詢
//		Verify_codeVO verify_codeVO3 = dao.findByPrimaryKey(1);
//		System.out.print(verify_codeVO3.getVc_id() + ",");
//		System.out.print(verify_codeVO3.getMem_id() + ",");
//		System.out.print(verify_codeVO3.getVc_code() + ",");
//		System.out.println(verify_codeVO3.getVc_time() );
//
//
//		System.out.println("---------------------");
//
//		// 查詢
//		List<Verify_codeVO> list = dao.getAll();
//		for (Verify_codeVO averify_codeVO : list) {
//			System.out.print(averify_codeVO.getVc_id() );
//			System.out.print(averify_codeVO.getMem_id() + ",");
//			System.out.print(averify_codeVO.getVc_code() + ",");
//			System.out.println(averify_codeVO.getVc_time());
//			System.out.println();
//		}
//		
//		// 查詢
//		Verify_codeVO verify_codeVO4 = dao.checkVerify_code(2);
//		System.out.print(verify_codeVO4.getVc_id() + ",");
//		System.out.print(verify_codeVO4.getMem_id() + ",");
//		System.out.print(verify_codeVO4.getVc_code() + ",");
//		System.out.println(verify_codeVO4.getVc_time() );

		
		
	}
	


}