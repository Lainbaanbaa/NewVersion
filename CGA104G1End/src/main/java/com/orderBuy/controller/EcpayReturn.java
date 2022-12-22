package com.orderBuy.controller;

import com.mem.model.MemService;
import com.memberCoupon.model.service.MemberCouponService;
import com.memberCoupon.model.service.impl.MemberCouponServiceImpl;
import com.orderBuy.model.entity.OrderBuy;
import com.orderBuy.model.service.OrderBuyService;
import com.orderBuy.model.service.impl.OrderBuyServiceImpl;
import core.util.MailServiceForOrder;

import javax.persistence.criteria.Order;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

@WebServlet(name = "EcpayReturn", value = "/EcpayReturn")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class EcpayReturn extends HttpServlet {
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
        String couponId = req.getParameter("CustomField1");
        String RtnCode = req.getParameter("RtnCode");//付款狀態
        String MerchantTradeNo = req.getParameter("MerchantTradeNo");//訂單編號(綠界查詢用)
        String PaymentDate = req.getParameter("PaymentDate"); //付款日期
        String PaymentType = req.getParameter("PaymentType");

        String orderId = null;
        byte orderPaying;

        if ("Credit_CreditCard".equals(PaymentType)) {
            orderPaying = 2; // 使用"信用卡"付款
        } else {
            orderPaying = 1; // 使用"ATM"付款
        }

        //取出訂單id
        if (MerchantTradeNo != null && MerchantTradeNo.length() != 0) {
            StringTokenizer tk = new StringTokenizer(MerchantTradeNo, "br");
            if (tk.hasMoreElements()) {
                orderId = tk.nextToken();
            }
        }

        OrderBuyService orderBuyService = new OrderBuyServiceImpl();
        assert orderId != null;
        OrderBuy orderBuy = orderBuyService.getOrderById(Integer.valueOf(orderId));


        Integer memberId = orderBuy.getMemId();
        Double finalPrice = orderBuy.getFinalPrice();
        String orderOther = orderBuy.getOrderOther();
        String receiverName = orderBuy.getReceiverName();


        if ("1".equals(RtnCode)) {

            Date date = new Date();
            String newOrderOther = orderOther
                    + "\n" + "----- " + date + " -----"
                    + "\n" + "付款成功，準備出貨"
                    + "\n" + "綠界訂單編號: " + MerchantTradeNo;

            orderBuy.setOrderStatus((byte) 2);
            orderBuy.setOrderPaying(orderPaying);
            orderBuy.setOrderOther(newOrderOther);
            orderBuyService.updateOrder(orderBuy);

            MemService memService = new MemService();
            // 寄email訂單成立
            MailServiceForOrder mailService = new MailServiceForOrder();
            String memEmail = memService.getOneMem(memberId).getMem_email();

            mailService.sendMail(memEmail, "【訂單付款成功通知】 您在 Ba-rei 的訂單已付款成功", PaymentDate, receiverName, String.valueOf(memberId), orderId, String.valueOf(finalPrice));


            ServletContext sc = this.getServletContext();
            String contextPath = sc.getContextPath();
            String url = contextPath + "/frontend/commodityDetails/OrderDetail.html";
            res.sendRedirect(url);

        } else {


            MemberCouponService memberCouponSvc = new MemberCouponServiceImpl();
            // 將優惠券切換為 0: 未使用
            memberCouponSvc.updateCouponStatus(memberId, Integer.valueOf(couponId), (byte) 0);

            Date date = new Date();
            String newOrderOther = orderOther
                    + "\n" + "----- " + date + " -----"
                    + "\n" + "付款失敗，訂單取消";


            orderBuy.setOrderStatus((byte) 9);
            orderBuy.setOrderPaying(orderPaying);
            orderBuy.setOrderOther(newOrderOther);

            orderBuyService.updateOrder(orderBuy);

            MemService memService = new MemService();
            // 寄email訂單失敗通知
            MailServiceForOrder mailService = new MailServiceForOrder();
            String memEmail = memService.getOneMem(memberId).getMem_email();

            mailService.sendMail(memEmail, "【訂單付款失敗通知】 您在 Ba-rei 的訂單因為未付款而失效", " -- ", receiverName, String.valueOf(memberId), orderId, String.valueOf(finalPrice));

            ServletContext sc = this.getServletContext();
            String contextPath = sc.getContextPath();
            String url = contextPath + "/frontend/commodityDetails/OrderDetail.html";
            res.sendRedirect(url);

        }
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req, res);
    }

}
