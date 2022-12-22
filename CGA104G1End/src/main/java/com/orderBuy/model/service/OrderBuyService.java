package com.orderBuy.model.service;

import com.coupon.model.entity.Coupon;
import com.orderBuy.model.entity.OrderBuy;
import org.json.JSONArray;

import java.util.List;

public interface OrderBuyService {

    boolean newOrder(OrderBuy orderBuy);

    boolean removeOrder(Integer orderId);

    boolean updateOrder(OrderBuy orderBuy);

    OrderBuy getOrderById(Integer orderId);

    JSONArray getAllDetails(Integer memId);

    JSONArray getAll();

    List<OrderBuy> listAllOrder();

    String NewOrder(String url, Integer orderId, Integer memId, Double finalPrice, String receiverName, Integer couponId);

    Integer getNewsCount();

}
