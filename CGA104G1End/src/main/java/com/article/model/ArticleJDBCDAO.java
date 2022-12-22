package com.article.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.mem.model.MemUtil;
import com.mem.model.MemVO;

public class ArticleJDBCDAO implements ArticleDAO_interface{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/ba_rei?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";
	
	private static final String INSERT_STMT =
			"insert into article(mem_id, sort_id, article_title, article_content)"
			+ " values (?, ?, ?, ?)";
	private static final String GET_ALL_STMT =
			"select article_id, mem_id, sort_id, article_title, article_content, article_status, article_like, article_dislike, article_publish, article_update from article where article_status = 1 order by article_id DESC";
	private static final String GET_ONE_STMT =
			"select article_id, mem_id, sort_id, article_title, article_content, article_status, article_like, article_dislike, article_publish, article_update from article where article_id = ?";
	private static final String DELETE = 
			"DELETE FROM article where article_id = ?";
	private static final String UPDATE =
			"update article set sort_id = ?, article_title = ?, article_content = ? where article_id = ?";
	private static final String HIDE =
			"update article set article_content = ?, article_status = ? where article_id = ?";
	

	@Override
	public void insert(ArticleVO articleVO) {
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ps = con.prepareStatement(INSERT_STMT);
			ps.setInt(1, articleVO.getMem_id());
			ps.setInt(2, articleVO.getSort_id());
			ps.setString(3, articleVO.getArticle_title());
			ps.setString(4, articleVO.getArticle_content());
			
			ps.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (ps != null) {
				try {
					ps.close();
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
	public void update(ArticleVO articleVO) {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ps = con.prepareStatement(UPDATE);
			

			ps.setInt(1, articleVO.getSort_id());
			ps.setString(2, articleVO.getArticle_title());
			ps.setString(3, articleVO.getArticle_content());
			ps.setInt(4, articleVO.getArticle_id());

			ps.executeUpdate();

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
			if (ps != null) {
				try {
					ps.close();
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
	
	public void hideArticle(ArticleVO articleVO) {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ps = con.prepareStatement(HIDE);
			

			ps.setString(1, articleVO.getArticle_content());
			ps.setInt(2, articleVO.getArticle_status());
			ps.setInt(3, articleVO.getArticle_id());

			ps.executeUpdate();

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
			if (ps != null) {
				try {
					ps.close();
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
	public void delete(Integer article_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, article_id);

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
	public ArticleVO findByPrimaryKey(Integer article_id) {
		ArticleVO articleVO = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ps = con.prepareStatement(GET_ONE_STMT);

			ps.setInt(1, article_id);

			rs = ps.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				articleVO = new ArticleVO();
				articleVO.setArticle_id(rs.getInt("article_id"));
				articleVO.setMem_id(rs.getInt("mem_id"));
				articleVO.setSort_id(rs.getInt("sort_id"));
				articleVO.setArticle_title(rs.getString("article_title"));
				articleVO.setArticle_content(rs.getString("article_content"));
				articleVO.setArticle_status(rs.getInt("article_status"));
				articleVO.setArticle_like(rs.getInt("article_like"));
				articleVO.setArticle_dislike(rs.getInt("article_dislike"));
				articleVO.setArticle_publish(rs.getDate("article_publish"));
				articleVO.setArticle_update(rs.getDate("article_update"));
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
			if (ps != null) {
				try {
					ps.close();
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
		return articleVO;
	}
	@Override
	public List<ArticleVO> getAll() {
		List<ArticleVO> list = new ArrayList<ArticleVO>();
		ArticleVO articleVO = null;

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ps = con.prepareStatement(GET_ALL_STMT);
			rs = ps.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				articleVO = new ArticleVO();
				articleVO.setArticle_id(rs.getInt("article_id"));
				articleVO.setMem_id(rs.getInt("mem_id"));
				articleVO.setSort_id(rs.getInt("sort_id"));
				articleVO.setArticle_title(rs.getString("article_title"));
				articleVO.setArticle_content(rs.getString("article_content"));
				articleVO.setArticle_status(rs.getInt("article_status"));
				articleVO.setArticle_like(rs.getInt("article_like"));
				articleVO.setArticle_dislike(rs.getInt("article_dislike"));
				articleVO.setArticle_publish(rs.getDate("article_publish"));
				articleVO.setArticle_update(rs.getDate("article_update"));
				list.add(articleVO); // Store the row in the list
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
			if (ps != null) {
				try {
					ps.close();
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
	
	public List<ArticleVO> getAllArt(Map<String, String[]> map) {
		List<ArticleVO> list = new ArrayList<ArticleVO>();
		ArticleVO articleVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			String MySql = "select * from article "
					+ArticleUtil.getWhereCondition(map)
					+"order by article_id";
			pstmt = con.prepareStatement(MySql);
			rs = pstmt.executeQuery();
		
			System.out.println(MySql);
			while (rs.next()) {
				articleVO = new ArticleVO();
				articleVO.setArticle_id(rs.getInt("article_id"));
				articleVO.setMem_id(rs.getInt("mem_id"));
				articleVO.setSort_id(rs.getInt("sort_id"));
				articleVO.setArticle_title(rs.getString("article_title"));
				articleVO.setArticle_content(rs.getString("article_content"));
				articleVO.setArticle_status(rs.getInt("article_status"));
				articleVO.setArticle_like(rs.getInt("article_like"));
				articleVO.setArticle_dislike(rs.getInt("article_dislike"));
				articleVO.setArticle_publish(rs.getDate("article_publish"));
				articleVO.setArticle_update(rs.getDate("article_update"));
				list.add(articleVO);
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null || pstmt != null || con != null) {
				try {
					rs.close();
					pstmt.close();
					con.close();
					
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	public static void main(String[] args) {
		ArticleJDBCDAO dao = new ArticleJDBCDAO();
		
				// 新增			
				ArticleVO articleVO1 = new ArticleVO();
				articleVO1.setMem_id(3);
				articleVO1.setSort_id(2);
				articleVO1.setArticle_title("純種哈士奇配偶徵求");
				articleVO1.setArticle_content("公哈士奇徵母哈士奇配種，需付血統證明，意者私lineID:dog9898");
				dao.insert(articleVO1);

		
				// 修改
				ArticleVO articleVO2 = new ArticleVO();
				
				articleVO2.setSort_id(1);
				articleVO2.setArticle_title("我家的狗會後空翻(附圖)");
				articleVO2.setArticle_content("如題，更新圖檔 不喜歡就滾");
				articleVO2.setArticle_id(8);
				
				dao.update(articleVO2);
				
				// 刪除
//				dao.delete(4);


				// 查詢
				ArticleVO articleVO3 = dao.findByPrimaryKey(1);
				System.out.print(articleVO3.getArticle_id());
				System.out.print(articleVO3.getMem_id());
				System.out.print(articleVO3.getSort_id());
				System.out.print(articleVO3.getArticle_title());
				System.out.print(articleVO3.getArticle_content());
				System.out.print(articleVO3.getArticle_status());
				System.out.print(articleVO3.getArticle_like());
				System.out.print(articleVO3.getArticle_dislike());
				System.out.print(articleVO3.getArticle_publish());
				System.out.println(articleVO3.getArticle_update());
				System.out.println("---------------------");
				
				// 查詢
				List<ArticleVO> list = dao.getAll();
				for (ArticleVO aEmp : list) {
					System.out.print(aEmp.getArticle_id());
					System.out.print(aEmp.getMem_id());
					System.out.print(aEmp.getSort_id());
					System.out.print(aEmp.getArticle_title());
					System.out.print(aEmp.getArticle_content());
					System.out.print(aEmp.getArticle_status());
					System.out.print(aEmp.getArticle_like());
					System.out.print(aEmp.getArticle_dislike());
					System.out.print(aEmp.getArticle_publish());
					System.out.print(aEmp.getArticle_update());
					System.out.println();
				}
	}
	


}
