package com.article_report.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.article_report.model.Article_reportService;

@WebServlet("/Article_reportServlet")
public class Article_reportServlet extends HttpServlet {
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
			String afrep_content = req.getParameter("afrep_content");
			Integer afrep_status = Integer.valueOf(req.getParameter("afrep_status").trim());
			
			Article_reportService article_reportSvc = new Article_reportService();
			article_reportSvc.addArticle_report(article_id, mem_id, afrep_content, afrep_status);
			String url = "/frontend/article/select_page.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
	}

}
