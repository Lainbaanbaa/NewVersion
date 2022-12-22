package com.news.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.article.model.ArticleService;
import com.article.model.ArticleVO;
import com.google.gson.Gson;
import com.news.model.NewsService;
import com.news.model.NewsVO;

@WebServlet("/newsServlet")
public class newsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		PrintWriter out = res.getWriter();
		
		if("insert".equals(action)) {
//			Gson gson = new Gson();
//			String json = req.getParameter("value");
//			NewsVO newsVO = gson.fromJson(json, NewsVO.class);
//			NewsService newsSvc = new NewsService();
//			newsVO = newsSvc.addNews(newsVO);
//			res.getWriter().append(gson.toJson(newsVO));
			
//			BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream(),"utf-8"));
//			String json= "";
//			if(br !=null) {
//				json = br.readLine();
//			}
//			
//			JSONObject obj =new JSONObject(json); 
//			JSONObject addNews = obj.getJSONObject("value");
			
//			Integer empId = Integer.valueOf((String) addNews.get("empId"));
//			String newsTitle = String.valueOf((String) addNews.get("newsTitle"));
//			String newsContent = String.valueOf((String) addNews.get("newsContent"));
			
			Integer empId = Integer.valueOf(req.getParameter("empId").trim());
			String newsTitle = req.getParameter("newsTitle").trim();
			String newsContent = req.getParameter("newsContent").trim();
			
			NewsVO newsVO = new NewsVO();
			newsVO.setEmpId(empId);
			newsVO.setNewsTitle(newsTitle);
			newsVO.setNewsContent(newsContent);
			
			NewsService newsSvc = new NewsService();
			newsSvc.addNews(newsVO);
			
			String url = "/backend/news/newsIndex.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		if("getAll".equals(action)) {
			NewsService newsSvc = new NewsService();
			out.write(newsSvc.getAll().toString());
		}
		
		if("update".equals(action)) {
			System.out.println(1);
			Integer newsId = Integer.valueOf(req.getParameter("newsId").trim());
			String newsTitle = req.getParameter("newsTitle").trim();
			String newsContent = req.getParameter("newsContent").trim();
			
			NewsVO newsVO = new NewsVO();
			newsVO.setNewsId(newsId);
			newsVO.setNewsTitle(newsTitle);
			newsVO.setNewsContent(newsContent);
			
			NewsService newsSvc = new NewsService();
			newsSvc.updateNews(newsVO);
			
			String url = "/backend/news/newsIndex.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		if("delete".equals(action)) {
			System.out.println("近來");
			Integer newsId = Integer.valueOf(req.getParameter("newsId"));
			System.out.println(newsId);
			NewsService newsSvc = new NewsService();
			newsSvc.deleteNews(newsId);
			System.out.println("delete");
			String url = "/backend/news/newsIndex.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
	}

}
