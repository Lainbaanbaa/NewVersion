package com.mem.model;
import java.util.*;

public interface MemDAO_interface {

	
	          public void insert(MemVO memVO);
	          public void update(MemVO memVO);
	          public void updateMem(MemVO memVO);
	          public void delete(Integer mem_id);
	          public MemVO findByPrimaryKey(Integer mem_id);
	          public MemVO findByMemId(Integer mem_id);
	          public MemVO login(String mem_account,String mem_password);
	          public List<MemVO> getAll();
	          public void updateStatus(Integer mem_id) ;
	          public MemVO findAccount(String mem_email ,String mem_uid );
	          public MemVO checkAccount(String mem_account);
	          public MemVO findPassword(String mem_account,String mem_email);
	      	  public List<MemVO> getAllMem(Map<String, String[]> map);
	          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//	        public List<EmpVO> getAll(Map<String, String[]> map); 
	

}
