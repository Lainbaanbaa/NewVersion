package com.group_buy_order.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.emp.model.EmpNoEffect;
import com.emp.model.EmpVO;

public class Group_Buy_OrderDAO implements Group_Buy_OrderDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/hikariCP-BaRei");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT_FIRST = "insert into GROUP_BUY_ORDER (GBITEM_ID, GB_ID, GBITEM_AMOUNT, GBORIGINAL_PRICE, GB_ENDPRICE, GBORDER_PAYING,GBORDER_SEND, GBORDER_STATUS, GBORDER_OTHER, RECEIVER_NAME, RECEIVER_ADDRESS, RECEIVER_PHONE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
	private static final String INSERT_STMT = "insert into GROUP_BUY_ORDER (GBITEM_ID, GB_ID, GBITEM_AMOUNT, GBORIGINAL_PRICE,, GB_ENDPRICE, GBORDER_DATE, GBORDER_PAYING, GBORDER_SEND, GBORDER_STATUS, GBORDER_OTHER, TRACKING_NUM, RECEIVER_NAME, RECEIVER_ADDRESS, RECEIVER_PHONE, PICKUP_TIME) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	/****************後臺用****************/
	private static final String GET_ALL_STMT = "SELECT GBORDER_ID, GBITEM_ID, GB_ID, GBITEM_AMOUNT, GBORIGINAL_PRICE, GB_ENDPRICE, GBORDER_DATE, GBORDER_PAYING, GBORDER_SEND, GBORDER_STATUS, GBORDER_OTHER, TRACKING_NUM, RECEIVER_NAME, RECEIVER_ADDRESS, RECEIVER_PHONE, PICKUP_TIME FROM GROUP_BUY_ORDER order by GBORDER_ID";
	private static final String GET_ONE_STMT = "SELECT GBORDER_ID, GBITEM_ID, GB_ID, GBITEM_AMOUNT, GBORIGINAL_PRICE, GB_ENDPRICE, GBORDER_DATE, GBORDER_PAYING, GBORDER_SEND, GBORDER_STATUS, GBORDER_OTHER, TRACKING_NUM, RECEIVER_NAME, RECEIVER_ADDRESS, RECEIVER_PHONE, PICKUP_TIME FROM GROUP_BUY_ORDER where GBORDER_ID = ?";
	private static final String DELETE = "DELETE FROM GROUP_BUY_ORDER where GBORDER_ID = ?";
	private static final String UPDATE = "UPDATE GROUP_BUY_ORDER set GBITEM_ID=?, GB_ID=?, GBITEM_AMOUNT=?, GBORIGINAL_PRICE=?, GB_ENDPRICE=?, GBORDER_DATE=?, GBORDER_PAYING=?, GBORDER_SEND=?, GBORDER_STATUS=?, GBORDER_OTHER=?, TRACKING_NUM=?, RECEIVER_NAME=?, RECEIVER_ADDRESS=?, RECEIVER_PHONE=?, PICKUP_TIME=? where GBORDER_ID = ?";

	/*********************** 團購主收確認 ***************************/
	private static final String UPDATE_P_T = "UPDATE GROUP_BUY_ORDER set PICKUP_TIME=current_timestamp() where GBORDER_ID = ?";

	/*********************** 團購主繳費確認 ***************************/
	private static final String UPDATE_PAYING = "UPDATE GROUP_BUY_ORDER set GBORDER_STATUS =3 where GBORDER_ID = ?";

	@Override

	/*********************** 團購主收確認 ***************************/

	public void update_pt(Group_Buy_OrderVO group_Buy_OrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_P_T);
			pstmt.setInt(1, group_Buy_OrderVO.getGborder_id());

			pstmt.executeUpdate();
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

	/*********************** 團購主繳費確認 ***************************/

	public void update_paying(Group_Buy_OrderVO group_Buy_OrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_PAYING);
			pstmt.setInt(1, group_Buy_OrderVO.getGborder_id());

			pstmt.executeUpdate();
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

	public Group_Buy_OrderVO insert_first(Group_Buy_OrderVO Group_Buy_OrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		int gborder_id = 0;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT_FIRST, pstmt.RETURN_GENERATED_KEYS);

			pstmt.setInt(1, Group_Buy_OrderVO.getGbitem_id());
			pstmt.setInt(2, Group_Buy_OrderVO.getGb_id());
			pstmt.setInt(3, Group_Buy_OrderVO.getGbitem_amount());
			pstmt.setInt(4, Group_Buy_OrderVO.getGboriginal_price());
			pstmt.setInt(5, Group_Buy_OrderVO.getGb_endprice());
			pstmt.setInt(6, Group_Buy_OrderVO.getGborder_paying());
			pstmt.setInt(7, Group_Buy_OrderVO.getGborder_send());
			pstmt.setInt(8, Group_Buy_OrderVO.getGborder_status());
			pstmt.setString(9, Group_Buy_OrderVO.getGborder_other());
			pstmt.setString(10, Group_Buy_OrderVO.getReceiver_name());
			pstmt.setString(11, Group_Buy_OrderVO.getReceiver_address());
			pstmt.setString(12, Group_Buy_OrderVO.getReceiver_phone());

			pstmt.executeUpdate();

			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				gborder_id = rs.getInt(1);
			}

			pstmt2 = con.prepareStatement(GET_ONE_STMT);
			pstmt2.setInt(1, gborder_id);
			rs = pstmt2.executeQuery();

			while (rs.next()) {
				Group_Buy_OrderVO = new Group_Buy_OrderVO();

				Group_Buy_OrderVO.setGborder_id(rs.getInt("gborder_id"));
				Group_Buy_OrderVO.setGbitem_id(rs.getInt("gbitem_id"));
				Group_Buy_OrderVO.setGb_id(rs.getInt("gb_id"));
				Group_Buy_OrderVO.setGbitem_amount(rs.getInt("gbitem_amount"));
				Group_Buy_OrderVO.setGboriginal_price(rs.getInt("gboriginal_price"));
				Group_Buy_OrderVO.setGb_endprice(rs.getInt("gb_endprice"));
				Group_Buy_OrderVO.setGborder_date(rs.getTimestamp("gborder_date"));
				Group_Buy_OrderVO.setGborder_paying(rs.getInt("gborder_paying"));
				Group_Buy_OrderVO.setGborder_send(rs.getInt("gborder_send"));
				Group_Buy_OrderVO.setGborder_status(rs.getInt("gborder_status"));
				Group_Buy_OrderVO.setGborder_other(rs.getString("gborder_other"));
				Group_Buy_OrderVO.setTracking_num(rs.getString("tracking_num"));
				Group_Buy_OrderVO.setReceiver_name(rs.getString("receiver_name"));
				Group_Buy_OrderVO.setReceiver_address(rs.getString("receiver_address"));
				Group_Buy_OrderVO.setReceiver_phone(rs.getString("receiver_phone"));
				Group_Buy_OrderVO.setPickup_time(rs.getTimestamp("pickup_time"));
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null || con != null || pstmt2 != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}

		}
		return Group_Buy_OrderVO;
	}

	public void insert(Group_Buy_OrderVO Group_Buy_OrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, Group_Buy_OrderVO.getGbitem_id());
			pstmt.setInt(2, Group_Buy_OrderVO.getGb_id());
			pstmt.setInt(3, Group_Buy_OrderVO.getGbitem_amount());
			pstmt.setInt(4, Group_Buy_OrderVO.getGboriginal_price());
			pstmt.setInt(6, Group_Buy_OrderVO.getGb_endprice());
			pstmt.setTimestamp(7, Group_Buy_OrderVO.getGborder_date());
			pstmt.setInt(8, Group_Buy_OrderVO.getGborder_paying());
			pstmt.setInt(9, Group_Buy_OrderVO.getGborder_send());
			pstmt.setInt(10, Group_Buy_OrderVO.getGborder_status());
			pstmt.setString(11, Group_Buy_OrderVO.getGborder_other());
			pstmt.setString(12, Group_Buy_OrderVO.getTracking_num());
			pstmt.setString(13, Group_Buy_OrderVO.getReceiver_name());
			pstmt.setString(14, Group_Buy_OrderVO.getReceiver_address());
			pstmt.setString(15, Group_Buy_OrderVO.getReceiver_phone());
			pstmt.setTimestamp(16, Group_Buy_OrderVO.getPickup_time());

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
	public Group_Buy_OrderVO updateone(Group_Buy_OrderVO group_buy_orderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, group_buy_orderVO.getGbitem_id());
			pstmt.setInt(2, group_buy_orderVO.getGb_id());
			pstmt.setInt(3, group_buy_orderVO.getGbitem_amount());
			pstmt.setInt(4, group_buy_orderVO.getGboriginal_price());
			pstmt.setInt(5, group_buy_orderVO.getGb_endprice());
			pstmt.setTimestamp(6, group_buy_orderVO.getGborder_date());
			pstmt.setInt(7, group_buy_orderVO.getGborder_paying());
			pstmt.setInt(8, group_buy_orderVO.getGborder_send());
			pstmt.setInt(9, group_buy_orderVO.getGborder_status());
			pstmt.setString(10, group_buy_orderVO.getGborder_other());
			pstmt.setString(11, group_buy_orderVO.getTracking_num());
			pstmt.setString(12, group_buy_orderVO.getReceiver_name());
			pstmt.setString(13, group_buy_orderVO.getReceiver_address());
			pstmt.setString(14, group_buy_orderVO.getReceiver_phone());
			pstmt.setTimestamp(15, group_buy_orderVO.getPickup_time());
			pstmt.setInt(16, group_buy_orderVO.getGborder_id());
			pstmt.executeUpdate();

			pstmt2 = con.prepareStatement(GET_ONE_STMT);
			pstmt2.setInt(1, group_buy_orderVO.getGborder_id());

			rs = pstmt2.executeQuery();

			while (rs.next()) {
				group_buy_orderVO = new Group_Buy_OrderVO();
				group_buy_orderVO.setGborder_id(rs.getInt("gborder_id"));
				group_buy_orderVO.setGbitem_id(rs.getInt("gbitem_id"));
				group_buy_orderVO.setGb_id(rs.getInt("gb_id"));
				group_buy_orderVO.setGbitem_amount(rs.getInt("gbitem_amount"));
				group_buy_orderVO.setGboriginal_price(rs.getInt("gboriginal_price"));
				group_buy_orderVO.setGb_endprice(rs.getInt("gb_endprice"));
				group_buy_orderVO.setGborder_date(rs.getTimestamp("gborder_date"));
				group_buy_orderVO.setGborder_paying(rs.getInt("gborder_paying"));
				group_buy_orderVO.setGborder_send(rs.getInt("gborder_send"));
				group_buy_orderVO.setGborder_status(rs.getInt("gborder_status"));
				group_buy_orderVO.setGborder_other(rs.getString("gborder_other"));
				group_buy_orderVO.setTracking_num(rs.getString("tracking_num"));
				group_buy_orderVO.setReceiver_name(rs.getString("receiver_name"));
				group_buy_orderVO.setReceiver_address(rs.getString("receiver_address"));
				group_buy_orderVO.setReceiver_phone(rs.getString("receiver_phone"));
				group_buy_orderVO.setPickup_time(rs.getTimestamp("pickup_time"));
			}

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
		return group_buy_orderVO;
	}

	@Override
	public void delete(Integer gborder_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, gborder_id);

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
	public Group_Buy_OrderVO findByPrimaryKey(Integer gborder_id) {
		Group_Buy_OrderVO Group_Buy_OrderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, gborder_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Group_Buy_OrderVO = new Group_Buy_OrderVO();
				Group_Buy_OrderVO.setGborder_id(rs.getInt("gborder_id"));
				Group_Buy_OrderVO.setGbitem_id(rs.getInt("gbitem_id"));
				Group_Buy_OrderVO.setGb_id(rs.getInt("gb_id"));
				Group_Buy_OrderVO.setGbitem_amount(rs.getInt("gbitem_amount"));
				Group_Buy_OrderVO.setGboriginal_price(rs.getInt("gboriginal_price"));
				Group_Buy_OrderVO.setGb_endprice(rs.getInt("gb_endprice"));
				Group_Buy_OrderVO.setGborder_date(rs.getTimestamp("gborder_date"));
				Group_Buy_OrderVO.setGborder_paying(rs.getInt("gborder_paying"));
				Group_Buy_OrderVO.setGborder_send(rs.getInt("gborder_send"));
				Group_Buy_OrderVO.setGborder_status(rs.getInt("gborder_status"));
				Group_Buy_OrderVO.setGborder_other(rs.getString("gborder_other"));
				Group_Buy_OrderVO.setTracking_num(rs.getString("tracking_num"));
				Group_Buy_OrderVO.setReceiver_name(rs.getString("receiver_name"));
				Group_Buy_OrderVO.setReceiver_address(rs.getString("receiver_address"));
				Group_Buy_OrderVO.setReceiver_phone(rs.getString("receiver_phone"));

				Group_Buy_OrderVO.setPickup_time(rs.getTimestamp("pickup_time"));
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
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return Group_Buy_OrderVO;
	}

	@Override
	public List<Group_Buy_OrderVO> getAll() {
		List<Group_Buy_OrderVO> list = new ArrayList<Group_Buy_OrderVO>();
		Group_Buy_OrderVO Group_Buy_OrderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Group_Buy_OrderVO = new Group_Buy_OrderVO();
				Group_Buy_OrderVO.setGborder_id(rs.getInt("gborder_id"));
				Group_Buy_OrderVO.setGbitem_id(rs.getInt("gbitem_id"));
				Group_Buy_OrderVO.setGb_id(rs.getInt("gb_id"));
				Group_Buy_OrderVO.setGbitem_amount(rs.getInt("gbitem_amount"));
				Group_Buy_OrderVO.setGboriginal_price(rs.getInt("gboriginal_price"));
				Group_Buy_OrderVO.setGb_endprice(rs.getInt("gb_endprice"));
				Group_Buy_OrderVO.setGborder_date(rs.getTimestamp("gborder_date"));
				Group_Buy_OrderVO.setGborder_paying(rs.getInt("gborder_paying"));
				Group_Buy_OrderVO.setGborder_send(rs.getInt("gborder_send"));
				Group_Buy_OrderVO.setGborder_status(rs.getInt("gborder_status"));
				Group_Buy_OrderVO.setGborder_other(rs.getString("gborder_other"));
				Group_Buy_OrderVO.setTracking_num(rs.getString("tracking_num"));
				Group_Buy_OrderVO.setReceiver_name(rs.getString("receiver_name"));
				Group_Buy_OrderVO.setReceiver_address(rs.getString("receiver_address"));
				Group_Buy_OrderVO.setReceiver_phone(rs.getString("receiver_phone"));
				Group_Buy_OrderVO.setPickup_time(rs.getTimestamp("pickup_time"));
				list.add(Group_Buy_OrderVO);
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
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	public List<Group_Buy_OrderVO> getAll(Map<String, String[]> map) {
		List<Group_Buy_OrderVO> list = new ArrayList<Group_Buy_OrderVO>();
		Group_Buy_OrderVO group_Buy_OrderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			String MySql = "select * from group_buy_order " + Group_Buy_Order_Complx.getWhereCondition(map)
					+ "order by gborder_id";
			pstmt = con.prepareStatement(MySql);
			rs = pstmt.executeQuery();

			System.out.println(MySql);
			while (rs.next()) {
				group_Buy_OrderVO = new Group_Buy_OrderVO();
				group_Buy_OrderVO.setGborder_id(rs.getInt("gborder_id"));
				group_Buy_OrderVO.setGbitem_id(rs.getInt("gbitem_id"));
				group_Buy_OrderVO.setGb_id(rs.getInt("gb_id"));
				group_Buy_OrderVO.setGbitem_amount(rs.getInt("gbitem_amount"));
				group_Buy_OrderVO.setGboriginal_price(rs.getInt("gboriginal_price"));
				group_Buy_OrderVO.setGb_endprice(rs.getInt("gb_endprice"));
				group_Buy_OrderVO.setGborder_date(rs.getTimestamp("gborder_date"));
				group_Buy_OrderVO.setGborder_paying(rs.getInt("gborder_paying"));
				group_Buy_OrderVO.setGborder_send(rs.getInt("gborder_send"));
				group_Buy_OrderVO.setGborder_status(rs.getInt("gborder_status"));
				group_Buy_OrderVO.setGborder_other(rs.getString("gborder_other"));
				group_Buy_OrderVO.setTracking_num(rs.getString("tracking_num"));
				group_Buy_OrderVO.setReceiver_name(rs.getString("receiver_name"));
				group_Buy_OrderVO.setReceiver_address(rs.getString("receiver_address"));
				group_Buy_OrderVO.setReceiver_phone(rs.getString("receiver_phone"));
				group_Buy_OrderVO.setPickup_time(rs.getTimestamp("pickup_time"));
				list.add(group_Buy_OrderVO);
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
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	public static void main(String[] args) {

		Group_Buy_OrderDAO dao = new Group_Buy_OrderDAO();
		// 新增
//		Group_Buy_OrderVO gboVO1 = new Group_Buy_OrderVO();
//		gboVO1.setGbitem_id(2);
////		gboVO1.setGb_id(2);
//		gboVO1.setGbitem_amount(50);
//		gboVO1.setGboriginal_price(129000);
//
//
//		gboVO1.setGb_endprice(90300);
//
//
//		gboVO1.setGborder_paying(0);
//		gboVO1.setGborder_send(1);
//		gboVO1.setGborder_status(0);
//
//		gboVO1.setGborder_other("無");
//		gboVO1.setTracking_num("test00002");
//		gboVO1.setReceiver_name("灰醬");
//		gboVO1.setReceiver_address("桃園市桃園區田園路10號7樓");
//		gboVO1.setReceiver_phone("0988511511");
////		gboVO1.setPickup_time(Timestamp.valueOf(LocalDateTime.now()));
//		gboVO1.setGborder_date(Timestamp.valueOf(LocalDateTime.now()));
//		  //PK
//
//		dao.insert_first(gboVO1);

		// 修改
		Group_Buy_OrderVO gboVO2 = new Group_Buy_OrderVO();
		gboVO2.setGborder_id(3); // PK
		dao.update_pt(gboVO2);

		// 刪除
//		dao.delete(5);

		// 查詢
//		Group_Buy_OrderVO gboVO3 = dao.findByPrimaryKey(2);
//		System.out.print(gboVO3.getGborder_id() + ",");
//		System.out.print(gboVO3.getGbitem_id() + ",");
//		System.out.print(gboVO3.getGb_id() + ",");
//		System.out.print(gboVO3.getGbitem_amount() + ",");
//		System.out.print(gboVO3.getGboriginal_price() + ",");
//		System.out.print(gboVO3.getDiscount_id() + ",");
//		System.out.print(gboVO3.getGb_endprice() + ",");
//		System.out.print(gboVO3.getGborder_date() + ",");
//		System.out.print(gboVO3.getGborder_paying() + ",");
//		System.out.print(gboVO3.getGborder_send() + ",");
//		System.out.print(gboVO3.getGborder_status() + ",");
//		System.out.print(gboVO3.getGborder_other() + ",");
//		System.out.print(gboVO3.getTracking_num() + ",");
//		System.out.print(gboVO3.getReceiver_name() + ",");
//		System.out.print(gboVO3.getReceiver_address() + ",");
//		System.out.print(gboVO3.getReceiver_phone() + ",");
//		System.out.println(gboVO3.getPickup_time());
//		System.out.println("---------------------");

		// 查詢
//		List<Group_Buy_OrderVO> list = dao.getAll();
//		for (Group_Buy_OrderVO aGbo : list) {
//			System.out.print(aGbo.getGborder_id() + ",");
//			System.out.print(aGbo.getGbitem_id() + ",");
//			System.out.print(aGbo.getGb_id() + ",");
//			System.out.print(aGbo.getGbitem_amount() + ",");
//			System.out.print(aGbo.getGboriginal_price() + ",");
//			System.out.print(aGbo.getDiscount_id() + ",");
//			System.out.print(aGbo.getGb_endprice() + ",");
//			System.out.print(aGbo.getGborder_date() + ",");
//			System.out.print(aGbo.getGborder_paying() + ",");
//			System.out.print(aGbo.getGborder_send() + ",");
//			System.out.print(aGbo.getGborder_status() + ",");
//			System.out.print(aGbo.getGborder_other() + ",");
//			System.out.print(aGbo.getTracking_num() + ",");
//			System.out.print(aGbo.getReceiver_name() + ",");
//			System.out.print(aGbo.getReceiver_address() + ",");
//			System.out.print(aGbo.getReceiver_phone() + ",");
//			System.out.print(aGbo.getPickup_time());
//			System.out.println();
	}
}
