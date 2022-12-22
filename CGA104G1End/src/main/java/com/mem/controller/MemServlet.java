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

@WebServlet("/MemServlet")
public class MemServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String Date = null;

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        HttpSession session = req.getSession();

        if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

            Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
            req.setAttribute("errorMsgs", errorMsgs);

            /*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
            String str = req.getParameter("mem_id");
            if (str == null || (str.trim()).length() == 0) {
                errorMsgs.put("mem_id", "請輸入員工編號");
            }

            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req.getRequestDispatcher("/backend/mem/select_page.jsp");
                failureView.forward(req, res);
                return;// 程式中斷
            }

            Integer mem_id = null;
            try {
                mem_id = Integer.valueOf(str);
            } catch (Exception e) {
                errorMsgs.put("mem_id", "員工編號格式不正確");
            }
            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req.getRequestDispatcher("/frontend/mem/select_page.jsp");
                failureView.forward(req, res);
                return;// 程式中斷
            }

            /*************************** 2.開始查詢資料 *****************************************/
            MemService memSvc = new MemService();
            MemVO memVO = memSvc.getOneMem(mem_id);
            if (memVO == null) {
                errorMsgs.put("mem_id", "查無資料");
            }
//				Integer id = memVO.getMem_id();
//				if (id != mem_id) {
//					errorMsgs.put("mem_id","請輸入員工編號");
//				}

            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req.getRequestDispatcher("backend/mem/select_page.jsp");
                failureView.forward(req, res);
                return;// 程式中斷
            }
            /*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
            req.setAttribute("memVO", memVO); // 資料庫取出的empVO物件,存入req
            String url = "/backend/mem/listOneMem.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
            successView.forward(req, res);
        }

        if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

            Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
            req.setAttribute("errorMsgs", errorMsgs);

            /*************************** 1.接收請求參數 ****************************************/
            Integer mem_id = Integer.valueOf(req.getParameter("mem_id"));

            /*************************** 2.開始查詢資料 ****************************************/
            MemService memSvc = new MemService();
            MemVO memVO = memSvc.getOneMem(mem_id);

            /*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
            req.setAttribute("memVO", memVO); // 資料庫取出的memVO物件,存入req
            String url = "/backend/mem/update_mem_input.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
            successView.forward(req, res);
        }

        if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            /*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
            String mem_account = req.getParameter("mem_account").trim();
            if (mem_account == null || mem_account.trim().length() == 0) {
                errorMsgs.add("帳號請勿空白");
            }

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
            Integer mem_status = Integer.valueOf(req.getParameter("mem_status").trim());
            try {
                mem_status = Integer.valueOf(req.getParameter("mem_status").trim());
            } catch (IllegalArgumentException e) {
                errorMsgs.add("狀態請勿空白");
            }

            Integer mem_id = Integer.valueOf(req.getParameter("mem_id").trim());

            MemVO memVO = new MemVO();
            memVO.setMem_account(mem_account);
            memVO.setMem_password(mem_password);
            memVO.setMem_name(mem_name);
            memVO.setMem_address(mem_address);
            memVO.setMem_phone(mem_phone);
            memVO.setMem_uid(mem_uid);
            memVO.setMem_email(mem_email);
            memVO.setMem_sex(mem_sex);
            memVO.setMem_dob(mem_dob);
            memVO.setMem_status(mem_status);
            memVO.setMem_id(mem_id);

            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                req.setAttribute("memVO", memVO); // 含有輸入格式錯誤的memVO物件,也存入req
                RequestDispatcher failureView = req.getRequestDispatcher("/backend/mem/update_mem_input.jsp");
                failureView.forward(req, res);
                return; // 程式中斷
            }

            /*************************** 2.開始新增資料 ***************************************/
            MemService memSvc = new MemService();
            memSvc.update(mem_id, mem_account, mem_password, mem_name, mem_address, mem_phone, mem_uid, mem_email,
                    mem_sex, mem_dob, mem_status);

            /*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
            String url = "/backend/mem/listAllMem.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
            successView.forward(req, res);
        }


        if ("insert".equals(action)) { // 來自addEmp.jsp的請求

            Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
            req.setAttribute("errorMsgs", errorMsgs);

            /*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
            String mem_name = req.getParameter("mem_name");
            String mem_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
            if (mem_name == null || mem_name.trim().length() == 0) {
                errorMsgs.put("mem_name", "會員姓名:請勿空白");
            } else if (!mem_name.trim().matches(mem_nameReg)) { // 以下練習正則(規)表示式(regular-expression)
                errorMsgs.put("mem_name", "會員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
            }

            String mem_account = req.getParameter("mem_account").trim();
            if (mem_account == null || mem_account.trim().length() == 0) {
                errorMsgs.put("mem_account", "會員帳號請勿空白");
            }

            String mem_password = req.getParameter("mem_password").trim();
            if (mem_password == null || mem_password.trim().length() == 0) {
                errorMsgs.put("mem_password", "會員密碼請勿空白");
            }

            String mem_address = req.getParameter("mem_address").trim();
            if (mem_address == null || mem_address.trim().length() == 0) {
                errorMsgs.put("mem_address", "地址請勿空白");
            }

            String mem_phone = req.getParameter("mem_phone").trim();
            if (mem_phone == null || mem_phone.trim().length() == 0) {
                errorMsgs.put("mem_phone", "電話請勿空白");
            }

            String mem_uid = req.getParameter("mem_uid").trim();
            if (mem_uid == null || mem_uid.trim().length() == 0) {
                errorMsgs.put("mem_uid", "證號請勿空白");
            }

            String mem_email = req.getParameter("mem_email").trim();
            if (mem_email == null || mem_email.trim().length() == 0) {
                errorMsgs.put("mem_email", "Email請勿空白");
            }

            String mem_sex = req.getParameter("mem_sex").trim();
            if (mem_sex == null || mem_sex.trim().length() == 0) {
                errorMsgs.put("mem_sex", "性別請勿空白");
            }

            java.sql.Date mem_dob = null;
            try {
                mem_dob = java.sql.Date.valueOf(req.getParameter("mem_dob").trim());
            } catch (IllegalArgumentException e) {
                errorMsgs.put("mem_dob", "請輸入日期");
            }

//			byte[]picupload=null;
//			Part part = req.getPart(mem_up);
//			InputStream in =getServletContext().getResourceAsStream("/NoData/null.jpg");
//			byte[]picup=new byte[in.available()];
//			in.read(picupload);

            MemVO memVO = new MemVO();
            memVO.setMem_account(mem_account);
            memVO.setMem_password(mem_password);
            memVO.setMem_name(mem_name);
            memVO.setMem_address(mem_address);
            memVO.setMem_phone(mem_phone);
            memVO.setMem_uid(mem_uid);
            memVO.setMem_email(mem_email);
            memVO.setMem_sex(mem_sex);
            memVO.setMem_dob(mem_dob);
//			memVO.setMem_up(mem_up);

            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                req.setAttribute("memVO", memVO); // 含有輸入格式錯誤的empVO物件,也存入req
                RequestDispatcher failureView = req.getRequestDispatcher("/frontend/mem/addMem.jsp");
                failureView.forward(req, res);
                return;
            }

            /*************************** 2.開始新增資料 ***************************************/
            MemService memSvc = new MemService();
            memSvc.addMem(mem_account, mem_password, mem_name, mem_address, mem_phone, mem_uid, mem_email, mem_sex,
                    mem_dob);


            /*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
//			mailSvc.sendMail("dragondazs17@gmail.com","主旨","內文");
            String url = "/backend/mem/listAllMem.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
            successView.forward(req, res);
        }

        if ("delete".equals(action)) { // 來自listAllEmp.jsp

            Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
            req.setAttribute("errorMsgs", errorMsgs);

            /*************************** 1.接收請求參數 ***************************************/

            Integer mem_id = Integer.valueOf(req.getParameter("mem_id"));
            Verify_codeService vSvc = new Verify_codeService();
            vSvc.deleteVerify_codeVO(mem_id);


            /*************************** 2.開始刪除資料 ***************************************/
            MemService memSvc = new MemService();
            memSvc.deleteMem(mem_id);

            /*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
            String url = "/backend/mem/listAllMem.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
            successView.forward(req, res);

        }

        if ("MemSerchPro".equals(action)) {
            Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
            req.setAttribute("errorMsgs", errorMsgs);
            Map<String, String[]> map = (Map<String, String[]>) session.getAttribute("map");
            if (req.getParameter("whichPage") == null) {
                Map<String, String[]> map1 = new HashMap<String, String[]>(req.getParameterMap());
                session.setAttribute("map", map1);
                map = map1;
            }
            MemService memSvc = new MemService();
            List<MemVO> list = memSvc.getAllMem(map);
            for (MemVO a : list) {
                System.out.println(a);
            }

            req.setAttribute("MemSerchPro", list);
            RequestDispatcher successView = req.getRequestDispatcher("/backend/mem/listAllMemPro.jsp");
            successView.forward(req, res);

        }


        if ("register".equals(action)) { // 來自login.jsp的請求

            Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
            req.setAttribute("errorMsgs", errorMsgs);
            MemService memSvc = new MemService();
            MemVO memVO = new MemVO();

            /*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

            String mem_account = req.getParameter("mem_account").trim();
            MemVO accountVO = memSvc.checkAccount(mem_account);
            if (accountVO != null) {
                errorMsgs.put("mem_account", "此帳號已被使用");
            }
            if (mem_account == null || mem_account.trim().length() == 0) {
                errorMsgs.put("mem_account", "會員帳號請勿空白");
            }

            String mem_password = req.getParameter("mem_password").trim();
            if (mem_password == null || mem_password.trim().length() == 0) {
                errorMsgs.put("mem_password", "會員密碼:請勿空白");
            }

            String mem_name = req.getParameter("mem_name");
            String mem_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]{2,10}$";
            if (mem_name == null || mem_name.trim().length() == 0) {
                errorMsgs.put("mem_name", "會員姓名:請勿空白");
            } else if (!mem_name.trim().matches(mem_nameReg)) { // 以下練習正則(規)表示式(regular-expression)
                errorMsgs.put("mem_name", "會員姓名: 只能是中、英文字母 , 且長度必需在2到8之間");
            }

            String mem_address = req.getParameter("mem_address").trim();
            if (mem_address == null || mem_address.trim().length() == 0) {
                errorMsgs.put("mem_address", "會員地址:請勿空白");
            }

            String mem_phone = req.getParameter("mem_phone").trim();
            if (mem_phone == null || mem_phone.trim().length() == 0) {
                errorMsgs.put("mem_phone", "會員電話:請勿空白");
            }

            String mem_uid = req.getParameter("mem_uid").trim();
            if (mem_uid == null || mem_uid.trim().length() == 0) {
                errorMsgs.put("mem_uid", "身分證字號:請勿空白");
            }

            String mem_email = req.getParameter("mem_email").trim();
            if (mem_email == null || mem_email.trim().length() == 0) {
                errorMsgs.put("mem_email", "Email:請勿空白");
            }

            String mem_sex = req.getParameter("mem_sex").trim();
            if (mem_sex == null || mem_sex.trim().length() == 0) {
                errorMsgs.put("mem_sex", "請選擇性別");
            }

            java.sql.Date mem_dob = null;
            try {
                mem_dob = java.sql.Date.valueOf(req.getParameter("mem_dob").trim());
            } catch (IllegalArgumentException e) {
                errorMsgs.put("mem_dob", "請選擇日期");
            }


            memVO.setMem_account(mem_account);
            memVO.setMem_password(mem_password);
            memVO.setMem_name(mem_name);
            memVO.setMem_address(mem_address);
            memVO.setMem_phone(mem_phone);
            memVO.setMem_uid(mem_uid);
            memVO.setMem_email(mem_email);
            memVO.setMem_sex(mem_sex);
            memVO.setMem_dob(mem_dob);


            if (!errorMsgs.isEmpty()) {
                req.setAttribute("memVO", memVO); // 含有輸入格式錯誤的memVO物件,也存入req
                RequestDispatcher failureView = req.getRequestDispatcher("/frontend/memLogin/register.jsp");
                failureView.forward(req, res);
                return;
            }

            /*************************** 2. 發送驗證碼 ***************************************/


            String to = req.getParameter("mem_email");

            String subject = "Ba-rei寵物線上購物及交流平台 會員註冊信";

            String reg_name = req.getParameter("mem_name");
            String regpass = memSvc.getAuthCode();
            String messageText = "Hello ! " + reg_name + "  先生/小姐\n" + "歡迎您加入 Ba-rei寵物線上購物及交流平台" + "\n" + "您註冊帳號的驗證碼為: " + regpass + "\n" + "請在5分鐘內完成驗證並重新登入";

            memSvc.sendMail(to, subject, messageText);

            /*************************** 4.開始新增資料 ***************************************/
            memSvc.register(mem_account, mem_password, mem_name, mem_address, mem_phone, mem_uid, mem_email, mem_sex,
                    mem_dob);
            /*************************** 5.利用註冊的帳號密碼方法獲取資料的的memID存到session ***************************************/

            Integer mem_id = memSvc.login(mem_account, mem_password).getMem_id();

            session.setAttribute("mem_id", mem_id);
            session.setAttribute("mem_email", mem_email);

