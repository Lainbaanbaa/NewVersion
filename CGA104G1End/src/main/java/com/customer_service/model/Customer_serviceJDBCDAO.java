package com.customer_service.model;

import java.sql.*;
import java.util.*;



public class Customer_serviceJDBCDAO implements Customer_serviceDAO_interface {
	
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/Ba-Rei?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";

	private static final String INSERT_STMT = 
		"INSERT INTO customer_service (mem_id,emp_id,cs_context,cs_pointer) VALUES (?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT cs_id,mem_id,emp_id,cs_time,cs_context,cs_pointer FROM customer_service order by cs_id";
	private static final String GET_ONE_STMT = 
		"SELECT cs_id,mem_id,emp_id,cs_time,cs_context,cs_pointer FROM customer_service where cs_id = ?";
	private static final String DELETE = 
		"DELETE FROM customer_service where cs_id = ?";
	private static final String UPDATE = 
		"UPDATE customer_service set mem_id=?, emp_id=?, cs_context=? ,cs_pointer=? where cs_id = ?";
	
	

	@Override
	public void insert(Customer_serviceVO customer_serviceVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, customer_serviceVO.getMem_id());
			pstmt.setInt(2, customer_serviceVO.getEmp_id());
//			pstmt.setTimestamp(3, customer_serviceVO.getCs_time());
			pstmt.setString(3, customer_serviceVO.getCs_context());
			pstmt.setInt(4, customer_serviceVO.getCs_pointer());


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
	public void update(Customer_serviceVO customer_serviceVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, customer_serviceVO.getMem_id());
			pstmt.setInt(2, customer_serviceVO.getEmp_id());
//			pstmt.setTimestamp(3, customer_serviceVO.getCs_time());
			pstmt.setString(3, customer_serviceVO.getCs_context());
			pstmt.setInt(4, customer_serviceVO.getCs_pointer());
			pstmt.setInt(5, customer_serviceVO.getCs_id());

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
	public void delete(Integer cs_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1,cs_id);

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
	public Customer_serviceVO findByPrimaryKey(Integer cs_id) {
		Customer_serviceVO customer_serviceVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, cs_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				customer_serviceVO = new Customer_serviceVO();
				customer_serviceVO.setMem_id(rs.getInt("mem_id"));
				customer_serviceVO.setEmp_id(rs.getInt("emp_id"));
				customer_serviceVO.setCs_time(rs.getTimestamp("cs_time"));
				customer_serviceVO.setCs_context(rs.getString("cs_context"));
				customer_serviceVO.setCs_pointer(rs.getInt("cs_pointer"));
	
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
		return customer_serviceVO;
	}

	@Override
	public List<Customer_serviceVO> getAll() {
		List<Customer_serviceVO> list = new ArrayList<Customer_serviceVO>();
		Customer_serviceVO customer_serviceVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				customer_serviceVO = new Customer_serviceVO();
				customer_serviceVO.setMem_id(rs.getInt("mem_id"));
				customer_serviceVO.setEmp_id(rs.getInt("emp_id"));
				customer_serviceVO.setCs_time(rs.getTimestamp("cs_time"));
				customer_serviceVO.setCs_context(rs.getString("cs_context"));
				customer_serviceVO.setCs_pointer(rs.getInt("cs_pointer"));
				list.add(customer_serviceVO); // Store the row in the list
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

		Customer_serviceJDBCDAO dao = new Customer_serviceJDBCDAO();

		// 新增
		Customer_serviceVO customer_serviceVO1 = new Customer_serviceVO();
		customer_serviceVO1.setMem_id(1);
		customer_serviceVO1.setEmp_id(1);
//		customer_serviceVO1.setCs_time();
		customer_serviceVO1.setCs_context("cs_cscs");
		customer_serviceVO1.setCs_pointer(1);
		dao.insert(customer_serviceVO1);

		// 修改
		Customer_serviceVO customer_serviceVO2 = new Customer_serviceVO();
		customer_serviceVO2.setMem_id(2);
		customer_serviceVO2.setEmp_id(1);
//		customer_serviceVO2.setCs_time();
		customer_serviceVO2.setCs_context("context");
		customer_serviceVO2.setCs_pointer(1);
		customer_serviceVO2.setCs_id(1);
		
		dao.update(customer_serviceVO2);

		// 刪除
		dao.delete(1);

		// 查詢
		Customer_serviceVO customer_serviceVO3 = dao.findByPrimaryKey(2);
		System.out.print(customer_serviceVO3.getMem_id() + ",");
		System.out.print(customer_serviceVO3.getEmp_id() + ",");
		System.out.print(customer_serviceVO3.getCs_time() + ",");
		System.out.print(customer_serviceVO3.getCs_context() + ",");
		System.out.print(customer_serviceVO3.getCs_pointer() + ",");

		System.out.println("---------------------");

		// 查詢
		List<Customer_serviceVO> list = dao.getAll();
		for (Customer_serviceVO xcustomer_serviceVO : list) {
			System.out.print(xcustomer_serviceVO.getMem_id() + ",");
			System.out.print(xcustomer_serviceVO.getEmp_id() + ",");
			System.out.print(xcustomer_serviceVO.getCs_time() + ",");
			System.out.print(xcustomer_serviceVO.getCs_context() + ",");
			System.out.print(xcustomer_serviceVO.getCs_pointer() + ",");

			System.out.println();
	}
	}
}


