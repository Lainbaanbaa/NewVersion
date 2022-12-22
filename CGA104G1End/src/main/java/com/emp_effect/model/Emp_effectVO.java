package com.emp_effect.model;

import java.io.Serializable;

public class Emp_effectVO implements Serializable{



	
	private static final long serialVersionUID = -7024329279688977502L;
	private Integer emp_id;
	private Integer effect_id;
	
	
	public com.emp.model.EmpVO getEmpVO() {
	    com.emp.model.EmpService empSvc = new com.emp.model.EmpService();
	    com.emp.model.EmpVO empVO = empSvc.getOneEmp(emp_id);
	    return empVO;
    }
	public com.effect.model.EffectVO getEffectVO() {
		com.effect.model.EffectService effectSvc = new com.effect.model.EffectService();
		com.effect.model.EffectVO effectVO = effectSvc.getOnEffect(effect_id);
	    return effectVO;
    }
	
	
	public Integer getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(Integer emp_id) {
		this.emp_id = emp_id;
	}
	public Integer getEffect_id() {
		return effect_id;
	}
	public void setEffect_id(Integer effect_id) {
		this.effect_id = effect_id;
	}
	
}
