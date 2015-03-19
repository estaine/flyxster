package com.estaine.flyxster.dao;

import java.util.List;
import java.util.Map;

/**
 * Created by AndreyRykhalsky on 02.02.2015.
 */
public interface GenericDAO<ModelObject> {

    int create(ModelObject modelObject);

    ModelObject get(int id);

    void update(ModelObject modelObject);

    void delete(ModelObject modelObject);

    List<ModelObject> listByProperty(String propertyName, Object propertyValue);

    List<ModelObject> listByProperties(Map<String, Object> properties);

    ModelObject getByProperty(String propertyName, Object propertyValue);

    ModelObject getByProperties(Map<String, Object> properties);

    List<ModelObject> getAll();

}