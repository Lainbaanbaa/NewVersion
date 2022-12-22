/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.login.controller;

import com.emp.model.EmpService;
import com.emp.model.EmpVO;
import org.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CheckLoginServlet", value = "/CheckLoginServlet")
public class CheckLoginServlet extends HttpServlet {

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

        HttpSession session = req.getSession();
        final Integer empId = (Integer) session.getAttribute("empId");
        final String action = req.getParameter("action");

        PrintWriter pw = res.getWriter();
        JSONObject jsonObj = new JSONObject();

        if ("getEmpName".equals(action)) {
            if (empId != null) {
                EmpService empService = new EmpService();
                EmpVO empVO = empService.getOneEmp(empId);
                String empName = empVO.getEmp_name();
                jsonObj.put("empName",  empName + " 你好" );
            } else {
                jsonObj.put("empName", "你好 訪客");
            }
            pw.println(jsonObj);
        }
    }
}
