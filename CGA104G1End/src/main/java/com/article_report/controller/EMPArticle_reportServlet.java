package com.article_report.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.article.model.ArticleService;
import com.article_report.model.Article_reportService;

@WebServlet("/EMPArticle_reportServlet")
public class EMPArticle_reportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("updateRes".equals(action)) {
			Integer afrep_result = Integer.valueOf(req.getParameter("afrep_result").trim());
			Integer afrep_status = Integer.valueOf(req.getParameter("afrep_status").trim());
			Integer emp_id = Integer.valueOf(req.getParameter("emp_id").trim());
			Integer afrep_id = Integer.valueOf(req.getParameter("afrep_id").trim());
			
			Article_reportService article_reportSvc = new Article_reportService();
			article_reportSvc.updateArticle_report(afrep_status, afrep_result, emp_id, afrep_id);
			String url = "/backend/article/selectPage.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		if("hideArticle".equals(action)) {
			Integer afrep_result = Integer.valueOf(req.getParameter("afrep_result").trim());
			Integer afrep_status = Integer.valueOf(req.getParameter("afrep_status").trim());
			Integer emp_id = Integer.valueOf(req.getParameter("emp_id").trim());
			Integer afrep_id = Integer.valueOf(req.getParameter("afrep_id").trim());
			Integer article_id = Integer.valueOf(req.getParameter("article_id").trim());
			String article_content = "本文章因違反使用者規範已被管理員刪除";
			Integer article_status = 2;
			
			
			Article_reportService article_reportSvc = new Article_reportService();
			article_reportSvc.updateArticle_report(afrep_status, afrep_result, emp_id, afrep_id);
			ArticleService articleSvc = new ArticleService();
			articleSvc.hideArticle(article_id, article_content, article_status);
			String url = "/backend/article/selectPage.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
	}

}
