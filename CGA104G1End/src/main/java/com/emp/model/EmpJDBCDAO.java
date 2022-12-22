package com.emp.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.emp_effect.model.*;

public class EmpJDBCDAO implements EmpDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/Ba_Rei?serverTimezone=Asia/Taipei";






	String userid = "root";
	String password = "password";


	private static final String INSERT_STMT = "INSERT INTO emp (emp_name,account,password,onjob_date,emp_status) VALUES(?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT emp_id,emp_name,account,password,onjob_date,emp_status FROM emp order by emp_id";
	private static final String GET_ONE_STMT = "SELECT emp_id,emp_name,account,password,onjob_date,emp_status FROM emp where emp_id = ?";
	private static final String DELETE = "DELETE FROM emp where emp_id = ?";
	private static final String UPDATE = "UPDATE emp set emp_name=? ,account=? ,password=? ,onjob_date=? ,emp_status=? where emp_id = ?";
	
	private static final String LOGIN = "SELECT e.emp_id,emp_name,account,password,onjob_date,emp_status,ef.effect_id,f.effect_name  FROM (emp e join emp_effect ef on e.emp_id = ef.emp_id  )  join effect f on  ef.effect_id = f.effect_id where account = ? and password = ?;";
	
	private static final String INSERT_EFFECT = "INSERT INTO emp_effect (emp_id, effect_id) VALUES (?, ?)";
	private static final String MAXID = "SELECT max(emp_id) EMP_ID from EMP";
			//驗證帳號重複
	private static final String GET_ONE_ACCOUNT = "SELECT account,emp_id  FROM emp where account  = ?";

	
	
	
	public Integer findmaxid() {
		
//		Emp_effectVO emp_effectVO = null;
		int Emp_id = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(MAXID);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				
//				 emp_effectVO = new Emp_effectVO();
				 Emp_id = rs.getInt("emp_id");
//				emp_effectVO.setEmp_id(rs.getInt("emp_id"));
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
		return Emp_id;
	}
	
	
	
		
	public void insert(Emp_effectVO emp_effectVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(INSERT_EFFECT);

			pstmt.setInt(1, emp_effectVO.getEmp_id());
			pstmt.setInt(2, emp_effectVO.getEffect_id());
			pstmt.executeUpdate();
			
		
			
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
	}
	
	@Override
	public void insert(EmpVO empVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, empVO.getEmp_name());
			pstmt.setString(2, empVO.getAccount());
			pstmt.setString(3, empVO.getPassword());
			pstmt.setDate(4, empVO.getOnjob_date());
			pstmt.setInt(5, empVO.getEmp_status());
			pstmt.executeUpdate();
			
			
			int empid = findmaxid();
			int effectid = empVO.getEffect_id();
		
			
			pstmt2 = con.prepareStatement(INSERT_EFFECT);
			pstmt2.setInt(1, empid);
			pstmt2.setInt(2, effectid);
			pstmt2.executeUpdate();
			
	
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null || con != null ||pstmt2 !=null) {
				try {
					pstmt.close();
					con.close();
					pstmt2.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		
	}

	@Override
	public void update(EmpVO empVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, empVO.getEmp_name());
			pstmt.setString(2, empVO.getAccount());
			pstmt.setString(3, empVO.getPassword());
			pstmt.setDate(4, empVO.getOnjob_date());
			pstmt.setInt(5, empVO.getEmp_status());
			pstmt.setInt(6, empVO.getEmp_id());

			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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

	@Override
	public void delete(Integer emp_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, emp_id);
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver." + e.getMessage());
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
	}

	
	
	
	
	//帳號驗證	
	public EmpVO findByAc(String account) {

		EmpVO empVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(GET_ONE_ACCOUNT);

			pstmt.setString(1, account);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				empVO = new EmpVO();
				empVO.setAccount(rs.getString("account"));
				empVO.setEmp_id(rs.getInt("emp_id"));
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
		
		return empVO;
	}
	
	
	
	
	@Override
	public EmpVO findBypk(Integer emp_id) {

		EmpVO empVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, emp_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				empVO = new EmpVO();
				empVO.setEmp_id(rs.getInt("emp_id"));
				empVO.setEmp_name(rs.getString("emp_name"));
				empVO.setAccount(rs.getString("account"));
				empVO.setPassword(rs.getString("password"));
				empVO.setOnjob_date(rs.getDate("onjob_date"));
				empVO.setEmp_status(rs.getInt("emp_status"));
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
		
		return empVO;
	}
	//登入

