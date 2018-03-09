package com.rudearts.weatherapp.model.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "city".
*/
public class CityDbDao extends AbstractDao<CityDb, Long> {

    public static final String TABLENAME = "city";

    /**
     * Properties of entity CityDb.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Name = new Property(1, String.class, "name", false, "NAME");
        public final static Property Country = new Property(2, String.class, "country", false, "COUNTRY");
        public final static Property Latitude = new Property(3, Double.class, "latitude", false, "LATITUDE");
        public final static Property Longitude = new Property(4, Double.class, "longitude", false, "LONGITUDE");
    }


    public CityDbDao(DaoConfig config) {
        super(config);
    }
    
    public CityDbDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"city\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"NAME\" TEXT," + // 1: name
                "\"COUNTRY\" TEXT," + // 2: country
                "\"LATITUDE\" REAL," + // 3: latitude
                "\"LONGITUDE\" REAL);"); // 4: longitude
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"city\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, CityDb entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
 
        String country = entity.getCountry();
        if (country != null) {
            stmt.bindString(3, country);
        }
 
        Double latitude = entity.getLatitude();
        if (latitude != null) {
            stmt.bindDouble(4, latitude);
        }
 
        Double longitude = entity.getLongitude();
        if (longitude != null) {
            stmt.bindDouble(5, longitude);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, CityDb entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
 
        String country = entity.getCountry();
        if (country != null) {
            stmt.bindString(3, country);
        }
 
        Double latitude = entity.getLatitude();
        if (latitude != null) {
            stmt.bindDouble(4, latitude);
        }
 
        Double longitude = entity.getLongitude();
        if (longitude != null) {
            stmt.bindDouble(5, longitude);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public CityDb readEntity(Cursor cursor, int offset) {
        CityDb entity = new CityDb( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // name
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // country
            cursor.isNull(offset + 3) ? null : cursor.getDouble(offset + 3), // latitude
            cursor.isNull(offset + 4) ? null : cursor.getDouble(offset + 4) // longitude
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, CityDb entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setCountry(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setLatitude(cursor.isNull(offset + 3) ? null : cursor.getDouble(offset + 3));
        entity.setLongitude(cursor.isNull(offset + 4) ? null : cursor.getDouble(offset + 4));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(CityDb entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(CityDb entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(CityDb entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}