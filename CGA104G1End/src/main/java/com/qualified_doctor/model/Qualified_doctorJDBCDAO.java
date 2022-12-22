package com.qualified_doctor.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Qualified_doctorJDBCDAO implements Qualified_doctorDAO_interface {
	
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/Ba_Rei?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";

	private static final String INSERT_STMT = 
		"INSERT INTO qualified_doctor (mem_id,doc_status) VALUES (?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT doc_id,mem_id,doc_status FROM qualified_doctor order by doc_id";
	private static final String GET_ONE_STMT = 
		"SELECT doc_id,mem_id,doc_status FROM qualified_doctor where doc_id = ?";
	private static final String DELETE = 
		"DELETE FROM qualified_doctor where doc_id = ?";
	private static final String UPDATE = 
		"UPDATE qualified_doctor set mem_id=?, doc_status=? where doc_id = ?";
	private static final String MEMID_CHK = 
		"SELECT mem_id FROM qualified_doctor where mem_id = ?";

	@Override
	public void insert(Qualified_doctorVO qualified_doctorVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(INSERT_STMT);
				
				pstmt.setInt(1, qualified_doctorVO.getMem_id());
				pstmt.setInt(2, qualified_doctorVO.getDoc_status());

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
	public void update(Qualified_doctorVO qualified_doctorVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(UPDATE);

				pstmt.setInt(1, qualified_doctorVO.getMem_id());
				pstmt.setInt(2, qualified_doctorVO.getDoc_status());
				pstmt.setInt(3, qualified_doctorVO.getDoc_id());
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
	public void delete(Integer doc_id) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(DELETE);

				pstmt.setInt(1, doc_id);

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
	public Qualified_doctorVO findByPrimaryKey(Integer doc_id) {
		Qualified_doctorVO qualified_doctorVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, doc_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				qualified_doctorVO = new Qualified_doctorVO();
				qualified_doctorVO.setDoc_id(rs.getInt("doc_id"));
				qualified_doctorVO.setMem_id(rs.getInt("mem_id"));
				qualified_doctorVO.setDoc_status(rs.getInt("doc_status"));

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
		return qualified_doctorVO;
	}

	@Override
	public List<Qualified_doctorVO> getAll() {
		List<Qualified_doctorVO> list = new ArrayList<Qualified_doctorVO>();
		Qualified_doctorVO qualified_doctorVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				qualified_doctorVO = new Qualified_doctorVO();
				qualified_doctorVO.setDoc_id(rs.getInt("doc_id"));
				qualified_doctorVO.setMem_id(rs.getInt("mem_id"));
				qualified_doctorVO.setDoc_status(rs.getInt("doc_status"));


				list.add(qualified_doctorVO); // Store the row in the list
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
	public Qualified_doctorVO memidChk(Integer mem_id) {
		Qualified_doctorVO qualified_doctorVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(MEMID_CHK);

			pstmt.setInt(1, mem_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				qualified_doctorVO = new Qualified_doctorVO();

				qualified_doctorVO.setMem_id(rs.getInt("mem_id"));

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
		return qualified_doctorVO;
	}

	public static void main(String[] args) {
		Qualified_doctorJDBCDAO dao = new Qualified_doctorJDBCDAO();

		
//		// 新增
//		Qualified_doctorVO qualified_doctorVO1 = new Qualified_doctorVO();
//		qualified_doctorVO1.setMem_id(2);
//		qualified_doctorVO1.setDoc_status(0);
//		dao.insert(qualified_doctorVO1);
//
//		// 修改
		Qualified_doctorVO qualified_doctorVO2 = new Qualified_doctorVO();
		qualified_doctorVO2.setMem_id(2);
		qualified_doctorVO2.setDoc_status(0);
		qualified_doctorVO2.setDoc_id(3);
		dao.update(qualified_doctorVO2);
//
//		// 刪除
//		dao.delete(2);
//
//		// 查詢
//		Qualified_doctorVO qualified_doctorVO3 = dao.findByPrimaryKey(1);
//		System.out.print(qualified_doctorVO3.getDoc_id() + ",");
//		System.out.print(qualified_doctorVO3.getMem_id() + ",");
//		System.out.print(qualified_doctorVO3.getDoc_status() + ",");
//	
//
//
//		System.out.println("---------------------");
//
//		// 查詢
//		List<Qualified_doctorVO> list = dao.getAll();
//		for (Qualified_doctorVO aqualified_doctorVO : list) {
//			System.out.print(aqualified_doctorVO.getDoc_id() + ",");
//			System.out.print(aqualified_doctorVO.getMem_id() + ",");
//			System.out.print(aqualified_doctorVO.getDoc_status() + ",");
//			System.out.println();
//		}
}
}
