package com.taskmanager.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.taskmanager.dao.CourseMapDAO;
import com.taskmanager.entity.CourseMap;
import com.taskmanager.entity.Page;

public class CourseMapDAOImpl extends HibernateDaoSupport implements CourseMapDAO{

	@Override
	public CourseMap findByStudentId(int id) {
		return this.getHibernateTemplate().get(CourseMap.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public CourseMap findByStudentNo(String no) {
		String hql = "from CourseMap cm where cm.studentNo='"
				+ no + "'";
		List<CourseMap> courses = (List<CourseMap>) this.getHibernateTemplate().find(hql);
		if (courses.size() > 0) {
			return courses.get(0);
		}
		return null;
	}

	@Override
	public void update(CourseMap courseMap) {
		this.getHibernateTemplate().update(courseMap);
	}

	@Override
	public void delete(CourseMap courseMap) {
		this.getHibernateTemplate().delete(courseMap);
	}

	@Override
	public void save(CourseMap courseMap) {
		this.getHibernateTemplate().save(courseMap);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<CourseMap> queryByPage(Page page, int courseId) {
		return (List<CourseMap>)this.getHibernateTemplate().executeFind(new HibernateCallback(){  
            public Object doInHibernate(Session session)  
                    throws HibernateException, SQLException {  
                Query query = session.createQuery("from CourseMap cm where cm.courseId="+courseId);  
                query.setFirstResult(page.getBeginIndex());  
                query.setMaxResults(page.getEveryPage());  
                return query.list();  
            }
        }); 
	}

	@Override
	public int courseMapCounts(int courseId) {
		int rowTotal=0;
		rowTotal = ((Long)this.getHibernateTemplate().find("select count(*) from CourseMap cm where cm.courseId="+courseId).get(0)).intValue();
		return rowTotal;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CourseMap> queryByStudentId(int studentId) {
		String hql = "from CourseMap cm where cm.studentId='"
				+ studentId + "'";
		List<CourseMap> courses = (List<CourseMap>) this.getHibernateTemplate().find(hql);
		if (courses.size() > 0) {
			return courses;
		}
		return null;
	}
	
}
