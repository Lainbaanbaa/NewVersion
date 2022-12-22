package com.group_join.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Group_JoinDAO implements Group_JoinDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/hikariCP-BaRei");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO GROUP_JOIN (GB_ID, MEM_ID, GBPAY_STATUS, PICKUP_STATUS, DELIVER_STATUS, GBBUY_AMOUNT ,GBBUY_PRICE) VALUES (?, ?, ?, ?, ? ,? ,?)";
	private static final String GET_ALL_STMT = "SELECT GB_ID, MEM_ID, GBPAY_STATUS, PICKUP_STATUS, DELIVER_STATUS  ,GBBUY_AMOUNT ,GBBUY_PRICE FROM  GROUP_JOIN order by GB_ID ";
	//參完查詢
	private static final String GET_ALL_BYMEM = "SELECT GB_ID, MEM_ID, GBPAY_STATUS, PICKUP_STATUS, DELIVER_STATUS  ,GBBUY_AMOUNT ,GBBUY_PRICE FROM  GROUP_JOIN where (MEM_ID = ?) order by GB_ID";
	//
	private static final String GET_ONE_STMT = "SELECT GB_ID, MEM_ID, GBPAY_STATUS, PICKUP_STATUS, DELIVER_STATUS  ,GBBUY_AMOUNT ,GBBUY_PRICE FROM GROUP_JOIN where GB_ID = ? and MEM_ID =?";
	private static final String GET_ALL_GB = "SELECT GB_ID, MEM_ID, GBPAY_STATUS, PICKUP_STATUS, DELIVER_STATUS  ,GBBUY_AMOUNT ,GBBUY_PRICE FROM GROUP_JOIN where GB_ID = ?";
	private static final String DELETE = "DELETE FROM GROUP_JOIN where GB_ID = ? and MEM_ID = ? ";
	private static final String UPDATE = "UPDATE GROUP_JOIN set  GBPAY_STATUS=?, PICKUP_STATUS=?, DELIVER_STATUS=?, GBBUY_AMOUNT=?, GBBUY_PRICE=? where (GB_ID = ?) and (MEM_ID = ?)";
	private static final String UPDATEPAY = "UPDATE GROUP_JOIN set  GBPAY_STATUS=? where (GB_ID = ?) and (MEM_ID = ?)";
	private static final String UPDATEPICKUP = "UPDATE GROUP_JOIN set  PICKUP_STATUS=? where (GB_ID = ?) and (MEM_ID = ?)";
	private static final String UPDATEDELIVER = "UPDATE GROUP_JOIN set  DELIVER_STATUS=? where (GB_ID = ?) and (MEM_ID = ?)";


	public List<Group_JoinVO> findByMem(Integer mem_id) {

		List<Group_JoinVO> list = new ArrayList<Group_JoinVO>();
		Group_JoinVO group_joinVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_BYMEM);
			pstmt.setInt(1, mem_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				group_joinVO = new Group_JoinVO();
				group_joinVO.setGb_id(rs.getInt("gb_id"));
				group_joinVO.setMem_id(rs.getInt("mem_id"));
				group_joinVO.setGbpay_status(rs.getInt("gbpay_status"));
				group_joinVO.setPickup_status(rs.getInt("pickup_status"));
				group_joinVO.setDeliver_status(rs.getInt("deliver_status"));
				group_joinVO.setGbbuy_amount(rs.getInt("gbbuy_amount"));
				group_joinVO.setGbbuy_price(rs.getInt("gbbuy_price"));
				list.add(group_joinVO);
				System.out.println(rs.getInt("gb_id"));
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null || con != null) {
				try {
					rs.close();con.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		return list;
	}



	@Override
	public void insert(Group_JoinVO Group_JoinVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, Group_JoinVO.getGb_id());
			pstmt.setInt(2, Group_JoinVO.getMem_id());
			pstmt.setInt(3, Group_JoinVO.getGbpay_status());
			pstmt.setInt(4, Group_JoinVO.getPickup_status());
			pstmt.setInt(5, Group_JoinVO.getDeliver_status());
			pstmt.setInt(6, Group_JoinVO.getGbbuy_amount());
			pstmt.setInt(7, Group_JoinVO.getGbbuy_price());
			pstmt.executeUpdate();

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
	public void update(Group_JoinVO Group_JoinVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, Group_JoinVO.getGbpay_status());
			pstmt.setInt(2, Group_JoinVO.getPickup_status());
			pstmt.setInt(3, Group_JoinVO.getDeliver_status());
			pstmt.setInt(4, Group_JoinVO.getGbbuy_amount());
			pstmt.setInt(5, Group_JoinVO.getGbbuy_price());
			pstmt.setInt(6, Group_JoinVO.getGb_id());
			pstmt.setInt(7, Group_JoinVO.getMem_id());

			pstmt.executeUpdate();

			// Handle any driver errors
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

	public void updatePay(Group_JoinVO Group_JoinVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATEPAY);

			pstmt.setInt(1, Group_JoinVO.getGbpay_status());
			pstmt.setInt(2, Group_JoinVO.getGb_id());
			pstmt.setInt(3, Group_JoinVO.getMem_id());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null || con != null) {
				try {
					pstmt.close();
					con.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	public void updatePickup(Group_JoinVO Group_JoinVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATEPICKUP);

			pstmt.setInt(1, Group_JoinVO.getPickup_status());
			pstmt.setInt(2, Group_JoinVO.getGb_id());
			pstmt.setInt(3, Group_JoinVO.getMem_id());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null || con != null) {
				try {
					pstmt.close();
					con.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	public void updateDeliver(Group_JoinVO Group_JoinVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATEDELIVER);

			pstmt.setInt(1, Group_JoinVO.getDeliver_status());
			pstmt.setInt(2, Group_JoinVO.getGb_id());
			pstmt.setInt(3, Group_JoinVO.getMem_id());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null || con != null) {
				try {
					pstmt.close();
					con.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void deleteGj(Integer gb_id, Integer mem_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, gb_id);
			pstmt.setInt(2, mem_id);

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());

		} finally {
			if (pstmt != null || con != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public Group_JoinVO findByPrimaryKey(Integer gb_id, Integer mem_id) {
		Group_JoinVO Group_JoinVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, gb_id);
			pstmt.setInt(2, mem_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Group_JoinVO = new Group_JoinVO();
				Group_JoinVO.setGb_id(rs.getInt("gb_id"));
				Group_JoinVO.setMem_id(rs.getInt("mem_id"));
				Group_JoinVO.setGbpay_status(rs.getInt("gbpay_status"));
				Group_JoinVO.setPickup_status(rs.getInt("pickup_status"));
				Group_JoinVO.setDeliver_status(rs.getInt("deliver_status"));
				Group_JoinVO.setGbbuy_amount(rs.getInt("gbbuy_amount"));
				Group_JoinVO.setGbbuy_price(rs.getInt("gbbuy_price"));
			}

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
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		return Group_JoinVO;
	}

	@Override
	public List<Group_JoinVO> getAll() {
		List<Group_JoinVO> list = new ArrayList<Group_JoinVO>();
		Group_JoinVO Group_JoinVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Group_JoinVO = new Group_JoinVO();
				Group_JoinVO.setGb_id(rs.getInt("gb_id"));
				Group_JoinVO.setMem_id(rs.getInt("mem_id"));
				Group_JoinVO.setGbpay_status(rs.getInt("gbpay_status"));
				Group_JoinVO.setPickup_status(rs.getInt("pickup_status"));
				Group_JoinVO.setDeliver_status(rs.getInt("deliver_status"));
				Group_JoinVO.setGbbuy_amount(rs.getInt("gbbuy_amount"));
				Group_JoinVO.setGbbuy_price(rs.getInt("gbbuy_price"));

				list.add(Group_JoinVO);
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null || pstmt != null || con != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}

		}

		return list;
	}

	public List<Group_JoinVO> findBygbid(Integer gb_id) {

		List<Group_JoinVO> list = new ArrayList<Group_JoinVO>();
		Group_JoinVO group_joinVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_GB);
			pstmt.setInt(1, gb_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				group_joinVO = new Group_JoinVO();
				group_joinVO.setGb_id(rs.getInt("gb_id"));
				group_joinVO.setMem_id(rs.getInt("mem_id"));
				group_joinVO.setGbpay_status(rs.getInt("gbpay_status"));
				group_joinVO.setPickup_status(rs.getInt("pickup_status"));
				group_joinVO.setDeliver_status(rs.getInt("deliver_status"));
				group_joinVO.setGbbuy_amount(rs.getInt("gbbuy_amount"));
				group_joinVO.setGbbuy_price(rs.getInt("gbbuy_price"));
				list.add(group_joinVO);
				System.out.println(rs.getInt("gb_id"));
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null || con != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	public List<Group_JoinVO> getAll(Map<String, String[]> map) {
		List<Group_JoinVO> list = new ArrayList<Group_JoinVO>();
		Group_JoinVO group_joinVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			String MySql = "select *  from group_join  " + Group_JoinGetAll.getWhereCondition(map) + "order by gb_id";
			pstmt = con.prepareStatement(MySql);
			rs = pstmt.executeQuery();

			System.out.println(MySql);
			while (rs.next()) {
				group_joinVO = new Group_JoinVO();
				group_joinVO.setGb_id(rs.getInt("gb_id"));
				group_joinVO.setMem_id(rs.getInt("mem_id"));
				group_joinVO.setGbpay_status(rs.getInt("gbpay_status"));
				group_joinVO.setPickup_status(rs.getInt("pickup_status"));
				group_joinVO.setDeliver_status(rs.getInt("deliver_status"));
				group_joinVO.setGbbuy_amount(rs.getInt("gbbuy_amount"));
				group_joinVO.setGbbuy_price(rs.getInt("gbbuy_price"));
				list.add(group_joinVO);
			}

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
}
