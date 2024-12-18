/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deliveryexpress.sdeu.database;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP
 * Classe generica para administrar en la base de datos los objetos o classes
 */
public class GenericDao<T, ID> {
    private final Dao<T, ID> dao;

    public GenericDao(ConnectionSource connectionSource, Class<T> clazz) throws SQLException{
            dao = DaoManager.createDao(connectionSource, clazz);
        
    }

    /***
     * Inset entity to database
     * @param entity 
     */
    public void create(T entity) {
        try {
            dao.create(entity);
        } catch (SQLException ex) {
            Logger.getLogger(GenericDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*select statement*/
    public T read(ID id) {
        try {
            return dao.queryForId(id);
        } catch (SQLException ex) {
            Logger.getLogger(GenericDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<T> readAll()  {
        try {
            return dao.queryForAll();
        } catch (SQLException ex) {
            Logger.getLogger(GenericDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /*update statement*/
    public void update(T entity){
        try {
            dao.update(entity);
        } catch (SQLException ex) {
            Logger.getLogger(GenericDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(ID id){
        try {
            dao.deleteById(id);
        } catch (SQLException ex) {
            Logger.getLogger(GenericDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     // Método genérico para buscar por columna
    /***
     * select where
     * @param columnName
     * @param value
     * @return 
     */
    public T findByColumn(String columnName, Object value) {
        try {
            QueryBuilder<T, ID> queryBuilder = dao.queryBuilder();
            queryBuilder.where().eq(columnName, value);
            return queryBuilder.queryForFirst(); // Devuelve el primer resultado que coincide
        } catch (Exception e) {
            return null;
        }

    }
}