package com.favorite_list.model;

import java.util.*;
import java.sql.*;
import java.time.LocalDateTime;

public class Favorite_listJDBCDAO implements Favorite_listDAO_interface {

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/Ba_Rei?serverTimezone=Asia/Taipei";
	String userid = "root";
	String password = "password";

	private static final String INSERT_STMT = "INSERT INTO favorite_list (mem_id,item_id) VALUES (?, ?)";
	private static final String GET_ALL_STMT = "SELECT mem_id,item_id,fav_time FROM favorite_list order by mem_id";
	private static final String GET_ONE_STMT = "SELECT mem_id,item_id,fav_time FROM favorite_list where mem_id = ?";
	private static final String DELETE = "DELETE FROM favorite_list where mem_id = ?";
	private static final String UPDATE = "UPDATE favorite_list set item_id=? where mem_id = ?";

	@Override
	public void insert(Favorite_listVO favorite_listVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, favorite_listVO.getMem_id());
			pstmt.setInt(2, favorite_listVO.getItem_id());
//			pstmt.setTimestamp(3, favorite_listVO.getFav_time());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void update(Favorite_listVO favorite_listVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(UPDATE);

		
			pstmt.setInt(1, favorite_listVO.getItem_id());
//			pstmt.setTimestamp(2,favorite_listVO.getFav_time());
			pstmt.setInt(2, favorite_listVO.getMem_id());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void delete(Integer mem_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, mem_id);

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null)
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

	@Override
	public Favorite_listVO findByPrimaryKey(Integer mem_id) {

		Favorite_listVO favorite_listVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, mem_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				favorite_listVO = new Favorite_listVO();
				favorite_listVO.setMem_id(rs.getInt("mem_id"));
				favorite_listVO.setItem_id(rs.getInt("item_id"));
				favorite_listVO.setFav_time(rs.getTimestamp("fav_time"));
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
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
		return favorite_listVO;
	}

	@Override
	public List<Favorite_listVO> getAll() {
		List<Favorite_listVO> list = new ArrayList<Favorite_listVO>();
		Favorite_listVO favorite_listVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				favorite_listVO = new Favorite_listVO();
				favorite_listVO.setMem_id(rs.getInt("mem_id"));
				favorite_listVO.setItem_id(rs.getInt("item_id"));
				favorite_listVO.setFav_time(rs.getTimestamp("fav_time"));
				list.add(favorite_listVO);
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		
		Favorite_listJDBCDAO dao = new Favorite_listJDBCDAO();
		// 新增
		Favorite_listVO favorite_listVO1 = new Favorite_listVO();
		favorite_listVO1.setMem_id(2);
		favorite_listVO1.setItem_id(1);
//		favorite_listVO1.setFav_time(Timestamp.valueOf(LocalDateTime.now()));
		dao.insert(favorite_listVO1);
		
		// 修改
		Favorite_listVO favorite_listVO2 =new Favorite_listVO();
		
		favorite_listVO2.setItem_id(1);
//		favorite_listVO2.setFav_time(Timestamp.valueOf(LocalDateTime.now()));
		favorite_listVO2.setMem_id(4);
		dao.update(favorite_listVO2);
		
		// 刪除
		dao.delete(2);
		
		// 查詢
		Favorite_listVO favorite_listVO3 = dao.findByPrimaryKey(1);
		System.out.print(favorite_listVO3.getMem_id() + ",");
		System.out.print(favorite_listVO3.getItem_id() + ",");
		System.out.println(favorite_listVO3.getFav_time());
		System.out.println("===========================");
		
		// 查詢
		List<Favorite_listVO> list = dao.getAll();
		for(Favorite_listVO fa : list) {
			System.out.print(fa.getMem_id()+ ",");
			System.out.print(fa.getItem_id() + ",");
			System.out.print(fa.getFav_time());
			System.out.println();
		}
		
	}
}
