package com.estaine.flyxster.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by AndreyRykhalsky on 02.02.2015.
 */
public class GenericDAOImpl <ModelObject> implements GenericDAO <ModelObject> {

    static {
        TimeZone.setDefault(TimeZone.getTimeZone("Etc/UTC"));
    }

    @Autowired
    protected SessionFactory sessionFactory;

    protected Class<ModelObject> type;

    public GenericDAOImpl() {
        Type type = getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) type;
        this.type = (Class) parameterizedType.getActualTypeArguments()[0];
    }

    @Override
    public int create(ModelObject modelObject) {
        Session session = sessionFactory.getCurrentSession();
        int id = (Integer) session.save(modelObject);
        return id;
    }

    @Override
    public ModelObject get(int id) {
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

    protected Criteria buildCriteria(Map<String, Object> properties) {
        Criteria criteria  = getCurrentSession().createCriteria(type);
        List<Criterion> criterionList = new ArrayList<>();
        for(Map.Entry<String, Object> property : properties.entrySet())
            criterionList.add(Restrictions.eq(property.getKey(), property.getValue()));

        criteria.add(Restrictions.and(criterionList.toArray(new Criterion[0])));

        return criteria;
    }

    protected Map<String, Object> buildPropertyMap(String propertyName, Object propertyValue) {
        Map<String, Object> properties = new HashMap<>();
        properties.put(propertyName, propertyValue);
        return properties;
    }



    @Override
    public List<ModelObject> listByProperties(Map<String, Object> properties) {

        return buildCriteria(properties).list();
    }

    @Override
    public ModelObject getByProperties(Map<String, Object> properties) {
        return (ModelObject) buildCriteria(properties).uniqueResult();
    }

    @Override
    public List<ModelObject> listByProperty(String propertyName, Object propertyValue) {
        return buildCriteria(buildPropertyMap(propertyName, propertyValue)).list();
    }

    @Override
    public ModelObject getByProperty(String propertyName, Object propertyValue) {
        return (ModelObject) buildCriteria(buildPropertyMap(propertyName, propertyValue)).uniqueResult();
    }

    @Override
    public List<ModelObject> getAll() {
        return listByProperties(new HashMap<>());
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
