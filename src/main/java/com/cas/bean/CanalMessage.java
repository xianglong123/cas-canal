package com.cas.bean;

import java.util.List;
import java.util.Map;

/**
 * @author xiang_long
 * @version 1.0
 * @date 2022/3/5 9:11 下午
 * @desc
 */
public class CanalMessage {

    private List<Map<String, String>> data;

    private String database;

    private Long es;

    private int id;

    private boolean isDdl;

    private Map<String, String> mysqlType;

    private String old;

    private String[] pkNames;

    private String sql;

    private Map<String, Object> sqlType;

    private String table;

    private String type;

    public List<Map<String, String>> getData() {
        return data;
    }

    public void setData(List<Map<String, String>> data) {
        this.data = data;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public Long getEs() {
        return es;
    }

    public void setEs(Long es) {
        this.es = es;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isDdl() {
        return isDdl;
    }

    public void setDdl(boolean ddl) {
        isDdl = ddl;
    }

    public Map<String, String> getMysqlType() {
        return mysqlType;
    }

    public void setMysqlType(Map<String, String> mysqlType) {
        this.mysqlType = mysqlType;
    }

    public String getOld() {
        return old;
    }

    public void setOld(String old) {
        this.old = old;
    }

    public String[] getPkNames() {
        return pkNames;
    }

    public void setPkNames(String[] pkNames) {
        this.pkNames = pkNames;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public Map<String, Object> getSqlType() {
        return sqlType;
    }

    public void setSqlType(Map<String, Object> sqlType) {
        this.sqlType = sqlType;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
