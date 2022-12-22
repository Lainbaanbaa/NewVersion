package com.emp_effect.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.emp.model.EmpVO;

public class Emp_effectJDBCDAO implements Emp_effectDAO_interface {
	
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/Ba_Rei?serverTimezone=Asia/Taipei";
	String userid = "root";
	String password = "password";

	private static final String INSER_STMT = "INSERT INTO emp_effect (emp_id, effect_id) VALUES (?, ?)";
	private static final String GET_ALL_STMT = "SELECT emp_id, effect_id FROM emp_effect order by emp_id";
	private static final String GET_ONE_STMT = "SELECT emp_id, effect_id FROM emp_effect where emp_id = ?";
	private static final String GET_ONE_EFFECT = "SELECT emp_id, effect_id FROM emp_effect where effect_id = ?";
	private static final String DELETE = "DELETE FROM emp_effect where emp_id = ? and effect_id = ?";
	private static final String UPDATE = "UPDATE emp_effect set effect_id=? where emp_id = ?";
	
	private static final String GET_ALL_EFFECT = "SELECT emp_id,effect_id FROM emp_effect where emp_id = ? and effect_id = ?";

	
	
	
	public Emp_effectVO getEffect(Integer emp_id , Integer effect_id) {
		
		Emp_effectVO emp_effectVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(GET_ALL_EFFECT);
			
			
			pstmt.setInt(1, emp_id);
			pstmt.setInt(2, effect_id);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {

				emp_effectVO = new Emp_effectVO();
				emp_effectVO.setEmp_id(rs.getInt("emp_id"));
				emp_effectVO.setEffect_id(rs.getInt("effect_id"));
				System.out.println(rs.getInt("emp_id"));
				System.out.println(rs.getInt("effect_id"));

			}
		
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
		} finally {
			if (pstmt != null || con != null || rs !=null) {
				try {
					
					pstmt.close();
					con.close();
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		return emp_effectVO;
	}
	
	

	@Override
	public void insert(Emp_effectVO emp_effectVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(INSER_STMT);

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
	public void update(Emp_effectVO emp_effectVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, emp_effectVO.getEffect_id());
			pstmt.setInt(2, emp_effectVO.getEmp_id());
			
			
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
	public void delete(Integer emp_id , Integer effect_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, emp_id);
			pstmt.setInt(2, effect_id);
			
			System.out.println("ID="+ emp_id);
			System.out.println(effect_id);
			
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver." + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
	}

	@Override
	public List<Emp_effectVO> findBypk(Integer emp_id) {
		
		List<Emp_effectVO> list = new ArrayList<Emp_effectVO>();
		Emp_effectVO emp_effectVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid , password);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1,emp_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				emp_effectVO = new Emp_effectVO();
				
				emp_effectVO.setEffect_id(rs.getInt("effect_id"));
				emp_effectVO.setEmp_id(rs.getInt("emp_id"));
				list.add(emp_effectVO);
				
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null || con!= null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
public List<Emp_effectVO> findByEffect(Integer effect_id) {
		
		List<Emp_effectVO> list = new ArrayList<Emp_effectVO>();
		Emp_effectVO emp_effectVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid , password);
			pstmt = con.prepareStatement(GET_ONE_EFFECT);
			pstmt.setInt(1,effect_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				emp_effectVO = new Emp_effectVO();
				
				emp_effectVO.setEffect_id(rs.getInt("effect_id"));
				emp_effectVO.setEmp_id(rs.getInt("emp_id"));
				list.add(emp_effectVO);
				
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null || con!= null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		return list;
	}


	@Override
	public List<Emp_effectVO> getAll() {
		List<Emp_effectVO> list = new ArrayList<Emp_effectVO>();
		Emp_effectVO emp_effectVO =null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				emp_effectVO = new Emp_effectVO();
				emp_effectVO.setEmp_id(rs.getInt("emp_id"));
				emp_effectVO.setEffect_id(rs.getInt("effect_id"));
				list.add(emp_effectVO);
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null||pstmt != null||con != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		return list;
		}
public static void main(String[] args) {
		
		Emp_effectJDBCDAO dao = new Emp_effectJDBCDAO();
//		// 新增
//		Emp_effectVO emp_EffectVO1 = new Emp_effectVO();
//		emp_EffectVO1.setEmp_id(1);
//		emp_EffectVO1.setEffect_id(1);
//		dao.insert(emp_EffectVO1);
		
		// 修改
//		Emp_effectVO emp_effectVO2 =new Emp_effectVO();
//		
//		emp_effectVO2.setEffect_id(2);
//		emp_effectVO2.setEmp_id(1);
//		emp_effectVO2.setEffect_id(3);
//		
//		
//		dao.update(emp_effectVO2);
		
		// 刪除
		dao.delete(1,3);
		
		// 查詢
//		List<Emp_effectVO>list2  =  dao.findBypk(1);
//		for(Emp_effectVO fa2 : list2) {
//			System.out.print(fa2.getEmp_id()+ ",");
//			System.out.print(fa2.getEffect_id() + ",");
//			System.out.println();
//		}
//		System.out.println("===========================");
//		
//		// 查詢
//		List<Emp_effectVO> list = dao.getAll();
//		for(Emp_effectVO fa : list) {
//			System.out.print(fa.getEmp_id()+ ",");
//			System.out.print(fa.getEffect_id() + ",");
//			System.out.println();
//		}
		
	}
}
