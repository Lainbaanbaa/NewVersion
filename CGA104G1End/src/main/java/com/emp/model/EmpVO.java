package com.emp.model;

import java.io.Serializable;
import java.sql.Date;

public class EmpVO implements Serializable {

	
	private static final long serialVersionUID = 510581347092152828L;
	private Integer emp_id;
	private String emp_name;
	private String account;
	private String password;
	private Date onjob_date;
	private Integer emp_status;
	
	private Integer effect_id;
	
	private String effect;

	public Integer getEmp_id() {
		return emp_id;
	}

	public Integer getEffect_id() {
		return effect_id;
	}

	public void setEffect_id(Integer effect_id) {
		this.effect_id = effect_id;
	}

	public String getEffect() {
		return effect;
	}

	public void setEffect(String effect) {
		this.effect = effect;
	}

	public void setEmp_id(Integer emp_id) {
		this.emp_id = emp_id;
	}

	public String getEmp_name() {
		return emp_name;
	}

	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getOnjob_date() {
		return onjob_date;
	}

	public void setOnjob_date(Date onjob_date) {
		this.onjob_date = onjob_date;
	}

	public Integer getEmp_status() {
		return emp_status;
	}

	public void setEmp_status(Integer emp_status) {
		this.emp_status = emp_status;
	}
	
	
	
	
	
//	 public com.emp_effect.model.Emp_effectVO getEmp_effectVO() {
//		 com.emp_effect.model.Emp_effectService emp_effecctSvc = new com.emp_effect.model.Emp_effectService();
//		 com.emp_effect.model.Emp_effectVO emp_effectVO = emp_effecctSvc.getOneEmp(emp_id);
//		    return emp_effectVO;
//	    }
//	 public com.effect.model.EffectVO getEffectVO() {
//		 com.effect.model.EffectService emp_effecctSvc = new com.effect.model.EffectService();
//		 com.effect.model.EffectVO effectVO = emp_effecctSvc.getOnEffectVO(effect_id);
//		    return effectVO;
//	    }
	
}
