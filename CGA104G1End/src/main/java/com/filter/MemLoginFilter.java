package com.filter;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mem.model.MemService;
import com.mem.model.MemVO;
import com.verify_code.model.Verify_codeService;


@WebFilter(urlPatterns =  { "/frontend/article/addArticle.jsp",
        "/frontend/article/addPic.jsp",
        "/frontend/article/updateArticle.jsp",
        "/Article_reportServlet",
        "/Article_commentServlet",
        "/CusNameServlet",
        "/ArtLikeHateServlet",
        "/frontend/group_join/*",
        "/frontend/groupBuy/*",
        "/frontend/groupBuy/listallgroupbuy.html",
        "/frontend/mem/*",
        "/frontend/coupon/*",
        "/frontend/group_buy_order/*",
        "/frontend/commodityDetails/*",
         "/frontend/orderBuy/*"
} )

public class MemLoginFilter implements Filter {

    private FilterConfig config;

    public void init(FilterConfig config) {
        this.config = config;
    }

    public void destroy() {
        config = null;
    }


    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws ServletException, IOException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        // 【取得 session】

        Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
        req.setAttribute("errorMsgs", errorMsgs);

        HttpSession session = req.getSession();

        // 【從 session 判斷此user是否登入過】

        MemService memSvc = new MemService();
//   MemVO memVO = memSvc.login((String)(session.getAttribute("account")), (String)
        Integer mem_id=(Integer) session.getAttribute("mem_id");
        MemVO memVO = (MemVO) session.getAttribute("memVO");
        Object account = session.getAttribute("account");

        if(mem_id == null || account == null){
//   errorMsgs.put("login", "請登入會員");
//   session.setAttribute("errorMsgs", errorMsgs);
            session.setAttribute("location", req.getRequestURI());
            session.invalidate();
            res.sendRedirect(req.getContextPath() + "/frontend/memLogin/login.jsp");
            return;
        }else {

            Integer mem_status = memVO.getMem_status();

            if (mem_status == 2) {
                chain.doFilter(request, response);
                return;
//     未驗證會員
            } else if(mem_status == 1) {
//     補發驗證信
                String mem_email =(String)(session.getAttribute("mem_email"));
                String subject = "Ba-rei購物商城 會員密碼函";
                String Verifycode = memSvc.getAuthCode();
                String mem_name = req.getParameter("mem_account");
                String messageText = "親愛的Ba-rei會員" + mem_name + "你好 !\n" + "您的會員登入密碼為: " + Verifycode + "\n" +"請妥善保管並重新登入";
                Verify_codeService vSvc = new Verify_codeService();
                vSvc.addVc_code(mem_id, Verifycode);
//        驗證碼存入資料庫
                memSvc.sendMail(mem_email, subject, messageText);
                res.sendRedirect(req.getContextPath() + "/frontend/memLogin/notVerify.jsp");

            } else {
//     被停權用戶
                session.setAttribute("location", req.getRequestURI());
                res.sendRedirect(req.getContextPath() + "/frontend/memLogin/freezeAc.jsp");
            }

        }
    }
}
