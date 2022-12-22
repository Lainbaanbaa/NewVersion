package com.mem.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.emp.model.EmpNoEffect;
import com.emp.model.EmpVO;

public class MemJDBCDAO implements MemDAO_interface{
	
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/Ba_Rei?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";

	private static final String INSERT_STMT = 
		"INSERT INTO member (mem_account,mem_password,mem_name,mem_address,mem_phone,mem_uid,mem_email,mem_sex,mem_dob) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT mem_id,mem_account,mem_password,mem_name,mem_address,mem_phone,mem_uid,mem_email,mem_sex,mem_dob,mem_status FROM member order by mem_id";
	private static final String DELETE = 
		"DELETE FROM member where mem_id = ?";
	private static final String UPDATE = 
		"UPDATE member set mem_account=?,mem_password=?,mem_name=?,mem_address=?,mem_phone=?,mem_uid=?,mem_email=?,mem_sex=?,mem_dob=?,mem_status=? where mem_id = ?";
	private static final String UPDATEMEM = 
		"UPDATE member set mem_password=?,mem_name=?,mem_address=?,mem_phone=?,mem_uid=?,mem_email=?,mem_sex=?,mem_dob=? where mem_id = ?";
	private static final String LOGIN = 
		"SELECT * FROM member where mem_account = ? and mem_password = ?";
	private static final String ID_FIND_MEM = 
		"SELECT * FROM member where mem_id = ? ";
//	private static final String FIND_MEM_ID = 
//	"SELECT * FROM member where mem_id = (select mem_id from member where mem_account = ?)";
	private static final String FIND_PASSWORD = 
			"SELECT mem_password from member where mem_account=? and mem_email = ? ";
	private static final String FIND_ACCOUNT = 
		"SELECT mem_account from member where mem_email=? and mem_uid = ? ";
	private static final String CHECK_ACCOUNT = 
		"SELECT mem_account from member where mem_account = ?";
	private static final String UPDATESTATUS = 
		"UPDATE MEMBER set mem_status=2 where mem_id=?";
//		" UPDATE MEMBER set mem_status=2 where mem_id ORDER BY mem_id DESC limit 1";
	
	public void insert(MemVO memVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, memVO.getMem_account());
			pstmt.setString(2, memVO.getMem_password());
			pstmt.setString(3, memVO.getMem_name());
			pstmt.setString(4, memVO.getMem_address());
			pstmt.setString(5, memVO.getMem_phone());
			pstmt.setString(6, memVO.getMem_uid());
			pstmt.setString(7, memVO.getMem_email());
			pstmt.setString(8, memVO.getMem_sex());
			pstmt.setDate(9, memVO.getMem_dob());
			


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
	
	

	public void update(MemVO memVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, memVO.getMem_account());
			pstmt.setString(2, memVO.getMem_password());
			pstmt.setString(3, memVO.getMem_name());
			pstmt.setString(4, memVO.getMem_address());
			pstmt.setString(5, memVO.getMem_phone());
			pstmt.setString(6, memVO.getMem_uid());
			pstmt.setString(7, memVO.getMem_email());
			pstmt.setString(8, memVO.getMem_sex());
			pstmt.setDate(9, memVO.getMem_dob());
			pstmt.setInt(10, memVO.getMem_status());
			pstmt.setInt(11, memVO.getMem_id());

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
	
	public void updateMem(MemVO memVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATEMEM);

//			pstmt.setString(1, memVO.getMem_account());

			pstmt.setString(1, memVO.getMem_password());
			pstmt.setString(2, memVO.getMem_name());
			pstmt.setString(3, memVO.getMem_address());
			pstmt.setString(4, memVO.getMem_phone());
			pstmt.setString(5, memVO.getMem_uid());
			pstmt.setString(6, memVO.getMem_email());
			pstmt.setString(7, memVO.getMem_sex());
			pstmt.setDate(8, memVO.getMem_dob());
			pstmt.setInt(9, memVO.getMem_id());

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
	
	
	public void delete(Integer mem_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, mem_id);

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

	public MemVO findByPrimaryKey(Integer mem_id) {

		MemVO memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(ID_FIND_MEM);

			pstmt.setInt(1, mem_id);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				// empVo 也稱為 Domain objects
				memVO = new MemVO();
				memVO.setMem_id(rs.getInt("mem_id"));
				memVO.setMem_account(rs.getString("mem_account"));
				memVO.setMem_password(rs.getString("mem_password"));
				memVO.setMem_name(rs.getString("mem_name"));
				memVO.setMem_address(rs.getString("mem_address"));
				memVO.setMem_phone(rs.getString("mem_phone"));
				memVO.setMem_uid(rs.getString("mem_uid"));
				memVO.setMem_email(rs.getString("mem_email"));
				memVO.setMem_sex(rs.getString("mem_sex"));
				memVO.setMem_dob(rs.getDate("mem_dob"));
				memVO.setMem_status(rs.getInt("mem_status"));
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
		return memVO;
	}
	
