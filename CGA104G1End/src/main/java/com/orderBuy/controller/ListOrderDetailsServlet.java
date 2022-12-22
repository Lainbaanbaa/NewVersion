package com.orderBuy.controller;

import com.orderBuy.model.service.OrderBuyService;
import com.orderBuy.model.service.impl.OrderBuyServiceImpl;
import org.json.JSONArray;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ListOrderDetailsServlet", value = "/ListOrderDetailsServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class ListOrderDetailsServlet extends HttpServlet {

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

        HttpSession session = req.getSession();
        final Integer memId = (Integer) session.getAttribute("mem_id");
        System.out.println(memId);

        PrintWriter pw = res.getWriter();
        OrderBuyService orderBuyService = new OrderBuyServiceImpl();

        try {
            JSONArray details = orderBuyService.getAllDetails(memId);
            pw.println(details);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
