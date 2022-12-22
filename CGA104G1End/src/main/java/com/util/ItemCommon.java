package com.util;

import org.hibernate.Session;

public interface ItemCommon {
	default Session getSession() {
		return HibernateUtil.getSessionFactory().getCurrentSession();
	}
}
