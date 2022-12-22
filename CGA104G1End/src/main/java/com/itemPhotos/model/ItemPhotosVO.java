package com.itemPhotos.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.item.model.ItemVO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ITEM_PHOTOS" ,catalog = "ba_rei")
public class ItemPhotosVO implements java.io.Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IP_ID")
	private Integer ipId;
	@Column(name = "ITEM_ID")
	private Integer itemId;
	@Column(name = "IP_PHOTO")
	private byte[] ipPhoto;

	@ManyToOne
	@JoinColumn(name="ITEM_ID",insertable = false,updatable = false)
	private ItemVO itemVO;

}
