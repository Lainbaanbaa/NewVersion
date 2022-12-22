package com.article.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.article.model.ArticleService;
import com.article.model.ArticleVO;
import com.mem.model.MemService;
import com.mem.model.MemVO;

@WebServlet("/ArticleServlet")
public class ArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("article_id");

			Integer article_id = null;
			try {
				article_id = Integer.valueOf(str);
			} catch (Exception e) {

			}

			/*************************** 2.開始查詢資料 *****************************************/
			ArticleService articleSvc = new ArticleService();
			ArticleVO articleVO = articleSvc.getOneArticle(article_id);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("articleVO", articleVO); // 資料庫取出的empVO物件,存入req
			String url = "/frontend/article/listOneArticle.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
		}

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			Integer mem_id = Integer.valueOf(req.getParameter("mem_id").trim());
			Integer sort_id = Integer.valueOf(req.getParameter("sort_id").trim());
			String article_title = req.getParameter("article_title");

			String article_content = req.getParameter("article_content").trim();

			/*************************** 2.開始新增資料 ***************************************/
			ArticleService articleSvc = new ArticleService();
			ArticleVO articleVO = articleSvc.addArticle(mem_id, sort_id, article_title, article_content);
			
	        session.setAttribute("mem_id", mem_id);
	        session.setAttribute("sort_id", sort_id);
	        session.setAttribute("article_content", article_content);
	        

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			req.setAttribute("articleVO", articleVO);
			String url = "/frontend/article/select_page.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			/*************************** 1.接收請求參數 ****************************************/
			Integer article_id = Integer.valueOf(req.getParameter("article_id"));

			/*************************** 2.開始查詢資料 ****************************************/
			ArticleService articleSvc = new ArticleService();
			ArticleVO articleVO = articleSvc.getOneArticle(article_id);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("articleVO", articleVO);
			String param = "?article_id=" + articleVO.getArticle_id() + "&article_title=" + articleVO.getArticle_title()
					+ "&article_content=" + articleVO.getArticle_content() + "&mem_id=" + articleVO.getMem_id()
					+ "&sort_id=" + articleVO.getSort_id();
			String url = "/frontend/article/updateArticle.jsp" + param;
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
			successView.forward(req, res);
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			Integer article_id = Integer.valueOf(req.getParameter("article_id").trim());
			Integer mem_id = Integer.valueOf(req.getParameter("mem_id").trim());
			Integer sort_id = Integer.valueOf(req.getParameter("sort_id").trim());
			String article_title = req.getParameter("article_title");

			String article_content = req.getParameter("article_content").trim();

			ArticleVO articleVO2 = new ArticleVO();
			articleVO2.setSort_id(sort_id);
			req.setAttribute("articleVO", articleVO2);

			/*************************** 2.開始修改資料 *****************************************/
			ArticleService articleSvc = new ArticleService();
			ArticleVO articleVO = articleSvc.updateArticle(article_id, sort_id, article_title, article_content, mem_id);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("articleVO", articleVO); // 資料庫update成功後,正確的的empVO物件,存入req
			String url = "/frontend/article/listOneArticle.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);
		}

		if ("delete".equals(action)) {
			Integer article_id = Integer.valueOf(req.getParameter("article_id"));

			ArticleService articleSvc = new ArticleService();
			articleSvc.deleteArticle(article_id);

			String url = "/frontend/article/select_page.jsp";
			RequestDispatcher successview = req.getRequestDispatcher(url);
			successview.forward(req, res);
		}
		
		if ("ArtSearch".equals(action)) {
            Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
            req.setAttribute("errorMsgs", errorMsgs);
            Map<String, String[]> map = (Map<String, String[]>) session.getAttribute("map");
            if (req.getParameter("whichPage") == null) {
                Map<String, String[]> map1 = new HashMap<String, String[]>(req.getParameterMap());
                session.setAttribute("map", map1);
                map = map1;
            }
            ArticleService articleSvc = new ArticleService();
            List<ArticleVO> list = articleSvc.getAllArt(map);
            for (ArticleVO a : list) {
                System.out.println(a);
            }

            req.setAttribute("ArtSearch", list);
            RequestDispatcher successView = req.getRequestDispatcher("/frontend/article/listAllArt.jsp");
            successView.forward(req, res);

        }
	}

}
