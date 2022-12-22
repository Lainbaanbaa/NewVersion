package com.orderBuy.model.dao;

import com.commodityDetails.model.entity.CommodityDetails;
import com.orderBuy.model.entity.OrderBuy;
import core.dao.CoreDao;
import org.json.JSONArray;

import java.util.List;

public interface OrderBuyDAO extends CoreDao {

    int insert(OrderBuy orderBuy);

    int deleteById(Integer orderId);

    int updateById(OrderBuy orderBuy);

    OrderBuy selectById(Integer orderId);

    JSONArray listById(Integer memId);

    JSONArray listAll();

    List<OrderBuy> selectAll();

}
