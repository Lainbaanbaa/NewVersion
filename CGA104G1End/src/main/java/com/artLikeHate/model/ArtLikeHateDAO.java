package com.artLikeHate.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ArtLikeHateDAO implements ArtLikeHateInterface{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/hikariCP-BaRei");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_UPDATE =
			"replace into like_hate(mem_id, article_id, like_status) values (?, ?, ?)";
	private static final String COUNT_LIKE =
			"select COUNT(*) from LIKE_HATE where article_id = ? AND like_status = 1";
	private static final String COUNT_HATE =
			"select COUNT(*) from LIKE_HATE where article_id = ? AND like_status = 2";

	public void insertUpdate(ArtLikeHateVO artLikeHateVO) {
		try (Connection connection = ds.getConnection();
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
		
		try (Connection connection = ds.getConnection();
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
		
		try (Connection connection = ds.getConnection();
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
		artLikeHateVO1.setLike_status(1);
		dao.insertUpdate(artLikeHateVO1);
		
		System.out.println(dao.countLike(2));
		
		System.out.print(dao.countHate(1));
		
	}

}
