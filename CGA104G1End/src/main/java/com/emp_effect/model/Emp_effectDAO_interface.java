package com.emp_effect.model;

import java.util.*;



public interface Emp_effectDAO_interface {

	
	public void insert(Emp_effectVO emp_effectVO);
	public void update(Emp_effectVO emp_effectVO);
	public void delete(Integer emp_id ,Integer effect_id);
	public Emp_effectVO getEffect(Integer emp_id ,Integer effect_id);
	public List<Emp_effectVO> findBypk(Integer emp_id);
	public List<Emp_effectVO> findByEffect(Integer emp_id);
	public List<Emp_effectVO> getAll();
	
}