//			      呼叫方法存放驗證碼
            Verify_codeService vSvc = new Verify_codeService();
            vSvc.addVc_code(mem_id, regpass);

            /*************************** 5.準備轉交驗證資料(Send the Success view) ***********/

            String url = "/frontend/memLogin/register_success.jsp";
            RequestDispatcher successViewchk = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
            successViewchk.forward(req, res);

        }

        if ("accountchk".equals(action)) {

            Map<String, String> msg = new LinkedHashMap<String, String>();
            req.setAttribute("msg", msg);

            String mem_account = req.getParameter("mem_account");
            MemService memSvc = new MemService();
            MemVO memVO = memSvc.checkAccount(mem_account);
            PrintWriter out = res.getWriter();

            if (memVO != null) {
                String chkAc = memSvc.checkAccount(mem_account).getMem_account();
                JSONObject jsonAc = new JSONObject();

//	        System.out.println(chkAc);
//	        System.out.println(jsonAc);

                jsonAc.put("mem_account", chkAc);
                out.println(jsonAc);


//			req.setAttribute("mem_account", mem_account);
            } else {
                JSONObject jsonAcf = new JSONObject();
                String chkAcf = "null";
                jsonAcf.put("mem_account", chkAcf);
                out.println(jsonAcf);
            }

//					session.setAttribute("memVO", memVO); // 含有輸入格式錯誤的memVO物件,也存入req
//					RequestDispatcher failureView = req.getRequestDispatcher("/frontend/mem/register.jsp");
//					failureView.forward(req, res);


//				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/mem/register.jsp");
//				failureView.forward(req, res);
//				req.getRequestDispatcher("/frontend/mem/register.jsp").forward(req, res);

        }


