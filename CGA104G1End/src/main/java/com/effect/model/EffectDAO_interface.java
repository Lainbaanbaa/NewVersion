package com.effect.model;

import java.util.*;

public interface EffectDAO_interface {
	public void insert(EffectVO effectVO);

	public void update(EffectVO effectVO);

	public void delete(Integer effect_id);

	public EffectVO findByPK(Integer effect_id);

	public List<EffectVO> getAll();
}
