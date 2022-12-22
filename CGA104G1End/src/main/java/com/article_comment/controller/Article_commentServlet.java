package com.article_comment.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.article.model.ArticleService;
import com.article.model.ArticleVO;
import com.article_comment.model.Article_commentService;

@WebServlet("/Article_commentServlet")
public class Article_commentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("insert".equals(action)) {
			Integer article_id = Integer.valueOf(req.getParameter("article_id").trim());
			Integer mem_id = Integer.valueOf(req.getParameter("mem_id").trim());
			String com_content = req.getParameter("com_content");
			
			Article_commentService article_commentSvc = new Article_commentService();
			article_commentSvc.addArticle_comment(article_id, mem_id, com_content);
			ArticleService articleSvc = new ArticleService();
			ArticleVO articleVO = articleSvc.getOneArticle(article_id);
//			String url = "/article/select_page.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url);
//			successView.forward(req, res);
			req.setAttribute("articleVO", articleVO); // 資料庫取出的empVO物件,存入req
			String url = "/frontend/article/listOneArticle.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
		}
	}

}
