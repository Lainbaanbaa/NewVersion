//package com.article.model;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.naming.Context;
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
//import javax.sql.DataSource;
//
//public class ArticleDAO implements ArticleDAO_interface{
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
//			"insert into article(mem_id, sort_id, article_title, article_content)"
//			+ " values (?, ?, ?, ?)";
//	private static final String GET_ALL_STMT =
//			"select article_id, mem_id, sort_id, article_title, article_content, article_status, article_like, article_dislike, article_publish, article_update from article order by article_id";
//	private static final String GET_ONE_STMT =
//			"select article_id, mem_id, sort_id, article_title, article_content, article_status, article_like, article_dislike, article_publish, article_update from article where article_id = ?";
//	private static final String DELETE = 
//			"DELETE FROM article where article_id = ?";
//	private static final String UPDATE =
//			"update article set sort_id = ?, article_title = ?, article_content = ?, article_status = ?, article_like = ?, article_dislike = ? where article_id = ?";
//	private static final String HIDE =
//			"update article set article_content = ?, article_status = ? where article_id = ?";
//
//	@Override
//	public void insert(ArticleVO articleVO) {
//		
//		Connection con = null;
//		PreparedStatement ps = null;
//		
//		try {
//			
//			con = ds.getConnection();
//			ps = con.prepareStatement(INSERT_STMT);
//			ps.setInt(1, articleVO.getMem_id());
//			ps.setInt(2, articleVO.getSort_id());
//			ps.setString(3, articleVO.getArticle_title());
//			ps.setString(4, articleVO.getArticle_content());
//			
//			ps.executeUpdate();
//
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//		} finally {
//			if (ps != null) {
//				try {
//					ps.close();
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
//	public void update(ArticleVO articleVO) {
//		Connection con = null;
//		PreparedStatement ps = null;
//
//		try {
//			con = ds.getConnection();
//			ps = con.prepareStatement(UPDATE);
//			
//
//			ps.setInt(1, articleVO.getSort_id());
//			ps.setString(2, articleVO.getArticle_title());
//			ps.setString(3, articleVO.getArticle_content());
//			ps.setInt(4, articleVO.getArticle_status());
//			ps.setInt(5, articleVO.getArticle_like());
//			ps.setInt(6, articleVO.getArticle_dislike());
//			ps.setInt(7, articleVO.getArticle_id());
//
//			ps.executeUpdate();
//
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (ps != null) {
//				try {
//					ps.close();
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
//
//		
//	}
//	
//	@Override
//	public void delete(Integer article_id) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(DELETE);
//
//			pstmt.setInt(1, article_id);
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
//
//	
//	@Override
//	public ArticleVO findByPrimaryKey(Integer article_id) {
//		ArticleVO articleVO = null;
//		Connection con = null;
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//
//		try {
//
//			con = ds.getConnection();
//			ps = con.prepareStatement(GET_ONE_STMT);
//
//			ps.setInt(1, article_id);
//
//			rs = ps.executeQuery();
//
//			while (rs.next()) {
//				// empVo 也稱為 Domain objects
//				articleVO = new ArticleVO();
//				articleVO.setArticle_id(rs.getInt("article_id"));
//				articleVO.setMem_id(rs.getInt("mem_id"));
//				articleVO.setSort_id(rs.getInt("sort_id"));
//				articleVO.setArticle_title(rs.getString("article_title"));
//				articleVO.setArticle_content(rs.getString("article_content"));
//				articleVO.setArticle_status(rs.getInt("article_status"));
//				articleVO.setArticle_like(rs.getInt("article_like"));
//				articleVO.setArticle_dislike(rs.getInt("article_dislike"));
//				articleVO.setArticle_publish(rs.getDate("article_publish"));
//				articleVO.setArticle_update(rs.getDate("article_update"));
//			}
//
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (ps != null) {
//				try {
//					ps.close();
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
//		return articleVO;
//	}
//	@Override
//	public List<ArticleVO> getAll() {
//		List<ArticleVO> list = new ArrayList<ArticleVO>();
//		ArticleVO articleVO = null;
//
//		Connection con = null;
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//
//		try {
//
//			con = ds.getConnection();
//			ps = con.prepareStatement(GET_ALL_STMT);
//			rs = ps.executeQuery();
//
//			while (rs.next()) {
//				// empVO 也稱為 Domain objects
//				articleVO = new ArticleVO();
//				articleVO.setArticle_id(rs.getInt("article_id"));
//				articleVO.setMem_id(rs.getInt("mem_id"));
//				articleVO.setSort_id(rs.getInt("sort_id"));
//				articleVO.setArticle_title(rs.getString("article_title"));
//				articleVO.setArticle_content(rs.getString("article_content"));
//				articleVO.setArticle_status(rs.getInt("article_status"));
//				articleVO.setArticle_like(rs.getInt("article_like"));
//				articleVO.setArticle_dislike(rs.getInt("article_dislike"));
//				articleVO.setArticle_publish(rs.getDate("article_publish"));
//				articleVO.setArticle_update(rs.getDate("article_update"));
//				list.add(articleVO); // Store the row in the list
//			}
//
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (ps != null) {
//				try {
//					ps.close();
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
//	public void hideArticle(ArticleVO articleVO) {
//		Connection con = null;
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//
//		try {
//			con = ds.getConnection();
//			ps = con.prepareStatement(HIDE);
//			rs = ps.executeQuery();
//			
//
//			ps.setString(1, articleVO.getArticle_content());
//			ps.setInt(2, articleVO.getArticle_status());
//			ps.setInt(3, articleVO.getArticle_id());
//
//			ps.executeUpdate();
//
//			// Handle any driver errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (ps != null) {
//				try {
//					ps.close();
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
//
//		
//	}
//	
//
//}
