package com.taskmanager.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.taskmanager.dao.TaskDAO;
import com.taskmanager.entity.Page;
import com.taskmanager.entity.Task;

public class TaskDAOImpl extends HibernateDaoSupport implements TaskDAO{

	@Override
	public Task findById(int id) {
		return this.getHibernateTemplate().get(Task.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Task findByCourseNo(String no) {
		String hql = "from Task t where t.courseNo='"
				+ no + "'";
		List<Task> tasks = (List<Task>) this.getHibernateTemplate().find(hql);
		if (tasks.size() > 0) {
			return tasks.get(0);
		}
		return null;
	}

	@Override
	public void delete(Task task) {
		this.getHibernateTemplate().delete(task);
	}

	@Override
	public void save(Task task) {
		this.getHibernateTemplate().save(task);
	}

	@Override
	public void update(Task task) {
		this.getHibernateTemplate().update(task);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Task> queryByPage(Page page, String courseNo) {
		return (List<Task>)this.getHibernateTemplate().executeFind(new HibernateCallback(){  
            public Object doInHibernate(Session session)  
                    throws HibernateException, SQLException {  
                Query query = session.createQuery("from Task t where t.courseNo='"+courseNo+"' order by t.id desc");  
                query.setFirstResult(page.getBeginIndex());  
                query.setMaxResults(page.getEveryPage());  
                return query.list();  
            }
        }); 
	}

	@Override
	public int taskCounts(String courseNo) {
		int rowTotal=0;
		rowTotal = ((Long)this.getHibernateTemplate().find("select count(*) from Task t where t.courseNo='"+courseNo+"'").get(0)).intValue();
		return rowTotal;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Task> stuqueryByPage(Page page,int id) {
		return (List<Task>)this.getHibernateTemplate().executeFind(new HibernateCallback(){  
            public Object doInHibernate(Session session)  
                    throws HibernateException, SQLException {  
                Query query = session.createQuery("select new com.taskmanager.entity.Task(t.id,t.name,t.courseName,t.courseId,t.teacherId,t.teacherName) from Task t,CourseMap c where t.courseId=c.courseId and c.studentId="+id+" order by t.endTime desc");  
                query.setFirstResult(page.getBeginIndex());  
                query.setMaxResults(page.getEveryPage());  
                return query.list();  
            }
        }); 
	}

	@Override
	public int stutaskCounts(int id) {
		int rowTotal=0;
		rowTotal = ((Long)this.getHibernateTemplate().find("select count(*) from Task t,CourseMap c where t.courseId=c.courseId and c.studentId="+id).get(0)).intValue();
		return rowTotal;
	}
	
}
