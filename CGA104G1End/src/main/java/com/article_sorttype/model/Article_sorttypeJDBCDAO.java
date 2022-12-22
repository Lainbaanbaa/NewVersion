package com.article_sorttype.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Article_sorttypeJDBCDAO implements Article_sorttypeDAO_interface{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/ba_rei?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";
	
	private static final String INSERT_STMT = 
			"INSERT INTO article_sorttype (sort_content) VALUES (?)";
		private static final String GET_ALL_STMT = 
			"SELECT sort_id, sort_content FROM article_sorttype order by sort_id";
		private static final String GET_ONE_STMT = 
			"SELECT sort_content FROM article_sorttype where sort_id = ?";
		private static final String DELETE = 
			"DELETE FROM article_sorttype where sort_id = ?";
		private static final String UPDATE = 
			"UPDATE article_sorttype set sort_content = ? where sort_id = ?";
		
		@Override
		public void insert(Article_sorttypeVO article_sorttypeVO) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(INSERT_STMT);

				pstmt.setString(1, article_sorttypeVO.getSort_content());

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
		public void update(Article_sorttypeVO article_sorttypeVO) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(UPDATE);

				pstmt.setString(1, article_sorttypeVO.getSort_content());
				pstmt.setInt(2, article_sorttypeVO.getSort_id());

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
		public void delete(Integer sort_id) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(DELETE);

				pstmt.setInt(1, sort_id);

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
		public Article_sorttypeVO findByPrimaryKey(Integer sort_id) {
			Article_sorttypeVO article_sorttypeVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setInt(1, sort_id);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVo 也稱為 Domain objects
					article_sorttypeVO = new Article_sorttypeVO();
//					article_sorttypeVO.setSort_id(rs.getInt("sort_id"));
					article_sorttypeVO.setSort_content(rs.getString("sort_content"));
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
			return article_sorttypeVO;
		}
		@Override
		public List<Article_sorttypeVO> getAll() {
			List<Article_sorttypeVO> list = new ArrayList<Article_sorttypeVO>();
			Article_sorttypeVO article_sorttypeVO = null;

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
					article_sorttypeVO = new Article_sorttypeVO();
					article_sorttypeVO.setSort_id(rs.getInt("sort_id"));
					article_sorttypeVO.setSort_content(rs.getString("sort_content"));
					list.add(article_sorttypeVO); // Store the row in the list
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
			
			Article_sorttypeJDBCDAO dao = new Article_sorttypeJDBCDAO();
			
//			// 新增
//			Article_sorttypeVO article_sorttypeVO1 = new Article_sorttypeVO();
//			article_sorttypeVO1.setSort_content("配種");
//			dao.insert(article_sorttypeVO1);
//
//			// 修改
//			Article_sorttypeVO article_sorttypeVO2 = new Article_sorttypeVO();
//			article_sorttypeVO2.setSort_id(4);
//			article_sorttypeVO2.setSort_content("交友");
//
//			dao.update(article_sorttypeVO2);

			// 刪除
//			dao.delete(5);

			// 查詢
			Article_sorttypeVO article_sorttypeVO3 = dao.findByPrimaryKey(1);
			System.out.println(article_sorttypeVO3.getSort_content());
			System.out.println("---------------------");

			// 查詢
//			List<Article_sorttypeVO> list = dao.getAll();
//			for (Article_sorttypeVO aEmp : list) {
//				System.out.print(aEmp.getSort_id());
//				System.out.print(aEmp.getSort_content());
//				System.out.println();
//			}
		}
		

}
