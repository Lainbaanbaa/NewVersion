
package com.notification.mode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


//import com.notification.*;

public class NotificationJDBCDAO implements NotificationDAO_interface{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/Ba-Rei?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";

	private static final String INSERT_STMT =
		"INSERT INTO notification (mem_id,ntf_context,ntf_read) VALUES (?, ?, ?)";
	private static final String GET_ALL_STMT =
		"SELECT ntf_id,mem_id,ntf_context,ntf_time,ntf_read FROM notification order by ntf_id";
	private static final String GET_ONE_STMT =
		"SELECT ntf_id,mem_id,ntf_context,ntf_time,ntf_read FROM notification where ntf_id = ?";
	private static final String DELETE =
		"DELETE FROM notification where ntf_id = ?";
	private static final String UPDATE =
		"UPDATE notification set mem_id=?, ntf_context=?, ntf_read=?  where ntf_id = ?";

	@Override
	public void insert(NotificationVO notificationVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, notificationVO.getMem_id());
			pstmt.setString(2, notificationVO.getNtf_context());
	//		pstmt.setTimestamp(3, notificationVO.getNtf_time());
			pstmt.setInt(3, notificationVO.getNtf_read());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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

	@Override
	public void update(NotificationVO notificationVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, notificationVO.getMem_id());
			pstmt.setString(2, notificationVO.getNtf_context());
//			pstmt.setTimestamp(3, notificationVO.getNtf_time());
			pstmt.setInt(3, notificationVO.getNtf_read());
			pstmt.setInt(4, notificationVO.getNtf_id());


			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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

	@Override
	public void delete(Integer ntf_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, ntf_id);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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

	@Override
	public NotificationVO findByPrimaryKey(Integer ntf_id) {

		NotificationVO notificationVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, ntf_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				notificationVO = new NotificationVO();
				notificationVO.setMem_id(rs.getInt("mem_id"));
				notificationVO.setNtf_context(rs.getString("ntf_context"));
				notificationVO.setNtf_time(rs.getTimestamp("ntf_time"));
				notificationVO.setNtf_read(rs.getInt("ntf_read"));

			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
		return notificationVO;
	}

	@Override
	public List<NotificationVO> getAll() {
		List<NotificationVO> list = new ArrayList<NotificationVO>();
		NotificationVO notificationVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				notificationVO = new NotificationVO();
				notificationVO.setMem_id(rs.getInt("mem_id"));
				notificationVO.setNtf_context(rs.getString("ntf_context"));
				notificationVO.setNtf_time(rs.getTimestamp("ntf_time"));
				notificationVO.setNtf_read(rs.getInt("ntf_read"));

				list.add(notificationVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
		return list;
	}

	public static void main(String[] args) {
		NotificationJDBCDAO dao = new NotificationJDBCDAO();


		// 新增
		NotificationVO notificationVO1 = new NotificationVO();
		notificationVO1.setMem_id(2);
		notificationVO1.setNtf_context("test");
//		notificationVO2.setNtf_time("2005-01-01 12:10:10");
		notificationVO1.setNtf_read(1);

		dao.insert(notificationVO1);

		// 修改
		NotificationVO notificationVO2 = new NotificationVO();
		notificationVO2.setNtf_id(2);
		notificationVO2.setMem_id(2);
		notificationVO2.setNtf_context("ntfntfntfntf");
//		notificationVO2.setNtf_time("2005-01-01 12:10:10");
		notificationVO2.setNtf_read(0);
		dao.update(notificationVO2);

		// 刪除
		dao.delete(1);

		// 查詢
		NotificationVO notificationVO3 = dao.findByPrimaryKey(2);
		System.out.print(notificationVO3.getMem_id() + ",");
		System.out.print(notificationVO3.getNtf_context() + ",");
		System.out.print(notificationVO3.getNtf_time() + ",");
		System.out.print(notificationVO3.getNtf_read() + ",");

		System.out.println("---------------------");

		// 查詢
		List<NotificationVO> list = dao.getAll();
		for (NotificationVO aNotificationVO : list) {
			System.out.print(aNotificationVO.getMem_id() + ",");
			System.out.print(aNotificationVO.getNtf_context() + ",");
			System.out.print(aNotificationVO.getNtf_time() + ",");
			System.out.print(aNotificationVO.getNtf_read() + ",");
			System.out.println();
		}
	}



}