//		輸入驗證碼進入比對
        if ("resend".equals(action)) {
            MemService memSvc = new MemService();
            Verify_codeService vSvc = new Verify_codeService();

            String to = req.getParameter("mem_email");
            String subject = "Ba-rei購物商城 會員註冊信";

            String reg_name = req.getParameter("mem_name");
            String regpass = memSvc.getAuthCode();
            String messageText = "Hello ! " + reg_name + "  先生/小姐\n" + "歡迎您加入 Ba-rei 購物商城" + "\n" + "您註冊帳號的驗證碼為: " + regpass + "\n" + "請在5分鐘內完成驗證並重新登入";

            memSvc.sendMail(to, subject, messageText);

            req.setAttribute("msg", "已重新發送驗證碼,請至信箱確認");
            req.getRequestDispatcher("/frontend/memLogin/register_success.jsp").forward(req, res);

            /*************************** 5.利用註冊的帳號密碼方法獲取資料的的memID存到session ***************************************/

            Integer mem_id = Integer.valueOf(req.getParameter("mem_id"));
            session.setAttribute("mem_id", mem_id);

//			      呼叫方法存放驗證碼
            vSvc.addVc_code(mem_id, regpass);
        }
        if ("regconfirm".equals(action)) {

            Map<String, String> msg = new LinkedHashMap<String, String>();
            req.setAttribute("msg", msg);

            String regpasschk = req.getParameter("regpasschk");
            Integer mem_id = Integer.valueOf(req.getParameter("mem_id"));

            Verify_codeService vSvc = new Verify_codeService();
            MemService memSvc = new MemService();


            if (!(regpasschk.equals(vSvc.checkVerify_code(mem_id).getVc_code()))) {
                req.setAttribute("msg", "驗證碼錯誤!請重新輸入");
                req.getRequestDispatcher("/frontend/memLogin/register_success.jsp").forward(req, res);

            } else {


                memSvc.updateStatus(mem_id);
                session.invalidate();
                req.setAttribute("msg", "註冊驗證成功 ! 請重新登入");
                String url = "/frontend/memLogin/login.jsp";
                RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
                successView.forward(req, res);
            }
        }


        if ("login".equals(action)) { // 來自addEmp.jsp的請求

            MemService memSvc = new MemService();


            String account = req.getParameter("account");
            String password = req.getParameter("password");


            MemVO memVO = memSvc.login(account, password);


            if (memVO == null) {
                req.setAttribute("msg", "帳號或密碼錯誤!請重新輸入");
                req.getRequestDispatcher("/frontend/memLogin/login.jsp").forward(req, res);

            } else {
                Integer mem_id = (memVO.getMem_id());
                Integer mem_status = (memVO.getMem_status());
                String mem_email = (memVO.getMem_email());
                session.setAttribute("memVO", memVO); // 資料庫取出的物件,存入session
                session.setAttribute("account", account);
                session.setAttribute("mem_id", mem_id); // 資料庫取出的物件,存入session
                session.setAttribute("mem_email", mem_email); // 資料庫取出的物件,存入session
                session.setAttribute("mem_status", mem_status); // 資料庫取出的物件,存入session
                try {
                    String location = (String) session.getAttribute("location");
                    if (location != null) {
                        session.removeAttribute("location"); // *工作2: 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
                        res.sendRedirect(location);
                        return;
                    }
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                }

                String url = "/CGA104G1/frontend/index.html";
                res.sendRedirect(url);
            }
        }

        if ("forgotac".equals(action)) {

            Map<String, String> msg = new LinkedHashMap<String, String>();
            req.setAttribute("msg", msg);

            String mem_account = req.getParameter("mem_account");
            String mem_email = req.getParameter("mem_email");
            String mem_uid = req.getParameter("mem_uid");
            String account = null;

            MemService memSvc = new MemService();
            try {
                account = memSvc.findAccount(mem_email, mem_uid).getMem_account();
            } catch (Exception e) {
                req.setAttribute("msg", "輸入錯誤!請重新輸入");
                req.getRequestDispatcher("/frontend/memLogin/forgot.jsp").forward(req, res);

            }

            req.setAttribute("msg", "  您的帳號為  〖" + account + "〗 , 請妥善保管 ");
            RequestDispatcher failureViewac = req.getRequestDispatcher("/frontend/memLogin/forgot.jsp");
            failureViewac.forward(req, res);

        }

        if ("forgotpw".equals(action)) {

            Map<String, String> msg = new LinkedHashMap<String, String>();
            req.setAttribute("msg", msg);

            String mem_account = req.getParameter("mem_account");
            String mem_email = req.getParameter("mem_email");
            String Verifycode = null;

            MemService memSvc = new MemService();
            try {
                Verifycode = memSvc.findPassword(mem_account, mem_email).getMem_password();

            } catch (Exception e) {
                req.setAttribute("msg", "輸入錯誤!請重新輸入");
                req.getRequestDispatcher("/frontend/memLogin/forgot.jsp").forward(req, res);

            }


            String subject = "Ba-rei購物商城 會員密碼函";

            String mem_name = req.getParameter("mem_account");
            String messageText = "親愛的Ba-rei會員" + mem_name + "你好 !\n" + "您的會員登入密碼為: " + Verifycode + "\n" + "請妥善保管並重新登入";

            memSvc.sendMail(mem_email, subject, messageText);

            req.setAttribute("msg", "已發送至信箱,請至信箱確認");
            RequestDispatcher failureViewpw = req.getRequestDispatcher("/frontend/memLogin/login.jsp");
            failureViewpw.forward(req, res);

        }
        
        if ("logout".equals(action)) {
			session.invalidate();
			String url = "/frontend/memLogin/login.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
    }
}

