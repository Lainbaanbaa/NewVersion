package com.group_buy_item_picture.model;

import java.util.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.group_buy_item.model.Group_Buy_ItemVO;

public class groupBuyItemPictureJDBCDAO implements groupBuyItemPictureDAO_interface {

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/ba_rei?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";

	private static final String INSERT_STMT = "INSERT INTO GROUP_BUY_ITEM_PICTURE (GBITEM_ID, GBIP_CONTENT) VALUES (?, ?)";

	private static final String GET_ALL_STMT = "SELECT GBIP_ID, GBITEM_ID, GBIP_CONTENT FROM GROUP_BUY_ITEM_PICTURE";

	private static final String GET_ONE_STMT = "SELECT GBIP_ID, GBITEM_ID, GBIP_CONTENT FROM GROUP_BUY_ITEM_PICTURE where GBIP_ID = ?";

	private static final String DELETE = "DELETE FROM GROUP_BUY_ITEM_PICTURE where GBIP_ID = ?";

	private static final String UPDATE = "UPDATE GROUP_BUY_ITEM_PICTURE set GBITEM_ID=?, GBIP_CONTENT=? where GBIP_ID = ?";

	private static final String GET_ALL_STMT_GBITEM_ID = "SELECT GBIP_ID, GBITEM_ID, GBIP_CONTENT FROM GROUP_BUY_ITEM_PICTURE where GBITEM_ID = ?";

	private static final String GET_ONE_STMT_GBITEM_ID = "SELECT GBIP_ID, GBITEM_ID, GBIP_CONTENT FROM GROUP_BUY_ITEM_PICTURE where GBITEM_ID = ? limit 1";

	private static final String GET_Second_STMT_GBITEM_ID = "SELECT GBIP_ID, GBITEM_ID, GBIP_CONTENT FROM GROUP_BUY_ITEM_PICTURE where GBITEM_ID = ? limit 2";
	@Override
	public void insert(groupBuyItemPictureVO groupBuyItemPictureVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, groupBuyItemPictureVO.getGbitem_id());
			pstmt.setBytes(2, groupBuyItemPictureVO.getGbip_content());

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
	public void update(groupBuyItemPictureVO groupBuyItemPictureVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, groupBuyItemPictureVO.getGbitem_id());
			pstmt.setBytes(2, groupBuyItemPictureVO.getGbip_content());
			pstmt.setInt(3, groupBuyItemPictureVO.getGbip_id());

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
	public void delete(Integer gbip_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, gbip_id);

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
	public groupBuyItemPictureVO findByPrimaryKey(Integer gbip_id) {
		groupBuyItemPictureVO groupBuyItemPictureVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, gbip_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				groupBuyItemPictureVO = new groupBuyItemPictureVO();
				groupBuyItemPictureVO.setGbip_id(rs.getInt("gbip_id"));
				groupBuyItemPictureVO.setGbitem_id(rs.getInt("gbitem_id"));
				groupBuyItemPictureVO.setGbip_content(rs.getBytes("gbip_content"));
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
		return groupBuyItemPictureVO;
	}

	@Override
	public List<groupBuyItemPictureVO> getAll() {
		List<groupBuyItemPictureVO> list = new ArrayList<groupBuyItemPictureVO>();
		groupBuyItemPictureVO groupBuyItemPictureVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				groupBuyItemPictureVO = new groupBuyItemPictureVO();
				groupBuyItemPictureVO.setGbip_id(rs.getInt("gbip_id"));
				groupBuyItemPictureVO.setGbitem_id(rs.getInt("gbitem_id"));
				groupBuyItemPictureVO.setGbip_content(rs.getBytes("gbip_content"));
				list.add(groupBuyItemPictureVO);
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

	@Override
	public List<groupBuyItemPictureVO> findByGbitemID(Integer gbitem_id) {
		List<groupBuyItemPictureVO> list = new ArrayList<groupBuyItemPictureVO>();
		groupBuyItemPictureVO groupBuyItemPictureVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT_GBITEM_ID);
			pstmt.setInt(1, gbitem_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				groupBuyItemPictureVO = new groupBuyItemPictureVO();
				groupBuyItemPictureVO.setGbip_id(rs.getInt("gbip_id"));
				groupBuyItemPictureVO.setGbitem_id(rs.getInt("gbitem_id"));
				groupBuyItemPictureVO.setGbip_content(rs.getBytes("gbip_content"));
				list.add(groupBuyItemPictureVO);
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

	@Override
	public groupBuyItemPictureVO findFirstPICByGbitemID(Integer gbitem_id) {
		groupBuyItemPictureVO groupBuyItemPictureVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT_GBITEM_ID);
			pstmt.setInt(1, gbitem_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				groupBuyItemPictureVO = new groupBuyItemPictureVO();
				groupBuyItemPictureVO.setGbip_id(rs.getInt("gbip_id"));
				groupBuyItemPictureVO.setGbitem_id(rs.getInt("gbitem_id"));
				groupBuyItemPictureVO.setGbip_content(rs.getBytes("gbip_content"));
			}

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
		return groupBuyItemPictureVO;
	}
	public groupBuyItemPictureVO findSecondtPICByGbitemID(Integer gbitem_id) {
		groupBuyItemPictureVO groupBuyItemPictureVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_Second_STMT_GBITEM_ID);
			pstmt.setInt(1, gbitem_id);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				groupBuyItemPictureVO = new groupBuyItemPictureVO();
				groupBuyItemPictureVO.setGbip_id(rs.getInt("gbip_id"));
				groupBuyItemPictureVO.setGbitem_id(rs.getInt("gbitem_id"));
				groupBuyItemPictureVO.setGbip_content(rs.getBytes("gbip_content"));
			}
			
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
		return groupBuyItemPictureVO;
	}