	public MemVO findAccount(String mem_email,String mem_uid) {
		MemVO memVO = null;
		Connection con= null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
	try {
		
		Class.forName(driver);
		con = DriverManager.getConnection(url,userid,passwd);
		pstmt = con.prepareStatement(FIND_ACCOUNT);
		
		pstmt.setString(1, mem_email);
		pstmt.setString(2, mem_uid);
		
		rs=pstmt.executeQuery();
		
		while (rs.next()) {
			memVO = new MemVO();
			memVO.setMem_account(rs.getString("mem_account")); 
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
	return memVO;
}

	public MemVO findPassword(String mem_account,String mem_email) {
		
		MemVO memVO = null;
		Connection con= null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
	try {
		
		Class.forName(driver);
		con = DriverManager.getConnection(url,userid,passwd);
		pstmt = con.prepareStatement(FIND_PASSWORD);
		
		pstmt.setString(1, mem_account);
		pstmt.setString(2, mem_email);

		
		rs=pstmt.executeQuery();
		
		while (rs.next()) {
			memVO = new MemVO();
			memVO.setMem_password(rs.getString("mem_password")); 
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
	return memVO;
}

	public MemVO checkAccount(String mem_account) {
		MemVO memVO = null;
		Connection con= null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
	try {
		
		Class.forName(driver);
		con = DriverManager.getConnection(url,userid,passwd);
		pstmt = con.prepareStatement(CHECK_ACCOUNT);
		
		pstmt.setString(1, mem_account);

		
		rs=pstmt.executeQuery();
		
		while (rs.next()) {
			memVO = new MemVO();
			memVO.setMem_account(rs.getString("mem_account")); 
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
	return memVO;
}

	
	public MemVO findByMemId(Integer mem_id) {

		MemVO memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(ID_FIND_MEM);

			pstmt.setInt(1, mem_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				memVO = new MemVO();
				memVO.setMem_account(rs.getString("mem_account"));
				memVO.setMem_password(rs.getString("mem_password"));
				memVO.setMem_name(rs.getString("mem_name"));
				memVO.setMem_address(rs.getString("mem_address"));
				memVO.setMem_phone(rs.getString("mem_phone"));
				memVO.setMem_uid(rs.getString("mem_uid"));
				memVO.setMem_email(rs.getString("mem_email"));
				memVO.setMem_sex(rs.getString("mem_sex"));
				memVO.setMem_dob(rs.getDate("mem_dob"));
				memVO.setMem_status(rs.getInt("mem_status"));
				memVO.setMem_id(rs.getInt("mem_id"));
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
		return memVO;
	}
	

	public List<MemVO> getAll() {
		List<MemVO> list = new ArrayList<MemVO>();
		MemVO memVO = null;

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
				memVO = new MemVO();
				memVO.setMem_id(rs.getInt("mem_id"));
				memVO.setMem_account(rs.getString("mem_account"));
				memVO.setMem_password(rs.getString("mem_password"));
				memVO.setMem_name(rs.getString("mem_name"));
				memVO.setMem_address(rs.getString("mem_address"));
				memVO.setMem_phone(rs.getString("mem_phone"));
				memVO.setMem_uid(rs.getString("mem_uid"));
				memVO.setMem_email(rs.getString("mem_email"));
				memVO.setMem_sex(rs.getString("mem_sex"));
				memVO.setMem_dob(rs.getDate("mem_dob"));
				memVO.setMem_status(rs.getInt("mem_status"));
				list.add(memVO); // Store the row in the list
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
	

	public MemVO login(String mem_account,String mem_password) {

		MemVO memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try { 

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(LOGIN);

			pstmt.setString(1, mem_account);
			pstmt.setString(2, mem_password);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				memVO = new MemVO();

				memVO.setMem_account(rs.getString("mem_account"));
				memVO.setMem_password(rs.getString("mem_password"));
				memVO.setMem_name(rs.getString("mem_name"));
				memVO.setMem_address(rs.getString("mem_address"));
				memVO.setMem_phone(rs.getString("mem_phone"));
				memVO.setMem_uid(rs.getString("mem_uid"));
				memVO.setMem_email(rs.getString("mem_email"));
				memVO.setMem_sex(rs.getString("mem_sex"));
				memVO.setMem_dob(rs.getDate("mem_dob"));
				memVO.setMem_status(rs.getInt("mem_status"));
				memVO.setMem_id(rs.getInt("mem_id"));
				
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
		return memVO;
	}
	
	
	public void updateStatus(Integer mem_id) {


		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATESTATUS);

			pstmt.setInt(1, mem_id);

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
	
	
	public List<MemVO> getAllMem(Map<String, String[]> map) {
		List<MemVO> list = new ArrayList<MemVO>();
		MemVO memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			String MySql = "select * from member "
					+MemUtil.getWhereCondition(map)
					+"order by mem_id";
			pstmt = con.prepareStatement(MySql);
			rs = pstmt.executeQuery();
		
			System.out.println(MySql);
			while (rs.next()) {
				memVO = new MemVO();
				memVO.setMem_account(rs.getString("mem_account"));
				memVO.setMem_password(rs.getString("mem_password"));
				memVO.setMem_name(rs.getString("mem_name"));
				memVO.setMem_address(rs.getString("mem_address"));
				memVO.setMem_phone(rs.getString("mem_phone"));
				memVO.setMem_uid(rs.getString("mem_uid"));
				memVO.setMem_email(rs.getString("mem_email"));
				memVO.setMem_sex(rs.getString("mem_sex"));
				memVO.setMem_dob(rs.getDate("mem_dob"));
				memVO.setMem_status(rs.getInt("mem_status"));
				memVO.setMem_id(rs.getInt("mem_id"));
				list.add(memVO);
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

		MemJDBCDAO dao = new MemJDBCDAO();
		
//		 //新增
//		MemVO memVO1 = new MemVO();
//		memVO1.setMem_account("cvbn2234");
//		memVO1.setMem_password("12345678");
//		memVO1.setMem_name("Allen");
//		memVO1.setMem_address("桃園市楊梅區");
//		memVO1.setMem_phone("0984525345");
//		memVO1.setMem_uid("H123455389");
//		memVO1.setMem_email("xxx098767@gmail.com");
//		memVO1.setMem_sex("男");
//		memVO1.setMem_dob(java.sql.Date.valueOf("1996-1-21"));
//		
//		dao.insert(memVO1);
//
//		// 修改
//		MemVO memVO2 = new MemVO();
//
//		memVO2.setMem_account("adgj47640");
//		memVO2.setMem_password("111111");
//		memVO2.setMem_name("MMMMMMMM");
//		memVO2.setMem_address("桃園市中壢區");
//		memVO2.setMem_phone("0988525345");
//		memVO2.setMem_uid("H123456789");
//		memVO2.setMem_email("mmm046767@gmail.com");
//		memVO2.setMem_sex("男");
//		memVO2.setMem_dob(java.sql.Date.valueOf("1995-10-21"));
//		memVO2.setMem_status(2);	
//		memVO2.setMem_id(1);
//		dao.update(memVO2);
//
//		 修改會員
//		MemVO memVO2 = new MemVO();
//
//		memVO2.setMem_account("test01");
//		memVO2.setMem_password("111111");
//		memVO2.setMem_name("MMMMMMMM");
//		memVO2.setMem_address("桃園市中壢區");
//		memVO2.setMem_phone("0988525345");
//		memVO2.setMem_uid("H123456789");
//		memVO2.setMem_email("mmm046767@gmail.com");
//		memVO2.setMem_sex("男");
//		memVO2.setMem_dob(java.sql.Date.valueOf("1995-10-21"));
//		memVO2.setMem_status(2);	
//		memVO2.setMem_id(4);
//		dao.update(memVO2);
//		
//		// 刪除
//		dao.delete(7);
//
//		// 查詢
//		MemVO memVO3 = dao.findByPrimaryKey(1);
//		System.out.print(memVO3.getMem_id() + ",");
//		System.out.print(memVO3.getMem_account() + ",");
//		System.out.print(memVO3.getMem_password() + ",");
//		System.out.print(memVO3.getMem_name() + ",");
//		System.out.print(memVO3.getMem_address() + ",");
//		System.out.print(memVO3.getMem_phone() + ",");
//		System.out.print(memVO3.getMem_uid() + ",");
//		System.out.println(memVO3.getMem_email()+ ",");
//		System.out.print(memVO3.getMem_sex() + ",");
//		System.out.print(memVO3.getMem_dob() + ",");
//		System.out.println(memVO3.getMem_status());
//		System.out.println("---------------------");
//
//		
//		// 查詢
//		List<MemVO> list = dao.getAll();
//		for (MemVO aMem : list) {
//			System.out.print(aMem.getMem_id() + ",");
//			System.out.print(aMem.getMem_account() + ",");
//			System.out.print(aMem.getMem_password() + ",");
//			System.out.print(aMem.getMem_name() + ",");
//			System.out.print(aMem.getMem_address() + ",");
//			System.out.print(aMem.getMem_phone() + ",");
//			System.out.print(aMem.getMem_uid() + ",");
//			System.out.println(aMem.getMem_email()+ ",");
//			System.out.print(aMem.getMem_sex() + ",");
//			System.out.print(aMem.getMem_dob() + ",");
//			System.out.println(aMem.getMem_status() );
//			System.out.println();
//		}
//		
//		MemVO memVO4 = dao.findByMemUser("xyz","123456");
//
//		System.out.print(memVO4.getMem_account());
//		System.out.print(memVO4.getMem_password());
		
//		MemVO memVO5 = dao.findByMemId(3);
//		System.out.println(memVO5.getMem_id());
//		System.out.println(memVO5.getMem_name());
		
//		MemVO memVO6 = dao.findAccount("dragondazs17@gmail.com","A123456789");
//		System.out.println(memVO6.getMem_account());

//		
	}
	





}

