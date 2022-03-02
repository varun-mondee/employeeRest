package com.rest.service;

import com.rest.model.Manager;
import com.rest.model.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Query;
import java.util.List;

@Service
public class ManagerService {

    @Autowired
    private SessionFactory factory;

    public Manager loginByCredentials(String email, String password)
    {
        Session session=factory.getSessionFactory().openSession();
        Query query= session.createQuery("from Manager e where e.email=:x and e.password=:y", Manager.class);
        query.setParameter("x",email);
        query.setParameter("y",password);
        List<Manager> manager=query.getResultList();
        if(manager!=null)
            return (Manager) manager.get(0);
        return null;
    }
}
