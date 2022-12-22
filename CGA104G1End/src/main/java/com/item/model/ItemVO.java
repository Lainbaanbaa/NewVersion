package com.item.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.*;

import com.commodityDetails.model.entity.CommodityDetails;
import com.itemPhotos.model.ItemPhotosVO;
import com.itemType.model.ItemTypeVO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ITEM", catalog = "ba_rei")
public class ItemVO implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ITEM_ID")
	private Integer itemId;
	@Column(name = "ITEMT_ID")
	private Integer itemtId;
	@Column(name = "ITEM_NAME")
	private String itemName;
	@Column(name = "ITEM_CONTENT")
	private String itemContent;
	@Column(name = "ITEM_PRICE")
	private Integer itemPrice;
	@Column(name = "ITEM_AMOUNT")
	private Integer itemAmount;
	@Column(name = "ITEM_STATUS")
	private Integer itemStatus;
	@Column(name = "ITEM_DATE")
	private Date itemDate;
	@Column(name = "ITEM_ENDDATE")
	private Date itemEnddate;
	@Transient
	private  String photo;
	@OneToMany(mappedBy = "itemVO")
//	@JoinColumn(name="ITEM_ID",referencedColumnName = "ITEM_ID")
	private List<ItemPhotosVO> photos;

	@OneToOne
	@JoinColumn(name = "ITEMT_ID",insertable = false,updatable = false)
	private ItemTypeVO itemTypeVO;

	@OneToMany(mappedBy = "item")
	private  List<CommodityDetails> commodityDetail;

//	public static void main(String[] args) {
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();// 拿連線
//		session.beginTransaction();//開始交易
//		ItemVO itemVO = session.get(ItemVO.class, 20);
//		System.out.println(itemVO.getItemName());
//		session.getTransaction().commit();
//
//	}


}
