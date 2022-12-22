package com.commodityDetails.model.service.impl;

import com.commodityDetails.model.dao.CommodityDetailsDAO;
import com.commodityDetails.model.dao.impl.CommodityDetailsDAOImpl;
import com.commodityDetails.model.entity.CommodityDetails;
import com.commodityDetails.model.service.CommodityDetailsService;

import java.util.List;

public class CommodityDetailsServiceImpl implements CommodityDetailsService {

    private CommodityDetailsDAO dao;

    public CommodityDetailsServiceImpl() {
        dao = new CommodityDetailsDAOImpl();
    }

    @Override
    public boolean addDetails(CommodityDetails commodityDetails) {
        return dao.insert(commodityDetails) > 0;
    }

    @Override
    public boolean updateDetails(CommodityDetails commodityDetails) {
        return dao.updateById(commodityDetails) > 0;
    }

    @Override
    public List<CommodityDetails> listOrderDetails(Integer orderId) {
        return dao.getDetail(orderId);
    }

    @Override
    public List<CommodityDetails> listAllDetails() {
       return dao.selectAll();
    }

    @Override
    public Integer getNewsCount() {
        return null;
    }
}
