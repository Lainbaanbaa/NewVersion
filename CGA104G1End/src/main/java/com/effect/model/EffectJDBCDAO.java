package com.effect.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EffectJDBCDAO implements EffectDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/Ba_Rei?serverTimezone=Asia/Taipei";
	String userid = "root";
	String password = "password";

	private static final String INSER_STMT = "INSERT INTO effect (effect_name, effect_info) VALUES ( ?, ?)";
	private static final String GET_ALL_STMT = "SELECT effect_id,effect_name,effect_info FROM effect order by effect_id";
	private static final String GET_ONE_STMT = "SELECT effect_id,effect_name,effect_info FROM effect where effect_id = ?";
	private static final String DELETE = "DELETE FROM effect where effect_id = ?";
	private static final String UPDATE = "UPDATE effect set effect_name=? ,effect_info=? where effect_id = ?";

	
	
	@Override
	public void insert(EffectVO effectVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(INSER_STMT);

			pstmt.setString(1, effectVO.getEffect_name());
			pstmt.setString(2, effectVO.getEffect_info());

			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null || con != null) {
				try {
					pstmt.close();
					con.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void update(EffectVO effectVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, effectVO.getEffect_name());
			pstmt.setString(2, effectVO.getEffect_info());
			pstmt.setInt(3, effectVO.getEffect_id());
			
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
		} finally {
			if (pstmt != null || con != null) {
				try {
					pstmt.close();
					con.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void delete(Integer effect_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, effect_id);
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver." + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
	}

	@Override
	public EffectVO findByPK(Integer effect_id) {

		EffectVO effectVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid , password);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, effect_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				effectVO  = new EffectVO();
				effectVO.setEffect_id(rs.getInt("effect_id"));
				effectVO.setEffect_name(rs.getString("effect_name"));
				effectVO.setEffect_info(rs.getString("effect_info"));
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null || con!= null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		return effectVO;
	}

	@Override
	public List<EffectVO> getAll() {
		List<EffectVO> list = new ArrayList<EffectVO>();
		EffectVO effectVO =null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				effectVO = new EffectVO();
				effectVO.setEffect_id(rs.getInt("effect_id"));
				effectVO.setEffect_name(rs.getString("effect_name"));
				effectVO.setEffect_info(rs.getString("effect_info"));
				list.add(effectVO);
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null||pstmt != null||con != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		return list;
		}
	
public static void main(String[] args) {
		
		EffectJDBCDAO dao = new EffectJDBCDAO();
		// 新增
		EffectVO effectVO1 = new EffectVO();
		effectVO1.setEffect_id(11);
		effectVO1.setEffect_name("測試");
		effectVO1.setEffect_info("測試使用");
		dao.insert(effectVO1);
		
		// 修改
		EffectVO effectVO2 =new EffectVO();
		
		
		effectVO2.setEffect_name("更換");
		effectVO2.setEffect_info("更換測試");
		effectVO2.setEffect_id(5);
		dao.update(effectVO2);
		
		// 刪除
		dao.delete(6);
		
		// 查詢
		EffectVO effectVO3 = dao.findByPK(1);
		System.out.print(effectVO3.getEffect_id() + ",");
		System.out.print(effectVO3.getEffect_name() + ",");
		System.out.println(effectVO3.getEffect_info());
		System.out.println("===========================");
		
		// 查詢
		List<EffectVO> list = dao.getAll();
		for(EffectVO adEff : list) {
			System.out.print(adEff.getEffect_id()+ ",");
			System.out.print(adEff.getEffect_name() + ",");
			System.out.print(adEff.getEffect_info());
			System.out.println();
		}
		
	}
}
