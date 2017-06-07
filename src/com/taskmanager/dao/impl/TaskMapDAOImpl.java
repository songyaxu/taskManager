package com.taskmanager.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.taskmanager.dao.TaskMapDAO;
import com.taskmanager.entity.Page;
import com.taskmanager.entity.TaskMap;

public class TaskMapDAOImpl extends HibernateDaoSupport implements TaskMapDAO{

	@Override
	public TaskMap findById(int id) {
		return this.getHibernateTemplate().get(TaskMap.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public TaskMap findByCourseNo(String no) {
		String hql = "from TaskMap t where t.courseNo='"
				+ no + "'";
		List<TaskMap> tasks = (List<TaskMap>) this.getHibernateTemplate().find(hql);
		if (tasks.size() > 0) {
			return tasks.get(0);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public TaskMap findTaskMap(int studentId, int taskId) {
		String hql = "from TaskMap t where t.taskId="
				+ taskId + " and t.studentId="+studentId;
		List<TaskMap> tasks = (List<TaskMap>) this.getHibernateTemplate().find(hql);
		if (tasks.size() > 0) {
			return tasks.get(0);
		}
		return null;
	}

	@Override
	public void delete(TaskMap taskMap) {
		this.getHibernateTemplate().delete(taskMap);
	}

	@Override
	public void save(TaskMap taskMap) {
		this.getHibernateTemplate().save(taskMap);
	}

	@Override
	public void update(TaskMap taskMap) {
		this.getHibernateTemplate().update(taskMap);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<TaskMap> queryByPage(Page page, int taskId) {
		return (List<TaskMap>)this.getHibernateTemplate().executeFind(new HibernateCallback(){  
            public Object doInHibernate(Session session)  
                    throws HibernateException, SQLException {  
                Query query = session.createQuery("from TaskMap t where t.taskId='"+taskId+"' order by t.id de   sc");  
                query.setFirstResult(page.getBeginIndex());  
                query.setMaxResults(page.getEveryPage());  
                return query.list();  
            }
        }); 
	}

	@Override
	public int taskMapCounts(int taskId) {
		int rowTotal=0;
		rowTotal = ((Long)this.getHibernateTemplate().find("select count(*) from TaskMap t where t.taskId='"+taskId+"'").get(0)).intValue();
		return rowTotal;
	}
	
}
