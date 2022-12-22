package com.artLikeHate.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ArtLikeHateJDBCDAO implements ArtLikeHateInterface{
	
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/ba_rei?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";
	
	private static final String INSERT_UPDATE =
			"replace into like_hate(mem_id, article_id, like_status) values (?, ?, ?)";
	private static final String COUNT_LIKE =
			"select COUNT(*) from LIKE_HATE where article_id = ? AND like_status = 1";
	private static final String COUNT_HATE =
			"select COUNT(*) from LIKE_HATE where article_id = ? AND like_status = 2";

	public void insertUpdate(ArtLikeHateVO artLikeHateVO) {
		try (Connection connection = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement ps = connection.prepareStatement(INSERT_UPDATE)) {
			ps.setInt(1, artLikeHateVO.getMem_id());
			ps.setInt(2, artLikeHateVO.getArticle_id());
			ps.setInt(3, artLikeHateVO.getLike_status());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Integer countLike(Integer article_id) {
		ResultSet rs = null;
		int count = 0;
		
		try (Connection connection = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement ps = connection.prepareStatement(COUNT_LIKE)) {
			ps.setInt(1, article_id);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				count=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public Integer countHate(Integer article_id) {
		ResultSet rs = null;
		int count = 0;
		
		try (Connection connection = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement ps = connection.prepareStatement(COUNT_HATE)) {
			ps.setInt(1, article_id);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				count=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public static void main(String[] args) {
		ArtLikeHateJDBCDAO dao = new ArtLikeHateJDBCDAO();
		
		ArtLikeHateVO artLikeHateVO1 = new ArtLikeHateVO();
		artLikeHateVO1.setMem_id(2);
		artLikeHateVO1.setArticle_id(8);
		artLikeHateVO1.setLike_status(0);
		dao.insertUpdate(artLikeHateVO1);
		
		System.out.println(dao.countLike(2));
		
		System.out.print(dao.countHate(1));
		
	}
	
}
