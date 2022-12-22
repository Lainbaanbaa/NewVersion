package com.article_comment.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Article_commentDAO implements Article_commentDAO_interface {

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
			"INSERT INTO article_comment (article_id, mem_id, com_content) VALUES (?, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT com_id,article_id, mem_id, com_content, com_publish FROM article_comment where article_id = ? order by com_id";
		private static final String GET_ONE_STMT = 
			"SELECT com_id, article_id, mem_id, com_content, com_publish FROM article_comment where com_id = ?";
		private static final String DELETE = 
			"DELETE FROM article_comment where com_id = ?";
		private static final String UPDATE = 
			"UPDATE article_comment set com_content=? where com_id = ?";

	@Override
	public void insert(Article_commentVO article_commentVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, article_commentVO.getArticle_id());
			pstmt.setInt(2, article_commentVO.getMem_id());
			pstmt.setString(3, article_commentVO.getCom_content());

			pstmt.executeUpdate();

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
	public void update(Article_commentVO article_commentVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, article_commentVO.getCom_content());
			pstmt.setInt(2, article_commentVO.getCom_id());

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
	public void delete(Integer com_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, com_id);

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
	public Article_commentVO findByPrimaryKey(Integer com_id) {
		Article_commentVO article_commentVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, com_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				article_commentVO = new Article_commentVO();
				article_commentVO.setCom_id(rs.getInt("com_id")); 
				article_commentVO.setArticle_id(rs.getInt("article_id"));
				article_commentVO.setMem_id(rs.getInt("mem_id"));
				article_commentVO.setCom_content(rs.getString("com_content"));
				article_commentVO.setCom_publish(rs.getDate("com_publish"));
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
		return article_commentVO;
	}

	@Override
	public List<Article_commentVO> getAll(Integer article_id) {
		List<Article_commentVO> list = new ArrayList<Article_commentVO>();
		Article_commentVO article_commentVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			pstmt.setInt(1, article_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				article_commentVO = new Article_commentVO();
				article_commentVO.setArticle_id(rs.getInt("article_id"));
				article_commentVO.setCom_id(rs.getInt("com_id")); 
				article_commentVO.setMem_id(rs.getInt("mem_id"));
				article_commentVO.setCom_content(rs.getString("com_content"));
				article_commentVO.setCom_publish(rs.getDate("com_publish"));
				list.add(article_commentVO); // Store the row in the list
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

		Article_commentJDBCDAO dao = new Article_commentJDBCDAO();

		// 新增
		Article_commentVO article_commentVO1 = new Article_commentVO();
		article_commentVO1.setArticle_id(1);
		article_commentVO1.setMem_id(1);
		article_commentVO1.setCom_content("發廢文就是爽:))");
		dao.insert(article_commentVO1);

		// 修改
		Article_commentVO article_commentVO2 = new Article_commentVO();
		article_commentVO2.setCom_content("謝謝醫生!");
		article_commentVO2.setCom_id(4);
		dao.update(article_commentVO2);
//
//		// 刪除
//		dao.delete(2);
//
		// 查詢
		Article_commentVO article_commentVO3 = dao.findByPrimaryKey(1);
		System.out.print(article_commentVO3.getCom_id());
		System.out.print(article_commentVO3.getArticle_id());
		System.out.print(article_commentVO3.getMem_id());
		System.out.print(article_commentVO3.getCom_content());
		System.out.println(article_commentVO3.getCom_publish());
		System.out.println("---------------------");

		// 查詢
		List<Article_commentVO> list = dao.getAll(1);
		for (Article_commentVO aEmp : list) {
			System.out.print(aEmp.getArticle_id());
			System.out.print(aEmp.getCom_id());
			System.out.print(aEmp.getMem_id());
			System.out.print(aEmp.getCom_content());
			System.out.print(aEmp.getCom_publish());
			System.out.println();
		}
	}
}
