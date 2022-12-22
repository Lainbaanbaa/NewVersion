package com.discount.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class DiscountDAO {

	private static DataSource ds = null;
//	static {
//		try {
//			Context ctx = new InitialContext();
//			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/hikariCP-BaRei");
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
//	}

	private static final String INSERT_STMT =
		"INSERT INTO `ba_rei`.`DISCOUNT` (`GBITEM_ID`, `DISCOUNT_MINAMOUNT`, `DISCOUNT_MAXAMOUNT`, `DISCOUNT_PRICE`, `DISCOUNT_NAR`) VALUES (?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT =
		"SELECT `DISCOUNT_ID`,`GBITEM_ID`, `DISCOUNT_MINAMOUNT`, `DISCOUNT_MAXAMOUNT`, `DISCOUNT_PRICE`, `DISCOUNT_NAR` FROM `ba_rei`.`DISCOUNT` order by `DISCOUNT_ID`";
	private static final String GET_ONE_STMT =
		"SELECT `DISCOUNT_ID`,`GBITEM_ID`, `DISCOUNT_MINAMOUNT`, `DISCOUNT_MAXAMOUNT`, `DISCOUNT_PRICE`, `DISCOUNT_NAR` FROM `ba_rei`.`DISCOUNT` where `DISCOUNT_ID` = ?";
	private static final String DELETE =
		"DELETE FROM `ba_rei`.`DISCOUNT` where `DISCOUNT_ID` = ?";
	private static final String UPDATE =
		"UPDATE `ba_rei`.`DISCOUNT` set `GBITEM_ID`=?, `DISCOUNT_MINAMOUNT`=?, `DISCOUNT_MAXAMOUNT`=?, `DISCOUNT_PRICE`=?, `DISCOUNT_NAR`=? where `DISCOUNT_ID` = ?";
	private static final String GET_ONE2_STMT =
			"SELECT `DISCOUNT_ID`,`GBITEM_ID`, `DISCOUNT_MINAMOUNT`, `DISCOUNT_MAXAMOUNT`, `DISCOUNT_PRICE`, `DISCOUNT_NAR` FROM `ba_rei`.`DISCOUNT` where `GBITEM_ID` = ?";


	public void insert(DiscountVO discountVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, discountVO.getGbitem_id());
			pstmt.setInt(2, discountVO.getDiscount_minamount());
			pstmt.setInt(3, discountVO.getDiscount_maxamount());
			pstmt.setInt(4, discountVO.getDiscount_price());
			pstmt.setString(5, discountVO.getDiscount_nar());

			pstmt.executeUpdate();

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

	public void update(DiscountVO discountVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, discountVO.getGbitem_id());
			pstmt.setInt(2, discountVO.getDiscount_minamount());
			pstmt.setInt(3, discountVO.getDiscount_maxamount());
			pstmt.setInt(4, discountVO.getDiscount_price());
			pstmt.setString(5, discountVO.getDiscount_nar());

			pstmt.executeUpdate();

			// Handle any driver errors
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

	public void delete(Integer discount_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, discount_id);

			pstmt.executeUpdate();

			// Handle any driver errors
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

	public DiscountVO findByPrimaryKey(Integer discount_id) {
		DiscountVO discountVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, discount_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				discountVO = new DiscountVO();
				discountVO.setDiscount_id(rs.getInt("discount_id"));
				discountVO.setGbitem_id(rs.getInt("gbitem_id"));
				discountVO.setDiscount_minamount(rs.getInt("discount_minamount"));
				discountVO.setDiscount_maxamount(rs.getInt("discount_maxamount"));
				discountVO.setDiscount_price(rs.getInt("discount_price"));
				discountVO.setDiscount_nar(rs.getString("discount_nar"));
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return discountVO;
	}


	public List<DiscountVO> getAll() {
		List<DiscountVO> list = new ArrayList<DiscountVO>();
		DiscountVO discountVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
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

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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

	public List<DiscountVO> findDiscountByPrimaryKey(Integer gbitem_id) {
		// TODO Auto-generated method stub
		return null;
	}

}
