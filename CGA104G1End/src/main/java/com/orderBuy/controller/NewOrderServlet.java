package com.orderBuy.controller;

import com.commodityDetails.model.entity.CommodityDetails;
import com.commodityDetails.model.service.CommodityDetailsService;
import com.commodityDetails.model.service.impl.CommodityDetailsServiceImpl;
import com.coupon.model.entity.Coupon;
import com.coupon.model.service.CouponService;
import com.coupon.model.service.impl.CouponServiceImpl;
import com.item.model.ItemVO;
import com.memberCoupon.model.service.impl.MemberCouponServiceImpl;
import com.memberCoupon.model.service.MemberCouponService;
import com.orderBuy.model.entity.OrderBuy;
import com.orderBuy.model.service.impl.OrderBuyServiceImpl;
import com.orderBuy.model.service.OrderBuyService;
import com.item.model.ItemService;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.*;

import static java.lang.System.out;

@WebServlet(name = "NewOrderServlet", value = "/NewOrderServlet")
public class NewOrderServlet extends HttpServlet {


    private OrderBuyService service;

    @Override
    public void init() throws ServletException {
        service = new OrderBuyServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        res.setCharacterEncoding("UTF-8");

        /* 允許跨域主機地址 */
        res.setHeader("Access-Control-Allow-Origin", "*");
        /* 允許跨域 GET, POST, HEAD 等 */
        res.setHeader("Access-Control-Allow-Methods", "*");
        /* 重新預檢驗跨域的緩存時間 (s) */
        res.setHeader("Access-Control-Max-Age", "3600");
        /* 允許跨域的請求頭 */
        res.setHeader("Access-Control-Allow-Headers", "*");
        /* 是否攜帶 cookie */
        res.setHeader("Access-Control-Allow-Credentials", "true");


        PrintWriter pw = res.getWriter();
        JSONObject jsonMsg = new JSONObject();

        HttpSession session = req.getSession();
        final Integer memberId = (Integer) session.getAttribute("mem_id");

        final Integer couponId = Integer.valueOf(req.getParameter("couponId"));

        final Byte orderPaying = Byte.valueOf(req.getParameter("orderPaying"));

        final Byte orderSend = Byte.valueOf(req.getParameter("orderSend"));

        final String receiverName = req.getParameter("receiverName");
        final String nameReg = "^[\\u4e00-\\u9fa5]+$|^[a-zA-Z\\s]+$";
        if (receiverName == null || receiverName.trim().length() == 0) {
            jsonMsg.put("receiverNameErr", "* 收件人姓名不可空白");
        } else if (!receiverName.trim().matches(nameReg)) {
            jsonMsg.put("receiverNameErr", "* 收件人姓名包含不合法字元");
        }

        final String city = req.getParameter("city");
        if (city == null || city.trim().length() == 0) {
            jsonMsg.put("cityErr", "* 縣市為必填項目");
        }
        final String dist = req.getParameter("dist");
        if (dist == null || dist.trim().length() == 0) {
            jsonMsg.put("distErr", "* 鄉鎮市區為必填項目");
        }
        final String address = req.getParameter("receiverAddress");
        if (address == null || address.trim().length() == 0) {
            jsonMsg.put("addressErr", "* 地址為必填項目");
        }

        String receiverAddress = city + ", " + dist + ", " + address;

        final String receiverPhone = req.getParameter("receiverPhone");
        String phoneReg = "^[0][9]\\d{8}$";
        if (receiverPhone == null || receiverPhone.trim().length() == 0) {
            jsonMsg.put("receiverPhoneErr", "* 手機號碼不可空白");
        } else if (!receiverPhone.trim().matches(phoneReg)) {
            jsonMsg.put("receiverPhoneErr", "* 手機號碼格式異常");
        }

        if (!jsonMsg.isEmpty()) {
            pw.println(jsonMsg);
            return;
        }

        JSONArray jsonArr;
        final String dataArr = req.getParameter("dataArr");

        Map<Integer, Integer> items = new HashMap<Integer, Integer>();
        Map<Integer, Integer> orderItems = new HashMap<Integer, Integer>();
        Map<Integer, Integer> itemPrices = new HashMap<Integer, Integer>();
        Map<Integer, String> itemNames = new HashMap<Integer, String>();

        ItemService itemSvc = new ItemService();

        Double originalPrice = 0.0;
        Double discountPrice;
        Double finalPrice = 0.0;


        if (couponId != 0) {
            CouponService couponSvc = new CouponServiceImpl();
            Coupon coupon = couponSvc.getCouponById(couponId);
            discountPrice = coupon.getCouponVal();
        } else {
            discountPrice = 0.0;
        }


        try {
            jsonArr = new JSONArray(dataArr);
            for (int index = 0; index < jsonArr.length(); index++) {

                JSONObject tmp = (JSONObject) jsonArr.get(index);

                Integer itemId = tmp.getInt("itemId");

                Integer cdAmount = tmp.getInt("quantity");
                ItemVO item = itemSvc.getItem(itemId);

                Integer itemPrice = item.getItemPrice();
                String itemName = item.getItemName();

                items.put(itemId, itemId);
                orderItems.put(itemId, cdAmount);
                itemPrices.put(itemId, itemPrice);
                itemNames.put(itemId, itemName);

                /**
                 * 計算訂單金額
                 * @param itemId: 商品編號
                 * @param itemPrice: 商品價格
                 * @param cdAmount: 商品數量
                 * @param originalPrice: 原始總價
                 * @param discountPrice: 折扣價格
                 * @param finalPrice: 最終價格
                 */
                originalPrice += itemPrice * cdAmount;
            }

        } catch (Exception e) {
            out.println("ERROR: " + e.getMessage());
        }
        finalPrice = originalPrice - discountPrice;

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Date date = new Date();
        String orderOther = "訂單成立時間: " + date
                + "\n" + "訂單收件人姓名: " + receiverName + "收件人電話: " + receiverPhone
                + "\n" + "收件地址: " + receiverAddress;

        // 寫入資料庫

        OrderBuy orderBuy = new OrderBuy();
        orderBuy.setMemId(memberId);
        orderBuy.setOriginalPrice(originalPrice);
        orderBuy.setDiscountPrice(discountPrice);
        orderBuy.setFinalPrice(finalPrice);
        orderBuy.setOrderDate(timestamp);
        orderBuy.setOrderPaying(orderPaying);
        orderBuy.setOrderOther(orderOther);
        orderBuy.setOrderSend(orderSend);
        orderBuy.setOrderStatus((byte) 0);
        orderBuy.setReceiverName(receiverName);
        orderBuy.setReceiverAddress(receiverAddress);
        orderBuy.setReceiverPhone(receiverPhone);

        // 新增商品訂單
        OrderBuyService orderBuySvc = new OrderBuyServiceImpl();
        boolean b = orderBuySvc.newOrder(orderBuy);

        Integer orderId = orderBuy.getOrderId();

        if (couponId != 0) {
            MemberCouponService memberCouponSvc = new MemberCouponServiceImpl();
            // 將優惠券切換為 1: 已使用
            memberCouponSvc.updateCouponStatus(memberId, couponId, (byte) 1);
        }

        Integer itemId;
        String itemName;
        Integer itemPrice;
        Integer cdAmount;

        CommodityDetails commodityDetails = new CommodityDetails();

        for (Integer integer : itemNames.keySet()) {

            itemId = items.get(integer);
            itemName = itemNames.get(integer);
            itemPrice = itemPrices.get(integer);
            cdAmount = orderItems.get(integer);

            commodityDetails.setOrderId(orderId);
            commodityDetails.setItemId(itemId);
            commodityDetails.setItemName(itemName);
            commodityDetails.setCdAmount(cdAmount);
            commodityDetails.setItemPrice(Double.valueOf(itemPrice));

            CommodityDetailsService commodityDetailsSvc = new CommodityDetailsServiceImpl();
            commodityDetailsSvc.addDetails(commodityDetails);
        }

        // 資料轉交綠界

        StringBuffer sb = new StringBuffer();
        String url = sb.append(req.getScheme()).append("://")
                .append(req.getServerName()).append(":")
                .append(req.getServerPort())
                .append(req.getContextPath()).toString();

        if (b) {

            JSONArray newOrder = new JSONArray();

            String result = orderBuySvc.NewOrder(url, orderId, memberId, finalPrice, receiverName, couponId);
            if (result == null) {
                jsonMsg.put("payErr", "綠界發生錯誤，請重稍後再試");
                return;
            }
            newOrder.put(result);
            pw.print(newOrder);

        } else {

            jsonMsg.put("payErr", "系統繁忙中，請重新確認");
            pw.print(jsonMsg);
            return;
        }
    }
}

