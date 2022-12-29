package com.group_buy_order.controller;


import core.util.MailServiceForOrder;

import javax.persistence.criteria.Order;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group_buy_order.model.Group_Buy_OrderService;
import com.group_buy_order.model.Group_Buy_OrderVO;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

@WebServlet(name = "EcpayReturnGB", value = "/EcpayReturnGB")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class EcpayReturnGB extends HttpServlet {
//	RtnCode 交易狀態 Int 1:成功，其餘為失敗
//	RtnMsg 交易訊息 String (200) 交易訊息 停用成功
//	MerchantID 特店編號 String(10) 2000132
//	MerchantTradeNo 特店交易編號 String(20)
//	訂單產生時傳送給綠界的特店交易編號。123456abc
//	CheckMacValue 檢查碼 String
//	特店必須檢查 CheckMacValue 來

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        res.setHeader("Access-Control-Allow-Origin", "*");
        res.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        res.setHeader("Access-Control-Max-Age", "300");
        res.setHeader("Access-Control-Allow-Headers", "content-type, x-requested-with");
        res.setHeader("Access-Control-Allow-Credentials", "true");
        res.setContentType("text/html;charset=UTF-8");

        PrintWriter out = res.getWriter();
        System.out.println(out+"在51行");
        String RtnCode = req.getParameter("TradeStatus");//付款狀態
        System.out.println(RtnCode);
        String MerchantTradeNo = req.getParameter("MerchantTradeNo");//訂單編號(綠界查詢用)
        String PaymentDate = req.getParameter("PaymentDate"); //付款日期
        String PaymentType = req.getParameter("PaymentType");

        String orderId = null;
        int GBORDER_PAYING;

        if ("Credit_CreditCard".equals(PaymentType)) {
        	GBORDER_PAYING = 2; // 使用"信用卡"付款
        } else {
        	GBORDER_PAYING = 1; // 使用"ATM"付款
        }

        //取出訂單id
        if (MerchantTradeNo != null && MerchantTradeNo.length() != 0) {
            StringTokenizer tk = new StringTokenizer(MerchantTradeNo, "br");
            if (tk.hasMoreElements()) {
                orderId = tk.nextToken();
            }
        }

        Group_Buy_OrderService group_Buy_OrderService = new Group_Buy_OrderService();
        assert orderId != null;
        Group_Buy_OrderVO group_buy_orderVO = group_Buy_OrderService.getOneEmp(Integer.valueOf(orderId));


        Integer memId = group_buy_orderVO.getMem_id();
        Integer Price = group_buy_orderVO.getGb_endprice();
//        String orderOther = group_buy_orderVO.getOrderOther();
//        String receiverName = group_buy_orderVO.getReceiverName();


        if ("1".equals(RtnCode)) {

//            Date date = new Date();
//            String newOrderOther = orderOther
//                    + "\n" + "----- " + date + " -----"
//                    + "\n" + "付款成功，準備出貨"
//                    + "\n" + "綠界訂單編號: " + MerchantTradeNo;
//
//            orderBuy.setOrderStatus((byte) 2);
//            orderBuy.setOrderPaying(orderPaying);
//            orderBuy.setOrderOther(newOrderOther);
//            orderBuyService.updateOrder(orderBuy);
//
//            MemService memService = new MemService();
//            // 寄email訂單成立
//            MailServiceForOrder mailService = new MailServiceForOrder();
//            String memEmail = memService.getOneMem(memberId).getMem_email();
//
//            mailService.sendMail(memEmail, "【訂單付款成功通知】 您在 Ba-rei 的訂單已付款成功", PaymentDate, receiverName, String.valueOf(memberId), orderId, String.valueOf(finalPrice));
//

            ServletContext sc = this.getServletContext();
            String contextPath = sc.getContextPath();
            String url = contextPath + "/frontend/group_buy_order/listOneGroup_Buy_Order_byMaster.jsp";
            res.sendRedirect(url);

        } else {

//
//            MemberCouponService memberCouponSvc = new MemberCouponServiceImpl();
//            // 將優惠券切換為 0: 未使用
//            memberCouponSvc.updateCouponStatus(memberId, Integer.valueOf(couponId), (byte) 0);
//
//            Date date = new Date();
//            String newOrderOther = orderOther
//                    + "\n" + "----- " + date + " -----"
//                    + "\n" + "付款失敗，訂單取消";
//
//
//            orderBuy.setOrderStatus((byte) 9);
//            orderBuy.setOrderPaying(orderPaying);
//            orderBuy.setOrderOther(newOrderOther);
//
//            orderBuyService.updateOrder(orderBuy);
//
//            MemService memService = new MemService();
//            // 寄email訂單失敗通知
//            MailServiceForOrder mailService = new MailServiceForOrder();
//            String memEmail = memService.getOneMem(memberId).getMem_email();
//
//            mailService.sendMail(memEmail, "【訂單付款失敗通知】 您在 Ba-rei 的訂單因為未付款而失效", " -- ", receiverName, String.valueOf(memberId), orderId, String.valueOf(finalPrice));

            ServletContext sc = this.getServletContext();
            String contextPath = sc.getContextPath();
            String url = contextPath + "/frontend/group_buy_order/listOneGroup_Buy_Order_byMaster.jsp";
            res.sendRedirect(url);

        }
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req, res);
    }

}

