package com.fuhaidev.app.biz_common.config;

/**
 * Created by xumingxun on 2018/4/28.
 */
public class DbContextHolder {
    public enum DbType {
        MASTER, SLAVE
    }
    private static final ThreadLocal<DbType> contextHolder=new ThreadLocal<DbType>();

    public static void setDbType(DbType dbType){
        if(dbType==null){
            throw new NullPointerException();
        }
        contextHolder.set(dbType);
    }

    public static DbType getDbType(){
//        return contextHolder.get();
        return contextHolder.get()==null? DbType.MASTER:contextHolder.get();
    }
    public static void clearDbType(){
        contextHolder.remove();
    }
}
