package com.taskmanager.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.taskmanager.dao.InfoDAO;
import com.taskmanager.entity.Info;
import com.taskmanager.entity.Page;

public class InfoDAOImpl extends HibernateDaoSupport implements InfoDAO{

	@Override
	public Info findInfoById(int id) {
		return this.getHibernateTemplate().get(Info.class, id);
	}

	@Override
	public void delete(Info info) {
		this.getHibernateTemplate().delete(info);
	}

	@Override
	public void save(Info info) {
		this.getHibernateTemplate().save(info);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Info> queryByPage(Page page,int style) {
		return (List<Info>)this.getHibernateTemplate().executeFind(new HibernateCallback(){  
            public Object doInHibernate(Session session)  
                    throws HibernateException, SQLException {  
                Query query = session.createQuery("from Info n where n.style="+style+ "order by n.time desc");  
                query.setFirstResult(page.getBeginIndex());  
                query.setMaxResults(page.getEveryPage());  
                return query.list();  
            }
        }); 
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Info> searchByName(Page page, String keyword,int style) {
		return (List<Info>)this.getHibernateTemplate().executeFind(new HibernateCallback(){  
            public Object doInHibernate(Session session)  
                    throws HibernateException, SQLException {  
            	String hql="from Info i where i.name like '%"+keyword+"%' and i.style="+style+" order by i.time desc";
            	Query query = session.createQuery(hql);  
                query.setFirstResult(page.getBeginIndex());  
                query.setMaxResults(page.getEveryPage());  
                return query.list();  
            }
        });
	}
	@Override
	public int InfoCounts(String columnName,String keyword,int style){
		int rowTotal=0;
		if(columnName.equals("0"))
			rowTotal = ((Long)this.getHibernateTemplate().find("select count(id) from Info i where i.style="+style).get(0)).intValue(); 
		else
		{
			System.out.println("¹Ø¼ü×Ö"+keyword);
			rowTotal = ((Long)this.getHibernateTemplate().find("select count(*) from Info i where i."+columnName+" like '%"+keyword+"%' and i.style="+style).get(0)).intValue();
		}
		return rowTotal;
	}
}
