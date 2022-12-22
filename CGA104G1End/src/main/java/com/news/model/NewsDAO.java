package com.news.model;

import org.hibernate.sql.Insert;
import org.json.JSONArray;
import org.json.JSONObject;

import com.article.model.ArticleVO;
import com.item.model.ItemVO;

import java.util.ArrayList;
import java.util.List;

public class NewsDAO implements NewsDAO_interface{

	@Override
	public void insert(NewsVO newsVO) {
		try {
			beginTransaction();
			getSession().save(newsVO);
			commit();
		} catch (Exception e) {
			rollback();
			e.printStackTrace();
		}
	}

	@Override
	public void update(NewsVO newsVO) {
		try {
			beginTransaction();
			getSession().update(newsVO);
			commit();
		} catch (Exception e) {
			rollback();
			e.printStackTrace();
		}
	} 

	@Override
	public void delete(Integer newsId) {
		try {
			beginTransaction();
			NewsVO newsVO = new NewsVO();
			newsVO.setNewsId(newsId);
			getSession().remove(newsVO);
			commit();
		} catch (Exception e) {
			rollback();
			e.printStackTrace();
		}
	}

	@Override
	public List<NewsVO> findByPrimaryKey(String newsTitle) {
		try {
			beginTransaction();
			final String hql = "FROM NewsVO WHERE newsTitle LIKE %:title%";
			List<NewsVO> list = getSession().createQuery(hql, NewsVO.class).setParameter("title", newsTitle).list();
			return list;
		} catch (Exception e) {
			rollback();
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<NewsVO> getAll() {
		try {
			beginTransaction();
			List<NewsVO> list = new ArrayList<NewsVO>();
			final String hql = "FROM NewsVO ORDER BY newsId DESC";
			list = getSession().createQuery(hql, NewsVO.class).list();
			commit();
			return list;
		} catch (Exception e) {
			rollback();
			e.printStackTrace();
			return null;
		}
		
	}
	
}
