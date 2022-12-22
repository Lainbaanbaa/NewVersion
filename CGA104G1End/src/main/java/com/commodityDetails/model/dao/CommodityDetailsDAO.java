package com.commodityDetails.model.dao;

import com.commodityDetails.model.entity.CommodityDetails;
import com.coupon.model.entity.Coupon;
import core.dao.CoreDao;

import java.util.List;

public interface CommodityDetailsDAO extends CoreDao {

    int insert(CommodityDetails commodityDetails);

    int deleteById(Integer orderId);

    int updateById(CommodityDetails commodityDetails);

    List<CommodityDetails> getDetail(Integer orderId);

    List<CommodityDetails> selectAll();

}
