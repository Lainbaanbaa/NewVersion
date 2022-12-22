package com.emp_effect.model;

import java.util.List;

public class Emp_effectService {
	
	private Emp_effectJDBCDAO dao;
	
	public Emp_effectService() {
		dao = new Emp_effectJDBCDAO();
	}
	public Emp_effectVO addEmp(Integer emp_id, Integer effect_id) {

		Emp_effectVO emp_effectVO =new Emp_effectVO();

		emp_effectVO.setEmp_id(emp_id);;
		emp_effectVO.setEffect_id(effect_id);;
	
		dao.insert(emp_effectVO);

		return emp_effectVO;
	}

	public Emp_effectVO updateEmp_effect(Integer emp_id, Integer effect_id) {

		Emp_effectVO emp_effectVO = new Emp_effectVO();

		emp_effectVO.setEmp_id(emp_id);
		emp_effectVO.setEffect_id(effect_id);
	
		dao.update(emp_effectVO);

		return emp_effectVO;
	}

	public void deleteEmp(Integer emp_id,Integer effect_id) {
		dao.delete(emp_id,effect_id);
	}
	
	public Emp_effectVO getEffect(Integer emp_id,Integer effect_id) {
		return dao.getEffect(emp_id,effect_id);
	}

	public List <Emp_effectVO> getOneEmp(Integer emp_id) {
		return dao.findBypk(emp_id);
	}
	
	public List <Emp_effectVO> getOneEffect(Integer effect_id) {
		return dao.findByEffect(effect_id);
	}

	public List<Emp_effectVO> getAll() {
		return dao.getAll();
	}
}