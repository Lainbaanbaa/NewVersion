package com.commodityDetails.model.service;

import com.commodityDetails.model.entity.CommodityDetails;

import java.util.List;

public interface CommodityDetailsService {

    boolean addDetails(CommodityDetails commodityDetails);

    boolean updateDetails(CommodityDetails commodityDetails);

    List<CommodityDetails> listOrderDetails(Integer orderId);

    List<CommodityDetails> listAllDetails();

    Integer getNewsCount();

}