//	public static void main(String[] args) {
//		// 測試建立一個dao物件
//		groupBuyItemPictureJDBCDAO dao = new groupBuyItemPictureJDBCDAO();
//		// 測試dao insert方法
//		// 測試建立一個bidpic物件
//		
//		groupBuyItemPictureVO groupBuyItemPictureVO = new groupBuyItemPictureVO();
//		groupBuyItemPictureVO.setGbitem_id(2);
//		FileInputStream in ;
//		
//		try {
//			in = new FileInputStream("C:\\Users\\Tibame_T14\\Pictures\\Camera Roll\\16pic_6090225_s.png");
//			byte[] buf = new byte[in.available()]; // byte[] buf = in.readAllBytes();  // Java 9 的新方法，一行抵三行
//			in.read(buf);
//			groupBuyItemPictureVO.setGbip_content(buf);
//			in.close();
//			System.out.println("buffer length: " + buf.length);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		dao.insert(groupBuyItemPictureVO);
//		
//		// 測試dao update方法
//		
//		groupBuyItemPictureVO groupBuyItemPictureVO2 = new groupBuyItemPictureVO();
//		groupBuyItemPictureVO2.setGbip_id(13);
//		groupBuyItemPictureVO2.setGbitem_id(3);
//		
//		FileInputStream in2 ;
//		try {
//			in2 = new FileInputStream("C:\\Users\\Tibame_T14\\Pictures\\Camera Roll\\螢幕擷取畫面 2022-10-02 022040.png");
//			byte[] buf = new byte[in2.available()]; // byte[] buf = in.readAllBytes();  // Java 9 的新方法，一行抵三行
//			in2.read(buf);
//			groupBuyItemPictureVO2.setGbip_content(buf);
//			in2.close();
//			System.out.println("buffer length: " + buf.length);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		dao.update(groupBuyItemPictureVO2);
//		
//		// 測試dao delete方法
//		dao.delete(14);
//		
//		//測試dao findByPrimaryKey方法
//		groupBuyItemPictureVO groupBuyItemPictureVO3 = dao.findByPrimaryKey(15);
//		System.out.println(groupBuyItemPictureVO3.getGbip_id());
//		System.out.println(groupBuyItemPictureVO3.getGbitem_id());
//		
//		// 測試dao getall方法
//		List<groupBuyItemPictureVO> list = dao.getAll();
//		for(groupBuyItemPictureVO groupBuyItemPictureVO:list) {
//			System.out.println(groupBuyItemPictureVO.getGbip_id());
//			System.out.println(groupBuyItemPictureVO.getGbitem_id());
//			System.out.println("------------------------------------");
//		}
//		// 測試dao findByGbitemID

//		List<groupBuyItemPictureVO> list2 = dao.findByGbitemID(2);
//		for (groupBuyItemPictureVO groupBuyItemPictureVO : list2) {
//			System.out.println(groupBuyItemPictureVO.getGbip_id());
//			System.out.println(groupBuyItemPictureVO.getGbitem_id());
//			System.out.println("------------------------------------");
//		}
//		list2.forEach(System.out::println);
//		// 測試dao findFirstPICByGbitemID
//		groupBuyItemPictureVO groupBuyItemPictureVO4 = dao.findFirstPICByGbitemID(1);
//		System.out.println(groupBuyItemPictureVO4.getGbip_id());
//		System.out.println(groupBuyItemPictureVO4.getGbitem_id());
//		
//	}
}
