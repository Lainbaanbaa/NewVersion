package com.emp.model;

import java.util.*;

import com.emp_effect.model.Emp_effectVO;

public interface EmpDAO_interface {
	
	public Integer findmaxid();
	public void insert(EmpVO empVO);
	public void update(EmpVO empVO);
	public void delete(Integer emp_id);
	public EmpVO findBypk(Integer emp_id);
	public EmpVO findByAc(String account);
	public List<EmpVO> getAll(); 
	public List<EmpVO> login(String account, String password);
	public List<EmpVO> getAll(Map<String, String[]> map);
}
