//package com.news.model;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//import java.sql.Timestamp;
//
//public class NewsJDBCDAO implements NewsDAO_interface{
//	String driver = "com.mysql.cj.jdbc.Driver";
//	String url = "jdbc:mysql://localhost:3306/ba_rei?serverTimezone=Asia/Taipei";
//	String userid = "root";
//	String passwd = "password";
//	
//	private static final String INSERT_STMT = 
//			"INSERT INTO news (emp_id, news_title, news_content) VALUES (?, ?, ?)";
//		private static final String GET_ALL_STMT = 
//			"SELECT news_id, emp_id, news_title, news_content, create_time FROM news order by news_id";
//		private static final String GET_ONE_STMT = 
//			"SELECT news_id, emp_id, news_title, news_content, create_time FROM news where news_id = ?";
//		private static final String DELETE = 
//			"DELETE FROM news where news_id = ?";
//		private static final String UPDATE = 
//			"UPDATE news set news_title =?, news_content = ? where news_id = ?";
//
//	@Override
//	public void insert(NewsVO newsVO) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(INSERT_STMT);
//
//			pstmt.setInt(1, newsVO.getEmp_id());
//			pstmt.setString(2, newsVO.getNews_title());
//			pstmt.setString(3, newsVO.getNews_content());
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
//	@Override
//	public void update(NewsVO newsVO) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(UPDATE);
//
//			pstmt.setString(1, newsVO.getNews_title());
//			pstmt.setString(2, newsVO.getNews_content());
//			pstmt.setInt(3, newsVO.getNews_id());
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
//
//	}
//
//	@Override
//	public void delete(Integer news_id) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(DELETE);
//
//			pstmt.setInt(1, news_id);
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
//	@Override
//	public NewsVO findByPrimaryKey(Integer news_id) {
//		NewsVO newsVO = null;
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
//			pstmt.setInt(1, news_id);
//
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				// empVo 也稱為 Domain objects
//				newsVO = new NewsVO();
//				newsVO.setNews_id(rs.getInt("news_id"));
//				newsVO.setEmp_id(rs.getInt("emp_id"));
//				newsVO.setNews_title(rs.getString("news_title"));
//				newsVO.setNews_content(rs.getString("news_content"));
//				newsVO.setCreate_time(rs.getTimestamp("create_time"));
//			}
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
//		return newsVO;
//	}
//
//	@Override
//	public List<NewsVO> getAll() {
//		List<NewsVO> list = new ArrayList<NewsVO>();
//		NewsVO newsVO = null;
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
//				// empVO 也稱為 Domain objects
//				newsVO = new NewsVO();
//				newsVO.setNews_id(rs.getInt("news_id"));
//				newsVO.setEmp_id(rs.getInt("emp_id"));
//				newsVO.setNews_title(rs.getString("news_title"));
//				newsVO.setNews_content(rs.getString("news_content"));
//				newsVO.setCreate_time(rs.getTimestamp("create_time"));
//				list.add(newsVO); // Store the row in the list
//			}
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
//
//	public static void main(String[] args) {
//
//		NewsJDBCDAO dao = new NewsJDBCDAO();
//
//		// 新增
//		NewsVO newsVO1 = new NewsVO();
//		newsVO1.setEmp_id(4);
//		newsVO1.setNews_title("公告測試");
//		newsVO1.setNews_content("測試中");
//		dao.insert(newsVO1);
//
//		// 修改
//		NewsVO newsVO2 = new NewsVO();
//		newsVO2.setNews_title("全公司調薪");
//		newsVO2.setNews_content("調薪10%");
//		newsVO2.setNews_id(1);
//	
//		dao.update(newsVO2);
//
//		// 刪除
//		dao.delete(2);
//
//		// 查詢
//		NewsVO NewsVO3 = dao.findByPrimaryKey(3);
//		System.out.print(NewsVO3.getNews_id());
//		System.out.print(NewsVO3.getEmp_id());
//		System.out.print(NewsVO3.getNews_title());
//		System.out.print(NewsVO3.getNews_content());
//		System.out.println(NewsVO3.getCreate_time());
//		System.out.println("---------------------");
//
//		// 查詢
//		List<NewsVO> list = dao.getAll();
//		for (NewsVO aEmp : list) {
//			System.out.print(aEmp.getNews_id());
//			System.out.print(aEmp.getEmp_id());
//			System.out.print(aEmp.getNews_title());
//			System.out.print(aEmp.getNews_content());
//			System.out.print(aEmp.getCreate_time());
//			System.out.println();
//		}
//	}
//}
