package com.orderBuy.model.service.impl;

import com.orderBuy.model.dao.impl.OrderBuyDAOImpl;
import com.orderBuy.model.dao.OrderBuyDAO;
import com.orderBuy.model.entity.OrderBuy;
import com.orderBuy.model.service.OrderBuyService;
import core.util.UUIDGenerator;
import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutALL;
import org.json.JSONArray;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class OrderBuyServiceImpl implements OrderBuyService {

    private OrderBuyDAO dao;

    public OrderBuyServiceImpl() {
        dao = new OrderBuyDAOImpl();
    }

    @Override
    public boolean newOrder(OrderBuy orderBuy) {
        return dao.insert(orderBuy) > 0;
    }

    @Override
    public boolean removeOrder(Integer orderId) {
        return dao.deleteById(orderId) > 0;
    }

    @Override
    public boolean updateOrder(OrderBuy orderBuy) {
        return dao.updateById(orderBuy) > 0;
    }

    @Override
    public OrderBuy getOrderById(Integer orderId) {
        return dao.selectById(orderId);
    }

    @Override
    public JSONArray getAllDetails(Integer memId) {
        return dao.listById(memId);
    }

    @Override
    public JSONArray getAll() {
        return dao.listAll();
    }

    @Override
    public List<OrderBuy> listAllOrder() {
        return dao.selectAll();
    }

    @Override
    public String NewOrder(String url, Integer orderId, Integer memId, Double finalPrice, String receiverName, Integer couponId) {

        Date date = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String orderDate = sd.format(date);
        String indexUrl = url + "/frontend/index.html";
        String returnUrl = url + "/EcpayReturn";

        AllInOne allInOne = new AllInOne("");
        AioCheckOutALL obj = new AioCheckOutALL();
        obj.setMerchantTradeNo(String.valueOf(orderId) + "br" + UUIDGenerator.getUUID()); // 訂單id+br+亂碼16位
        obj.setMerchantTradeDate(orderDate); // 交易時間
        obj.setTotalAmount(String.valueOf(finalPrice.intValue())); // 訂單總金額
        obj.setTradeDesc("A test order."); // 訂單描述
        obj.setItemName(receiverName + " 的 Ba-Rei 商品訂單，訂單編號: " + orderId); // 商品項目
        obj.setReturnURL(indexUrl);
        obj.setClientBackURL(indexUrl);
        obj.setOrderResultURL(returnUrl);
        obj.setCustomField1(couponId.toString());
        obj.setNeedExtraPaidInfo("N");

        return allInOne.aioCheckOut(obj, null);
    }

    @Override
    public Integer getNewsCount() {
        return null;
    }
}