public List<EmpVO> login(String account , String password) {
	List<EmpVO> list = new ArrayList<EmpVO>();
	
	
	EmpVO empVO = null;
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,this.password);
			pstmt = con.prepareStatement(LOGIN);
			
			
			pstmt.setString(1, account);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			
		

				while (rs.next()) {
					empVO = new EmpVO();
					empVO.setEmp_id(rs.getInt("emp_id"));
					empVO.setEmp_name(rs.getString("emp_name"));
					empVO.setAccount(rs.getString("account"));
					empVO.setPassword(rs.getString("password"));
					empVO.setOnjob_date(rs.getDate("onjob_date"));
					empVO.setEmp_status(rs.getInt("emp_status"));
					empVO.setEffect_id(rs.getInt("effect_id"));
					empVO.setEffect(rs.getString("effect_name"));
					list.add(empVO);				
				}
	            

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null || con != null ||rs!= null) {
				try {
					pstmt.close();
					con.close();
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	@Override
	public List<EmpVO> getAll() {

		List<EmpVO> list = new ArrayList<EmpVO>();
		EmpVO empVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				empVO = new EmpVO();
				empVO.setEmp_id(rs.getInt("emp_id"));
				empVO.setEmp_name(rs.getString("emp_name"));
				empVO.setAccount(rs.getString("account"));
				empVO.setPassword(rs.getString("password"));
				empVO.setOnjob_date(rs.getDate("onjob_date"));
				empVO.setEmp_status(rs.getInt("emp_status"));
				list.add(empVO);
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	@Override
	public List<EmpVO> getAll(Map<String, String[]> map) {
		List<EmpVO> list = new ArrayList<EmpVO>();
		EmpVO empVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			String MySql = "select * from emp "
					+EmpNoEffect.getWhereCondition(map)
					+"order by emp_id";
			pstmt = con.prepareStatement(MySql);
			rs = pstmt.executeQuery();
		
			System.out.println(MySql);
			while (rs.next()) {
				empVO = new EmpVO();
				empVO.setEmp_id(rs.getInt("emp_id"));
				empVO.setEmp_name(rs.getString("emp_name"));
				empVO.setOnjob_date(rs.getDate("onjob_date"));
				empVO.setEmp_status(rs.getInt("emp_status"));
				list.add(empVO);
			}
//			Integer enpid = null;
//			for(EmpVO a :list) {
//				enpid = a.getEmp_id();
//				if(enpid==null) {
//					System.out.println("空資料");
//					
//				}
//			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	



	public static void main(String[] args) {

		EmpJDBCDAO dao = new EmpJDBCDAO();
	

		//登入
		
		
		Map<Integer, Integer> a= new HashMap<Integer, Integer>();
		
		
	
		List<EmpVO> list = dao.login("asdfgh", "asdfgh");
		for (EmpVO aEmp : list) {
//		System.out.println(aEmp.getAccount());
//		System.out.println(aEmp.getPassword());
//		System.out.println(aEmp.getEffect_id());
//		System.out.println(aEmp.getEffect());
			Emp_effectJDBCDAO dao2 = new Emp_effectJDBCDAO();
			List<Emp_effectVO> list2 = dao2.getAll();
			for(Emp_effectVO fa : list2) {
				System.out.print(fa.getEmp_id()+ ",");
				System.out.print(fa.getEffect_id() + ",");
				System.out.println();
			}
		
		a.put(aEmp.getEmp_id(),aEmp.getEffect_id());
		for(Integer key : a.keySet()) {
			System.out.println(key +"," + a.get(key) );
		}
		
		
		
		
		
		}
//		
//		// 新增
		EmpVO empVO1 = new EmpVO();
		empVO1.setEmp_name("呂華");
		empVO1.setAccount("b0099");
		empVO1.setPassword("b0099");
		empVO1.setOnjob_date(java.sql.Date.valueOf("2011-10-04"));
		empVO1.setEmp_status(1);
		dao.insert(empVO1);
//
//		// 修改
//		EmpVO empVO2 = new EmpVO();
//		empVO2.setEmp_id(1);
//		empVO2.setEmp_name("吳麗華");
//		empVO2.setAccount("c003");
//		empVO2.setPassword("c003");
//		empVO2.setOnjob_date(java.sql.Date.valueOf("2001-03-03"));
//		empVO2.setEmp_status(1);
//		dao.update(empVO2);
//
//		// 刪除
//		dao.delete(7);
//
//		// 查詢
//		EmpVO empVO3 = dao.findBypk(2);
//		System.out.print(empVO3.getEmp_id() + ",");
//		System.out.print(empVO3.getEmp_name() + ",");
//		System.out.print(empVO3.getAccount() + ",");
//		System.out.print(empVO3.getPassword() + ",");
//		System.out.print(empVO3.getOnjob_date() + ",");
//		System.out.println(empVO3.getEmp_status());
//		System.out.println("=====================");
//
//		// 查詢
//		List<EmpVO> list = dao.getAll();
//		for (EmpVO aEmp : list) {
//			System.out.print(aEmp.getEmp_id() + ",");
//			System.out.print(aEmp.getEmp_name() + ",");
//			System.out.print(aEmp.getAccount() + ",");
//			System.out.print(aEmp.getPassword() + ",");
//			System.out.print(aEmp.getOnjob_date() + ",");
//			System.out.print(aEmp.getEmp_status());
//			System.out.println();
//		
//		}		
	}
}
