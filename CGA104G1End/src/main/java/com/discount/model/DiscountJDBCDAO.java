package com.discount.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.emp.model.EmpVO;

public class DiscountJDBCDAO implements DiscountDAO_interface {

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/ba_rei?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";

	private static final String INSERT_STMT = "INSERT INTO `ba_rei`.`DISCOUNT` (`GBITEM_ID`, `DISCOUNT_MINAMOUNT`, `DISCOUNT_MAXAMOUNT`, `DISCOUNT_PRICE`, `DISCOUNT_NAR`) VALUES (?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT `DISCOUNT_ID`,`GBITEM_ID`, `DISCOUNT_MINAMOUNT`, `DISCOUNT_MAXAMOUNT`, `DISCOUNT_PRICE`, `DISCOUNT_NAR` FROM `ba_rei`.`DISCOUNT` order by `DISCOUNT_ID`";
	private static final String GET_ONE_STMT = "SELECT `DISCOUNT_ID`,`GBITEM_ID`, `DISCOUNT_MINAMOUNT`, `DISCOUNT_MAXAMOUNT`, `DISCOUNT_PRICE`, `DISCOUNT_NAR` FROM `ba_rei`.`DISCOUNT` where `DISCOUNT_ID` = ?";
	private static final String GET_ONE_DISCOUNT_STMT = "SELECT `DISCOUNT_ID`,`GBITEM_ID`, `DISCOUNT_MINAMOUNT`, `DISCOUNT_MAXAMOUNT`, `DISCOUNT_PRICE`, `DISCOUNT_NAR` FROM `ba_rei`.`DISCOUNT` where `GBITEM_ID` = ?";
	private static final String DELETE = "DELETE FROM `ba_rei`.`DISCOUNT` where `DISCOUNT_ID` = ?";
	private static final String UPDATE = "UPDATE `ba_rei`.`DISCOUNT` set `GBITEM_ID`=?, `DISCOUNT_MINAMOUNT`=?, `DISCOUNT_MAXAMOUNT`=?, `DISCOUNT_PRICE`=?, `DISCOUNT_NAR`=? where `DISCOUNT_ID` = ?";
	private static final String SEARCH_GBITEM_ID_EXIST_IN_DISCOUNT = "SELECT GBITEM_ID FROM ba_rei.DISCOUNT WHERE GBITEM_ID = ?";
	@Override
	public void insert(DiscountVO DiscountVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, DiscountVO.getGbitem_id());
			pstmt.setInt(2, DiscountVO.getDiscount_minamount());
			pstmt.setInt(3, DiscountVO.getDiscount_maxamount());
			pstmt.setInt(4, DiscountVO.getDiscount_price());
			pstmt.setString(5, DiscountVO.getDiscount_nar());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

//==============================================團購商品送出======================================
	@Override
	public List<DiscountVO> findDiscountByPrimaryKey(Integer gbitem_id) {
		List<DiscountVO> list = new ArrayList<DiscountVO>();

		DiscountVO discountVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_DISCOUNT_STMT);

