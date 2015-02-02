package com.estaine.flyxster.dao;

/**
 * Created by AndreyRykhalsky on 02.02.2015.
 */
public interface GenericDAO <ModelObject> {
    String create(ModelObject modelObject);
    ModelObject read(String id);
    void update(ModelObject modelObject);
    void delete(ModelObject modelObject);
}
