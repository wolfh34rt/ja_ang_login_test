package com.sixwolfmedia.amaterasu.db;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public final class DbContext {
    private EntityManager _em;

    public EntityManager getEntityManager() {
        return _em;
    }

    public void setEntityManager(EntityManager _em) {
        this._em = _em;
    }
    
    public DbContext(String jdbcUrl, String jdbcUser, String jdbcPassword, String jdbcDriver) throws IllegalStateException {
        Map<String, String> persistenceMap = new HashMap<String, String>();

        persistenceMap.put("javax.persistence.jdbc.url", jdbcUrl);
        persistenceMap.put("javax.persistence.jdbc.user", jdbcUser);
        persistenceMap.put("javax.persistence.jdbc.password", jdbcPassword);
        persistenceMap.put("javax.persistence.jdbc.driver", jdbcDriver);
        
        try {
        	this.setEntityManager(Persistence.createEntityManagerFactory("amaterasu.db", persistenceMap).createEntityManager());
        } catch (IllegalStateException e) {
        	throw e;
        }
    }
}