package com.taskmanager.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.taskmanager.dao.CourseDAO;
import com.taskmanager.entity.Course;
import com.taskmanager.entity.Page;

public class CourseDAOImpl extends HibernateDaoSupport implements CourseDAO{
	
	@Override
	public Course findById(int id) {
		return this.getHibernateTemplate().get(Course.class, id);
	}

	@Override
	public void delete(Course course) {
		this.getHibernateTemplate().delete(course);
	}

	@Override
	public void update(Course course) {
		this.getHibernateTemplate().update(course);
	}

	@Override
	public void save(Course course) {
		this.getHibernateTemplate().save(course);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Course findByNo(String no) {
		String hql = "from Course c where c.no='"
				+ no + "'";
		List<Course> courses = (List<Course>) this.getHibernateTemplate().find(hql);
		if (courses.size() > 0) {
			return courses.get(0);
		}
		return null;
	}

	@SuppressWarnings({ "unchecked"})
	@Override
	public Course findByTeacherId(int id) {
		String hql = "from Course c where c.teacherId='"
				+ id + "'";
		List<Course> courses = (List<Course>) this.getHibernateTemplate().find(hql);
		if (courses.size() > 0) {
			return courses.get(0);
		}
		return null;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Course> queryByPage(Page page) {
		return (List<Course>)this.getHibernateTemplate().executeFind(new HibernateCallback(){  
            public Object doInHibernate(Session session)  
                    throws HibernateException, SQLException {  
                Query query = session.createQuery("from Course c order by c.id desc");  
                query.setFirstResult(page.getBeginIndex());  
                query.setMaxResults(page.getEveryPage());  
                return query.list();  
            }
        }); 
	}

	@Override
	public int courseCounts() {
		int rowTotal=0;
		rowTotal = ((Long)this.getHibernateTemplate().find("select count(*) from Course").get(0)).intValue();
		return rowTotal;
	}

	@Override
	public List<Course> searchByName(Page page, String keyword, int style) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
