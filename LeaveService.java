package com.rest.service;

import com.rest.model.Leave;
import com.rest.model.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.hibernate.Transaction;
import javax.persistence.Query;
import javax.transaction.*;
import java.util.List;

@Service
public class LeaveService {

    @Autowired
    private SessionFactory factory;

    public List<Leave> getLeaves(Employee employee){
        Session session=factory.getSessionFactory().openSession();
       Query query= session.createQuery("from Leave a where a.employeeId=:x");
       query.setParameter("x",employee);
      List<Leave> leave= query.getResultList();
        return leave;
    }

    public List<Leave> myLeaves(String spec){
        Session session=factory.getSessionFactory().openSession();
        Query query= session.createQuery("from Leave a where a.department=:x");
        query.setParameter("x",spec);
        List<Leave> leave= query.getResultList();
        return leave;
    }

    public void acceptLeave(int id) {
        Session session=factory.getSessionFactory().openSession();
        Query query= session.createQuery("update Leave a set a.status=:y where a.aid=:z");
     Transaction trx=session.beginTransaction();
        query.setParameter("y","Accepted");
        query.setParameter("z",id);
        query.executeUpdate();
        trx.commit();
    }

    public void rejectLeave(int id) {
        Session session=factory.getSessionFactory().openSession();
        Query query= session.createQuery("update Leave a set a.status=:y where a.aid=:z");
        Transaction trx=session.beginTransaction();
        query.setParameter("y","Rejected");
        query.setParameter("z",id);
        query.executeUpdate();
        trx.commit();
    }

}
