package com.group_join.model;

public class Group_JoinVO implements java.io.Serializable{
	
	private static final long serialVersionUID = 1180089583423735176L;
	private Integer gb_id;
	private Integer mem_id;
	private Integer gbpay_status;
	private Integer pickup_status;
	private Integer deliver_status;
	private Integer gbbuy_amount;
	private Integer gbitem_id;
	private Integer gbbuy_price;
	private String gb_name;
	

	
	public com.group_buy_item.model.Group_Buy_ItemVO getOneGbi() {
	    com.group_buy_item.model.Group_Buy_ItemService group_buy_itemSvc = new com.group_buy_item.model.Group_Buy_ItemService();
	    com.group_buy_item.model.Group_Buy_ItemVO group_buy_itemVO = group_buy_itemSvc.getOneGbi(gbitem_id);
	    return group_buy_itemVO;
    }
	
	 public com.mem.model.MemVO getMemVO() {
		 com.mem.model.MemService memService = new com.mem.model.MemService();
		 com.mem.model.MemVO memVO = memService.getOneMem(mem_id);
		    return memVO;
	  }
	
	 
	 public com.group_buy.model.Group_BuyVO getGroup_BuyVO() {
		 com.group_buy.model.Group_BuyService group_buyService = new com.group_buy.model.Group_BuyService();
		 com.group_buy.model.Group_BuyVO group_buyVO = group_buyService.getOneGroup_Buy(gb_id);
		    return group_buyVO;
	  }
	 
	public Integer getGbbuy_price() {
		return gbbuy_price;
	}

	public String getGb_name() {
		return gb_name;
	}

	public void setGb_name(String gb_name) {
		this.gb_name = gb_name;
	}

	public void setGbbuy_price(Integer gbbuy_price) {
		this.gbbuy_price = gbbuy_price;
	}

	public Integer getGbitem_id() {
		return gbitem_id;
	}

	public void setGbitem_id(Integer gbitem_id) {
		this.gbitem_id = gbitem_id;
	}
	
	public Integer getGbbuy_amount() {
		return gbbuy_amount;
	}
	public void setGbbuy_amount(Integer gbbuy_amount) {
		this.gbbuy_amount = gbbuy_amount;
	}
	public Integer getGb_id() {
		return gb_id;
	}
	public void setGb_id(Integer gb_id) {
		this.gb_id = gb_id;
	}
	public Integer getMem_id() {
		return mem_id;
	}
	public void setMem_id(Integer mem_id) {
		this.mem_id = mem_id;
	}
	public Integer getGbpay_status() {
		return gbpay_status;
	}
	public void setGbpay_status(Integer gbpay_status) {
		this.gbpay_status = gbpay_status;
	}
	public Integer getPickup_status() {
		return pickup_status;
	}
	public void setPickup_status(Integer pickup_status) {
		this.pickup_status = pickup_status;
	}
	public Integer getDeliver_status() {
		return deliver_status;
	}
	public void setDeliver_status(Integer deliver_status) {
		this.deliver_status = deliver_status;
	}
	
	
	

}
