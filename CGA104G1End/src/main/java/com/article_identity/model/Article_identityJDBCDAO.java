package com.article_identity.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.article.model.ArticleVO;

public class Article_identityJDBCDAO implements Article_identityDAO_interface{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/ba_rei?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";

	private static final String INSERT_STMT = 
		"INSERT INTO article_identity (article_pic, mem_id) VALUES (?, ?)";
	private static final String GET_ONE_STMT = 
		"SELECT article_picno, article_pic, mem_id, upload_time FROM article_identity WHERE upload_time = (select MAX(upload_time) FROM article_identity WHERE mem_id = ?)";
	@Override
	public void insert(Article_identityVO article_identityVO) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ps = con.prepareStatement(INSERT_STMT);
			ps.setString(1, article_identityVO.getArticle_pic());
			ps.setInt(2, article_identityVO.getMem_id());
			
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
	public Article_identityVO findByPrimaryKey(Integer mem_id) {
		Article_identityVO article_identityVO = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ps = con.prepareStatement(GET_ONE_STMT);

			ps.setInt(1, mem_id);

			rs = ps.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				article_identityVO = new Article_identityVO();
				article_identityVO.setMem_id(rs.getInt("mem_id"));
				article_identityVO.setArticle_pic(rs.getString("article_pic"));
				article_identityVO.setArticle_picno(rs.getInt("article_picno"));
				article_identityVO.setUpload_time(rs.getTimestamp("upload_time"));
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
		return article_identityVO;
	}
	
	public static void main(String[] args) {

		Article_identityJDBCDAO dao = new Article_identityJDBCDAO();

		// 新增
//		Article_identityVO article_identityVO1 = new Article_identityVO();
//		article_identityVO1.setArticle_pic("123");
//		article_identityVO1.setMem_id(2);
//		dao.insert(article_identityVO1);
//		System.out.println(1);

		// 刪除
//		dao.delete(1);

		// 查詢
		Article_identityVO article_identityVO2 = dao.findByPrimaryKey(2);

		System.out.print(article_identityVO2.getMem_id());
		System.out.print(article_identityVO2.getArticle_pic());
		System.out.print(article_identityVO2.getUpload_time());
		System.out.println("---------------------");

		// 查詢
//		List<EmpVO> list = dao.getAll();
//		for (EmpVO aEmp : list) {
//			System.out.print(aEmp.getEmpno() + ",");
//			System.out.print(aEmp.getEname() + ",");
//			System.out.print(aEmp.getJob() + ",");
//			System.out.print(aEmp.getHiredate() + ",");
//			System.out.print(aEmp.getSal() + ",");
//			System.out.print(aEmp.getComm() + ",");
//			System.out.print(aEmp.getDeptno());
//			System.out.println();
		}
	

}