			pstmt.setInt(1, gbitem_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				discountVO = new DiscountVO();
				discountVO.setDiscount_id(rs.getInt("discount_id"));
				discountVO.setGbitem_id(rs.getInt("gbitem_id"));
				discountVO.setDiscount_minamount(rs.getInt("discount_minamount"));
				discountVO.setDiscount_maxamount(rs.getInt("discount_maxamount"));
				discountVO.setDiscount_price(rs.getInt("discount_price"));
				discountVO.setDiscount_nar(rs.getString("discount_nar"));
				list.add(discountVO);
			}

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
		return list;
	}

	// ==============================================資料送網團購商品訂單======================================
	@Override
	public void update(DiscountVO DiscountVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, DiscountVO.getGbitem_id());
			pstmt.setInt(2, DiscountVO.getDiscount_minamount());
			pstmt.setInt(3, DiscountVO.getDiscount_maxamount());
			pstmt.setInt(4, DiscountVO.getDiscount_price());
			pstmt.setString(5, DiscountVO.getDiscount_nar());
			pstmt.setInt(6, DiscountVO.getDiscount_id());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void delete(Integer discount_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, discount_id);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public DiscountVO findByPrimaryKey(Integer discount_id) {
		DiscountVO DiscountVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, discount_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				DiscountVO = new DiscountVO();
				DiscountVO.setDiscount_id(rs.getInt("discount_id"));
				DiscountVO.setGbitem_id(rs.getInt("gbitem_id"));
				DiscountVO.setDiscount_minamount(rs.getInt("discount_minamount"));
				DiscountVO.setDiscount_maxamount(rs.getInt("discount_maxamount"));
				DiscountVO.setDiscount_price(rs.getInt("discount_price"));
				DiscountVO.setDiscount_nar(rs.getString("discount_nar"));
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
		return DiscountVO;
	}
	@Override
	public DiscountVO CheckByGBitemID(Integer gbitem_id) {
		DiscountVO DiscountVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(SEARCH_GBITEM_ID_EXIST_IN_DISCOUNT);
			
			pstmt.setInt(1, gbitem_id);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				DiscountVO = new DiscountVO();
				DiscountVO.setGbitem_id(rs.getInt("gbitem_id"));
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
		return DiscountVO;
	}

	@Override
	public List<DiscountVO> getAll() {
		List<DiscountVO> list = new ArrayList<DiscountVO>();
		DiscountVO discountVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				discountVO = new DiscountVO();
				discountVO.setDiscount_id(rs.getInt("discount_id"));
				discountVO.setGbitem_id(rs.getInt("gbitem_id"));
				discountVO.setDiscount_minamount(rs.getInt("discount_minamount"));
				discountVO.setDiscount_maxamount(rs.getInt("discount_maxamount"));
				discountVO.setDiscount_price(rs.getInt("discount_price"));
				discountVO.setDiscount_nar(rs.getString("discount_nar"));
				list.add(discountVO);
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
//
//	public static void main(String[] args) {
//
//		DiscountJDBCDAO dao = new DiscountJDBCDAO();

		// 新增
//		DiscountVO discVO1 = new DiscountVO();
//		discVO1.setGbitem_id(4);
//		discVO1.setDiscount_minamount(55);
//		discVO1.setDiscount_maxamount(500);
//		discVO1.setDiscount_price(30000);
//		discVO1.setDiscount_nar("折價三萬");
//		dao.insert(discVO1);

		// 修改
//		DiscountVO discVO2 = new DiscountVO();
//		discVO2.setDiscount_id(4);
//		discVO2.setGbitem_id(5);
//		discVO2.setDiscount_minamount(60);
//		discVO2.setDiscount_maxamount(120);
//		discVO2.setDiscount_price(20000);
//		discVO2.setDiscount_nar("折價二萬");
//		dao.update(discVO2);

//		// 刪除
//		dao.delete(1);

//		// 查詢
//		DiscountVO discVO3 = dao.findByPrimaryKey(1);
//		System.out.print(discVO3.getDiscount_id() + ",");
//		System.out.print(discVO3.getGbitem_id() + ",");
//		System.out.print(discVO3.getDiscount_minamount() + ",");
//		System.out.print(discVO3.getDiscount_maxamount() + ",");
//		System.out.print(discVO3.getDiscount_price() + ",");
//		System.out.println(discVO3.getDiscount_nar());
//		System.out.println("---------------------");
		// 以gnitem_id查詢是否有團購商品已經設定折扣
//		DiscountVO discVO3 = dao.CheckByGBitemID(6);
//		System.out.print(discVO3);
//		System.out.println("---------------------");
		
//		List<DiscountVO> list = dao.findDiscountByPrimaryKey(1);
//		for (DiscountVO aDisc : list) {
//			System.out.print(aDisc.getDiscount_id() + ",");
//			System.out.print(aDisc.getGbitem_id() + ",");
//			System.out.print(aDisc.getDiscount_minamount() + ",");
//			System.out.print(aDisc.getDiscount_maxamount() + ",");
//			System.out.print(aDisc.getDiscount_price() + ",");
//			System.out.print(aDisc.getDiscount_nar());
//			System.out.println();
//		}

//		 查詢
//		List<DiscountVO> list = dao.getAll();
//		for (DiscountVO aDisc : list) {
//			System.out.print(aDisc.getDiscount_id() + ",");
//			System.out.print(aDisc.getGbitem_id() + ",");
//			System.out.print(aDisc.getDiscount_minamount() + ",");
//			System.out.print(aDisc.getDiscount_maxamount() + ",");
//			System.out.print(aDisc.getDiscount_price() + ",");
//			System.out.print(aDisc.getDiscount_nar());
//			System.out.println();
//		}
//	}
}
