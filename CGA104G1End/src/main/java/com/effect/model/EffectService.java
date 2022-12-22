package com.effect.model;

import java.util.List;

public class EffectService {

	
	private EffectDAO_interface dao;
	
	
	public EffectService() {
		dao = new EffectJDBCDAO();
	}
	
	
	public EffectVO addEffect(String effect_name, String effect_info) {
		
		EffectVO effectVO = new EffectVO();
		
		effectVO.setEffect_name(effect_name);
		effectVO.setEffect_info(effect_info);
		dao.insert(effectVO);
		return effectVO;
	}
	
	public EffectVO updateEffect(Integer effect_id, String effect_name, String effect_info) {
		
		EffectVO effectVO = new EffectVO();
		effectVO.setEffect_id(effect_id);
		effectVO.setEffect_name(effect_name);
		effectVO.setEffect_info(effect_info);
		
		dao.update(effectVO);
		return effectVO;
	}

	public void deleteEffect(Integer effect_id) {
		dao.delete(effect_id);
	}

	public EffectVO getOnEffect(Integer effect_id) {
		return dao.findByPK(effect_id);
	}

	public List<EffectVO> getAll(){
		return dao.getAll();
	}
}
