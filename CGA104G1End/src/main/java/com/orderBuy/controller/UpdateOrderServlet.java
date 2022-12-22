package com.orderBuy.controller;

import com.orderBuy.model.entity.OrderBuy;
import com.orderBuy.model.service.OrderBuyService;
import com.orderBuy.model.service.impl.OrderBuyServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

import static java.lang.Integer.valueOf;

@WebServlet(name = "UpdateOrderServlet", value = "/UpdateOrderServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class UpdateOrderServlet extends HttpServlet {

    private OrderBuyService service;

    @Override
    public void init() throws ServletException {
        service = new OrderBuyServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        res.setCharacterEncoding("UTF-8");

        req.setCharacterEncoding("UTF-8");

        res.setContentType("text/html;charset=utf-8");
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

        final String action = req.getParameter("action");

        switch (action) {
            case "cancel", "return" -> application(req, res);
            case "ship" -> ship(req, res);
            case "finish" -> finish(req, res);
            case "acceptReturn" -> acceptReturn(req, res);
            case "rejectReturn" -> rejectReturn(req, res);
        }

    }

    private void ship(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

        final Integer orderId = valueOf(req.getParameter("orderId"));
        final String orderOther = req.getParameter("orderOther");

        OrderBuyService orderBuyService = new OrderBuyServiceImpl();
        OrderBuy orderBuy = orderBuyService.getOrderById(orderId);

        Byte newOrderStatus = 3;

        Date date = new Date();

        String others = "物流編號: ";
        if (orderBuy.getOrderStatus() == 3) {
            others = "更正訂單物流編號，更新為: ";
        } else if (orderBuy.getOrderStatus() == 8) {
            others = "換貨處理物編號: ";
        }

        String oldOderOther;
        if (orderBuy.getOrderOther() == null) {
            oldOderOther = "沒有更早的內容";
        } else {
            oldOderOther = orderBuy.getOrderOther();
        }

        String newOrderOther = oldOderOther
                + "\n" + "----- " + date + " -----"
                + "\n" + "已成功出貨，" + others + orderOther;
        orderBuy.setOrderStatus(newOrderStatus);
        orderBuy.setOrderOther(newOrderOther);
        orderBuy.setTrackingNum(orderOther);
        orderBuyService.updateOrder(orderBuy);

    }

    private void application(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

        final Integer orderId = valueOf(req.getParameter("orderId"));
        final String orderOther = req.getParameter("orderOther");
        final String action = req.getParameter("action");

        OrderBuyService orderBuyService = new OrderBuyServiceImpl();
        OrderBuy orderBuy = orderBuyService.getOrderById(orderId);

        Byte newOrderStatus = switch (action) {
            case "cancel" -> 4;
            case "return" -> 7;
            default -> orderBuy.getOrderStatus();
        };

        Date date = new Date();

        String orderStatus = switch (valueOf(orderBuy.getOrderStatus())) {
            case 0 -> "待付款";
            case 1 -> "未完成付款，訂單自動取消";
            case 2 -> "已完成付款，準備出貨中";
            case 3 -> "正在運輸途中";
            case 4 -> "取消訂單審核中";
            case 5 -> "未取貨，退回平台";
            case 6 -> "已完成取貨";
            case 7 -> "退/換貨申請中";
            case 8 -> "退/換貨處理中";
            default -> "訂單已取消";
        };

        String oldOderOther;
        if (orderBuy.getOrderOther() == null) {
            oldOderOther = "沒有更早的內容";
        } else {
            oldOderOther = orderBuy.getOrderOther();
        }

        String newOrderOther = oldOderOther
                + "\n" + "----- " + date + " -----"
                + "\n" + "申請項目: " + action + " 申請前狀態: " + orderStatus
                + "\n" + "訂單狀況: " + orderOther;
        orderBuy.setOrderStatus(newOrderStatus);
        orderBuy.setOrderOther(newOrderOther);
        orderBuyService.updateOrder(orderBuy);
    }

    private void finish(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

        final Integer orderId = valueOf(req.getParameter("orderId"));
        final String action = req.getParameter("action");

        OrderBuyService orderBuyService = new OrderBuyServiceImpl();
        OrderBuy orderBuy = orderBuyService.getOrderById(orderId);

        Byte newOrderStatus = 6;

        Date date = new Date();

        String oldOderOther;
        if (orderBuy.getOrderOther() == null) {
            oldOderOther = "沒有更早的內容";
        } else {
            oldOderOther = orderBuy.getOrderOther();
        }

        String newOrderOther = oldOderOther
                + "\n" + "----- " + date + " -----"
                + "\n" + "已由買家確認訂單已完成";
        orderBuy.setOrderStatus(newOrderStatus);
        orderBuy.setOrderOther(newOrderOther);
        orderBuyService.updateOrder(orderBuy);
    }

    private void acceptReturn(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

        final Integer orderId = valueOf(req.getParameter("orderId"));

        OrderBuyService orderBuyService = new OrderBuyServiceImpl();
        OrderBuy orderBuy = orderBuyService.getOrderById(orderId);

        Byte newOrderStatus = 8;

        Date date = new Date();

        String oldOderOther;
        if (orderBuy.getOrderOther() == null) {
            oldOderOther = "沒有更早的內容";
        } else {
            oldOderOther = orderBuy.getOrderOther();
        }

        String newOrderOther = oldOderOther
                + "\n" + "----- " + date + " -----"
                + "\n" + "已通過退/換貨審核，開始退換貨作業..";
        orderBuy.setOrderStatus(newOrderStatus);
        orderBuy.setOrderOther(newOrderOther);
        orderBuyService.updateOrder(orderBuy);

    }

    private void rejectReturn(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

        final Integer orderId = valueOf(req.getParameter("orderId"));

        OrderBuyService orderBuyService = new OrderBuyServiceImpl();
        OrderBuy orderBuy = orderBuyService.getOrderById(orderId);

        Byte newOrderStatus = 6;

        Date date = new Date();

        String oldOderOther;
        if (orderBuy.getOrderOther() == null) {
            oldOderOther = "沒有更早的內容";
        } else {
            oldOderOther = orderBuy.getOrderOther();
        }

        String newOrderOther = oldOderOther
                + "\n" + "----- " + date + " -----"
                + "\n" + "已拒絕退/換貨審核";
        orderBuy.setOrderStatus(newOrderStatus);
        orderBuy.setOrderOther(newOrderOther);
        orderBuyService.updateOrder(orderBuy);

    }
}
