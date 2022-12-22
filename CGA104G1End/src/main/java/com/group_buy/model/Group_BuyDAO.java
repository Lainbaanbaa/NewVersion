package com.group_buy.model;

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


public class Group_BuyDAO implements Group_BuyDAO_interface{

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/hikariCP-BaRei");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}


	private static final String INSERT_STMT =
			"INSERT INTO GROUP_BUY (MEM_ID, GBITEM_ID, GB_MIN, GB_AMOUNT, GBSTART_DATE, GBEND_DATE,GB_STATUS) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT =
			"SELECT GB_ID, MEM_ID, GBITEM_ID, GB_MIN, GB_AMOUNT, GBSTART_DATE, GBEND_DATE,GB_STATUS FROM GROUP_BUY order by GB_ID";
	private static final String GET_ONE_STMT =
			"SELECT GB_ID, MEM_ID, GBITEM_ID, GB_MIN, GB_AMOUNT, GBSTART_DATE, GBEND_DATE,GB_STATUS FROM GROUP_BUY where GB_ID = ?";
	private static final String DELETE =
			"DELETE FROM GROUP_BUY where GB_ID = ?";
	private static final String UPDATE =
			"UPDATE GROUP_BUY set MEM_ID=?, GBITEM_ID=?, GB_MIN=?, GB_AMOUNT=?, GBSTART_DATE=?, GBEND_DATE=?, GB_STATUS=? where GB_ID = ?";


	@Override
	public void insert(Group_BuyVO Group_BuyVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, Group_BuyVO.getMem_id());
			pstmt.setInt(2, Group_BuyVO.getGbitem_id());
			pstmt.setInt(3, Group_BuyVO.getGb_min());
			pstmt.setInt(4, Group_BuyVO.getGb_amount());
			pstmt.setTimestamp(5, Group_BuyVO.getGbstart_date());
			pstmt.setTimestamp(6, Group_BuyVO.getGbend_date());
			pstmt.setInt(7, Group_BuyVO.getGb_status());

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
	public void update(Group_BuyVO Group_BuyVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, Group_BuyVO.getMem_id());
			pstmt.setInt(2, Group_BuyVO.getGbitem_id());
			pstmt.setInt(3, Group_BuyVO.getGb_min());
			pstmt.setInt(4, Group_BuyVO.getGb_amount());
			pstmt.setTimestamp(5, Group_BuyVO.getGbstart_date());
			pstmt.setTimestamp(6, Group_BuyVO.getGbend_date());
			pstmt.setInt(7, Group_BuyVO.getGb_status());
			pstmt.setInt(8, Group_BuyVO.getGb_id());

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
	public void delete(Integer gb_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, gb_id);

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
	public Group_BuyVO findByPrimaryKey(Integer gb_id) {
		Group_BuyVO Group_BuyVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, gb_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Group_BuyVO = new Group_BuyVO();
				Group_BuyVO.setGb_id(rs.getInt("gb_id"));
				Group_BuyVO.setMem_id(rs.getInt("mem_id"));
				Group_BuyVO.setGbitem_id(rs.getInt("gbitem_id"));
				Group_BuyVO.setGb_min(rs.getInt("gb_min"));
				Group_BuyVO.setGb_amount(rs.getInt("gb_amount"));
				Group_BuyVO.setGbstart_date(rs.getTimestamp("gbstart_date"));
				Group_BuyVO.setGbend_date(rs.getTimestamp("gbend_date"));
				Group_BuyVO.setGb_status(rs.getInt("gb_status"));
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
		return Group_BuyVO;
	}

	@Override
	public List<Group_BuyVO> getAll() {
		List<Group_BuyVO> list = new ArrayList<Group_BuyVO>();
		Group_BuyVO Group_BuyVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Group_BuyVO = new Group_BuyVO();
				Group_BuyVO.setGb_id(rs.getInt("gb_id"));
				Group_BuyVO.setMem_id(rs.getInt("mem_id"));
				Group_BuyVO.setGbitem_id(rs.getInt("gbitem_id"));
				Group_BuyVO.setGb_min(rs.getInt("gb_min"));
				Group_BuyVO.setGb_amount(rs.getInt("gb_amount"));
				Group_BuyVO.setGbstart_date(rs.getTimestamp("gbstart_date"));
				Group_BuyVO.setGbend_date(rs.getTimestamp("gbend_date"));
				Group_BuyVO.setGb_status(rs.getInt("gb_status"));
				list.add(Group_BuyVO);
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

	@Override
	public List<Group_BuyVO> findByMemID(Integer mem_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateGbprice(Group_BuyVO Group_BuyVO) {
		// TODO Auto-generated method stub

	}

	@Override
	public Group_BuyVO findLast() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateGbAmount(Group_BuyVO Group_BuyVO) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateGbStatus(Group_BuyVO Group_BuyVO) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Group_BuyVO> joinGBIGetAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Group_BuyVO> getAll2InProgress() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Group_BuyVO> getAll2End() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Group_BuyVO> joinGBIGetAllWhereMemID(Integer mem_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Group_BuyVO CheckByMemID(Integer mem_id) {
		// TODO Auto-generated method stub
		return null;
	}

}
