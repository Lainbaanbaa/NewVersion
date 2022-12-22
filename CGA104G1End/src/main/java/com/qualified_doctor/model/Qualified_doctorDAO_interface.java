package com.qualified_doctor.model;

import java.util.List;


public interface Qualified_doctorDAO_interface {
	
    public void insert(Qualified_doctorVO qualified_doctorVO);
    public void update(Qualified_doctorVO qualified_doctorVO);
    public void delete(Integer doc_id);
    public Qualified_doctorVO findByPrimaryKey(Integer doc_id);
    public List<Qualified_doctorVO> getAll();
	public Qualified_doctorVO memidChk(Integer mem_id);
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 



}
