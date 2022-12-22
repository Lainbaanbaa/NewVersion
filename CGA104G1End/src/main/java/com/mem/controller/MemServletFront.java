package com.mem.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.emp.model.EmpService;
import com.emp.model.EmpVO;
import com.mem.model.MemService;
import com.mem.model.MemVO;
import com.verify_code.model.Verify_codeService;

@WebServlet("/MemServletFront")
public class MemServletFront extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String Date = null;

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        HttpSession session = req.getSession();

        if ("getOne_For_UpdateMem".equals(action)) { // 來自listAllEmp.jsp的請求

            Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
            req.setAttribute("errorMsgs", errorMsgs);

            /*************************** 1.接收請求參數 ****************************************/
            Integer mem_id = (Integer) session.getAttribute("mem_id");

            /*************************** 2.開始查詢資料 ****************************************/
            MemService memSvc = new MemService();
            MemVO memVO = memSvc.getOneMem(mem_id);

            /*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
            session.setAttribute("memVO", memVO); // 資料庫取出的empVO物件,存入req
            String url = "/frontend/mem/updateMem.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
            successView.forward(req, res);
        }

        if ("updateMem".equals(action)) { // 來自update_emp_input.jsp的請求

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            /*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

//   String mem_account = req.getParameter("mem_account").trim();
//   if (mem_account == null || mem_account.trim().length() == 0) {
//    errorMsgs.add("帳號請勿空白");
//   }

            String mem_password = req.getParameter("mem_password").trim();
            if (mem_password == null || mem_password.trim().length() == 0) {
                errorMsgs.add("密碼請勿空白");
            }

            String mem_name = req.getParameter("mem_name");
            String mem_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
            if (mem_name == null || mem_name.trim().length() == 0) {
                errorMsgs.add("員工姓名: 請勿空白");
            } else if (!mem_name.trim().matches(mem_nameReg)) { // 以下練習正則(規)表示式(regular-expression)
                errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
            }


            String mem_address = req.getParameter("mem_address").trim();
            if (mem_address == null || mem_address.trim().length() == 0) {
                errorMsgs.add("地址請勿空白");
            }


            String mem_email = req.getParameter("mem_email").trim();
            if (mem_email == null || mem_email.trim().length() == 0) {
                errorMsgs.add("Email請勿空白");
            }

            String mem_phone = req.getParameter("mem_phone").trim();
            if (mem_phone == null || mem_phone.trim().length() == 0) {
                errorMsgs.add("電話請勿空白");
            }

            String mem_uid = req.getParameter("mem_uid").trim();
            if (mem_uid == null || mem_uid.trim().length() == 0) {
                errorMsgs.add("證號請勿空白");
            }

            String mem_sex = req.getParameter("mem_sex").trim();
            if (mem_sex == null || mem_sex.trim().length() == 0) {
                errorMsgs.add("性別請勿空白");
            }

            java.sql.Date mem_dob = null;
            try {
                mem_dob = java.sql.Date.valueOf(req.getParameter("mem_dob").trim());
            } catch (IllegalArgumentException e) {
                errorMsgs.add("生日請勿空白");
            }
//   Integer mem_status = Integer.valueOf(req.getParameter("mem_status").trim());
//   try {
//    mem_status = Integer.valueOf(req.getParameter("mem_status").trim());
//   } catch (IllegalArgumentException e) {
//    errorMsgs.add("狀態請勿空白");
//   }
//
            Integer mem_id = Integer.valueOf(req.getParameter("mem_id").trim());

            MemVO memVO = new MemVO();
//   memVO.setMem_account(mem_account);
            memVO.setMem_password(mem_password);
            memVO.setMem_name(mem_name);
            memVO.setMem_address(mem_address);
            memVO.setMem_phone(mem_phone);
            memVO.setMem_uid(mem_uid);
            memVO.setMem_email(mem_email);
            memVO.setMem_sex(mem_sex);
            memVO.setMem_dob(mem_dob);
//   memVO.setMem_status(mem_status);
            memVO.setMem_id(mem_id);

            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                session.setAttribute("memVO", memVO);
                RequestDispatcher failureView = req.getRequestDispatcher("/frontend/mem/updateMem.jsp");
                failureView.forward(req, res);
                return; // 程式中斷
            }

            /*************************** 2.開始新增資料 ***************************************/

            MemService memSvc = new MemService();
            memSvc.updateMem(mem_id, mem_password, mem_name, mem_address, mem_phone, mem_uid, mem_email,
                    mem_sex, mem_dob);
            session.invalidate();
            /*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
            String url = "/CGA104G1/frontend/mem/mem_index.jsp";
            res.sendRedirect(url);

        }


    }
}