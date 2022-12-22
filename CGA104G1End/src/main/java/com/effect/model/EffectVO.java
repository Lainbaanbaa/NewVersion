package com.effect.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class EffectVO implements Serializable {

	private Integer effect_id;
	private String effect_name;
	private String effect_info;
	public Integer getEffect_id() {
		return effect_id;
	}
	public void setEffect_id(Integer effect_id) {
		this.effect_id = effect_id;
	}
	public String getEffect_name() {
		return effect_name;
	}
	public void setEffect_name(String effect_name) {
		this.effect_name = effect_name;
	}
	public String getEffect_info() {
		return effect_info;
	}
	public void setEffect_info(String effect_info) {
		this.effect_info = effect_info;
	}

	
}
