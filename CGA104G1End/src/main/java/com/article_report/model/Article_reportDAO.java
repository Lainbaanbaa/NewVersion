package com.article_report.model;

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

public class Article_reportDAO implements Article_reportDAO_interface{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/hikariCP-BaRei");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = 
			"INSERT INTO article_report (article_id, mem_id, afrep_content, afrep_status) VALUES (?, ?, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT afrep_id, article_id, mem_id, afrep_content, afrep_status, afrep_result, emp_id, afrep_date FROM article_report order by afrep_id";
		private static final String GET_ONE_STMT = 
			"SELECT afrep_id, article_id, mem_id, afrep_content, afrep_status, afrep_result, emp_id, afrep_date FROM article_report where afrep_id = ?";
		private static final String DELETE = 
			"DELETE FROM article_report where afrep_id = ?";
		private static final String UPDATE = 
			"UPDATE article_report set afrep_status=?, afrep_result=?, emp_id=? where afrep_id = ?";

	@Override
	public void insert(Article_reportVO article_reportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, article_reportVO.getArticle_id());
			pstmt.setInt(2, article_reportVO.getMem_id());
			pstmt.setString(3, article_reportVO.getAfrep_content());
			pstmt.setInt(4, article_reportVO.getAfrep_status());

			pstmt.executeUpdate();

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
	public void update(Article_reportVO article_reportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, article_reportVO.getAfrep_status());
			pstmt.setInt(2, article_reportVO.getAfrep_result());
			pstmt.setInt(3, article_reportVO.getEmp_id());
			pstmt.setInt(4, article_reportVO.getAfrep_id());

			pstmt.executeUpdate();

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
	public void delete(Integer afrep_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, afrep_id);

			pstmt.executeUpdate();

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
	public Article_reportVO findByPrimaryKey(Integer afrep_id) {
		Article_reportVO article_reportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, afrep_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				article_reportVO = new Article_reportVO();
				article_reportVO.setAfrep_id(rs.getInt("afrep_id"));
				article_reportVO.setArticle_id(rs.getInt("article_id"));
				article_reportVO.setMem_id(rs.getInt("mem_id"));
				article_reportVO.setAfrep_content(rs.getString("afrep_content"));
				article_reportVO.setAfrep_status(rs.getInt("afrep_status"));
				article_reportVO.setAfrep_result(rs.getInt("afrep_result"));
				article_reportVO.setEmp_id(rs.getInt("emp_id"));
				article_reportVO.setAfrep_date(rs.getDate("afrep_date"));
			}

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
		return article_reportVO;
	}

	@Override
	public List<Article_reportVO> getAll() {
		List<Article_reportVO> list = new ArrayList<Article_reportVO>();
		Article_reportVO article_reportVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				article_reportVO = new Article_reportVO();
				article_reportVO.setAfrep_id(rs.getInt("afrep_id"));
				article_reportVO.setArticle_id(rs.getInt("article_id"));
				article_reportVO.setMem_id(rs.getInt("mem_id"));
				article_reportVO.setAfrep_content(rs.getString("afrep_content"));
				article_reportVO.setAfrep_status(rs.getInt("afrep_status"));
				article_reportVO.setAfrep_result(rs.getInt("afrep_result"));
				article_reportVO.setEmp_id(rs.getInt("emp_id"));
				article_reportVO.setAfrep_date(rs.getDate("afrep_date"));
				list.add(article_reportVO); // Store the row in the list
			}

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

		Article_reportJDBCDAO dao = new Article_reportJDBCDAO();

//		// 新增
//		Article_reportVO article_reportVO1 = new Article_reportVO();
//		article_reportVO1.setArticle_id(1);
//		article_reportVO1.setMem_id(5);
//		article_reportVO1.setAfrep_content("檢舉測試");
//		article_reportVO1.setAfrep_status(2);
//		dao.insert(article_reportVO1);
//
//		// 修改
//		Article_reportVO article_reportVO2 = new Article_reportVO();
//		article_reportVO2.setAfrep_status(1);
//		article_reportVO2.setAfrep_result(1);
//		article_reportVO2.setEmp_id(1);
//		article_reportVO2.setAfrep_id(1);
//		dao.update(article_reportVO2);
//
//		// 刪除
//		dao.delete(2);
//
//		// 查詢
//		Article_reportVO article_reportVO3 = dao.findByPrimaryKey(1);
//		System.out.print(article_reportVO3.getAfrep_id());
//		System.out.print(article_reportVO3.getArticle_id());
//		System.out.print(article_reportVO3.getMem_id());
//		System.out.print(article_reportVO3.getAfrep_content());
//		System.out.print(article_reportVO3.getAfrep_status());
//		System.out.print(article_reportVO3.getAfrep_result());
//		System.out.print(article_reportVO3.getEmp_id());
//		System.out.println(article_reportVO3.getAfrep_date());
//		System.out.println("---------------------");
		

		// 查詢
		List<Article_reportVO> list = dao.getAll();
		for (Article_reportVO aEmp : list) {
			System.out.print(aEmp.getAfrep_id());
			System.out.print(aEmp.getArticle_id());
			System.out.print(aEmp.getMem_id());
			System.out.print(aEmp.getAfrep_content());
			System.out.print(aEmp.getAfrep_status());
			System.out.print(aEmp.getAfrep_result());
			System.out.print(aEmp.getEmp_id());
			System.out.println(aEmp.getAfrep_date());
			System.out.println();
		}
	}

	@Override
	public List<Article_reportVO> getAllSolved() {
		// TODO Auto-generated method stub
		return null;
	}
}
