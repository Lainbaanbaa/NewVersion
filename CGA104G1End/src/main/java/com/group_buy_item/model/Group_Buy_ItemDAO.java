package com.group_buy_item.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class Group_Buy_ItemDAO implements Group_Buy_ItemDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/hikariCP-BaRei");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO GROUP_BUY_ITEM (GBITEM_NAME, GBITEM_CONTENT, GBITEM_PRICE, GBITEM_STATUS, GBITEM_STARTDATE, GBITEM_ENDDATE) VALUES (?, ?, ?, ?, ?, ?)";

	private static final String GET_ALL_STMT = "SELECT GBITEM_ID,GBITEM_NAME, GBITEM_CONTENT, GBITEM_PRICE, GBITEM_STATUS, GBITEM_STARTDATE, GBITEM_ENDDATE FROM GROUP_BUY_ITEM order by GBITEM_ID";

	private static final String GET_ONE_STMT = "SELECT GBITEM_ID,GBITEM_NAME, GBITEM_CONTENT, GBITEM_PRICE, GBITEM_STATUS, GBITEM_STARTDATE, GBITEM_ENDDATE FROM GROUP_BUY_ITEM where GBITEM_ID = ?";

	private static final String DELETE = "DELETE FROM GROUP_BUY_ITEM where GBITEM_ID = ?";

	private static final String UPDATE = "UPDATE GROUP_BUY_ITEM set GBITEM_NAME=?, GBITEM_CONTENT=?, GBITEM_PRICE=?, GBITEM_STATUS=?, GBITEM_STARTDATE=?, GBITEM_ENDDATE=? where GBITEM_ID = ?";

	@Override
	public void insert(Group_Buy_ItemVO Group_Buy_ItemVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, Group_Buy_ItemVO.getGbitem_name());
			pstmt.setString(2, Group_Buy_ItemVO.getGbitem_content());
			pstmt.setInt(3, Group_Buy_ItemVO.getGbitem_price());
			pstmt.setInt(4, Group_Buy_ItemVO.getGbitem_status());
			pstmt.setDate(5, Group_Buy_ItemVO.getGbitem_startdate());
			pstmt.setDate(6, Group_Buy_ItemVO.getGbitem_enddate());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public void update(Group_Buy_ItemVO Group_Buy_ItemVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, Group_Buy_ItemVO.getGbitem_name());
			pstmt.setString(2, Group_Buy_ItemVO.getGbitem_content());
			pstmt.setInt(3, Group_Buy_ItemVO.getGbitem_price());
			pstmt.setInt(4, Group_Buy_ItemVO.getGbitem_status());
			pstmt.setDate(5, Group_Buy_ItemVO.getGbitem_startdate());
			pstmt.setDate(6, Group_Buy_ItemVO.getGbitem_enddate());
			pstmt.setInt(7, Group_Buy_ItemVO.getGbitem_id());

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

	@Override
	public void delete(Integer gbitem_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, gbitem_id);

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public Group_Buy_ItemVO findByPrimaryKey(Integer gbitem_id) {

		Group_Buy_ItemVO Group_Buy_ItemVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, gbitem_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Group_Buy_ItemVO = new Group_Buy_ItemVO();
				Group_Buy_ItemVO.setGbitem_id(rs.getInt("gbitem_id"));
				Group_Buy_ItemVO.setGbitem_name(rs.getString("gbitem_name"));
				Group_Buy_ItemVO.setGbitem_content(rs.getString("gbitem_content"));
				Group_Buy_ItemVO.setGbitem_price(rs.getInt("gbitem_price"));
				Group_Buy_ItemVO.setGbitem_status(rs.getInt("gbitem_status"));
				Group_Buy_ItemVO.setGbitem_startdate(rs.getDate("gbitem_startdate"));
				Group_Buy_ItemVO.setGbitem_enddate(rs.getDate("gbitem_enddate"));
			}

			// Handle any driver errors
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
		return Group_Buy_ItemVO;
	}

	@Override
	public List<Group_Buy_ItemVO> getAll() {
		List<Group_Buy_ItemVO> list = new ArrayList<Group_Buy_ItemVO>();
		Group_Buy_ItemVO Group_Buy_ItemVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				Group_Buy_ItemVO = new Group_Buy_ItemVO();
				Group_Buy_ItemVO.setGbitem_id(rs.getInt("gbitem_id"));
				Group_Buy_ItemVO.setGbitem_name(rs.getString("gbitem_name"));
				Group_Buy_ItemVO.setGbitem_content(rs.getString("gbitem_content"));
				Group_Buy_ItemVO.setGbitem_price(rs.getInt("gbitem_price"));
				Group_Buy_ItemVO.setGbitem_status(rs.getInt("gbitem_status"));
				Group_Buy_ItemVO.setGbitem_startdate(rs.getDate("gbitem_startdate"));
				Group_Buy_ItemVO.setGbitem_enddate(rs.getDate("gbitem_enddate"));
				list.add(Group_Buy_ItemVO);
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

}
