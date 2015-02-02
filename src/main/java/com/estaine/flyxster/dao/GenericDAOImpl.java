package com.estaine.flyxster.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by AndreyRykhalsky on 02.02.2015.
 */
public class GenericDAOImpl <ModelObject> implements GenericDAO <ModelObject> {

    @Autowired
    private SessionFactory sessionFactory;

    protected Class<ModelObject> type;

    public GenericDAOImpl() {
        Type type = getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) type;
        this.type = (Class) parameterizedType.getActualTypeArguments()[0];
    }

    @Override
    public String create(ModelObject modelObject) {
        Session session = sessionFactory.getCurrentSession();
        String id = (String) session.save(modelObject);
        return id;
    }

    @Override
    public ModelObject read(String id) {
        Session session = sessionFactory.getCurrentSession();
        ModelObject modelObject = (ModelObject) session.get(type, id);
        return modelObject;
    }

    @Override
    public void update(ModelObject modelObject) {
        Session session = sessionFactory.getCurrentSession();
        session.update(modelObject);
    }

    @Override
    public void delete(ModelObject modelObject) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(modelObject);
    }
}
