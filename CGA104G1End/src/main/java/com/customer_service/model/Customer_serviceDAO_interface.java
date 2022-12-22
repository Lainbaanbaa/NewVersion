package com.customer_service.model;

import java.util.List;


public interface Customer_serviceDAO_interface {
	
	public void insert(Customer_serviceVO customer_serviceVO);
    public void update(Customer_serviceVO customer_serviceVO);
    public void delete(Integer cs_id);
    public Customer_serviceVO findByPrimaryKey(Integer cs_id);
    public List<Customer_serviceVO> getAll();


}
