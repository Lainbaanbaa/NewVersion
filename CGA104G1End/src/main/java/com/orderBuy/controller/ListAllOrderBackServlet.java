package com.orderBuy.controller;

import com.orderBuy.model.service.OrderBuyService;
import com.orderBuy.model.service.impl.OrderBuyServiceImpl;
import org.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ListAllOrderBackServlet", value = "/ListAllOrderBackServlet")
public class ListAllOrderBackServlet extends HttpServlet {
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

        PrintWriter pw = res.getWriter();
        OrderBuyService orderBuyService = new OrderBuyServiceImpl();

        try {
            JSONArray details = orderBuyService.getAll();
            pw.println(details);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


